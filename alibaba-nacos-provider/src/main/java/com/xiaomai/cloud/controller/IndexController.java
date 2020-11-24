package com.xiaomai.cloud.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangfeng
 * @date 2020/11/20
 */
@Api(value = "IndexController测试接口")
@RestController
public class IndexController {
    @ApiOperation(value = "测试index接口", nickname = "测试IndexController的index接口")
    @GetMapping("/index")
    public String index() {
        return "测试IndexController的index接口...";
    }

}