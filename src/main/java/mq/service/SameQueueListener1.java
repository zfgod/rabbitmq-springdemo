package mq.service;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
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
@Service("sameQueueListener1")
public class SameQueueListener1 implements ChannelAwareMessageListener {
    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;

//    此方法可以在auto ack下使用，且不继承listener,只需在queue配置中指定此方法即可
//    public void onMessage(MqObject object) {
//        System.out.println(object.toString());
//
//    }

    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            MessageProperties properties = message.getMessageProperties();
            long deliveryTag = properties.getDeliveryTag();
//      测试：先确认消息
            channel.basicAck(deliveryTag,true);
            byte[] body = message.getBody();
            System.out.println(body.toString());
            String s = new String(body, "UTF-8");
            System.out.println(s);
            MqObject mqObject1 = JSONObject.parseObject(s, MqObject.class);
            System.out.println(mqObject1.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
