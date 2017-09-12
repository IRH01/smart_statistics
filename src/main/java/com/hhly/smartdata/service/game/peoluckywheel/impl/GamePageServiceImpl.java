package com.hhly.smartdata.service.game.peoluckywheel.impl;

import com.hhly.smartdata.mapper.game.peoluckywheel.GamePageReposity;
import com.hhly.smartdata.model.game.peoluckywheel.GamePage;
import com.hhly.smartdata.service.game.peoluckywheel.GamePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GamePageServiceImpl implements GamePageService{
    @Autowired
    private GamePageReposity gamePageReposity;

    @Override
    public List<GamePage> getAllAvailableGamePage(){
        List<GamePage> value = gamePageReposity.getAllAvailableGamePage();
        return value;
    }
}
