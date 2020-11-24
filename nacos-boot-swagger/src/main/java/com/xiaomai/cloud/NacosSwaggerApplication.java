package com.xiaomai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2020/11/20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosSwaggerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosSwaggerApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
