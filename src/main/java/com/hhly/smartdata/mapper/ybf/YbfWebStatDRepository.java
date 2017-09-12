package com.hhly.smartdata.mapper.ybf;

import com.hhly.smartdata.dto.YbfWebStatD;
import com.hhly.smartdata.mapper.authentication.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class YbfWebStatDRepository extends BaseRepository{

    /**
     * 查询获取日统计数据
     *
     * @param map
     * @return
     */
    public List<YbfWebStatD> findYbfWebStatD(Map<String, Object> map){
        return this.template.selectList("ybfWebStatD.find", map);
    }

    /**
     * 累计计算日统计数据
     *
     * @param map
     * @return
     */
    public List<YbfWebStatD> countYbfWebStatD(Map<String, Object> map){
        return this.template.selectList("ybfWebStatD.count", map);
    }
}
