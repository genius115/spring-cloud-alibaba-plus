package com.xiaomai.cloud.springcloudgatewayknife4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudGatewayKnife4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudGatewayKnife4jApplication.class, args);
    }

}
