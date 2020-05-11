package com.example.wechat.service.payment;

import com.example.wechat.dto.request.ProductDto;
import com.example.wechat.dto.response.product.ProductDetail;
import com.example.wechat.dto.response.product.ProductInfo;

import java.util.List;


/**
 * 微信商戶交易交互。
 * @since 20200511
 * @author ONEC
 */
public interface PaymentOrderService {


    /**
     * 产品集合
     *
     * @param typeEnum
     * @return Banner
     */
    List<ProductDetail> productList(ProductDto typeEnum);

    /**
     * 产品集合
     *
     * @param
     * @return Banner
     */
    ProductInfo productDetail(String id);
}
