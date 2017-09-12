package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameTransHourly;

@Repository
public class GameTransHReposity extends BaseRepository{
	
	/**
	 * 统计查找
	 * @param conditionMap
	 * @return
	 */
	public List<GameTransHourly> statistics(Map<String, Object> conditionMap){
		List<GameTransHourly> values = template.selectList("gameTransHourly.statistics", conditionMap);
		return values;
	}
	
}
