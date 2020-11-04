package com.xiaomai.cloud.service;

import com.alibaba.fastjson.JSON;
import com.xiaomai.cloud.bean.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author wangfeng
 * @date 2020/11/2
 */
@Service
public class SessionService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    Long expireTime = 10800L;

    public void saveSession(LoginUser loginUser) {
        String key =String.format("login:user:%s", loginUser.userToken);
        redisTemplate.opsForValue().set(key, JSON.toJSONString(loginUser),expireTime,TimeUnit.SECONDS);
    }


    public LoginUser getSession(String token) {
//		String key =String.format("login:user:%s", token);
//		String s = redisTemplate.opsForValue().get(key);
//		if(StringUtils.isEmpty(s)) {
//			return null;
//		}
//		return JSON.parseObject(s, LoginUser.class);
        if("success".equals(token)) {
            return new LoginUser("lucy","real lucy","123456");
        }else {
            return null;
        }
    }
}