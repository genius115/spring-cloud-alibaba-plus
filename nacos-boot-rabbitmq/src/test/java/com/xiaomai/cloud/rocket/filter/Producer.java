package com.xiaomai.cloud.rocket.filter;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.UUID;

/**
 * @author Madison
 * @date 2021/3/1
 */
public class Producer {
    public static void main(String[] args) throws Exception{
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("filter-group");
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.start();
        try {
            String signer = UUID.randomUUID().toString().replaceAll("-","");
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("filter" + i);
                user.setPwd(String.valueOf(i));
                user.setSign(signer);
                //创建消息实例，指定topic tags 消息内容
                Message message = new Message("filter-topic", "Tag1", "keys:"+i, JSON.toJSONString(user).getBytes());
                //消息添加属性
                message.putUserProperty("a",String.valueOf(i));

                //System.out.println("生产者发送消息:" + JSON.toJSONString(user));
                //发送消息并且获取发送结果
                SendResult sendResult = producer.send(message);
                System.out.println("发送结果："+sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
