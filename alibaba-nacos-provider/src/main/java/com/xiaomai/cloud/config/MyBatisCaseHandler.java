package com.xiaomai.cloud.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * 拦截器处理 对数据库的字段处理
 *
 * 1、实现元对象处理器接口：com.baomidou.mybatisplus.core.handlers.MetaObjectHandler
 *
 * 2、MetaObjectHandler提供的默认方法的策略均为:如果属性有值则不覆盖,如果填充值为null则不填充
 *
 *
 * @author Madison
 * @date 2021/3/21
 */
@Slf4j
@Component
public class MyBatisCaseHandler implements MetaObjectHandler {
    /**
     * 插入时填充策略
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insertFill succeed!");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);

        // 起始版本 3.3.0(推荐使用)
        //this.strictInsertFill(metaObject, "gmtCreate", LocalDateTime.class, LocalDateTime.now());
        // 或者
        // 起始版本 3.3.3(推荐)
        //this.strictInsertFill(metaObject, "gmtCreate", () -> LocalDateTime.now(), LocalDateTime.class);
        // 或者
        // 也可以使用(3.3.0 该方法有bug)
        //this.fillStrategy(metaObject, "gmtCreate", LocalDateTime.now());
    }

    /**
     * 修改时填充策略
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start updateFill succeed!");
        this.setFieldValByName("gmtModified",new Date(),metaObject);

        // 起始版本 3.3.0(推荐)
        //this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime.class, LocalDateTime.now());
        // 起始版本 3.3.3(推荐)
        // 或者
        //this.strictUpdateFill(metaObject, "gmtModified", () -> LocalDateTime.now(), LocalDateTime.class);
        // 也可以使用(3.3.0 该方法有bug)
        // 或者
        //this.fillStrategy(metaObject, "gmtModified", LocalDateTime.now());

    }
}
