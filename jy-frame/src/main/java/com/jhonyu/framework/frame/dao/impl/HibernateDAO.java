package com.jhonyu.framework.frame.dao.impl;


import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;

import com.jhonyu.framework.frame.constants.ErrorCodeConstant;
import com.jhonyu.framework.frame.dao.PageFormatCallable;
import com.jhonyu.framework.frame.util.ReflectionUtil;
import com.jhonyu.framework.frame.validation.ExcpHelper;
import com.jhonyu.framework.frame.validation.ExcpHelper.Condition;
import com.jhonyu.framework.frame.validation.ValiContainer;
import com.jhonyu.framework.frame.vo.JhonyuBaseInfo;
import com.jhonyu.framework.frame.vo.Page;

/**
 * @Description: BaseHibernateDAO更上一层的封装，主要是为了适应分页的需求
 * @className: HibernateDAO
 * @userName: jiangyu
 * @date:  2015年11月9日 上午10:36:22
 * @param <T>
 *            对应的实体
 * @param <PK>
 *            对应实体的主键
 */
@SuppressWarnings("unchecked")
public class HibernateDAO<T, PK extends Serializable> extends BaseHibernateDAO<T, PK>
{

    public HibernateDAO()
    {
        super();
    }

    /**]
     * @Description: 按分页的方式查询所有的记录
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:28:50
     * @param page
     *            分页辅助类
     * @return
     */
    public Page<T> getAll(final Page<T> page)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(page, ErrorCodeConstant.PAGE_OBJ_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        return this.findByPage(page);
    }

    /**
     * @Description: 通过query接口实现分页查询
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:28:50
     * @param page
     *            分页辅助类
     * @param hql
     *            自定义hql语句
     * @param values
     *            hql语句中的参数，参数为数组形式
     * @return
     */
    protected Page<T> findPage(final Page<T> page, final String hql, final Object... values)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(page, ErrorCodeConstant.PAGE_OBJ_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        
        Query query = this.createQuery(hql, values);
        if (page.isAutoCount())
        {
            long totalCount = this.countHqlResult(hql, values);
            page.setTotalCount(totalCount);
        }
        query = this.setPageParameter2Query(query, page);
        List<T> result = query.list();
        page.setList(result);
        return page;

    }
    
