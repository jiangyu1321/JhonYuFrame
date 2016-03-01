package com.jhonyu.framework.database.dto;

/**
 * @Description: 数据库信息
 * @className: DBInfo
 * @userName: jiangyu
 * @date: 2016年2月16日 上午10:34:22
 */
public class DBInfoDemo
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

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }

    public String getDatabaseName()
    {
        return databaseName;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public static class Builder
    {
        /**
         * 用户名
         */
        private String user = "root";

        /**
         * 密码
         */
        private String password = "1234";

        /**
         * 数据库名称
         */
        private String databaseName = "";

        /**
         * 项目包根路径
         */
        private String packageName = "";

        public Builder user(String val)
        {
            user = val;
            return this;
        }

        public Builder password(String val)
        {
            password = val;
            return this;
        }

        public Builder databaseName(String val)
        {
            databaseName = val;
            return this;
        }

        public Builder packageName(String val)
        {
            packageName = val;
            return this;
        }

        public DBInfoDemo build()
        {
            return new DBInfoDemo(this);
        }
    }

    private DBInfoDemo(Builder builder)
    {
        user = builder.user;
        password = builder.password;
        databaseName = builder.databaseName;
        packageName = builder.packageName;
    }

}
