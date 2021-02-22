package com.xiaomai.cloud.rabbit;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Madison
 * @date 2021/2/22
 */
@Component
public class WorkCustomer {

    //消费者1

    @RabbitListener(queuesToDeclare = @Queue(value = "Work-SpringBoot"))
    public void receive1(String msg){
        System.out.println("work message 1="+msg);
    }

    //消费者2

    @RabbitListener(queuesToDeclare = @Queue(value = "Work-SpringBoot"))
    public void receive2(String msg){
        System.out.println("work message 2="+msg);
    }
}
