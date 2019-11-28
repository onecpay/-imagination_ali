package com.example.product.controller;

import com.common.dto.response.ResponseData;

/**
 * @author ONEC
 */
public class BaseController {

    /**
     * 请求公共返回数据：成功
     *
     * @param response String
     * @return
     */
    protected ResponseData success(String response) {
        return ResponseData.ok(response);
    }

    /**
     * 请求公共返回数据：成功
     *
     * @param response String
     * @return
     */
    protected ResponseData error(String response) {
        return ResponseData.error(response);
    }
}
