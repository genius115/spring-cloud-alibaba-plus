package com.xiaomai.xiaomaispringbootbase.controller;

import com.xiaomai.xiaomaispringbootbase.Bean.User;
import com.xiaomai.xiaomaispringbootbase.service.TestService;
import com.xiaomai.xiaomaispringbootbase.service.file.FileService;
import com.xiaomai.xiaomaispringbootbase.util.Db;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author Madison
 * @date 2022/6/3
 */
@Slf4j
@RestController
public class TestController {
   
    @Resource(name = "AsyncThread")
    private Executor executor;

    @Autowired
    public Db db;

    @Autowired
    public TestService testService;

    @Autowired
    private FileService fileService;
    
    @GetMapping("/test")
    public String test(){
        return  "111111";
    }
    
    @GetMapping("/querybook")
    public String querybook(){            
        return testService.querybook();
    }

    @GetMapping("/queryUserById")
    public User queryById(int userId){
        return testService.queryById(userId);
    }

    @GetMapping("/queryPhoneById")
    public User queryPhoneById(int userId){
        return testService.queryPhoneById(userId);
    }
    
    @GetMapping("/file")
    public String file(){
        return fileService.uploadFile();
    }


    /**
     * SpringBoot多线程进行异步请求的处理
     * @return
     */
   /* @Async("AsyncThread")
    @RequestMapping(value = "/thread")
    public CompletableFuture<String> testThread() {
        System.out.println("----------------------------------------------");
        System.out.println(Thread.currentThread().getName() + "正在处理请求");
        System.out.println("----------------------------------------------");
        String result = "请求失败";
        Random r = new Random();
        if(r.nextInt(1000)>100){
            result = "请求成功";
        }
        //....你的业务逻辑
        return CompletableFuture.completedFuture(result);
    }*/

    /**
     * SpringBoot请求线程优化
     */
    /**
     * 异步调用restful
     * 当controller返回值是Callable的时候，springmvc就会启动一个线程将Callable交给TaskExecutor去处理
     * 然后DispatcherServlet还有所有的spring拦截器都退出主线程，然后把response保持打开的状态
     * 当Callable执行结束之后，springmvc就会重新启动分配一个request请求，然后DispatcherServlet就重新
     * 调用和处理Callable异步执行的返回结果， 然后返回视图
     *
     * @return
     */
   /* @GetMapping("/hello")
    public Callable<String> helloController() {
        log.info(Thread.currentThread().getName() + " 进入helloController方法");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info(Thread.currentThread().getName() + " 进入call方法");
                log.info("执行业务逻辑。。。。。。");                
                log.info(Thread.currentThread().getName() + " 从helloService方法返回");
                return "执行成功！";
            }
        };
        log.info(Thread.currentThread().getName() + " 从helloController方法返回");
        return callable;
    }*/
    
    
    @GetMapping("/exec1")
    public String exec1(String flag){
        executor.execute(()->{
            // 要执行的任务
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(100);
                }catch (Exception e){
                    
                }                
                log.info(Thread.currentThread().getName()+"---"+flag+"---"+i);
            }
                       
        });
        /*executor.execute(()->{
            // 要执行的任务
            for(int i=0;i<100;i++){
                try{
                    Thread.sleep(100);
                }catch (Exception e){

                }
                log.info(Thread.currentThread().getName()+"***"+flag+"***"+i);
            }

        });*/
        return "**线程池执行任务**"+new Date();
    }    
    
    @GetMapping("/dbtest")
    public String dbTest(){       
        List<Map<String,Object>> list = db.queryMapList("Select count(*) from dual",new String[]{});
        log.info("--"+list.size());
        return "***"+list.size();
    }
}
