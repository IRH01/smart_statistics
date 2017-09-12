package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Function;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionMapper{

    List<Function> queryByRole(List<Integer> roleIds);

    int insert(Function record);

    Function get(Integer id);

    int update(Function record);

    List<Function> search(Function condition);

    void batchDelete(List<Integer> ids);

    void delete(Integer id);

    List<Function> getAll();

}