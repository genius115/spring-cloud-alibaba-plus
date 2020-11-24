package com.xiaomai.cloud;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @author wangfeng
 * @date 2020/11/23
 */
public class TestComm {
    public static void main(String[] args) {
        boolean result = DateUtils.isSameDay(new Date(),new Date());
        System.out.println(result);
    }
}
