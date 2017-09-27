package com.hhly.smartdata.model;

import java.util.Date;

public class BaseModel{

    private Date startDate;

    private Date endDate;

    private Integer intervalCountr;

    public Date getStartDate(){
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public Integer getIntervalCountr(){
        return intervalCountr;
    }

    public void setIntervalCountr(Integer intervalCountr){
        this.intervalCountr = intervalCountr;
    }
}
