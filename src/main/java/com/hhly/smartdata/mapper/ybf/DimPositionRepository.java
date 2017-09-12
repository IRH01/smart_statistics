package com.hhly.smartdata.mapper.ybf;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hhly.smartdata.model.authentication.Node;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.ybf.DimPosition;

@Repository
public class DimPositionRepository extends BaseRepository{
   
	/**
	 * 根据 id 获取
	 * @param tblId
	 * @return
	 */
    public DimPosition get(Integer tblId){
        return template.selectOne("dimPosition.get", tblId);
    }
    
    /**
     * 获取域名信息
     * @return
     */
    public List<DimPosition> getDomains(){
    	List<DimPosition> list = template.selectList("dimPosition.getDomains");
        return list;
    }
    
    /**
     * 获取非域名位置
     * @return
     */
    public List<Node> getPositions(String domainId){
    	List<Node> list = template.selectList("dimPosition.getPositions",domainId);
        return list;
    }
}