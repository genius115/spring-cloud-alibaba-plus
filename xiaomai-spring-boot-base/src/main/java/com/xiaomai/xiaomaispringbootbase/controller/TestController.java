package com.xiaomai.xiaomaispringbootbase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2022/6/3
 */
@RestController
public class TestController {
    
    @GetMapping("/test")
    public String test(){
        return  "111111";
    }
}
