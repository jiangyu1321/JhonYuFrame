package com.jhonyu.framework.database.natives.mysql;


import com.jhonyu.framework.database.natives.specification.DBType2JavaTypeHelper;


/**
 * @Description: mysql数据类型的转换
 * @className: MySqlTypeTransfer
 * @userName: jiangyu
 * @date: 2016年2月16日 上午11:35:43
 */
public class MySqlTypeTransfer implements DBType2JavaTypeHelper
{

    @Override
    public String transfer(String dbFieldType)
    {
        if (dbFieldType.contains("tinyint"))
        {
            return "Integer";
        }
        else if (dbFieldType.contains("bigint"))
        {
            return "Long";
        }
        else if (dbFieldType.contains("int") || dbFieldType.contains("unsigned"))
        {
            return "Integer";
        }
        else if (dbFieldType.contains("timestamp") 
                    || dbFieldType.contains("datetime")
                    || dbFieldType.contains("date"))
        {
            return "Date";
        }
        else if (dbFieldType.contains("decimal"))
        {
            return "BigDecimal";
        }
        else if (dbFieldType.contains("varchar") || dbFieldType.contains("longtext")
                 || dbFieldType.contains("time") || dbFieldType.contains("enum")
                 || dbFieldType.contains("set") || dbFieldType.contains("text"))
        {
            return "String";
        }
        else if (dbFieldType.contains("binary") || dbFieldType.contains("blob"))
        {
            return "byte[]";
        }
        else
        {
            return "String";
        }
    }

}
