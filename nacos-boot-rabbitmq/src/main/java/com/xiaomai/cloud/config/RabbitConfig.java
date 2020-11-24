package com.xiaomai.cloud.config;

import com.xiaomai.cloud.component.MsgReceiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *
 *     Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 *     Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 *     Queue:消息的载体,每个消息都会被投到一个或多个队列。
 *     Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 *     Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 *     vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 *     Producer:消息生产者,就是投递消息的程序.
 *     Consumer:消息消费者,就是接受消息的程序.
 *     Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 *
 * @author wangfeng
 * @date 2020/11/23
 */
@Configuration
public class RabbitConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.virtual-host}")
    private String vhost;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;


    public static final String EXCHANGE_A = "my-mq-exchange_A";

    public static final String QUEUE_A = "QUEUE_A";

    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vhost);
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    /**
     * 获取队列A
     * @return
     */
    @Bean
    public Queue queueA() {
        //队列持久
        return new Queue(QUEUE_A, true);
    }

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
         FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
         HeadersExchange ：通过添加属性key-value匹配
         DirectExchange:按照routingkey分发到指定队列
         TopicExchange:多关键字匹配
     */
    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE_A,true,false);
    }


    /**
     * 声明绑定关系
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
    }

   /* @Bean
    MessageListenerAdapter listenerAdapter(MsgReceiver receiver) {
        return new MessageListenerAdapter(receiver, "process");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QUEUE_A);
        container.setMessageListener(listenerAdapter);
        //手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }*/
}
