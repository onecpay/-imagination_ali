package com.example.gateway.config;


import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * 全局配置类
 *
 * @author yangzhongying
 */
public class Global {

    /**
     * 获取配置
     *
     * @param key
     * @return String
     */
    public static String getConfig(String key) {
        return SpringContextHolder.getBean(ConfigurableEnvironment.class).getProperty(key);
    }

    public static boolean isDevMode() {
        String devMode = getConfig("platform.dev.mode");
        return StringUtils.isEmpty(devMode) || "true".equals(devMode) || "1".equals(devMode);
    }
}