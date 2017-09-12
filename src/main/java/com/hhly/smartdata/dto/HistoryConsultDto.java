package com.hhly.smartdata.dto;

public class HistoryConsultDto{

    private long consultCount;

    private String date;

    public long getConsultCount(){
        return consultCount;
    }

    public void setConsultCount(long consultCount){
        this.consultCount = consultCount;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}
