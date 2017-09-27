package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataGameStart;

import java.util.List;
import java.util.Map;

public interface DataGameStartMapper{
    int insert(DataGameStart record) throws Exception;

    int insertSelective(DataGameStart record) throws Exception;

    DataGameStart selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataGameStart record) throws Exception;

    int updateByPrimaryKey(DataGameStart record) throws Exception;

    List<Map<String, Object>> selectYesterdayLaunchGameUser() throws Exception;
}