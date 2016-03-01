package com.jhonyu.framework.frame.util;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jhonyu.framework.frame.util.HqlFormatterUtil.Restriction;
import com.jhonyu.framework.frame.vo.PropertyHelper;
import com.jhonyu.framework.util.common.CollectionUtil;


/**
 * @Description: 属性工具类
 * @className: PropertyUtil
 * @userName: jiangyu
 * @date: 2015年11月15日 上午11:32:02
 */
public class PropertyUtil
{
    private static Set<PropertyHelper> PROPERTIES = Collections.synchronizedSet(new HashSet<PropertyHelper>());

    public PropertyUtil addProperty(String propertyName, Object value, Restriction restriction)
    {
        if (CollectionUtil.isEmpty(propertyName) || CollectionUtil.isEmpty(value))
        {
            return this;
        }
        PROPERTIES.add(new PropertyHelper(propertyName, value, restriction));
        return this;
    }

    public PropertyUtil clear()
    {
        PROPERTIES.clear();
        return this;
    }

    public Set<PropertyHelper> getPropertyHelps()
    {
        return PROPERTIES;
    }

    private PropertyUtil()
    {}

    private static class PropertyHold
    {
        private static PropertyUtil INSTANCE = new PropertyUtil();
    }

    public static PropertyUtil getInstance()
    {
        return PropertyHold.INSTANCE.clear();
    }
}
