package com.xiaomai.springboot.service.msg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author Madison
 * @date 2022/6/2
 */
@Configuration
@ConditionalOnProperty(name = "msg.type",havingValue = "rocket")
public class RocketMsgService implements MsgService{
    @Override
    public String send(String msg) {
        return "RocketMsgService---"+msg;
    }
}
