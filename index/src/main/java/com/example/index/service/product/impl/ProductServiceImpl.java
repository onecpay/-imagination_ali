package com.example.index.service.product.impl;

import com.example.index.service.product.ProductService;
import com.example.index.dto.ProductInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ONEC
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Override
    public List<ProductInfoDto> productList(Integer pageNo, Integer pageSize) {
        return null;
    }
}
