package com.hhly.smartdata.model.partner;

/**
 * 代理推广链接汇总
 *
 * @author wanghuang
 */
public class PartnerRefWebsiteSum{

    /**
     * 来源地址
     */
    private String referrerWebsiteUrl;
    /**
     * 推广地址
     */
    private String promoteUrl;
    /**
     * 推广编码
     */
    private String partnerNo;
    /**
     * 代理姓名
     */
    private String realName;
    /**
     * 代理账号
     */
    private String userId;
    /**
     * pv
     */
    private long pvCount;
    /**
     * uv
     */
    private long uvCount;
    /**
     * ip
     */
    private long ipCount;
    /**
     * 注册用户数
     */
    private int registCount;
    /**
     * 付费用户数
     */
    private int payCount;
    /**
     * 跳出率=单页面访问次数/总访问次数
     */
    private double outRate;
    /**
     * 平均访问时长=总访问时长/访问次数
     */
    private double avgDuration;

    /**
     * 访问总时长
     */
    private long totalDuration;
    /**
     * 访问次数
     */
    private long accessTimes;
    /**
     * 单页面访问次数
     */
    private long onePageAccessTimes;

    public String getReferrerWebsiteUrl(){
        return referrerWebsiteUrl;
    }

    public void setReferrerWebsiteUrl(String referrerWebsiteUrl){
        this.referrerWebsiteUrl = referrerWebsiteUrl;
    }

    public String getPromoteUrl(){
        return promoteUrl;
    }

    public void setPromoteUrl(String promoteUrl){
        this.promoteUrl = promoteUrl;
    }

    public String getPartnerNo(){
        return partnerNo;
    }

    public void setPartnerNo(String partnerNo){
        this.partnerNo = partnerNo;
    }

    public String getRealName(){
        return realName;
    }

    public void setRealName(String realName){
        this.realName = realName;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public long getPvCount(){
        return pvCount;
    }

    public void setPvCount(long pvCount){
        this.pvCount = pvCount;
    }

    public long getUvCount(){
        return uvCount;
    }

    public void setUvCount(long uvCount){
        this.uvCount = uvCount;
    }

    public long getIpCount(){
        return ipCount;
    }

    public void setIpCount(long ipCount){
        this.ipCount = ipCount;
    }

    public int getRegistCount(){
        return registCount;
    }

    public void setRegistCount(int registCount){
        this.registCount = registCount;
    }

    public int getPayCount(){
        return payCount;
    }

    public void setPayCount(int payCount){
        this.payCount = payCount;
    }

    public long getTotalDuration(){
        return totalDuration;
    }

    public void setTotalDuration(long totalDuration){
        this.totalDuration = totalDuration;
    }

    public long getAccessTimes(){
        return accessTimes;
    }

    public void setAccessTimes(long accessTimes){
        this.accessTimes = accessTimes;
    }

    public long getOnePageAccessTimes(){
        return onePageAccessTimes;
    }

    public void setOnePageAccessTimes(long onePageAccessTimes){
        this.onePageAccessTimes = onePageAccessTimes;
    }

    public double getOutRate(){
        if(accessTimes != 0){
            outRate = (double) onePageAccessTimes / accessTimes;
        }
        return outRate;
    }

    public double getAvgDuration(){
        if(accessTimes != 0){
            avgDuration = (double) totalDuration / accessTimes;
        }
        return avgDuration;
    }
}
