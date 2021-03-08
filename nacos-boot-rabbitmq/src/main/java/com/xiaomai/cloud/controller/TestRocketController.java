package com.xiaomai.cloud.controller;

import com.xiaomai.cloud.config.rocket.ParamConfigService;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.rocketmq.common.message.Message;

/**
 * @author Madison
 * @date 2021/2/25
 */
@RestController
public class TestRocketController {

    @Autowired
    private DefaultMQProducer defaultMQProducer;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private ParamConfigService paramConfigService;

    @RequestMapping(value = "/send/rocket", method = {RequestMethod.GET})
    public SendResult testStringQueue(@RequestParam("msg") String msg) {
        // 可以不使用Config中的Group
        defaultMQProducer.setProducerGroup(paramConfigService.platGroup);

        SendResult sendResult = null;
        String msgInfo = "rocketmq  message:"+msg;
        try {
            Message sendMsg = new Message(paramConfigService.platTopic,
                    paramConfigService.accountTag, msgInfo.getBytes());
            sendResult = defaultMQProducer.send(sendMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendResult;
    }
}