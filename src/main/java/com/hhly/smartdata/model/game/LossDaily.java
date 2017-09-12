package com.hhly.smartdata.model.game;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class LossDaily {
	public static final String formatStyle = "yyyy-MM-dd";
	
	private String statDate;
	private int platformId;
	private long seven;
	private double sevenPercent;
	private long fourteen;
	private double fourteenPercent;
	private long thirty;
	private double thirtyPercent;
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
	public long getSeven() {
		return seven;
	}
	public void setSeven(long seven) {
		this.seven = seven;
	}
	public long getFourteen() {
		return fourteen;
	}
	public void setFourteen(long fourteen) {
		this.fourteen = fourteen;
	}
	public long getThirty() {
		return thirty;
	}
	public void setThirty(long thirty) {
		this.thirty = thirty;
	}
	public double getSevenPercent() {
		sevenPercent = 0;
		if(0 != getCount()){
			sevenPercent = (double)seven/getCount();
		}
		return sevenPercent;
	}
	public double getFourteenPercent() {
		fourteenPercent = 0;
		if(0 != getCount()){
			fourteenPercent = (double)fourteen/getCount();
		}
		return fourteenPercent;
	}
	public double getThirtyPercent() {
		thirtyPercent = 0;
		if(0 != getCount()){
			thirtyPercent = (double)thirty/getCount();
		}
		return thirtyPercent;
	}
	public abstract long getCount();
}
