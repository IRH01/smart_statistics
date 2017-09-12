package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoColStatD;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class InfoColStatDRepository extends BaseRepository{

    public InfoColStatD getInfoTypeStatD(Map<String, Object> param){

        return this.getTemplate().selectOne("infoColStatD.queryOne", param);
    }
}
