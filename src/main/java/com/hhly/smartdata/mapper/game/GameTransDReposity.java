package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.GameTransDaily;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameTransDReposity extends BaseRepository{
    /**
     * 查找
     *
     * @param conditionMap
     * @return
     */
    public List<GameTransDaily> statistics(Map<String, Object> conditionMap){
        List<GameTransDaily> values = template.selectList("gameTransDaily.statistics", conditionMap);
        return values;
    }

}
