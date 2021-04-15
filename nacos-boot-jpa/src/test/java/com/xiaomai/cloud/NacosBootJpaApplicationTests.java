package com.xiaomai.cloud;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NacosBootJpaApplication.class})
class NacosBootJpaApplicationTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void contextLoads() {

    }

}
