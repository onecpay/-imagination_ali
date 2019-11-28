package com.example.notify.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbit mq 测试
 *
 * @author onec
 */
@Configuration
public class RabbitConfig {

    /**
     * 创建最简单的队列
     * 可发送字符：对象等：
     * @return
     */
    @Bean
    public Queue createQueue() {
        return new Queue("HELLO");
    }

}
