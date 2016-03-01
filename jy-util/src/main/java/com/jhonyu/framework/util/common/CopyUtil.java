package com.jhonyu.framework.util.common;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * @Description: 拷贝的工具类
 * @className: CopyUtil
 * @userName: jiangyu
 * @date: 2016年2月16日 下午12:56:38
 */
public class CopyUtil
{
    public static final Logger LOG = Logger.getLogger(CopyUtil.class);

    /**
     * @Description: List集合的深复制
     * @userName: jiangyu
     * @date: 2015年11月26日 上午9:17:20
     * @param src
     *            需要被复制的List集合
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> deepCopy(List<T> src)
    {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out;
        try
        {
            out = new ObjectOutputStream(byteOut);
            out.writeObject(src);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            List<T> dest = (List<T>)in.readObject();
            return dest;
        }
        catch (IOException e)
        {
            LOG.error("deep copy List data IO Exception:" + e.getMessage());
            e.printStackTrace();
        }
        catch (ClassNotFoundException e2)
        {
            LOG.error("deep copy List data ClassNotFoundException:" + e2.getMessage());
            e2.printStackTrace();
        }
        return new ArrayList<T>();
    }

}
