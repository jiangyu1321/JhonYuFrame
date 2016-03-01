package com.jhonyu.framework.frame.util;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: spring上下文获取工具类
 * @className: SpringContextUtil
 * @userName: jiangyu
 * @date: 2016年2月16日 上午9:33:51
 */
public class SpringContextUtil implements ApplicationContextAware
{

    private SpringContextUtil()
    {

    }

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException
    {
        // SpringContextUtil.applicationContext = applicationContext;
        setContext(applicationContext);
    }

    private static void setContext(ApplicationContext applicationContext)
    {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * @Description: 获取bean对象
     * @userName: jiangyu
     * @date: 2015年11月13日 上午11:30:16
     * @param componentClass 要加载的bean类
     * @return
     */
    public static <T> T getComponent(Class<T> componentClass)
    {
        return SpringContextUtil.getApplicationContext().getBean(componentClass);
    }
}
