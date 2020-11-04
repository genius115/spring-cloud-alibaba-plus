package com.xiaomai.cloud.bean;

import lombok.Data;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
public class LoginUser {

    public static String userToken;

    private String userName;
    private String realName;
    private String pwd;

    public LoginUser() {
    }
    public LoginUser(String userName, String realName, String pwd) {
        this.userName = userName;
        this.realName = realName;
        this.pwd = pwd;
    }
    public static String getUserToken() {
        return userToken;
    }
    public static void setUserToken(String userToken) {
        LoginUser.userToken = userToken;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
