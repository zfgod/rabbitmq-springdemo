package mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * author: zf
 * Date: 2016/12/20  16:13
 * Description: 此监听器接收一个队列的消息
 */
@Service("oneQueueListener")
public class OneQueueListener implements MessageListener{
    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;

    public void onMessage(Message message) {

        MessageProperties messageProperties = message.getMessageProperties();
        String s = message.getBody().toString();
        System.out.println(s);
    }
}
