package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GamePlatformDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GamePlatformDailyReposity extends BaseRepository{
    public List<GamePlatformDaily> find(Map<String, Object> conditions){
        List<GamePlatformDaily> value = template.selectList("gamePlatformDaily.find", conditions);
        return value;
    }
}
