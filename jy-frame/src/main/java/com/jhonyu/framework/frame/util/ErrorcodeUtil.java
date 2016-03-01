package com.jhonyu.framework.frame.util;

import java.util.Properties;

/**
 * @Description: 错误码工具类 必须在classpath下配置/config/errorcode.properties文件
 * @className: ErrorcodeUtil
 * @userName: jiangyu
 * @date: 2016年2月16日 上午9:33:27
 */
public final class ErrorcodeUtil
{
    private static Properties errProps;

    private static final String ERROR_DESC_NOT_FOUND = "未找到异常描述";

    private ErrorcodeUtil()
    {}

    public static final String SYS_ERROR = "999999";

    public static final String SUCCESS = "0";

    public void setErrProps(Properties errProps)
    {
        ErrorcodeUtil.errProps = errProps;
    }

    public static String getErrorDesc(String errorCode)
    {
        return errProps.getProperty(errorCode, ERROR_DESC_NOT_FOUND);
    }

    /**
     * @Description: 扩展错误信息的组织方式
     * @userName: jiangyu
     * @date: 2015年11月25日 下午4:40:19
     * @param errorCode
     *            错误编码
     * @param replaceVal
     *            进行替换的实际值
     * @return
     */
    public static String getErrorDesc(String errorCode, Object... replaceVal)
    {
        String errorMsg = errProps.getProperty(errorCode, ERROR_DESC_NOT_FOUND);
        if (replaceVal != null && replaceVal.length > 0)
        {
            for (int i = 0; i < replaceVal.length; i++ )
            {
                errorMsg = errorMsg.replace("{" + i + "}",
                    replaceVal[i] == null ? "" : replaceVal[i].toString());
            }
        }
        return errorMsg;
    }
}
