package com.xiaomai.cloud.rabbit.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaomai.cloud.rabbit.RabbitUtils;

import java.io.IOException;

/**
 * @author Madison
 * @date 2021/2/20
 */
public class Provide {
    private static String EXCHANGE_NAME="EXCHANGE_DIRECT";
    private static String ROUTING_KEY_INFO="ROUTING-INFO";
    private static String ROUTING_KEY_ERROR="ROUTING-ERROR";
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明交换机 参数1：交换机名称  参数2：direct  路由模式
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        //发送消息
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY_ERROR,null,("这是direct模型发布基于route key:["+ROUTING_KEY_INFO+"]发送消息").getBytes());

        RabbitUtils.closeChannelAndConnec(channel,connection);
    }
}
