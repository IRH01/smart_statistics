package com.hhly.smartdata.service.game;

import com.hhly.smartdata.model.game.PlatformInfo;

import java.util.List;

public abstract class PlatformInfoService{
    public abstract List<PlatformInfo> getGamePlatformInfo(int countryId, String platformTerminal);
}
