package com.example.wechat.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.common.utils.redis.RedisKeyUtil;
import com.example.wechat.dao.customer.BannerInfoMapper;
import com.example.wechat.entity.customer.BannerInfo;
import com.example.wechat.enums.StatusEnum;
import com.example.wechat.service.BannerService;
import com.example.wechat.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 获取轮播图。
 *
 * @author ONEC
 */
@Slf4j
@Service
public class BannerServiceImpl implements BannerService {

    @Resource
    ValueOperations<String, Object> valueOperations;
    @Resource
    BannerInfoMapper bannerInfoMapper;

    @Override
    public BannerInfo saveBanner(BannerInfo bannerInfo) {
        return null;
    }

    @Override
    public BannerInfo editBanner(BannerInfo bannerInfo) {
        return null;
    }

    /**
     * 获取轮播图。redis中以string 类型存储。
     *
     * @return
     */
    @Override
    public List<BannerInfo> bannerInfo() {
        String bannerListKey = RedisKeyUtil.getKey(RedisKeyUtil.SERVICE_NAME_WECHAT, KeyUtil.TABLE_BANNER_NAME,
                KeyUtil.CODE_BANNER);
        String bannerList = (String) valueOperations.get(bannerListKey);
        if (!StringUtils.isBlank(bannerList)) {
            return JSONObject.parseObject(bannerList, new TypeReference<List<BannerInfo>>() {
            });
        }
        List<BannerInfo> bannerInfo = bannerInfoMapper.selectAll();
        bannerInfo = bannerInfo
                .stream()
                .filter(e -> StatusEnum.AVAILABLE.name().equals(e.getStatus()))
                .collect(Collectors.toList());
        log.info("首页轮播图获取成功：{}", bannerInfo);
        valueOperations.set(bannerListKey, JSONObject.toJSONString(bannerInfo), RedisKeyUtil.getExiperTime(), TimeUnit.SECONDS);
        return bannerInfo;
    }
}
