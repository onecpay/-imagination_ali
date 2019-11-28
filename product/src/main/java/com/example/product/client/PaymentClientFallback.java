package com.example.product.client;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ComponentScan(basePackages = "com.example.product.client")
public class PaymentClientFallback implements PaymentClient {
    @Override
    public void requestPayment(JSONObject requestJson) {
        log.info("收款服务请求降级：");
    }
}
