package com.xiaomai.cloud.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * Security 新增安全认证过滤类
 * @author wangfeng
 * @date 2020/11/4
 */
@Component
public class SecurityBasicAuthorizationFilter implements GlobalFilter, Ordered {

    @Value("${spring.security.user.name}")
    private String username;
    @Value("${spring.security.user.password}")
    private String password;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String auth = username.concat(":").concat(password);
        String encodedAuth = new sun.misc.BASE64Encoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " +encodedAuth;
        //headers中增加授权信息
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().header("Authorization", authHeader).build();
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
        return chain.filter(build);
    }
    /**
     * 优先级
     * 数字越大优先级越低
     * @return
     */
    @Override
    public int getOrder() {
        return -1;
    }
}