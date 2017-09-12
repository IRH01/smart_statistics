package com.hhly.smartdata.service.game;

import com.hhly.smartdata.model.game.PlatformInfo;

import java.util.List;

public interface PlatformInfoService{
    List<PlatformInfo> getGamePlatformInfo(int countryId, String platformTerminal);
}
