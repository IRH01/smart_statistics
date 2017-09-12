package com.hhly.smartdata.model.ybf;

import java.util.Date;

public class DimPosition {
	private String tblId;
	private String positionId;
	private String positionName;
	private String parentPositionId;
	private String originSys;
	private String originId;
	private Date createdDate;
	private String createdUser;
	private Date updatedDate;
	private String updatedUser;
	
	public String getTblId() {
		return tblId;
	}
	public void setTblId(String tblId) {
		this.tblId = tblId;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getParentPositionId() {
		return parentPositionId;
	}
	public void setParentPositionId(String parentPositionId) {
		this.parentPositionId = parentPositionId;
	}
	public String getOriginSys() {
		return originSys;
	}
	public void setOriginSys(String originSysString) {
		this.originSys = originSysString;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedUser() {
		return updatedUser;
	}
	public void setUpdatedUser(String updateUser) {
		this.updatedUser = updateUser;
	}
}
