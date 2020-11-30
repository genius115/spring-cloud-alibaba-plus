package com.xiaomai.cloud.controller.user;

import com.xiaomai.cloud.beans.user.UserEntity;
import com.xiaomai.cloud.config.BusinessException;
import com.xiaomai.cloud.config.minio.MinIoUtils;
import com.xiaomai.cloud.po.order.Payment;
import com.xiaomai.cloud.po.user.User;
import com.xiaomai.cloud.service.minio.UploadService;
import com.xiaomai.cloud.service.order.OrderService;
import com.xiaomai.cloud.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangfeng
 * @date 2020/11/20
 */
@Api(value = "用户接口")
@RestController
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @Autowired
    private UploadService UploadServiceImpl;


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

    @ApiOperation(value = "新增用户-Mysql8", nickname = "新增")
    @PostMapping("/user/add")
    public String addUser(@Validated @RequestBody User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                System.out.println(fieldError);
            }
            return "fail";
        }
        return userServiceImpl.addUser(user);
    }

    @ApiOperation(value = "删除用户-Mysql8", nickname = "删除")
    @DeleteMapping("/user/remove")
    public String removeUser(String id){
        return userServiceImpl.removeUser(id);
    }


    @ApiOperation("上传文件")
    @PostMapping(value = "/user/upload/file",consumes = "multipart/*", headers = "content-type=multipart/form-data")
    public String upload(@RequestParam("file") MultipartFile file) {
        try {
            return UploadServiceImpl.upload(file);
        } catch (BusinessException e) {
            e.printStackTrace();
            return "error" + System.currentTimeMillis();
        }
    }

    @ApiOperation("下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "objectName", value = "资源名", required = true, dataType = "String")})
    @GetMapping(value = "/user/downurl/file")
    public String downurl(@RequestParam String objectName) {
       return UploadServiceImpl.downurl(objectName);
    }

}
