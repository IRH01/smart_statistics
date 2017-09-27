package com.hhly.smartdata.model.member;

import java.util.Date;

public class UserInfo{
    /**
     * 用户名
     */
    private String userId;

    /**
     * 密码
     */
    private String password;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String headIcon;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1男 2女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 所在地
     */
    private String location;

    /**
     * 0 禁用 1 未激活 2 已激活
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 身份证号码
     */
    private String idCardNo;

    /**
     * 详细地址
     */
    private String address;

    /**
     * qq号码
     */
    private String qqNumber;

    /**
     * 手机绑定状态（0未有 1绑定）
     */
    private Integer phoneStatus;

    /**
     * 邮箱绑定状态（0未有 1绑定）
     */
    private Integer emailStatus;

    /**
     * 平台id
     */
    private Integer platformId;

    /**
     * 注册类型 1 用户名 2 手机 3 邮箱 4 第三方登录 5游客身份注册 6活动创建用户
     */
    private Integer regType;

    /**
     * 支付密码
     */
    private String paypassword;

    /**
     * 来源（1pc2ios3安卓4其
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
     * 小图像
     */
    private String headIconSmall;

    /**
     * 登录注册类型 0：平台注册登录；1：qer注册登录；6：facebook注册登录；
     */
    private String loginType;

    /**
     * 是否是成年人（0未成年 1成年人）
     */
    private String isAdult;

    /**
     * 禁用开始时间
     */
    private Date forbiddenStarttime;

    /**
     * 禁用结束时间
     */
    private Date forbiddenEnttime;

    /**
     * 0中国  1美国 2泰国  3越南 4韩国 5印尼6繁体7新加坡8马来
     */
    private Integer country;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 注册ip
     */
    private String registerIp;

    /**
     * 禁用备注
     */
    private String forbiddenRemak;

    /**
     * 邀请码
     */
    private String inviteCode;

    /**
     * 来源渠道
     */
    private String channelid;

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getHeadIcon(){
        return headIcon;
    }

    public void setHeadIcon(String headIcon){
        this.headIcon = headIcon;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public Integer getSex(){
        return sex;
    }

    public void setSex(Integer sex){
        this.sex = sex;
    }

    public Integer getAge(){
        return age;
    }

    public void setAge(Integer age){
        this.age = age;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getLastLoginTime(){
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime){
        this.lastLoginTime = lastLoginTime;
    }

    public String getRealName(){
        return realName;
    }

    public void setRealName(String realName){
        this.realName = realName;
    }

    public String getIdCardNo(){
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo){
        this.idCardNo = idCardNo;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getQqNumber(){
        return qqNumber;
    }

    public void setQqNumber(String qqNumber){
        this.qqNumber = qqNumber;
    }

    public Integer getPhoneStatus(){
        return phoneStatus;
    }

    public void setPhoneStatus(Integer phoneStatus){
        this.phoneStatus = phoneStatus;
    }

    public Integer getEmailStatus(){
        return emailStatus;
    }

    public void setEmailStatus(Integer emailStatus){
        this.emailStatus = emailStatus;
    }

    public Integer getPlatformId(){
        return platformId;
    }

    public void setPlatformId(Integer platformId){
        this.platformId = platformId;
    }

    public Integer getRegType(){
        return regType;
    }

    public void setRegType(Integer regType){
        this.regType = regType;
    }

    public String getPaypassword(){
        return paypassword;
    }

    public void setPaypassword(String paypassword){
        this.paypassword = paypassword;
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

    public String getHeadIconSmall(){
        return headIconSmall;
    }

    public void setHeadIconSmall(String headIconSmall){
        this.headIconSmall = headIconSmall;
    }

    public String getLoginType(){
        return loginType;
    }

    public void setLoginType(String loginType){
        this.loginType = loginType;
    }

    public String getIsAdult(){
        return isAdult;
    }

    public void setIsAdult(String isAdult){
        this.isAdult = isAdult;
    }

    public Date getForbiddenStarttime(){
        return forbiddenStarttime;
    }

    public void setForbiddenStarttime(Date forbiddenStarttime){
        this.forbiddenStarttime = forbiddenStarttime;
    }

    public Date getForbiddenEnttime(){
        return forbiddenEnttime;
    }

    public void setForbiddenEnttime(Date forbiddenEnttime){
        this.forbiddenEnttime = forbiddenEnttime;
    }

    public Integer getCountry(){
        return country;
    }

    public void setCountry(Integer country){
        this.country = country;
    }

    public String getLastLoginIp(){
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp){
        this.lastLoginIp = lastLoginIp;
    }

    public String getRegisterIp(){
        return registerIp;
    }

    public void setRegisterIp(String registerIp){
        this.registerIp = registerIp;
    }

    public String getForbiddenRemak(){
        return forbiddenRemak;
    }

    public void setForbiddenRemak(String forbiddenRemak){
        this.forbiddenRemak = forbiddenRemak;
    }

    public String getInviteCode(){
        return inviteCode;
    }

    public void setInviteCode(String inviteCode){
        this.inviteCode = inviteCode;
    }

    public String getChannelid(){
        return channelid;
    }

    public void setChannelid(String channelid){
        this.channelid = channelid;
    }
}