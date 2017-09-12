package com.hhly.smartdata.model.game.operative;

import java.util.Date;

/**
 * 会员注册渠道明细统计
 * @author wanghuang
 *
 */
public class VipRegChannelStatDetail {
	//注册来源ID
	private int platformId;
	//注册来源名称
	private String platformName;
	//会员userId
	private String userId;
	//登录类型：登录类型 0：平台注册登录；1：QQ注册登录；2：新浪微博注册登录；3：微信注册登录；4：支付宝注册登录；5：twitter注册登录；6：Facebook注册登录
	private String loginType;
	//用户昵称
	private String nickName;
	//手机
	private String phone;
	//邮箱
	private String email;
	//注册时间
	private Date createTime;
	//首冲金额
	private double payFirstAmount;
	
	//首冲平台ID
	private int payPlatformId;
	//首冲项目
	private String payPlatformName;
	//首冲时间
	private Date payDate;
	//充值总额
	private double payAmount;
	//是否是直属会员：0、是 1、否
	private int isVip;
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public double getPayFirstAmount() {
		return payFirstAmount;
	}
	public void setPayFirstAmount(double payFirstAmount) {
		this.payFirstAmount = payFirstAmount;
	}
	public int getPayPlatformId() {
		return payPlatformId;
	}
	public void setPayPlatformId(int payPlatformId) {
		this.payPlatformId = payPlatformId;
	}
	public String getPayPlatformName() {
		return payPlatformName;
	}
	public void setPayPlatformName(String payPlatformName) {
		this.payPlatformName = payPlatformName;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public int getIsVip() {
		return isVip;
	}
	public void setIsVip(int isVip) {
		this.isVip = isVip;
	}
}
