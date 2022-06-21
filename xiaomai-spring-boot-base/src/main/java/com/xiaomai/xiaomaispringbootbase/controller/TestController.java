package com.xiaomai.xiaomaispringbootbase.controller;

import com.xiaomai.xiaomaispringbootbase.Bean.User;
import com.xiaomai.xiaomaispringbootbase.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2022/6/3
 */
@RestController
public class TestController {
    @Autowired
    public TestService testService;
    
    @GetMapping("/test")
    public String test(){
        return  "111111";
    }
    
    

    @GetMapping("/querybook")
    public String querybook(){
        return testService.querybook();
    }

    @GetMapping("/queryUserById")
    public User queryById(int userId){
        return testService.queryById(userId);
    }

    @GetMapping("/queryPhoneById")
    public User queryPhoneById(int userId){
        return testService.queryPhoneById(userId);
    }
}
