package com.example.product.controller.product;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.request.RequestData;
import com.common.dto.response.ResponseData;
import com.example.product.controller.BaseController;
import com.example.product.entity.ProductDetail;
import com.example.product.enums.TypeEnum;
import com.example.product.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ONEC
 */
@RestController
@RequestMapping(value = "detail")
@Api(tags = "产品信息服务restful 接口")
@Slf4j
public class ProductController extends BaseController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "productList")
    @ApiOperation(value = "获取当前所有产品", notes = "获取当前所有可用产品")
    public ResponseData productList(@RequestBody RequestData requestData) {

        ProductDetail detail = JSONObject.parseObject(requestData.getBizContent(), ProductDetail.class);
        log.info("产品服务：获取当前的所有的产品参数：{}", detail);
        return ResponseData.ok(JSONObject.toJSONString(productService.productList(detail.getType())));
    }

    /**
     * 使用了异步线程
     *
     * @param
     * @return
     */
    @RequestMapping(value = "productId")
    @ApiOperation(value = "产品ID获取产品详细信息", notes = "产品ID获取产品详细信息")
    public ResponseData productId(@RequestBody RequestData requestData) {
        ProductDetail detail = JSONObject.parseObject(requestData.getBizContent(), ProductDetail.class);
        return ResponseData.ok(JSONObject.toJSONString(productService.productId(detail)));
    }

    /**
     * 全局异常：没有生效
     *
     * @param productCode
     * @return
     */
    @RequestMapping(value = "respProductException")
    @ApiOperation(value = "测试产品异常处理", notes = "生成product全局异常：是否可以呗base 异常捕获")
    public ResponseData respProductException(String productCode) {

        log.info("接收到产品编号请求：{}", productCode);
        return ResponseData.ok(productCode);
    }


}
