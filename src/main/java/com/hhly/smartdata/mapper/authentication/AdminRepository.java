package com.hhly.smartdata.mapper.authentication;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.authentication.Admin;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository extends BaseRepository{
    public int delete(Integer id){
        return template.delete("baseAdmin.delete", id);
    }

    public int deleteByUserId(Integer userId){
        return template.delete("baseAdmin.deleteByUserId", userId);
    }

    public int insert(Admin record){
        return template.insert("baseAdmin.insert", record);
    }

    public Admin get(Integer id){
        return template.selectOne("baseAdmin.get", id);
    }

    public int update(Admin record){
        return template.update("baseAdmin.update", record);
    }

    public List<Admin> searchAdmins(Admin condition, Page page){
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Admin> list = template.selectList("baseAdmin.searchAdmins", condition);
        PageInfo<Admin> pageInfo = new PageInfo<Admin>(list);
        page.setTotalPage(pageInfo.getPages());
        page.setTotalRecord(pageInfo.getTotal());
        return list;
    }


}