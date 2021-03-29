package com.xiaomai.cloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangfeng
 * @date 2020/11/5
 */
@RestController
@Slf4j
public class TestSentinelController {

    @GetMapping("/testA")
    @HystrixCommand(fallbackMethod = "getTestAFB") //指定降级方法的方法名
    // 一旦调用服务方法失败抛出“错误信息后，会自动返回用@HystrixCommand标注好的fallbackMethod调用类中的指定方法”
    public String testA(){
        return "Test A ......";
    }

    @GetMapping("/testB")
    @HystrixCommand(fallbackMethod = "getTestBFB") //指定降级方法的方法名
    //  一旦调用服务方法失败抛出“错误信息后，会自动返回用@HystrixCommand标注好的fallbackMethod调用类中的指定方法”
    public String testB(){
        log.info(Thread.currentThread().getName()+"----Test B");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Test B ......";
    }


    //降级方法的参数和返回值，需要与原始方法一致，方法名一致
    public String getTestAFB(){
        return new Date()+":Test A ---- fallbackMethod getTestAFB...";
    }

    //降级方法的参数和返回值，需要与原始方法一致，方法名一致
    public String getTestBFB(){
        return new Date()+":Test B ---- fallbackMethod getTestBFB...";
    }
}
