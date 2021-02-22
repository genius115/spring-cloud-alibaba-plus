package com.xiaomai.cloud.rabbit.topic;

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
    private static String EXCHANGE_NAME="EXCHANGE_TOPIC";
    private static String ROUTING_KEY="user.#";

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机及交换机类型 订阅模式
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //临时队列
        String queue = channel.queueDeclare().getQueue();

        //绑定交换机和队列  动态统配符形式route key
        channel.queueBind(queue,EXCHANGE_NAME,ROUTING_KEY);

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("消费者2：Message=="+new String(body));
            }
        });


    }
}
