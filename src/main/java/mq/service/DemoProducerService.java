package mq.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sys.model.MqObject;

/**
 * author: zf
 * Date: 2016/12/20  14:22
 * Description:
 */
@Service
public class DemoProducerService {
    @Autowired
    @Qualifier(value = "directExChangeTemplate")
    RabbitTemplate hasExChangeTemplate;

    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;

    //
    public void sendMessage(MqObject object){
        String exchange = hasExChangeTemplate.getExchange();
//        也可以更改交换机
//        template.setExchange("zf_direct_queue");
        hasExChangeTemplate.setRoutingKey("zf_direct_queue");
        hasExChangeTemplate.convertAndSend(object);
    }

    public void sendMessageInDetail(String exchangeName,String routingKey,MqObject object){
        String exchange = noExChangeTemplate.getExchange();
        noExChangeTemplate.convertAndSend(exchangeName,routingKey,object);
    //设置队列优先级
//        noExChangeTemplate.convertAndSend(exchangeName, routingKey, object, new MessagePostProcessor() {
//            public Message postProcessMessage(Message message) throws AmqpException {
//                message.getMessageProperties().setPriority(1);
//                return message;
//            }
//        });
    }
}
