package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoTypeStatD;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class InfoTypeStatDRepository extends BaseRepository{

    public InfoTypeStatD getInfoTypeStatD(Map<String, Object> param){

        return this.getTemplate().selectOne("infoTypeStatD.queryOne", param);
    }
}
