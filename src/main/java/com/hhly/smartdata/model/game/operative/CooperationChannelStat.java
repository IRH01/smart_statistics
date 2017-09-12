package com.hhly.smartdata.model.game.operative;

import java.util.Date;

public class CooperationChannelStat {
	private Date statDate;
	private String channelId;
	private String channelName;
	private int platformId;
	private String platformName;
	private int regUCnt;
	private int payUCnt;
	private double payAmount;
	private double consumeAmount;
	private double accountBalance;
	
	public Date getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = statDate;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
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
	public int getRegUCnt() {
		return regUCnt;
	}
	public void setRegUCnt(int regUCnt) {
		this.regUCnt = regUCnt;
	}
	public int getPayUCnt() {
		return payUCnt;
	}
	public void setPayUCnt(int payUCnt) {
		this.payUCnt = payUCnt;
	}
	public double getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(double payAmount) {
		this.payAmount = payAmount;
	}
	public double getConsumeAmount() {
		return consumeAmount;
	}
	public void setConsumeAmount(double consumeAmount) {
		this.consumeAmount = consumeAmount;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
}
