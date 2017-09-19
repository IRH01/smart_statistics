package com.hhly.generator.model;

public class TblFunction {
    private Double id;

    private Double parentId;

    private String name;

    private Double functionIndex;

    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public Double getParentId() {
        return parentId;
    }

    public void setParentId(Double parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getFunctionIndex() {
        return functionIndex;
    }

    public void setFunctionIndex(Double functionIndex) {
        this.functionIndex = functionIndex;
    }
}