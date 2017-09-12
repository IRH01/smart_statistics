package com.hhly.smartdata.service.ybf;

import java.util.List;

import com.hhly.smartdata.model.ybf.DimYbfUrlCfg;
import com.hhly.smartdata.util.page.Page;

public interface DimYbfUrlCfgService {
	
	/**
	 * 根据id查询
	 * @param tblId
	 * @return
	 */
	public DimYbfUrlCfg getById(String tblId);
	
	/**
	 * 根据urlId查询
	 * @param urlId
	 * @return
	 */
	public DimYbfUrlCfg getByUrlId(String urlId);
	
	
	/**
	 * 分页，条件查询
	 * @param condition
	 * @param page
	 * @return
	 */
	public List<DimYbfUrlCfg> search(DimYbfUrlCfg condition,Page page);
	
	/**
	 * 插入
	 * @param value
	 * @return
	 */
	public boolean insert(DimYbfUrlCfg value);
	
	/**
	 * 更新
	 * @param value
	 * @return
	 */
	public boolean update(DimYbfUrlCfg value);
	
	/**
	 * 根据id删除
	 * @param tlbId
	 * @return
	 */
	public boolean deleteById(String tblId);
	
	/**
	 * 获取域名
	 * @return
	 */
	public List<String> getDomains();
	
	/**
	 * 通过域名查询
	 * @param domainName
	 * @return
	 */
	public List<DimYbfUrlCfg> getUrlCfgByDomain(String domainName);
}
