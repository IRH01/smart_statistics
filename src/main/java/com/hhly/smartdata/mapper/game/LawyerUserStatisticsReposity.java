package com.hhly.smartdata.mapper.game;

import com.hhly.smartdata.mapper.authentication.BaseRepository;
import com.hhly.smartdata.model.game.ChannelMonthAllbet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class LawyerUserStatisticsReposity extends BaseRepository{
    public List<ChannelMonthAllbet> find(Map<String, Object> conMap){
        List<ChannelMonthAllbet> values = template.selectList("lawyerUserStatistics.getAllChannelMonthAllbet", conMap);
        return values;
    }

    public int findCount(Map<String, Object> conMap){
        return template.selectOne("lawyerUserStatistics.count", conMap);
    }

    public int findCountMonth(Map<String, Object> conMap){
        return template.selectOne("lawyerUserStatistics.countMonth", conMap);
    }

    public int findCountDay(Map<String, Object> conMap){
        return template.selectOne("lawyerUserStatistics.countDay", conMap);
    }

    public List<ChannelMonthAllbet> findMonth(Map<String, Object> conMap){
        List<ChannelMonthAllbet> values = template.selectList("lawyerUserStatistics.getChannelMonthSum", conMap);
        return values;
    }

    public List<ChannelMonthAllbet> findDay(Map<String, Object> conMap){
        List<ChannelMonthAllbet> values = template.selectList("lawyerUserStatistics.getChannelDaySum", conMap);
        return values;
    }
}
