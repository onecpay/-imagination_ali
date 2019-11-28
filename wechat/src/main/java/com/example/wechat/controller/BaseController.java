package com.example.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.response.ResponseData;
import netscape.javascript.JSObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ONEC
 */
public class BaseController {

    protected ModelAndView respModelView(String viewName, String message) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(viewName);
        mv.addObject("message", message);
        mv.setStatus(HttpStatus.OK);
        return mv;
    }

    protected ResponseData responseData(Object object) {
        return ResponseData.ok(JSONObject.toJSONString(object));
    }
}
