package com.example.product.content;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ONEC
 */
public class SmsCode {


    /**
     * 腾讯云短息服务.
     */
    public static int APP_ID = 1400256236;
    /**
     * 短信应用 SDK AppKey
     */
    public static String APP_KEY = "26a3484f612fe20388081b6ff4d9bdb1";
    /**
     * 短信模板 ID，需要在短信应用中申请
     * NOTE: 这里的模板 ID`7839`只是示例，真实的模板 ID 需要在短信控制台中申请：
     */
    //public static Integer templateId[] = {427841, 44444};
    /**
     * NOTE: 签名参数使用的是`签名内容`，而不是`签名ID`。这里的签名"腾讯云"只是示例，真实的签名需要在短信控制台申请
     */
    public static String smsSign = "小样视界";

    public static final Map<String, Integer> templateId = new HashMap<>();

    static {
        templateId.put("templateId_reg", 427841);
        templateId.put("templateId_login", 427841);
        templateId.put("12", 427841);
        templateId.put("11", 427841);
    }
}
