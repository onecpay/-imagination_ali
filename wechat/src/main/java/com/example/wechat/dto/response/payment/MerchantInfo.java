package com.example.wechat.dto.response.payment;

import lombok.Data;

import java.util.List;

/**
 * 产品信息.
 *
 * @author ONEC
 */
@Data
public class MerchantInfo {

    private long id;

    private String code;

    private MerchantDetail productDetail;
    /**
     * 平台描述
     */
    private MerchantParam productParam;

    /**
     * 上游明细
     */
    private List<MerchantDetail> detailList;
}
