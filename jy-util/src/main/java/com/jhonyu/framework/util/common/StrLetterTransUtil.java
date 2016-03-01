package com.jhonyu.framework.util.common;

/**
 * @Description: 字符串字母转换工具类
 * @className: StrLetterTransUtil
 * @userName: jiangyu
 * @date: 2016年2月16日 下午12:02:45
 */
public class StrLetterTransUtil
{
    /**
     * @Description: 初始化首字母
     * @userName: jiangyu
     * @date: 2015年11月23日 下午6:13:12
     * @param src
     *            目的的字符串
     * @return 转换后的字符串
     */
    public static String initCap(String src)
    {
        StringBuffer dest = new StringBuffer("");
        if (!CollectionUtil.isEmpty(src))
        {
            dest.append(src.substring(0, 1).toUpperCase()).append(src.substring(1).toLowerCase());
        }
        return dest.toString();
    }
    
    
    /**
     * @Description: 数据库带分隔线的字段名称转换成实体的字段名
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:42:37
     * @param str 要转换的字段名称
     * @param isTableName
     * @return
     */
    public static String dbField2BeanField(String fieldName,boolean isTableName){
        while(fieldName.contains("_")){
            int i = fieldName.indexOf("_");
            if(i+1<fieldName.length()){
                char c = fieldName.charAt(i+1);
                String temp = (c+"").toUpperCase();
                fieldName = fieldName.replace("_"+c, temp);
            }
        }
        /** 如果是表名转换为实体名的话就需要将首字母大写 **/
        if(isTableName){
            fieldName = fieldName.substring(0,1).toUpperCase().concat(fieldName.substring(1));
        }
        return fieldName;
    }
}
