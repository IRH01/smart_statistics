package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataInterfaceInvoke;

import java.util.List;

public interface DataInterfaceInvokeMapper{
    int insert(DataInterfaceInvoke record) throws Exception;

    int insertSelective(DataInterfaceInvoke record) throws Exception;

    DataInterfaceInvoke selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataInterfaceInvoke record) throws Exception;

    int updateByPrimaryKey(DataInterfaceInvoke record) throws Exception;

    // 查询统计接口数据
    List<DataInterfaceInvoke> findDataInfaceInvokeList();

}
