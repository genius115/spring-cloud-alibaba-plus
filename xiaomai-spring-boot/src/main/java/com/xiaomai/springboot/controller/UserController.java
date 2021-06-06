package com.xiaomai.springboot.controller;

import com.xiaomai.springboot.bean.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2021/4/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public User addUser(@RequestBody User user){
        return null;
    }
}