package com.example.payment.controller;

import com.common.dto.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 路由数据接口：
 *
 * @author ONEC
 */
@Api(value = "/route", tags = "路由数据接口")
@RestController
@RequestMapping(value = "/route")
public class RouteController extends BasePaymentController {

    @ApiOperation(value = "", tags = "获取路由通道数据")
    @RequestMapping(value = "responseRoute")
    public ResponseData responseRoute() {
        return responseParam("");
    }
}
