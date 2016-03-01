package com.jhonyu.framework.util.validate;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;


/**
 * @Description: 字符串工具类
 * @className: StringUtil
 * @userName: jiangyu
 * @date: 2016年2月18日 上午11:57:31
 */
public class StringUtil
{
    /**
     * @Description: 将字符串有某种编码转变成另一种编码
     * @userName: jiangyu
     * @date: 2016年2月18日 上午11:58:36
     * @param str
     *            要转换的字符串
     * @param originCharset
     *            原始字符串编码
     * @param targetCharset
     *            目标字符串编码
     * @return 返回目标字符串编码的字符串
     */
    public static String encodeString(String str, Charset originCharset, Charset targetCharset)
    {
        return str = new String(str.getBytes(originCharset), targetCharset);
    }

    /**
     * @Description: URL转码
     * @userName: jiangyu
     * @date: 2016年2月18日 下午12:00:58
     * @param str
     *            要转码的字符串
     * @param charset
     *            编码格式
     * @return 返回编码后的字符串
     */
    @SuppressWarnings("deprecation")
    public static String encodeUrl(String str, String charset)
    {
        if (null != charset && !charset.isEmpty())
        {
            try
            {
                return URLEncoder.encode(str, charset);
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return URLEncoder.encode(str);
    }

    /**
     * @Description: URL解码
     * @userName: jiangyu
     * @date: 2016年2月18日 下午12:43:30
     * @param str
     *            要解码的字符串
     * @param charset
     *            编码格式
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String decodeUrl(String str, String charset)
    {
        if (null != charset && !charset.isEmpty())
        {
            try
            {
                return URLDecoder.decode(str, charset);
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        return URLDecoder.decode(str);
    }

    /**
     * @Description: 判断字符串是否是空
     * @userName: jiangyu
     * @date: 2016年2月18日 下午12:44:58
     * @param str
     *            判断的字符串
     * @return
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.length() == 0;
    }

    /**
     * @Description: <p>判断字符串是否是""," ",null</p>
     * @userName: jiangyu
     * @date: 2016年2月18日 下午12:46:06
     * @param str
     *            判断的字符串
     * @return
     */
    public static boolean isBlank(String str)
    {

        int strLen;
        if (str == null || (strLen = str.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++ )
        {
            if ((Character.isWhitespace(str.charAt(i)) == false))
            {
                return false;
            }
        }
        return true;
    }

}
