package com.example.manage.client.payment;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ONEC
 */
@FeignClient(name = "payment", path = "payment", fallback = PaymentClientFallback.class)
public interface PaymentClient {

    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void requestPayment(@RequestBody JSONObject requestJson);
}
