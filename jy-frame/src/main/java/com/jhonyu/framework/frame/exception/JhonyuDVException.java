package com.jhonyu.framework.frame.exception;

/**
 * @Description: 数据校验的异常
 * @className: JhonyuDVException
 * @userName: jiangyu
 * @date: 2016年2月18日 下午1:26:10
 */
public class JhonyuDVException extends JhonyuViewException
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 358316660398026548L;
    
    /**
     * @Description 
     * <p>strs代表的是可替换的值 例如：异常信息可以定义为：从一个{0}状态调整到{1}状态出现异常 ,strs中按顺序对应的值分别会替换{0}，{1}中的信息</p>
     * @author jiangyu
     * @param errorCode
     * @param str
     */
    public JhonyuDVException(String errorCode,Object... str){
        super(errorCode, str);
    }

}
