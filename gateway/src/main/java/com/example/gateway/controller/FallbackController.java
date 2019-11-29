package com.example.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认失败返回
 */
@RestController
public class FallbackController {
    @RequestMapping("/defaultFallback")
    public Map defaultFallback() {
        Map map = new HashMap<>();
        map.put("code", 1);
        map.put("message", "服务异常");
        return map;
    }

}
