package com.hhly.smartdata.model;

/**
 * 渠道平台推广关系:1个渠道只能对应1个平台
 *
 * @author wanghuang
 */
public class ChannelPlatform{
    /**
     * 渠道ID
     */
    String channelId;
    /**
     * 渠道名称
     */
    String channelName;
    /**
     * 平台ID
     */
    String platformId;
    /**
     * 平台名称
     */
    String platformName;

    public String getChannelId(){
        return channelId;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }

    public String getChannelName(){
        return channelName;
    }

    public void setChannelName(String channelName){
        this.channelName = channelName;
    }

    public String getPlatformId(){
        return platformId;
    }

    public void setPlatformId(String platformId){
        this.platformId = platformId;
    }

    public String getPlatformName(){
        return platformName;
    }

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }
}