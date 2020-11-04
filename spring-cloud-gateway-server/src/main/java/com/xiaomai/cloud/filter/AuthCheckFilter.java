package com.xiaomai.cloud.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.xiaomai.cloud.bean.LoginUser;
import com.xiaomai.cloud.service.SessionService;

import lombok.extern.slf4j.Slf4j;


/**
 * @author wangfeng
 * @date 2020/11/2
 */
@Slf4j
@Component
public class AuthCheckFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private SessionService sessionService;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain)->{
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            //1、获取token
            String token = request.getHeaders().getFirst("token");
            log.info("当前请求请求头token:{}",token);
            if(token==null) {
                token = request.getQueryParams().getFirst("token");
                log.info("当前请求请求参数token:{}",token);
            }

            log.info("当前请求的URL{},method{}",request.getURI().getPath(),request.getMethodValue());

            if(StringUtils.isEmpty(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            //2、验证用户是否登录
            LoginUser loginUser = this.sessionService.getSession(token);
            if(loginUser==null) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }
            log.info("---token验证成功---{}",loginUser);
            //3、将用户名传递给后端
            ServerWebExchange build;
            try {
                ServerHttpRequest host = exchange.getRequest().mutate()
                        .header("X-User-Name", loginUser.getUserName())
                        .header("X-Real-Name", loginUser.getRealName()) //URLEncode.encode(,"utf-8")
                        .build();
                build = exchange.mutate().request(host).build();
            }catch(Exception e){
                build = exchange;
            }
            return chain.filter(build);
        };
    }


}

