package com.xiaomai.cloud.rabbit;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author Madison
 * @date 2021/2/20
 */
@Slf4j
public class RabbitUtils {

    private static ConnectionFactory connectionFactory;

    //类加载时执行，只执行一次   单利模式
    static {
        //重量级资源
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/msg");
        connectionFactory.setUsername("msg");
        connectionFactory.setPassword("msg");
    }

    //定义连接提供的对象的方法
    public static Connection getConnection() {
        try {
            return connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道，关闭连接工具
    public static void closeChannelAndConnec(Channel channel, Connection connection) {
        try {
            if (channel != null) channel.close();
            if (connection != null) connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void doWork(String task) {
        log.debug("开始时间：{}",System.currentTimeMillis());
        for (char ch : task.toCharArray()) {
            if (ch == '.') {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        log.debug("结束时间：{}",System.currentTimeMillis());
    }
}
