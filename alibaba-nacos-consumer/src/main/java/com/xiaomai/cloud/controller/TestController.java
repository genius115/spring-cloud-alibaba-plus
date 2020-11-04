package com.xiaomai.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@RestController
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/get")
    public String get(){
        return  restTemplate.getForObject("http://alibaba-nacos-provider/get", String.class);
    }

    @GetMapping("/demo")
    public String demo(){
        return  "nacosconsumer -- demo";
    }

    @GetMapping("/getUser")
    public String getUser(){
        return  "getUser Success!!!";
    }
}
