package com.xiaomai.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangfeng
 * @date 2020/11/3
 */
// @FeignClient标注的默认配置类为FeignClientsConfiguration
@Configuration
public class MyOpenFeignConfiguration {
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
