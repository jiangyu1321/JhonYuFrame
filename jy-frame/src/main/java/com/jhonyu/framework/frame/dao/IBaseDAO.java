package com.jhonyu.framework.frame.dao;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;

import com.jhonyu.framework.frame.vo.JhonyuBaseInfo;
import com.jhonyu.framework.frame.vo.Page;
import com.jhonyu.framework.frame.vo.PropertyHelper;


/**
 * @Description: dao层抽象的接口<br/>
 * @className: IBaseDAO
 * @userName: jiangyu
 * @date: 2015年11月12日 下午4:04:42<br/>
 * @param <T>
 *            DAO操作的对象类型，也就是对应的实体类型
 * @param <PK>
 *            主键类型
 */
public interface IBaseDAO<T, PK extends Serializable>
{
    /**
     * @Description: 新增记录
     * @userName: jiangyu
     * @date: 2015年11月2日 下午4:36:10
     * @param entity
     * @return 返回值为主键
     */
    public T save(final T entity);

    /**
     * @Description: 更新和新增记录
     * @userName: jiangyu
     * @date: 2015年11月2日 下午4:36:27
     * @param entity
     * @return 实体记录
     */
    public T saveOrUpdate(final T entity);
    /**
     * @Description: 更新记录
     * @userName: jiangyu
     * @date: 2015年11月16日 上午11:08:52
     * @param entity
     */
    public void update(final T entity);
    
    /**
     * @Description: 批量新增记录
     * @userName: jiangyu
     * @date: 2015年11月16日 上午10:57:06
     * @param list 实体对象集合
     */
    public void batchSave(final List<T> list);

    /**
     * @Description: 通过主键查询记录
     * @userName: jiangyu
     * @date: 2015年11月2日 下午4:36:39
     * @param id
     *            主键值
     * @return 返回对应的实体
     */
    public T find(final PK id);

    /**
     * @Description: 删除记录
     * @userName: jiangyu
     * @date: 2015年11月2日 下午4:36:56
     * @param entity
     *            传入实体类型参数
     */
    public void delete(final T entity);

    /**
     * @Description: 通过主键删除
     * @userName: jiangyu
     * @date: 2015年11月2日 下午4:37:09
     * @param id
     *            主键值
     */
    public void delete(final PK id);

    /**
     * @Description: 通过主键list查询记录
     * @userName: jiangyu
     * @date: 2015年11月2日 下午4:37:27
     * @param idList
     *            主键集合
     * @return 返回值为满足条件的实体List集合
     */
    public List<T> find(final Collection<PK> idList);

    /**
     * @Description: 查询全部
     * @userName: jiangyu
     * @date: 2015年11月8日 上午10:41:03
     * @return 返回值为所有的实体List集合
     */
    public List<T> findAll();

    /**
     * @Description: 支持排序查询所有记录
     * @userName: jiangyu
     * @date: 2015年11月8日 上午10:40:03
     * @param orderProperty
     *            排序的属性名称
     * @param isAsc
     *            是否是升序 \n true:升序 \n false:降序
     * @return 返回值为所有的记录按照排序方式的List集合
     */
    public List<T> findAllIsAsc(String orderProperty, boolean isAsc);

    /**
     * @Description: 支持排序查询所有记录
     * @userName: jiangyu
     * @date: 2015年11月8日 上午10:40:03
     * @param orderProperty
     *            排序的属性名称
     * @param 排序方式
     *            asc 升序 desc 降序
     * @return 返回值为所有的记录按照排序方式的List集合
     */
    public List<T> findOrderedRecord(String orderProperty, String orderWay);

    /**
     * @Description: 按属性查找，匹配方式为相等<br>
     * @userName: jiangyu
     * @date: 2015年11月8日 下午1:58:23
     * @param propertyName
     *            属性名称
     * @param value
     *            属性对应的值
     * @return 返回值为满足查询条件值的List集合
     */
    public List<T> findByProperty(final String propertyName, final Object value);

