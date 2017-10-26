package com.hhly.smartdata.model.authentication;

import com.google.common.collect.Maps;

import java.util.Date;
import java.util.Map;

public class User{

    public static final String ON = "0";//启用
    public static final String OFF = "1";//禁用
    public static final String USER_ADMIN = "1";
    public static final String USER_CP = "2";
    public static final String USER_CHANNEL = "3";


    public static final Map<String, String> statusMap = Maps.newLinkedHashMap();

    static{
        statusMap.put(ON, "启用");
        statusMap.put(OFF, "禁用");
    }

    private Integer id;
    private String username;
    private String passwd;
    private String userStatus; //0:启用 1:禁用
    private String userType;  //1:内部  2:CP  3：渠道
    private Date createTime;
    private String locale;        //语言
    private String newPassword;


    public String getUsername(){
        return username;
    }


    public void setUsername(String username){
        this.username = username == null ? null : username.trim();
    }

    public String getPasswd(){
        return passwd;
    }

    public void setPasswd(String passwd){
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getUserStatus(){
        return userStatus;
    }

    public void setUserStatus(String userStatus){
        this.userStatus = userStatus;
    }

    public String getUserType(){
        return userType;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getNewPassword(){
        return newPassword;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public String getLocale(){
        return locale;
    }

    public void setLocale(String locale){
        this.locale = locale;
    }

    @Override
    public String toString(){
        return "User [id=" + id + ", username=" + username
                + ", passwd=" + passwd + ", userStatus=" + userStatus
                + ", userType=" + userType + ", createTime=" + createTime
                + ", locale=" + locale + ", newPassword=" + newPassword + "]";
    }
}