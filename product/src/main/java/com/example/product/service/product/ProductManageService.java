package com.example.product.service.product;

import com.example.product.dto.ProductInfo;
import com.example.product.entity.ProductDetail;
import com.example.product.enums.TypeEnum;

/**
 * 管理平台产品服务
 * @author ONEC
 */
public interface ProductManageService {

    /**
     * 获取不同类的产品.
     *
     * @param type
     * @return
     */
    ProductInfo productList(TypeEnum type);

}
