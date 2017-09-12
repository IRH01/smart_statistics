package com.hhly.smartdata.service.ybf.impl;

import com.hhly.smartdata.mapper.ybf.DimYbfUrlCfgRepository;
import com.hhly.smartdata.model.ybf.DimYbfUrlCfg;
import com.hhly.smartdata.service.ybf.DimYbfUrlCfgService;
import com.hhly.smartdata.util.RandomUtil;
import com.hhly.smartdata.util.page.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DimYbfUrlCfgServiceImpl implements DimYbfUrlCfgService{
    @Autowired
    private DimYbfUrlCfgRepository dimYbfUrlCfgRepository;

    /**
     * 通过id查询
     */
    @Override
    public DimYbfUrlCfg getById(String tblId){
        return dimYbfUrlCfgRepository.getById(tblId);
    }


    /**
     * 条件分页查询
     */
    @Override
    public List<DimYbfUrlCfg> search(DimYbfUrlCfg condition, Page page){
        return dimYbfUrlCfgRepository.search(condition, page);
    }

    /**
     * 插入
     */
    @Override
    public boolean insert(DimYbfUrlCfg value){
        if(null == value
                || StringUtils.isEmpty(value.getUrlName()) || StringUtils.isEmpty(value.getUrlName().trim())
                || StringUtils.isEmpty(value.getDomainName()) || StringUtils.isEmpty(value.getDomainName().trim())){
            return false;
        }
        //urlId不能相同
        if(null != this.dimYbfUrlCfgRepository.getByUrlId(value.getUrlId())){
            return false;
        }
        value.setTblId(RandomUtil.getUUID());
        value.setCreatedDate(new Date());
        return dimYbfUrlCfgRepository.insert(value);
    }

    /**
     * 更新
     */
    @Override
    public boolean update(DimYbfUrlCfg value){
        DimYbfUrlCfg dimYbfUrlCfg = this.dimYbfUrlCfgRepository.getByUrlId(value.getUrlId());
        //修改后的urlId不能与数据库其他数据相同
        if(dimYbfUrlCfg != null && !value.getTblId().equals(dimYbfUrlCfg.getTblId())){
            return false;
        }
        value.setUpdatedDate(new Date());
        return dimYbfUrlCfgRepository.update(value);
    }

    /**
     * 根据id删除
     */
    @Override
    public boolean deleteById(String tblId){
        return dimYbfUrlCfgRepository.deleteById(tblId);
    }


    /**
     * 根据urlId查询
     */
    @Override
    public DimYbfUrlCfg getByUrlId(String urlId){
        return this.dimYbfUrlCfgRepository.getByUrlId(urlId);
    }


    /**
     * 获取域名
     */
    @Override
    public List<String> getDomains(){
        return this.dimYbfUrlCfgRepository.getDomains();
    }


    /**
     * 通过域名查询
     */
    @Override
    public List<DimYbfUrlCfg> getUrlCfgByDomain(String domainName){
        return this.dimYbfUrlCfgRepository.getUrlCfgByDomain(domainName);
    }
}
