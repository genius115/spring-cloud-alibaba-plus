package com.xiaomai.cloud.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@Configuration
public class RateLimiterConfig {

    @Bean(value = "apiKeyResolver")
    public KeyResolver apiKeyResolver() {
        //URL限流,超出限流返回429状态
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}