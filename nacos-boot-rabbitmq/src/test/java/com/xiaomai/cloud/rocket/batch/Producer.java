package com.xiaomai.cloud.rocket.batch;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Madison
 * @date 2021/3/1
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception{
        //生产者组
        DefaultMQProducer producer = new DefaultMQProducer("batch-group");
        producer.setDefaultTopicQueueNums(3); //设置默认的queue数量
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.start();
        String signer = UUID.randomUUID().toString().replaceAll("-","");
        try {
            List<Message> msgs = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("batch" + i);
                user.setPwd(String.valueOf(i));
                user.setSign(signer);
                //创建消息实例，指定topic tags 消息内容
                Message message = new Message("batch-topic", "tag1", JSON.toJSONString(user).getBytes());
                //System.out.println("生产者发送消息:" + JSON.toJSONString(user));
                msgs.add(message);
            }

            //发送批量消息，单个小于4M
            SendResult sendResult = producer.send(msgs);
            System.out.println("发送结果："+sendResult);

            //把大的消息分裂成若干个小消息
            ListSplitter splitter = new ListSplitter(msgs);
            while (splitter.hasNext()){
                try {
                    List<Message> listItem = splitter.next();
                    SendResult sendResultSplit = producer.send(listItem);
                    System.out.println("分割后，发送结果："+sendResultSplit);
                } catch (MQClientException e) {
                    e.printStackTrace();
                } catch (RemotingException e) {
                    e.printStackTrace();
                } catch (MQBrokerException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // producer.shutdown();
        log.info("生产者发送消息完成。。。");
    }
}
