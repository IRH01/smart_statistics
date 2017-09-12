package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameSummaryDaily;

@Repository
public class GameSummaryDReposity extends BaseRepository{
	public List<GameSummaryDaily> find(Map<String, Object> conditionMap){
		List<GameSummaryDaily> values = template.selectList("gameSummaryDaily.find", conditionMap);
		return values;
	}
}
