package com.hhly.smartdata.dto;

public class PositionStatDShower {
	
	private String positionId;
	
	private String positionName;
	
	private long clickCnt;
	
	private long stayCnt;
	
	private long ipCnt;

	private long infoCnt;

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
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

	public long getIpcnt() {
		return ipCnt;
	}

	public void setIpcnt(long ipCnt) {
		this.ipCnt = ipCnt;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public long getInfoCnt() {
		return infoCnt;
	}

	public void setInfoCnt(long infoCnt) {
		this.infoCnt = infoCnt;
	}
}
