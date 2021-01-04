package com.xiaomai.cloud.controller.test;

import com.alibaba.fastjson.JSONObject;
import com.xiaomai.cloud.service.PostgresqlService;
import com.xiaomai.cloud.service.postgres.BookServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@RestController
@RefreshScope
@Api(value = "test", tags = "测试接口")
public class TestController {

    @Autowired
    private PostgresqlService postgresqlService;

    @Autowired
    private BookServiceImpl bookServiceImpl;


    @GetMapping("/get")
    public String get(){
        return "Spring Cloud Alibaba Provider 我来啦..."+ new Date();
    }

    @GetMapping("/one")
    public String demo(){
        return "demo";
    }

    @GetMapping("/demo/one")
    public String demoone(){
        return "demo-one";
    }

    @GetMapping("/time/out")
    public String requestTimeOut(){
        try {
            TimeUnit.SECONDS.sleep(6);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return "Test Request Time Out----OK";
    }

    @GetMapping("/postgres/getBook")
    public String getBook(){
        return JSONObject.toJSONString(postgresqlService.selectAllBook());
    }

    @GetMapping("/postgres/getBookPage")
    public String getBook(@RequestParam("page") Integer pageNum,
                          @RequestParam("size") Integer sizeNum){
        return JSONObject.toJSONString(bookServiceImpl.selectAllBook(pageNum,sizeNum));
    }

    @GetMapping("/postgres/getBookPageByName")
    public String getBook(@RequestParam("page") Integer pageNum,
                          @RequestParam("size") Integer sizeNum,
                          @RequestParam("name") String name){
        return JSONObject.toJSONString(bookServiceImpl.selectBookPageByName(pageNum,sizeNum,name));
    }


    @GetMapping("/postgres/getBookPageByCodeORName")
    public String getBook(@RequestParam("page") Integer pageNum,
                          @RequestParam("size") Integer sizeNum,
                          @RequestParam("code") String code,
                          @RequestParam("name") String name){
        return JSONObject.toJSONString(bookServiceImpl.selectBookPageByCodeORName(pageNum,sizeNum,code,name));
    }
}

