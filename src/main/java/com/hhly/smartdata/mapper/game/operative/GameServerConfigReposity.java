package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameServerConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameServerConfigReposity extends BaseRepository{
    public List<GameServerConfig> getByPlatformId(int platformId){
        List<GameServerConfig> values = template.selectList("gameServerConfig.getByPlatformId", platformId);
        return values;
    }
}
