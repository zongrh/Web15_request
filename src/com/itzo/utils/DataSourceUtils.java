package com.itzo.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Zon on 2018/6/20.
 */
public class DataSourceUtils {
    private static DataSource dataSource = new ComboPooledDataSource();
    private static ThreadLocal<Connection> t1 = new ThreadLocal<>();

    //直接可以获取一个连接池
    public static DataSource getDataSource() {
        return dataSource;
    }

    //获取连接对象
    public static Connection getConnection() throws SQLException {
        Connection con = t1.get();
        if (con == null) {
            con = dataSource.getConnection();
            t1.set(con);
        }
        return con;
    }

    //开启事务
    public static void startTransaction() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.setAutoCommit(false);
        }
    }

    //事务回滚
    public static void rollback() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.rollback();
        }
    }

    //提交并且 关闭资源及 从ThereadLocall中释放
    public static void commitAndRelease() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.commit();//事务提交
            conn.close();//关闭资源
            t1.remove();//从线程绑定中移除
        }
    }

    //关闭资源方法
    public static void closeConnection() throws SQLException {
        Connection con = getConnection();
        if (con != null) {
            con.close();
        }
    }

    public static void closeStatement(Statement st) throws SQLException {
        if (st != null) {
            st.close();
        }
    }

    public static void closeResultSer(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }


}








