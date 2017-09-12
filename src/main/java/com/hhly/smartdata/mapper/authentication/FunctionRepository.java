package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Function;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FunctionRepository extends BaseRepository{

    public List<Function>  queryByRole(List<Integer> roleIds){
        return template.selectList("baseFunction.queryByRole",roleIds);
    }

    public int insert(Function record){
        return template.insert("baseFunction.insert",record);
    }

    public Function get(Integer id){
        return template.selectOne("baseFunction.get",id);
    }

    public int update(Function record){
        return template.update("baseFunction.update",record);
    }

    public List<Function> search(Function condition){
        return template.selectList("baseFunction.search",condition);
    }
    public void batchDelete(List<Integer> ids){
        template.delete("baseFunction.batchDelete",ids);
    }

    public void delete(Integer id){
        template.delete("baseFunction.delete", id);
    }

    public List<Function>  getAll(){
        return template.selectList("baseFunction.getAll");
    }

}