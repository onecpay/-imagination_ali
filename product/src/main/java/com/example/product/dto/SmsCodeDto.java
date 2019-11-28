package com.example.product.dto;


import lombok.Data;

/**
 * 处理短信
 * @author ONEC
 */
@Data
public class SmsCodeDto {

    private String phone;

    private String type;

    private String smsCode;
}
