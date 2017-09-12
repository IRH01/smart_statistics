package com.hhly.smartdata.service.game.operative.impl;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.mapper.game.operative.GameServerConfigReposity;
import com.hhly.smartdata.service.game.operative.GameServerConfigService;

@Service
public class GameServerConfigServiceImpl implements GameServerConfigService {
	
	@Autowired
	GameServerConfigReposity gameServerConfigReposity;
	
	@Override
	public JSONArray getByPlatformId(
			int platformId) {
		return JSONArray.fromObject(gameServerConfigReposity.getByPlatformId(platformId));
	}

}
