package com.xiaomai.cloud.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.nacos.common.utils.UuidUtils;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author Madison
 * @date 2021/1/25
 */
@Slf4j
public class SocketClient {

    public static void main(String[] args) {
        // 服务端socket.io连接通信地址
        String url = "http://localhost:9092";
        try {
            IO.Options options = new IO.Options();
            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
            final Socket socket = IO.socket(url + "?mac="+UuidUtils.generateUuid().substring(0,8), options);

            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("connected", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on("disconnected", objects -> log.debug("服务端:" + objects[0].toString()));

            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("push_data_event", objects -> log.debug("服务端push_data_event:" + objects[0].toString()));

            // 自定义事件`message` -> 接收服务端消息
            socket.on("message", objects -> log.debug("服务端message:" + objects[0].toString()));

            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on("messageevent", objects -> log.debug("服务端messageevent:" + objects[0].toString()));

            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            // socket.on("myBroadcast", objects -> log.debug("服务端：" + objects[0].toString()));

            socket.connect();

            for (int i=0;i<10;i++) {
                Thread.sleep(3000);
                // 自定义事件`messageevent` -> 向服务端发送消息
                socket.emit("messageevent-server", "发送数据 " + DateUtil.now());
            }
            Thread.sleep(5000);
            socket.disconnect();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
