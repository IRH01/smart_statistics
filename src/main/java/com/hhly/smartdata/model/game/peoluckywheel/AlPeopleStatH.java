package com.hhly.smartdata.model.game.peoluckywheel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AlPeopleStatH extends AlPeopleStat {
	public static final String formatStyle = "HH";
	
	@Override
	public void setEtlDate(Date etlDate){
		this.etlDate = new SimpleDateFormat(formatStyle).format(etlDate);
		System.out.println(this.etlDate);
	}
}
