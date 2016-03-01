package com.jhonyu.framework.frame.util;

import com.jhonyu.framework.frame.exception.JhonyuDVException;
import com.jhonyu.framework.frame.exception.JhonyuSysException;
import com.jhonyu.framework.frame.exception.JhonyuViewException;


/**
 * @Description: 校验结果的处理
 * @className: ValidateResultProcessor
 * @userName: jiangyu
 * @date: 2016年1月15日 上午9:49:23
 *  eg:任务处于{0}状态，不允许参与{1}任务...{n} 
 *  
 */
public class ExcpResultUtil
{
    /**
     * @Description: 校验失败处理【错误信息前端可见】
     * @userName: jiangyu
     * @date: 2016年1月15日 上午9:49:23
     * @param errorCode 错误编码
     * @param params 附加信息    分别对应的是替换符中的值
     *  eg:任务处于{0}状态，不允许参与{1}任务...{n} 
     */
    public static void failView(String errorCode,Object... params){
        /** eg:任务处于{0}状态，不允许参与此任务 **/
        throw new JhonyuViewException(errorCode,params);
    }
    
    /**
     * @Description: 校验失败处理【错误信息前端不可见】
     * @userName: jiangyu
     * @date: 2016年1月15日 上午9:50:23
     * @param errorCode  分别对应的是替换符中的值
     * @param params 附加信息    分别对应的是替换符中的值
     *  eg:任务处于{0}状态，不允许参与{1}任务...{n} 
     */
    public static void failSys(String errorCode,Object... params){
        /** eg:任务处于{0}状态，不允许参与{1}任务 **/
        throw new JhonyuSysException(errorCode,params);
    }
    
    /**
     * @Description: 数据校验失败的异常处理
     * @userName: jiangyu
     * @date: 2016年2月18日 下午3:04:01
     * @param errorCode
     * @param params
     */
    public static void validateExcpFail(String errorCode,Object... params){
        throw new JhonyuDVException(errorCode, params);
    }
}

