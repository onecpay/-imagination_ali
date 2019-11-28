package com.example.wechat.controller;

import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
import com.example.wechat.exception.CustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * spring 配置全局异常:
 * 配置该异常处理时：不可使用try-catch 捕获异常：
 *
 * @author ONEC
 */
@Slf4j
@ControllerAdvice(basePackages = "com.example.wechat")
public class ExceptionHandlerAdvice {

    private static final String HEARDS_AJAX = "XMLHttpRequest";

    /**
     * 捕获404
     *
     * @param ex
     * @param ex
     * @return
     */
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public Object noHandlerFoundHandler(HttpServletRequest request, NoHandlerFoundException ex) {
        ex.printStackTrace();
        return responseData(request, "failed/failed", "访问地址有误");
    }

    /**
     * 处理系统全局异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return responseData(request, "failed/failed", "系统繁忙稍后再试");
    }

    /**
     * 处理自定义异常（controller and service）
     *
     * @param
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public Object handlerBaseException(HttpServletRequest request, BaseException e) {
        return responseData(request, "failed/failed", e.getMsg());
    }

    /**
     * 处理无权限异常（controller and service）
     *
     * @param
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    public Object handlerBaseException(HttpServletRequest request, AccessDeniedException e) {
        return responseData(request, "failed/failed", "抱歉您暂无该权限");
    }

    /**
     * 处理客户自定义异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = CustomerException.class)
    public Object handlerCustomerException(HttpServletRequest request, CustomerException e) {
        e.printStackTrace();
        return responseData(request, "failed/failed", e.getMsg());
    }


    /**
     * 判断是否ajax
     *
     * @param request
     * @return
     */
    private Object responseData(HttpServletRequest request, String redirectUrl, String message) {
        String header = request.getHeader("X-Requested-With");
        if (HEARDS_AJAX.equals(header)) {
            return ResponseData.error(message);
        }
        return responseModel(redirectUrl, message);
    }


    /**
     * 返回失败页面：
     *
     * @param message
     * @return
     */
    ModelAndView responseModel(String redirectUrl, String message) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(redirectUrl);
        mv.addObject("respMsg", message);
        return mv;
    }
}
