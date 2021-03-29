package com.xiaomai.cloud.test.controller;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试 sa-token框架
 * @author Madison
 * @date 2021/3/24
 */
@Slf4j
@RequestMapping("/auth")
@RestController
public class LoginController {

    @RequestMapping("/login")
    public String loginUser(String userId){
        //标记当前会话登录的账号id
        //建议的参数类型：long | int | String， 不可以传入复杂类型，如：User、Admin等等
        StpUtil.setLoginId(userId);
        return "login success!";
    }

    @RequestMapping("/query")
    public String query(){
        /*
        StpUtil.checkLogin()
        检验当前会话是否已经登录, 如果未登录，则抛出异常：NotLoginException
        扩展：NotLoginException 对象可通过 getLoginKey() 方法获取具体是哪个 StpLogic 抛出的异常
        扩展：NotLoginException 对象可通过 getType() 方法获取具体的场景值，详细参考章节：未登录场景值
        */

        //获取当前会话是否已经登录，返回true=已登录，false=未登录
        if(StpUtil.isLogin()){
            //获取当前StpLogic的token名称
            String tokenName = StpUtil.getTokenName();
            //获取当前会话登录id, 如果未登录，则抛出异常：NotLoginException
            //StpUtil.getLoginId();
            String userId = StpUtil.getLoginIdAsString();
//            类似API还有：
//            StpUtil.getLoginIdAsString() 获取当前会话登录id, 并转化为String类型
//            StpUtil.getLoginIdAsInt() 获取当前会话登录id, 并转化为int类型
//            StpUtil.getLoginIdAsLong() 获取当前会话登录id, 并转化为long类型

            // 获取当前会话的token值
            log.info("TokenValue:"+StpUtil.getTokenValue());
            log.info("MD5（TokenValue）:"+SaSecureUtil.md5(StpUtil.getTokenValue()));

            // 获取当前会话的token信息参数
            StpUtil.getTokenInfo();
            return DateUtil.now()+"：欢迎您使用系统！["
                    +userId+";"
                    +tokenName+";"
                    +StpUtil.getTokenValue()+";"
                    +StpUtil.getTokenInfo()+
                    "]";
        }else{
            return DateUtil.now()+"：请您登陆系统，谢谢！";
        }
    }


    // 测试角色接口， 浏览器访问： http://localhost:8081/test/testRole
    @RequestMapping("/testRole")
    public String testRole() {
        System.out.println("======================= 进入方法，测试角色接口 ========================= ");

        System.out.println("是否具有角色标识 user " + StpUtil.hasRole("user"));
        System.out.println("是否具有角色标识 admin " + StpUtil.hasRole("admin"));

        System.out.println("没有admin权限就抛出异常");
        StpUtil.checkRole("admin");

        System.out.println("在【admin、user】中只要拥有一个就不会抛出异常");
        StpUtil.checkRoleOr("admin", "user");

        System.out.println("在【admin、user】中必须全部拥有才不会抛出异常");
        StpUtil.checkRoleAnd("admin", "user");

        System.out.println("角色测试通过");

        /**
         * 持久化的key前缀，多账号认证体系时以此值区分，比如：login、user、admin
         */
        StpUtil.getLoginKey();

        return "testRole success!";
    }


    @RequestMapping("/logout")
    public String  logoutUser(){
        //当前会话注销登录
        StpUtil.logout();
        return "logout success!";
    }
}
