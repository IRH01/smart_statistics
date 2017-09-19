package com.hhly.generator.dao;

import com.hhly.generator.model.TblUser;

public interface TblUserMapper {
    int insert(TblUser record);

    int insertSelective(TblUser record);

    TblUser selectByPrimaryKey(Double userId);

    int updateByPrimaryKeySelective(TblUser record);

    int updateByPrimaryKey(TblUser record);
}