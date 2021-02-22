package com.xiaomai.cloud.rabbit.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaomai.cloud.rabbit.RabbitUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 路由规则  *单个   #零个到多个
 * * (star) can substitute for exactly one word.
 * # (hash) can substitute for zero or more words.
 *
 * @author Madison
 * @date 2021/2/20
 */
@Slf4j
public class Provide {
    private static String EXCHANGE_NAME="EXCHANGE_TOPIC";
    private static String ROUTING_KEY="user.save";
    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机  订阅模式
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        //发布消息
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,null,("这是生产者Topic,基于路由["+ROUTING_KEY+"]消息").getBytes());

        RabbitUtils.closeChannelAndConnec(channel,connection);
    }
}
