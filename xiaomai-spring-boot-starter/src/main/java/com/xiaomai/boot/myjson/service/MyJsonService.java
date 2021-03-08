package com.xiaomai.boot.myjson.service;

import com.alibaba.fastjson.JSON;

/**
 * 业务处理类
 * @author Madison
 * @date 2021/3/2
 */
public class MyJsonService {
    //前缀
    private String prefixName;
    //后缀
    private String suffixName;

    /**
     * 将java对象转为带指定前后缀的字符串
     * @param o
     * @return
     */
    public String objectToMyJson(Object o){
        return prefixName+ JSON.toJSONString(o)+suffixName;
    }
    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }
}
