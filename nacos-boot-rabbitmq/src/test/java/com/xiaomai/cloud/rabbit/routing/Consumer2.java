package com.xiaomai.cloud.rabbit.routing;

import com.rabbitmq.client.*;
import com.xiaomai.cloud.rabbit.RabbitUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Madison
 * @date 2021/2/20
 */
@Slf4j
public class Consumer2 {
    private static String EXCHANGE_NAME="EXCHANGE_DIRECT";

    private static String ROUTING_KEY_INFO="ROUTING-INFO";

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明交换机 参数1：交换机名称  参数2：direct  路由模式
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        //临时队列
        String queue = channel.queueDeclare().getQueue();

        //队列与交换换机绑定
        channel.queueBind(queue,EXCHANGE_NAME,ROUTING_KEY_INFO);

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("消费者2（info）："+new String(body));
            }
        });


    }
}
