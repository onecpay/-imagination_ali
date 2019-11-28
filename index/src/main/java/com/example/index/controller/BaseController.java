package com.example.index.controller;

import com.example.index.dto.ResponseLayui;

/**
 * @author ONEC
        */
public class BaseController {

    protected ResponseLayui responseData(Object object, double size) {
        return ResponseLayui.ok(object, size);
    }
}
