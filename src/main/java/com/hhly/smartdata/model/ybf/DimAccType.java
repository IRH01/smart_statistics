package com.hhly.smartdata.model.ybf;

import java.util.Date;

public class DimAccType {

	private String tblId;
	private String accTypeId;
	private String accTypeName;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;

	public String getTblId() {
		return tblId;
	}

	public void setTblId(String tblId) {
		this.tblId = tblId;
	}

	public String getAccTypeId() {
		return accTypeId;
	}

	public void setAccTypeId(String accTypeId) {
		this.accTypeId = accTypeId;
	}

	public String getAccTypeName() {
		return accTypeName;
	}

	public void setAccTypeName(String accTypeName) {
		this.accTypeName = accTypeName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
