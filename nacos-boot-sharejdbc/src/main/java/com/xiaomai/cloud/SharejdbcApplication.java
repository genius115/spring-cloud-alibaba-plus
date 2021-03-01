package com.xiaomai.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 分库分表
 * @author Madison
 * @date 2021/3/1
 */
@MapperScan(basePackages = {"com.xiaomai.cloud.dao"})
@SpringBootApplication
public class SharejdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SharejdbcApplication.class,args);
    }
}
