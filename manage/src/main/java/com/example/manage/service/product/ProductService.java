package com.example.manage.service.product;

import com.example.manage.dto.ProductInfoDto;

import java.util.List;

/**
 * @author ONEC
 */
public interface ProductService {

    List<ProductInfoDto> productList(Integer pageNo, Integer pageSize);
}
