package com.xiaomai.cloud.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


import com.google.common.util.concurrent.RateLimiter;

public class TestDemo {
	
	
	public static void main(String[] args) throws Exception {
//		Date startWeek = DateUti.beginOfWeek(Calendar.getInstance()).getTime();		
//		Date endWeek = DateUtil.endOfWeek(Calendar.getInstance()).getTime();
//		System.out.println(startWeek);
//		System.out.println(endWeek);
		
//        testNoRateLimiter();  
//        testWithRateLimiter();  
//        testWithRateLimiterRallBack();
    }  
   
    public static void testNoRateLimiter() {  
        Long start = System.currentTimeMillis();  
        for (int i = 0; i < 10; i++) {  
            System.out.println("call execute.." + i);  
              
        }  
        Long end = System.currentTimeMillis();  
          
        System.out.println(end - start);  
          
    }  
      
    public static void testWithRateLimiter() {  
        Long start = System.currentTimeMillis();  
        RateLimiter limiter = RateLimiter.create(1.0); // 每秒不超过1个任务被提交  
        for (int i = 0; i < 10; i++) {  
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞  
            System.out.println("call execute.." + i);               
        }  
        Long end = System.currentTimeMillis();  
          
        System.out.println(end - start);  
          
    }  
    
    public static void testWithRateLimiterRallBack() throws InterruptedException {  
        Long start = System.currentTimeMillis();  
        RateLimiter limiter = RateLimiter.create(3); // 每秒不超过3个任务被提交  
        for (int i = 0; i < 10; i++) {  
            Boolean result = limiter.tryAcquire(); // 请求RateLimiter, 是否被限流
            System.out.println("call execute.." + i + result);  
            Thread.sleep(100);
        }  
        Long end = System.currentTimeMillis();  
          
        System.out.println(end - start);           
    }  
}
