package com.hhly.smartdata.model.source;

import java.util.Date;

public class DataGameStart{
    /**
     * 点击平台按钮‘开始游戏’
     */
    private Long id;

    /**
     * 国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚
     */
    private Integer country = 0;

    /**
     * 客户端标识,识别游客访问
     */
    private String clientIdentity = "";

    /**
     * 账户（userId）
     */
    private String userId = "";

    /**
     * 终端:1.pc 2.安卓 3ios 4.h5 5.其他
     */
    private Integer platformTerminal = 0;

    /**
     * 平台名称
     */
    private String platformName = "";

    /**
     * 平台Id:默认为玩一下平台;PC=1,移动端为31111
     */
    private Integer platformId = 0;

    /**
     * 域名
     */
    private String websiteDomain = "";

    /**
     * 网站路径，不带参数,不计域名
     */
    private String urlPath = "";

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

    public String getClientIdentity(){
        return clientIdentity;
    }

    public void setClientIdentity(String clientIdentity){
        this.clientIdentity = clientIdentity;
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

    public Integer getPlatformId(){
        return platformId;
    }

    public void setPlatformId(Integer platformId){
        this.platformId = platformId;
    }

    public String getWebsiteDomain(){
        return websiteDomain;
    }

    public void setWebsiteDomain(String websiteDomain){
        this.websiteDomain = websiteDomain;
    }

    public String getUrlPath(){
        return urlPath;
    }

    public void setUrlPath(String urlPath){
        this.urlPath = urlPath;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public String getPlatformName(){
        return platformName;
    }

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;

    }

}