package com.hhly.generator.dao;

import com.hhly.generator.model.TblMenu;

public interface TblMenuMapper {
    int insert(TblMenu record);

    int insertSelective(TblMenu record);

    TblMenu selectByPrimaryKey(Double id);

    int updateByPrimaryKeySelective(TblMenu record);

    int updateByPrimaryKey(TblMenu record);
}