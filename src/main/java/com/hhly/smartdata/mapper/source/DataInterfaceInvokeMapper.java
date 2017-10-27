package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataInterfaceInvoke;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataInterfaceInvokeMapper {
    int insert(DataInterfaceInvoke record) throws Exception;

    int insertSelective(DataInterfaceInvoke record) throws Exception;

    DataInterfaceInvoke selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataInterfaceInvoke record) throws Exception;

    int updateByPrimaryKey(DataInterfaceInvoke record) throws Exception;

    List<Map<String, Object>> findDataInterfaceInvokeList(@Param("startDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

}
