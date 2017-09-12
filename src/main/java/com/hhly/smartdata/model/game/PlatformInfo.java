package com.hhly.smartdata.model.game;

import java.util.Date;

public class PlatformInfo {
	private String id;
	private String name;
	private Date createTime;
	private Date updateTime;
	//是否是游戏，0不是，1是
	private int isGame;
	//国家
	private int country;
	//重大类型
	private String platformTerminalName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getIsGame() {
		return isGame;
	}
	public void setIsGame(int isGame) {
		this.isGame = isGame;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public String getPlatformTerminalName() {
		return platformTerminalName;
	}
	public void setPlatformTerminalName(String platformTerminalName) {
		this.platformTerminalName = platformTerminalName;
	}
}
