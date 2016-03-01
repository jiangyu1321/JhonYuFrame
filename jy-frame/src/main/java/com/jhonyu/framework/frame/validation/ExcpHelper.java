package com.jhonyu.framework.frame.validation;

/**
 * @Description: 异常抛出的辅助类
 * @className: ExcpHelper
 * @userName: jiangyu
 * @date: 2015年11月12日 下午8:51:42
 */
public class ExcpHelper
{
    /**
     * 待校验的对象
     */
    private Object obj;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 校验条件
     */
    private Condition condition;
    
    /**
     * 扩展参数信息
     */
    private Object[] paramsInfo;

    public static enum Condition {
        /** 为空或者为"" **/
        EMPTY(101),
        /** 小于等于0 **/
        LE_0(102),
        /** 预编译参数个数和参数预编译值个数不匹配 **/
        NOT_MATCH(103);
        
        private Integer value;
        
        Condition(Integer value){
            this.value = value;
        }

        public Integer getValue()
        {
            return value;
        }
    }

    public ExcpHelper(Object obj, String errorCode, Condition condition,Object... params)
    {
        this.obj = obj;
        this.errorCode = errorCode;
        this.condition = condition;
        this.paramsInfo = params;
    }

    public Object getObj()
    {
        return obj;
    }

    public void setObj(Object obj)
    {
        this.obj = obj;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public Condition getCondition()
    {
        return condition;
    }

    public void setCondition(Condition condition)
    {
        this.condition = condition;
    }

    public Object[] getParamsInfo()
    {
        return paramsInfo;
    }

    public void setParamsInfo(Object[] paramsInfo)
    {
        this.paramsInfo = paramsInfo;
    }
}
