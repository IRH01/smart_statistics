package com.hhly.smartdata.mapper.game.operative;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameServerConfig;

@Repository
public class GameServerConfigReposity extends BaseRepository{
	public List<GameServerConfig> getByPlatformId(int platformId){
		List<GameServerConfig> values = template.selectList("gameServerConfig.getByPlatformId", platformId);
		return values;
	}
}
