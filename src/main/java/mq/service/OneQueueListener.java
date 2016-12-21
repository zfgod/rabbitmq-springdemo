package mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sys.model.MqObject;

/**
 * author: zf
 * Date: 2016/12/20  16:13
 * Description: 此监听器接收一个队列的消息
 */
@Service("oneQueueListener")
public class OneQueueListener {
    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;
// 此方法简便,适合事务性不强的简单通信，不必继承listener
    public void onMessage(MqObject message) {
        System.out.println(message.toString());
    }
}
