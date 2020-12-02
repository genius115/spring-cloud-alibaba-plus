package com.xiaomai.cloud.demo.controller;

import com.xiaomai.cloud.demo.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 *
 *  1、Repository接口
 *  2、CrudRepository接口
 *  3、PagingAndSortingRepository接口
 *
 *  4、JpaRepository接口
 *  5、JPASpecificationExecutor接口
 * @author wangfeng
 * @date 2020/12/1
 */
@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/jpa/base")
    public String base(){
        this.demoService.base();
        return "OK"+new Date();
    }
    @GetMapping("/jpa/crud")
    public String crud(){
        this.demoService.crud();
        return "OK"+new Date();
    }

    @GetMapping("/jpa/crudpage")
    public String crudPage(){
        this.demoService.crudPage();
        return "OK"+new Date();
    }

    @GetMapping("/jpa/crudspec")
    public String crudSpec(){
        this.demoService.crudSpecification();
        this.demoService.crudSpecification1();
        this.demoService.crudSpecification2();
        return "OK"+new Date();
    }

}
