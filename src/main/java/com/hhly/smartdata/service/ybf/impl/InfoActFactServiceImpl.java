package com.hhly.smartdata.service.ybf.impl;

import com.hhly.smartdata.mapper.ybf.DimAddInfoColStatRepository;
import com.hhly.smartdata.mapper.ybf.InfoActFactRepository;
import com.hhly.smartdata.model.ybf.ActCountInfo;
import com.hhly.smartdata.service.ybf.InfoActFactService;
import com.hhly.smartdata.util.DateUtil;
import com.hhly.smartdata.util.ExportExcellUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InfoActFactServiceImpl implements InfoActFactService{

    @Autowired
    private InfoActFactRepository infoActFactRepository;
    @Autowired
    private DimAddInfoColStatRepository dimAddInfoColStatRepository;


    /**
     * 根据日期和域名计算，获取日更新资讯数量
     *
     * @param startDate
     * @param endDate
     * @param domainId  要查询的域名Id
     * @return
     */
    public List<ActCountInfo> countInfoCnt(Date startDate, Date endDate,
                                           String domainId){
        //获取不同资讯类别的更新数量
        List<ActCountInfo> typeInfoCount = this.dimAddInfoColStatRepository.countTypeInfoCnt(startDate, endDate, domainId);
        //获取总的更新资讯数量
        double totalCount = this.dimAddInfoColStatRepository.countAllInfoCnt(startDate, endDate, domainId);
        countAvgPercentValue(typeInfoCount, totalCount);
        //对其他类别进行排序，反正最后
        dealOtherType(typeInfoCount);
        return typeInfoCount;
    }


    /**
     * 根据日期和域名计算，获取点击量信息
     *
     * @param startDate
     * @param endDate
     * @param domainId  要查询的域名Id
     * @return
     */
    @Override
    public List<ActCountInfo> countClickInfo(Date startDate, Date endDate,
                                             String domainId){
        //获取不同资讯类别的点击量
        List<ActCountInfo> typeClickCount = this.infoActFactRepository.countClickByTypeAdDate(startDate, endDate, domainId);
        //获取总点击量
        double totalCount = this.infoActFactRepository.countAllClickByDate(startDate, endDate, domainId);
        countAvgPercentValue(typeClickCount, totalCount);
        //对其他类别进行排序，反正最后
        dealOtherType(typeClickCount);
        return typeClickCount;
    }

    /**
     * 导出点击量数据为excel
     *
     * @param response
     * @param startDate
     * @param endDate
     * @param domainId
     */
    public void exportClickCount(HttpServletResponse response, Date startDate, Date endDate,
                                 String domainId){
        String fileName = DateUtil.formatDate(startDate, "yyyy-MM-dd") + "至" + DateUtil.formatDate(endDate, "yyyy-MM-dd") + "点击量统计数据";
        String sheetName = "点击量统计数据";
        List<ActCountInfo> values = this.countClickInfo(startDate, endDate, domainId);
        List<String> columnsNameList = new ArrayList<String>();
        List<List<Object>> valueList = new ArrayList<List<Object>>();
        if(!CollectionUtils.isEmpty(values)){
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row2ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();
            columnsNameList.add("");//第一列为空
            row1ValueList.add("总计");
            row2ValueList.add("平均（总计/该类别下所有IP数）");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values){
                columnsNameList.add(value.getInfoTypeName());
                row1ValueList.add(value.getCount());
                row2ValueList.add(value.getAvgCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(row1ValueList);
            valueList.add(row2ValueList);
            valueList.add(row3ValueList);
        }else{
            columnsNameList.add("没有数据");
        }
        ExportExcellUtil.export(response, fileName, sheetName, columnsNameList, valueList);
    }

    /**
     * 根据日期和域名计算，获取点击量信息
     *
     * @param startDate
     * @param endDate
     * @param domainId  要查询的域名Id
     * @return
     */
    @Override
    public List<ActCountInfo> countIPInfo(Date startDate, Date endDate,
                                          String domainId){
        //获取不同资讯类别的IP数量
        List<ActCountInfo> typeIPCount = this.infoActFactRepository.countIPByTypeAdDate(startDate, endDate, domainId);
        //获取总数
        //double totalCount = this.getTotalCount(typeIPCount);
        double totalCount = this.infoActFactRepository.countAllIPByDate(startDate, endDate, domainId);
        countAvgPercentValue(typeIPCount, totalCount);
        //对其他类别进行排序，反正最后
        dealOtherType(typeIPCount);
        return typeIPCount;
    }

    /**
     * 导出IP统计数据为excel
     *
     * @param response
     * @param startDate
     * @param endDate
     * @param domainId
     */
    public void exportIPCount(HttpServletResponse response, Date startDate, Date endDate,
                              String domainId){
        String fileName = DateUtil.formatDate(startDate, "yyyy-MM-dd") + "至" + DateUtil.formatDate(endDate, "yyyy-MM-dd") + "IP数量统计数据";
        String sheetName = "IP数量统计数据";
        List<ActCountInfo> values = this.countIPInfo(startDate, endDate, domainId);
        List<String> columnsNameList = new ArrayList<String>();
        List<List<Object>> valueList = new ArrayList<List<Object>>();
        if(!CollectionUtils.isEmpty(values)){
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row2ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();
            columnsNameList.add("");//第一列为空
            row1ValueList.add("总计");
            row2ValueList.add("平均（总计/该类别下所有点击过的资讯数）");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values){
                columnsNameList.add(value.getInfoTypeName());
                row1ValueList.add(value.getCount());
                row2ValueList.add(value.getAvgCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(row1ValueList);
            valueList.add(row2ValueList);
            valueList.add(row3ValueList);
        }else{
            columnsNameList.add("没有数据");
        }
        ExportExcellUtil.export(response, fileName, sheetName, columnsNameList, valueList);
    }

    /**
     * 根据日期和域名计算，获取停留时长
     *
     * @param startDate
     * @param endDate
     * @param domainId  要查询的域名Id
     * @return
     */
    @Override
    public List<ActCountInfo> countStayTimeInfo(Date startDate, Date endDate,
                                                String domainId){
        //获取不同资讯类别的停留时长
        List<ActCountInfo> typeStayTime = this.infoActFactRepository.countStayTimeByTypeAdDate(startDate, endDate, domainId);
        //获取总点击量
        double totalCount = this.infoActFactRepository.countAllStayTimeByDate(startDate, endDate, domainId);
        countAvgPercentValue(typeStayTime, totalCount);
        //对其他类别进行排序，反正最后
        dealOtherType(typeStayTime);
        return typeStayTime;
    }

    /**
     * 导出停留时长数据为excel
     *
     * @param response
     * @param startDate
     * @param endDate
     * @param domainId
     */
    public void exportStayTimeInfo(HttpServletResponse response, Date startDate, Date endDate,
                                   String domainId){
        String fileName = DateUtil.formatDate(startDate, "yyyy-MM-dd") + "至" + DateUtil.formatDate(endDate, "yyyy-MM-dd") + "停留时长统计数据";
        String sheetName = "停留时长统计数据";
        List<ActCountInfo> values = this.countStayTimeInfo(startDate, endDate, domainId);
        List<String> columnsNameList = new ArrayList<String>();
        List<List<Object>> valueList = new ArrayList<List<Object>>();
        if(!CollectionUtils.isEmpty(values)){
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row2ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();
            columnsNameList.add("");//第一列为空
            row1ValueList.add("总计：秒");
            row2ValueList.add("平均（总计/该类别下所有IP数）：秒");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values){
                columnsNameList.add(value.getInfoTypeName());
                row1ValueList.add(value.getCount());
                row2ValueList.add(value.getAvgCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(row1ValueList);
            valueList.add(row2ValueList);
            valueList.add(row3ValueList);
        }else{
            columnsNameList.add("没有数据");
        }
        ExportExcellUtil.export(response, fileName, sheetName, columnsNameList, valueList);
    }


    /**
     * 计算平均值和百分比
     *
     * @param values
     * @param totalCount
     */
    private void countAvgPercentValue(List<ActCountInfo> values, double totalCount){
        if(!CollectionUtils.isEmpty(values)){
            DecimalFormat df = new DecimalFormat("######0.00");
            ActCountInfo item = null;
            double typeCount = 0;
            double percent = 0;
            double avgCount = 0;
            for(int i = 0; i < values.size(); i++){
                item = values.get(i);
                typeCount = item.getCount();
                //获取百分比
                if(0 == totalCount){
                    percent = 0;
                }else{
                    //计算单个资讯百分比
                    percent = (double) typeCount / totalCount;
                }
                item.setPercent(percent);
                //设置值
                item.setAvgCount(0);
                if(item.getUnitValue() != 0){
                    avgCount = (double) item.getCount() / item.getUnitValue();
                    avgCount = Double.valueOf(df.format(avgCount));//取两位小数
                    item.setAvgCount(avgCount);
                }
            }
        }
    }

    /**
     * 导出数据为excel
     *
     * @param response
     * @param startDate
     * @param endDate
     * @param domainId
     */
    public void exportToExcel(HttpServletResponse response, Date startDate, Date endDate,
                              String domainId){
        String fileName = DateUtil.formatDate(startDate, "yyyy-MM-dd") + "至" + DateUtil.formatDate(endDate, "yyyy-MM-dd") + "总览统计数据";
        String sheetName = "总览统计数据";
        List<String> columnsNameList = new ArrayList<String>();
        List<List<Object>> valueList = new ArrayList<List<Object>>();
        columnsNameList.add("");//第一列为空
        columnsNameList.add("资讯类别");//第二列为空

        List<ActCountInfo> values4 = this.countInfoCnt(startDate, endDate, domainId);
        if(!CollectionUtils.isEmpty(values4)){
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();
            row1ValueList.add("日更新资讯数量统计");
            row1ValueList.add("数量");
            row3ValueList.add("");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values4){
                columnsNameList.add(value.getInfoTypeName());
                row1ValueList.add(value.getCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(row1ValueList);
            valueList.add(row3ValueList);
        }

        //每个表空两行
        valueList.add(new ArrayList<Object>());
        valueList.add(new ArrayList<Object>());
        valueList.add(new ArrayList<Object>());

        List<ActCountInfo> values = this.countClickInfo(startDate, endDate, domainId);
        if(!CollectionUtils.isEmpty(values)){
            List<Object> columnsNameList1 = new ArrayList<Object>();
            columnsNameList1.add("");//第一列为空
            columnsNameList1.add("资讯类别");//第二列为空
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row2ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();
            row1ValueList.add("点击量统计");
            row1ValueList.add("总计");
            row2ValueList.add("");
            row2ValueList.add("平均（总计/该类别下所有IP数）");
            row3ValueList.add("");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values){
                columnsNameList1.add(value.getInfoTypeName());
                row1ValueList.add(value.getCount());
                row2ValueList.add(value.getAvgCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(columnsNameList1);
            valueList.add(row1ValueList);
            valueList.add(row2ValueList);
            valueList.add(row3ValueList);
        }

        //空多行
        valueList.add(new ArrayList<Object>());
        valueList.add(new ArrayList<Object>());


        List<ActCountInfo> values1 = this.countIPInfo(startDate, endDate, domainId);
        ;
        if(!CollectionUtils.isEmpty(values1)){
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row2ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();

            row1ValueList.add("IP数量统计");
            row1ValueList.add("总计");
            row2ValueList.add("");
            row2ValueList.add("平均（总计/该类别下所有点击过的资讯数）");
            row3ValueList.add("");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values1){
                row1ValueList.add(value.getCount());
                row2ValueList.add(value.getAvgCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(row1ValueList);
            valueList.add(row2ValueList);
            valueList.add(row3ValueList);
        }
        //每个表空两行
        valueList.add(new ArrayList<Object>());
        valueList.add(new ArrayList<Object>());

        List<ActCountInfo> values2 = this.countStayTimeInfo(startDate, endDate, domainId);
        if(!CollectionUtils.isEmpty(values2)){
            DecimalFormat df = new DecimalFormat("######0.00");
            List<Object> row1ValueList = new ArrayList<Object>();
            List<Object> row2ValueList = new ArrayList<Object>();
            List<Object> row3ValueList = new ArrayList<Object>();

            row1ValueList.add("停留时长统计");
            row1ValueList.add("总计：秒");
            row2ValueList.add("");
            row2ValueList.add("平均（总计/该类别下所有IP数）：秒");
            row3ValueList.add("");
            row3ValueList.add("百分比");
            for(ActCountInfo value : values2){
                row1ValueList.add(value.getCount());
                row2ValueList.add(value.getAvgCount());
                row3ValueList.add(df.format(value.getPercent() * 100) + "%");
            }
            valueList.add(row1ValueList);
            valueList.add(row2ValueList);
            valueList.add(row3ValueList);
        }

        ExportExcellUtil.export(response, fileName, sheetName, columnsNameList, valueList);
    }


    /**
     * 统计总数
     *
     * @param values
     * @return
     */
    @SuppressWarnings("unused")
    private double getTotalCount(List<ActCountInfo> values){
        double totalCount = 0;
        if(!CollectionUtils.isEmpty(values)){
            for(ActCountInfo value : values){
                totalCount += value.getCount();
            }
        }
        return totalCount;
    }


    /**
     * 将其他类型放在最后
     *
     * @param values
     */
    private void dealOtherType(List<ActCountInfo> values){
        if(!CollectionUtils.isEmpty(values)){
            for(ActCountInfo value : values){
                if(null != value){
                    //类型是其他
                    if("EMPTY".equalsIgnoreCase(value.getInfoTypeId())){
                        //先移除该类别数据
                        if(values.remove(value)){
                            values.add(value);//再添加到末尾
                        }
                        return;
                    }
                }
            }
        }
    }
}
