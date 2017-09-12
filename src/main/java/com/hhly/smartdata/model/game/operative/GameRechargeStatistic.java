package com.hhly.smartdata.model.game.operative;

public class GameRechargeStatistic {
	
	/**
	 * 手续费取2%
	 */
	private final static double commisionRate = 0.02;  
	
	//订单金额
	private double orderAmount;
	//手续费
	private double commissions;
	//真实付款金额
	private double receiptAmount;
	
	public double getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}
	public double getCommissions() {
		return commissions;
	}
	public void setCommissions(double commission) {
		this.commissions = commission;
	}
	public double getReceiptAmount() {
		return receiptAmount;
	}
	public void setReceiptAmount(double receiptAmount) {
		this.receiptAmount = receiptAmount;
	}
	public static double getCommisionrate() {
		return commisionRate;
	}
}
