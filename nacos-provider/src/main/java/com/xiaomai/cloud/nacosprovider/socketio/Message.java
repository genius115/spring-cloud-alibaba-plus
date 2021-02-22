package com.xiaomai.cloud.nacosprovider.socketio;

import java.util.Map;

/**
 * @author Madison
 * @date 2021/1/25
 */
public class Message {
    private String type;
    private Map<String, Object> payload;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type='" + type + '\'' +
                ", payload=" + payload +
                '}';
    }
}
