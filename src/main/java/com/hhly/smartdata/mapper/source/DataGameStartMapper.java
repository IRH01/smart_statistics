package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataGameStart;

public interface DataGameStartMapper {
    int insert(DataGameStart record);

    int insertSelective(DataGameStart record);

    DataGameStart selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataGameStart record);

    int updateByPrimaryKey(DataGameStart record);
}