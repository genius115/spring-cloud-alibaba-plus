package com.xiaomai;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 *Java集合框架(常用接口)： *
 * Collection 接口存储一组不唯一，无序的对象 (父类接口) (List Set)
 * List 接口存储一组不唯一，有序(插入顺序)的对象 *(ArrayList LinkedList)
 * Set 接口存储一组唯一，无序的对象 *(HashSet TreeSet)

 list：存储： 有序的 可重复的
 * 访问：可以for循环，foreach循环，iterator迭代器 迭代。
 set：存储：无序的 不重复的
 * 访问：可以foreach循环，iterator迭代器 迭代
 map：存储：存储的是一对一对的映射 ”key=value“，key值  是无序，不重复的。value值可重复
 * 访问：可以map中key值转为为set存储，然后迭代这个set，用map.get(key)获取value
 * 也可以 转换为entry对象 用迭代器迭代

 *
 * 数据结构 集合
 *
 * @author Madison
 * @date 2021/4/8
 */
public class Demo {

    public static void main(String[] args) throws InterruptedException {
        List<String> l = new ArrayList<>();
        l = new LinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("1");
        l.add("5");
        System.out.println("----------------");
        Iterator<String> itList = l.iterator();
        while (itList.hasNext()){
            System.out.print(itList.next());
        }
        System.out.println();

        //排序
        Set<Integer> s = new TreeSet<>();
        //s = new HashSet<Integer>();
        s.add(9);
        s.add(1);
        s.add(4);
        s.add(2);
        s.add(3);
        s.add(1);
        //s.add(null);
        System.out.println("----------------");
        Iterator it = s.iterator();
        while (it.hasNext()){
            System.out.print(it.next());
        }
        System.out.println();


        Map<String,String> m = new TreeMap();
        m =new HashMap<>();
        m.put("3", "3");
        m.put("1", "1");
        m.put("2", "12");
        m.put("1", "123");
        m.put(null, null);
        System.out.println("----------------");
        for(Map.Entry<String, String> row : m.entrySet()){
            System.out.println(row.getKey()+";"+row.getValue());
        }
        System.out.println("----------------");
        for(String row : m.keySet()){
            System.out.println("key:"+row);
        }
        System.out.println("----------------");
        for(String row : m.values()){
            System.out.println("value:"+row);
        }

        System.out.println("----------------");
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                Long id = Thread.currentThread().getId();
                System.out.println("Thread = [" + id + "]");
            }).start();
        }
        //.sleep(5000);


        RunnableImpl runnable=new RunnableImpl();
        Thread thread=new Thread(runnable);
        thread.start();

        // 使用匿名内部类简化
        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - new");
            }
        };
        new Thread(runnable1).start();

        // 继续简化
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " - new");
            }
        }).start();
    }
}
class RunnableImpl implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " - new Runnable");
    }
}