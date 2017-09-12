package com.hhly.smartdata.service.game.operative.lmdz;

import com.github.pagehelper.PageInfo;
import com.hhly.smartdata.model.game.operative.lmdz.LmdzChannelDaily;
import com.hhly.smartdata.model.game.operative.lmdz.LmdzChannelDailySum;

import java.util.List;
import java.util.Map;

public interface LmdzChannelDailyService{

    /**
     * 分页，条件查询
     *
     * @param condition
     * @param page
     * @return
     */
    public PageInfo<LmdzChannelDaily> find(Map<String, Object> condition, int pageNumber,
                                           int pageSize);

    /**
     * 汇总统计
     *
     * @param condition
     * @return
     */
    public LmdzChannelDailySum sum(Map<String, Object> condition);

    /**
     * 获取grades
     *
     * @param condition
     * @return
     */
    public List<String> getGrades(Map<String, Object> condition);

    public List<LmdzChannelDaily> getOss(Map<String, Object> condition);

    public String export(Map<String, Object> conditionMap);

    public boolean canExport(Map<String, Object> conditionMap);
}
