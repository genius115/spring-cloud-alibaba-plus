package com.xiaomai.cloud.rocket.base;

/**
 * @author Madison
 * @date 2021/2/25
 */
import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.util.unit.DataUnit;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 基本消息模型    同步消息
 * @author Madison
 * @date 2021/2/25
 * @Function 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("base-group");
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.start();
        try {
            String signer = UUID.randomUUID().toString().replaceAll("-","");
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("base" + i);
                user.setPwd("pwd"+String.valueOf(i));
                user.setSign(signer);
                //创建消息实例，指定topic tags 消息内容
                //参数一：消息主题 参数二：消息Tag  参数三：消息内容
                Message message = new Message("base-topic", "Base-Tag", JSON.toJSONString(user).getBytes());
                System.out.println("生产者发送消息:" + JSON.toJSONString(user));

                //****发送同步消息，发送消息并且获取发送结果
                SendResult sendResult = producer.send(message);
                System.out.println("发送结果："+sendResult);

                //线程休眠一秒
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //producer.shutdown();
    }
}