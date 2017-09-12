package com.hhly.smartdata.model.game.operative;

/**
 * 会员注册渠道统计汇总
 *
 * @author wanghuang
 */
public class VipRegChannelStatSum{
    //渠道ID
    private String channelId;
    //渠道名称
    private String channelName;
    private long pvCnt;
    private long uvCnt;
    private long ipCnt;
    //付费用户数
    private long payUCnt;
    //注册用户数
    private long regUCnt;
    //首冲金额
    private double payFirstAmount;
    //充值总额
    private double payAmount;

    //总注册用户数
    private long totalRegUCnt;
    //总付费用户数
    private long totalPayUCnt;
    //渠道注册用户数权重
    private double regUCntRate;
    //渠道付费用户数权重
    private double payUCntRate;

    public String getChannelId(){
        return channelId;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }

    public String getChannelName(){
        return channelName;
    }

    public void setChannelName(String channelName){
        this.channelName = channelName;
    }

    public long getPvCnt(){
        return pvCnt;
    }

    public void setPvCnt(long pvCnt){
        this.pvCnt = pvCnt;
    }

    public long getUvCnt(){
        return uvCnt;
    }

    public void setUvCnt(long uvCnt){
        this.uvCnt = uvCnt;
    }

    public long getIpCnt(){
        return ipCnt;
    }

    public void setIpCnt(long ipCnt){
        this.ipCnt = ipCnt;
    }

    public long getPayUCnt(){
        return payUCnt;
    }

    public void setPayUCnt(long payUCnt){
        this.payUCnt = payUCnt;
    }

    public long getRegUCnt(){
        return regUCnt;
    }

    public void setRegUCnt(long regUCnt){
        this.regUCnt = regUCnt;
    }

    public double getPayFirstAmount(){
        return payFirstAmount;
    }

    public void setPayFirstAmount(double payFirstAmount){
        this.payFirstAmount = payFirstAmount;
    }

    public double getPayAmount(){
        return payAmount;
    }

    public void setPayAmount(double payAmount){
        this.payAmount = payAmount;
    }

    public long getTotalRegUCnt(){
        return totalRegUCnt;
    }

    public void setTotalRegUCnt(long totalRegUCnt){
        this.totalRegUCnt = totalRegUCnt;
    }

    public long getTotalPayUCnt(){
        return totalPayUCnt;
    }

    public void setTotalPayUCnt(long totalPayUCnt){
        this.totalPayUCnt = totalPayUCnt;
    }

    public double getRegUCntRate(){
        if(totalRegUCnt == 0){
            return 0;
        }
        regUCntRate = (double) regUCnt / totalRegUCnt;
        return regUCntRate;
    }

    public double getPayUCntRate(){
        if(totalPayUCnt == 0){
            return 0;
        }
        payUCntRate = (double) payUCnt / totalPayUCnt;
        return payUCntRate;
    }
}
