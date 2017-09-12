package com.hhly.smartdata.model.game.operative;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameTotalDaily {
public static final String formatStyle = "yyyy-MM-dd";
	
	private String statDate;
	private int regCount;
	private long rechargeCount;
	private double payAmount;
	private long regTotalCount;
	//当日登录用户数
	private long dau;
	private long wau;
	private long mau;
	private Date createTime;
	private Date updateTime;
	//付费率=付费用户数/DAU
	private double payPercent;
	//留存率=当日登录用户数/所有注册用户数
	private double remainPercent;
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = new SimpleDateFormat(formatStyle).format(statDate);
	}
	public int getRegCount() {
		return regCount;
	}
	public void setRegCount(int regCount) {
		this.regCount = regCount;
	}
	public long getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(long rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public long getRegTotalCount() {
		return regTotalCount;
	}
	public void setRegTotalCount(long regTotalCount) {
		this.regTotalCount = regTotalCount;
	}
	public long getDau() {
		return dau;
	}
	public void setDau(long dau) {
		this.dau = dau;
	}
	public long getWau() {
		return wau;
	}
	public void setWau(long wau) {
		this.wau = wau;
	}
	public long getMau() {
		return mau;
	}
	public void setMau(long mau) {
		this.mau = mau;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public double getPayPercent() {
		payPercent = 0;
		if(0 != dau){
			payPercent = (double)rechargeCount / dau;
		}
		return payPercent;
	}
	public double getRemainPercent() {
		remainPercent = 0;
		if(0 != regTotalCount){
			remainPercent = (double)dau / regTotalCount;
		}
		return remainPercent;
	}
	
}
