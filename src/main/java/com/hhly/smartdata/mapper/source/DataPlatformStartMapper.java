package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataPlatformStart;

public interface DataPlatformStartMapper {
    int insert(DataPlatformStart record);

    int insertSelective(DataPlatformStart record);

    DataPlatformStart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataPlatformStart record);

    int updateByPrimaryKey(DataPlatformStart record);
}