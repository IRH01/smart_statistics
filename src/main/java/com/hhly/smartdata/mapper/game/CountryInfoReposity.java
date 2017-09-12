package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.CountryInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CountryInfoReposity extends BaseRepository{
    public List<CountryInfo> getExistDataCountryInfo(){
        List<CountryInfo> values = template.selectList("countryInfo.getExistDataCountryInfo");
        return values;
    }
}
