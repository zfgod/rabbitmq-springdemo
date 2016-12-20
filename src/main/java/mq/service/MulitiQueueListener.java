package mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Service;

/**
 * author: zf
 * Date: 2016/12/20  16:13
 * Description: 此监听器接收多个队列的消息
 * // acknowledge="manual"
 * // 第一次收到消息之后，没有确认，不会接着收到消息
 *
 */
@Service("mulitiQueueListener")
public class MulitiQueueListener implements MessageListener{
    public void onMessage(Message message) {
        MessageProperties messageProperties = message.getMessageProperties();
        String s = message.getBody().toString();
        System.out.println(s);
    }
}
