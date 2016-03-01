package com.jhonyu.framework.util.time;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jhonyu.framework.util.common.CollectionUtil;


/**
 * @Description: 时间工具类
 * @className: TimeUtil
 * @userName: jiangyu
 * @date: 2016年2月18日 下午10:06:27
 */
public class TimeUtil
{
    // 转换的日期格式
    public static String PATTERN_DATE_1 = "yyyy-MM-dd";

    public static String PATTERN_DATE_2 = "yyyyMMdd";

    public static String PATTERN_DATE_3 = "yyyyMMddhhMMss";

    public static String PATTERN_DATE_4 = "yyyy-MM-dd HH:mm:ss:SSS";

    public static String PATTERN_DATE_5 = "yyyy年MM月dd日";

    public static String PATTERN_DATE_6 = "yyyy-MM-dd HH:mm";

    public static String PATTERN_DATE_7 = "yyyy-MM-dd HH:mm:ss";

    public static String PATTERN_DATE_8 = "yyyy-MM-dd HH";

    public static String PATTERN_DATE_9 = "MMdd";

    public static String PATTERN_TIME_1 = "HH:mm";

    public static String PATTERN_YEAR_1 = "yyyy";

    public static String STARTTIME_SUFFIX = " 00:00:00";

    public static String ENDTIME_SUFFIX = " 23:59:59";

