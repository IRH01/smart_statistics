package com.hhly.smartdata.model.smartdata;

import java.util.Date;

public class DailyKeepRecordReport {
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
    Integer registerCount = 0;

    /**
     * 1天后留存
     */
    private Integer oneRemain = 0;

    /**
     * 2天后留存
     */
    private Integer twoRemain = 0;

    /**
     * 3天后留存
     */
    private Integer threeRemain = 0;

    /**
     * 4天后留存
     */
    private Integer fourRemain = 0;

    /**
     * 5天后留存
     */
    private Integer fiveRemain = 0;

    /**
     * 6天后留存
     */
    private Integer sixRemain = 0;

    /**
     * 7天后留存
     */
    private Integer sevenRemain = 0;

    /**
     * 14天后留存
     */
    private Integer fourteenRemain = 0;

    /**
     * 30天后留存
     */
    private Integer thirtyRemain = 0;

    /**
     * 统计执行日期
     */
    private Date executeTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatisticsDay() {
        return statisticsDay;
    }

    public void setStatisticsDay(String statisticsDay) {
        this.statisticsDay = statisticsDay;
    }

    public Byte getSourceType() {
        return sourceType;
    }

    public void setSourceType(Byte sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getRegisterCount() {
        return registerCount;
    }

    public void setRegisterCount(Integer registerCount) {
        this.registerCount = registerCount;
    }

    public Integer getOneRemain() {
        return oneRemain;
    }

    public void setOneRemain(Integer oneRemain) {
        this.oneRemain = oneRemain;
    }

    public Integer getTwoRemain() {
        return twoRemain;
    }

    public void setTwoRemain(Integer twoRemain) {
        this.twoRemain = twoRemain;
    }

    public Integer getThreeRemain() {
        return threeRemain;
    }

    public void setThreeRemain(Integer threeRemain) {
        this.threeRemain = threeRemain;
    }

    public Integer getFourRemain() {
        return fourRemain;
    }

    public void setFourRemain(Integer fourRemain) {
        this.fourRemain = fourRemain;
    }

    public Integer getFiveRemain() {
        return fiveRemain;
    }

    public void setFiveRemain(Integer fiveRemain) {
        this.fiveRemain = fiveRemain;
    }

    public Integer getSixRemain() {
        return sixRemain;
    }

    public void setSixRemain(Integer sixRemain) {
        this.sixRemain = sixRemain;
    }

    public Integer getSevenRemain() {
        return sevenRemain;
    }

    public void setSevenRemain(Integer sevenRemain) {
        this.sevenRemain = sevenRemain;
    }

    public Integer getFourteenRemain() {
        return fourteenRemain;
    }

    public void setFourteenRemain(Integer fourteenRemain) {
        this.fourteenRemain = fourteenRemain;
    }

    public Integer getThirtyRemain() {
        return thirtyRemain;
    }

    public void setThirtyRemain(Integer thirtyRemain) {
        this.thirtyRemain = thirtyRemain;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}