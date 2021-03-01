package com.xiaomai.cloud.rocket.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;


import java.util.List;

/**
 * @author Madison
 * @date 2021/3/1
 */
public class Consumer {
    public static void main(String[] args) throws Exception{

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filter-group");
        //多个地址以;隔开
        consumer.setNamesrvAddr("localhost:9876");
        //订阅PushTopic下Tag  //*表示不过滤，可以通过tag来过滤，比如:”tagA”或者使用与符号“||”
        // 1/ ||  2/SQL语句
        //consumer.subscribe("filter-topic", "Tag1||Tag2");
        consumer.subscribe("filter-topic", MessageSelector.bySql(" a=3 ")); //SQL语句

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消费者消费:"+new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //返回消息消费状态
            }
        });

        consumer.setConsumeThreadMin(5);
        consumer.setConsumeThreadMax(5);

        consumer.start();
    }
}
