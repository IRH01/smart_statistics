package com.hhly.smartdata.service.game.operative;


public interface GameRechargeSummaryService {
	/**
	 * 统计充值到游戏的钱
	 * @return
	 */
	public double statisticsRcg2Game(String countryId);
	
	/**
	 * 统计充值到乐盈盈平台乐盈币的金额
	 * @return
	 */
	public double statisticsRcg2Lyb(String countryId);
	
	/**
	 * 统计剩余乐盈币的金额
	 * @return
	 */
	public double statisticsRemainLyb(String countryId);
}
