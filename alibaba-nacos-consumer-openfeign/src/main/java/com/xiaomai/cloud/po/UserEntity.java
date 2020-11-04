package com.xiaomai.cloud.po;

/**
 * @author wangfeng
 * @date 2020/11/3
 */
public class UserEntity {
    private int Id;
    private String loginName;
    private int bindType;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getBindType() {
        return bindType;
    }

    public void setBindType(int bindType) {
        this.bindType = bindType;
    }
}
