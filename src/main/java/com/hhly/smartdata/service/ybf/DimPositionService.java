package com.hhly.smartdata.service.ybf;

import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.model.ybf.DimPosition;

import java.util.List;

public interface DimPositionService {
	
	/**
	 * 根据id获取
	 * @param tblId
	 * @return
	 */
	public DimPosition get(Integer tblId);
	
	/**
	 * 获取域名信息
	 * @return
	 */
	public List<DimPosition> getDomains();
	
	/**
     * 获取非域名位置
     * @return
     */
    public List<Node> getPositions(String domainId);
}
