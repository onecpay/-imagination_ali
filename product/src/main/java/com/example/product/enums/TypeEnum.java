package com.example.product.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 产品类型
 *
 * @author ONEC
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TypeEnum {
    /**
     * 网申
     */
    P000A(10000, "网申"),
    /**
     * 网申
     */
    P000B(10001, "网贷"),
    /**
     * 网申
     */
    P000C(10002, "收款"),
    /**
     * 收款
     */
    P000D(10003, "还款");

    private Integer code;

    private String message;

}
