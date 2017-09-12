package com.hhly.smartdata.model.game.peoluckywheel;

import java.util.Date;

public abstract class AlPeopleStat {
	protected String etlDate;
	private String domainId;
	private String channelId;
	private String gameId;
	private long pvCnt;
	private long uvCnt;
	private long stayCnt;
	private double avgStayCnt;
	private Date createdDate;
	private Date updatedDate;
	
	public String getEtlDate() {
		return etlDate;
	}
	
	public abstract void setEtlDate(Date etlDate);
	
	public String getDomainId() {
		return domainId;
	}
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public long getPvCnt() {
		return pvCnt;
	}
	public void setPvCnt(long pvCnt) {
		this.pvCnt = pvCnt;
	}
	public long getUvCnt() {
		return uvCnt;
	}
	public void setUvCnt(long uvCnt) {
		this.uvCnt = uvCnt;
	}
	public long getStayCnt() {
		return stayCnt;
	}
	public void setStayCnt(long stayCnt) {
		this.stayCnt = stayCnt;
	}
	public double getAvgStayCnt() {
		avgStayCnt = 0;
		if (pvCnt != 0) {
			//平均停留时长=停留时长/访问次数
			avgStayCnt = (double)stayCnt / pvCnt;
		}
		return avgStayCnt;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
