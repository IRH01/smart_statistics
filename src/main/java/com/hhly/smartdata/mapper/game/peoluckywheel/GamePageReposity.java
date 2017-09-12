package com.hhly.smartdata.mapper.game.peoluckywheel;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.GamePage;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GamePageReposity extends BaseRepository{
    public List<GamePage> getAllAvailableGamePage(){
        List<GamePage> values = template.selectList("gamePage.getAllAvailableGamePage");
        return values;
    }
}
