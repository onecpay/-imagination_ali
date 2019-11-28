package com.example.wechat.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author ONEC
 */
@Data
public class ProductSettle {


    /**
     * 产品编码
     */
    private String code;

    /**
     * 通道结算费
     */
    private Long amount;

    /**
     * 会员结算费
     */
    private Long customerPrice;

    /**
     * 非会员结算费
     */
    private Long price;

    /**
     * 结算周期
     */
    private Integer settlePeriod;

    /**
     * 贷款费率
     */
    private BigDecimal loansFee;

    /**
     * 贷款最高金额
     */
    private Double loansMaxAmount;

}