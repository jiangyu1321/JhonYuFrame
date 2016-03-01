package com.jhonyu.framework.frame.vo;

/**
 * @Description: 分页查询的时候前台传入的参数
 * @className: JifennBaseInfo
 * @userName: jiangyu
 * @date: 2015年11月25日 上午9:01:42
 */
public class JhonyuBaseInfo
{
    /**
     * 分页大小
     */
    private Integer pageSize = 10;

    /**
     * 当前页
     */
    private Integer pageNo = 1;

    public JhonyuBaseInfo()
    {}

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public Integer getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(Integer pageNo)
    {
        this.pageNo = pageNo;
    }
}
