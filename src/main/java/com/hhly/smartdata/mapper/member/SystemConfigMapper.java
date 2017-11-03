package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.SystemConfig;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemConfigMapper{

    int insert(SystemConfig record) throws Exception;

    int insertSelective(SystemConfig record) throws Exception;

    SystemConfig selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(SystemConfig record) throws Exception;

    int updateByPrimaryKey(SystemConfig record) throws Exception;

    String getConfigValueByKey(String configKey) throws Exception;
}