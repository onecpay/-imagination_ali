package com.example.product.mapper;

import com.example.product.entity.ProductDetail;
import com.example.product.enums.TypeEnum;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author ONEC
 */
@Repository
public interface ProductDetailMapper extends Mapper<ProductDetail> {


    /**
     * 以产品类型获取，不同的简易产品信息。
     *
     * @param type
     * @return
     */
    List<ProductDetail> selectDetailInfo(TypeEnum type);
}