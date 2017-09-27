package com.hhly.smartdata.model.member;

import java.util.Date;

public class LoginTrack{
    /**
     * 登录日志
     */
    private Long id;

    /**
     * 登录用户名
     */
    private String userName;

    /**
     * 登录结果 0 失败 1 成功
     */
    private Integer loginResult;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录ip地址
     */
    private String loginIp;

    /**
     * 系统类型
     */
    private Integer osType;

    /**
     * 版本编号
     */
    private Integer versionCode;

    /**
     * 版本名称
     */
    private String versionName;

    /**
     * 平台id
     */
    private Integer platformid;

    /**
     * 具体平台下面的区（目前此字段保留 没意义）
     */
    private Integer gameareaId;

    /**
     * 服表 主键 id
     */
    private Long serviceId;

    /**
     * 登录ip所属地区
     */
    private String loginIpArea;

    /**
     * 布尔型：是否独立账户登录(1是|0否)
     */
    private Integer isThird;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public Integer getLoginResult(){
        return loginResult;
    }

    public void setLoginResult(Integer loginResult){
        this.loginResult = loginResult;
    }

    public Date getLoginTime(){
        return loginTime;
    }

    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }

    public String getLoginIp(){
        return loginIp;
    }

    public void setLoginIp(String loginIp){
        this.loginIp = loginIp;
    }

    public Integer getOsType(){
        return osType;
    }

    public void setOsType(Integer osType){
        this.osType = osType;
    }

    public Integer getVersionCode(){
        return versionCode;
    }

    public void setVersionCode(Integer versionCode){
        this.versionCode = versionCode;
    }

    public String getVersionName(){
        return versionName;
    }

    public void setVersionName(String versionName){
        this.versionName = versionName;
    }

    public Integer getPlatformid(){
        return platformid;
    }

    public void setPlatformid(Integer platformid){
        this.platformid = platformid;
    }

    public Integer getGameareaId(){
        return gameareaId;
    }

    public void setGameareaId(Integer gameareaId){
        this.gameareaId = gameareaId;
    }

    public Long getServiceId(){
        return serviceId;
    }

    public void setServiceId(Long serviceId){
        this.serviceId = serviceId;
    }

    public String getLoginIpArea(){
        return loginIpArea;
    }

    public void setLoginIpArea(String loginIpArea){
        this.loginIpArea = loginIpArea;
    }

    public Integer getIsThird(){
        return isThird;
    }

    public void setIsThird(Integer isThird){
        this.isThird = isThird;
    }
}