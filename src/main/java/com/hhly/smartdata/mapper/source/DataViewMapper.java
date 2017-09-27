package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataView;

public interface DataViewMapper{
    int insert(DataView record) throws Exception;

    int insertSelective(DataView record) throws Exception;

    DataView selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataView record) throws Exception;

    int updateByPrimaryKey(DataView record) throws Exception;
}