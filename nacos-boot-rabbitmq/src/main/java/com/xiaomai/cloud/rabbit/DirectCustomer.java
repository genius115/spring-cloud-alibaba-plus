package com.xiaomai.cloud.rabbit;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *
 * 路由 直联消费者
 *
 * @author Madison
 * @date 2021/2/22
 */
@Component
public class DirectCustomer {

    @RabbitListener(bindings = {
        @QueueBinding(
                value = @Queue, //创建临时队列
                exchange = @Exchange(value = "Exchange-Direct"), //绑定的交换机
                key={
                        "info","warn"
                }
        )
    })
    public void receive1(String msg){
        System.out.println("info/warn---direct消息："+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "Exchange-Direct"), //绑定的交换机
                    key={
                            "info"
                    }
            )
    })
    public void receive2(String msg){
        System.out.println("info---direct消息："+msg);
    }
}
