package com.xiaomai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2020/11/4
 */
@EnableCircuitBreaker //添加此注解告诉主启动类对Hystrix的支持
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderSentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosProviderSentinelApplication.class,args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
