package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionMapper{

    List<Function> queryByRole(List<Integer> roleIds)throws Exception;

    int insert(Function record)throws Exception;

    Function get(Integer id)throws Exception;

    int update(Function record)throws Exception;

    List<Function> search(Function condition)throws Exception;

    void batchDelete(List<Integer> ids)throws Exception;

    void delete(Integer id)throws Exception;

    List<Function> getAll() throws Exception;

}