package com.xiaomai.xiaomaispringbootbase.util;

/**
 * @author Madison
 * @date 2022/7/9
 */
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DruidUtils {

    private static Connection connection = null;
    //获取元数据
    public static DataSource getDatasource() {
        DataSource dataSource = DruidConnection.getInstace().getDataSource();
        return dataSource;
    }

    //获取链接
    public static Connection getConnection() {
        connection = DruidConnection.getInstace().getConnection();
        return connection;
    }

    //归还资源
    public void release() {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
