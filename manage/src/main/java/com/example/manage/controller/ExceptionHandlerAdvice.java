package com.example.manage.controller;

import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
import com.example.manage.exception.ManageException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

;import javax.servlet.http.HttpServletRequest;

/**
 * spring 配置全局异常:
 * 配置该异常处理时：不可使用try-catch 捕获异常：
 *
 * @author ONEC
 */
@Slf4j
@ControllerAdvice(basePackages = "com.example.product")
public class ExceptionHandlerAdvice {

    /**
     * 处理产品系统异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object handlerException(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return responseData(request, "", e.getMessage());
    }

    /**
     * 处理自定义异常（controller and service）
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    public Object handlerBaseException(BaseException e) {
        e.printStackTrace();
        return ResponseData.error(e.getMsg());
    }

    @ExceptionHandler(value = ManageException.class)
    public Object handlerProductException(HttpServletRequest request, ManageException e) {
        e.printStackTrace();
        return responseData(request, "", e.getMessage());
    }

    /**
     * 处理无权限异常.
     *
     * @param e UnauthorizedException
     * @return
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public Object handlerProductException(HttpServletRequest request, AuthorizationException e) {
        e.printStackTrace();
        return responseData(request, "", e.getMessage());
    }

    /**
     * 处理无角色异常.
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public Object handlerProductException(HttpServletRequest request, UnauthorizedException e) {
        e.printStackTrace();
        return responseData(request, "", "角色访问权限不足");
    }

    /**
     * 判断是否ajax
     *
     * @param request
     * @return
     */
    private Object responseData(HttpServletRequest request, String redirectUrl, String message) {
        String header = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(header);
        if (isAjax) {
            return ResponseData.error(message);
        }
        return responseModel(redirectUrl, message);
    }

    /**
     * 返回视图
     *
     * @param redirectUrl
     * @param message
     * @return
     */
    private ModelAndView responseModel(String redirectUrl, String message) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", message);
        mv.setViewName(redirectUrl);
        return mv;
    }
}
