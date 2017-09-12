package com.hhly.smartdata.model.game;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GameTransHourly {
	public static final String formatStyle = "HH";
	
	private String  statHour;
	private int platformId;
	private int deviceType;
	private long regAndLoginCount;
	private long regAndRechargeTimes;
	private long regAndRechargeCount;
	private long regAndRechargeAmount;
	private long actAndRechargeTimes;
	private long actAndRechargeCount;
	private long actAndRechargeAmount;
	private Date createTime;
	private Date updateTime;
	public String getStatHour() {
		return statHour;
	}
	public void setStatHour(Date statHour) {
		this.statHour = new SimpleDateFormat(formatStyle).format(statHour);
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
	public long getRegAndLoginCount() {
		return regAndLoginCount;
	}
	public void setRegAndLoginCount(long regAndLoginCount) {
		this.regAndLoginCount = regAndLoginCount;
	}
	public long getRegAndRechargeTimes() {
		return regAndRechargeTimes;
	}
	public void setRegAndRechargeTimes(long regAndRechargeTimes) {
		this.regAndRechargeTimes = regAndRechargeTimes;
	}
	public long getRegAndRechargeCount() {
		return regAndRechargeCount;
	}
	public void setRegAndRechargeCount(long regAndRechargeCount) {
		this.regAndRechargeCount = regAndRechargeCount;
	}
	public long getRegAndRechargeAmount() {
		return regAndRechargeAmount;
	}
	public void setRegAndRechargeAmount(long regAndRechargeAmount) {
		this.regAndRechargeAmount = regAndRechargeAmount;
	}
	public long getActAndRechargeTimes() {
		return actAndRechargeTimes;
	}
	public void setActAndRechargeTimes(long actAndRechargeTimes) {
		this.actAndRechargeTimes = actAndRechargeTimes;
	}
	public long getActAndRechargeCount() {
		return actAndRechargeCount;
	}
	public void setActAndRechargeCount(long actAndRechargeCount) {
		this.actAndRechargeCount = actAndRechargeCount;
	}
	public long getActAndRechargeAmount() {
		return actAndRechargeAmount;
	}
	public void setActAndRechargeAmount(long actAndRechargeAmount) {
		this.actAndRechargeAmount = actAndRechargeAmount;
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
