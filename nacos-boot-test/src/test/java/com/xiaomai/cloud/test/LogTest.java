package com.xiaomai.cloud.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @runWith注解作用：
 * --@RunWith就是一个运行器
 * --@RunWith(JUnit4.class)就是指用JUnit4来运行
 * --@RunWith(SpringJUnit4ClassRunner.class)，让测试运行于Spring测试环 境，以便在测试开始的时候自动创建Spring的应用上下文
 * --@RunWith(Suite.class)的话就是一套测试集合
 *
 *
 * @author Madison
 * @date 2021/3/4
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LogTest {
    Logger logger =LoggerFactory.getLogger(Logger.class);

    //logging.level.com.xiaomai=trace
    //日志只输出了info、warn、error。也就是说SpringBoot默认是设置info级别。
    @Test
    public void contextLoad(){
        logger.trace("这是trace日志。。。。。。");
        logger.debug("这是debug日志。。。。。。");
        logger.info("这是info日志。。。。。。");
        logger.warn("这是warn日志。。。。。。");
        logger.error("这是error日志。。。。。。");
    }

}
