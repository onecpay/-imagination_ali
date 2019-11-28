package com.example.manage.controller;

import com.example.manage.dto.ResponseLayui;

/**
 * @author ONEC
        */
public class BaseController {

    protected ResponseLayui responseData(Object object, double size) {
        return ResponseLayui.ok(object, size);
    }
}
