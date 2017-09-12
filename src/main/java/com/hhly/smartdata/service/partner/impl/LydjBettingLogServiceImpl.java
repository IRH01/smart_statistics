package com.hhly.smartdata.service.partner.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.constant.FileConstants;
import com.hhly.smartdata.mapper.partner.LydjBettingLogReposity;
import com.hhly.smartdata.model.partner.LydjBettingLog;
import com.hhly.smartdata.service.partner.LydjBettingLogService;
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
public class LydjBettingLogServiceImpl implements LydjBettingLogService{
    /**
     * 数据取两位小数
     */
    private static DecimalFormat df = new DecimalFormat("################0.00");

    @Autowired
    private LydjBettingLogReposity lydjBettingLogReposity;

    @Override
    public PageInfo<LydjBettingLog> find(
            Map<String, Object> conditionMap, int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<LydjBettingLog> values = lydjBettingLogReposity.find(conditionMap);
        PageInfo<LydjBettingLog> pageInfo = new PageInfo<LydjBettingLog>(values);
        return pageInfo;
    }

    public String export(Map<String, Object> conditionMap, int type){
        int count = lydjBettingLogReposity.count(conditionMap);
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
            return exportZip(fileName, pageCount, conditionMap, type);
        }else{
            //单页，导出为excel文件
            return exportExcel(fileName, conditionMap, type);
        }
    }

    @Override
    public boolean canExport(Map<String, Object> conditionMap){
        int count = lydjBettingLogReposity.count(conditionMap);
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
    private String exportZip(String fileName, int pageCount, Map<String, Object> conditionMap, int type){
        try{
            String folderName = fileName + System.currentTimeMillis();
            File fileDir = FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + folderName);
            if(null == fileDir){
                return null;
            }
            List<String> listColumn = getTableHeaderList(type);
            for(int pageNumber = 1; pageNumber <= pageCount; pageNumber++){
                List<LydjBettingLog> valueList = this.findByPage(conditionMap, pageNumber, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
                List<List<Object>> listRow = new ArrayList<List<Object>>();
                for(int i = 0; i < valueList.size(); i++){
                    listRow.add(getValueList(valueList.get(i), type));
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
    private String exportExcel(String fileName, Map<String, Object> conditionMap, int type){
        try{
            //创建临时文件路径，确保可存储
            FileUtil.createDir(FileConstants.EXCEL_TEMP_FILE_FOLDER);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
        String folderName = FileConstants.EXCEL_TEMP_FILE_FOLDER + System.getProperty("file.separator") + fileName + System.currentTimeMillis();
        List<String> listColumn = getTableHeaderList(type);
        List<LydjBettingLog> valueList = this.findByPage(conditionMap, 1, FileConstants.EXCEL_SINGLE_SHEET_ROW_NUMBER);
        List<List<Object>> listRow = new ArrayList<List<Object>>();
        for(int i = 0; i < valueList.size(); i++){
            listRow.add(getValueList(valueList.get(i), type));
        }
        String filePath = folderName + ".xls";
        //生成excel文件
        File excelFile = ExportExcellUtil.createExcelFile(filePath, fileName, listColumn, listRow, 23);
        return excelFile.getPath();
    }

    private List<LydjBettingLog> findByPage(Map<String, Object> conditionMap, int pageNumber, int pageSize){
        PageHelper.startPage(pageNumber, pageSize);
        List<LydjBettingLog> values = lydjBettingLogReposity.find(conditionMap);
        return values;
    }

    private List<String> getTableHeaderList(int type){
        List<String> listColumn = new ArrayList<String>();
        if(1 == type){
            //投注数据
            listColumn.add("时间");
            listColumn.add("下注金额");
            listColumn.add("下注订单号");
            listColumn.add("伙伴类型");
        }else{
            //输赢数据
            listColumn.add("时间");
            listColumn.add("下注金额");
            listColumn.add("下注订单号");
            listColumn.add("伙伴类型");
            listColumn.add("输赢");
        }
        return listColumn;
    }

    private List<Object> getValueList(LydjBettingLog value, int type){
        List<Object> valueList = new ArrayList<Object>();
        if(1 == type){
            //投注数据
            valueList.add(DateUtil.formatDateYYYYMMDDHHmmss(value.getSettleTime()));
            valueList.add(df.format(value.getBettingAmount()));
            valueList.add(value.getBettingOrderNo());
            valueList.add(value.getCurrency());
        }else{
            //输赢数据
            valueList.add(DateUtil.formatDateYYYYMMDDHHmmss(value.getSettleTime()));
            valueList.add(df.format(value.getBettingAmount()));
            valueList.add(value.getBettingOrderNo());
            valueList.add(value.getCurrency());
            valueList.add(df.format(value.getSettleAmount()));
        }
        return valueList;
    }

    @Override
    public String exportBettingData(Map<String, Object> conditionMap){
        return export(conditionMap, 1);
    }

    @Override
    public String exportLosingData(Map<String, Object> conditionMap){
        return export(conditionMap, 0);
    }
}
