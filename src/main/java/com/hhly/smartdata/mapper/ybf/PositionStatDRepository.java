package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.dto.PositionCountByDay;
import com.hhly.smartdata.dto.PositionStatDShower;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PositionStatDRepository extends BaseRepository{

    public List<PositionStatDShower> getPositionStatD(Map<String, Object> param){

        return this.getTemplate().selectList("positionStatD.findPositionStatD", param);
    }

    public PositionCountByDay getPositionStatDCountByDay(Map<String, Object> param){

        return this.getTemplate().selectOne("positionStatD.countByDay", param);
    }

}
