package com.hhly.smartdata.service.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.CountryInfo;
import com.hhly.smartdata.mapper.game.CountryInfoReposity;
import com.hhly.smartdata.service.game.CountryInfoService;

@Service
public class CountryInfoServiceImpl extends CountryInfoService{
	@Autowired
	CountryInfoReposity CountryInfoReposity;

	@Override
	public List<CountryInfo> getExistDataCountryInfo() {
		return this.CountryInfoReposity.getExistDataCountryInfo();
	}
}
