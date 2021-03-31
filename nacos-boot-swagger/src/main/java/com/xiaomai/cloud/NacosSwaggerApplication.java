package com.xiaomai.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
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
@Slf4j
public class NacosSwaggerApplication implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(NacosSwaggerApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     * 工程启动结束后会立即执行的方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("SpringApplication run execute ..... args:{}",args);
    }
}
