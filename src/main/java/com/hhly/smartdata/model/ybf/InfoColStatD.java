package com.hhly.smartdata.model.ybf;

import java.util.Date;

public class InfoColStatD{

    private Date etlDate;

    private String domainId;

    private String infoTypeId;

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

    public String getDomainId(){
        return domainId;
    }

    public void setDomainId(String domainId){
        this.domainId = domainId;
    }

    public String getInfoTypeId(){
        return infoTypeId;
    }

    public void setInfoTypeId(String infoTypeId){
        this.infoTypeId = infoTypeId;
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
