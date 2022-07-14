package com.xiaomai.xiaomaispringbootbase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Madison
 * @date 2022/7/9
 */
@Component
@ConfigurationProperties(prefix="pingruan.base.c3p0.data")
@Data
public class C3p0Properties {
    private String useExtData = "false";
    private String driverClass;
    private String jdbcUrl;
    private String user;
    private String password;
    private int initialPoolSize = 10;
    private int minPoolSize = 5;
    private int maxPoolSize = 20;
}
