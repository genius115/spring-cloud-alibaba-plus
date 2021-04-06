package com.xiaomai.cloud.api.test.controller;

import com.xiaomai.cloud.api.comm.R;
import com.xiaomai.cloud.api.comm.annotation.Limiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2021/4/6
 */
@RestController
public class TestController {

    @RequestMapping(value = "/t/test1")
    @Limiter(LimitNum = 3)
    public R<String> Curry() {
        System.out.println("接口1请求成功!");
        return new R.Builder<String>().setData("test1请求成功!").ok();
    }

    @RequestMapping(value = "/t/test2")
    @Limiter(LimitNum = 0.01)
    public R<String> Harden() {
        System.out.println("接口2请求成功！");
        return new R.Builder<String>().setData("test2请求成功!").ok();
    }
}
