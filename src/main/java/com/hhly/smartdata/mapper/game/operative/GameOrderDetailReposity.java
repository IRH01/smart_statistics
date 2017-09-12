package com.hhly.smartdata.mapper.game.operative;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameOrderDetail;

@Repository
public class GameOrderDetailReposity extends BaseRepository{
	
	public List<GameOrderDetail> find(Map<String, Object> conMap) {
		List<GameOrderDetail> values = template.selectList("gameOrderDetail.find", conMap);
		return values;
	}
	
	public List<GameOrderDetail> getStates(Map<String, Object> condition) {
		return super.template.selectList("gameOrderDetail.getStates",condition);
	}
	
	public List<GameOrderDetail> getProduces(Map<String, Object> condition) {
		return super.template.selectList("gameOrderDetail.getProduces",condition);
	}

	public int findCount(Map<String, Object> conMap) {
		return template.selectOne("gameOrderDetail.count", conMap);
	}
}
