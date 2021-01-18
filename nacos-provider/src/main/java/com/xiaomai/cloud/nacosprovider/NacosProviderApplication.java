package com.xiaomai.cloud.nacosprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NacosProviderApplication {

    public static void main(String[] args) {
        // SpringApplication.run(NacosProviderApplication.class, args);

        ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosProviderApplication.class, args);

//        while(true){
            String userName = applicationContext.getEnvironment().getProperty("user.name");
            String userAge = applicationContext.getEnvironment().getProperty("user.age");
            String userCode = applicationContext.getEnvironment().getProperty("user.code");
            System.err.println("user name :"+userName+"; age: "+userAge+"; code: "+userCode);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }

    }

}
