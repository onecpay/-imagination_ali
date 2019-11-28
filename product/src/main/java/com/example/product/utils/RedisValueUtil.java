package com.example.product.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ONEC
 */
@Component
public class RedisValueUtil {

    /**
     *      * RedisTemplate支持泛型
     *     
     */
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    /**
     *      * stringRedisTemplate不支持泛型，它的类型是<String,String>
     *     
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /**
     *      * 保存redis缓存
     *      * @param key
     *      * @param value
     *     
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     *      * 获取redis缓存
     *      * @param key
     *      * @return
     *     
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
