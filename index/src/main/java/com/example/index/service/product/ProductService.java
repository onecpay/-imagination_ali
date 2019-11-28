package com.example.index.service.product;

import com.example.index.dto.ProductInfoDto;

import java.util.List;

/**
 * @author ONEC
 */
public interface ProductService {

    List<ProductInfoDto> productList(Integer pageNo, Integer pageSize);
}
