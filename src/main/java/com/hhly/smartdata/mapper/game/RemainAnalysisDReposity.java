package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.RemainAnalysisDaily;

@Repository
public class RemainAnalysisDReposity extends BaseRepository{
	public List<RemainAnalysisDaily> find(Map<String, Object> conditionMap){
		List<RemainAnalysisDaily> values = template.selectList("remainAnalysisDaily.find", conditionMap);
		return values;
	}
}
