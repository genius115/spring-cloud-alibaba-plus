package com.xiaomai.cloud.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author developer
 * @date 2021/1/14
 */
public class TestJvmGC {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add("第"+i+"元素");
        }
        System.out.println(list);
    }
}
