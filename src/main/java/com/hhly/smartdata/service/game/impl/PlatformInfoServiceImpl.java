package com.hhly.smartdata.service.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.PlatformInfo;
import com.hhly.smartdata.mapper.game.PlatformInfoReposity;
import com.hhly.smartdata.service.game.PlatformInfoService;

@Service
public class PlatformInfoServiceImpl extends PlatformInfoService{
	@Autowired
	PlatformInfoReposity platformInfoReposity;
	
	@Override
	public List<PlatformInfo> getGamePlatformInfo(int countryId,String platformTerminal) {
		return platformInfoReposity.getGamePlatformInfo(countryId,platformTerminal);
	}
	
}
