package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameChannelDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameChannelDailyReposity extends BaseRepository{
    public List<GameChannelDaily> find(Map<String, Object> conditions){
        List<GameChannelDaily> value = template.selectList("gameChannelDaily.find", conditions);
        return value;
    }
}
