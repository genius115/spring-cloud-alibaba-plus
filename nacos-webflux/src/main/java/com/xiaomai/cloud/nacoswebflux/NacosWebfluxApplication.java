package com.xiaomai.cloud.nacoswebflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NacosWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosWebfluxApplication.class, args);
    }

}
