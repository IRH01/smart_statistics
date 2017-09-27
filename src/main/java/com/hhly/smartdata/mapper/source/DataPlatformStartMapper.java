package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataPlatformStart;

import java.util.List;
import java.util.Map;

public interface DataPlatformStartMapper{
    int insert(DataPlatformStart record) throws Exception;

    int insertSelective(DataPlatformStart record) throws Exception;

    DataPlatformStart selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataPlatformStart record) throws Exception;

    int updateByPrimaryKey(DataPlatformStart record) throws Exception;

    List<Map<String,Object>> selectIntervalGamelaunch()throws Exception;
}