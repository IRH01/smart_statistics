package com.hhly.smartdata.model.ybf;

import java.util.Date;

//位置统计
public class PositionStatD{

    private Date etlDate;

    private String domainId;

    private String positionId;

    private long clickCnt;

    private long stayCnt;

    private long ipCnt;

    private Date createdDate;

    private Date updatedDate;

    public Date getEtlDate(){
        return etlDate;
    }

    public void setEtlDate(Date etlDate){
        this.etlDate = etlDate;
    }

    public String getDomainId(){
        return domainId;
    }

    public void setDomainId(String domainId){
        this.domainId = domainId;
    }

    public String getPositionId(){
        return positionId;
    }

    public void setPositionId(String positionId){
        this.positionId = positionId;
    }

    public long getClickCnt(){
        return clickCnt;
    }

    public void setClickCnt(long clickCnt){
        this.clickCnt = clickCnt;
    }

    public long getStayCnt(){
        return stayCnt;
    }

    public void setStayCnt(long stayCnt){
        this.stayCnt = stayCnt;
    }

    public long getIpCnt(){
        return ipCnt;
    }

    public void setIpCnt(long ipCnt){
        this.ipCnt = ipCnt;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate(){
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate){
        this.updatedDate = updatedDate;
    }

}
