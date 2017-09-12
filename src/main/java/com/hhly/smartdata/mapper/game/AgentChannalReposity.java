package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.ChannalAgent;
import com.hhly.smartdata.model.game.ChannalCostDetails;
import com.hhly.smartdata.model.game.ChannalDetails;
import com.hhly.smartdata.model.game.ChannalRegistDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AgentChannalReposity extends BaseRepository{
    public List<ChannalAgent> find(Map<String, Object> conMap){
        List<ChannalAgent> values = template.selectList("channalAgent.find", conMap);
        return values;
    }

//	public int findCount(Map<String, Object> conMap) {
//		return template.selectOne("channalAgent.count", conMap);
//	}

    public List<ChannalDetails> findChannelDetails(Map<String, Object> conMap){
        List<ChannalDetails> values = template.selectList("channalAgent.findChannelDetails", conMap);
        return values;
    }

    public int countChannelDetails(Map<String, Object> conMap){
        return template.selectOne("channalAgent.countChannelDetails", conMap);
    }

    public List<ChannalCostDetails> findChannelCostDetails(Map<String, Object> conMap){
        List<ChannalCostDetails> values = template.selectList("channalAgent.findChannelCostDetails", conMap);
        return values;
    }

    public int countChannelCostDetails(Map<String, Object> conMap){
        return template.selectOne("channalAgent.countChannelCostDetails", conMap);
    }

    public List<ChannalRegistDetails> findChannelRegistDetails(Map<String, Object> conMap){
        List<ChannalRegistDetails> values = template.selectList("channalAgent.findChannelRegistDetails", conMap);
        return values;
    }

    public int countChannelRegistDetails(Map<String, Object> conMap){
        return template.selectOne("channalAgent.countChannelRegistDetails", conMap);
    }
}
