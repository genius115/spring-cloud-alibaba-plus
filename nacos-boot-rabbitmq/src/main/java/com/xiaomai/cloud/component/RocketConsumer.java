package com.xiaomai.cloud.component;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author Madison
 * @date 2021/3/6
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "Spring-Boot-Topic",consumerGroup = "Spring-Boot-Consumer-1")
public class RocketConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String s) {
        log.info("Receive Message:"+s);
    }
}
