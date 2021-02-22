package com.xiaomai.cloud.rabbit;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * hello-world 消费者
 *
 * @author Madison
 * @date 2021/2/22
 */
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "Hello-SpringBoot",durable = "false",exclusive = "false",autoDelete = "false"))
public class HelloCustomer {

    // 回调方法

    @RabbitHandler
    public void receive(String message){
        System.out.println("hello message="+message);
    }

}
