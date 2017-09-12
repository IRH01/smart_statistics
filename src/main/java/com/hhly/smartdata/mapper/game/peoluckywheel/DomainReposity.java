package com.hhly.smartdata.mapper.game.peoluckywheel;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.peoluckywheel.Domain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomainReposity extends BaseRepository{
    public List<Domain> getAllAvailableDomain(){
        List<Domain> values = template.selectList("domain.getAllAvailableDomain");
        return values;
    }
}
