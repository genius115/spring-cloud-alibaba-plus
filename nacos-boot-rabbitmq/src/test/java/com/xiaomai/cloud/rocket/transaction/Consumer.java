package com.xiaomai.cloud.rocket.transaction;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author Madison
 * @date 2021/2/25
 * @Function 消息消费者
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction-group");

        //多个地址以;隔开
        consumer.setNamesrvAddr("localhost:9876");

        //订阅PushTopic下Tag  //*表示不过滤，可以通过tag来过滤，比如:”tagA”或者使用与符号“||”
        consumer.subscribe("transaction-topic", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消息队列ID = [" + msg.getMsgId() + "],线程名称：["+Thread.currentThread().getName()+"],消息"+new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //返回消息消费状态
            }
        });
        consumer.start();
    }
}