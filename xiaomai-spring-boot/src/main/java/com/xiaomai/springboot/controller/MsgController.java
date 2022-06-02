package com.xiaomai.springboot.controller;

import com.xiaomai.springboot.service.msg.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2022/6/2
 */
@RestController
@RequestMapping("/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @GetMapping("/send")
    public String uploadFile(String msg){
        return msgService.send(msg);
    }

}
