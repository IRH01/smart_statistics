package com.hhly.generator.model;

import java.util.Date;

public class MonthRegisterReport {
    private Long id;

    private String statisticsDay;

    private Integer pcPageView;

    private Integer pcUserView;

    private Integer hfivePopulation;

    private Integer hfivePageView;

    private Integer hfiveUserView;

    private Integer iosPopulation;

    private Integer iosInstallCount;

    private Integer androidPopulation;

    private Integer androidInstallCount;

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

    public Integer getPcPageView() {
        return pcPageView;
    }

    public void setPcPageView(Integer pcPageView) {
        this.pcPageView = pcPageView;
    }

    public Integer getPcUserView() {
        return pcUserView;
    }

    public void setPcUserView(Integer pcUserView) {
        this.pcUserView = pcUserView;
    }

    public Integer getHfivePopulation() {
        return hfivePopulation;
    }

    public void setHfivePopulation(Integer hfivePopulation) {
        this.hfivePopulation = hfivePopulation;
    }

    public Integer getHfivePageView() {
        return hfivePageView;
    }

    public void setHfivePageView(Integer hfivePageView) {
        this.hfivePageView = hfivePageView;
    }

    public Integer getHfiveUserView() {
        return hfiveUserView;
    }

    public void setHfiveUserView(Integer hfiveUserView) {
        this.hfiveUserView = hfiveUserView;
    }

    public Integer getIosPopulation() {
        return iosPopulation;
    }

    public void setIosPopulation(Integer iosPopulation) {
        this.iosPopulation = iosPopulation;
    }

    public Integer getIosInstallCount() {
        return iosInstallCount;
    }

    public void setIosInstallCount(Integer iosInstallCount) {
        this.iosInstallCount = iosInstallCount;
    }

    public Integer getAndroidPopulation() {
        return androidPopulation;
    }

    public void setAndroidPopulation(Integer androidPopulation) {
        this.androidPopulation = androidPopulation;
    }

    public Integer getAndroidInstallCount() {
        return androidInstallCount;
    }

    public void setAndroidInstallCount(Integer androidInstallCount) {
        this.androidInstallCount = androidInstallCount;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }
}