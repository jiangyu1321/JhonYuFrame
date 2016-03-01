package com.jhonyu.framework.util.common;

import org.apache.log4j.Logger;

public class ClassUtil
{
    public static final Logger LOG = Logger.getLogger(ClassUtil.class);
    /**
     * @Description: 获取类的实例的class
     * @userName: jiangyu
     * @date: 2015年11月23日 下午6:06:13
     * @return
     */
    public static Class<?> getClazzByName(String fullName)
    {
        try
        {
            return Class.forName(fullName);
        }
        catch (ClassNotFoundException e)
        {
            LOG.error("GeneralTools getClazzByName ClassNotFoundException:" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
}
