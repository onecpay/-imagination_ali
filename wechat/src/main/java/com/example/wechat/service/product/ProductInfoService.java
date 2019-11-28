package com.example.wechat.service.product;

import com.example.wechat.dto.request.ProductDto;
import com.example.wechat.dto.response.ProductDetail;
import com.example.wechat.dto.response.product.ProductInfo;

import java.util.List;


/**
 * @author ONEC
 */
public interface ProductInfoService {


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
