package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class DailyKeepRecordReport{
    /**
     * 日报表，充值来源统计报表
     */
    private Long id;

    /**
     * 统计日期(日)yyyy-MM-dd
     */
    private String statisticsDay = "";

    /**
     * 源端类型：1、PC 2.android 3.IOS 4.H5
     */
    private Byte sourceType = 0;

    /**
     * 注册人数
     */
    private Integer registerCount = 0;

    /**
     * 1天后留存
     */
    private Integer one = 0;

    /**
     * 2天后留存
     */
    private Integer two = 0;

    /**
     * 3天后留存
     */
    private Integer three = 0;

    /**
     * 4天后留存
     */
    private Integer four = 0;

    /**
     * 5天后留存
     */
    private Integer five = 0;

    /**
     * 6天后留存
     */
    private Integer six = 0;

    /**
     * 7天后留存
     */
    private Integer seven = 0;

    /**
     * 14天后留存
     */
    private Integer fourteen = 0;

    /**
     * 30天后留存
     */
    private Integer thirty = 0;

    /**
     * 统计执行日期
     */
    private Date executeTime;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getStatisticsDay(){
        return statisticsDay;
    }

    public void setStatisticsDay(String statisticsDay){
        this.statisticsDay = statisticsDay;
    }

    public Byte getSourceType(){
        return sourceType;
    }

    public void setSourceType(Byte sourceType){
        this.sourceType = sourceType;
    }

    public Integer getRegisterCount(){
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount){
        this.registerCount = registerCount;
    }

    public Integer getOne(){
        return one;
    }

    public void setOne(Integer one){
        this.one = one;
    }

    public Integer getTwo(){
        return two;
    }

    public void setTwo(Integer two){
        this.two = two;
    }

    public Integer getThree(){
        return three;
    }

    public void setThree(Integer three){
        this.three = three;
    }

    public Integer getFour(){
        return four;
    }

    public void setFour(Integer four){
        this.four = four;
    }

    public Integer getFive(){
        return five;
    }

    public void setFive(Integer five){
        this.five = five;
    }

    public Integer getSix(){
        return six;
    }

    public void setSix(Integer six){
        this.six = six;
    }

    public Integer getSeven(){
        return seven;
    }

    public void setSeven(Integer seven){
        this.seven = seven;
    }

    public Integer getFourteen(){
        return fourteen;
    }

    public void setFourteen(Integer fourteen){
        this.fourteen = fourteen;
    }

    public Integer getThirty(){
        return thirty;
    }

    public void setThirty(Integer thirty){
        this.thirty = thirty;
    }

    public Date getExecuteTime(){
        return executeTime;
    }

    public void setExecuteTime(Date executeTime){
        this.executeTime = executeTime;
    }
}