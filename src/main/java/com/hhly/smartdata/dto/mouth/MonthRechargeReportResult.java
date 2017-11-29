package com.hhly.smartdata.dto.mouth;

import com.hhly.smartdata.model.smartdata.MonthRechargeReport;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by Iritchie.ren on 2017/10/13.
 */
public class MonthRechargeReportResult extends MonthRechargeReport{

    public BigDecimal getArppu(){
        BigDecimal result = new BigDecimal(0.0000);
        if(this.getNewRechargePopulation() != 0){
            result = this.getRechargeAmount().divide(new BigDecimal(this.getNewRechargePopulation()), 4, RoundingMode.HALF_UP);
        }
        return result;
    }

    public BigDecimal getArpu(){
        BigDecimal result = new BigDecimal(0.0000);
        if(this.getLoginPopulation() != 0){
            result = this.getRechargeAmount().divide(new BigDecimal(this.getLoginPopulation()), 4, RoundingMode.HALF_UP);
        }
        return result;
    }
}
