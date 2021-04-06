package com.xiaomai.cloud.test.exception;

import cn.hutool.core.date.DateUtil;

/**
 *
 * @Description 限流自定义异常
 * @author Madison
 * @date 2021/4/5
 */
public class LimitAccessException extends RuntimeException{

    public LimitAccessException(String message) {
        //super(message);
        System.out.println("LimitAccessException = [" + DateUtil.now() + com.opslab.util.DateUtil.currentDateTime() + "]");
    }
}
