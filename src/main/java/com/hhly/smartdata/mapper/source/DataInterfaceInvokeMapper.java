package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataInterfaceInvoke;

public interface DataInterfaceInvokeMapper{
    int insert(DataInterfaceInvoke record);

    int insertSelective(DataInterfaceInvoke record);

    DataInterfaceInvoke selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataInterfaceInvoke record);

    int updateByPrimaryKey(DataInterfaceInvoke record);
}