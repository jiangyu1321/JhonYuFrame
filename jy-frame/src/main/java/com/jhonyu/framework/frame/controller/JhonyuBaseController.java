package com.jhonyu.framework.frame.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jhonyu.framework.frame.constants.ErrorCodeConstant;
import com.jhonyu.framework.frame.dto.JhonyuResponse;
import com.jhonyu.framework.frame.exception.JhonyuSysException;
import com.jhonyu.framework.frame.exception.JhonyuViewException;
import com.jhonyu.framework.frame.util.ErrorcodeUtil;
import com.jhonyu.framework.frame.util.ResponseUtil;

/**
 * @Description: 基础控制器
 * 提供顶层的异常处理能力，封装了异常情况下的返回值
 * @className: JhonyuBaseController
 * @userName: jiangyu
 * @date: 2016年2月16日 上午8:21:50
 */
@Controller
public abstract class JhonyuBaseController
{

    private static final Logger LOG = Logger.getLogger(JhonyuBaseController.class);

    /**
     * 
     * Description: <br>
     * 对所有经过Controller的异常进行了统一处理
     * 如果抛出的是JifennViewException，向前端返回错误码和错误信息
     * 如果抛出的是JifennSysException，向前端返回系统异常，具体异常信息不可见
     * 
     * @param request HTTP请求
     * @param response HTTP响应
     * @param ex 捕捉到的异常
     * @return JhonyuResponse 通用返回值
     * @see JhonyuResponse
     * 
     * @modifiedby jiangyu 
     */
    @ExceptionHandler
    @ResponseBody
    public JhonyuResponse resolveException(HttpServletRequest request,
                                           HttpServletResponse response, Exception ex)
    {

        JhonyuResponse responseValue = null;
        if (null != ex && ex instanceof JhonyuViewException)
        {
            JhonyuViewException viewEx = (JhonyuViewException)ex;
            String errCode = viewEx.getErrorCode();
            Object[] replaceVal = viewEx.getReplaceVal();
            LOG.error("errorCode:" + errCode + " errorMsg:" + ErrorcodeUtil.getErrorDesc(errCode,replaceVal));
            responseValue = ResponseUtil.error(errCode,replaceVal);
        }
        else if (null != ex && ex instanceof JhonyuSysException)
        {
            JhonyuSysException sysex = (JhonyuSysException)ex;
            String errCode = sysex.getErrorCode();
            Object[] replaceVal = sysex.getReplaceVal();
            LOG.error("errorCode:" + errCode + " errorMsg:" + ErrorcodeUtil.getErrorDesc(errCode,replaceVal));
            responseValue = ResponseUtil.error(ErrorcodeUtil.SYS_ERROR);
        }
        else
        {
            if (ex != null)
            {   /** get请求方式的时候，如果参数少了要提示异常信息  added by jiangyu **/
                if (ex instanceof MissingServletRequestParameterException)
                {
                    /** eg:Required String parameter 'ip' is not present **/
                    MissingServletRequestParameterException excp = (MissingServletRequestParameterException)ex;
                    String name = excp.getParameterName();
                    String type = excp.getParameterType();
                    Object[] replaceValue = new Object[2];
                    replaceValue[0] = name;
                    replaceValue[1] = type;
                    responseValue = ResponseUtil.error(ErrorCodeConstant.METHOD_GET_WAY_PARAMETER_EMPTY,replaceValue);
                }else {
                    responseValue = ResponseUtil.error(ErrorcodeUtil.SYS_ERROR);
                }
            }
        }
        LOG.error(ex.getStackTrace(), ex);
        return responseValue;
    }
}
