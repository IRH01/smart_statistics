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
import com.hhly.smartdata.model.game.operative.UserRechargeSummary;
import com.hhly.smartdata.mapper.game.operative.UserRechargeSummaryReposity;
import com.hhly.smartdata.service.game.operative.UserRechargeSummaryService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public  class UserRechargeSummaryServiceImpl implements UserRechargeSummaryService{
	@Autowired
	UserRechargeSummaryReposity userRechargeSummaryReposity;
	
	@Override
	public JSONObject find(Map<String, Object> conditionMap,int pageNumber, int pageSize ) {
		PageHelper.startPage(pageNumber, pageSize);
		List<UserRechargeSummary> values = userRechargeSummaryReposity.find(conditionMap);
		PageInfo<UserRechargeSummary> pageInfo = new PageInfo<UserRechargeSummary>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	public boolean canExport(Map<String, Object> conditionMap){
		int count = userRechargeSummaryReposity.count(conditionMap);
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
	
	public String export(Map<String, Object> conditionMap){
		int count = userRechargeSummaryReposity.count(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "用户充值汇总";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZip(fileName,pageCount,conditionMap);
		}else{
		//单页，导出为excel文件
			return exportExcel(fileName,conditionMap);
		}
	}
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 */
	private String exportExcel(String fileName,Map<String, Object> conditionMap){
		try {
			//创建临时文件路径，确保可存储
			FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
		List<String> listColumn = getTableHeaderList();
		List<UserRechargeSummary> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,30);
		return excelFile.getPath();
	}
	
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @return
	 */
	private String exportZip(String fileName,int pageCount,Map<String, Object> conditionMap){
		try {
			String folderName = fileName + System.currentTimeMillis();
			File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
			if(null == fileDir){
				return null;
			}
			List<String> listColumn = getTableHeaderList();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<UserRechargeSummary> valueList = this.findByPage(conditionMap, pageNumber, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getValueList(valueList.get(i)));
				}
				String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls"; 
				ExportExcellUtil.createExcelFile(filePath,fileName,listColumn,listRow,30);
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
	
	private List<UserRechargeSummary> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize ) {
		PageHelper.startPage(pageNumber, pageSize);
		List<UserRechargeSummary> values = userRechargeSummaryReposity.find(conditionMap);
		return values;
	}
	
	private List<String> getTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("渠道号");
		listColumn.add("渠道名称");
		listColumn.add("用户名");
		listColumn.add("用户昵称");
		listColumn.add("国家");
		listColumn.add("平台");
		listColumn.add("游戏");
		listColumn.add("服务器");
		listColumn.add("手机号");
		listColumn.add("邮箱地址");
		listColumn.add("注册时间");
		listColumn.add("充值金额");
		return listColumn;
	}
	
	private List<Object> getValueList(UserRechargeSummary value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getChannelId());
		valueList.add(value.getChannelName());
		valueList.add(value.getUserId());
		valueList.add(value.getNickName());
		valueList.add(value.getCountryName());
		valueList.add(value.getPlatformTerminalName());
		valueList.add(value.getPlatformName());
		valueList.add(value.getServerName());
		valueList.add(value.getPhone());
		valueList.add(value.getEmail());
		valueList.add(value.getRegTime());
		valueList.add(value.getTotalAmount());
		return valueList;
	}
}
