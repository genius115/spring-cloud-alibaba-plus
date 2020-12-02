package com.xiaomai.cloud.config;

/**
 * @author wangfeng
 * @date 2020/11/27
 */
public enum Constant {
    VIDEO_MAX_SIZE(10000000,"文件大小"),
    IMG_MAX_SIZE(5000000,"图片大小");

    //枚举value字段
    private int value;
    //枚举描述字段
    private String description;

    public int getValue() {
        return value;
    }
    public String getDescription() {
        return description;
    }
    Constant(int value,String description){ //构造初始化赋值
        this.description=description;
        this.value=value;
    }



}
