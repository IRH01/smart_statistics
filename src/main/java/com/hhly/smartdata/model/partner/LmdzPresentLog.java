package com.hhly.smartdata.model.partner;

import java.util.Date;

/**
 * 撩妹德州送礼日志记录
 * @author Administrator
 *
 */
public class LmdzPresentLog {
	private int platformId;
	private String userId;
	private Date presentTime;
	//主播姓名
	private String anchorName;
	//主播id
	private String anchorId;
	//礼物名称
	private String presentName;
	//礼物数量
	private int presentCount;
	//结算金额
	private double settleAmount;
	//支付类型
	private int type;
	//单号
	private String presentOrderNo;
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getPresentTime() {
		return presentTime;
	}
	public void setPresentTime(Date presentTime) {
		this.presentTime = presentTime;
	}
	public String getAnchorName() {
		return anchorName;
	}
	public void setAnchorName(String anchorName) {
		this.anchorName = anchorName;
	}
	public String getAnchorId() {
		return anchorId;
	}
	public void setAnchorId(String anchorId) {
		this.anchorId = anchorId;
	}
	public String getPresentName() {
		return presentName;
	}
	public void setPresentName(String presentName) {
		this.presentName = presentName;
	}
	public int getPresentCount() {
		return presentCount;
	}
	public void setPresentCount(int presentCount) {
		this.presentCount = presentCount;
	}
	public double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(double settleAmount) {
		this.settleAmount = settleAmount;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getPresentOrderNo() {
		return presentOrderNo;
	}
	public void setPresentOrderNo(String presentOrderNo) {
		this.presentOrderNo = presentOrderNo;
	}
}
