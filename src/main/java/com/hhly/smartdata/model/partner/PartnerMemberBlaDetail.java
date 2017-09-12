package com.hhly.smartdata.model.partner;

import java.util.Date;


/**
 * 合作伙伴游戏类别佣金收入明细
 * @author wanghuang
 *
 */
public class PartnerMemberBlaDetail {
	//月份
	private Date monthId;
	//合作伙伴用户ID
	private String partnerUserId;
	//游戏类别ID
	private int gameTypeId;
	//游戏类型名称
	private String gameTypeName;
	//终端类型
	private int  platformType;
	//终端类型名称
	private String platformTypeName;
	//返佣模式
	private int rackBackModel;
	//佣金计算等级
	private String commissionLevel;
	//佣金计算比例
	private double  commissionPercent;
	//佣金计算金额
	private double commissionAmount;
	//活动金额
	private double activityAmount;
	public Date getMonthId() {
		return monthId;
	}
	public void setMonthId(Date monthId) {
		this.monthId = monthId;
	}
	public String getPartnerUserId() {
		return partnerUserId;
	}
	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}
	public int getGameTypeId() {
		return gameTypeId;
	}
	public void setGameTypeId(int gameTypeId) {
		this.gameTypeId = gameTypeId;
	}
	public String getGameTypeName() {
		return gameTypeName;
	}
	public void setGameTypeName(String gameTypeName) {
		this.gameTypeName = gameTypeName;
	}
	public int getRackBackModel() {
		return rackBackModel;
	}
	public void setRackBackModel(int rackBackModel) {
		this.rackBackModel = rackBackModel;
	}
	public String getCommissionLevel() {
		return commissionLevel;
	}
	public void setCommissionLevel(String commissionLevel) {
		this.commissionLevel = commissionLevel;
	}
	public double getCommissionPercent() {
		return commissionPercent;
	}
	public void setCommissionPercent(double commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
	public double getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
	public double getActivityAmount() {
		return activityAmount;
	}
	public void setActivityAmount(double activityAmount) {
		this.activityAmount = activityAmount;
	}
	public int getPlatformType() {
		return platformType;
	}
	public void setPlatformType(int platformType) {
		this.platformType = platformType;
	}
	public String getPlatformTypeName() {
		switch (platformType) {
		case 1:
			platformTypeName = "PC";
			break;
		case 2:
			platformTypeName = "Android";
			break;
		case 3:
			platformTypeName = "IOS";
			break;
		case 4:
			platformTypeName = "H5";
			break;
		case 5:
			platformTypeName = "Others";
			break;
		default:
			platformTypeName = platformType + "";
			break;
		}
		return platformTypeName;
	}
}
