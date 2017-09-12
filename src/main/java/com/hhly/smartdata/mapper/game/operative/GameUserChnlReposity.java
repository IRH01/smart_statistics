package com.hhly.smartdata.mapper.game.operative;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.GameUserChnl;

@Repository
public class GameUserChnlReposity extends BaseRepository{
	
	public List<GameUserChnl> find(Map<String, Object> conMap) {
		if(conMap.get("type").equals("D")) {
			return template.selectList("gameUserChnl.findD", conMap);
		} else {
			return template.selectList("gameUserChnl.findH", conMap);
		}
	}
	
	public List<GameUserChnl> getProduces(Map<String, Object> condition) {
		return super.template.selectList("gameUserChnl.getProduces",condition);
	}

	public int findCount(Map<String, Object> conMap) {
		if(conMap.get("type").equals("D")) {
			return template.selectOne("gameUserChnl.countD", conMap);
		} else {
			return template.selectOne("gameUserChnl.countH", conMap);
		}
		
	}
}
