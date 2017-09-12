package com.hhly.smartdata.model.authentication;

/**
 * 用户渠道关联关系
 *
 * @author wanghuang
 */
public class UserChannel{
    private Integer id;
    private Integer userId;
    private String channelId;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getChannelId(){
        return channelId;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }
}
