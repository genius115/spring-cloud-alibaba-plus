package com.xiaomai.xiaomaispringbootbase.util;

/**
 * @author Madison
 * @date 2022/7/9
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xiaomai.xiaomaispringbootbase.config.C3p0Properties;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 获取数据库连接
 *
 * @author vander
 * @date 2018年11月26日
 */
public class Db {
    private static final QueryRunner runner = new QueryRunner();
    private static ComboPooledDataSource ds = null;
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
    
    public Db(C3p0Properties c3p0){
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass(c3p0.getDriverClass());
            ds.setJdbcUrl(c3p0.getJdbcUrl());
            ds.setUser(c3p0.getUser());
            ds.setPassword(c3p0.getPassword());
            ds.setInitialPoolSize(c3p0.getInitialPoolSize());
            ds.setMinPoolSize(c3p0.getMinPoolSize());
            ds.setMaxPoolSize(c3p0.getMaxPoolSize());
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
    /**
     * 获取连接
     *
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        Connection conn = threadLocal.get();
        if (null == conn) {
            conn = getDataSource().getConnection();
            threadLocal.set(conn);
        }
        return conn;
    }
    /**
     * 开启事务
     */
    private void startTransaction() {
        try {
            Connection conn = threadLocal.get();
            if (null == conn) {
                conn = getConnection();
                threadLocal.set(conn);
            }
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 事务提交
     */
    private void commit() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 事务回滚
     */
    private void rollback() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放连接
     */
    private void close() {
        try {
            Connection conn = threadLocal.get();
            if (conn != null) {
                conn.close();
                threadLocal.remove();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 获取数据源
     *
     * @return
     */
    private DataSource getDataSource() {
        return ds;
    }
    /**
     * 查询（返回Array结果）
     * @param sql
     * @param params
     * @return
     */
    public Object[] queryArray(String sql, Object... params) {
        Object[] result = null;
        try {
            result = runner.query(getConnection(), sql, new ArrayHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询（返回ArrayList结果）
     * @param sql
     * @param params
     * @return
     */
    public List<Object[]> queryArrayList(String sql, Object... params) {
        List<Object[]> result = null;
        try {
            result = runner.query(getConnection(), sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询（返回Map结果）
     * @param sql
     * @param params
     * @return
     */
    public Map<String, Object> queryMap(String sql, Object... params) {
        Map<String, Object> result = null;
        try {
            result = runner.query(getConnection(), sql, new MapHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询（返回MapList结果）
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> queryMapList(String sql, Object... params) {
        List<Map<String, Object>> result = null;
        try {
            result = runner.query(getConnection(), sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /* 查询（返回Bean结果） */
    public <T> T queryBean(Class<T> cls, Map<String, String> map, String sql,
                           Object... params) {
        T result = null;
        try {
            if (map != null) {
                result = runner.query(getConnection(), sql, new BeanHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))),
                        params);
            } else {
                result = runner.query(getConnection(), sql, new BeanHandler<T>(cls), params);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询（返回BeanList结果）
     * @param cls
     * @param map
     * @param sql
     * @param params
     * @return
     */
    public <T> List<T> queryBeanList(Class<T> cls, Map<String, String> map, String sql,
                                     Object... params) {
        List<T> result = null;
        try {
            if (map != null) {
                result = runner.query(getConnection(), sql,
                        new BeanListHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))), params);
            } else {
                result = runner.query(getConnection(), sql, new BeanListHandler<T>(cls), params);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询指定列名的值（单条数据）
     * @param column
     * @param sql
     * @param params
     * @return
     */
    public <T> T queryColumn(String column, String sql, Object... params) {
        T result = null;
        try {
            result = runner.query(getConnection(), sql, new ScalarHandler<T>(column), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询指定列名的值（多条数据）
     * @param column
     * @param sql
     * @param params
     * @return
     */
    public <T> List<T> queryColumnList(String column, String sql, Object... params) {
        List<T> result = null;
        try {
            result = runner.query(getConnection(), sql, new ColumnListHandler<T>(column), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 查询指定列名对应的记录映射
     * @param column
     * @param sql
     * @param params
     * @return
     */
    public <T> Map<T, Map<String, Object>> queryKeyMap(String column, String sql,
                                                       Object... params) {
        Map<T, Map<String, Object>> result = null;
        try {
            result = runner.query(getConnection(), sql, new KeyedHandler<T>(column), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
    /**
     * 更新（包括UPDATE、INSERT、DELETE，返回受影响的行数）
     * @param sql
     * @param params
     * @return
     */
    public int update( String sql, Object... params) {
        int result = 0;
        try {
            startTransaction();
            result = runner.update(getConnection(), sql, params);
            commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }finally {
            close();
        }
        return result;
    }
}
