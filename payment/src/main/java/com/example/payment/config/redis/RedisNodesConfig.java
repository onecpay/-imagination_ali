package com.example.payment.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * springboot 集群redis
 * jedis
 * @author ONEC
 */
@Component
@Configuration
public class RedisNodesConfig {

    /**
     * 获取集群redis ip
     */
    @Value("${spring.redis.cluster.nodes}")
    private String clusterNodes;

    @Value("${spring.redis.timeout}")
    private Integer redisTimeOut;

    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private Integer maxWait;

    @Value("${spring.redis.password}")
    private String password;


    /**
     * 获取jedis 集群
     *
     * @return
     */
    @Bean
    public JedisCluster getJedisCluster() {
        String[] nodes = clusterNodes.split(",");
        Set<HostAndPort> cluster = new HashSet<>();
        for (String node : nodes) {
            String[] param = node.split(":");
            cluster.add(new HostAndPort(param[0], Integer.parseInt(param[1])));
        }
        // 创建redis 集群
        JedisCluster jedisCluster = new JedisCluster(cluster, maxWait, redisTimeOut, maxActive,  password, new GenericObjectPoolConfig());
        return jedisCluster;
    }
}
