package com.xiaomai.cloud.rabbit.fanout;

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
    private static String EXCHANGE_NAME = "EXCHANGE_FANOUT";

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //通道绑定交换机 参数1：交换机名称(注册交换机) 参数2：交换机类型 fanout 广播类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //临时队列
        String queue = channel.queueDeclare().getQueue();

        //绑定交换机和队列
        channel.queueBind(queue,EXCHANGE_NAME,"");

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("消费者2--Message=="+new String(body));
            }
        });


    }
}