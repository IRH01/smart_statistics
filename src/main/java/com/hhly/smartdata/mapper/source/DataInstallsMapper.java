package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataInstalls;

public interface DataInstallsMapper{
    int insert(DataInstalls record);

    int insertSelective(DataInstalls record);

    DataInstalls selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataInstalls record);

    int updateByPrimaryKey(DataInstalls record);
}