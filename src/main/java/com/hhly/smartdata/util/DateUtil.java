package com.hhly.smartdata.util;


import java.text.ParseException;
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
     * 获取前一天的日期字符串
     *
     * @param dateStr "yyyy-MM-dd"
     * @return String ,"yyyy-MM-dd"
     */
    public static String getYesterdayStr(String dateStr) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        return getYesterdayStr(date);
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

    /**
     * 获取当月月的第一天
     *
     * @param month
     * @return String ,"yyyy-MM-dd"
     */
    public static String getMonthFirstDayStr(String month) throws ParseException{
        if(month == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
        Date date = sdfMonth.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    /**
     * dd
     * 获取前当月的最后一天
     *
     * @param month "yyyy-MM"
     * @return String ,"yyyy-MM-dd"
     */
    public static String getMonthEndDayStr(String month) throws ParseException{
        if(month == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfMonth = new SimpleDateFormat("yyyy-MM");
        Date date = sdfMonth.parse(month);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取前一月的第一天
     *
     * @param date
     * @return String ,"yyyy-MM-dd"
     */
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

    /**
     * dd
     * 获取前一月的最后一天
     *
     * @param date
     * @return String ,"yyyy-MM-dd"
     */
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
     * 获取离当前时间最近的靠后的整点30分钟的日期
     *
     * @param date
     * @return String ,"yyyy-MM-dd HH:mm:ss"
     */
    public static Date getThirtyMinutePoint(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        long interpolation = calendar.getTime().getTime() - date.getTime();
        if(interpolation >= 30 * 60 * 1000 && interpolation <= 60 * 60 * 1000){
            calendar.add(Calendar.MINUTE, -30);
        }

        if(interpolation < 0 || interpolation > 60 * 60 * 1000){
            throw new IllegalArgumentException("时间参数异常，不能超过制定时间范围，传入时间为：" + date);
        }
        return calendar.getTime();
    }

    /**
     * 获取离当前时间最近的靠前的整点30分钟的日期
     *
     * @param date
     * @return String ,"yyyy-MM-dd HH:mm:ss"
     */
    public static Date getBeforeThirtyMinutePoint(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        long interpolation = date.getTime() - calendar.getTime().getTime();
        if(interpolation >= 30 * 60 * 1000 && interpolation <= 60 * 60 * 1000){
            calendar.add(Calendar.MINUTE, 30);
        }

        if(interpolation < 0 || interpolation > 60 * 60 * 1000){
            throw new IllegalArgumentException("时间参数异常，不能超过制定时间范围，传入时间为：" + date);
        }
        return calendar.getTime();
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, -30);// 时间差值
        Date beforeD = calendar.getTime();
        return sdf.format(beforeD);
    }

    /**
     * 获取当月1日0点时间
     */
    public static Date getNowMonthZeroTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取当日0点时间
     */
    public static Date getNowZeroTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取当日23:59:59时间
     */
    public static Date getNowLongestTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取某时，位移多少天的最小时间时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetDayStartTime(Date date, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取某时，位移多少天的最大时间时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetDayTime(Date date, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        return calendar.getTime();
    }

    /**
     * 获取某时，位移多少天的最大时间时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetDayEndTime(Date date, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset);
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();
    }

    /**
     * 获取某时，位移多少月的最小时间时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetMonthTime(Date date, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, offset);
        return calendar.getTime();
    }

    /**
     * 获取某时，位移多少月的最小时间时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetMonthStartTime(Date date, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, offset);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTime();
    }

    /**
     * 获取某时，位移多少月的最大时间时间
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date offsetMonthEndTime(Date date, int offset){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, offset);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
        return calendar.getTime();
    }

}



