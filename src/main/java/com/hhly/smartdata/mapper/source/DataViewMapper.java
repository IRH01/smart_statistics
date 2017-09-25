package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataView;

public interface DataViewMapper {
    int insert(DataView record);

    int insertSelective(DataView record);

    DataView selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataView record);

    int updateByPrimaryKey(DataView record);
}