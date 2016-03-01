package com.jhonyu.framework.frame.exception;

/**
 * @Description: TODO
 * @className: JhonyuException
 * @userName: jiangyu
 * @date: 2016年2月18日 下午1:01:17
 */
public class JhonyuException extends RuntimeException
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4763294338542809396L;

    private String errorCode = "999999";

    private String desc;

    /** added by jiangyu begin **/
    
    private Object[] replaceVal;

    /** added by jiangyu end **/
    
    public JhonyuException()
    {

    }

    public JhonyuException(String errorCode)
    {
        this.errorCode = errorCode;
    }

    /** added by jiangyu begin **/
    
    public JhonyuException(String errorCode, Object... replaceVal)
    {
        this.errorCode = errorCode;
        this.replaceVal = replaceVal;
    }
    /** added by jiangyu end **/
    
    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public Object[] getReplaceVal()
    {
        return replaceVal;
    }
}
