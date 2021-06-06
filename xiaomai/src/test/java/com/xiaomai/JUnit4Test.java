package com.xiaomai;

/**
 *
 * assertThat 使用了 Hamcrest 的 Matcher 匹配符，用户可以使用匹配符规定的匹配准则精确的指定一些想设定满足的条件，
 * 具有很强的易读性，而且使用起来更加灵活。
 *
 * @author Madison
 * @date 2021/4/8
 */
import org.junit.*;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

/**
 *JUnit4演示
 */
public class JUnit4Test {
    /**
     *普通测试
     * */
    @Test
    public void test() {
        System.out.println("test()");
    }
    /**
     *普通测试
     * */
    @Test
    public void test2() {
        System.out.println("test2()");
    }
    /**
     *忽略执行
     * */
    @Ignore
    public void testIgnore() {
        System.out.println("testIgnore()忽略");
    }

    /**
     *每个方法之前执行
     * */
    @Before
    public void testBefore() {
        System.out.println("Before()");
    }

    /**
     *每个方法之后执行
     * */
    @After
    public void testAfter() {
        System.out.println("After()");
    }

    /**
     *使用断言
     */
    @Test
    public void test3() {
        System.out.println("test3 @Test");
        int a = 1;
        int b = 2;
        //使用断言      Assert.assertEquals("测试期望的值", "方法运行的实际的值")
        Assert.assertEquals(4, a + b);
    }

    @Test
    public void test4() {
        String s="developer";
        Assert.assertThat(s, anyOf(containsString("developer"), containsString("Works")));
        Assert.assertThat(s, is(3));
    }

}