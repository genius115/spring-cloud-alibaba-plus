package com.xiaomai.cloud.controller.test;

import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@RestController
@RefreshScope
@Api(value = "test", tags = "测试接口")
public class TestController {

    @GetMapping("/get")
    public String get(){
        return "Spring Cloud Alibaba Provider 我来啦..."+ new Date();
    }

    @GetMapping("/one")
    public String demo(){
        return "demo";
    }

    @GetMapping("/demo/one")
    public String demoone(){
        return "demo-one";
    }

    @GetMapping("/time/out")
    public String requestTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(6);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Test Request Time Out----OK";
    }

}

