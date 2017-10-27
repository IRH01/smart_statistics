package com.hhly.smartdata.mapper.source;


import com.hhly.smartdata.model.source.DataGameStart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DataGameStartMapper {
    int insert(DataGameStart record) throws Exception;

    int insertSelective(DataGameStart record) throws Exception;

    DataGameStart selectByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(DataGameStart record) throws Exception;

    int updateByPrimaryKey(DataGameStart record) throws Exception;

    List<Map<String, Object>> selectYesterdayLaunchGameUser() throws Exception;

    List<Map<String, Object>> selectPlatformAllGameStartCount(@Param("startDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

    List<Map<String, Object>> selectFirstThirtyMinGameStartCount(@Param("startDate") String startDate, @Param("intervalTime") Integer intervalTime) throws Exception;

}