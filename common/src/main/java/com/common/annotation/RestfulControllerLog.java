package com.common.annotation;



import java.lang.annotation.*;

/**
 * 自定义运行时controller 日志注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface RestfulControllerLog {

    /**
     * 请求方法名
     *
     * @return name
     */
    String name();

    /**
     * 是否保存日志记录
     *
     * @return boolean
     */
    boolean intoDb() default false;
}
