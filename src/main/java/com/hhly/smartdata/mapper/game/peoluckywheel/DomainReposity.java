package com.hhly.smartdata.mapper.game.peoluckywheel;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.Domain;

@Repository
public class DomainReposity extends BaseRepository{
	public List<Domain> getAllAvailableDomain(){
		List<Domain> values = template.selectList("domain.getAllAvailableDomain");
		return values;
	}
}
