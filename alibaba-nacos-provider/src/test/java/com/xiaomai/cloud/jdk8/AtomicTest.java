package com.xiaomai.cloud.jdk8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程并发操作
 *
 * @author Madison
 * @date 2021/1/17
 */
public class AtomicTest {

    public static void atomicTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        MyRunnable myRunnable = new MyRunnable();
        for (int i = 0; i < 10; i++) {
            executorService.submit(myRunnable);
        }
    }

    public static void main(String[] args) {
        atomicTest();
    }
}

class MyRunnable implements Runnable {
    private int i = 0;

    public synchronized int getI() {
        return i++;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getI());

    }
}

