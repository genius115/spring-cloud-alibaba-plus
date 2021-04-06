package com.xiaomai.cloud.test.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @author Madison
 * @date 2021/4/2
 */
public class RateLimiterTest {


    public static void main(String[] args) throws InterruptedException {
        //限流 每秒允许10个请求进入秒杀 令牌速度=0.1s
        RateLimiter limiter = RateLimiter.create(10);

        // 100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                //每个秒杀请求如果50ms  0.05s  以内得到令牌就算秒杀成功  否则就返回秒杀失败
                if (limiter.tryAcquire(50,TimeUnit.MICROSECONDS)){
                    System.out.println("恭喜你，秒杀成功！");
                }else{
                    System.err.println("秒杀失败，请继续努力！");
                }
            }).start();
            // 给limter 0.01s的时间生成新的令牌生成
            Thread.sleep(10);
        }

    }
}
