package com.example.wechat.controller.common;

import com.common.annotation.RestfulControllerLog;
import com.common.dto.response.ResponseData;
import com.example.wechat.dto.request.CustomerDto;
import com.example.wechat.service.BannerService;
import com.example.wechat.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转公共各类
 *
 * @author ONEC
 */
@Api(value = "common", tags = "参数公共跳转")
@Controller
public class CommonRedirectController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonRedirectController.class);

    @Autowired
    private CustomerService customerService;
    @Autowired
    private BannerService bannerService;

    @ApiOperation(value = "无权限跳转公共参数", notes = "跳转相应的文件：试图")
    @RequestMapping(value = "/common/{directory}/{respPage}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView redirect(@PathVariable("directory") String directory, @PathVariable("respPage") String respPage, HttpServletRequest request) {
        request.setAttribute("bannerList", bannerService.bannerInfo());
        return respModeView(request, directory + "/" + respPage);
    }

    @PreAuthorize(value = "hasAuthority('ROLE_USER')")
    @ApiOperation(value = "权限跳转公共参数", notes = "跳转相应的文件：试图")
    @RequestMapping(value = "/permission/{directory}/{respPage}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView redirectPermission(@PathVariable("directory") String directory, @PathVariable("respPage") String respPage, HttpServletRequest request) {
        request.setAttribute("bannerList", bannerService.bannerInfo());
        return respModeView(request, "permission/" + directory + "/" + respPage);
    }

    /**
     * 获取短信验证码：
     *
     * @return
     */
    @ApiOperation(value = "/common/requestCode", notes = "获取短信验证码")
    @PostMapping(value = "/common/requestCode")
    @RestfulControllerLog(name = "requestCode")
    @ResponseBody
    public ResponseData requestCode(@RequestBody CustomerDto customerDto) {
        return customerService.requestCode(customerDto);
    }

    ModelAndView respModeView(HttpServletRequest request, String redirectUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(redirectUrl);
        String ipAddress = request.getRemoteAddr();
        LOGGER.info("当前IP：{}：跳转地址为:{}", ipAddress, redirectUrl);
        return modelAndView;
    }
}
