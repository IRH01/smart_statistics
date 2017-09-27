package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataInterfaceInvoke;

import java.util.List;
import java.util.Map;

public interface DataInterfaceInvokeMapper{
    int insert(DataInterfaceInvoke record) throws Exception;

    int insertSelective(DataInterfaceInvoke record) throws Exception;

    DataInterfaceInvoke selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataInterfaceInvoke record) throws Exception;

    int updateByPrimaryKey(DataInterfaceInvoke record) throws Exception;

    List<Map<String,Object>> findDataInterfaceInvokeList()throws Exception;

}
