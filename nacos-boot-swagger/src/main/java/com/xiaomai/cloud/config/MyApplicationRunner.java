package com.xiaomai.cloud.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Madison
 * @date 2021/3/31
 */
@Component
// 指定其执行顺序,值越小优先级越高
@Order(value = 1)
public class MyApplicationRunner implements ApplicationRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MyApplicationRunner.class);
    /**
     * 工程启动结束后会立即执行的方法
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOG.info("MyApplicationRunner execute ................args:{}",args);
    }
}