package com.xiaomai.cloud.nacoswebflux.index.controller;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSONObject;
import com.xiaomai.cloud.nacoswebflux.index.domain.City;
import com.xiaomai.cloud.nacoswebflux.index.service.CityCacheService;
import com.xiaomai.cloud.nacoswebflux.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author developer
 * @date 2021/1/12
 */

@RestController
public class CityCacheController {
    public static final Logger log = LoggerFactory.getLogger(CityCacheController.class);

    @Autowired
    private RedisUtils redisUtils;


    @Qualifier("redisCacheManager")
    @Autowired
    RedisCacheManager redisCacheManager;

    // 使用缓存管理器得到缓存，进行api调用
    @RequestMapping(value = "/cache/getStr/{id}")
    public String getStrById(@PathVariable(value = "id") Integer id) {
        log.info("查询id为{}的字符串信息", id);
        //获取某个缓存
        Cache strCache = redisCacheManager.getCache("str");
        String str = null;
        if (strCache.get(id) == null) {
            str = UUID.fastUUID().toString();
            strCache.put(id, str);
            log.info("从随机数中获取的数据" + str);
        } else {
            SimpleValueWrapper valueWrapper = (SimpleValueWrapper) strCache.get(id);
            str = (String) valueWrapper.get();
            log.info("从缓存获取的数据" + str);
        }
        return str;
    }

    //使用redisUtils进行缓存操作
    @RequestMapping(value = "/hello/{id}")
    public String hello(@PathVariable(value = "id") String id) {
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists(id);
        String str = "";
        if (hasKey) {
            //获取缓存
            Object object = redisUtils.get(id);
            log.info("从缓存获取的数据" + object);
            str = object.toString();

        } else {
            //从数据库中获取信息
            log.info("从数据库中获取数据");
            str = UUID.fastUUID().toString();

            //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
            redisUtils.set(id, str, 10L, TimeUnit.MINUTES);
            log.info("数据插入缓存" + str);
        }
        return str;
    }
}
