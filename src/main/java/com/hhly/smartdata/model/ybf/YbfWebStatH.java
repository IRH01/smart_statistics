package com.hhly.smartdata.model.ybf;

import java.util.Date;

public class YbfWebStatH{

    private Date etlDate;

    private String urlId;

    private long clickCnt;

    private long userCnt;

    private long ipCnt;

    private long stayCnt;

    private long totalUserCnt;

    private Date createDate;

    private Date updateDate;

    public Date getEtlDate(){
        return etlDate;
    }

    public void setEtlDate(Date etlDate){
        this.etlDate = etlDate;
    }

    public String getUrlId(){
        return urlId;
    }

    public void setUrlId(String urlId){
        this.urlId = urlId;
    }

    public long getClickCnt(){
        return clickCnt;
    }

    public void setClickCnt(long clickCnt){
        this.clickCnt = clickCnt;
    }

    public long getUserCnt(){
        return userCnt;
    }

    public void setUserCnt(long userCnt){
        this.userCnt = userCnt;
    }

    public long getIpCnt(){
        return ipCnt;
    }

    public void setIpCnt(long ipCnt){
        this.ipCnt = ipCnt;
    }

    public long getStayCnt(){
        return stayCnt;
    }

    public void setStayCnt(long stayCnt){
        this.stayCnt = stayCnt;
    }

    public long getTotalUserCnt(){
        return totalUserCnt;
    }

    public void setTotalUserCnt(long totalUserCnt){
        this.totalUserCnt = totalUserCnt;
    }

    public Date getCreateDate(){
        return createDate;
    }

    public void setCreateDate(Date createDate){
        this.createDate = createDate;
    }

    public Date getUpdateDate(){
        return updateDate;
    }

    public void setUpdateDate(Date updateDate){
        this.updateDate = updateDate;
    }

}
