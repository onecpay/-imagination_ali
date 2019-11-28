package com.example.payment.util;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * 公用redis 操作类
 *
 * @author ONEC
 */
public class RedisUtil {

    @Resource
    private JedisCluster jedisCluster;

    /**
     * 存储string ：key：value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    /**
     * 存储对象
     *
     * @param key
     * @param t
     * @param expire
     * @param <T>
     */
    public <T> void setObject(String key, T t, Integer expire) {
        jedisCluster.setex(key, expire, JSONObject.toJSONString(t));
    }
}
