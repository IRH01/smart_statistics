package com.hhly.smartdata.mapper.game.peoluckywheel;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.GamePage;

@Repository
public class GamePageReposity extends BaseRepository{
	public List<GamePage> getAllAvailableGamePage(){
		List<GamePage> values = template.selectList("gamePage.getAllAvailableGamePage");
		return values;
	}
}
