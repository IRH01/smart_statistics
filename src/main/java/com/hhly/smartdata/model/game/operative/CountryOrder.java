package com.hhly.smartdata.model.game.operative;


/**
 * 各国会员充值汇总信息
 * @author wanghuang
 *
 */
public class CountryOrder {
	/**
	 * 国家Id
	 */
	private int country;
	/**
	 * 国家名称
	 */
	private String countryName;
	/**
	 *注册用户数 
	 */
	private double registCount;
	/**
	 * 充值用户数
	 */
	private double userCount;
	/**
	 * 充值次数
	 */
	private double rechargeCount;
	/**
	 * 充值金额
	 */
	private double rechargeAmount;
	
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public double getRegistCount() {
		return registCount;
	}
	public void setRegistCount(double registCount) {
		this.registCount = registCount;
	}
	public double getUserCount() {
		return userCount;
	}
	public void setUserCount(double userCount) {
		this.userCount = userCount;
	}
	public double getRechargeCount() {
		return rechargeCount;
	}
	public void setRechargeCount(double rechargeCount) {
		this.rechargeCount = rechargeCount;
	}
	public double getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
}
