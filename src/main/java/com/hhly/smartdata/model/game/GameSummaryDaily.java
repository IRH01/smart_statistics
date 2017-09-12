package com.hhly.smartdata.model.game;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameSummaryDaily {
	public static final String formatStyle = "yyyy-MM-dd";
	
	private String statDate;
	private int platformId;
	private int deviceType;
	private long registerCount;
	private long activeCount;
	private long payTimes;
	private long payCount;
	private long payAmount;
	private Date createTime;
	private Date updateTime;
	
	public String getStatDate() {
		return statDate;
	}
	public void setStatDate(Date statDate) {
		this.statDate = new SimpleDateFormat(formatStyle).format(statDate);
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public long getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(long registerCount) {
		this.registerCount = registerCount;
	}
	public long getActiveCount() {
		return activeCount;
	}
	public void setActiveCount(long activeCount) {
		this.activeCount = activeCount;
	}
	public long getPayTimes() {
		return payTimes;
	}
	public void setPayTimes(long payTimes) {
		this.payTimes = payTimes;
	}
	public long getPayCount() {
		return payCount;
	}
	public void setPayCount(long payCount) {
		this.payCount = payCount;
	}
	public long getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
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
}
