package com.xiaomai.cloud;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Madison
 * @date 2021/3/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NacosBootRabbitMQ.class})
@Slf4j
public class RocketProducerTest {

    Logger logger = LoggerFactory.getLogger(RocketProducerTest.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void  SendMsg(){
        rocketMQTemplate.convertAndSend("Spring-Boot-Topic","TagA");
        this.logger.info("RocketMQ消息发送成功");
    }



}