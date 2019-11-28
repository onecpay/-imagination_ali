package com.example.manage.controller.product;

import com.common.dto.response.ResponseData;
import com.example.manage.controller.BaseController;
import com.example.manage.dto.ProductInfoDto;
import com.example.manage.dto.ResponseLayui;
import com.example.manage.service.product.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ONEC
 */
@RestController
@RequestMapping(value = "product")
@Api(tags = "产品服务restful 接口")
@Slf4j
public class ProductController extends BaseController {


    @Autowired
    private ProductService productService;

    /**
     * 获取所有产品。
     *
     * @return
     */
    @RequestMapping(value = "productList")
    public ResponseLayui productList(@RequestParam("page") Integer pageNo, @RequestParam("limit") Integer pageSize) {
        List<ProductInfoDto> productInfoDto = productService.productList(pageNo, pageSize);
        return ResponseLayui.ok(productInfoDto, productInfoDto.size());
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
//        if (true) {
//            throw new ProductException(100001, "生成产品异常数据");
//        }
//        if(productCode.equals("")){
//
//        }
        return ResponseData.ok(productCode);
    }
}
