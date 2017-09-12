package com.hhly.smartdata.service.partner.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.mapper.partner.LmdzBettingLogReposity;
import com.hhly.smartdata.model.partner.LmdzBettingLog;
import com.hhly.smartdata.service.partner.LmdzBettingLogService;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.ExportExcellUtil;
import com.hhly.smartdata.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LmdzBettingLogServiceImpl implements LmdzBettingLogService{
    /**
     * 数据取两位小数
     */
    private static DecimalFormat df = new DecimalFormat("################0.00");

    @Autowired
    private LmdzBettingLogReposity lmdzBettingLogReposity;

    @Override
    public PageInfo<LmdzBettingLog> find(
            Map<String, Object> conditionMap, int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<LmdzBettingLog> values = lmdzBettingLogReposity.find(conditionMap);
        PageInfo<LmdzBettingLog> pageInfo = new PageInfo<LmdzBettingLog>(values);
        return pageInfo;
    }

    @Override
    public String export(Map<String, Object> conditionMap){
        int count = lmdzBettingLogReposity.count(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return null;
        }
        String fileName = "礼物对账报表";
        //多个分页，导出为Zip文件
        if(pageCount > 1){
            return exportZip(fileName, pageCount, conditionMap);
        }else{
            //单页，导出为excel文件
            return exportExcel(fileName, conditionMap);
        }
    }

    @Override
    public boolean canExport(Map<String, Object> conditionMap){
        int count = lmdzBettingLogReposity.count(conditionMap);
        int pageCount = count / FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER;
        if(count % FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER > 0){
            pageCount++;
        }
        //分页数量不能超过最大文件数
        if(pageCount > FileConstants.EXCEL_LIMIT_FILE_SIZE){
            return false;
        }
        return true;
    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     * @param response
     */
    private String exportZip(String fileName, int pageCount, Map<String, Object> conditionMap){
        try{
            String folderName = fileName + System.currentTimeMillis();
            File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
            if(null == fileDir){
                return null;
            }
            List<String> listColumn = getTableHeaderList();
            for(int pageNumber = 1; pageNumber <= pageCount; pageNumber++){
                List<LmdzBettingLog> valueList = this.findByPage(conditionMap, pageNumber, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
                List<List<Object>> listRow = new ArrayList<List<Object>>();
                for(int i = 0; i < valueList.size(); i++){
                    listRow.add(getValueList(valueList.get(i)));
                }
                String filePath = fileDir.getPath() + System.getProperty("file.separator") + fileName + pageNumber + ".xls";
                ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
            }
            File zipFile = FileUtil.zipDIR(fileDir.getPath(), FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName + ".zip");
            //打包完成，删除目录
            FileUtil.delete(fileDir.getPath());
            return zipFile.getPath();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 导出为zip
     *
     * @param fileName
     * @param pageCount
     * @param conditionMap
     */
    private String exportExcel(String fileName, Map<String, Object> conditionMap){
        try{
            //创建临时文件路径，确保可存储
            FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
        List<String> listColumn = getTableHeaderList();
        List<LmdzBettingLog> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
        List<List<Object>> listRow = new ArrayList<List<Object>>();
        for(int i = 0; i < valueList.size(); i++){
            listRow.add(getValueList(valueList.get(i)));
        }
        String filePath = folderName + ".xls";
        //生成excel文件
        File excelFile = ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
        return excelFile.getPath();
    }

    private List<LmdzBettingLog> findByPage(Map<String, Object> conditionMap, int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<LmdzBettingLog> values = lmdzBettingLogReposity.find(conditionMap);
        return values;
    }

    private List<String> getTableHeaderList(){
        List<String> listColumn = new ArrayList<String>();
        listColumn.add("时间");
        listColumn.add("局号");
        listColumn.add("房间号");
        listColumn.add("游戏类型");
        listColumn.add("有效投注");
        listColumn.add("收益");
        return listColumn;
    }

    private List<Object> getValueList(LmdzBettingLog value){
        List<Object> valueList = new ArrayList<Object>();
        valueList.add(DateUtil.formatDateYYYYMMDDHHmmss(value.getSettleTime()));
        valueList.add(value.getBureauNo());
        valueList.add(value.getRoomNo());
        valueList.add(value.getGameTypeName());
        valueList.add(df.format(Double.parseDouble(value.getBetAmount())));
        valueList.add(df.format(value.getIncomeAmount()));
        return valueList;
    }
}