    /**
     * @Description: 通过属性，查询唯一的对象，匹配方式为相等
     * @userName: jiangyu
     * @date: 2015年11月8日 下午2:01:03
     * @param propertyName
     *            属性名称
     * @param value
     *            属性值 \n 如果属性名称为别的关联实体，请在传入参数值的时候务必传入实体对象
     * @return 返回值为满足属性值筛选的唯一记录
     */
    public T findUniqueByProperty(final String propertyName, final Object value);
    
    /**
     * @Description: 通过Criterion查询唯一的对象
     * @userName: jiangyu
     * @date: 2015年11月24日 下午10:41:45
     * @param criterions
     * @return
     */
    public T findUniqueByProperties(Criterion... criterions);

    /**
     * @Description: 自己写hql查询语句 灵活处理查询，参数类型为数组
     * @userName: jiangyu
     * @date: 2015年11月8日 下午2:03:49
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的预编译参数对应的值
     * @return 返回值为满足查询条件的List集合,返回值不局限于当前的实体，可以是Object
     */
    public <X> List<X> find(final String hql, final Object... values);

    /**
     * @Description: 自定义hql语句，参数封装在Map中
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:05:46
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的占位的预编译参数对应的值
     * @return 返回值为满足查询条件的List集合,返回值不局限于当前的实体，可以是Object
     */
    public <X> List<X> find(final String hql, final Map<String, ?> values);

    /**
     * @Description: 根据Hql语句查询唯一结果,参数为数组
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:11:16
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的预编译参数对应的值
     * @return 返回值为满足查询条件的唯一对象 ,返回值不局限于当前的实体，可以是Object
     */
    public <X> X findUnique(final String hql, final Object... values);

    /**
     * @Description: 执行HQL语句进行删除和更新操作,参数为数组
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:14:04
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的预编译参数对应的值
     * @return 返回值为DML操纵所影响的记录数
     */
    public int batchExecute(final String hql, final Object... values);

    /**
     * @Description: 执行hql语句进行删除和更新操作，参数为Map
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:15:58
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的预编译参数对应的值
     * @return 返回值为DML操纵所影响的记录数
     */
    public int batchExecute(final String hql, final Map<String, ?> values);

    /**
     * @Description: 根据Hql语句查询唯一结果,参数为Map
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:12:31
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的预编译参数对应的值
     * @return 返回值为满足查询条件的唯一对象 ,返回值不局限于当前的实体，可以是Object
     */
    public <X> X findUnique(final String hql, final Map<String, ?> values);

    /**
     * @Description: 执行hql返回query接口
     * @userName: jiangyu
     * @date: 2015年11月8日 下午2:03:14
     * @param hql
     *            自定义hql
     * @param values
     *            hql中对应的预编译参数对应的值
     * @return 返回值为组装好参数的Query接口
     */
    public Query createQuery(String hql, Object[] values);

    /**
     * @Description: 使用map封装查询的参数
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:09:07
     * @param hql
     *            自定义hql
     * @param values
     * @return 返回值为组装好参数的Query接口
     */
    public Query createQuery(final String hql, final Map<String, ?> values);

    /**
     * @Description: 不需要参数的查询
     * @userName: jiangyu
     * @date: 2015年11月14日 下午6:26:36
     * @param hql
     *            自定义hql
     * @return 返回值为Query接口
     */
    public Query createQuery(final String hql);

    /**
     * @Description: 按Criteria查询对象List列表.
     * @userName: jiangyu
     * @date: 2015年11月8日 下午1:46:33
     * @param criterions
     *            数量可变的Criterion.
     * @return 返回值为满足条件的List结果结合
     */
    public List<T> find(final Criterion... criterions);

    /**
     * @Description: 组装Criteria接口，封装查询条件到Criteria接口中
     * @userName: jiangyu
     * @date: 2015年11月9日 上午10:46:40
     * @param criterions
     *            查询条件的数组
     * @return 封装好查询条件的Criteria接口
     */
    public Criteria createCriteria(final Criterion... criterions);

    // ==================================================================
    // = 提供sql查询接口执行的方式，以满足hql语句不能灵活执行执行 =
    // ==================================================================

