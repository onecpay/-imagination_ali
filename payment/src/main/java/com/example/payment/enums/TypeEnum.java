package com.example.payment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 订单交易类型：接口请求type
 *
 * @author ONEC
 */
@Getter
@AllArgsConstructor
public enum TypeEnum {

    /**********************快捷api交易模式************/
    // 绑定交易卡
    PAYMENT_API_BINDING("绑卡交易"),

    // 获取短信验证码
    PAYMENT_API_SMS("获取短信验证码"),

    //快捷确认支付
    PAYMENT_API_PAY("快捷确认支付"),

    /**********************快捷html交易模式************/
    // 绑定交易卡
    PAYMENT_HTML_BINDING("快捷绑定交易卡"),

    //快捷确认支付
    PAYMENT_HTML_PAY("快捷确认支付"),

    /**********************支付宝************/
    // 支付宝
    PAYMENT_API_ALIPAY("支付宝支付"),

    /**********************微信************/

    PAYMENT_API_WEIXINPAY("微信支付"),

    /**********************云闪付************/

    PAYMENT_API_UNIONPAY("云闪付");


    private String message;
}
