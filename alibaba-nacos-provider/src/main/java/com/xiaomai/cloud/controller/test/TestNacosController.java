package com.xiaomai.cloud.controller.test;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * springboot获取properties文件的配置内容(转载)
 * https://www.cnblogs.com/mr-wuxiansheng/p/10338789.html
 *
 *
 *
 * @author developer
 * @date 2021/1/5
 */
@RestController
@RefreshScope
@Api(value = "testNacos", tags = "测试Nacos中间件")
@RequestMapping("/nacos")
public class TestNacosController {

    //@Value 读取属性后，可以进行实体注入

    @Value("${config.info}")
    private String info;

    //spring

    @Autowired
    private Environment environment;

    //属性较多，配置属性与实体对应

    @Autowired
    private ConfigBeanProp configBeanProp;

    @CrossOrigin
    @GetMapping("/get")
    public String get(){
        return "Spring Cloud Alibaba Provider 我来啦 ..." +
                "<p>1>@Value:"+ info+";" +
                "<p>2>Environment:"+environment.getProperty("config.info")+";"+
                "<p>3>ConfigBeanProp:"+configBeanProp.getInfo();
    }

}
