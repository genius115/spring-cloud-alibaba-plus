package com.xiaomai.cloud.rabbit.workqueue;

import cn.hutool.core.date.DateUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import com.xiaomai.cloud.rabbit.RabbitUtils;

import java.io.IOException;

/**
 * @author Madison
 * @date 2021/2/20
 */
public class Provide {
    private static String QUEUE_NAME="WORK_QUEUE";

    public static void main(String[] args) throws IOException {
        //1、获取连接
        Connection connection = RabbitUtils.getConnection();
        //2、建立通道
        Channel channel = connection.createChannel();
        //3、通道绑定队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //4、发布消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("",QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,(i+"--"+DateUtil.currentSeconds()+"--WORk_QUEUE").getBytes());
        }

        RabbitUtils.closeChannelAndConnec(channel,connection);
    }
}
