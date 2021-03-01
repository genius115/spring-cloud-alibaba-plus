package com.xiaomai.cloud.rocket.base;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;

/**
 * @author Madison
 * @date 2021/3/1
 */
public class AsyncConsumer {
    public static void main(String[] args) throws MQClientException {

        //定义消费组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("base-group");

        //多个地址以;隔开
        consumer.setNamesrvAddr("localhost:9876");

        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        //如果非第一次启动，那么按照上次消费的位置继续消费  CONSUME_FROM_FIRST_OFFSET
        //consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //订阅PushTopic下Tag  //*表示不过滤，可以通过tag来过滤，比如:”tagA”或者使用与符号“||”
        consumer.subscribe("Async-topic", "base-tag-1");

        /*
         * 注册消息监听回调这里有两种监听，MessageListenerConcurrently以及MessageListenerOrderly
         * 前者是普通监听，后者是顺序监听
         */
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(
                    List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消费者消费数据:"+new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS; //返回消息消费状态
            }
        });
        //启动消费者
        consumer.start();
    }
}
