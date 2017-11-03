package com.hhly.smartdata.dto.current;

/**
 * @author hejh
 * @version V1.0
 * @Package com.hhly.smartdata.dto.current
 * @Description: 图形报表数据对象
 * @date 2017-11-02 16:27
 */
public class ChartSeriesData{
    private String name;
    private String type;
    private Object data;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }
}
