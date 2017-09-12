package com.hhly.smartdata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

public class DateListUtil{

    public static final String formatStyle = "yyyy-MM-dd";
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(formatStyle);
    public static final long DAY_MS = 24 * 60 * 60 * 1000;

    /**
     * 获取时间段列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static TreeSet<String> getCountDateList(String startDate, String endDate){

        Date endDate2 = null;
        Date startDate2 = null;

        if(endDate == null){
            endDate2 = new Date();
        }else{
            endDate2 = DateListUtil.getDateDate(endDate);
        }

        if(startDate == null){
            startDate2 = new Date(endDate2.getTime() - 6 * DAY_MS);
        }else{
            startDate2 = DateListUtil.getDateDate(startDate);
        }

        return DateListUtil.getCountDateList(startDate2, endDate2);

    }

    /**
     * 获取查询时间段列表,限定时间长度只能是最近30天
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static TreeSet<String> getLimitCountDateList(String startDate, String endDate){

        Date endDate2 = null;
        Date startDate2 = null;

        if(endDate == null){
            endDate2 = new Date();
        }else{
            endDate2 = DateListUtil.getDateDate(endDate);
        }

        if(startDate == null){
            startDate2 = new Date(endDate2.getTime() - 6 * DAY_MS);
        }else{
            startDate2 = DateListUtil.getDateDate(startDate);
        }

        return DateListUtil.getLimitCountDateList(startDate2, endDate2);

    }

    /**
     * 获取时间段列表，限定时间长度只能是最近30天
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static TreeSet<String> getLimitCountDateList(Date startDate, Date endDate){

        TreeSet<String> dates = new TreeSet<String>();

        // 开始时间大于结束时时间，回复默认
        if(startDate.getTime() > endDate.getTime()){
            startDate = new Date(new Date().getTime() - 6 * DAY_MS);
            endDate = new Date();
        }
        //限定只能查询最近30天
        if(new Date().getTime() - startDate.getTime() > 29 * DAY_MS){
            startDate = new Date(new Date().getTime() - 29 * DAY_MS);
            endDate = new Date();
        }

        Date currentDate = startDate;
        while(currentDate.getTime() <= endDate.getTime()){
            dates.add(DateListUtil.getStringDate(currentDate));
            currentDate = new Date(currentDate.getTime() + DAY_MS);
        }
        return dates;
    }

    /**
     * 获取时间段列表
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static TreeSet<String> getCountDateList(Date startDate, Date endDate){

        TreeSet<String> dates = new TreeSet<String>();

        // 开始时间大于结束时时间，回复默认
        if(startDate.getTime() > endDate.getTime()){
            startDate = new Date(new Date().getTime() - 6 * DAY_MS);
            endDate = new Date();
        }

        Date currentDate = startDate;
        while(currentDate.getTime() <= endDate.getTime()){
            dates.add(DateListUtil.getStringDate(currentDate));
            currentDate = new Date(currentDate.getTime() + DAY_MS);
        }
        return dates;
    }

    public static String getStringDate(Date date){
        return SIMPLE_DATE_FORMAT.format(date);
    }

    public static Date getDateDate(String date){
        try{
            Date date2 = SIMPLE_DATE_FORMAT.parse(date);
            return date2;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
