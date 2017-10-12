package com.hhly.smartdata.util.page;

/**
 * Created by Iritchie.ren on 2017/10/11.
 */
public class Pagination{

    /**
     * 记录总数
     */
    private Long total;
    /**
     * 查询结果
     */
    private Object data;

    public Long getTotal(){
        return total;
    }

    public void setTotal(Long total){
        this.total = total;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }
}
