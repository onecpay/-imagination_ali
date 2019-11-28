package com.example.manage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author
 * @date 2018/1/22 20:50
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(baseInterceptor).excludePathPatterns("/static/**", "/res/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

//        registry
//                .addResourceHandler("/upload/**")
//                .addResourceLocations("file:" + MyUtils.getUploadFilePath() + "upload/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
