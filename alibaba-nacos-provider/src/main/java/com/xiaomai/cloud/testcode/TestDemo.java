package com.xiaomai.cloud.testcode;

import com.xiaomai.cloud.po.test.TestVo;
import org.junit.jupiter.api.Test;

/**
 * @author Madison
 * @date 2021/3/22
 */
public class TestDemo {
    @Test
    public void TestBuilder1() {
        TestVo testVo = TestVo.builder().name("张三").age(10).email("12345678@qq.com").build();
        System.out.println(testVo);
    }
}