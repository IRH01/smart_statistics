package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.dto.YbfWebStatH;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class YbfWebStatHRepository extends BaseRepository{

    public List<YbfWebStatH> findYbfWebStatH(Map<String, String> param){

        return this.template.selectList("ybfWebStatH.find", param);
    }
}
