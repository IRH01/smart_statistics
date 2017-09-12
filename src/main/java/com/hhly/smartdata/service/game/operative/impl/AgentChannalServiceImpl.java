package com.hhly.smartdata.service.game.operative.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.model.game.ChannalAgent;
import com.hhly.smartdata.model.game.ChannalCostDetails;
import com.hhly.smartdata.model.game.ChannalDetails;
import com.hhly.smartdata.model.game.ChannalRegistDetails;
import com.hhly.smartdata.mapper.game.AgentChannalReposity;
import com.hhly.smartdata.service.game.operative.AgentChannalService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class AgentChannalServiceImpl implements AgentChannalService {
	@Autowired
	private AgentChannalReposity agentChannalReposity;
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalAgent> values = agentChannalReposity.find(conditionMap);
		PageInfo<ChannalAgent> pageInfo = new PageInfo<ChannalAgent>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	@Override
	public JSONObject findChannelDetails(Map<String, Object> conditionMap,
			int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalDetails> values = agentChannalReposity.findChannelDetails(conditionMap);
		PageInfo<ChannalDetails> pageInfo = new PageInfo<ChannalDetails>(values);
		return JSONObject.fromObject(pageInfo);
	}

	@Override
	public boolean canExport(Map<String, Object> conditionMap) {
		int count = agentChannalReposity.countChannelDetails(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return false;
		}
		return true;
	}

	@Override
	public String export(Map<String, Object> conditionMap) {
		int count = agentChannalReposity.countChannelDetails(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "渠道详情";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZip(fileName,pageCount,conditionMap,0);
		}else{
		//单页，导出为excel文件
			return exportExcel(fileName,conditionMap,0);
		}
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @param response
	 */
	private String exportZip(String fileName,int pageCount,Map<String, Object> conditionMap,int type){
		try {
			String folderName = fileName + System.currentTimeMillis();
			File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
			if(null == fileDir){
				return null;
			}
			List<String> listColumn = getQTableHeaderList();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<ChannalDetails> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getQValueList(valueList.get(i)));
				}
				String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls"; 
				ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
			}
			File zipFile = FileUtil.zipDIR(fileDir.getPath(), FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName + ".zip");
			//打包完成，删除目录
			FileUtil.delete(fileDir.getPath());
			return zipFile.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 */
	private String exportExcel(String fileName,Map<String, Object> conditionMap,int type){
		try {
			//创建临时文件路径，确保可存储
			FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
		List<String> listColumn = getQTableHeaderList();
		List<ChannalDetails> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getQValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<String> getQTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("渠道名称");
		listColumn.add("注册量");
		listColumn.add("投入金额");
		listColumn.add("启动次数");
		listColumn.add("平均使用时长");
		return listColumn;
	}
	
	private List<Object> getQValueList(ChannalDetails value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getChannelId());
		valueList.add(value.getRegCount());
		valueList.add(value.getRechargeAmount());
		valueList.add(value.getStartTime());
		valueList.add(value.getAvgTime());
		return valueList;
	}
	
	private List<ChannalDetails> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalDetails> values = agentChannalReposity.findChannelDetails(conditionMap);
		return values;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public JSONObject findChannelCostDetails(Map<String, Object> conditionMap,
			int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalCostDetails> values = agentChannalReposity.findChannelCostDetails(conditionMap);
		PageInfo<ChannalCostDetails> pageInfo = new PageInfo<ChannalCostDetails>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	@Override
	public boolean canExportCost(Map<String, Object> conditionMap) {
		int count = agentChannalReposity.countChannelCostDetails(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return false;
		}
		return true;
	}

	@Override
	public String exportCost(Map<String, Object> conditionMap) {
		int count = agentChannalReposity.countChannelDetails(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "投入详情";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZipCost(fileName,pageCount,conditionMap,0);
		}else{
		//单页，导出为excel文件
			return exportExcelCost(fileName,conditionMap,0);
		}
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @param response
	 */
	private String exportZipCost(String fileName,int pageCount,Map<String, Object> conditionMap,int type){
		try {
			String folderName = fileName + System.currentTimeMillis();
			File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
			if(null == fileDir){
				return null;
			}
			List<String> listColumn = getCostQTableHeaderList();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<ChannalCostDetails> valueList = this.findCostByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getCostQValueList(valueList.get(i)));
				}
				String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls"; 
				ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
			}
			File zipFile = FileUtil.zipDIR(fileDir.getPath(), FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName + ".zip");
			//打包完成，删除目录
			FileUtil.delete(fileDir.getPath());
			return zipFile.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 */
	private String exportExcelCost(String fileName,Map<String, Object> conditionMap,int type){
		try {
			//创建临时文件路径，确保可存储
			FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
		List<String> listColumn = getCostQTableHeaderList();
		List<ChannalCostDetails> valueList = this.findCostByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getCostQValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<String> getCostQTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("订单号");
		listColumn.add("用户名");
		listColumn.add("用户ID");
		listColumn.add("投入金额");
		listColumn.add("平台名称");
		listColumn.add("支付类型");
		listColumn.add("时间");
		return listColumn;
	}
	
	private List<Object> getCostQValueList(ChannalCostDetails value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getOrderNo());
		valueList.add(value.getUserName());
		valueList.add(value.getUserId());
		valueList.add(value.getCostAmount());
		valueList.add(value.getPlatformId());
		valueList.add(value.getPayType());
		valueList.add(value.getCostTime());
		return valueList;
	}
	
	private List<ChannalCostDetails> findCostByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalCostDetails> values = agentChannalReposity.findChannelCostDetails(conditionMap);
		return values;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public JSONObject findChannelRegistDetails(Map<String, Object> conditionMap,
			int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalRegistDetails> values = agentChannalReposity.findChannelRegistDetails(conditionMap);
		PageInfo<ChannalRegistDetails> pageInfo = new PageInfo<ChannalRegistDetails>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	@Override
	public boolean canExportRegist(Map<String, Object> conditionMap) {
		int count = agentChannalReposity.countChannelRegistDetails(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return false;
		}
		return true;
	}

	@Override
	public String exportRegist(Map<String, Object> conditionMap) {
		int count = agentChannalReposity.countChannelDetails(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "注册详情";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZipRegist(fileName,pageCount,conditionMap,0);
		}else{
		//单页，导出为excel文件
			return exportExcelRegist(fileName,conditionMap,0);
		}
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @param response
	 */
	private String exportZipRegist(String fileName,int pageCount,Map<String, Object> conditionMap,int type){
		try {
			String folderName = fileName + System.currentTimeMillis();
			File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
			if(null == fileDir){
				return null;
			}
			List<String> listColumn = getRegistQTableHeaderList();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<ChannalRegistDetails> valueList = this.findRegistByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getRegistQValueList(valueList.get(i)));
				}
				String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls"; 
				ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
			}
			File zipFile = FileUtil.zipDIR(fileDir.getPath(), FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName + ".zip");
			//打包完成，删除目录
			FileUtil.delete(fileDir.getPath());
			return zipFile.getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 */
	private String exportExcelRegist(String fileName,Map<String, Object> conditionMap,int type){
		try {
			//创建临时文件路径，确保可存储
			FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
		List<String> listColumn = getRegistQTableHeaderList();
		List<ChannalRegistDetails> valueList = this.findRegistByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getRegistQValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<String> getRegistQTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("用户ID");
		listColumn.add("用户名");
		listColumn.add("注册地址");
		listColumn.add("注册时间");
		return listColumn;
	}
	
	private List<Object> getRegistQValueList(ChannalRegistDetails value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getUserId());
		valueList.add(value.getUserName());
		valueList.add(value.getRegistAddress());
		valueList.add(value.getRegistTime());
		return valueList;
	}
	
	private List<ChannalRegistDetails> findRegistByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ChannalRegistDetails> values = agentChannalReposity.findChannelRegistDetails(conditionMap);
		return values;
	}
	
}
