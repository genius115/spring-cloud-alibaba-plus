package com.xiaomai.cloud.rocket.base;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.rocket.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.UUID;

/**
 *
 * 异步消息
 *
 * @author Madison
 * @date 2021/3/1
 */
@Slf4j
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //实例化消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("base-group");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        //发送失败重试次数
        producer.setRetryTimesWhenSendAsyncFailed(0);

        String signer = UUID.randomUUID().toString().replaceAll("-","");
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setLoginName("Async-base" + i);
            user.setPwd("pwd"+String.valueOf(i));
            user.setSign(signer);

            final int index =i;

            //创建消息，并制定参数
            //Message msg = new Message("Async-topic", "base-tag-1", "Async-msg",("Hello world Async Msg "+i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            Message msg = new Message("base-topic", "Tag2", "Async-msg",JSON.toJSONString(user).getBytes(RemotingHelper.DEFAULT_CHARSET));

            //SendCallback接收异步返回结果的回调
            producer.send(msg, new SendCallback() {
                //发送成功回调
                @Override
                public void onSuccess(SendResult sendResult) {
                    //System.out.println("sendResult = [" + sendResult + "]");
                    System.out.printf("%-10d OK %s %n",index,sendResult.getMsgId());
                }

                //发送异常回调
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("throwable = [" + throwable + "]");
                }
            });
        }
        //producer.shutdown();
        log.info("生产者发送消息主程序完成。。。");
    }
}
