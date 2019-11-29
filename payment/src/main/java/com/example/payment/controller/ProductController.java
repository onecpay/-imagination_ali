package com.example.payment.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.common.dto.response.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ONEC
 */
@RestController
@RequestMapping(value = "product")
@Api(tags = "产品服务restful 接口")
@Slf4j
public class ProductController  {

    @RequestMapping(value = "respProductName")
    @ApiOperation(value = "获取产品名称", notes = "通过产品id 获取产品名称：")
    @SentinelResource(value = "protected-resource", blockHandler = "handleBlock")
    public String respProductName(String productName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productName;
    }

    public String handleBlock(Integer id, BlockException e) {
        return "限流控制成功";
    }

    /**
     * 使用了异步线程
     *
     * @param productCode
     * @return
     */
    @RequestMapping(value = "respProductCode")
    @ApiOperation(value = "获取产品编码", notes = "通过产品id 获取产品编码：(使用了异步线程)")
    @Async(value = "asyncExecutor")
    public String respProductCode(String productCode) {

        log.info("接收到产品编号请求：{}", productCode);
        return productCode;
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

        log.info("payment 服务接收到请求：{}", productCode);
        return ResponseData.ok(productCode);
    }
}
