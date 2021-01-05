package com.xiaomai.cloud.controller.test;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author developer
 * @date 2021/1/5
 */
@Component
@ConfigurationProperties(prefix = "demo")
@PropertySource(value = "config.properties")
public class ConfigBeanProp {

    private String info;

    private String name;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}