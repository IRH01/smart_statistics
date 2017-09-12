package com.hhly.smartdata.model.ybf;

import java.util.Date;

//资讯数据域名日统计表
public class InfoDomainStatD{

    private Date etlDate;

    private String infoTypeId;

    private String positionId;

    private long userCnt;

    private long clickCnt;

    private long stayCnt;

    private long ipCnt;

    private long infoCnt;

    private Date createDate;

    private Date updateDate;

    public Date getEtlDate(){
        return etlDate;
    }

    public void setEtlDate(Date etlDate){
        this.etlDate = etlDate;
    }

    public String getInfoTypeId(){
        return infoTypeId;
    }

    public void setInfoTypeId(String infoTypeId){
        this.infoTypeId = infoTypeId;
    }

    public String getPositionId(){
        return positionId;
    }

    public void setPositionId(String positionId){
        this.positionId = positionId;
    }

    public long getUserCnt(){
        return userCnt;
    }

    public void setUserCnt(long userCnt){
        this.userCnt = userCnt;
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

    public long getInfoCnt(){
        return infoCnt;
    }

    public void setInfoCnt(long infoCnt){
        this.infoCnt = infoCnt;
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
