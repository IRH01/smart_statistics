package com.hhly.smartdata.mapper.member;


import com.hhly.smartdata.model.member.PlatformInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformInfoMapper{
    int insert(PlatformInfo record);

    int insertSelective(PlatformInfo record);

    PlatformInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PlatformInfo record);

    int updateByPrimaryKey(PlatformInfo record);
}