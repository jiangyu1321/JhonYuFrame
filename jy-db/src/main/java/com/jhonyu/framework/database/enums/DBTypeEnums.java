package com.jhonyu.framework.database.enums;

import com.jhonyu.framework.database.constants.DBConnectorUrlTemplate;

/**
 * @Description: 数据库类型枚举
 * @className: DBTypeEnums
 * @userName: jiangyu
 * @date: 2016年2月16日 上午10:11:24
 */
public enum DBTypeEnums 
{
    MS_SQL(1,"ms_sql","sun.jdbc.odbc.JdbcOdbcDriver",DBConnectorUrlTemplate.MS_SQL),
    MY_SQL(2,"mysql","com.mysql.jdbc.Driver",DBConnectorUrlTemplate.MY_SQL),
    ORACLE(3,"oracle","oracle.jdbc.driver.OracleDriver",DBConnectorUrlTemplate.ORACLE);
    
    private Integer value;
    private String code;
    private String driver;
    private String connectUrl;
    
    private DBTypeEnums(Integer value, String code, String driver, String connectUrl)
    {
        this.value = value;
        this.code = code;
        this.driver = driver;
        this.connectUrl = connectUrl;
    }

    public Integer getValue()
    {
        return value;
    }

    public String getCode()
    {
        return code;
    }

    public String getDriver()
    {
        return driver;
    }

    public String getConnectUrl()
    {
        return connectUrl;
    }
    
}