    /**
     * @Description: 分页查询记录
     * @userName: jiangyu
     * @date: 2016年1月19日 上午10:02:50
     * @param page
     * @param hql
     * @param values
     * @return
     */
    protected Page<T> findPage(final Page<T> page, final String hql, final Map<String, ?> values) {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(page, ErrorCodeConstant.PAGE_OBJ_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();

        Query q = this.createQuery(hql, values);

        if (page.isAutoCount()) {
            long totalCount = this.countHqlResult(hql, values);
            page.setTotalCount(totalCount);
        }

        this.setPageParameter2Query(q, page);

        List<T> result = q.list();
        page.setList(result);
        return page;
    }
    
    
    /**
     * @Description: 查询记录总数,参数为数组
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:31:09
     * @param hql
     *            自定义hql语句
     * @param values
     * @return
     */
    public long countHqlResult(String hql, final Object... values)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(hql, ErrorCodeConstant.HQL_OR_SQL_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .registerExcep(new ExcpHelper(values, ErrorCodeConstant.PRE_PARAMS_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        String countHql = this.prepareCountHql(hql);
        Long count = this.findUnique(countHql, values);
        return count;
    }

    /**
     * @Description: 查询记录总数,参数为Map
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:31:36
     * @param hql
     *            自定义的hql语句
     * @param values
     *            hql语句中对应的参数
     * @return
     */
    public long countHqlResult(String hql, final Map<String, ?> values)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(hql, ErrorCodeConstant.HQL_OR_SQL_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .registerExcep(new ExcpHelper(values, ErrorCodeConstant.PRE_PARAMS_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        String countHql = this.prepareCountHql(hql);
        Long count = this.findUnique(countHql, values);
        return count;
    }

    /**
     * @Description: 设置分页参数到query接口中
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:32:32
     * @param query
     *            query接口
     * @param page
     *            分页辅助类
     * @return
     */
    private Query setPageParameter2Query(Query query, Page<T> page)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(query, ErrorCodeConstant.QUERY_INTERFACE_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .registerExcep(new ExcpHelper(page, ErrorCodeConstant.PAGE_OBJ_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        if (page.getFirst() > 0 && page.getPageSize() != -1)
        {
            query.setFirstResult(page.getFirst() - 1);
            query.setMaxResults(page.getPageSize());
        }
        return query;
    }

    /**
     * @Description: 准备查询总记录数的hql语句[注意：此方法实现得不是很好，后期进一步完善]
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:33:13
     * @param hql
     *            自定义hql语句
     * @return
     */
    private String prepareCountHql(String hql)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(hql, ErrorCodeConstant.HQL_OR_SQL_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        
        String fromHql = hql;
        String entityName = StringUtils.substringAfter(fromHql, "from");
        if ("".equals(entityName) || null == entityName)
        {
            entityName = StringUtils.substringAfter(fromHql, "FROM");
        }
        fromHql = "from " + entityName;
        fromHql = StringUtils.substringBefore(fromHql, "order by");
        String countHqlStr = "select count(*) " + fromHql;
        return countHqlStr;
    }

    /**
     * @Description: 分页查询数据，使用criterion方式
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:33:48
     * @param page
     *            分页辅助类
     * @param criterions
     *            查询条件
     * @return
     */
    private Page<T> findByPage(Page<T> page, final Criterion... criterions)
    {
        ValiContainer.getInstance().clear()
        .registerExcep(new ExcpHelper(page, ErrorCodeConstant.PAGE_OBJ_EMPTY_OR_NULL_CODE,Condition.EMPTY))
        .validate();
        
        Criteria criteria = this.createCriteria(criterions);
        if (page.isAutoCount())
        {
            long totalCount = this.countCriteriaResult(criteria);
            page.setTotalCount(totalCount);
        }
        criteria = this.setPageParameter2Criteria(criteria, page);
        List<T> result = criteria.list();
        page.setList(result);
        return page;
    }

    /**
     * @Description: 设置分页参数到Criteria对象，辅助函数
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:34:33
     * @param criteria
     * @param page
     * @return
     */
    private Criteria setPageParameter2Criteria(Criteria criteria, Page<T> page)
    {
        ValiContainer.getInstance().clear()
            .registerExcep(new ExcpHelper(criteria, ErrorCodeConstant.CRITERIA_INTERFACE_EMPTY_OR_NULL_CODE,Condition.EMPTY))
            .registerExcep(new ExcpHelper(page.getPageSize(),ErrorCodeConstant.PAGE_NO_LESS_THAN_ZERO_CODE ,Condition.LE_0))
            .validate();
        
        criteria.setFirstResult(page.getFirst() - 1);
        criteria.setMaxResults(page.getPageSize());

        if (page.isOrderBySetted())
        {
            String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
            String[] orderArray = StringUtils.split(page.getOrder(), ',');
            
            ValiContainer.getInstance()
            .registerExcep(new ExcpHelper(orderByArray.length == orderArray.length, ErrorCodeConstant.PRE_PARAM_COUNT_NOT_MATCH_CODE,Condition.NOT_MATCH))
            .validate();
            
            for (int i = 0; i < orderArray.length; i++ )
            {
                if (Page.ASC.equals(orderArray[i]))
                {
                    criteria.addOrder(Order.asc(orderByArray[i]));
                }
                else
                {
                    criteria.addOrder(Order.desc(orderByArray[i]));
                }
            }
        }
        return criteria;
    }

    /**
     * @Description: 查询满足条件的记录总数
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:34:51
     * @param criteria
     * @return
     */
    @SuppressWarnings("rawtypes")
    protected long countCriteriaResult(final Criteria criteria)
    {
        CriteriaImpl impl = (CriteriaImpl)criteria;
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();
        List<CriteriaImpl.OrderEntry> orderEntries = null;
        
        orderEntries = (List)ReflectionUtil.getFieldValue(impl, "orderEntries");
        ReflectionUtil.setFieldValue(impl, "orderEntries", new ArrayList());
        
        // 执行count语句
        Long totalCountObject = (Long)criteria.setProjection(Projections.rowCount()).uniqueResult();
        long totalCount = (totalCountObject != null) ? totalCountObject : 0;
        criteria.setProjection(projection);
        if (projection == null)
        {
            criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null)
        {
            criteria.setResultTransformer(transformer);
        }
        ReflectionUtil.setFieldValue(impl, "orderEntries", orderEntries);
        return totalCount;
    }
    
    
    @Override
    public <X> Page<X> pageSqlQuery(JhonyuBaseInfo info,String sql,Map<String, Object> params,PageFormatCallable<X> formatter)
    {
        /*
        Page page = new Page();
        Query query = super.createSQLQuery(sql, params);
        query.setFirstResult((info.getPageNo()-1)*info.getPageSize()).setMaxResults(info.getPageSize());

        String countField = countFiled(sql, " as ", " AS ");
        if (CollectionUtil.isEmpty(countField))
        {
            countField = "*";
        }
        
        String conditionSql = StringUtils.substringAfter(sql, "from");
        if ("".equals(conditionSql) || null == conditionSql)
        {
            conditionSql = StringUtils.substringAfter(sql, "FROM");
        }
        *//** don't need order **//*
        conditionSql = StringUtils.substringBefore(StringUtils.substringBefore(conditionSql, "order by"), "ORDER BY");
        *//** don't need group **//*
        conditionSql = StringUtils.substringBefore(StringUtils.substringBefore(conditionSql, "group by"), "GROUP BY");
        
        String countHqlStr = "SELECT COUNT("+countField+") FROM " + conditionSql;
        
        Query countQuery = createSQLQuery(countHqlStr, params);
        BigInteger recordCount = (BigInteger)countQuery.uniqueResult();
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Object> list = query.list();
        Long totalCount = recordCount==null?0L:recordCount.longValue();
        page.setTotalCount(totalCount);
        page.setPageNo(info.getPageNo());
        page.setPageSize(info.getPageSize());
        if (formatter!=null)
        {
            page.setList(formatter.format(list));
        }else {
            page.setList(list);
        }
        return page;
        */
        
        return pageComplexSqlQuery(info,sql,params,formatter);
    }
    
    @SuppressWarnings("unused")
    private static String countFiled(String sql, String... str)
    {
        String backSql = sql;
        backSql = backSql.toLowerCase().trim();
        if (sql.indexOf(",") > -1)
        {
            int tx = sql.indexOf(",");
            sql = sql.substring(6, tx);
        }
        else
        {
            int fx = backSql.indexOf(" from ");
            sql = sql.substring(6, fx);
        }
        if (str != null && str.length > 0)
        {
            for (String replaceVal : str)
            {
                sql = StringUtils.substringBefore(sql, replaceVal);
            }
        }
        return sql.trim();
    }

    @SuppressWarnings("rawtypes")
    private <X> Page<X> pageComplexSqlQuery(JhonyuBaseInfo info, String sql,
                                           Map<String, Object> params,
                                           PageFormatCallable<X> formatter)
    {
        Page page = new Page();
        Query query = super.createSQLQuery(sql, params);
        query.setFirstResult((info.getPageNo()-1)*info.getPageSize()).setMaxResults(info.getPageSize());

        String countHqlStr = "SELECT COUNT(1) FROM (" + sql+") roundtable";
        
        Query countQuery = createSQLQuery(countHqlStr, params);
        BigInteger recordCount = (BigInteger)countQuery.uniqueResult();
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Object> list = query.list();
        Long totalCount = recordCount==null?0L:recordCount.longValue();
        page.setTotalCount(totalCount);
        page.setPageNo(info.getPageNo());
        page.setPageSize(info.getPageSize());
        if (formatter!=null)
        {
            page.setList(formatter.format(list));
        }else {
            page.setList(list);
        }
        /** 查询DB的时间  **/
        page.setSysTime(getSysTime().getTime());
        
        return page;
    }
    
    /**
     * @Description: 查询DB服务器的时间
     * @userName: jiangyu
     * @date: 2016年2月1日 下午2:08:27
     * @return
     */
    protected Date getSysTime() {
        String sql = "select now()";
        return (Date) currentSession().createSQLQuery(sql).uniqueResult();
    }
}
