package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.dto.InfoTypeDto;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.DimInfoType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DimInfoTypeRepository extends BaseRepository{

    /**
     * 根据 id 获取
     *
     * @param tblId
     * @return
     */
    public DimInfoType get(Integer tblId){
        return template.selectOne("dimPosition.get", tblId);
    }

    /**
     * 获取所有类别信息
     *
     * @return
     */
    public List<InfoTypeDto> getAll(Map<String, String> param){
        List<InfoTypeDto> list = template.selectList("dimInfoType.getAll", param);
        return list;
    }
}
