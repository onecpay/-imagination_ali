package com.example.wechat.exception;

import com.common.exception.BaseException;

/**
 * 自定义用户异常
 * @author ONEC
 */
public class CustomerException extends BaseException {

    /**
     * @param code
     * @param msg
     */
    public CustomerException(Integer code, String msg) {
        super(code, msg);
    }
}
