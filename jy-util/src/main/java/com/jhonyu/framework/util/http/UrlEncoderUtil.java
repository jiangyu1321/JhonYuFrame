package com.jhonyu.framework.util.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//import org.apache.log4j.Logger;

/**
 * @Description: TODO
 * @className: UrlEncoderUtil
 * @userName: jiangyu
 * @date: 2015年12月26日 下午2:42:20
 */
public class UrlEncoderUtil
{
//    private static final Logger LOG = Logger.getLogger(UrlEncoderUtil.class);
    
    public static String UTF8Encode(String params){
        String res = "";
        try
        {
            res = URLEncoder.encode(params,"utf-8");
        }
        catch (UnsupportedEncodingException e)
        {
//            LOG.info("encode failed ,reason is :"+e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
}

