package com.example.notify.service.rabbitmq;

import com.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author
 */
@Slf4j
@Component
public class RabbitmqSendParam {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 测试简单队列发送消息
     */
    public void sendHello() {
        String content = "发送简单队列消息到mq:" + DateUtils.getBusinsessDay();
        log.info("发送请求参数为：{}", content);
        amqpTemplate.convertAndSend("HELLO", content);
    }


}
