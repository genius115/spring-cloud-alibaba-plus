package com.xiaomai.xiaomaispringbootbase.util;

import com.xiaomai.xiaomaispringbootbase.Bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author Madison
 * @date 2022/7/9
 */
public class TestDruidAndDbUtil {
    
    @Test
    public void test1() throws Exception {
        for(int i=0;i<1000;i++) {
            Connection connection=DbUtils.getConnection();
            if(connection!=null) {
                System.out.println("连接成功"+i+"..."+connection.hashCode()+connection.toString());
            }
            connection.close();


            //获取链接
            QueryRunner runner = new QueryRunner(DruidUtils.getDatasource());
            //编写sql语句
            String sql =" SELECT u.user_id AS userId,u.user_name AS userName,u.age FROM tb_user u";
            //返回查询值
            List<User> user = runner.query(sql,new BeanListHandler<>(User.class));
            System.out.println(user);
        }
    }    
}
