package com.example.notify.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * rabbit mq 测试
 *
 * @author onec
 */
@Slf4j
@Component
@RabbitListener(queues = "online_order")
public class RabbitOnlineOrderConfig {


    @RabbitHandler
    public void receiverHello(String message) {
        log.info("成功接收到的参数为：{}", message);
    }

}
