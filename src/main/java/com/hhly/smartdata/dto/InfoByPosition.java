package com.hhly.smartdata.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoByPosition {

	public static final String formatStyle = "yyyy-MM-dd HH:mm:ss";

	private String infoId;

	private String domainId;

	private String positionId;

	private String positionName;

	private String infoName;

	private long clickCnt;

	private long stayCnt;

	private long ipCnt;

	private String updateDate;

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getInfoName() {
		return infoName;
	}

	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}

	public String getInfoId() {
		return infoId;
	}

	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
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

	public long getIpCnt() {
		return ipCnt;
	}

	public void setIpCnt(long ipCnt) {
		this.ipCnt = ipCnt;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = new SimpleDateFormat(formatStyle).format(updateDate);
	}

}
