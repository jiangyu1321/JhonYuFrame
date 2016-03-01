package com.jhonyu.framework.frame.vo;


import java.util.Map;


/**
 * @Description: 查询条件的帮助类
 * @className: QueryInfoHelper
 * @userName: jiangyu
 * @date: 2016年2月1日 下午9:50:20
 */
public class QueryInfoHelper
{
    /**
     * sql或者hql
     */
    private String hsql = "";
    /**
     * 对应的属性的查询参数
     */
    private Map<String, Object> params;

    public QueryInfoHelper()
    {}

    public QueryInfoHelper(String hsql, Map<String, Object> params)
    {
        this.hsql = hsql;
        this.params = params;
    }

    public String getHsql()
    {
        return hsql;
    }

    public void setHsql(String hsql)
    {
        this.hsql = hsql;
    }

    public Map<String, Object> getParams()
    {
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }

}
