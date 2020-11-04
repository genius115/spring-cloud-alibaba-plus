package com.xiaomai.cloud.controller;

import com.xiaomai.cloud.services.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@RestController
@RequestMapping("/feign")
@Api(tags = {"Feign调用测试"})
@Slf4j
public class TestConsumerOpenfeignController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get")
    @ApiOperation(value = "获取payment服务的返回信息", notes = "根据请求参数，返回请求信息")
    @ApiImplicitParam(name = "name", value = "名字", defaultValue = "swagger")
    public String get(@RequestParam(value = "name", defaultValue = "swagger") String name){
        log.info(name);
        return  paymentService.get();
    }

    @GetMapping("/demo")
    public String demo(){
        return  paymentService.demo();
    }

    @GetMapping("/getUser")
    public String getUser(){
        return  paymentService.demoone();
    }

    @GetMapping("/time/out")
    public String requestTimeOut(){
        return paymentService.requestTimeOut();
    }
}
