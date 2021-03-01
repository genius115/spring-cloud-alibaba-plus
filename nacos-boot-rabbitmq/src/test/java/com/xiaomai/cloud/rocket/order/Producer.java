package com.xiaomai.cloud.rocket.order;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * 业务相关消息进入一个队列，一个队列一个线程进行处理，保证局部顺序
 *
 * 顺序消息
 *
 * @author Madison
 * @date 2021/3/1
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("order-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        List<OrderStep> orders =  OrderStep.buildOrders();
        for (OrderStep order : orders) {

            Message msg = new Message("base-topic","TagOrder",order.getDesc(),JSON.toJSONString(order).getBytes());

            //发送消息
            /**
             *
             * 参数一 消息对象
             * 参数二 消息队列的选择器
             * 参数三 选择队列的业务标识（订单ID）
             * @param list
             * @param message
             * @param o
             * @return
             */
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                /**
                 *
                 * @param list  队列集合
                 * @param message  消息对象
                 * @param o 业务标识的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    long orderId = (long) o;
                    int index = (int) orderId%(list.size());
                    return list.get(index);
                }
            },order.getOrderId());

            System.out.println("发送结果：sendResult = [" + sendResult + "]");

        }

        producer.shutdown();
    }
}
