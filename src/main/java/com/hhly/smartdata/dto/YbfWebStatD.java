package com.hhly.smartdata.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YbfWebStatD{

    public static final String formatStyle = "yyyy-MM-dd";

    private String etlDate;

    private String urlId;

    private long clickCnt;

    private long userCnt;

    private long ipCnt;

    private long stayCnt;

    private long avgStayCnt;

    private long totalUserCnt;

    private String urlName;

    private long viewCnt;

    private long dayTotalUserCnt;

    /**
     * 累计用户所占百分比
     */
    private double userPercent;


    public String getEtlDate(){
        return etlDate;
    }

    public void setEtlDate(Date etlDate){
        this.etlDate = new SimpleDateFormat(formatStyle).format(etlDate);
    }

    public String getUrlId(){
        return urlId;
    }

    public void setUrlId(String urlId){
        this.urlId = urlId;
    }

    public long getClickCnt(){
        return clickCnt;
    }

    public void setClickCnt(long clickCnt){
        this.clickCnt = clickCnt;
    }

    public long getUserCnt(){
        return userCnt;
    }

    public void setUserCnt(long userCnt){
        this.userCnt = userCnt;
    }

    public long getIpCnt(){
        return ipCnt;
    }

    public void setIpCnt(long ipCnt){
        this.ipCnt = ipCnt;
    }

    public long getStayCnt(){
        return stayCnt;
    }

    public void setStayCnt(long stayCnt){
        this.stayCnt = stayCnt;
    }

    public long getTotalUserCnt(){
        return totalUserCnt;
    }

    public void setTotalUserCnt(long totalUserCnt){
        this.totalUserCnt = totalUserCnt;
    }

    public String getUrlName(){
        return urlName;
    }

    public void setUrlName(String urlName){
        this.urlName = urlName;
    }

    public long getAvgStayCnt(){
        avgStayCnt = 0;
        if(0 != viewCnt){
            avgStayCnt = stayCnt / viewCnt;
        }
        return avgStayCnt;
    }

    public long getViewCnt(){
        return viewCnt;
    }

    public void setViewCnt(long viewCnt){
        this.viewCnt = viewCnt;
    }

    public long getDayTotalUserCnt(){
        return dayTotalUserCnt;
    }

    public void setDayTotalUserCnt(long dayTotalUserCnt){
        this.dayTotalUserCnt = dayTotalUserCnt;
    }

    public double getUserPercent(){
        userPercent = 0;
        if(0 != totalUserCnt){
            userPercent = dayTotalUserCnt / (double) totalUserCnt;
        }
        return userPercent;
    }
}
