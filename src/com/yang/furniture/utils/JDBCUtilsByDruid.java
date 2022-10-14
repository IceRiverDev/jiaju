package com.yang.furniture.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author 刘洋
 * @version 1.0
 * 基于druid数据库连接池的工具类
 */
public class JDBCUtilsByDruid {

    private static DataSource ds;
    private static final ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    //在静态代码块完成 ds初始化
    static {
        Properties properties = new Properties();
        try {
            properties.load(JDBCUtilsByDruid.class.getClassLoader()
                    .getResourceAsStream("druid.properties"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //编写getConnection方法
    public static Connection getConnection() throws SQLException {

        Connection connection = connectionThreadLocal.get();

        if (connection == null) {
            Connection dsConnection = null;
            dsConnection = ds.getConnection();
            dsConnection.setAutoCommit(false);
            connectionThreadLocal.set(dsConnection);
            connection = dsConnection;
        }

        return connection;
    }


    public static void commit() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        connectionThreadLocal.remove();
    }

    public static void rollback() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        connectionThreadLocal.remove();
    }

    //关闭连接, 老师再次强调： 在数据库连接池技术中，close 不是真的断掉连接
    //而是把使用的Connection对象放回连接池
    public static void close(ResultSet resultSet, Statement statement, Connection connection) {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
