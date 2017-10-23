package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataView;

import java.util.List;
import java.util.Map;

public interface DataViewMapper{
    int insert(DataView record) throws Exception;

    int insertSelective(DataView record) throws Exception;

    DataView selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataView record) throws Exception;

    int updateByPrimaryKey(DataView record) throws Exception;

    List<Map<String,Object>> selectYesterdayUserViewAndPageView()throws Exception;

    List<Map<String,Object>> selectFirstThirtyMinUserViewAndPageView(Map<String,Object> map)throws Exception;
}