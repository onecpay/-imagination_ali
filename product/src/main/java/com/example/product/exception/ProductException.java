package com.example.product.exception;

import com.common.exception.BaseException;

/**
 * 公共异常
 * @author ONEC
 */
public class ProductException extends BaseException {

    /**
     * 自定义异常数据
     * @param code
     * @param msg
     */


    public  ProductException (Integer code ,String msg){
        super(code,msg);
    }
}
