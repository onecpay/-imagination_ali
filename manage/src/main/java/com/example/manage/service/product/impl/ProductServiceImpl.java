package com.example.manage.service.product.impl;

import com.example.manage.client.product.ProductClient;
import com.example.manage.dto.ProductInfoDto;
import com.example.manage.service.product.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ONEC
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductClient productClient;


    @Override
    public List<ProductInfoDto> productList(Integer pageNo, Integer pageSize) {
//        productClient.productList();
        return null;
    }
}
