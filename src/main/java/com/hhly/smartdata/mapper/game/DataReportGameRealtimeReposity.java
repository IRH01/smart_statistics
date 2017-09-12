package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DataReportGameRealtime;

@Repository
public class DataReportGameRealtimeReposity extends BaseRepository{
	
	public List<DataReportGameRealtime> find(Map<String, Object> conMap) {
		List<DataReportGameRealtime> values = template.selectList("dataReportGameRealtime.find", conMap);
		return values;
	}
}
