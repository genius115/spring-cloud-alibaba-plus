package com.xiaomai.cloud.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 发布/订阅  消费者
 * @author Madison
 * @date 2021/2/22
 */
@Slf4j
@Component
public class FanoutCustomer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "Exchange-Fanout",type = "fanout") //绑定的交换机
            )
    })
    public void receive1(String msg){
        System.out.println("1--fanout消息："+msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "Exchange-Fanout",type = "fanout") //绑定的交换机
            )
    })
    public void receive2(String msg){
        System.out.println("2--fanout消息："+msg);
    }
}
