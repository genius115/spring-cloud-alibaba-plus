package com.xiaomai.cloud.api;

import com.opslab.util.DateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2021/4/6
 */
@RestController
@SpringBootApplication
public class ApiBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiBootApplication.class);
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @RequestMapping("testWeb")
    public String test(){
        return DateUtil.currentDateTime()+ "*****[ApiBootApplication OK...]["+applicationName+"...]";
    }

    @RequestMapping("testDb")
    public String testDb(){
        return DateUtil.currentDateTime()+ "*****[ApiBootApplication DataBase OK...]["+applicationName+"...]";
    }

}
