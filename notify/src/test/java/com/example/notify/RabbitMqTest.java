package com.example.notify;

import com.example.notify.service.rabbitmq.RabbitmqSendParam;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 测试mq（0rabbitmq）
 */
public class RabbitMqTest extends NotifyServiceApplicationTests {

    @Autowired
    RabbitmqSendParam rabbitmqSendParam;


    @Test
    public  void sendHello(){
        rabbitmqSendParam.sendHello();
    }
}
