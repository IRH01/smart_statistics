package com.hhly.smartdata.service.ybf;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hhly.smartdata.model.ybf.ActCountInfo;

public interface InfoActFactService {
	
	/**
	 * 根据日期计算，获取点击量信息
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			要查询的域名Id
	 * @return
	 */
	public List<ActCountInfo> countClickInfo(Date startDate,Date endDate,String domainId);
	
	/**
	 * 根据日期计算，获取IP数量信息
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			要查询的域名Id
	 * @return
	 */
	public List<ActCountInfo> countIPInfo(Date startDate,Date endDate,String domainId);
	
	/**
	 * 根据日期和域名，统计停留时长
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			要查询的域名Id
	 * @return
	 */
	public List<ActCountInfo> countStayTimeInfo(Date startDate,Date endDate,String domainId);
	
	/**
	 * 导出数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	public void exportClickCount(HttpServletResponse response,Date startDate, Date endDate,
			String domainId);
	
	/**
	 * 导出IP统计数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	public void exportIPCount(HttpServletResponse response,Date startDate, Date endDate,
			String domainId);
	
	/**
	 * 导出数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	public void exportStayTimeInfo(HttpServletResponse response,Date startDate, Date endDate,
			String domainId);
	
	/**
	 * 导出数据为excel
	 * @param response
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 */
	public void exportToExcel(HttpServletResponse response,Date startDate, Date endDate,
			String domainId);

	/**
	 * 根据日期和域名，统计日更新资讯数量
	 * @param startDate
	 * @param endDate
	 * @param domainId
	 * 			要查询的域名Id
	 * @return
	 */
	public List<ActCountInfo> countInfoCnt(Date startDate, Date endDate,
			String domainId);
}
