package com.example;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.example.product.controller.code.SmsCodeController;
import com.example.product.controller.product.ProductController;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {

    @Autowired
    SmsCodeController smsCodeController;

    @Autowired
    ProductController productController;

    @Test
    public void contextLoads() {
        JSONObject re = new JSONObject();
        re.put("phone", "15611321516");
        re.put("type", "templateId_reg");

        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(re));
        log.info("测试请求返回数据：{}", smsCodeController.requestCode(requestData));
    }


    @Test
    public void contextProductInfo() {
        JSONObject re = new JSONObject();
        re.put("type", "P000A");

        RequestData requestData = new RequestData();
        requestData.setBizContent(JSONObject.toJSONString(re));
        //log.info("测试请求返回数据：{}", productController.productList(requestData));
    }
}
