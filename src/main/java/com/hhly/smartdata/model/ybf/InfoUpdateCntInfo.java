package com.hhly.smartdata.model.ybf;

/**
 * 日更新资讯数量信息
 * @author Administrator
 *
 */
public class InfoUpdateCntInfo {
	
	private long count;
	private String infoTypeId;
	private String infoTypeName;
	private double percent;
	
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getInfoTypeId() {
		return infoTypeId;
	}
	public void setInfoTypeId(String infoTypeId) {
		this.infoTypeId = infoTypeId;
	}
	public String getInfoTypeName() {
		return infoTypeName;
	}
	public void setInfoTypeName(String infoTypeName) {
		this.infoTypeName = infoTypeName;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
}
