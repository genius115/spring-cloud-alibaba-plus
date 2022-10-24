package com.xiao.mai;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ExampleBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleBootApplication.class, args);
    }

    @Value("${server.port}")
    String port;
    
    @Value("${val:123456}")
    String val;
    
    @GetMapping("/")
    public String index() {
        return "端口号:"+port+";验证参数val(默认值：123456):"+val;
    }
    
}
