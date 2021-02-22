package com.xiaomai.cloud;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2020/11/3
 */
@EnableDiscoveryClient
@SpringBootApplication
//开启Feigin客户端 指定service所在包
@EnableFeignClients(basePackages = "com.xiaomai.cloud.services")
public class AlibabaNacosConsumerOpenfeignApplicaton {
    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosConsumerOpenfeignApplicaton.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
