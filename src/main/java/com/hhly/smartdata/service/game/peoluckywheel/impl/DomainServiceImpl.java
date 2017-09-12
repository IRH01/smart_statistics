package com.hhly.smartdata.service.game.peoluckywheel.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.peoluckywheel.Domain;
import com.hhly.smartdata.mapper.game.peoluckywheel.DomainReposity;
import com.hhly.smartdata.service.game.peoluckywheel.DomainService;

@Service
public class DomainServiceImpl implements DomainService {
	@Autowired
	private DomainReposity domainReposity;
	
	@Override
	public List<Domain> getAllAvailableDomain() {
		List<Domain> value = domainReposity.getAllAvailableDomain();
		return value;
	}
}
