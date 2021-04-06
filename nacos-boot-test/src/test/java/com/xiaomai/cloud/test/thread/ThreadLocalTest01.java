package com.xiaomai.cloud.test.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 *
 * （1）每个Thread维护着一个ThreadLocalMap的引用 *
 * （2）ThreadLocalMap是ThreadLocal的内部类，用Entry来进行存储 *
 * （3）ThreadLocal创建的副本是存储在自己的threadLocals中的，也就是自己的ThreadLocalMap。 *
 * （4）ThreadLocalMap的键值为ThreadLocal对象，而且可以有多个threadLocal变量，因此保存在map中 *
 * （5）在进行get之前，必须先set，否则会报空指针异常，当然也可以初始化一个，但是必须重写initialValue()方法。
 * （6）ThreadLocal本身并不存储值，它只是作为一个key来让线程从ThreadLocalMap获取value。
 *
 * @author Madison
 * @date 2021/3/29
 */
public class ThreadLocalTest01 {
    public static void main(String[] args) {
        //新建ThreadLocal
        ThreadLocal<String> local = new ThreadLocal<>();
        //新家一个随机数类
        Random random = new Random();

        //使用java8的Stream新建5个线程
        IntStream.range(0,10).forEach(a-> new Thread(()-> {
            //为每个线程设置相应的local值
            local.set(a+" " +random.nextInt(1000));
            System.out.println("线程和Local值分别是：" + local.get() + "]");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //避免内存泄露
            local.remove();
        }).start());
    }
}
