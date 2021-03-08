package com.xiaomai.cloud.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Madison
 * @date 2021/3/2
 */
public class TestNacosPwd {
    public static void main(String[] args) {

        System.out.println("Nacos修改默认密码\n" +
                "原始密码【123456】\n" +
                "加密密码【" + new BCryptPasswordEncoder().encode("123456") + "】");
    }
}
