package com.xiaomai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2020/11/4
 */
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
