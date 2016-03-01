package com.jhonyu.framework.frame.dto;


/**
 * @Description: 后端统一返回值
 * @className: JhonyuResponse
 * @userName: jiangyu
 * @date: 2016年2月16日 上午8:21:16
 */
public class JhonyuResponse
{
    public JhonyuResponse()
    {}

    public JhonyuResponse(String retCode, String retMsg)
    {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    private String retCode = "0";

    private String retMsg = "";

    private Object retObj;

    public String getRetCode()
    {
        return retCode;
    }

    public void setRetCode(String retCode)
    {
        this.retCode = retCode;
    }

    public String getRetMsg()
    {
        return retMsg;
    }

    public void setRetMsg(String retMsg)
    {
        this.retMsg = retMsg;
    }

    public Object getRetObj()
    {
        return retObj;
    }

    public void setRetObj(Object retObj)
    {
        this.retObj = retObj;
    }
}
