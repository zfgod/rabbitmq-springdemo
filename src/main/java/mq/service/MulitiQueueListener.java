package mq.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

/**
 * author: zf
 * Date: 2016/12/20  16:13
 * Description: 此监听器接收多个队列的消息
 * // acknowledge="auto"
 *
 */
@Service("mulitiQueueListener")
public class MulitiQueueListener implements MessageListener{

// auto模式自动确认消息，可以继承MessageListener即可，不需要获取channel来确认消息
    public void onMessage(Message message) {
        try {
            String s =  new String(message.getBody(),"UTF-8");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
