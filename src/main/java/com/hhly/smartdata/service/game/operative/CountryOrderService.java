package com.hhly.smartdata.service.game.operative;

import com.hhly.smartdata.model.game.operative.CountryOrder;

import java.util.List;
import java.util.Map;

public interface CountryOrderService{
    public List<CountryOrder> find(Map<String, Object> conditionMap);
}
