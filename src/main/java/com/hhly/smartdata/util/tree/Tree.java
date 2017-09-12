package com.hhly.smartdata.util.tree;

import java.util.List;

public class Tree<ID,DATA> {
    private ID id;
    private DATA data;
    private List<Tree<ID,DATA>> children;
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public DATA getData() {
		return data;
	}
	public void setData(DATA data) {
		this.data = data;
	}
	public List<Tree<ID, DATA>> getChildren() {
		return children;
	}
	public void setChildren(List<Tree<ID, DATA>> children) {
		this.children = children;
	}
}
