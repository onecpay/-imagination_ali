package com.example.wechat.controller.center;

import com.common.annotation.RestfulControllerLog;
import com.common.dto.response.ResponseData;
import com.example.wechat.controller.BaseController;
import com.example.wechat.dto.request.CustomerDto;
import com.example.wechat.entity.security.Customer;
import com.example.wechat.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录注册管理：
 *
 * @author ONEC
 */
@Api(value = "user manage：", tags = "用户登录管理菜单")
@Controller
@RequestMapping(value = "manage")
public class LoginController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 用户登录页面：
     *
     * @return
     */
    @ApiOperation(value = "/doLogin", notes = "用户登录接口")
    @GetMapping(value = "/odLogin")
    public ModelAndView login() {
        return respModelView("/center/user", "请求有误");
    }

    /**
     * 用户注册页面：
     *
     * @return
     */
    @ApiOperation(value = "register", notes = "用户注册接口")
    @PostMapping(value = "doRegister")
    @RestfulControllerLog(name = "register")
    @ResponseBody
    public ResponseData register(@RequestBody CustomerDto customerDto) {
        Customer customer = customerService.saveCustomer(customerDto);
        return responseData(customer);
    }

    /**
     * 测试
     * master
     *
     * @return
     */
    @RequestMapping(value = "whoIm")
    @ApiOperation(value = "whoIm", notes = "master 测试")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @ResponseBody
    public Object whoIm() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


}
