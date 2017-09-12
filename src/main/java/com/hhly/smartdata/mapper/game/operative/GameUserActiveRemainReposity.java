package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameUserActiveRemain;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class GameUserActiveRemainReposity extends BaseRepository{

    public List<GameUserActiveRemain> find(Map<String, Object> conMap){
        List<GameUserActiveRemain> values = template.selectList("gameUserActiveRemain.find", conMap);
        return values;
    }

    //	public List<GameOrderDetail> getStates(Map<String, Object> condition) {
//		return super.template.selectList("gameOrderDetail.getStates",condition);
//	}
//	
    public List<GameUserActiveRemain> getProduces(Map<String, Object> condition){
        return super.template.selectList("gameUserActiveRemain.getProduces", condition);
    }

    public int findCount(Map<String, Object> conMap){
        return template.selectOne("gameUserActiveRemain.count", conMap);
    }
}
