package com.hhly.smartdata.mapper.game;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.PayLossDaily;

@Repository
public class PayLossDReposity extends BaseRepository{
	public List<PayLossDaily> find(Map<String, Object> conditionMap){
		List<PayLossDaily> values = template.selectList("payLossDaily.find", conditionMap);
		return values;
	}
}
