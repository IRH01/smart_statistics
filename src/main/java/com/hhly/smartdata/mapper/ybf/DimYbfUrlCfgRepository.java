package com.hhly.smartdata.mapper.ybf;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.DimYbfUrlCfg;
import com.hhly.smartdata.util.page.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DimYbfUrlCfgRepository extends BaseRepository{

    /**
     * 通过id查询
     *
     * @param tblId
     * @return
     */
    public DimYbfUrlCfg getById(String tblId){
        DimYbfUrlCfg value = null;
        value = template.selectOne("dimYbfUrlCfg.getById", tblId);
        return value;
    }

    /**
     * 通过urlId查询
     *
     * @param tblId
     * @return
     */
    public DimYbfUrlCfg getByUrlId(String urlId){
        DimYbfUrlCfg value = null;
        value = template.selectOne("dimYbfUrlCfg.getByUrlId", urlId);
        return value;
    }

    /**
     * 根据条件分页查询
     *
     * @param condition 查询条件
     * @param page      分页条件
     * @return
     */
    public List<DimYbfUrlCfg> search(DimYbfUrlCfg condition, Page page){
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DimYbfUrlCfg> list = template.selectList("dimYbfUrlCfg.search", condition);
        PageInfo<DimYbfUrlCfg> pageInfo = new PageInfo<DimYbfUrlCfg>(list);
        page.setTotalPage(pageInfo.getPages());
        page.setTotalRecord(pageInfo.getTotal());
        return list;
    }

    /**
     * 插入
     *
     * @param value
     * @return
     */
    public boolean insert(DimYbfUrlCfg value){
        int result = template.insert("dimYbfUrlCfg.insert", value);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 更新
     *
     * @param value
     * @return
     */
    public boolean update(DimYbfUrlCfg value){
        int result = template.update("dimYbfUrlCfg.update", value);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 根据id删除
     *
     * @param tblId
     * @return
     */
    public boolean deleteById(String tblId){
        int result = template.delete("dimYbfUrlCfg.deleteById", tblId);
        if(result > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取域名
     *
     * @return
     */
    public List<String> getDomains(){
        List<String> domainsList = template.selectList("dimYbfUrlCfg.getDomains");
        return domainsList;
    }

    /**
     * 通过域名查询
     *
     * @param domainName
     * @return
     */
    public List<DimYbfUrlCfg> getUrlCfgByDomain(String domainName){
        List<DimYbfUrlCfg> values = template.selectList("dimYbfUrlCfg.getUrlCfgByDomain", domainName);
        return values;
    }
}
