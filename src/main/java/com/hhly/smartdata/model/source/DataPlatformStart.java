package com.hhly.smartdata.model.source;

import java.util.Date;

public class DataPlatformStart{
    /**
     * 平台App启动日志
     */
    private Long id;

    /**
     * 国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚
     */
    private Integer country = 0;

    /**
     * 手机唯一号（MAC地址/imei）
     */
    private String uniqueNo = "";

    /**
     * 账户(userId)
     */
    private String userId = "";

    /**
     * 终端:2.安卓 3.ios
     */
    private Integer platformTerminal = 0;

    /**
     * 经度
     */
    private Double longitude = 0D;

    /**
     * 纬度
     */
    private Double latitude = 0D;

    /**
     * Ip地址
     */
    private String ip="";

    /**
     * 创建日期
     */
    private Date createTime;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Integer getCountry(){
        return country;
    }

    public void setCountry(Integer country){
        this.country = country;
    }

    public String getUniqueNo(){
        return uniqueNo;
    }

    public void setUniqueNo(String uniqueNo){
        this.uniqueNo = uniqueNo;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public Integer getPlatformTerminal(){
        return platformTerminal;
    }

    public void setPlatformTerminal(Integer platformTerminal){
        this.platformTerminal = platformTerminal;
    }

    public Double getLongitude(){
        return longitude;
    }

    public void setLongitude(Double longitude){
        this.longitude = longitude;
    }

    public Double getLatitude(){
        return latitude;
    }

    public void setLatitude(Double latitude){
        this.latitude = latitude;
    }

    public String getIp(){
        return ip;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}