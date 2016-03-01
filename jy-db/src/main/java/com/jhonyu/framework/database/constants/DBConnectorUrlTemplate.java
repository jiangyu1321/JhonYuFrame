package com.jhonyu.framework.database.constants;

/**
 * @Description: 不同数据库连接的url模板
 * @className: DBDriverTemplate
 * @userName: jiangyu
 * @date: 2016年2月16日 上午10:57:10
 */
public interface DBConnectorUrlTemplate
{
    /** msSql **/
    String MS_SQL = "";

    /** mySql **/
    String MY_SQL = "jdbc:mysql://{0}/{1}?useUnicode=true&amp;characterEncoding=UTF-8";

    /** oracle **/
    String ORACLE = "jdbc:oracle:thin:@{0}:1521:{1}";

}
