//package com.example.product.controller;
//
//import com.common.dto.response.ResponseData;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author ONEC
// */
//@RestController
//@RequestMapping(value = "product")
//@Api(tags = "产品服务restful 接口")
//@Slf4j
//public class ProductController extends BaseController {
//
//    @RequestMapping(value = "respProductName")
//    @ApiOperation(value = "获取产品名称", notes = "通过产品id 获取产品名称：")
//    public String respProductName(String productName) {
//        return productName;
//    }
//
//    /**
//     * 使用了异步线程
//     *
//     * @param productCode
//     * @return
//     */
//    @RequestMapping(value = "respProductCode")
//    @ApiOperation(value = "获取产品编码", notes = "通过产品id 获取产品编码：(使用了异步线程)")
//    @Async(value = "asyncExecutor")
//    public String respProductCode(String productCode) {
//
//        log.info("接收到产品编号请求：{}", productCode);
//        return productCode;
//    }
//
//    /**
//     * 全局异常：没有生效
//     *
//     * @param productCode
//     * @return
//     */
//    @RequestMapping(value = "respProductException")
//    @ApiOperation(value = "测试产品异常处理", notes = "生成product全局异常：是否可以呗base 异常捕获")
//    public ResponseData respProductException(String productCode) {
//
//        log.info("接收到产品编号请求：{}", productCode);
////        if (true) {
////            throw new ProductException(100001, "生成产品异常数据");
////        }
////        if(productCode.equals("")){
////
////        }
//        return ResponseData.ok(productCode);
//    }
//}
