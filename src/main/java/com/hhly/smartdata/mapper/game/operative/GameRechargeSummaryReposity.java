package com.hhly.smartdata.mapper.game.operative;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameRechargeStatistic;

@Repository
public class GameRechargeSummaryReposity extends BaseRepository{
	/**
	 * 统计所有充值的钱
	 * @return
	 */
	public GameRechargeStatistic statistics(String countryId){
		GameRechargeStatistic value = template.selectOne("gameRechargeSummary.statistics",countryId);
		return value;
	}
	
	/**
	 * 统计充值到乐盈盈平台乐盈币的金额
	 * @return
	 */
	public double statisticsRcg2Lyb(String countryId){
		Double value = template.selectOne("gameRechargeSummary.statisticsRcg2Lyb",countryId);
		return value == null ? 0 : value;
	}
	
	/**
	 * 统计充值到乐盈盈平台乐盈币的金额
	 * @return
	 */
	public double statisticsLyb2Game(String countryId){
		Double value = template.selectOne("gameRechargeSummary.statisticsLyb2Game",countryId);
		return value == null ? 0 : value;
	}
}
