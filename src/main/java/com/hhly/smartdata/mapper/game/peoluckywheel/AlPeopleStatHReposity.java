package com.hhly.smartdata.mapper.game.peoluckywheel;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.AlPeopleStatH;

@Repository
public class AlPeopleStatHReposity extends BaseRepository{
	public List<AlPeopleStatH> find(Map<String,Object> conditionMap){
		List<AlPeopleStatH> values = template.selectList("alPeopleStatH.find",conditionMap);
		return values;
	}
}
