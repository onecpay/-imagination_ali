package com.example.wechat.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * springboot 数据源参数配置：
 *
 * @author ONEC
 */
@Data
@Component
@ConfigurationProperties(value = "spring.datasource.master")
public class MasterDataProperties {


    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;

    /**
     * 配置最大连接池的数量。
     */
    private int maxActive;
    /**
     * 最小连接池的数量。
     */
    private int minIdle;
    /**
     * 初始化建立物理链接的个数：
     */
    private int initialSize;
    /**
     * 获取链接时的最大等待时间：单位为毫秒
     */
    private int maxWait;
    private int timeBetweenEvictionRunsMillis;
    private int minEvictableIdleTimeMillis;


    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturns;
    private boolean useSSL;

    private String validationQuery;

}
