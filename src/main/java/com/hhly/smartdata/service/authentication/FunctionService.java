package com.hhly.smartdata.service.authentication;

import com.alibaba.fastjson.JSONArray;
import com.hhly.smartdata.model.authentication.Function;

import java.util.List;
import java.util.Map;


public interface FunctionService{
    List<Function> getAll();

    void save(Function function);

    /**
     * @param funcTree 待更新功能树
     * @return 已更新功能map key:ztreeId,value:id
     */
    Map<String, Integer> batchUpdateFuncs(JSONArray funcTree, Integer parentId);

    void update(Function function);

    void delete(Integer id);

    List<Function> queryByRole(List<Integer> roleIds);


}
