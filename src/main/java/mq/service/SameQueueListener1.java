package mq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.AcknowledgeMode;
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

//    public void onMessage(MqObject object) {
//        System.out.println(object.toString());
//
//    }

    public void onMessage(Message message, Channel channel) throws Exception {
        MessageProperties messageProperties = message.getMessageProperties();
//        channel.basicQos();
        channel.basicAck(1,true);
    }
  /*
  final int messageCount = 3;
    boolean result = template.execute(new ChannelCallback<Boolean>() {

        @Override
        public Boolean doInRabbit(final Channel channel) throws Exception {
            int n = messageCount;
            channel.basicQos(messageCount); // prefetch
            long deliveryTag = 0;
            while (n > 0) {
                GetResponse result = channel.basicGet("si.test.queue", false);
                if (result != null) {
                    System.out.println(new String(result.getBody()));
                    deliveryTag = result.getEnvelope().getDeliveryTag();
                    n--;
                }
                else {
                    Thread.sleep(1000);
                }
            }
            if (deliveryTag > 0) {
                channel.basicAck(deliveryTag, true);
            }
            return true;
        }
    });
    */
}
