package com.xiaomai.boot.myjson.nacosbootknife4j.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * 分组排序---建议优先级是：@ApiSupport>@ApiSort>@Api
 *
 *
 * @author Madison
 * @date 2021/2/2
 */
@ApiSupport(author = "xiaoymin@foxmail.com",order = 284)
@Api(tags = "首页模块")
@RestController
public class TestController {

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    @ApiOperation(value = "向客人问好")

    @GetMapping("/sayHi")
    public ResponseEntity<String> sayHi(@RequestParam(value = "name") String name) {
        return ResponseEntity.ok("Hi:" + name);
    }

    @ApiOperationSupport(author = "1111-xiaoymin@foxmail.com",order = 284)
    @ApiOperation(value = "写文档注释我是认真的")
    @GetMapping("/getRealDoc")
    public ResponseEntity<String> getRealDoc(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Hi:" + new Date());
    }

}
