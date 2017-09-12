package com.hhly.smartdata.model.partner;

import java.util.Date;

/**
 * 乐盈电竞投注数据
 * @author wanghuang
 *
 */
public class LydjBettingLog {
	private String platformId;
	private String userId;
	private Date bettingTime;
	private Date settleTime;
	private double bettingAmount;
	private String bettingOrderNo;
	//货币
	private String currency;
	private double settleAmount;
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getBettingTime() {
		return bettingTime;
	}
	public void setBettingTime(Date bettingTime) {
		this.bettingTime = bettingTime;
	}
	public Date getSettleTime() {
		return settleTime;
	}
	public void setSettleTime(Date settleTime) {
		this.settleTime = settleTime;
	}
	public double getBettingAmount() {
		return bettingAmount;
	}
	public void setBettingAmount(double bettingAmount) {
		this.bettingAmount = bettingAmount;
	}
	public String getBettingOrderNo() {
		return bettingOrderNo;
	}
	public void setBettingOrderNo(String bettingOrderNo) {
		this.bettingOrderNo = bettingOrderNo;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(double settleAmount) {
		this.settleAmount = settleAmount;
	}
}
