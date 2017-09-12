package com.hhly.smartdata.dto;

public class PositionByInfo{

    private String infoId;

    private String positionId;

    private String domainId;

    private String positionName;

    private long clickCnt;

    private long stayCnt;

    private long ipCnt;

    public String getInfoId(){
        return infoId;
    }

    public void setInfoId(String infoId){
        this.infoId = infoId;
    }

    public String getPositionId(){
        return positionId;
    }

    public void setPositionId(String positionId){
        this.positionId = positionId;
    }

    public String getPositionName(){
        return positionName;
    }

    public void setPositionName(String positionName){
        this.positionName = positionName;
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

    public String getDomainId(){
        return domainId;
    }

    public void setDomainId(String domainId){
        this.domainId = domainId;
    }


}
