package com.hhly.generator.dao;

import com.hhly.generator.model.TblUserRole;

public interface TblUserRoleMapper {
    int insert(TblUserRole record);

    int insertSelective(TblUserRole record);

    TblUserRole selectByPrimaryKey(Double id);

    int updateByPrimaryKeySelective(TblUserRole record);

    int updateByPrimaryKey(TblUserRole record);
}