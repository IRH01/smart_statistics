package com.hhly.smartdata.mapper.game;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.CountryInfo;

@Repository
public class CountryInfoReposity extends BaseRepository{
	public List<CountryInfo>getExistDataCountryInfo(){
		List<CountryInfo> values = template.selectList("countryInfo.getExistDataCountryInfo");
		return values;
	}
}
