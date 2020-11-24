package com.xiaomai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2020/11/23
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosBootRabbitMQ {
    public static void main(String[] args) {
        SpringApplication.run(NacosBootRabbitMQ.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