    /**
     * @Description: 根据Sql执行特定的查询，参数封装在数组中
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:19:24
     * @param sql
     *            自定义SQL
     * @param values
     *            sql中的预编译参数对应的值
     * @return 返回值为组装好查询条件参数的SQLQuery接口
     */
    public SQLQuery createSQLQuery(final String sql, final Object... values);

    /**
     * @Description: 根据sql执行特定的查询，参数封装在map中
     * @userName: jiangyu
     * @date: 2015年11月8日 下午4:21:00
     * @param sql
     *            自定义SQL
     * @param map
     *            sql中的预编译参数对应的值
     * @return 返回值为组装好查询条件参数的SQLQuery接口
     */
    public SQLQuery createSQLQuery(final String sql, final Map<String, ?> values);

    /**
     * @Description: 根据sql执行特定的查询
     * @userName: jiangyu
     * @date: 2015年11月14日 下午6:28:15
     * @param sql
     *            自定义SQL
     * @return 返回值为SQLQuery接口
     */
    public SQLQuery createSQLQuery(final String sql);
    
    /**
     * @Description: sql分页查询数据
     * @userName: jiangyu
     * @date: 2015年11月25日 下午2:25:30
     * @param info 分页参数信息 ，子类中一般都需要继承JifennBaseInfo类
     * @param sql 自定义sql语句，可以带预编译的参数
     * @param params 对应sql语句预编译参数的值
     * @param formatter 回调接口，对查询出来的分页结果进行转换成X类型的数据模型，如果不需要转换可以传入null,需要转换的就需要实现接口中的format方法
     * 接口参考：{@link com.jifenn.framework.frame.dao.PageFormatCallable}
     * @return 封装好的分页结果  {@link com.jifenn.framework.frame.vo.Page}
     */
    public <X> Page<X> pageSqlQuery(JhonyuBaseInfo info,String sql,Map<String, Object> params,PageFormatCallable<X> formatter);
    
    /**
     * @Description: 多个属性查询
     * @userName: jiangyu
     * @date: 2015年12月17日 下午3:49:49
     * @param params 条件参数
     * @param isAsc 是否是升序
     * @return
     */
    public List<T> findByProperties(Map<String,Object> params);
    
    /**
     * @Description: 多个属性查询[目前只支持等于的条件]
     * @userName: jiangyu
     * @date: 2015年12月17日 下午3:49:49
     * @param params 条件参数
     * @param isAsc 是否是升序
     * @return
     */
    public T findUniqueRecordByProperties(Map<String,Object> params);
    
    /**
     * @Description: 多个属性查询+是否排序   目前只支持当个字段的排序
     * @userName: jiangyu
     * @date: 2015年12月17日 下午3:49:49
     * @param orderField 排序的字段
     * @param isAsc 是否是升序
     * @param params 条件参数
     * @return
     */
    public List<T> findIsAscRecordByProperties(String orderField,boolean isAsc,Map<String,Object> params);
    
    /**
     * @Description: 删除记录【根据单一属性匹配：条件为相等】
     * @userName: jiangyu
     * @date: 2016年2月1日 下午9:12:29
     * @param propertyName 属性名称
     * @param propertyValue 属性对应的值
     * @return 返回所受影响的结果的记录数
     */
    public int deleteRecord(String propertyName,Object propertyValue);
    
    /**
     * @Description: 删除记录【多个属性相等的匹配方式】
     * @userName: jiangyu
     * @date: 2016年2月1日 下午9:15:49
     * @param values 对应的属性集合
     * @return 返回所受影响的记录
     */
    public int deleteRecordByProperties(Map<String, Object> values);
    
    /**
     * @Description: 不带属性约束的删除
     * @userName: jiangyu
     * @date: 2016年2月1日 下午9:35:39
     * @return 返回所受影响的记录
     */
    public int deleteRecordWithOutProperty();
    
    /**
     * @Description: 删除记录[包含复合的约束条件]
     * @userName: jiangyu
     * @date: 2016年2月1日 下午9:37:03
     * @param propertyHelpers 属性约束条件{@link com.jifenn.framework.frame.vo.PropertyHelper }
     * @return 返回所受影响的记录
     */
    public int deleteRecordWithCondition(Set<PropertyHelper> propertyHelpers);
    
}
