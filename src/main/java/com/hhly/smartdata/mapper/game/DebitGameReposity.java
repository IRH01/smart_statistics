package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DebitGame;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DebitGameReposity extends BaseRepository{
    public List<DebitGame> find(Map<String, Object> conMap){
        List<DebitGame> values = template.selectList("debitGame.find", conMap);
        return values;
    }

    public int findCount(Map<String, Object> conMap){
        return template.selectOne("debitGame.count", conMap);
    }
}
