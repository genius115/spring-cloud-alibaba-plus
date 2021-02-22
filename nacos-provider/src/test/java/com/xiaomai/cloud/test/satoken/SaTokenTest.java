package com.xiaomai.cloud.test.satoken;

import cn.dev33.satoken.stp.StpUtil;

/**
 * @author Madison
 * @date 2021/2/6
 */
public class SaTokenTest {
    public static void main(String[] args) {
        StpUtil.setLoginId("1233");

        // 标记当前会话登录的账号id
// 建议的参数类型：long | int | String， 不可以传入复杂类型，如：User、Admin等等
        //StpUtil.setLoginId(Object loginId);

// 当前会话注销登录
        StpUtil.logout();

// 获取当前会话是否已经登录，返回true=已登录，false=未登录
        StpUtil.isLogin();

// 检验当前会话是否已经登录, 如果未登录，则抛出异常：`NotLoginException`
        StpUtil.checkLogin();
    }
}
