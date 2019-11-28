package com.example.product.service.product;

import com.example.product.dto.ProductInfo;
import com.example.product.entity.ProductDetail;
import com.example.product.enums.TypeEnum;

/**
 * @author ONEC
 */
public interface ProductService {

    /**
     * 获取不同类的产品.
     *
     * @param type
     * @return
     */
    ProductInfo productList(TypeEnum type);

    /**
     * 获取不同类的产品.
     *
     * @param productId
     * @return
     */
    ProductInfo productId(ProductDetail productId);
}
