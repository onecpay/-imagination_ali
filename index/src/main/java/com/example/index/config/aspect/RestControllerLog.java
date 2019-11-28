package com.example.index.config.aspect;

import com.common.annotation.RestfulControllerLog;
import com.common.exception.BaseException;
import com.example.index.exception.ManageException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * AspectLog :添加日志：
 *
 * @author ONEC
 */
@Component
@Aspect
@Slf4j
public class RestControllerLog {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private static final String START_TIME = "startTime";
    private static final String IP = "ip";
    private static final String REQUEST_PARAMS = "requestParams";

    /**
     * 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
     * execution("返回值" "拦截的路劲"..*("拦截的参数"))：
     * <p>
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)
     * modifiers-pattern：方法的操作权限
     * ret-type-pattern：返回值
     * declaring-type-pattern：方法所在的包
     * name-pattern：方法名
     * parm-pattern：参数名
     * throws-pattern：异常
     */
    @Pointcut("execution(* com.example.index.controller..*(..))")
    public void aspectServiceLog() {

    }

    /**
     * 前置通知
     *
     * @param joinPoint
     */
    @Before(value = "aspectServiceLog() && @annotation(controllerLog)")
    public void requestParam(JoinPoint joinPoint, RestfulControllerLog controllerLog) {
        // 接收请求：记录请求日志：
        ServletRequestAttributes requestAttribute = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(requestAttribute).getRequest();

        log.info("微信服务请求：URL:{},IP:{},METHOD:{},", request.getRequestURL(),
                request.getRemoteAddr(), joinPoint.getSignature().getName());
        // 开始时间。
        long startTime = System.currentTimeMillis();

        Map<String, Object> threadInfo = new HashMap<>();
        threadInfo.put(START_TIME, startTime);
        threadInfo.put(IP, request.getRemoteAddr());
//        StringBuilder requestStr = new StringBuilder();
//        Object[] args = joinPoint.getArgs();
//        if (args != null && args.length > 0) {
//            for (Object arg : args) {
//                requestStr.append(arg.toString());
//            }
//        }
        threadInfo.put(REQUEST_PARAMS, joinPoint.getArgs());
        threadLocal.set(threadInfo);
    }

    @AfterReturning(value = "aspectServiceLog() && @annotation(controllerLog)", returning = "object")
    public void responseAfter(RestfulControllerLog controllerLog, Object object) {

        Map<String, Object> threadInfo = threadLocal.get();

        if (controllerLog.intoDb()) {

        }
        threadLocal.remove();
        log.info("{}接口结束调用:耗时={}ms,result={}", controllerLog.name(),null
                , object);

    }

    /**
     * 配置后置通知,aspectServiceLog()上注册的切入点
     *
     * @After :后置通知
     * @Around ： 环绕通知：
     * @AfterReturning ： 后置返回通知：
     * @AfterThrowing(pointcut="(", throwing="ex")： 抛出异常通知
     */
    @AfterReturning(value = "aspectServiceLog()", returning = "object")
    public void responseParam(Object object) {
        if (log.isInfoEnabled()) {
            log.info("通知返回数据：{} ", object);
        }
    }

    /**
     * controller异常：切入
     *
     * @param e
     * @return
     */
    @AfterThrowing(value = "aspectServiceLog()", throwing = "e")
    public void responseThrow(BaseException e) {

        if (ManageException.class.isAssignableFrom(e.getClass())) {
            log.error("切入面：aspect:产品异常：{}");
        }
        log.error("切入面：aspect:异常日志：{}", e.getMsg());
    }

    /**
     * 增强环绕通知
     *
     * @param joinPoint
     * @param controllerLog
     */
    @Around(value = "aspectServiceLog() && @annotation(controllerLog)")
    public void responseAround(JoinPoint joinPoint, RestfulControllerLog controllerLog) {


    }
}
