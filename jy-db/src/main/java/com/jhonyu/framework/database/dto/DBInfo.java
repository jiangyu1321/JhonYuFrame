package com.jhonyu.framework.database.dto;

/**
 * @Description: 数据库信息
 * @className: DBInfo
 * @userName: jiangyu
 * @date: 2016年2月16日 上午10:51:40
 */
public class DBInfo
{
    /**
     * 用户名
     */
    private String user = "root";

    /**
     * 密码
     */
    private String password = "root";

    /**
     * 数据库名称
     */
    private String databaseName = "";

    /**
     * 项目包根路径
     */
    private String packageName = "";

    /**
     * 数据库服务的主机地址
     */
    private String host;

    public DBInfo()
    {
        // TODO Auto-generated constructor stub
    }

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public void setDatabaseName(String databaseName)
    {
        this.databaseName = databaseName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }
}
