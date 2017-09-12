package com.hhly.smartdata.model.game.operative;

/**
 * 各国不同平台游戏充值汇总表
 * @author wanghuang
 *
 */
public class CountryPlatform {
	/**
	 * 平台类型：1.PC 2.安卓 3ios 4.h5  5.其他
	 */
	private int platformTerminal;
	/**
	 * 平台类型名称
	 */
	private String platformTerminalName;
	/**
	 * 平台ID
	 */
	private int platformId;
	/**
	 * 平台名称
	 */
	private String platformName;
	/**
	 * 充值金额
	 */
	private double rechargeAmount;
	/**
	 * 充值用户数
	 */
	private int userCount;
	/**
	 * 充值次数
	 */
	private double rechargeCount;
	/**
	 * 在线人数
	 */
	private int onlineCount;
	
	public int getPlatformTerminal() {
		return platformTerminal;
	}
	public void setPlatformTerminal(int platformTerminal) {
		this.platformTerminal = platformTerminal;
	}
	public String getPlatformTerminalName() {
		return platformTerminalName;
	}
	public void setPlatformTerminalName(String platformTerminalName) {
		this.platformTerminalName = platformTerminalName;
	}
	public int getPlatformId() {
		return platformId;
	}
	public void setPlatformId(int platformId) {
		this.platformId = platformId;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public double getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public int getUserCount() {
		return userCount;
	}
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}
	public double getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(double rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public int getOnlineCount() {
		return onlineCount;
	}
	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
}
