package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataPlatformStart;

public interface DataPlatformStartMapper{
    int insert(DataPlatformStart record) throws Exception;

    int insertSelective(DataPlatformStart record) throws Exception;

    DataPlatformStart selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataPlatformStart record) throws Exception;

    int updateByPrimaryKey(DataPlatformStart record) throws Exception;
}