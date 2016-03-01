package com.jhonyu.framework.util.common;

import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @Description: 字符串分隔工具
 * @className: StrSplitUtil
 * @userName: jiangyu
 * @date: 2016年2月16日 下午12:04:10
 */
public class StrSplitUtil
{
    public static String SPLIT_PATTERN = ",|;|，|；|(\\n)";
    
    /**
     * 按char型分隔符分割字符,将所给分隔符变成拆分成多个char,然后分别分割
     *
     * @param str
     *            原始字符
     * @param spec
     *            分隔
     * @return 分割后的字符数组
     */
    public static String[] strSplit(String str, String spec)
    {
        return StrSplitUtil.strSplit(str, spec, false);
    }

    
    /**
     * @Description: 按char型分隔符分割字符,将所给分隔符变成拆分成多个char,然后分别分割
     * @userName: jiangyu
     * @date: 2016年2月16日 下午12:05:11
     * @param str 原始字符
     * @param spec 分隔
     * @param withNull 是否统计空字符串
     * @return 分割后的字符数组
     */
    public static String[] strSplit(String str, String spec, boolean withNull)
    {
        if (CollectionUtil.isEmpty(str))
        {
            return new String[0];
        }
        StringTokenizer token = new StringTokenizer(str, spec);
        int count = token.countTokens();
        Vector<String> vt = new Vector<String>();
        for (int i = 0; i < count; i++ )
        {
            String tmp = token.nextToken();
            if (withNull || !CollectionUtil.isEmpty(tmp))
            {
                vt.addElement(tmp.trim());
            }
        }
        return vt.toArray(new String[0]);
    }

}
