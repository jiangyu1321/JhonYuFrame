package com.jhonyu.framework.frame.vo;


import com.jhonyu.framework.frame.util.HqlFormatterUtil.Restriction;


/**
 * @Description: 属性辅助类
 * @className: PropertyHelper
 * @userName: jiangyu
 * @date: 2015年11月15日 上午11:26:01
 */
public class PropertyHelper
{
    /** 属性名称 **/
    private String propertyName;

    /** 属性值 **/
    private Object value;

    /** 属性对应的约束条件 **/
    private Restriction res;
    
    /**
     * 通过构造参数注入
     * @param propertyName 属性名称
     * @param value 属性值
     * @param res 属性的约束条件
     */
    public PropertyHelper(String propertyName, Object value, Restriction res)
    {
        this.propertyName = propertyName;
        this.value = value;
        this.res = res;
    }

    public String getPropertyName()
    {
        return propertyName;
    }

    public Object getValue()
    {
        return value;
    }

    public Restriction getRes()
    {
        return res;
    }
}
