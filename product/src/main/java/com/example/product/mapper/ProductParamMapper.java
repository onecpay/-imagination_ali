package com.example.product.mapper;

import com.example.product.entity.ProductParam;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ONEC
 */
@Repository
public interface ProductParamMapper extends Mapper<ProductParam> {

    /**
     *
     * @param code
     * @return
     */
    ProductParam selectParamCode(String code);
}