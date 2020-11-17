package com.xiaomai.cloud.controller;

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
    public String testA(){
        return "Test A ......";
    }

    @GetMapping("/testB")
    public String testB(){
        log.info(Thread.currentThread().getName()+"----Test B");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Test B ......";
    }
}
