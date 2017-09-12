package com.hhly.smartdata.service.game.impl;

import com.hhly.smartdata.mapper.game.PlatformInfoReposity;
import com.hhly.smartdata.model.game.PlatformInfo;
import com.hhly.smartdata.service.game.PlatformInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatformInfoServiceImpl implements PlatformInfoService{
    @Autowired
    PlatformInfoReposity platformInfoReposity;

    @Override
    public List<PlatformInfo> getGamePlatformInfo(int countryId, String platformTerminal){
        return platformInfoReposity.getGamePlatformInfo(countryId, platformTerminal);
    }

}
