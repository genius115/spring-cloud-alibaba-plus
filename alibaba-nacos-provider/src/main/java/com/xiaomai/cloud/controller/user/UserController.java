package com.xiaomai.cloud.controller.user;

import com.xiaomai.cloud.beans.user.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangfeng
 * @date 2020/11/20
 */
@Api(value = "用户接口")
@RestController
public class UserController {


    @ApiOperation(value = "获取用户信息接口", nickname = "根据用户ID获取用户相关信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @PostMapping("/postMember")
    public UserEntity postMember(@RequestParam Integer id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName("admin");
        return userEntity;
    }


    @ApiOperation(value = "添加用户", nickname = "添加用户接口1", notes = "入参是复杂对象", produces = "application/json")
    @PostMapping("/postUser")
    @ResponseBody
    @ApiImplicitParam(paramType = "query", name = "userId", value = "用户id", required = true, dataType = "int")
    public UserEntity postUser(@RequestBody UserEntity user, @RequestParam("userId") int userId) { // 这里用包装类竟然报错
        if (user.getId() == userId) {
            return user;
        }
        return new UserEntity();
    }


    @ApiOperation(value = "添加用户", nickname = "添加用户接口2", notes = "入参是简单对象", produces = "application/json")
    @PostMapping("/addUser")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userName", value = "用户姓名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户id", required = true, dataType = "int")})
    public UserEntity addUser(String userName, int id) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userName);
        userEntity.setId(id);
        return userEntity;
    }

}
