package com.example.product.service.product.impl;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.StringUtils;
import com.common.utils.redis.RedisKeyUtil;
import com.example.product.dto.ProductInfo;
import com.example.product.entity.ProductDetail;
import com.example.product.entity.ProductParam;
import com.example.product.entity.ProductSettle;
import com.example.product.enums.TypeEnum;
import com.example.product.mapper.ProductDetailMapper;
import com.example.product.mapper.ProductParamMapper;
import com.example.product.mapper.ProductSettleMapper;
import com.example.product.service.product.ProductService;
import com.example.product.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ONEC
 * 前端api 接口产品服务 。
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    protected ProductDetailMapper detailMapper;
    @Resource
    protected ProductSettleMapper settleMapper;
    @Resource
    protected ProductParamMapper paramMapper;

    /**
     * 依据产品类型。获取产品信息.
     *
     * @param type
     * @return
     */
    @Override
    public ProductInfo productList(TypeEnum type) {

        String redisKey = RedisKeyUtil.getKey(RedisKeyUtil.SERVICE_NAME_PRODUCT, KeyUtil.TABLE_BANNER_NAME_DETAIL,
                type.name());
        String productList = stringRedisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotBlank(productList)) {
            return JSONObject.parseObject(productList, ProductInfo.class);
        }
        List<ProductDetail> detailList = detailMapper.selectDetailInfo(type);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setDetailList(detailList);
        stringRedisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(productInfo),
                RedisKeyUtil.getExiperTime(), TimeUnit.SECONDS);
        return productInfo;
    }

    @Override
    public ProductInfo productId(ProductDetail productId) {
        String redisKey = RedisKeyUtil.getKey(RedisKeyUtil.SERVICE_NAME_PRODUCT, KeyUtil.TABLE_BANNER_NAME_DETAIL,
                String.valueOf(productId.getId()));
        String productList = stringRedisTemplate.opsForValue().get(redisKey);
        if (StringUtils.isNotBlank(productList)) {
            return JSONObject.parseObject(productList, ProductInfo.class);
        }
        // 详情：
        ProductDetail detail = detailMapper.selectOne(productId);
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductDetail(detail);

        ProductSettle settle = settleMapper.selectSetterCode(detail.getCode());
        productInfo.setProductSettle(settle);

        ProductParam param = paramMapper.selectParamCode(detail.getCode());
        productInfo.setProductParam(param);

        stringRedisTemplate.opsForValue().set(redisKey, JSONObject.toJSONString(productInfo), RedisKeyUtil.getExiperTime(), TimeUnit.SECONDS);
        return productInfo;
    }
}
