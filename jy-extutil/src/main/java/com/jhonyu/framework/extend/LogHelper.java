package com.jhonyu.framework.extend;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.jhonyu.framework.util.common.CollectionUtil;

/**
 * @Description: 日志信息的简单格式化
 * @className: LogMsgFormat
 * @userName: jiangyu
 * @date: 2016年1月4日 下午6:13:46
 */
public class LogHelper
{
    private static final String MSG_PREFIX = "【EXPORT_MSG】";
    
    private static final String EXPORT_PREFIX = "Export Excel ";

    public static String format(String msg)
    {
        return MSG_PREFIX + EXPORT_PREFIX + msg;
    }
    
    public static void errLog(Logger loger,String msg){
        loger.error(format(msg));
    }
    
    public static void errLog(Logger loger,Exception e,String msg){
        if (Level.ERROR.equals(loger.getLevel()))
        {
            loger.error(format(msg+" ,the reason is : "+e.getMessage()));
        }else {
            if (!CollectionUtil.isEmpty(e))
            {
                loger.info(format(msg));
            }else {
                loger.info(format(msg+" ,the reason is : "+e.getMessage()));
            }
        }
    }
}
