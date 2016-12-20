package mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * author: zf
 * Date: 2016/12/20  16:13
 * Description: 此监听器接收一个队列的消息
 */
@Service("sameQueueListener2")
public class SameQueueListener2 implements MessageListener{
    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;

    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        Map<String, Object> headers = messageProperties.getHeaders();
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            System.out.println(value.toString());
        }
    }
}
