package com.hhly.smartdata.model.game.operative;

public class GameServerConfig {
	private String id;
	private String name;
	private String areaId;
	private String areaName;
	private String fullName;
	
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
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getFullName() {
		fullName = areaName + " " + name;
		return fullName;
	}
}
