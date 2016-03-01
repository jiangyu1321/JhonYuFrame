package com.jhonyu.framework.util.common;


import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 转换成List的工具类
 * @className: TransferListUtil
 * @userName: jiangyu
 * @date: 2016年2月16日 下午12:57:56
 */
public class TransferListUtil
{
    /**
     * @Description: 将数组转换为in字段
     * @userName: jiangyu
     * @date: 2015年11月18日 下午7:25:37
     * @param data
     * @return
     */
    public static String IntegerArray2InStr(Integer[] data)
    {
        if (data == null)
        {
            return "0";
        }
        String inStr = "";
        for (int i = 0; i < data.length; i++ )
        {
            inStr += data[i] + ",";
        }
        if (inStr.length() > 0)
        {
            inStr = inStr.substring(0, inStr.length() - 1);
        }
        return inStr;
    }

    /**
     * @Description:
     * @userName: jiangyu
     * @date: 2015年11月18日 下午7:27:33
     * @param activitiesIdArr
     * @return
     */
    public static String strArray2IntInStr(String[] data)
    {
        if (data == null)
        {
            return "0";
        }
        String inStr = "";
        for (int i = 0; i < data.length; i++ )
        {
            inStr += data[i] + ",";
        }
        if (inStr.length() > 0)
        {
            inStr = inStr.substring(0, inStr.length() - 1);
        }
        return inStr;
    }

    /**
     * @Description: 将用逗号分隔开的字段串转换成List<Integer>
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:49:53
     * @param strSrc
     *            带逗号分隔的字符串
     * @return
     */
    public static List<Integer> StrToIntegerList(String strSrc)
    {
        List<Integer> result = new ArrayList<Integer>();
        if (!CollectionUtil.isEmpty(strSrc))
        {
            String[] strArr = StrSplitUtil.strSplit(strSrc, ",");
            for (String str : strArr)
            {
                result.add(Integer.valueOf(str));
            }
        }
        return result;
    }

    /**
     * @Description: str数组转换为List<Integer>
     * @userName: jiangyu
     * @date: 2015年11月18日 下午7:35:23
     * @param strArr
     *            要转换的字符串数组
     * @return
     */
    public static List<Integer> StrArrToIntegerList(String[] strArr)
    {
        List<Integer> result = new ArrayList<Integer>();
        if (!CollectionUtil.isEmpty(strArr))
        {
            for (String str : strArr)
            {
                result.add(Integer.valueOf(str));
            }
        }
        return result;
    }

    /**
     * @Description: 将Str数组转换成List<Long>
     * @userName: jiangyu
     * @date: 2016年2月16日 上午11:44:24
     * @param strArr
     *            要转换的字符串数组
     * @return 转换之后的List<Long>
     */
    public static List<Long> StrArrToLongList(String[] strArr)
    {
        List<Long> result = new ArrayList<Long>();
        if (!CollectionUtil.isEmpty(strArr))
        {
            for (String str : strArr)
            {
                result.add(Long.valueOf(str));
            }
        }
        return result;
    }

}
