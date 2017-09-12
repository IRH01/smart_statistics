package com.hhly.smartdata.service.game.operative;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.CountryPlatform;

public interface CountryPlatformService {
	public abstract PageInfo<CountryPlatform> find(Map<String, Object> conditionMap,int pageNumber,int pageSize);
}
