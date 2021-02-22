package com.xiaomai.cloud.rabbit;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * 主题  消费者  动态路由
 *
 * @author Madison
 * @date 2021/2/22
 */
@Component
public class TopicsCustomer {

    @RabbitListener(bindings= {
            @QueueBinding(
                    value = @Queue,
                    exchange =@Exchange(value = "Exchange-Topic",type = "topic"),
                    key={
                            "info.#","user.save","user.*"
                    }
            )
    })
    public void receive1(String msg){
        System.out.println("info---Topics:"+msg);
    }

    @RabbitListener(bindings= {
            @QueueBinding(
                    value = @Queue,
                    exchange =@Exchange(value = "Exchange-Topic",type = "topic"),
                    key={
                            "warn.#","order.#","produce.#","user.#"
                    }
            )
    })
    public void receive2(String msg){
        System.out.println("warn---Topics:"+msg);
    }
}
