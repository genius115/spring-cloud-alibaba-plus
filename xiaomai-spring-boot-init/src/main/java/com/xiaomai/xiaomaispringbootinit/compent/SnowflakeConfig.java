package com.xiaomai.xiaomaispringbootinit.compent;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Madison
 * @date 2021/6/8
 */
@Slf4j
@Component
public class SnowflakeConfig {

    @Value("${snowflake.workerId}")
    private Long workerId;

    @Autowired
    private MyConfigDemoBean MyConfigDemoBean;

    @Bean
    public Snowflake getSnowflake() {
        log.info("**********配置信息**********");
        log.info("**********MyConfigDemoBean-phone:{}**********",MyConfigDemoBean.getPhone());
        log.info("**********MyConfigDemoBean-interest:{}**********",MyConfigDemoBean.getInterests().get(0));
        log.info("**********MyConfigDemoBean-books[book0]:{}-{}**********",MyConfigDemoBean.getBooks().get(0).getId(),MyConfigDemoBean.getBooks().get(0).getName());
        log.info("**********snowflake.workerId:{}**********",workerId);
        return IdUtil.createSnowflake(workerId,2);
    }
}
