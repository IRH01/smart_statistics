package com.hhly.smartdata.service.game.operative.impl;

import com.hhly.smartdata.mapper.game.operative.GameRechargeSummaryReposity;
import com.hhly.smartdata.model.game.operative.GameRechargeStatistic;
import com.hhly.smartdata.service.game.operative.GameRechargeSummaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameRechargeSummaryServiceImpl implements GameRechargeSummaryService{

    @Autowired
    private GameRechargeSummaryReposity gameRechargeSummaryReposity;

    @Override
    public double statisticsRcg2Game(String countryId){
        GameRechargeStatistic allRcg = gameRechargeSummaryReposity.statistics(countryId);
        double rcg2Lyb = gameRechargeSummaryReposity.statisticsRcg2Lyb(countryId);
        if(null != allRcg){
            return allRcg.getOrderAmount() - rcg2Lyb;
        }else{
            return 0;
        }
    }

    @Override
    public double statisticsRcg2Lyb(String countryId){
        return gameRechargeSummaryReposity.statisticsRcg2Lyb(countryId);
    }

    @Override
    public double statisticsRemainLyb(String countryId){
        return gameRechargeSummaryReposity.statisticsRcg2Lyb(countryId) - gameRechargeSummaryReposity.statisticsLyb2Game(countryId);
    }

}
