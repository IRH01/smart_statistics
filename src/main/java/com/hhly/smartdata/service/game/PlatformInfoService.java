package com.hhly.smartdata.service.game;

import java.util.List;

import com.hhly.smartdata.model.game.PlatformInfo;

public abstract class PlatformInfoService {
	public abstract List<PlatformInfo> getGamePlatformInfo(int countryId,String platformTerminal);
}
