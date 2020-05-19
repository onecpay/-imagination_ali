package com.example.manage.controller;

import com.common.dto.response.ResponseData;
import com.example.manage.dto.ResponseLayui;

/**
 * @author ONEC
 */
public class BaseController {

    protected ResponseLayui responseData(Object object, double size) {
        return ResponseLayui.ok(object, size);
    }

    /**
     * 返回api成功
     * @return
     */
    protected ResponseData responseData() {
        return ResponseData.ok();
    }
}
