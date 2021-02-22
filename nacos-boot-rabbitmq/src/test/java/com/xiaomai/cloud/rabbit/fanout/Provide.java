package com.xiaomai.cloud.rabbit.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.xiaomai.cloud.rabbit.RabbitUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author Madison
 * @date 2021/2/20
 */
@Slf4j
public class Provide {
    private static String EXCHANGE_NAME = "EXCHANGE_FANOUT";

    public static void main(String[] args) throws IOException {
        Connection connection = RabbitUtils.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明指定交换机 参数1：交换机名称(注册交换机) 参数2：交换机类型 fanout 广播类型
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout"); //广播 一条消息多个消费同时消费

        //发布消息
        channel.basicPublish(EXCHANGE_NAME,"",null,"fanout type message".getBytes("UTF-8"));

        RabbitUtils.closeChannelAndConnec(channel,connection);
    }
}