    /**
     * @Description: 将时间转为指定格式的字符串
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:06:45
     * @param date
     * @param pattern
     * @return
     */
    public static String getTimeStr(Date date, String pattern)
    {
        if (null == date)
        {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:08:40
     * @param pattern
     * @return
     */
    public static String getTimeStr(String pattern)
    {
        return getTimeStr(new Date(), pattern);
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:08:46
     * @param pattern
     * @return
     */
    public static String getTimeStrExcpBeginZero(String pattern)
    {
        String result = getTimeStr(new Date(), pattern);
        if (!CollectionUtil.isEmpty(result))
        {
            if ("0".equals(result.substring(0, 1)))
            {
                result = result.substring(1);
            }
        }
        return result;
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:08:50
     * @param date
     * @param pattern
     * @param isTrue
     * @return
     */
    public static String getTimeStrExcpBeginZero(Date date, String pattern, boolean isTrue)
    {
        String result = getTimeStr(date, pattern);
        if (!CollectionUtil.isEmpty(result))
        {
            if (isTrue)
            {
                if ("0".equals(result.substring(0, 1)))
                {
                    result = result.substring(1);
                }
            }
        }
        return result;
    }

    /**
     * @Description: 将日期转换为字符串
     * @userName: jiangyu
     * @date: 2016年1月11日 下午5:31:50
     * @param date
     * @param patten
     * @return
     */
    public static String Date2Sring(Date date, String patten)
    {
        if (CollectionUtil.isEmpty(date))
        {
            date = new Date();
        }
        if (CollectionUtil.isEmpty(patten))
        {
            patten = PATTERN_DATE_1;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(date);
    }

    /**
     * @Description: 获取指定范围的随机数
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:08:59
     * @param start
     * @param end
     * @return
     */
    public static int getRandom(int start, int end)
    {
        return start + ((int)(Math.random() * 10 * (end - start + 2)) % (end - start + 1));
    }

    /**
     * @Description: 获得两个日期之间相差的天数
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:09:11
     * @param time1 开始时间
     * @param time2 结束时间
     * @param patten 两个日期之间相差的天数
     * @return
     */
    public static long getQuot(String time1, String time2, String patten)
    {
        long quot = 0;
        SimpleDateFormat ft = new SimpleDateFormat(patten);
        try
        {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);

            quot = date2.getTime() - date1.getTime();
            quot = quot / 1000 / 60 / 60 / 24;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return quot;
    }

    /**
     * @Description: 将Timestamp时间转为指定格式的字符串
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:09:35
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String getTimestampStr(Timestamp timestamp, String pattern)
    {
        if (null == timestamp)
        {
            return "";
        }

        java.util.Calendar c = java.util.Calendar.getInstance();
        c.setTimeInMillis(timestamp.getTime());
        return new SimpleDateFormat(pattern).format(c.getTime());
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:09:54
     * @param string
     * @return
     */
    public static Date getTimestampDateFromString(String string)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_DATE_4);
        try
        {
            Date date = dateFormat.parse(string);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        }
        catch (ParseException e)
        {}
        return new Date();
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:10:01
     * @param string
     * @param format
     * @return
     */
    public static Date getTimestampDateFromString(String string, String format)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try
        {
            Date date = dateFormat.parse(string);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        }
        catch (ParseException e)
        {}
        return new Date();
    }

    /**
     * @Description: 获取某年某月最大天数
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:10:06
     * @param year 年
     * @param month 月
     * @return
     */
    public static int getMaxDay(String year, String month)
    {
        // 计算某一月份的最大天数
        Calendar time = Calendar.getInstance();
        time.clear(); // 若不clear，很多信息会继承自系统当前时间
        time.set(Calendar.YEAR, Integer.valueOf(year));
        time.set(Calendar.MONTH, Integer.valueOf(month) - 1);
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH); // 本月总天数
        return day;
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:10:26
     * @param timeStr
     * @return
     */
    public static Timestamp getTimeStamp(String timeStr)
    {
        Date date = getTimestampDateFromString(timeStr);
        Timestamp time = new Timestamp(date.getTime());
        return time;
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:10:31
     * @param date
     * @return
     */
    public static Timestamp getTimeStamp(Date date)
    {
        Timestamp time = new Timestamp(date.getTime());
        return time;
    }

    /**
     * @Description: 计算传入时间与当前时间的相隔天数 
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:12:27
     * @param timestamp
     * @return
     */
    public static long getDays(Timestamp timestamp)
    {
        long currentTime = System.currentTimeMillis();
        long temp = timestamp.getTime();
        long dayslong = currentTime - temp;
        long days = (dayslong >> 10) / 84375;
        long l_days = (long)days;
        return l_days;
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:12:41
     * @param timeStr
     * @param pattern
     * @return
     */
    public static Timestamp getTimeStamp(String timeStr, String pattern)
    {
        DateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        try
        {
            Timestamp ts = new Timestamp(format.parse(timeStr).getTime());
            return ts;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:12:46
     * @param date
     * @param fmt
     * @return
     * @throws Exception
     */
    public static Date getDateFromString(String date, String fmt)
        throws Exception
    {
        if (date == null || date.trim().length() == 0) return null;
        DateFormat df = new SimpleDateFormat(fmt);
        return df.parse(date);
    }

    /**
     * @Description: 将字符串转换为时间
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:12:51
     * @param str
     * @param pattern
     * @return
     */
    public static Date getDateFormatString(String str, String pattern)
    {
        if (str != null && !str.trim().equals(""))
        {
            try
            {
                return new SimpleDateFormat(pattern).parse(str);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }

    }

    /**
     * @Description: 获取两个时间差(格式:XX天XX小时XX分XX秒)
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:13:08
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getTwoDateDiff(Date startDate, Date endDate)
    {
        long l = endDate.getTime() - startDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sed = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        return "" + day + "天" + hour + "小时" + min + "分" + sed + "秒";

    }

    /**
     * @Description: 获取两个时间的间隔天数
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:13:23
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getTwoDay(Date startDate, Date endDate)
    {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String endTempDate = myFormatter.format(endDate.clone());
        String startTempDate = myFormatter.format(startDate.clone());
        try
        {
            return ((myFormatter.parse(endTempDate).getTime()) - (myFormatter.parse(startTempDate).getTime()))
                   / (24 * 60 * 60 * 1000);
        }
        catch (ParseException e)
        {
            return -1L;
        }
    }

    /**
     * @Description: 获取当前日期的开始时间可结束时间
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:13:37
     * @return
     */
    public static List<Date> getBeginAndEndDate()
    {
        List<Date> preDates = new ArrayList<Date>();
        preDates.add(getTodayBeginTime());
        preDates.add(getTodayEndTime());
        return preDates;
    }

    /**
     * @Description: 获取今天的开始时间
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:13:50
     * @return
     */
    public static Date getTodayBeginTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_7);
        Date begin = null;
        try
        {
            String baseTimeStr = getBaseDateStr();
            String beginTime = baseTimeStr + STARTTIME_SUFFIX;
            begin = sdf.parse(beginTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return begin;
    }

    /**
     * @Description: 获取今天的结束时间
     * @userName: jiangyu
     * @date: 2015年12月23日 上午11:23:50
     * @return
     */
    public static Date getTodayEndTime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_7);
        Date end = null;
        try
        {
            String baseTimeStr = getBaseDateStr();
            String endTime = baseTimeStr + ENDTIME_SUFFIX;
            end = sdf.parse(endTime);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return end;
    }

    /**
     * @Description: 获取当前日期
     * @userName: jiangyu
     * @date: 2015年12月23日 上午11:23:57
     * @return
     */
    public static String getBaseDateStr()
    {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_1);
        return sdf.format(new Date());
    }

    public static String getBaseDateStr(String patten)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(patten);
        return sdf.format(new Date());
    }

    /**
     * @Description: 获取日期的星期几
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:02:39
     * @param dt
     * @return
     */
    public static String getDateOfWeek(Date dt)
    {
        String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    /**
     * @Description: 日期的天
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:28:42
     * @param dt
     * @return
     */
    public static Integer getDateOfDay(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * @Description: 日期的月
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:28:38
     * @param dt
     * @return
     */
    public static Integer getDateOfMonth(Date dt)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * @Description: 日期中天的尾数
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:30:23
     * @param dt
     * @return
     */
    public static Integer getDayOfSuffix(Date dt)
    {
        int day = getDateOfDay(dt);
        return day % 10;
    }

    /**
     * @Description: 获取当前日期是星期几
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:02:39
     * @return
     */
    public static String getCurrentDateOfWeek()
    {
        Date date = new Date();
        return getDateOfWeek(date);
    }

    /**
     * @Description: 获取当前日期的天
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:28:42
     * @return
     */
    public static Integer getCurrentDateOfDay()
    {
        Date date = new Date();
        return getDateOfDay(date);
    }

    /**
     * @Description: 获取当前日期的月
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:28:38
     * @return
     */
    public static Integer getCurrentDateOfMonth()
    {
        Date date = new Date();
        return getDateOfMonth(date);
    }

    /**
     * @Description: 获取当前日期中天的尾数
     * @userName: jiangyu
     * @date: 2016年1月11日 上午10:30:23
     * @param dt
     * @return
     */
    public static Integer getCurrentDayOfSuffix()
    {
        Date date = new Date();
        return getDayOfSuffix(date);
    }

    /**
     * @Description: 获取当前的年份
     * @userName: jiangyu
     * @date: 2016年1月11日 下午4:33:12
     * @return
     */
    public static String getCurrentYear()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year + "";
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:14:14
     * @return
     */
    public static String getCurrentPreviousYear()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return (year - 1) + "";
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:14:19
     * @return
     */
    public static String getCurrentNextYear()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return (year + 1) + "";
    }

    /**
     * @Description: TODO
     * @userName: jiangyu
     * @date: 2016年2月18日 下午10:14:24
     * @param args
     */
    public static void main(String[] args)
    {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DATE); // 日
        int month = cal.get(Calendar.MONTH) + 1;// 月
        int year = cal.get(Calendar.YEAR); // 年

        System.out.println("Date: " + cal.getTime());
        System.out.println("Day: " + day);
        System.out.println("Month: " + month);
        System.out.println("Year: " + year);

        System.out.println(getTimeStrExcpBeginZero(TimeUtil.PATTERN_DATE_9));
    }

}
