package mq.service;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sys.model.MqObject;

import java.util.HashMap;
import java.util.Map;

/**
 * author: zf
 * Date: 2016/12/20  14:22
 * Description: 消息发送
 */
@Service
public class BaseChannelService {

    @Autowired
    @Qualifier(value = "mqConnectionFactory")
    private ConnectionFactory connectionFactory;

    /**
     * 发送消息
     * @param object
     */
    public void sendMessageInDetail(MqObject object) {
        Connection connection = null;
        Channel channel = null;
        try {
//        获取链接,创建通道
//        声明交换机：如果没有就创建,如果已存在,则声明它的属性必须和此前的属性一致:要么换交换机
            connection = connectionFactory.createConnection();//默认直接打开连接
            boolean open = connection.isOpen();
//          channel开启事务,发送消息完毕需要提交事务
            boolean transaction = false;
            channel = connection.createChannel(transaction);//默认直接开启通道,与事务无关
            boolean open1 = channel.isOpen();
            String exchangeName = object.getExchange();
            boolean durable1 = true;//持久化
            boolean auto_delete1 = false;//自动删除
//            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT); //direct fanout topic
            channel.exchangeDeclarePassive(exchangeName);//如果已经存在
//            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT,durable1); //无则新建
//        声明队列：如果没有就创建,如果已存在,则声明它的属性必须和此前的属性一致:要么换队列
            String queueName = "channel.declare.queue";
            boolean durable = true;//持久化
            boolean exclusive = false;//排他性
            boolean auto_delete = false;//自动删除
            Map<String,Object> map = new HashMap<String, Object>();
            //如果不需要新建队列,直接获取已经定义好的队列
            AMQP.Queue.DeclareOk declareOk = channel.queueDeclarePassive(queueName);
//          channel.queueDeclare(queueName,durable,exclusive,auto_delete,map);//无则新建
//          队列与交换机的绑定
            String bindingKeyOrPattern = "a.b.c";
            // 指定direct类型的交换机时,消息的routingKey必须为 "a.b.c" 才会发送到此队列中,
            // 如果交换机与队列都已存在且已经有绑定规则，可以不再进行绑定
            channel.queueBind(queueName, exchangeName, bindingKeyOrPattern);
//需要绑定路由键
            channel.basicAck(1,true);
//            String routingKey = object.getRoutingKey();
            String routingKey = "a.b.c";
            channel.basicPublish(exchangeName, routingKey,
                    MessageProperties.PERSISTENT_TEXT_PLAIN, object.toString().getBytes());
//          channel开启事务,发送消息完毕需要提交事务
          if(transaction){
              channel.txCommit();
          }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(channel!=null){
                   channel.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 接收消息
     * @param object
     */
    public void recevieMessage(MqObject object) {
        Connection connection = null;
        Channel channel = null;
        try {
//        获取链接,创建通道
//        声明交换机：如果没有就创建,如果已存在,则声明它的属性必须和此前的属性一致:要么换交换机,要么去管理界面重新定义此交换机
            connection = connectionFactory.createConnection();
            boolean transaction = false;
            channel = connection.createChannel(false);


            channel.basicAck(1,true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(channel!=null){
                    channel.close();
                }
                if(connection!=null){
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
