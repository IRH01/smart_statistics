package com.hhly.smartdata.model.game;

public class PayLossDaily extends LossDaily{
	private long payCount;
	
	public long getPayCount() {
		return payCount;
	}
	public void setPayCount(long payCount) {
		this.payCount = payCount;
	}
	
	@Override
	public long getCount() {
		return payCount;
	}
}
