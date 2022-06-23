package com.xiaomai.xiaomaispringbootbase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

/**
 * @author Madison
 * @date 2022/6/22
 */

@SpringBootTest(classes = BaseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HellControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void test1() throws Exception {
        ResponseEntity<String> response = this.restTemplate.getForEntity( "/test", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
        
    }
    
    @Test    
    public void test2() throws Exception {
        ResponseEntity<String> response = this.restTemplate.getForEntity( "/queryUserById?userId=1", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }
}