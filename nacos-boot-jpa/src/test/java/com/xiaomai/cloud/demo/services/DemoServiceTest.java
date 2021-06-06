package com.xiaomai.cloud.demo.services;

import com.xiaomai.cloud.NacosBootJpaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Madison
 * @date 2021/4/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {NacosBootJpaApplication.class})
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void Test1(){
        System.out.println(demoService);
    }

}
