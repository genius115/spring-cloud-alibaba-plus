package com.xiaomai.cloud.api;

import com.opslab.util.DateUtil;
import com.xiaomai.cloud.api.config.AuthUtil;
import com.xiaomai.cloud.api.config.JwtUtil;
import com.xiaomai.cloud.api.config.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(ApiBootApplication.class);

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

    @RequestMapping("/test")
    public String Test(){
        try {
            String a = "";
//			a.charAt(5);
        } catch (Exception e) {
            logger.error("Unexpected error in ", e);
        }
        return "["+AuthUtil.getItemcode()+";"+UserContext.getAccountitemcode()+"]"+
                "["+AuthUtil.getUserid()+";"+UserContext.getAccountuserid()+"]"+
                "["+AuthUtil.getUsername()+";"+UserContext.getAccountusername()+"]";
    }

}
