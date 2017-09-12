package com.hhly.smartdata.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoStatDShower {
   
	public static final String formatStyle = "yyyy-MM-dd HH:mm:ss";
	
	private String infoTypeName;
	
	//咨询的标题
	private String infoName;
	
	private String infoId;
	
	private String createDate;
	
	private long clickCnt;
	
	private long stayCnt;
	
	private long userCnt;
	
	private long ipCnt;

	public String getInfoTypeName() {
		return infoTypeName;
	}

	public void setInfoTypeName(String infoTypeName) {
		this.infoTypeName = infoTypeName;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = new SimpleDateFormat(formatStyle).format(createDate);
	}

	public long getClickCnt() {
		return clickCnt;
	}

	public void setClickCnt(long clickCnt) {
		this.clickCnt = clickCnt;
	}

	public long getStayCnt() {
		return stayCnt;
	}

	public void setStayCnt(long stayCnt) {
		this.stayCnt = stayCnt;
	}

	public long getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(long userCnt) {
		this.userCnt = userCnt;
	}

	public long getIpCnt() {
		return ipCnt;
	}

	public void setIpCnt(long ipCnt) {
		this.ipCnt = ipCnt;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
}
