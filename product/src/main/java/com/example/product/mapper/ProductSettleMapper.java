package com.example.product.mapper;

import com.example.product.entity.ProductSettle;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author ONEC
 */
@Repository
public interface ProductSettleMapper extends Mapper<ProductSettle> {

    /**
     * @param code
     * @return
     */
    ProductSettle selectSetterCode(String code);
}