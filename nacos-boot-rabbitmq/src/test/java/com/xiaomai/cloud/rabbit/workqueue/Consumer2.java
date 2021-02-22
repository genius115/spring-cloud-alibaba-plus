package com.xiaomai.cloud.rabbit.workqueue;

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

    private static String QUEUE_NAME="WORK_QUEUE";

    public static void main(String[] args) throws IOException {
        //1、获取连接
        Connection connection = RabbitUtils.getConnection();
        //2、建立通道
        Channel channel = connection.createChannel();

        channel.basicQos(1); //每次消费一条数据
        //3、通道绑定队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //4、消费消息  参数2：true 自动确认（接收即确认）  false 手动确认
        channel.basicConsume(QUEUE_NAME,false, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                log.info("消费者2--Message=="+new String(body));
                RabbitUtils.doWork(new String(body));
                //手动确认 参数1：确认队列中那个具体消息   参数2：是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

        // RabbitUtils.closeChannelAndConnec(channel,connection);
    }

}
