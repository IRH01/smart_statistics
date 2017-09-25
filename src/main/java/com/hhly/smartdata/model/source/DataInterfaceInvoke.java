package com.hhly.smartdata.model.source;

import java.util.Date;

public class DataInterfaceInvoke{
    /**
     * 接口调用
     */
    private Long id;

    /**
     * 国家:0中国 1美国 2泰国 3越南 4韩国 5印尼6繁体7新加坡8马来西亚
     */
    private Integer country;

    /**
     * 账户（userId）
     */
    private String userId;

    /**
     * 平台Id:默认为玩一下平台;PC=1,移动端为31111
     */
    private Integer platformId;

    /**
     * 终端:1.pc 2.安卓 3ios 4.h5 5.其他
     */
    private Integer platformTerminal;

    /**
     * 接口类型:1.注册接口 2.充值接口
     */
    private Integer interfaceType;

    /**
     * 域名
     */
    private String websiteDomain;

    /**
     * 网站路径，不带参数,不计域名
     */
    private String urlPath;

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

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public Integer getPlatformId(){
        return platformId;
    }

    public void setPlatformId(Integer platformId){
        this.platformId = platformId;
    }

    public Integer getPlatformTerminal(){
        return platformTerminal;
    }

    public void setPlatformTerminal(Integer platformTerminal){
        this.platformTerminal = platformTerminal;
    }

    public Integer getInterfaceType(){
        return interfaceType;
    }

    public void setInterfaceType(Integer interfaceType){
        this.interfaceType = interfaceType;
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

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}