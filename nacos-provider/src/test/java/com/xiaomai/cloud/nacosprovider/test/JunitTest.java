package com.xiaomai.cloud.nacosprovider.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Madison
 * @date 2021/1/27
 */
@SpringBootTest
public class JunitTest {
    private Myclass myclass;

    @Before
    public void setUp() {
        myclass = new Myclass();
    }

    @Test
    public void testHello(){
        myclass.test1();
    }
}