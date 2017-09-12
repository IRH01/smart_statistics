package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameSummaryHourly;

@Repository
public class GameSummaryHReposity extends BaseRepository{
	public List<GameSummaryHourly> find(Map<String, Object> conditionMap){
		List<GameSummaryHourly> values = template.selectList("gameSummaryHourly.find", conditionMap);
		return values;
	}
}
