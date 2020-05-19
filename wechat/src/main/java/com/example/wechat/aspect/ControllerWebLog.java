package com.example.wechat.aspect;

import com.common.annotation.RestfulControllerLog;
import com.common.utils.DateTimePattern;
import com.common.utils.DateUtils;
import com.example.wechat.dao.WechatLogMapper;
import com.example.wechat.entity.WechatLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * aspect 注入controller 日志：
 *
 * 设置接口返回日志
 * @author ONEC
 */
@Slf4j
@Aspect
@Component
public class ControllerWebLog {

    @Resource
    WechatLogMapper wechatLogMapper;


    private ThreadLocal<Map<String, String>> threadLocal = new ThreadLocal<>();
    /**
     * 开始时间
     */
    private static final String START_TIME = "START_TIME";
    /**
     * 开始时间
     */
    private static final String END_TIME = "END_TIME";
    /**
     * 请求参数
     */
    private static final String REQUEST_PARAM = "REQUEST_PARAM";
    private static final String REQUEST_IP = "REQUEST_IP";
    private static final String REQUEST_URL = "REQUEST_URL";
    private static final String REQUEST_METHOD = "REQUEST_METHOD";

    /**
     * 定义切入点
     */
    @Pointcut(value = "execution(* com.example.wechat.controller..*(..))")
    public void aspectControllerLog() {

    }

    /**
     * 记录请求日志：
     *
     * @param joinPoint
     * @param controllerLog
     */
    @Before(value = "aspectControllerLog() && @annotation(controllerLog)")
    public void requestParam(JoinPoint joinPoint, RestfulControllerLog controllerLog) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttributes).getRequest();
        String requestUri = request.getRequestURI();
        String ipAddress = request.getRemoteAddr();

        // 获取请求参数：
        StringBuilder sb = new StringBuilder();
        Object[] requestObj = joinPoint.getArgs();
        String requestParam = null;
        if (null != requestObj && requestObj.length > 0) {
            for (Object str : requestObj) {
                sb.append("|").append(str);
            }
            requestParam = sb.toString();
        }

        String method = controllerLog.name();
        // 存储请求参数
        Map<String, String> requestMap = new HashMap<>(6);
        requestMap.put(REQUEST_URL, requestUri);
        requestMap.put(REQUEST_IP, ipAddress);
        requestMap.put(REQUEST_METHOD, method);
        requestMap.put(REQUEST_PARAM, requestParam);
        requestMap.put(START_TIME, DateTimePattern.receiveTime(LocalDateTime.now(), "yyyy-MM-dd HH:ss:mm"));

        threadLocal.set(requestMap);
        log.info("【微信服务请求】：IP:【{}】,URI:【{}】,METHOD:【{}】,BODY:【{}】", requestMap.get(REQUEST_IP), requestMap.get(REQUEST_URL),
                requestMap.get(REQUEST_METHOD), requestMap.get(REQUEST_PARAM));
    }

    /**
     * 记录返回日志数据
     *
     * @param controllerLog
     * @param resp
     */
    @AfterReturning(value = "aspectControllerLog() && @annotation(controllerLog)", returning = "resp")
    public void responseParam(RestfulControllerLog controllerLog, Object resp) {
        Map<String, String> threadInfo = threadLocal.get();
        // 存储日志记录
        if (controllerLog.intoDb()) {
            WechatLog wechatLog = new WechatLog(1, threadInfo.get(REQUEST_METHOD), threadInfo.get(REQUEST_IP),
                    threadInfo.get(REQUEST_URL), threadInfo.get(REQUEST_PARAM), resp.toString(), DateUtils.getBusinsessDate(),
                    DateUtils.getBusinsessDate(), "微信服务请求日志");
            //wechatLog.setId(IdGen.uuidLong());
            wechatLogMapper.insert(wechatLog);
        }
        log.info("【微信服务】返回成功：IP:【{}】,URI:【{}】,METHOD:【{}】,BODY:【{}】", threadInfo.get(REQUEST_IP), threadInfo.get(REQUEST_URL),
                threadInfo.get(REQUEST_METHOD), resp.toString());
        /**
         * 必须清除
         */
        threadLocal.remove();
    }
}
