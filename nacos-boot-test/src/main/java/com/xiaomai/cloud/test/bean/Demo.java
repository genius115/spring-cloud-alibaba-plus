package com.xiaomai.cloud.test.bean;

import com.xiaomai.cloud.test.valid.CaseMode;
import com.xiaomai.cloud.test.valid.CheckCase;

/**
 * @author Madison
 * @date 2021/3/4
 */
public class Demo{

    @CheckCase(value = CaseMode.LOWER,message = "userName必须是小写")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
