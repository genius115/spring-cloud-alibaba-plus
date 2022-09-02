package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.util.Date;
import java.util.TimeZone;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args)  throws Exception{        
//        SpringApplication.run(DemoApplication.class, args);
        
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        ConfigurableApplicationContext application = SpringApplication.run(DemoApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port +  "/\n\t" +
                "External: \thttp://" + ip + ":" + port +  "/\n\t" +
                "----------------------------------------------------------");
        System.out.println("---项目启动成功---"+new Date());        
    }

}
