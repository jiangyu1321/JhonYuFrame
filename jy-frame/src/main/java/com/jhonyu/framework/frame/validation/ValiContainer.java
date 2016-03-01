package com.jhonyu.framework.frame.validation;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.jhonyu.framework.frame.enums.ExcpTypeEnum;
import com.jhonyu.framework.frame.exception.JhonyuDaoException;
import com.jhonyu.framework.frame.exception.JhonyuSysException;
import com.jhonyu.framework.frame.util.ExcpResultUtil;
import com.jhonyu.framework.frame.validation.ExcpHelper.Condition;
import com.jhonyu.framework.util.common.CollectionUtil;


/**
 * @Description: 校验容器类
 * @className: ValiContainer
 * @userName: jiangyu
 * @date: 2015年11月12日 下午8:48:50
 */
public class ValiContainer
{
    private ValiContainer()
    {}

    private static class ValiContainerHolder
    {
        private static final ValiContainer INSTANCE = new ValiContainer();
    }

    public static ValiContainer getInstance()
    {
        return ValiContainerHolder.INSTANCE.clear();
    }

    /**
     * 存储校验对象
     */
    private static Set<ExcpHelper> exceptions = Collections.synchronizedSet(new HashSet<ExcpHelper>());

    /**
     * @Description: 注册校验的对象
     * @userName: jiangyu
     * @date: 2015年11月12日 下午9:08:20
     * @param excp
     *            异常校验对象
     * @return 异常容器
     */
    public synchronized ValiContainer registerExcep(ExcpHelper excp)
    {
        exceptions.add(excp);
        return this;
    }
    /**
     * @Description: 注册校验对象
     * @userName: jiangyu
     * @date: 2015年11月26日 上午11:08:53
     * @param obj 校验的内容
     * @param errorCode 校验不通过的异常编码
     * @param condition 校验的条件（校验的标准）
     * @return
     */
    public synchronized ValiContainer registerExcep(Object obj, String errorCode, Condition condition,Object...params)
    {
        ExcpHelper excp = new ExcpHelper(obj, errorCode, condition,params);
        exceptions.add(excp);
        return this;
    }
    
    public synchronized ValiContainer clear()
    {
        exceptions.clear();
        return this;
    }

    /**
     * @Description: 校验异常对象
     * @userName: jiangyu
     * @date: 2015年11月12日 下午9:09:03
     */
    public synchronized void validate(ExcpTypeEnum ...excpType)
    {

        if (exceptions != null && exceptions.size() > 0)
        {
            for (ExcpHelper excp : exceptions)
            {
                boolean flag = false;
                if (Condition.EMPTY.getValue().equals(excp.getCondition().getValue()))
                {
                    if (CollectionUtil.isEmpty(excp.getObj()))
                    {
                        flag = true;
                    }
                }
                else if (Condition.LE_0.getValue().equals(excp.getCondition().getValue()))
                {
                    if ((int)excp.getObj()<=0)
                    {
                        flag = true;
                    }
                }
                else if (Condition.NOT_MATCH.getValue().equals(excp.getCondition().getValue()))
                {
                    if ((boolean)excp.getObj())
                    {
                        flag = true;
                    }
                }
                
                if (flag)
                {
                    validateExcpHandler(excp, excpType);
                }
            }
        }
    }
    /**
     * @Description: 校验的时候异常信息的出来
     * @userName: jiangyu
     * @date: 2015年11月26日 上午11:41:41
     * @param excp
     * @param excpType
     */
    private void validateExcpHandler(ExcpHelper excp, ExcpTypeEnum... excpType)
    {
        if (excpType!=null&&excpType.length>0)
        {
            ExcpTypeEnum type = excpType[0];
            
            String codeCode = type.getCode();
            
            if (ExcpTypeEnum.CONTROLLER.getCode().equals(codeCode))
            {
                /**控制器层校验抛出异常 **/
                throw new JhonyuSysException(excp.getErrorCode(),excp.getParamsInfo());
                
            }else if (ExcpTypeEnum.SERVICE.getCode().equals(codeCode))
            {
                /**控制器层校验抛出异常 **/
                throw new JhonyuSysException(excp.getErrorCode(),excp.getParamsInfo());
                
            }else if (ExcpTypeEnum.DAO.getCode().equals(codeCode))
            {
                /**控制器层校验抛出异常 **/
                throw new JhonyuDaoException(excp.getErrorCode(),excp.getParamsInfo());
                
            }else if (ExcpTypeEnum.COMPONENT.getCode().equals(codeCode))
            {
                /**控制器层校验抛出异常 **/
                throw new JhonyuSysException(excp.getErrorCode(),excp.getParamsInfo());
                
            }else {
                
                throw new JhonyuSysException(excp.getErrorCode(),excp.getParamsInfo());
            }
        }else {
            /**默认没有传入校验类型则为dao层校验抛出异常 **/
            throw new JhonyuDaoException(excp.getErrorCode(),excp.getParamsInfo());
        }
    }

    /**
     * @Description: 抛出dao的异常
     * @userName: jiangyu
     * @date: 2015年11月13日 下午4:30:12
     * @param errorCode
     */
    public void throwDaoExcp(String errorCode)
    {
        throw new JhonyuDaoException(errorCode);
    }
    /**
     * @Description: 抛出dao的异常
     * @userName: jiangyu
     * @date: 2015年11月13日 下午4:30:12
     * @param errorCode
     */
    public void throwSysExcp(String errorCode)
    {
        throw new JhonyuSysException(errorCode);
    }
    
    /**
     * @Description: 为空的判断，为空的话抛出可视化的异常信息
     * @userName: jiangyu
     * @date: 2016年1月19日 下午2:31:21
     * @param obj 校验的对象
     * @param errorCode 对应错误异常的异常编码
     */
    public void validateEmpty(Object obj, String errorCode)
    {
        /** 校验是否存在记录 **/
        if (CollectionUtil.isEmpty(obj))
        {
            /** 任务实例不存在 **/
            ExcpResultUtil.failView(errorCode);
        }
    }
}
