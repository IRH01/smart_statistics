package com.hhly.smartdata.service.partner.impl;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.model.partner.LmdzPresentLog;
import com.hhly.smartdata.mapper.partner.LmdzPresentLogReposity;
import com.hhly.smartdata.service.partner.LmdzPresentLogService;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class LmdzPresentLogServiceImpl implements LmdzPresentLogService {
	/**
	 * 数据取两位小数
	 */
	private static DecimalFormat df = new DecimalFormat("################0.00");   
	
	@Autowired
	private LmdzPresentLogReposity lmdzPresentLogReposity;

	@Override
	public PageInfo<LmdzPresentLog> find(
			Map<String, Object> conditionMap, int pageNumber, int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<LmdzPresentLog> values = lmdzPresentLogReposity.find(conditionMap);
		PageInfo<LmdzPresentLog> pageInfo = new PageInfo<LmdzPresentLog>(values);
		return pageInfo;
	}

	@Override
	public String export(Map<String, Object> conditionMap) {
		int count = lmdzPresentLogReposity.count(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "礼物对账报表";
		//多个分页，导出为Zip文件
		if(pageCount > 1){
			return exportZip(fileName,pageCount,conditionMap);
		}else{
		//单页，导出为excel文件
			return exportExcel(fileName,conditionMap);
		}
	}

	@Override
	public boolean canExport(Map<String, Object> conditionMap) {
		int count = lmdzPresentLogReposity.count(conditionMap);
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
	
	/**
	 * 导出为zip
	 * @param fileName
	 * @param pageCount
	 * @param conditionMap
	 * @param response
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
				List<LmdzPresentLog> valueList = this.findByPage(conditionMap, pageNumber, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
				List<List<Object>> listRow = new ArrayList<List<Object>>();
				for(int i = 0;i < valueList.size();i++){
					listRow.add(getValueList(valueList.get(i)));
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
		List<LmdzPresentLog> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
		List<List<Object>> listRow = new ArrayList<List<Object>>();
		for(int i = 0;i < valueList.size();i++){
			listRow.add(getValueList(valueList.get(i)));
		}
		String filePath = folderName + ".xls"; 
		//生成excel文件
		File excelFile = ExportExcellUtil.createExcelFile(filePath,fileName , listColumn, listRow,23);
		return excelFile.getPath();
	}
	
	private List<LmdzPresentLog> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize ) {
		PageHelper.startPage(pageNumber, pageSize);
		List<LmdzPresentLog> values = lmdzPresentLogReposity.find(conditionMap);
		return values;
	}
	
	private List<String> getTableHeaderList(){
		List<String> listColumn = new ArrayList<String>();
		listColumn.add("单号");
		listColumn.add("时间");
		listColumn.add("主播名称");
		listColumn.add("名称");
		listColumn.add("数量");
		listColumn.add("金额");
		return listColumn;
	}
	
	private List<Object> getValueList(LmdzPresentLog value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getPresentOrderNo());
		valueList.add(DateUtil.formatDateYYYYMMDDHHmmss(value.getPresentTime()));
		valueList.add(value.getAnchorName());
		valueList.add(value.getPresentName());
		valueList.add(value.getPresentCount());
		valueList.add(df.format(value.getSettleAmount()));
		return valueList;
	}
}
