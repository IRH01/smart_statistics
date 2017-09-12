package com.hhly.smartdata.service.game.operative;

import net.sf.json.JSONArray;

public interface GameServerConfigService{
	public JSONArray getByPlatformId(int platformId);
}
