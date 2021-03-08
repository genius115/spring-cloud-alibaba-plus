package com.xiaomai.cloud.test.controller;

import com.xiaomai.cloud.test.bean.JsonResult;
import com.xiaomai.cloud.test.exception.UserNotExistException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2021/3/5
 */
@RestController
@RequestMapping("/test/exception")
public class TestGlobeExceptionController {

    @GetMapping(value = "/user/{id}")
    public JsonResult findUser(@PathVariable Integer id) throws UserNotExistException {
        if (id < 100) {
            throw new UserNotExistException(id);
        }
        return new JsonResult(true, "暂未实现用户查询功能");
    }
}
