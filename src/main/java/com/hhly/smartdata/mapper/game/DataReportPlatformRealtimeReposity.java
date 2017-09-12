package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DataReportPlatformRealtime;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DataReportPlatformRealtimeReposity extends BaseRepository{

    public List<DataReportPlatformRealtime> find(Map<String, Object> conMap){
        List<DataReportPlatformRealtime> values = template.selectList("dataReportPlatformRealtime.find", conMap);
        return values;
    }
}
