package com.xiaomai.cloud.rocket.delay;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Madison
 * @date 2021/2/25
 * @Function 消息生产者
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws MQClientException {
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("delay-group");
        producer.setDefaultTopicQueueNums(3); //设置默认的queue数量
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.start();
        String signer = UUID.randomUUID().toString().replaceAll("-","");
        try {
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("delay" + i);
                user.setPwd(String.valueOf(i));
                user.setSign(signer);
                //创建消息实例，指定topic tags 消息内容
                Message message = new Message("delay-topic", "tag1", JSON.toJSONString(user).getBytes());
                //System.out.println("生产者发送消息:" + JSON.toJSONString(user));

                //延迟参数以内置固定级别，最少1s 最多2H，不支持任意时间的延迟  18个级别
                message.setDelayTimeLevel(2);
                //发送消息并且获取发送结果
                SendResult sendResult = producer.send(message);
                System.out.println("发送结果："+sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // producer.shutdown();
        log.info("生产者发送消息完成。。。");
    }
}