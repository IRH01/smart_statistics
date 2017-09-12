package com.hhly.smartdata.mapper.game.operative;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.operative.ViewOrderDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ViewOrderDetailReposity extends BaseRepository{
    public List<ViewOrderDetail> find(Map<String, Object> conditionMap){
        List<ViewOrderDetail> values = template.selectList("viewOrderDetail.find", conditionMap);
        return values;
    }

    public int count(Map<String, Object> conditionMap){
        int value = template.selectOne("viewOrderDetail.count", conditionMap);
        return value;
    }

    public List<ViewOrderDetail> findQlfMemberData(Map<String, Object> conditionMap){
        List<ViewOrderDetail> values = template.selectList("viewOrderDetail.findQlfMemberData", conditionMap);
        return values;
    }

    public int countQlfMemberData(Map<String, Object> conditionMap){
        int value = template.selectOne("viewOrderDetail.countQlfMemberData", conditionMap);
        return value;
    }
}
