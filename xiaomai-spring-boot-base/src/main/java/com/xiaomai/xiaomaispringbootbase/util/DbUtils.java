package com.xiaomai.xiaomaispringbootbase.util;

/**
 * @author Madison
 * @date 2022/7/9
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 阿里的数据库连接池
 * 性能最好的
 * Druid
 * */
public class DbUtils {
    //声明连接池对象
    private static DruidDataSource ds;
    static{
        //实例化配置对象
        Properties properties=new Properties();
        try {//加载配置文件
            properties.load(DbUtils.class.getClassLoader().getResourceAsStream("database.properties"));
            ds = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }
    
    //获取连接对象
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
