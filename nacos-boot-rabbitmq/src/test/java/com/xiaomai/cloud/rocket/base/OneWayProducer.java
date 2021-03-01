package com.xiaomai.cloud.rocket.base;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 *
 * 单向消息
 *
 * @author Madison
 * @date 2021/3/1
 */
@Slf4j
public class OneWayProducer {
    public static void main(String[] args) throws MQClientException {
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("base-group");
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.start();
        try {
            String signer = UUID.randomUUID().toString().replaceAll("-","");
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("base-one-way" + i);
                user.setPwd("pwd"+String.valueOf(i));
                user.setSign(signer);
                //创建消息实例，指定topic tags 消息内容
                //参数一：消息主题 参数二：消息Tag  参数三：消息内容
                Message message = new Message("base-topic", "Tag3", JSON.toJSONString(user).getBytes());

                //***发送单向消息
                producer.sendOneway(message);

                System.out.println("生产者发送消息:" + JSON.toJSONString(user));
                //线程休眠一秒
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //producer.shutdown();
    }
}
