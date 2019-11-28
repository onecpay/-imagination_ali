package com.example.payment.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.example.payment.dto.request.OnlineOrderParam;
import com.example.payment.dto.response.ResponseOrder;
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
@Api(value = "order", tags = "订单数据出入口")
@RestController
@RequestMapping(value = "/order")
public class OrderController extends BasePaymentController {

    /**
     * 创建交易订单
     *
     * @param requestData
     * @return
     */
    @ApiOperation(value = "createOrder", tags = "创建交易订单数据入口")
    @RequestMapping(value = "/api/createOrder")
    public ResponseData createOrder(@RequestBody @Valid RequestData requestData) {

        // 数据及商户验签：
        OnlineOrderParam orderParam = null;
        // 请求上游交易：
        ResponseOrder responseOrder = null;

        PaymentTypeEnum requestMethod = PaymentTypeEnum.valueOf(requestData.getService());
        try {
            responseOrder = payments.get(requestMethod.name()).execute(orderParam);
        } catch (Exception e) {
            e.printStackTrace();
            // 异常处理
            return responseParam(log, e);
        }
        return responseParam(JSONObject.toJSONString(responseOrder));
    }
}
