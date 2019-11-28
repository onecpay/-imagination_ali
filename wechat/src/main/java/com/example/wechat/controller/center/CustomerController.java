package com.example.wechat.controller.center;

import com.alibaba.fastjson.JSONArray;
import com.common.annotation.RestfulControllerLog;
import com.common.dto.response.ResponseData;
import com.example.wechat.controller.BaseController;
import com.example.wechat.dto.request.CustomerDto;
import com.example.wechat.entity.customer.CustomerExtra;
import com.example.wechat.service.CustomerService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录注册管理：
 *
 * @author ONEC
 */
@Api(value = "user manage：", tags = "用户登录管理菜单")
@Controller
@RequestMapping(value = "customer")
public class CustomerController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    /**
     * 补充信息.
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "extra")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @RestfulControllerLog(name = "extraInfo")
    public ResponseData extraInfo(@RequestBody CustomerExtra customerExtra, HttpServletRequest request) {
        UserDetails customer = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        customerExtra.setCustomerNo((customer.getUsername()));

        CustomerExtra customerExtra1 = customerService.saveCustomerExtra(customerExtra);
        List<String> images = Collections.singletonList(customerExtra1.getCustomerUrl());
        request.setAttribute("images", images);
        return ResponseData.ok("信息补充完成");
    }

    /**
     * 查看我的信息.
     *
     * @return
     */
    @RequestMapping(value = "customerInfo")
    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @RestfulControllerLog(name = "customerInfo")
    public ModelAndView customerInfo(CustomerDto customerDto, HttpServletRequest request) {
        UserDetails customer = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        customerDto.setPhone(customer.getUsername());
        CustomerExtra customerExtra = customerService.customerInfo(customerDto);
        JSONArray jsonArray = JSONArray.parseArray(customerExtra.getCustomerUrl());
        List customerUrl = new ArrayList<>(jsonArray);

        request.setAttribute("images", customerUrl);
        request.setAttribute("customerInfo", customerExtra);
        return respModelView("permission/personal/customerInfo", "查询成功");
    }
}
