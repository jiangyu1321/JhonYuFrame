package com.jhonyu.framework.util.encrypt;

/**
 * @Description: TODO
 * @className: EncrptyUtil
 * @userName: jiangyu
 * @date: 2016年1月7日 下午2:35:13
 */
public class EncrptyUtil
{
    private static final String SUFFIX = "jifenn_heartbeat";

    public static String encrptyCode(String plainText)
    {
        return MD5Util.getMD5Code(plainText + SUFFIX);
    }
    
    public static void main(String[] args)
    {
        System.out.println(encrptyCode("jjyy"));
    }
    /**
     * @Description: 校验密码
     * @userName: jiangyu
     * @date: 2016年1月9日 上午8:58:40
     * @param ip ip地址
     * @param timestamp 时间戳
     * @param encrptyStr 要校验的密文
     * @return
     */
    public static boolean varifyAuthorize(String ip,String timestamp,String encrptyStr){
        String str = MD5Util.getMD5Code(ip+timestamp+SUFFIX);
        if (str.equals(encrptyStr))
        {
            return true;
        }else {
            return false;
        }
    }
}
