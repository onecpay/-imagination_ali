package com.example.wechat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置数据跨域问题：
 * 跨域问题我相信在使用前后台分理的时候肯定会出现这种问题
 * @author  来自网络
 */
@Configuration
public class CorsFilterConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("*");
        List<String> allowedMethods = new ArrayList<>();
        allowedMethods.add("*");
        List<String> allowedHeaders = new ArrayList<>();
        allowedHeaders.add("*");
        List<String> exposedHeaders = new ArrayList<>();
        exposedHeaders.add("Link");
        exposedHeaders.add("X-Total-Count");
        corsConfiguration.setAllowedOrigins(allowedOrigins);
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setAllowedHeaders(allowedHeaders);
        corsConfiguration.setExposedHeaders(exposedHeaders);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(1800L);
        source.registerCorsConfiguration("/api/**", corsConfiguration);
        return new CorsFilter(source);
    }
}
