package com.hhly.smartdata.dto.mouth;

import com.hhly.smartdata.model.smartdata.MonthRechargeReport;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Iritchie.ren on 2017/10/13.
 */
public class MonthRechargeReportResult extends MonthRechargeReport{

    public BigDecimal getArpu(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getNewRechargePopulation() != 0){
            result = this.getRechargeAmount().divide(new BigDecimal(this.getNewRechargePopulation()), 2, RoundingMode.HALF_UP);
        }
        return result;
    }
}
