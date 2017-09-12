package com.hhly.smartdata.mapper.game;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.TerminalType;

@Repository
public class TerminalTypeReposity extends BaseRepository{
	public List<TerminalType> getAll(){
		List<TerminalType> values = template.selectList("terminalType.getAll");
		return values;
	}
}
