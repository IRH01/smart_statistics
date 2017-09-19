package com.hhly.generator.dao;

import com.hhly.generator.model.TblRolePermission;

public interface TblRolePermissionMapper {
    int insert(TblRolePermission record);

    int insertSelective(TblRolePermission record);

    TblRolePermission selectByPrimaryKey(Double id);

    int updateByPrimaryKeySelective(TblRolePermission record);

    int updateByPrimaryKey(TblRolePermission record);
}