package com.example.index.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转公共各类
 *
 * @author ONEC
 */
@Api(value = "common", tags = "参数公共跳转")
@Controller
@RequestMapping(value = "common")
public class CommonRedirectController {

    @ApiOperation(value = "无权限跳转公共参数", notes = "跳转相应的文件：试图")
    @RequestMapping(value = "/{directory}", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView redirect(@PathVariable("directory") String directory) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(directory);
        return mv;
    }


}
