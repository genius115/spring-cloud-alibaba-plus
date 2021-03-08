package com.xiaomai.cloud.nacosprovider.controller;

import com.xiaomai.boot.myjson.service.MyJsonService;
import com.xiaomai.boot.myjson.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试自定义启动器
 * @author Madison
 * @date 2021/3/2
 */
@RestController
@RequestMapping("demo")
public class DemoController {

    @Autowired
    private MyJsonService myJsonService;


    @GetMapping()
    public String test(){
        Person person =new Person("张三",18,"陕西西安");
        return myJsonService.objectToMyJson(person);
    }

}
