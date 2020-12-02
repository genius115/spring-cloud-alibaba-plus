package com.xiaomai.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringData：其实SpringData就是Spring提供了一个操作数据的框架。而SpringData JPA只是SpringData框架下的一个基于JPA标准操作数据的模块。
 * SpringData JPA：基于JPA的标准数据进行操作。简化操作持久层的代码。只需要编写接口就可以。
 */

@SpringBootApplication
public class NacosBootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosBootJpaApplication.class, args);
    }

}
