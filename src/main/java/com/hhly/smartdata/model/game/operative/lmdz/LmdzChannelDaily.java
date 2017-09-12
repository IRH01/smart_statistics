package com.hhly.smartdata.model.game.operative.lmdz;

import java.util.Date;

public class LmdzChannelDaily{
    private String channelId;
    private String osType;
    private String userId;
    private Date regDate;
    private String grade;
    private double rechargeAmount;
    private int inning;

    private String osid;
    private String osname;

    public String getChannelId(){
        return channelId;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public Date getRegDate(){
        return regDate;
    }

    public void setRegDate(Date regDate){
        this.regDate = regDate;
    }

    public String getGrade(){
        return grade;
    }

    public void setGrade(String grade){
        this.grade = grade;
    }

    public double getRechargeAmount(){
        return rechargeAmount;
    }

    public void setRechargeAmount(double rechargeAmount){
        this.rechargeAmount = rechargeAmount;
    }

    public int getInning(){
        return inning;
    }

    public void setInning(int inning){
        this.inning = inning;
    }

    public String getOsid(){
        return osid;
    }

    public void setOsid(String osid){
        this.osid = osid;
    }

    public String getOsname(){
        return osname;
    }

    public void setOsname(String osname){
        this.osname = osname;
    }

    public String getOsType(){
        return osType;
    }

    public void setOsType(String osType){
        this.osType = osType;
    }
}
