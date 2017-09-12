package com.hhly.smartdata.model.game.peoluckywheel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlPeopleStatD extends AlPeopleStat{
    public static final String formatStyle = "yyyy-MM-dd";

    @Override
    public void setEtlDate(Date etlDate){
        this.etlDate = new SimpleDateFormat(formatStyle).format(etlDate);
    }
}
