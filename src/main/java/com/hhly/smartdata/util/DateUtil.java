package com.hhly.smartdata.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static SimpleDateFormat sdfYMDHMS = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    public static SimpleDateFormat sdf0 = new SimpleDateFormat("YYYYMMddHHmmss");

    public static Date parseUtilDate(String dateStr, String pattern){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(dateStr);
            return date;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static Date parseSqlDate(String dateStr, String pattern){
        Date utilDate = parseUtilDate(dateStr, pattern);
        if(utilDate != null){
            return new Date(utilDate.getTime());
        }
        return null;
    }

    public static String formatDate(Date date, String pattern){
        try{
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.format(date);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String formatDate(java.sql.Date date, String pattern){
        Date utilDate = new Date(date.getTime());
        return formatDate(utilDate, pattern);
    }


    public static Date add(Date date, int field, int amount){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date dateFormat(Date date, String format){
        if(date == null || format == null || format.length() == 0){
            throw new IllegalArgumentException(" Date and format  must be null");
        }
        String newDate = formatDate(date, format);
        return parseSqlDate(newDate, format);
    }

    public static Date dateAddFormat(Date date, int calendarType, int amount, String format){
        if(date == null || format == null || format.length() == 0){
            throw new IllegalArgumentException(" Date and format  must be null");
        }
        Date newDate = add(date, calendarType, amount);
        return dateFormat(newDate, format);
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
     * 把时间转化成YYYY-MM-DD hh:mm:ss 格式
     *
     * @param date
     * @return
     */
    public static String formatDateYYYYMMDDHHmmss(Date date){
        return sdfYMDHMS.format(date);
    }

    public static Date parseDate(String dateStr, String format){
        Date date = null;

        try{
            DateFormat df_parseDate = new SimpleDateFormat(format);
            date = df_parseDate.parse(dateStr);
        }catch(Exception var5){
            ;
        }

        return date;
    }

    public static Date parseDate(String dateStr){
        return parseDate(dateStr, "yyyy-MM-dd");
    }

    public static String formatDate(Date date){
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    public static String formatDateByFormat(Date date, String format){
        String result = "";
        if(date != null){
            try{
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            }catch(Exception var4){
                ;
            }
        }

        return result;
    }

    public static String formatDatayyMMDDHHMMSS(Date date){
        return sdf0.format(date);
    }

    public static Date getYearMonth(Date date){
        Date value = null;
        if(date == null){
            return date;
        }else{
            value = parseDate((new SimpleDateFormat("yyyy-MM")).format(date), "yyyy-MM");
            return value;
        }
    }

    public static String convertYearMonth(Date date){
        return date == null ? null : (new SimpleDateFormat("yyyy-MM")).format(date);
    }

    public String getChineseWeek(Calendar date, String character){
        String[] dayNames = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if("zh".equals(character)){
            dayNames[0] = "星期日";
            dayNames[1] = "星期一";
            dayNames[2] = "星期二";
            dayNames[3] = "星期三";
            dayNames[4] = "星期四";
            dayNames[5] = "星期五";
            dayNames[6] = "星期六";
        }

        int dayOfWeek = date.get(7);
        return dayNames[dayOfWeek - 1];
    }

    public Calendar getNextMonday(Calendar date){
        Calendar result = null;
        result = date;

        do{
            result = (Calendar) result.clone();
            result.add(5, 1);
        }while(result.get(7) != 2);

        return result;
    }

}
