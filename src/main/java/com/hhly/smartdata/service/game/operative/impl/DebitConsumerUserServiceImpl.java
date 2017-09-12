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
import com.hhly.smartdata.model.game.ConsumerUser;
import com.hhly.smartdata.mapper.game.DebitConsumerUserReposity;
import com.hhly.smartdata.service.game.operative.DebitConsumerUserService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class DebitConsumerUserServiceImpl implements DebitConsumerUserService {
	@Autowired
	private DebitConsumerUserReposity debitConsumerUserReposity;
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ConsumerUser> values = debitConsumerUserReposity.find(conditionMap);
		PageInfo<ConsumerUser> pageInfo = new PageInfo<ConsumerUser>(values);
		return JSONObject.fromObject(pageInfo);
	}
	@Override
	public JSONObject findCon(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ConsumerUser> values = debitConsumerUserReposity.findCon(conditionMap);
		PageInfo<ConsumerUser> pageInfo = new PageInfo<ConsumerUser>(values);
		return JSONObject.fromObject(pageInfo);
	}
	
	
	
	
	
	@Override
	public boolean canExport(Map<String, Object> conditionMap) {
		int count = debitConsumerUserReposity.findCount(conditionMap);
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
		int count = debitConsumerUserReposity.findCount(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "消费明细";
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
				List<ConsumerUser> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
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
		List<ConsumerUser> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
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
		listColumn.add("订单号");
		listColumn.add("用户名");
		listColumn.add("用户昵称");
		listColumn.add("交易时间");
		listColumn.add("交易类型");
		listColumn.add("交易前余额");
		listColumn.add("交易金额");
		listColumn.add("余额");
		return listColumn;
	}
	
	private List<Object> getQValueList(ConsumerUser value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getOrderNo());
		valueList.add(value.getUserId());
		valueList.add(value.getNickName());
		valueList.add(value.getHandleTime());
		valueList.add(value.getRechargeType());
		valueList.add(value.getBeforeCash());
		valueList.add(value.getLyb());
		valueList.add(value.getAfterCash());
		return valueList;
	}
	
	private List<ConsumerUser> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ConsumerUser> values = debitConsumerUserReposity.find(conditionMap);
		return values;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean canExportList(Map<String, Object> conditionMap) {
		int count = debitConsumerUserReposity.findCountList(conditionMap);
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
	public String exportList(Map<String, Object> conditionMap) {
		int count = debitConsumerUserReposity.findCountList(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "消费列表";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZipList(fileName,pageCount,conditionMap,0);
		}else{
		//单页，导出为excel文件
			return exportExcelList(fileName,conditionMap,0);
		}
	}
	
	

	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @param response
	 */
	private String exportZipList(String fileName,int pageCount,Map<String, Object> conditionMap,int type){
		try {
			String folderName = fileName + System.currentTimeMillis();
			File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
			if(null == fileDir){
				return null;
			}
			List<String> listColumn = getQTableHeaderList1();
			for(int pageNumber = 1;pageNumber <= pageCount;pageNumber++){
				List<ConsumerUser> valueList = this.findByPageList(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getQValueList1(valueList.get(i)));
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
	private String exportExcelList(String fileName,Map<String, Object> conditionMap,int type){
		try {
			//创建临时文件路径，确保可存储
			FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
		List<String> listColumn = getQTableHeaderList1();
		List<ConsumerUser> valueList = this.findByPageList(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getQValueList1(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<String> getQTableHeaderList1(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("订单号");
		listColumn.add("用户名");
		listColumn.add("用户昵称");
		listColumn.add("渠道号");
		listColumn.add("游戏");
		listColumn.add("服务器");
		listColumn.add("交易金额");
		listColumn.add("交易类型");
		listColumn.add("交易提交时间");
		listColumn.add("交易时间");
		listColumn.add("交易状态");
		
		return listColumn;
	}
	
	private List<Object> getQValueList1(ConsumerUser value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getOrderNo());
		valueList.add(value.getUserId());
		valueList.add(value.getNickName());
		valueList.add(value.getChannelId());
		valueList.add(value.getPlatformName());
		valueList.add(value.getServerName());
		valueList.add(value.getLyb());
		valueList.add(value.getRechargeType());
		valueList.add(value.getApplyTime());
		valueList.add(value.getHandleTime());
		valueList.add(value.getStatus());
		return valueList;
	}
	
	private List<ConsumerUser> findByPageList(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<ConsumerUser> values = debitConsumerUserReposity.findCon(conditionMap);
		return values;
	}
	
}
