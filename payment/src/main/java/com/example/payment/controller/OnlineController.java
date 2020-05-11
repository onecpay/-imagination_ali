package com.example.payment.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.example.payment.dto.request.OnlineParam;
import com.example.payment.dto.response.ResponseOnline;
import com.example.payment.enums.PaymentTypeEnum;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 交易数据统一出入口
 *
 * @author ONEC
 */
@Slf4j
@Api(value = "online", tags = "公共入口")
@RestController
@RequestMapping(value = "/online")
public class OnlineController extends BasePaymentController {

    /**
     * 创建交易订单.
     *
     * @param requestData
     * @return
     */
    @ApiOperation(value = "payment", tags = "公共入口")
    @RequestMapping(value = "/api/payment")
    public ResponseData payment(@RequestBody @Valid RequestData requestData) {

        // 数据及商户验签：
        OnlineParam onlineParam = null;
        // 请求上游交易：
        ResponseOnline responseOrder = null;

        PaymentTypeEnum requestMethod = PaymentTypeEnum.valueOf(requestData.getService());
        try {
            responseOrder = serviceApi.get(requestMethod.name()).execute(onlineParam);
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            return responseParam(log, e);
        }
        return responseParam(JSONObject.toJSONString(responseOrder));
    }
}
