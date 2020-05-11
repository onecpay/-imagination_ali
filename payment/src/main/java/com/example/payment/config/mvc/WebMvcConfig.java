package com.example.payment.config.mvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebMvcConfig {


    /**
     * 格式化gosn 时间
     *
     * @return
     */
    @Bean
    Gson gson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    }
}
