package com.hhly.smartdata.mapper.game.peoluckywheel;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.AlPeopleStatD;

@Repository
public class AlPeopleStatDReposity extends BaseRepository{
	public List<AlPeopleStatD> find(Map<String,Object> conditionMap){
		List<AlPeopleStatD> values = template.selectList("alPeopleStatD.find",conditionMap);
		return values;
	}
}
