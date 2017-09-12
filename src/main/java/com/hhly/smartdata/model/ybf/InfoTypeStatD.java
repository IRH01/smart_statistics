package com.hhly.smartdata.model.ybf;

import java.util.Date;

//资讯数据类别日统计表
public class InfoTypeStatD {
   
	private Date etlDate;
	
	private  String  positionId;
	
	private  String infoTypeId;
	
	private long userCnt;
	
	private long clickCnt;
	
	private long stayCnt;
	
	private long ipCnt;
	
	private long infoCnt;
	
	private Date createDate;
	
	private Date updatedDate;

	public Date getEtlDate() {
		return etlDate;
	}

	public void setEtlDate(Date etlDate) {
		this.etlDate = etlDate;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getInfoTypeId() {
		return infoTypeId;
	}

	public void setInfoTypeId(String infoTypeId) {
		this.infoTypeId = infoTypeId;
	}

	public long getUserCnt() {
		return userCnt;
	}

	public void setUserCnt(long userCnt) {
		this.userCnt = userCnt;
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

	public long getInfoCnt() {
		return infoCnt;
	}

	public void setInfoCnt(long infoCnt) {
		this.infoCnt = infoCnt;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
}
