package com.example.wechat.service;

import com.example.wechat.entity.customer.BannerInfo;

import java.util.List;


/**
 * @author ONEC
 */
public interface BannerService {


    /**
     * 用户注册：分配权限
     *
     * @param bannerInfo
     * @return Banner
     */
    BannerInfo saveBanner(BannerInfo bannerInfo);

    /**
     * 用户注册：补充信息
     *
     * @param bannerInfo
     * @return Banner
     */
    BannerInfo editBanner(BannerInfo bannerInfo);

    /**
     * 查询我的信息
     *
     * @param
     * @return
     */
    List<BannerInfo> bannerInfo();

}
