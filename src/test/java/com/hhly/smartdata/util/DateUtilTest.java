package com.hhly.smartdata.util;

import com.hhly.smartdata.common.BaseTest;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil Tester.
 *
 * @author Iritchie.ren
 * @version 1.0
 * @since <pre>十月 31, 2017</pre>
 */
public class DateUtilTest extends BaseTest{

    /**
     * Method: date2String(Date date)
     */
    @Test
    public void testDate2StringDate() throws Exception{
        String result = DateUtil.date2String(new Date());
        System.err.println(result);
    }

    /**
     * Method: date2String(Date date, String pattern)
     */
    @Test
    public void testDate2StringForDatePattern() throws Exception{

    }

    /**
     * Method: string2Date(String dateStr)
     */
    @Test
    public void testString2DateDateStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: string2Date(String dateStr, String pattern)
     */
    @Test
    public void testString2DateForDateStrPattern() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: add(Date date, int field, int amount)
     */
    @Test
    public void testAdd() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getTime(Date date, int hour, int minute, int second)
     */
    @Test
    public void testGetTime() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getYesterdayStr(Date date)
     */
    @Test
    public void testGetYesterdayStrDate() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getYesterdayStr(String dateStr)
     */
    @Test
    public void testGetYesterdayStrDateStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getLastMonthStr(Date date)
     */
    @Test
    public void testGetLastMonthStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getMonthFirstDayStr(String month)
     */
    @Test
    public void testGetMonthFirstDayStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getMonthEndDayStr(String month)
     */
    @Test
    public void testGetMonthEndDayStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getLastMonthFirstDayStr(Date date)
     */
    @Test
    public void testGetLastMonthFirstDayStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getLastMonthEndDayStr(Date date)
     */
    @Test
    public void testGetLastMonthEndDayStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getThirtyMinutePoint(Date date)
     */
    @Test
    public void testGetThirtyMinutePoint() throws Exception{
        System.err.println(DateUtil.date2String(DateUtil.getThirtyMinutePoint(new Date())));
    }

    /**
     * Method: getBeforeThirtyMinutePoint(Date date)
     */
    @Test
    public void testGetBeforeThirtyMinutePoint() throws Exception{
        System.err.println(DateUtil.date2String(DateUtil.getBeforeThirtyMinutePoint(new Date())));
    }

    /**
     * Method: getFirstThirtyMinStr(Date date)
     */
    @Test
    public void testGetFirstThirtyMinStr() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getNowMonthZeroTime(Date date)
     */
    @Test
    public void testGetNowMonthZeroTime() throws Exception{
        //TODO: Test goes here...
    }

    /**
     * Method: getNowZeroTime(Date date)
     */
    @Test
    public void testGetNowZeroTime() throws Exception{
        //TODO: Test goes here...
    }

    @Test
    public void testOffsetDayStartTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int amount = -1;
        Date now = new Date();

        Date result = DateUtil.offsetDayStartTime(now, amount);
        System.err.println(sdf.format(result));
    }

    @Test
    public void testOffsetDayEndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        int amount = -1;

        Date result = DateUtil.offsetDayEndTime(now, amount);
        System.err.println(sdf.format(result));
    }

    @Test
    public void testOffsetMonthStartTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int amount = -1;
        Date now = new Date();

        Date result = DateUtil.offsetMonthStartTime(now, amount);
        System.err.println(sdf.format(result));
    }

    @Test
    public void testOffsetMonthEndTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        int amount = -1;

        Date result = DateUtil.offsetMonthEndTime(now, amount);
        System.err.println(sdf.format(result));
    }
} 
