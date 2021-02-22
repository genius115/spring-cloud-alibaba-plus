package com.xiaomai.cloud.nacosprovider.controller;

import com.xiaomai.cloud.nacosprovider.socketio.MessageEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * socket.io是js实现的，websocket框架，为了解决浏览器不兼容问题而设计
 * socket.io.js下载地址：https://cdnjs.com/libraries/socket.io
 * 常用的方式是，前端使用socket.io.js，后端使用node.js实现socket.io的接口，可是我们的架构后端使用的是java，
 * 所以我使用的是netty-socketio，基于spring-boot实现；
 *
 *
 * @author Madison
 * @date 2021/1/25
 */
@CrossOrigin
@Controller
public class SocketController {

    @Autowired
    private MessageEventHandler messageEventHandler;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/api/test")
    @ResponseBody
    public String getMsg(){
        return "Test Hello World!";
    }

    @GetMapping("/socket/toClient")
    @ResponseBody
    public String toClient(){
        messageEventHandler.sendBuyLogEvent();
        return "To Client Success!!!";
    }


}
