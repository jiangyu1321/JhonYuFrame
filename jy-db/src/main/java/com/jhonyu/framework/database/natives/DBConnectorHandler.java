package com.jhonyu.framework.database.natives;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jhonyu.framework.database.dto.DBInfo;
import com.jhonyu.framework.database.enums.DBTypeEnums;


/**
 * @Description: 数据库底层最基本的处理
 * @className: DBConnectorHandler
 * @userName: jiangyu
 * @date: 2016年2月16日 上午10:07:20
 */
public class DBConnectorHandler
{
    /**
     * @Description: 获取数据库连接
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:05:58
     * @param typeEnum
     *            数据库类型
     * @param dbinfo
     *            数据库连接信息
     * @return
     */
    public static Connection getConnection(DBTypeEnums typeEnum, DBInfo dbinfo)
    {
        Connection conn = null;
        /** 获取连接的url **/
        String url = constructUrl(typeEnum, dbinfo);
        try
        {
            Class.forName(typeEnum.getDriver());
            conn = DriverManager.getConnection(url, dbinfo.getUser(), dbinfo.getPassword());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * @Description: 组织数据库连接的url
     * @userName: jiangyu
     * @date: 2016年2月16日 上午10:23:10
     * @return
     */
    public static String constructUrl(DBTypeEnums typeEnum, DBInfo dbInfo)
    {
        String host = dbInfo.getHost();
        String database = dbInfo.getDatabaseName();
        return typeEnum.getConnectUrl().replace("{0}", host).replace("{1}", database);
    }

    /**
     * @Description: 查询操作[本函数用来执行用户传入的sql语句(仅限于select语句)]
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:15:12
     * @param conn
     * @param sql
     * @return
     */
    public static ResultSet query(Connection conn, String sql)
    {
        ResultSet rs = null;
        Statement stmt = null;
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(conn, stmt, rs);
        }

        return rs;
    }

    /**
     * @Description: 更新操作 [本方法用来执行更新语句，并返回影响了多少行(insert,update,delete)]
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:15:18
     * @param conn
     * @param sql
     * @return
     */
    public static int update(Connection conn, String sql)
    {
        Statement stmt = null;
        int influenceRecord = 0;
        try
        {
            stmt = conn.createStatement();
            influenceRecord = stmt.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            close(conn, stmt, null);
        }
        return influenceRecord;
    }

    /**
     * @Description: 关闭连接
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:15:24
     * @param conn
     * @param stmt
     * @param rs
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs)
    {
        try
        {
            if (rs != null)
            {
                rs.close();
                rs = null;
            }
            if (stmt != null)
            {
                stmt.close();
                stmt = null;
            }
            if (conn != null)
            {
                conn.close();
                conn = null;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
