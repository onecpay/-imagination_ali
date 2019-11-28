package com.example.payment.exception;

import com.alibaba.fastjson.JSONObject;
import com.common.dto.response.ResponseData;
import com.common.exception.BaseException;
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


    public static ResponseData handlerException(Logger logger, Exception e) {
        Integer code = 20000;
        String msg = "服务请求异常";
        Map<String, String> errorMap = new HashMap<>();
        if (e instanceof BaseException) {
            code = ((BaseException) e).getCode();
            msg = ((BaseException) e).getMsg();
        }
        errorMap.put("code", String.valueOf(code));
        errorMap.put("msg", msg);

        logger.error("收款服务请求错误：{}", msg);
        return ResponseData.error(JSONObject.toJSONString(errorMap));
    }
}
