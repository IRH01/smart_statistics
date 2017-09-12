package com.hhly.smartdata.service.game.operative.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.operative.PlatTerminalInfo;
import com.hhly.smartdata.mapper.game.operative.PlatTerminalInfoReposity;
import com.hhly.smartdata.service.game.operative.PlatTerminalInfoService;

@Service
public class PlatTerminalInfoServiceImpl implements PlatTerminalInfoService {
	@Autowired
	private PlatTerminalInfoReposity platTerminalInfoReposity;
	
	@Override
	public List<PlatTerminalInfo> getAll() {
		return platTerminalInfoReposity.getAll();
	}


}
