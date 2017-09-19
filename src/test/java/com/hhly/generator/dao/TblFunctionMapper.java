package com.hhly.generator.dao;

import com.hhly.generator.model.TblFunction;

public interface TblFunctionMapper {
    int insert(TblFunction record);

    int insertSelective(TblFunction record);

    TblFunction selectByPrimaryKey(Double id);

    int updateByPrimaryKeySelective(TblFunction record);

    int updateByPrimaryKey(TblFunction record);
}