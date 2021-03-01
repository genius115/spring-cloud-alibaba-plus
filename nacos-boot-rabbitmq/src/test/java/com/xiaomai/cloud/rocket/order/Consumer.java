package com.xiaomai.cloud.rocket.order;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Date;
import java.util.List;

/**
 * 基本消息-消费者
 * @author Madison
 * @date 2021/2/25
 * @Function 消息消费者
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws MQClientException {

        //1、定义消费组
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("base-group");

        //2、多个地址以;隔开
        consumer.setNamesrvAddr("localhost:9876");

        //设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        //如果非第一次启动，那么按照上次消费的位置继续消费  CONSUME_FROM_FIRST_OFFSET
        //consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);

        //3、订阅PushTopic下Tag  //*表示不过滤，可以通过tag来过滤，比如:”tagA”或者使用与符号“||”
        consumer.subscribe("base-topic", "TagOrder");

        //默认消费模式为负载均衡，广播模式MessageModel.BROADCASTING
        //consumer.setMessageModel(MessageModel.BROADCASTING);
        /*
         *4、注册消息监听回调这里有两种监听，MessageListenerConcurrently 以及 MessageListenerOrderly
         * 前者是普通监听，后者是顺序监听
         */
        consumer.registerMessageListener(new MessageListenerOrderly(){
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    log.info("消息队列ID:{}",consumeOrderlyContext.getMessageQueue().getQueueId());
                    System.out.println("线程名称：["+Thread.currentThread().getName()+"],消息"+new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        //5、启动消费者
        consumer.start();

        log.info("消费者启动成功...,时间：{}。",new Date());
    }
}