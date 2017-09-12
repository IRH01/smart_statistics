package com.hhly.smartdata.model.game;

public class GameStatDaily extends GameSummaryDaily{

    private long regAndLoginCount;
    private long regAndRechargeCount;
    private String deviceName;
    //Average Revenue Per User（每用户平均收入）
    private double arpu;

    /**
     * 注册用户转化率
     */
    private double regConversionPercent;
    /**
     * 注册充值转化率
     */
    private double regAndRechargeCvsPercent;

    public long getRegAndLoginCount(){
        return regAndLoginCount;
    }

    public void setRegAndLoginCount(long regAndLoginCount){
        this.regAndLoginCount = regAndLoginCount;
    }

    public long getRegAndRechargeCount(){
        return regAndRechargeCount;
    }

    public void setRegAndRechargeCount(long regAndRechargeCount){
        this.regAndRechargeCount = regAndRechargeCount;
    }

    public String getDeviceName(){
        return deviceName;
    }

    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }

    public double getRegConversionPercent(){
        regConversionPercent = 0;
        if(this.getRegisterCount() != 0){
            regConversionPercent = (double) this.regAndLoginCount / this.getRegisterCount();
        }
        return regConversionPercent;
    }

    public double getRegAndRechargeCvsPercent(){
        regAndRechargeCvsPercent = 0;
        if(this.getRegisterCount() != 0){
            regAndRechargeCvsPercent = (double) this.regAndRechargeCount / this.getRegisterCount();
        }
        return regAndRechargeCvsPercent;
    }

    public double getArpu(){
        arpu = 0;
        if(super.getActiveCount() != 0){
            arpu = (double) super.getPayAmount() / super.getActiveCount();
        }
        return arpu;
    }
}
