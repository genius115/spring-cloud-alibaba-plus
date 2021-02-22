package com.xiaomai.cloud.rabbit.helloworld;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Madison
 * @date 2021/2/20
 */
@Slf4j
public class TestRabbitmqConsumer {
    private final static String QUEUE_NAME = "hello";

    //消费消息  一直保持监听队列，并消费
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/msg");
        factory.setUsername("msg");
        factory.setPassword("msg");

        //创建连接对象
        Connection connection = factory.newConnection();

        //创建连接中的通道
        Channel channel = connection.createChannel();

        //通道绑定对应的消息队列
        //参数1：队列名称
        //参数2：用来定义队列特性是否持久化 true 持久化队列 false 不持久化
        //参数3：exclusive 是否独占队列 true 独占队列 false 不独占队列
        //参数4：autoDelete 是否在消费完成后自动删除队列 true 自动删除 false 不自动删除
        //参数5：额外附加参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //消费消息
        //参数1 消费那个队列的消息 参数2：开启消息的自动确认机制 参数3：消费消息的回调接口
        //注意：主线程与回调线程，junit为单线程，可能主线程已经完成，单回调未执行
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel){
            @Override //最后一个参数，消息队列中取出的参数
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                TestRabbitmqConsumer.log.info("Message==="+new String(body));
            }
        });

        //channel.close();
        //connection.close();
    }
}
