package com.hhly.smartdata.service.game.operative;

import java.util.List;
import java.util.Map;

import com.hhly.smartdata.model.game.operative.CountryOrder;

public interface CountryOrderService {
	public List<CountryOrder> find(Map<String, Object> conditionMap);
}
