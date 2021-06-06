package com.xiaomai.xiaomaispringbootinit;

import com.xiaomai.xiaomaispringbootinit.service.RetryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Madison
 * @date 2021/4/7
 */
// 启用了重试功能
@Slf4j
@RestController
@EnableRetry
@SpringBootApplication
public class XiaomaiSpringBootInitApplication {
    @Value("${spring.application.name}")
    private String applicationname;

    @Value("${server.port}")
    private String port;

    @Autowired
    private RetryService retryService;

    public static void main(String[] args) {
        SpringApplication.run(XiaomaiSpringBootInitApplication.class, args);
    }

    @GetMapping("/retryservice")
    private String retryServiceTest(){
        retryService.retry();
        return "OK";
    }

    @RequestMapping("/test")
    public String Test(){
        return "时间:["+new Date()+"]应用名称:["+applicationname+"]端口号:["+port+"]";
    }

    @GetMapping("/")
    public String getIndex() {
        // 级别由低到高 trace<debug<info<warn<error
        log.trace("这是一个trace日志...");
        log.debug("这是一个debug日志...");
        // SpringBoot默认是info级别，只会输出info及以上级别的日志
        log.info("这是一个info日志...");
        log.warn("这是一个warn日志...");
        log.error("这是一个error日志...");
        String str = "https://www.baidu.com";
        log.info("======欢迎访问百度：{}\n", str);

        return "OK";
    }
}
