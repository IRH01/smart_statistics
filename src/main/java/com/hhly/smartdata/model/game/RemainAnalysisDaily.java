package com.hhly.smartdata.model.game;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RemainAnalysisDaily {
	public static final String formatStyle = "yyyy-MM-dd";
	
	private String statDate;
	private int platformId;
	private long registerCount;
	private long one;
	double onePercent;
	private long two;
	private double twoPercent;
	private long three;
	private double threePercent;
	private long four;
	private double fourPercent;
	private long five;
	private double fivePercent;
	private long six;
	private double sixPercent;
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
	public long getRegisterCount() {
		return registerCount;
	}
	public void setRegisterCount(long registerCount) {
		this.registerCount = registerCount;
	}
	public long getOne() {
		return one;
	}
	public void setOne(long one) {
		this.one = one;
	}
	public long getTwo() {
		return two;
	}
	public void setTwo(long two) {
		this.two = two;
	}
	public long getThree() {
		return three;
	}
	public void setThree(long three) {
		this.three = three;
	}
	public long getFour() {
		return four;
	}
	public void setFour(long four) {
		this.four = four;
	}
	public long getFive() {
		return five;
	}
	public void setFive(long five) {
		this.five = five;
	}
	public long getSix() {
		return six;
	}
	public void setSix(long six) {
		this.six = six;
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
	public double getOnePercent() {
		onePercent = 0;
		if(0 != registerCount){
			onePercent = (double)one/registerCount;
		}
		return onePercent;
	}
	public double getTwoPercent() {
		twoPercent = 0;
		if(0 != registerCount){
			twoPercent = (double)two/registerCount;
		}
		return twoPercent;
	}
	public double getThreePercent() {
		threePercent = 0;
		if(0 != registerCount){
			threePercent = (double)three/registerCount;
		}
		return threePercent;
	}
	public double getFourPercent() {
		fourPercent = 0;
		if(0 != registerCount){
			fourPercent = (double)four/registerCount;
		}
		return fourPercent;
	}
	public double getFivePercent() {
		fivePercent = 0;
		if(0 != registerCount){
			fivePercent = (double)five/registerCount;
		}
		return fivePercent;
	}
	public double getSixPercent() {
		sixPercent = 0;
		if(0 != registerCount){
			sixPercent = (double)six/registerCount;
		}
		return sixPercent;
	}
	public double getSevenPercent() {
		sevenPercent = 0;
		if(0 != registerCount){
			sevenPercent = (double)seven/registerCount;
		}
		return sevenPercent;
	}
	public double getFourteenPercent() {
		fourteenPercent = 0;
		if(0 != registerCount){
			fourteenPercent = (double)fourteen/registerCount;
		}
		return fourteenPercent;
	}
	public double getThirtyPercent() {
		thirtyPercent = 0;
		if(0 != registerCount){
			thirtyPercent = (double)thirty/registerCount;
		}
		return thirtyPercent;
	}
}
