package com.xiaomai.xiaomaispringbootinit.compent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author Madison
 * @date 2021/6/14
 */
@Import({Dog.class,User.class})
@Component
@Configuration(proxyBeanMethods = true)
public class MyConfig {

    //@Bean("tom")
    @Bean
    public Dog getDog(){
        return new Dog();
    }

    @Bean
    public User getUser(){
        User user = new User("1","zhangsan",18,new Dog());
        user.setDog(getDog());
        return user;
    }

}
