package com.hhly.smartdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils{
    public static String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        return format.format(new Date());
    }

    public static String getYesterdayTime(){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);

        return format.format(calendar.getTime());
    }

    public static String getStartTime(String startTime, String endTime){
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try{
            long monthTimes = 60l * 24 * 60 * 60 * 1000;
            Long start = format.parse(startTime).getTime();
            Long end = format.parse(endTime).getTime();
            if(end - start > monthTimes){
                start = end - monthTimes;
                startTime = format.format(new Date(start));
            }
        }catch(ParseException e){
            e.printStackTrace();
        }
        return startTime;
    }
}
