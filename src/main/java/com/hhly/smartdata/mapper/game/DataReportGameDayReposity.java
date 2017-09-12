package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DataReportGameDay;

@Repository
public class DataReportGameDayReposity extends BaseRepository{
	
	public List<DataReportGameDay> find(Map<String, Object> conMap) {
		List<DataReportGameDay> values = template.selectList("dataReportGameDay.find", conMap);
		return values;
	}
	
	public List<DataReportGameDay> findList(Map<String, Object> conMap) {
		List<DataReportGameDay> values = template.selectList("dataReportGameDay.findList", conMap);
		return values;
	}
	
}
