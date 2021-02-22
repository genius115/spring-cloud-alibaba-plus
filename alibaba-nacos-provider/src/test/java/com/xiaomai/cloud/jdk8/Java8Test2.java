package com.xiaomai.cloud.jdk8;

import org.apache.commons.collections4.multimap.AbstractSetValuedMap;

import java.io.*;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Madison
 * @date 2021/1/17
 */
public class Java8Test2 {
    /*
    final static  String salutation = "Hello! ";

    public static void main(String[] args) {
        GreetingService greetService1 = (message) -> System.out.println(salutation + message);
        greetService1.sayMessage("Runoob");
    }

    interface GreetingService {
        void sayMessage(String message);
    }
    */
    public static void main(String args[]) {
        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3


        Map<String, String> map = new HashMap<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("对象序列化测试.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("对象序列化测试.txt"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }


}
