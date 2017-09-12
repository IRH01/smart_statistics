package com.hhly.smartdata.model.game;

public class ActiveLossDaily extends LossDaily{
	private long activeCount;
	
	public long getActiveCount() {
		return activeCount;
	}
	public void setActiveCount(long activeCount) {
		this.activeCount = activeCount;
	}
	@Override
	public long getCount() {
		return activeCount;
	}
}
