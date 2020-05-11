package com.example.wechat.service.product.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.common.dto.request.RequestData;
import com.example.wechat.client.product.ProductClient;
import com.example.wechat.content.ResultStatus;
import com.example.wechat.dto.request.ProductDto;
import com.example.wechat.dto.response.product.ProductDetail;
import com.example.wechat.dto.response.product.ProductParam;
import com.example.wechat.dto.response.product.ProductSettle;
import com.example.wechat.dto.response.product.ProductInfo;
import com.example.wechat.exception.CustomerException;
import com.example.wechat.service.product.ProductInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ONEC
 * 获取产品信息
 */
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Resource
    private ProductClient productClient;

    @Override
    public List<ProductDetail> productList(ProductDto productDto) {
        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(productDto));
        JSONObject responseData = productClient.productList(requestData);

        JSONObject detailList = productResult(responseData);

        return JSONObject.parseObject(JSONObject.toJSONString(detailList.get("detailList")),
                new TypeReference<List<ProductDetail>>() {
                });
    }

    @Override
    public ProductInfo productDetail(String id) {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(productDto));

        JSONObject responseData = productClient.productDetail(requestData);
        JSONObject detailList = productResult(responseData);

        ProductDetail detail = JSONObject.parseObject(detailList.getString("productDetail"), ProductDetail.class);
        ProductSettle settle = JSONObject.parseObject(detailList.getString("productSetter"), ProductSettle.class);
        ProductParam param = JSONObject.parseObject(detailList.getString("productParam"), ProductParam.class);

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductDetail(detail);
        productInfo.setProductSettle(settle);
        productInfo.setProductParam(param);

        return productInfo;
    }


    JSONObject productResult(JSONObject responseData) {
        if (ResultStatus.SUCCESS_CODE != responseData.getInteger(ResultStatus.SUB_CODE)) {
            throw new CustomerException(50001, responseData.getString(ResultStatus.SUB_MSG));
        }
        return JSONObject.parseObject(responseData.getString(ResultStatus.DATA));
    }


}
