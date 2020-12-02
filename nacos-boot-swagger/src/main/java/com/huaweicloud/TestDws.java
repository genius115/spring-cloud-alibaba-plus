package com.huaweicloud;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

/**
 * //以下用例以gsjdbc4.jar为例，
 * 如果要使用gsjdbc200.jar，
 * 请替换驱动类名（将代码中的“org.postgresql”替换成“com.huawei.gauss200.jdbc”）与连接URL串前缀（将“jdbc:postgresql”替换为“jdbc:gaussdb”）。
 * //演示基于JDBC开发的主要步骤，会涉及创建数据库、创建表、插入数据等。
 * @author wangfeng
 * @date 2020/11/25
 */
public class TestDws {

    //创建数据库连接。
    public static Connection GetConnection(String username, String passwd) {
        String driver = "org.postgresql.Driver";
        String sourceURL = "jdbc:postgresql://localhost:8000/postgres";
        Connection conn = null;
        try {
            //加载数据库驱动。
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            //创建数据库连接。
            conn = DriverManager.getConnection(sourceURL, username, passwd);
            System.out.println("Connection succeed!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return conn;
    }

    //执行普通SQL语句，创建customer_t1表。
    public static void CreateTable(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            //执行普通SQL语句。
            int rc = stmt
                    .executeUpdate("CREATE TABLE customer_t1(c_customer_sk INTEGER, c_customer_name VARCHAR(32));");

            stmt.close();
        } catch (SQLException e) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    //执行预处理语句，批量插入数据。
    public static void BatchInsertData(Connection conn) {
        PreparedStatement pst = null;

        try {
            //生成预处理语句。
            pst = conn.prepareStatement("INSERT INTO customer_t1 VALUES (?,?)");
            for (int i = 0; i < 3; i++) {
                //添加参数。
                pst.setInt(1, i);
                pst.setString(2, "data " + i);
                pst.addBatch();
            }
            //执行批处理。
            pst.executeBatch();
            pst.close();
        } catch (SQLException e) {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    //执行预编译语句，更新数据。
    public static void ExecPreparedSQL(Connection conn) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn
                    .prepareStatement("UPDATE customer_t1 SET c_customer_name = ? WHERE c_customer_sk = 1");
            pstmt.setString(1, "new Data");
            int rowcount = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }


    //执行存储过程。
    public static void ExecCallableSQL(Connection conn) {
        CallableStatement cstmt = null;
        try {

            cstmt=conn.prepareCall("{? = CALL TESTPROC(?,?,?)}");
            cstmt.setInt(2, 50);
            cstmt.setInt(1, 20);
            cstmt.setInt(3, 90);
            //注册out类型的参数，类型为整型。
            cstmt.registerOutParameter(4, Types.INTEGER);
            cstmt.execute();
            //获取out参数
            int out = cstmt.getInt(4);
            System.out.println("The CallableStatment TESTPROC returns:"+out);
            cstmt.close();
        } catch (SQLException e) {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }


    /**
     * 主程序，逐步调用各静态方法。
     * @param args
     */
    public static void main(String[] args) {
        //创建数据库连接。
        Connection conn = GetConnection("tester", "Password1234");

        //创建表。
        CreateTable(conn);

        //批插数据。
        BatchInsertData(conn);

        //执行预编译语句，更新数据。
        ExecPreparedSQL(conn);

        //执行存储过程。
        //ExecCallableSQL(conn);

        //关闭数据库连接。
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}