package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.dto.InfoStatDShower;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InfoStatDRepository extends BaseRepository{

    public List<InfoStatDShower> getInfoStatD(Map<String, String> param){

        return this.getTemplate().selectList("infoStatD.findInfoStatD", param);
    }
}
