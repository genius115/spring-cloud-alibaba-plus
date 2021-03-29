package com.xiaomai.cloud.test;

import cn.dev33.satoken.SaTokenManager;
import cn.dev33.satoken.autowired.SaTokenSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Madison
 * @date 2021/3/2
 */
@SaTokenSetup // 标注启动 sa-token
@SpringBootApplication
public class TestApplication {
    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(TestApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
        LOG.info("项目启动成功。。。。。。");
        System.out.println("启动成功：sa-token配置如下：" + SaTokenManager.getConfig());
    }

    @PostConstruct
    void setDefaultTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
