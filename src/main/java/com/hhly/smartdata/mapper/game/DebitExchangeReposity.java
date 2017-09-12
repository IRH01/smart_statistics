package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DebitExchange;

@Repository
public class DebitExchangeReposity extends BaseRepository{
	public List<DebitExchange> find(Map<String, Object> conMap) {
		List<DebitExchange> values = template.selectList("debitExchange.find", conMap);
		return values;
	}
	
	public int findCount(Map<String, Object> conMap) {
		return template.selectOne("debitExchange.count", conMap);
	}
}
