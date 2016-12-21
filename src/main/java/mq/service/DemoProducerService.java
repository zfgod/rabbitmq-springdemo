package mq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sys.model.MqObject;

/**
 * author: zf
 * Date: 2016/12/20  14:22
 * Description: 消息发送
 */
@Service
public class DemoProducerService {
    @Autowired
    @Qualifier(value = "directExChangeTemplate")
    RabbitTemplate hasExChangeTemplate;//此template指定了默认的交换机

    @Autowired
    @Qualifier(value = "noExChangeTemplate")
    RabbitTemplate noExChangeTemplate;//此template没有指定默认的交换机

    //
    public void sendMessage(MqObject object){
        String exchange = hasExChangeTemplate.getExchange();
//        也可以更改交换机
//        template.setExchange("zf_direct_queue");
        hasExChangeTemplate.setEncoding("UTF-8");
        hasExChangeTemplate.setRoutingKey("zf_direct_queue");
        hasExChangeTemplate.convertAndSend(object);
    }

    public void sendMessageInDetail(String exchangeName,String routingKey,MqObject object){
        String exchange = noExChangeTemplate.getExchange();
        noExChangeTemplate.setEncoding("UTF-8");
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
