package com.example.manage.exception;

import com.alibaba.fastjson.JSONObject;
import com.common.exception.BaseException;
import com.example.manage.dto.ResponseLayui;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常数据处理
 *
 * @author ONEC
 * @date 2019-04-16
 */
public class ExceptionHelper {


    public static ResponseLayui handlerException(Logger logger, Exception e) {
        Integer code = 20000;
        String msg = "服务请求异常";
        Map<String, String> errorMap = new HashMap<>();
        if (e instanceof ManageException) {
            code = ((BaseException) e).getCode();
            msg = ((BaseException) e).getMsg();
        }else if(e instanceof UnauthorizedException){
            code = 9000;
            msg = "權限不足，請勿訪問";
        }
        errorMap.put("code", String.valueOf(code));
        errorMap.put("msg", msg);

        logger.error("管理系请求异常：{}", msg);
        return ResponseLayui.error(JSONObject.toJSONString(errorMap));
    }
}
