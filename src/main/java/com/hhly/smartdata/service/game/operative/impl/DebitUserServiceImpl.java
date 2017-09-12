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
import com.hhly.smartdata.model.game.DebitUser;
import com.hhly.smartdata.mapper.game.DebitUserReposity;
import com.hhly.smartdata.service.game.operative.DebitUserService;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;

@Service
public class DebitUserServiceImpl implements DebitUserService {
	@Autowired
	private DebitUserReposity debitUserReposity;
	@Override
	public JSONObject find(Map<String, Object> conditionMap, int pageNumber,
			int pageSize) {
		PageHelper.startPage(pageNumber, pageSize);
		List<DebitUser> values = debitUserReposity.find(conditionMap);
		PageInfo<DebitUser> pageInfo = new PageInfo<DebitUser>(values);
		return JSONObject.fromObject(pageInfo);
	}
	@Override
	public boolean canExport(Map<String, Object> conditionMap) {
		int count = debitUserReposity.findCount(conditionMap);
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
		int count = debitUserReposity.findCount(conditionMap);
		int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
		if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
			pageCount ++;
		}
		//分页数量不能超过最大文件数
		if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
			return null;
		}
		String fileName = "用户管理";
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
				List<DebitUser> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
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
		List<DebitUser> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER,type);
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
		listColumn.add("用户名");
		listColumn.add("用户昵称");
		listColumn.add("乐盈币余额");
		listColumn.add("乐盈券余额");
		listColumn.add("电竞币余额");
		listColumn.add("德州币余额");
		return listColumn;
	}
	
	private List<Object> getQValueList(DebitUser value){
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(value.getUserId());
		valueList.add(value.getNickName());
		valueList.add(value.getLybCnt());
		valueList.add(value.getLyqCnt());
		valueList.add(value.getDjbCnt());
		valueList.add(value.getDzbCnt());
		return valueList;
	}
	
	private List<DebitUser> findByPage(Map<String, Object> conditionMap,int pageNumber, int pageSize,int type) {
		PageHelper.startPage(pageNumber, pageSize);
		List<DebitUser> values = debitUserReposity.find(conditionMap);
		return values;
	}
}
