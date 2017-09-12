package com.hhly.smartdata.model.game.operative;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserRechargeSummary{
    public static final String formatStyle = "yyyy-MM-dd HH:mm:ss";

    private int platformId;
    private int serverId;
    private int areaId;
    private String userId;
    private String nickName;
    private String phone;
    private String email;
    private String regTime;
    private double totalAmount;
    private String platformName;
    private String serverName;
    private String channelId;
    private String channelName;
    private String countryName;
    //终端类型
    private String platformTerminalName;

    public int getPlatformId(){
        return platformId;
    }

    public void setPlatformId(int platformId){
        this.platformId = platformId;
    }

    public int getServerId(){
        return serverId;
    }

    public void setServerId(int serverId){
        this.serverId = serverId;
    }

    public int getAreaId(){
        return areaId;
    }

    public void setAreaId(int areaId){
        this.areaId = areaId;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getNickName(){
        return nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getRegTime(){
        return regTime;
    }

    public void setRegTime(Date regTime){
        this.regTime = new SimpleDateFormat(formatStyle).format(regTime);
    }

    public double getTotalAmount(){
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount){
        this.totalAmount = totalAmount;
    }

    public String getPlatformName(){
        return platformName;
    }

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }

    public String getServerName(){
        return serverName;
    }

    public void setServerName(String serverName){
        this.serverName = serverName;
    }

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

    public String getCountryName(){
        return countryName;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public String getPlatformTerminalName(){
        return platformTerminalName;
    }

    public void setPlatformTerminalName(String platformTerminalName){
        this.platformTerminalName = platformTerminalName;
    }
}
