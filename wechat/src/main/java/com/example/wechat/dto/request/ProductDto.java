package com.example.wechat.dto.request;

import com.example.wechat.enums.TypeEnum;
import lombok.Data;

/**
 * @author ONEC
 * 产请求参数
 */
@Data
public class ProductDto {

    /**
     * 产品类型
     */
    private TypeEnum type;

    /**
     * 产品编号
     */
    private String id;

}
