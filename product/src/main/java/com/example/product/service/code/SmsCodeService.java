package com.example.product.service.code;

import com.example.product.dto.SmsCodeDto;

/**
 * 对接服务商短信接口.
 *
 * @author ONEC
 */
public interface SmsCodeService {


    /**
     * 发送腾讯短信.
     *
     * @param smsCodeDto
     * @param
     * @return
     */
    boolean tencentSend(SmsCodeDto smsCodeDto);


    /**
     * 验证腾讯短信.
     *
     * @param phone
     * @param smsCode
     * @return
     */
    boolean checkCode(String type, String phone, String smsCode);
}
