package com.example.manage.exception;

import com.common.exception.BaseException;

/**
 * 公共异常
 * @author ONEC
 */
public class ManageException extends BaseException {

    /**
     * 自定义异常数据
     * @param code
     * @param msg
     */


    public  ManageException (Integer code ,String msg){
        super(code,msg);
    }
}
