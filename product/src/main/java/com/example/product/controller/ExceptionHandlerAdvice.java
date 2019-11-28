package com.example.product.controller;

import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
import com.example.product.exception.ProductException;
import lombok.extern.slf4j.Slf4j;;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * spring 配置全局异常:
 * 配置该异常处理时：不可使用try-catch 捕获异常：
 * @author ONEC
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.example.product")
public class ExceptionHandlerAdvice {

    /**
     * 处理产品系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseData handlerException(Exception e) {
        e.printStackTrace();
        return ResponseData.error("服务系统异常");
    }

    /**
     * 处理自定义异常（controller and service）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseData handlerBaseException(BaseException e){
        //e.printStackTrace();
        return  ResponseData.error(e.getMsg());
    }

    @ExceptionHandler(value = ProductException.class)
    public ResponseData handlerProductException(ProductException e){
        //e.printStackTrace();
        return  ResponseData.error(e.getMsg());
    }
}
