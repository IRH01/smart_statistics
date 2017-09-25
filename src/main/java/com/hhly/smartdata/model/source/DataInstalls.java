package com.hhly.smartdata.model.source;

import java.math.BigDecimal;
import java.util.Date;

public class DataInstalls{
    /**
     * 手机安装量
     */
    private Integer id;

    /**
     * 手机唯一号（MAC地址/imei）
     */
    private String uniqueNo;

    /**
     * 终端:2.安卓 3.ios
     */
    private Integer platformTerminal;

    /**
     * 国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚
     */
    private Integer country;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建日期
     */
    private Date createTime;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUniqueNo(){
        return uniqueNo;
    }

    public void setUniqueNo(String uniqueNo){
        this.uniqueNo = uniqueNo;
    }

    public Integer getPlatformTerminal(){
        return platformTerminal;
    }

    public void setPlatformTerminal(Integer platformTerminal){
        this.platformTerminal = platformTerminal;
    }

    public Integer getCountry(){
        return country;
    }

    public void setCountry(Integer country){
        this.country = country;
    }

    public BigDecimal getLongitude(){
        return longitude;
    }

    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }

    public BigDecimal getLatitude(){
        return latitude;
    }

    public void setLatitude(BigDecimal latitude){
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