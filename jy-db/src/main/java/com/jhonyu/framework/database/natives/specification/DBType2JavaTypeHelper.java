package com.jhonyu.framework.database.natives.specification;

/**
 * @Description: 数据库字段类型转换帮助类
 * @className: DBTypeHelper
 * @userName: jiangyu
 * @date: 2016年2月16日 上午11:30:24
 */
public interface DBType2JavaTypeHelper
{
    /**
     * @Description: 数据库字段类型到Java字段类型的转换
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:32:09
     * @param dbFieldType 数据库字段类型
     * @return Java字段类型
     */
    public String transfer(String dbFieldType);
}
