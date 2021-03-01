package com.xiaomai.cloud.rocket.transaction;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @author Madison
 * @date 2021/3/1
 */
@Slf4j
public class Producer {
    public static void main(String[] args) throws Exception{
        //生产者组
        TransactionMQProducer producer = new TransactionMQProducer("transaction-group");
        producer.setNamesrvAddr("localhost:9876"); //指定name sever服务地址，获取broker
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 在该方法中执行本地事务
             * @param msg
             * @param arg
             * @return
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                System.out.println("msg = [" + msg + "], arg = [" + arg + "]");
                if(StringUtils.equals("TagA",msg.getTags())){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else if(StringUtils.equals("TagB",msg.getTags())){
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }else if(StringUtils.equals("TagC",msg.getTags())){
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.UNKNOW;
            }

            /**
             * 该方法MQ进行消息事务状态回查
             * @param msg
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                log.info("消息Tags:"+msg.getTags());
                System.out.println("回查消息：msg = [" + msg + "]");
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        producer.start();

        String[] tags = {"TagA","TagB","TagC"};
        try {
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setLoginName("Transaction" + i);
                user.setPwd(String.valueOf(i));
                //创建消息实例，指定topic tags 消息内容
                Message message = new Message("transaction-topic", tags[i%3], JSON.toJSONString(user).getBytes());
                //System.out.println("生产者发送消息:" + JSON.toJSONString(user));
                //发送消息并且获取发送结果
                SendResult sendResult = producer.sendMessageInTransaction(message,null);
                System.out.println("发送结果："+sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // producer.shutdown();
    }
}
