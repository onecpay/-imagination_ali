package com.example.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author ONEC
 */

@Getter
@AllArgsConstructor
@ToString
public enum ProductEnum {

    /**
     * 所有产品
     */
    DEFAULT("所有产品", 0),
    /**
     * 微信支付
     */
    WEIXIN("微信支付", 1),
    /**
     * 阿里支付
     */
    ALIPAY("阿里支付", 2),
    /**
     * 快捷支付
     */
    QUICK("快捷支付", 3),
    /**
     * 申请状态成功
     */
    REPAYMENT("还款", 4),
    /**
     * 申请状态成功
     */
    UNIONPAY("云闪付", 5);

    private String msg;

    private Integer index;

}
