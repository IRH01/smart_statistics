package com.hhly.smartdata.mapper.game.operative;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.PlatTerminalInfo;

@Repository
public class PlatTerminalInfoReposity extends BaseRepository{
	
	/**
	 * 获取全部
	 * @return
	 */
	public List<PlatTerminalInfo> getAll() {
		return super.template.selectList("platTerminalInfo.getAll");
	}
}
