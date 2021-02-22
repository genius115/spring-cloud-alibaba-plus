package com.xiaomai.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Madison
 * @date 2021/2/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NacosBootRabbitMQ.class)
public class RabbitTest {

    //注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;


    //Topics 动态路由
    @Test
    public void TestTopics(){
        rabbitTemplate.convertAndSend("Exchange-Topic","warn.save","topic发送的消息");
    }


    //直联
    @Test
    public void TestDirect(){
        rabbitTemplate.convertAndSend("Exchange-Direct","warn","warn消息");
    }


    //Fanout 广播
    @Test
    public void TestFanout(){
        rabbitTemplate.convertAndSend("Exchange-Fanout","","Fanout消息");
    }


    //work queue
    @Test
    public void TestWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("Work-SpringBoot","work-queue---"+i);
        }
    }

    //Hello world
    @Test
    public void TestHelloWorld(){
        rabbitTemplate.convertAndSend("Hello-SpringBoot","Hello-World");
    }

}
