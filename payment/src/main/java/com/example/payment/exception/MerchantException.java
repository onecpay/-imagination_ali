package com.example.payment.exception;

import com.common.exception.BaseException;

/**
 * 公共异常
 *
 * @author ONEC
 */
public class MerchantException extends BaseException {

    /**
     * 自定义异常数据
     *
     * @param code
     * @param msg
     */
    public MerchantException(Integer code, String msg) {
        super(code, msg);
    }
}
