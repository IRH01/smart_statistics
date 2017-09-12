package com.hhly.smartdata.dto;

public class InfoTypeDto{

    private String tblId;

    private String typeId;

    private String typeName;

    public String getTypeName(){
        return typeName;
    }

    public void setTypeName(String typeName){
        this.typeName = typeName;
    }

    public String getTblId(){
        return tblId;
    }

    public void setTblId(String tblId){
        this.tblId = tblId;
    }

    public String getTypeId(){
        return typeId;
    }

    public void setTypeId(String typeId){
        this.typeId = typeId;
    }
}
