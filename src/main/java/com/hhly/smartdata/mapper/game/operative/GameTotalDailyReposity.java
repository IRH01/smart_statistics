package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameTotalDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameTotalDailyReposity extends BaseRepository{
    public List<GameTotalDaily> find(Map<String, Object> conditions){
        List<GameTotalDaily> value = template.selectList("gameTotalDaily.find", conditions);
        return value;
    }
}
