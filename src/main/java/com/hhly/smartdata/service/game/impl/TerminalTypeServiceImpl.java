package com.hhly.smartdata.service.game.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hhly.smartdata.model.game.TerminalType;
import com.hhly.smartdata.mapper.game.TerminalTypeReposity;
import com.hhly.smartdata.service.game.TerminalTypeService;

@Service
public class TerminalTypeServiceImpl implements TerminalTypeService {
	@Autowired
	TerminalTypeReposity terminalTypeReposity;
	
	@Override
	public List<TerminalType> getAll() {
		return terminalTypeReposity.getAll();
	}

}
