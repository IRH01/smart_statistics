package com.hhly.smartdata.model.authentication;

public class Function{
    private Integer id;

    private Integer parentId;

    private String functionName;

    private Integer index;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getParentId(){
        return parentId;
    }

    public void setParentId(Integer parentId){
        this.parentId = parentId;
    }

    public String getFunctionName(){
        return functionName;
    }

    public void setFunctionName(String functionName){
        this.functionName = functionName == null ? null : functionName.trim();
    }

    public Integer getIndex(){
        return index;
    }

    public void setIndex(Integer index){
        this.index = index;
    }
}