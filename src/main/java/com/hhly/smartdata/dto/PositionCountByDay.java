package com.hhly.smartdata.dto;

public class PositionCountByDay {

	private String date;

	private long clickCntTotal;

	private long stayCntTotal;

	private long ipCntTotal;

	public long getClickCntTotal() {
		return clickCntTotal;
	}

	public void setClickCntTotal(long clickCntTotal) {
		this.clickCntTotal = clickCntTotal;
	}

	public long getStayCntTotal() {
		return stayCntTotal;
	}

	public void setStayCntTotal(long stayCntTotal) {
		this.stayCntTotal = stayCntTotal;
	}

	public long getIpCntTotal() {
		return ipCntTotal;
	}

	public void setIpCntTotal(long ipCntTotal) {
		this.ipCntTotal = ipCntTotal;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
