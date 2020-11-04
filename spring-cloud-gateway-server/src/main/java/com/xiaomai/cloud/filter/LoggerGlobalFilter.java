package com.xiaomai.cloud.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@Component
public class LoggerGlobalFilter implements GlobalFilter{
    private Logger logger = LoggerFactory.getLogger(LoggerGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //统一权限验证
        logger.info("gateway-执行全局过滤器--logger");
        return chain.filter(exchange);
    }

}