package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.CountryPlatform;
import com.hhly.smartdata.mapper.game.operative.CountryPlatformReposity;
import com.hhly.smartdata.service.game.operative.CountryPlatformService;

@Service
public class CountryPlatformImplService implements CountryPlatformService {
	@Autowired
	private CountryPlatformReposity countryPlatformReposity;
	
	@Override
	public PageInfo<CountryPlatform> find(Map<String, Object> conditionMap,
			int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<CountryPlatform> values = countryPlatformReposity.find(conditionMap);
		PageInfo<CountryPlatform> pageInfo = new PageInfo<CountryPlatform>(values);
		return pageInfo;
	}
}
