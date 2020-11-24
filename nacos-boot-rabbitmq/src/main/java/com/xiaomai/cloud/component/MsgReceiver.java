package com.xiaomai.cloud.component;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.xiaomai.cloud.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author wangfeng
 * @date 2020/11/23
 */
@Component
@RabbitListener(queues = RabbitConfig.QUEUE_A)
public class MsgReceiver {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("接收处理队列A当中的消息： " + content);
    }
/*
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception{
        logger.info("接收处理队列A当中的消息： " + JSON.toJSONString(message));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        logger.info("onMessage="+new String(message.getBody()));
    }*/
}
