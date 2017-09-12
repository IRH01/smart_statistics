package com.hhly.smartdata.model.game.operative.lmdz;


public class LmdzChannelDailySum{
    private int userCount;
    private double rechargeAmountSum;
    private int inningSum;

    public int getUserCount(){
        return userCount;
    }

    public void setUserCount(int userCount){
        this.userCount = userCount;
    }

    public double getRechargeAmountSum(){
        return rechargeAmountSum;
    }

    public void setRechargeAmountSum(double rechargeAmountSum){
        this.rechargeAmountSum = rechargeAmountSum;
    }

    public int getInningSum(){
        return inningSum;
    }

    public void setInningSum(int inningSum){
        this.inningSum = inningSum;
    }
}
