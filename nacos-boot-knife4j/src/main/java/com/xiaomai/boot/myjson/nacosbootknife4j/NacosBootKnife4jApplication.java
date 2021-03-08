package com.xiaomai.boot.myjson.nacosbootknife4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NacosBootKnife4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosBootKnife4jApplication.class, args);
    }

}
