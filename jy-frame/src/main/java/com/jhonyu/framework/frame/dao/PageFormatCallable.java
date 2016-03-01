package com.jhonyu.framework.frame.dao;

import java.util.List;

/**
 * @Description: sql分页的回调接口
 * @className: PageFormatCallable
 * @userName: jiangyu
 * @date: 2015年11月25日 上午8:20:04
 */
public interface PageFormatCallable<X>
{   
    /**
     * @Description: 将查询出来的分页数据转换成X类型格式的数据
     * @userName: jiangyu
     * @date: 2015年11月25日 下午2:22:08
     * @param list 分页结果对象
     * @return 转换后的结果对象
     */
    List<X> format(List<Object> list);
}

