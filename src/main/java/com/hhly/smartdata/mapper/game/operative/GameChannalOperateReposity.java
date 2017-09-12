package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameChannalOperate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameChannalOperateReposity extends BaseRepository{

    public List<GameChannalOperate> find(Map<String, Object> conMap){
        List<GameChannalOperate> values = template.selectList("gameChannalOperate.find", conMap);
        return values;
    }

    public List<GameChannalOperate> getProduces(Map<String, Object> condition){
        return super.template.selectList("gameChannalOperate.getProduces", condition);
    }

    public int findCount(Map<String, Object> conMap){
        return template.selectOne("gameChannalOperate.count", conMap);
    }
}
