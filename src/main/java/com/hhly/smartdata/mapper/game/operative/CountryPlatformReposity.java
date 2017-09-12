package com.hhly.smartdata.mapper.game.operative;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.CountryPlatform;

@Repository
public class CountryPlatformReposity extends BaseRepository{
	
	/**
	 * 条件查询
	 * @param conMap
	 * @return
	 */
	public List<CountryPlatform> find(Map<String, Object> conMap) {
		return super.template.selectList("countryPlatform.find",conMap);
	}

}
