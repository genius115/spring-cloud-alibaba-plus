package com.cloud.boot.jbdc.controller;

import com.cloud.boot.jbdc.dao.Demo;
import com.cloud.boot.jbdc.dao.DemoMapper;
import com.cloud.boot.jbdc.service.JdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2021/7/13
 */
@RestController
public class JdbcController {

    @Autowired
    private DemoMapper demoMapper;
    
    @Autowired
    private JdbcService jdbcService;
    
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    private JdbcTemplate secondJdbcTemplate;

    @GetMapping("/get")
    public Object get(){
        return jdbcService.get();
    }

    @GetMapping("/getMapper")
    public Object getMapper(){
        Demo demo = new Demo();
        demo.setId("1");
        return demoMapper.queryDemoById(demo);
    }
    
    
    @GetMapping("/getPrimary")
    public Object getPrimary(){
        return jdbcService.get(primaryJdbcTemplate);
    }
    @GetMapping("/getSecond")
    public Object getSecond(){
        return jdbcService.get(secondJdbcTemplate);
    }
}
