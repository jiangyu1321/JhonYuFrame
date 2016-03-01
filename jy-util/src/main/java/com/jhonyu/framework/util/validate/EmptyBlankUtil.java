package com.jhonyu.framework.util.validate;

/**
 * @Description: 为空判断
 * @className: EmptyBlankUtil
 * @userName: jiangyu
 * @date: 2016年2月15日 下午8:59:52
 */
public class EmptyBlankUtil
{
    public static String replaceNullStr(Object value) {
        return null == value ? "" : value.toString().trim();
    }
}
