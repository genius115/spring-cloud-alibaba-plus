package com.xiaomai.cloud.rocket;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.io.Serializable;

/**
 * @author Madison
 * @date 2021/2/25
 * @Function 消息生产者
 */
public class Producer {
    public static void main(String[] args) throws MQClientException {
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        producer.setDefaultTopicQueueNums(3); //设置默认的queue数量
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.setInstanceName("rmq-instance");
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("abc" + i);
                user.setPwd(String.valueOf(i));
                //创建消息实例，指定topic tags 消息内容
                Message message = new Message("log-topic", "user-tag", JSON.toJSONString(user).getBytes());
                System.out.println("生产者发送消息:" + JSON.toJSONString(user));
                //发送消息并且获取发送结果
                SendResult sendResult = producer.send(message);
                System.out.println("发送结果："+sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // producer.shutdown();
    }

    /**
     * 发送用户消息
     */
    static class User implements Serializable {
        private String loginName;
        private String pwd;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}