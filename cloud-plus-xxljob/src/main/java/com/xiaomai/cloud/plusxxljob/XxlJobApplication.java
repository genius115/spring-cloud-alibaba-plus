package com.xiaomai.cloud.plusxxljob;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Madison
 * @date 2021/2/2
 */
@Slf4j
@SpringBootApplication
public class XxlJobApplication {
    private static Logger logger = LoggerFactory.getLogger(XxlJobApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(XxlJobApplication.class, args);
        logger.info("******************************************************************************************");
        logger.info("Start XxlJobApplication Success.....");
        logger.info("******************************************************************************************");
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

}
