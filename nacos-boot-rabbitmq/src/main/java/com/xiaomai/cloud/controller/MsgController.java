package com.xiaomai.cloud.controller;

import com.xiaomai.cloud.component.MsgProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangfeng
 * @date 2020/11/23
 */
@RestController
public class MsgController {

    @Autowired
    private MsgProducer msgProducer;

    @GetMapping("/send")
    public String sendMsg(@RequestParam("msg") String msg){
        msgProducer.sendMsg(msg);
        return "ok!!!--"+new Date();
    }
}
