package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.InfoDomainStatD;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InfoDomainStatDRepository extends BaseRepository{

    public List<InfoDomainStatD> getInfoActStatD(Map<String, String> params){

        return this.getTemplate().selectList("infoDomainStatD.findByDate", params);
    }
}
