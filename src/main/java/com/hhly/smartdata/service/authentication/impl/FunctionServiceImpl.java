package com.hhly.smartdata.service.authentication.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hhly.smartdata.mapper.authentication.FunctionRepository;
import com.hhly.smartdata.model.authentication.Function;
import com.hhly.smartdata.model.authentication.Permission;
import com.hhly.smartdata.service.authentication.FunctionService;
import com.hhly.smartdata.service.authentication.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class FunctionServiceImpl implements FunctionService{

    @Resource
    private FunctionRepository functionRepository;
    @Resource
    private PermissionService permissionService;

    @Override
    public List<Function> getAll(){
        return functionRepository.search(null);//查询条件为null，即查询全部
    }

    @Override
    public void save(Function function){
        functionRepository.insert(function);
    }

    /**
     * 更新功能树
     *
     * @param funcTree 功能树
     * @param parentId 父节点id
     * @return Map {key:ztreeId,value:id} 用户更新页面ztree
     */
    public Map<String, Integer> updateFuncTree(JSONArray funcTree, Integer parentId){
        Map<String, Integer> ids = Maps.newHashMap();
        for(int i = 0; i < funcTree.size(); i++){
            JSONObject funcJson = funcTree.getJSONObject(i);
            Function func = new Function();
            func.setName(funcJson.getString("name"));
            func.setIndex(i + 1);
            func.setParentId(parentId);
            if(funcJson.getInteger("id") != null){
                func.setId(funcJson.getInteger("id"));
                update(func);
            }else{
                save(func);
            }
            ids.put(funcJson.getString("tId"), func.getId());
            if(funcJson.getJSONArray("children") != null){
                //遍历子集
                ids.putAll(updateFuncTree(funcJson.getJSONArray("children"), func.getId()));
            }
        }
        return ids;
    }

    @Override
    public Map<String, Integer> batchUpdateFuncs(JSONArray funcTree, Integer parentId){
        //1:更新功能树--树结构的function
        Map<String, Integer> result = updateFuncTree(funcTree, parentId);

        //2:查询所有的功能
        List<Function> allFuncs = getAll();

        //3:剔除1中的功能id，即为不存在功能id 进行删除
        Collection<Integer> existIds = result.values();
        for(Function func : allFuncs){
            if(!existIds.contains(func.getId())){
                delete(func.getId());
            }
        }
        return result;
    }

    @Override
    public void update(Function function){
        functionRepository.update(function);
    }

    @Override
    public void delete(Integer id){

        Permission permCondition = new Permission();
        permCondition.setFunctionId(id);
        permissionService.delete(permCondition);//删除功能下的权限--同时删除关联资源

        functionRepository.delete(id);
    }

    public List<Function> queryByRole(List<Integer> roleIds){
        return functionRepository.queryByRole(roleIds);
    }


}
