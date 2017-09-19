package com.hhly.generator.dao;

import com.hhly.generator.model.TblAdmin;

public interface TblAdminMapper {
    int insert(TblAdmin record);

    int insertSelective(TblAdmin record);

    TblAdmin selectByPrimaryKey(Double id);

    int updateByPrimaryKeySelective(TblAdmin record);

    int updateByPrimaryKey(TblAdmin record);
}