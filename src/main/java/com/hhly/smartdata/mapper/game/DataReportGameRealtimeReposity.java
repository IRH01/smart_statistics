package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.DataReportGameRealtime;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DataReportGameRealtimeReposity extends BaseRepository{

    public List<DataReportGameRealtime> find(Map<String, Object> conMap){
        List<DataReportGameRealtime> values = template.selectList("dataReportGameRealtime.find", conMap);
        return values;
    }
}
