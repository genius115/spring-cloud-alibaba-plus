package com.example.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Madison
 * @date 2022/4/5
 */
@RestController
public class HelloController {
    
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    
    @GetMapping("/hello")
    public String viewHello(){
        Long count = stringRedisTemplate.opsForValue().increment("views",1L);
        return "Hello, Thank you ["+ count +"] view!!!";
    }
}
