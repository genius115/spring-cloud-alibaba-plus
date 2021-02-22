package com.xiaomai.cloud.rabbit.helloworld;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

/**
 * @author Madison
 * @date 2021/2/19
 */
@Slf4j
public class TestRabbitmqProvide {
    private final static String QUEUE_NAME = "hello";

    //生产消息
    @Test
    public void RabbitPublish() throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        try {
            factory.setUri("amqp://msg:msg@localhost:5672/%2Fmsg");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        /**
        factory.setUsername("msg");
        factory.setPassword("msg");
        factory.setVirtualHost("/msg");
        factory.setHost("localhost");
        factory.setPort(5672);
        **/

        //创建连接对象
        Connection connection = factory.newConnection();
        //创建连接中的通道
        Channel channel = connection.createChannel();
        //通道绑定对应的消息队列
        //参数1：队列名称
        //参数2：用来定义队列特性是否持久化    true 持久化队列 false 不持久化
        //参数3：exclusive 是否独占队列   true 独占队列 false 不独占队列
        //参数4：autoDelete 是否在消费完成后自动删除队列    true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //发布消息
        //参数1 交换机名称 参数2：队列名称 参数3：传递消息额外信息  MessageProperties.PERSISTENT_TEXT_PLAIN 消息持久化
        //参数4：消息的具体内容
        channel.basicPublish("",QUEUE_NAME,null,(DateUtil.currentSeconds()+"---Hello RabbitMQ").getBytes());


//        String message = "Hello World!";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");
//
//        Thread.sleep(3000);
//
//        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        //System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String recvMessage = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + recvMessage + "'");
//        };
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
//

        //消费消息
//        channel.basicConsume("queue-hello-test", true, "myConsumerTag",new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag,
//                                       Envelope envelope,
//                                       AMQP.BasicProperties properties,
//                                       byte[] body)
//                    throws IOException
//            {
//                String routingKey = envelope.getRoutingKey();
//                String contentType = properties.getContentType();
//                long deliveryTag = envelope.getDeliveryTag();
//                log.info(new String(body));
//                channel.basicAck(deliveryTag, false);
//            }
//        });

        channel.close();
        connection.close();

        TestRabbitmqProvide.log.info("消息生产完成。。。");
    }
}
