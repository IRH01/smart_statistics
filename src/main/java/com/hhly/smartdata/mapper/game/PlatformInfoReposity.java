package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.PlatformInfo;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PlatformInfoReposity extends BaseRepository{
    public List<PlatformInfo> getGamePlatformInfo(int countryId, String platformTerminal){
        Map<String, Object> conMap = new HashMap<String, Object>();
        conMap.put("countryId", countryId);
        conMap.put("platformTerminal", platformTerminal);
        List<PlatformInfo> values = template.selectList("platformInfo.getGamePlatformInfo", conMap);
        return values;
    }
}
