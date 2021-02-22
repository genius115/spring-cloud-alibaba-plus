package com.xiaomai.cloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangfeng
 * @date 2020/11/3
 */
@Configuration
public class MyRibbonConfiguration {

    // 开启消息者端的负载均衡功能，默认是轮询策略
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 指定Ribbon使用随机算法策略
    @Bean
    public IRule loadBalanceRule() {
        return new CustomRule();
    }

    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }
}
