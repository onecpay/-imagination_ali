package com.example.product.service.product.impl;

import com.example.product.dto.ProductInfo;
import com.example.product.enums.TypeEnum;
import com.example.product.service.product.ProductManageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author ONEC
 * 运营平台 接口产品服务 。
 */
@Slf4j
@Service
public class ProductManageServiceImpl implements ProductManageService {


    /**
     * 依据产品类型。获取产品信息.
     *
     * @param type
     * @return
     */
    @Override
    public ProductInfo productList(TypeEnum type) {


        return null;
    }


}
