package com.xiaomai.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.SpringfoxWebMvcConfiguration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
// mapper扫描
@MapperScan(basePackages = {"com.xiaomai.cloud.mapper"})
@EnableDiscoveryClient
@SpringBootApplication
public class AlibabaNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlibabaNacosProviderApplication.class, args);
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

}
