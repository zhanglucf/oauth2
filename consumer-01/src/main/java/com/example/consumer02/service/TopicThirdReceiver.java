package com.example.consumer02.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author zhenhua zhang
 * @data 2020/1/15
 */
@Component
@RabbitListener(queues = "topic.test")
public class TopicThirdReceiver {

    @RabbitHandler
    public void process(Map testMessage) {
        System.out.println("TopicThirdReceiver消费者收到消息  : " + testMessage.toString());
        String receiveTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println("#### 消息接收到的数据" + receiveTime);
    }
}
