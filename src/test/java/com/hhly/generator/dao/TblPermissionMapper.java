package com.hhly.generator.dao;

import com.hhly.generator.model.TblPermission;

public interface TblPermissionMapper {
    int insert(TblPermission record);

    int insertSelective(TblPermission record);
}