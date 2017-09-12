package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameStatDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameStatDReposity extends BaseRepository{
    /**
     * 查找
     *
     * @param conditionMap
     * @return
     */
    public List<GameStatDaily> find(Map<String, Object> conditionMap){
        List<GameStatDaily> values = template.selectList("gameStatDaily.find", conditionMap);
        return values;
    }

    /**
     * 统计
     *
     * @param conditionMap
     * @return
     */
    public List<GameStatDaily> statistics(Map<String, Object> conditionMap){
        List<GameStatDaily> values = template.selectList("gameStatDaily.statistics", conditionMap);
        return values;
    }
}
