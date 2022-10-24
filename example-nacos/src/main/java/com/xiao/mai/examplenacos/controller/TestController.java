package com.xiao.mai.examplenacos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.Date;

/**
 * @author Madison
 * @date 2022/10/21
 */

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String index(){
        String IP = "127.0.0.1";
        try {
//            IP = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex){
            
        }        
        return "Hello World!&nbsp;["+new Date()+"]";
    }

}
