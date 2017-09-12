package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.operative.CountryOrder;
import com.hhly.smartdata.mapper.game.operative.CountryOrderReposity;
import com.hhly.smartdata.service.game.operative.CountryOrderService;

@Service
public class CountryOrderImplService implements CountryOrderService {
	@Autowired
	private CountryOrderReposity countryOrderReposity;
	
	@Override
	public List<CountryOrder> find(Map<String, Object> conditionMap) {
		List<CountryOrder> values = countryOrderReposity.find(conditionMap);
		return values;
	}
}
