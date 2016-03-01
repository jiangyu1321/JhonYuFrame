/*
 * 文件名：ResponseUtil.java 版权：Copyright by www.huawei.com 描述： 修改人：JTP 修改时间：2015年11月6日 跟踪单号： 修改单号：
 * 修改内容：
 */

package com.jhonyu.framework.frame.util;

import com.jhonyu.framework.frame.dto.JhonyuResponse;




/**
 * 返回值工具类
 * 
 * @author JTP
 * @version 2015年11月6日
 * @see ResponseUtil
 * @since
 */
public final class ResponseUtil
{
    /**
     * @Description: modified by jiangyu
     * @date: 2015年11月25日 下午4:38:39
     * @param errorCode
     *            错误异常编码
     * @param replaceVal
     *            错误信息中的占位符替换的值
     * @return
     */
    public static JhonyuResponse error(String errorCode, Object... replaceVal)
    {
        JhonyuResponse response = new JhonyuResponse();
        response.setRetCode(errorCode);
        response.setRetMsg(ErrorcodeUtil.getErrorDesc(errorCode, replaceVal).replaceAll("\\d", "*"));

        return response;
    }

    /**
     * @Description: modified by jiangyu
     * @date: 2015年11月25日 下午4:38:31
     * @param errorCode
     *            错误异常编码
     * @param o
     *            存放的对象
     * @param replaceVal
     *            错误信息中的占位符替换的值
     * @return
     */
    public static JhonyuResponse error(String errorCode, Object o, Object... replaceVal)
    {
        JhonyuResponse response = new JhonyuResponse();
        response.setRetCode(errorCode);
        response.setRetMsg(ErrorcodeUtil.getErrorDesc(errorCode,replaceVal).replaceAll("\\d", "*"));
        response.setRetObj(o);
        return response;
    }

    public static JhonyuResponse ok(Object o)
    {
        JhonyuResponse response = new JhonyuResponse();
        response.setRetObj(o);

        return response;
    }

    public static JhonyuResponse ok(String msg, Object o)
    {
        JhonyuResponse response = new JhonyuResponse();
        response.setRetMsg(msg);
        response.setRetObj(o);

        return response;
    }
}
