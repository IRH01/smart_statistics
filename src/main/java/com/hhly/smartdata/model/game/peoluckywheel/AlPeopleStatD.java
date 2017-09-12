package com.hhly.smartdata.model.game.peoluckywheel;

import java.util.Date;
import java.text.SimpleDateFormat;

public class AlPeopleStatD extends AlPeopleStat {
	public static final String formatStyle = "yyyy-MM-dd";
	
	@Override
	public void setEtlDate(Date etlDate){
		this.etlDate = new SimpleDateFormat(formatStyle).format(etlDate);
	}
}
