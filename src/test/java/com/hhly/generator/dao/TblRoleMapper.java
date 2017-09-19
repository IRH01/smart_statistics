package com.hhly.generator.dao;

import com.hhly.generator.model.TblRole;

public interface TblRoleMapper {
    int insert(TblRole record);

    int insertSelective(TblRole record);

    TblRole selectByPrimaryKey(Double id);

    int updateByPrimaryKeySelective(TblRole record);

    int updateByPrimaryKey(TblRole record);
}