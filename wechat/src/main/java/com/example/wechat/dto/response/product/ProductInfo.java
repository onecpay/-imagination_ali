package com.example.wechat.dto.response.product;

import lombok.Data;

import java.util.List;

/**
 * 产品信息.
 *
 * @author ONEC
 */
@Data
public class ProductInfo {

    private long id;

    private String code;

    private ProductDetail productDetail;
    /**
     * 结算详情
     */
    private ProductSettle productSettle;
    /**
     * 平台描述
     */
    private ProductParam productParam;

    /**
     * 上游明细
     */
    private List<ProductDetail> detailList;
}
