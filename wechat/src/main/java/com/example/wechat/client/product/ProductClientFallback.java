package com.example.wechat.client.product;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author ONEC
 */
@Slf4j
@Component
@ComponentScan(basePackages = "com.example.wechat.client")
public class ProductClientFallback implements ProductClient {

    @Override
    public String requestCode(RequestData requestData) {
        log.info("产品服务code请求降级：");
        throw new BaseException(20001, "系统繁忙稍后再试");
    }

    @Override
    public String checkSmsCode(RequestData requestData) {
        log.info("产品服务code_check请求降级：");
        throw new BaseException(20001, "系统繁忙稍后再试");
    }

    @Override
    public JSONObject productList(RequestData requestData) {
        log.info("产品服务detail请求降级：");
        throw new BaseException(20001, "系统繁忙稍后再试");
    }

    @Override
    public JSONObject productDetail(RequestData requestData) {
        log.info("产品服务productDetail请求降级：");
        throw new BaseException(20001, "系统繁忙稍后再试");
    }
}
