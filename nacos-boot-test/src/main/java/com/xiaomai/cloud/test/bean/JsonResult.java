package com.xiaomai.cloud.test.bean;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author Madison
 * @date 2021/3/5
 */
public class JsonResult {
    private boolean success;
    private String  msg;
    private int code;
    private T data;

    public JsonResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }


}
