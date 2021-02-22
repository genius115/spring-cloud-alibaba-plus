package com.xiaomai.cloud.nacosprovider.socketio;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 接收前台用户信息类
 * @author liangxifeng 2018-07-07
 */
@Component
@ToString
public class MessageInfo implements Serializable {

    String msgContent;

    public MessageInfo() {
    }

    public MessageInfo(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgContent() {
        return this.msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;

    }
}