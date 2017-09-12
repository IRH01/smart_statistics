package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.dto.InfoByPosition;
import com.hhly.smartdata.dto.PositionByInfo;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InfoPositionStatDRepository extends BaseRepository{

    public List<PositionByInfo> getInfoPositionStatDByInfo(Map<String, String> param){

        return this.template.selectList("infoPositionStatD.findPositionByInfo", param);
    }

    public List<InfoByPosition> getInfoPositionStatDByPosition(Map<String, Object> param){

        return this.template.selectList("infoPositionStatD.findInfoByPosition", param);
    }
}
