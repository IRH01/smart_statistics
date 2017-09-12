package com.hhly.smartdata.mapper.authentication;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.authentication.User;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends BaseRepository{
    public int delete(Integer id){
        return template.delete("baseUser.delete", id);
    }

    public int insert(User record){
        return template.insert("baseUser.insert", record);
    }

    public User getUser(Integer id){
        return template.selectOne("baseUser.getUser", id);
    }

    public User getByUsername(String username){
        return template.selectOne("baseUser.getByUsername", username);
    }

    public int update(User record){
        return template.update("baseUser.update", record);
    }

    public List<User> searchUsers(User condition, Page page){
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<User> list = template.selectList("baseUser.searchUsers", condition);
        PageInfo<User> pageInfo = new PageInfo<User>(list);
        page.setTotalPage(pageInfo.getPages());
        page.setTotalRecord(pageInfo.getTotal());
        return list;
    }
}