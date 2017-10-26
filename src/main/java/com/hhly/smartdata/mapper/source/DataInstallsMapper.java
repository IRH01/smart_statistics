package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataInstalls;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface DataInstallsMapper{
    int insert(DataInstalls record) throws Exception;

    int insertSelective(DataInstalls record) throws Exception;

    DataInstalls selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(DataInstalls record) throws Exception;

    int updateByPrimaryKey(DataInstalls record) throws Exception;

    List<Map<String,Object>> selectYesterdayInstalls()throws Exception;
}