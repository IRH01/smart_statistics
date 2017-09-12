package com.hhly.smartdata.mapper.authentication;

import com.hhly.smartdata.model.authentication.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuRepository extends BaseRepository{

    public int delete(Menu condition){
        return template.delete("baseMenu.delete", condition);
    }

    public int delById(Integer id){
        return template.delete("baseMenu.delById", id);
    }

    public List<Menu> getMenuByPerms(List<String> permIds){
        return template.selectList("baseMenu.getMenuByPerms", permIds);
    }

    public int insert(Menu record){
        return template.insert("baseMenu.insert", record);
    }

    public List<Menu> searchMenu(Menu condition){
        return template.selectList("baseMenu.searchMenu", condition);
    }

    public int update(Menu record){
        return template.update("baseMenu.update", record);
    }

}