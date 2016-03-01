package com.jhonyu.framework.frame.util;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jhonyu.framework.frame.constants.ErrorCodeConstant;
import com.jhonyu.framework.frame.enums.ExcpTypeEnum;
import com.jhonyu.framework.frame.validation.ExcpHelper;
import com.jhonyu.framework.frame.validation.ExcpHelper.Condition;
import com.jhonyu.framework.frame.validation.ValiContainer;


/**
 * @Description: hql组装工具类
 * @className: HqlFormatter
 * @userName: jiangyu
 * @date: 2015年11月15日 上午10:25:12
 */
public class HqlFormatterUtil
{
    private static Map<String, Restriction> HQL_PARAM_MAP = new ConcurrentHashMap<String, Restriction>();

    private HqlFormatterUtil()
    {}

    public HqlFormatterUtil clear()
    {
        HQL_PARAM_MAP.clear();
        return this;
    }

    private static class FormatterHolder
    {
        private static HqlFormatterUtil INSTANCE = new HqlFormatterUtil();
    }

    public static HqlFormatterUtil getInstance()
    {
        return FormatterHolder.INSTANCE.clear();
    }

    /** 运算符 **/
    public static enum Restriction {
        EQ("=", Integer.valueOf(1)), 
        LE("<=", Integer.valueOf(2)), 
        GE(">=", Integer.valueOf(3)), 
        NE("<>", Integer.valueOf(4)), 
        L("<", Integer.valueOf(5)), 
        G(">", Integer.valueOf(6)), 
        LIKE("like", Integer.valueOf(7)), 
        EQ_OR("OR", Integer.valueOf(8));

        private String operator;

        private Integer value;

        private Restriction(String operator, Integer value)
        {
            this.operator = operator;
            this.value = value;
        }

        public String getOperator()
        {
            return operator;
        }

        public Integer getValue()
        {
            return value;
        }
    }

    /**
     * @Description: 添加属性条件
     * @userName: jiangyu
     * @date: 2015年11月15日 上午10:57:17
     * @param propertyName
     *            属性名称
     * @param obj
     *            属性值
     * @param restriction
     *            约束条件
     */
    public HqlFormatterUtil addRestrict(String propertyName, Restriction restriction)
    {
        /** 为空性的校验 **/
        ValiContainer.getInstance().clear().registerExcep(
            new ExcpHelper(propertyName, ErrorCodeConstant.PROPERTYNAME_EMPTY_OR_NULL_CODE,
                Condition.EMPTY)).registerExcep(
            new ExcpHelper(restriction, ErrorCodeConstant.QUERY_RESTRICTION_EMPTY_OR_NULL_CODE,
                Condition.EMPTY)).validate(ExcpTypeEnum.SERVICE);
        HQL_PARAM_MAP.put(propertyName, restriction);
        return this;
    }

    /**
     * @Description: 格式化hql语句
     * @userName: jiangyu
     * @date: 2015年11月15日 上午10:56:58
     * @return 组织好的hql语句
     */
    public String format()
    {
        String hqlStr = "";
        for (Map.Entry<String, Restriction> entry : HQL_PARAM_MAP.entrySet())
        {
            String property = entry.getKey();
            String res = entry.getValue().getOperator();
            if (Restriction.LIKE.getOperator().equals(res))
            {
                hqlStr += " and " + property + " " + res + ":" + property;
            }
            else
            {
                hqlStr += " and " + property + res + ":" + property;
            }
        }
        return hqlStr;
    }
}
