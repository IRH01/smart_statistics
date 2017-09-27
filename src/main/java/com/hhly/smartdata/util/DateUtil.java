package com.hhly.smartdata.util;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{

    /**
     * 解析日期成对应的字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param date 日期
     * @return 字符串
     */
    public static String date2String(Date date){
        if(date == null){
            return null;
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return date2String(date, pattern);
    }

    /**
     * 解析日期成对应的字符串
     *
     * @param date    日期
     * @param pattern 日期正则格式
     * @return 字符串
     */
    public static String date2String(Date date, String pattern){
        if(date == null){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 解析字符串成对应的日期格式 yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static Date string2Date(String dateStr){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        return string2Date(dateStr, pattern);
    }

    /**
     * 解析字符串成对应的日期格式
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date string2Date(String dateStr, String pattern){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(dateStr);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 日期时间增加相应的时间，调用案例如：
     * <p><code>add(Calendar.DAY_OF_MONTH, -5)</code>.
     *
     * @param field  the calendar field .See {@link  java.util.Calendar}.
     * @param amount the amount of date or time to be added to the field.
     */
    public static Date add(Date date, int field, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 根据日期设置时分秒，获取新的时间
     *
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getTime(Date date, int hour, int minute, int second){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 获取前一天的日期字符串
     *
     * @param date
     * @return String ,"yyyy-MM-dd"
     */
    public static String getYesterdayStr(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, -1);//日期加-1天
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取前一月的日期字符串
     *
     * @param date
     * @return String ,"yyyy-MM"
     */
    public static String getLastMonthStr(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//日期加-1月
        return sdf.format(calendar.getTime());
    }

    public static String getLastMonthFirstDayStr(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//日期加-1月
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    public static String getLastMonthEndDayStr(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);//日期加-1月
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取前30分钟的日期
     *
     * @param date
     * @return String ,"yyyy-MM-dd HH:mm:ss"
     */
    public static String getFirstThirtyMinStr(Date date){
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -30);// 时间差值
        Date beforeD = beforeTime.getTime();
        return sdf.format(beforeD);
    }


}



