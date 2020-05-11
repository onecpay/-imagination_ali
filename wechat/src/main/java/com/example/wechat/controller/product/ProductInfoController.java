package com.example.wechat.controller.product;

import com.common.annotation.RestfulControllerLog;
import com.common.dto.response.ResponseData;
import com.example.wechat.controller.BaseController;
import com.example.wechat.dto.request.ProductDto;
import com.example.wechat.dto.response.product.ProductDetail;
import com.example.wechat.dto.response.product.ProductInfo;
import com.example.wechat.enums.TypeEnum;
import com.example.wechat.service.product.ProductInfoService;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录注册管理：
 *
 * @author ONEC
 */
@Api(value = "user manage：", tags = "产品服务管理")
@Controller
@RequestMapping(value = "product")
public class ProductInfoController extends BaseController {

    @Resource
    ProductInfoService productService;

    /**
     * 获取产品信息.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "coList")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @RestfulControllerLog(name = "coList")
    public ResponseData coList(@RequestParam("type") TypeEnum type) {
        ProductDto productDto = new ProductDto();
        productDto.setType(type);
        List<ProductDetail> infoDtoList = productService.productList(productDto);
        return ResponseData.ok("信息补充完成");
    }

    /**
     * 获取产品信息.
     *
     * @return
     */
    @RequestMapping(value = "productList")
    @RestfulControllerLog(name = "productList")
    public ModelAndView productList(@RequestParam("type") TypeEnum type, HttpServletRequest request) {
        ProductDto productDto = new ProductDto();
        productDto.setType(type);
        List<ProductDetail> productList = productService.productList(productDto);
        request.setAttribute("productInfo", productList);
        return respModelView("product/productInfo", "查询成功");
    }

    /**
     * 获取产品信息.
     *
     * @return
     */
    @RequestMapping(value = "/detail/{id}")
    @RestfulControllerLog(name = "detail")
    public ModelAndView detail(@PathVariable("id") String id, HttpServletRequest request) {
        ProductInfo productInfo = productService.productDetail(id);
        request.setAttribute("detail", productInfo.getProductDetail());
        request.setAttribute("settle", productInfo.getProductSettle());
        request.setAttribute("param", productInfo.getProductParam());
        return respModelView("product/detail", "查询成功");
    }
}
