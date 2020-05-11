package com.example.payment.controller;

import com.common.dto.response.ResponseData;
import com.example.payment.exception.ExceptionHelper;
import com.example.payment.service.online.base.BaseOnlineDefaultService;
import com.example.payment.service.utils.AnalyticParameterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * 服务出口公共controller
 *
 * @author ONEC
 */
public class BasePaymentController {

    @Autowired
    protected AnalyticParameterService analyticParameterService;

    @Autowired
    protected Map<String, BaseOnlineDefaultService> serviceApi;





    /**
     * 请求公共返回数据：成功
     *
     * @param response String
     * @return
     */
    ResponseData responseParam(String response) {
        return ResponseData.ok(response);
    }

    /**
     * 请求公共返回数据：失败
     *
     * @param logger
     * @return
     */
    ResponseData responseParam(Logger logger, Exception e) {
        return ExceptionHelper.handlerException(logger, e);
    }
}


