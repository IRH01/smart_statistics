package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameStatDaily;

@Repository
public class GameStatDReposity extends BaseRepository{
	/**
	 * 查找
	 * @param conditionMap
	 * @return
	 */
	public List<GameStatDaily> find(Map<String, Object> conditionMap){
		List<GameStatDaily> values = template.selectList("gameStatDaily.find", conditionMap);
		return values;
	}
	
	/**
	 * 统计
	 * @param conditionMap
	 * @return
	 */
	public List<GameStatDaily> statistics(Map<String, Object> conditionMap){
		List<GameStatDaily> values = template.selectList("gameStatDaily.statistics", conditionMap);
		return values;
	}
}
