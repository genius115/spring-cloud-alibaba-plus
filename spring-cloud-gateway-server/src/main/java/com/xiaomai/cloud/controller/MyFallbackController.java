package com.xiaomai.cloud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@RestController
public class MyFallbackController {

    @RequestMapping("/myfallback")
    public String fallback() {
        return "请求-Hystrix 服务降级（网关托底数据 ），请您稍后操作。。。";
    }

}
