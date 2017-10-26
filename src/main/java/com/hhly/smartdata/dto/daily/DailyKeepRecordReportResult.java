package com.hhly.smartdata.dto.daily;

import com.hhly.smartdata.model.smartdata.DailyKeepRecordReport;

/**
 * Created by Iritchie.ren on 2017/10/17.
 */
public class DailyKeepRecordReportResult extends DailyKeepRecordReport{

    /**
     * 次日留存率
     */
    private Double onePercent;
    private Double twoPercent;
    private Double threePercent;
    private Double fourPercent;
    private Double fivePercent;
    private Double sixPercent;
    private Double sevenPercent;
    private Double fourteenPercent;
    private Double thirtyPercent;

    public Double getOnePercent() {
        onePercent = 0D;
        if(0 != this.getRegisterCount()){
            onePercent = (double)this.getOneRemain()/this.getRegisterCount();
        }
        return onePercent;
    }
    public Double getTwoPercent() {
        twoPercent = 0D;
        if(0 != this.getRegisterCount()){
            twoPercent = (double)this.getTwoRemain()/this.getRegisterCount();
        }
        return twoPercent;
    }
    public Double getThreePercent() {
        threePercent = 0D;
        if(0 != this.getRegisterCount()){
            threePercent = (double)this.getThreeRemain()/this.getRegisterCount();
        }
        return threePercent;
    }
    public Double getFourPercent() {
        fourPercent = 0D;
        if(0 != this.getRegisterCount()){
            fourPercent = (double)this.getFourRemain()/this.getRegisterCount();
        }
        return fourPercent;
    }
    public Double getFivePercent() {
        fivePercent = 0D;
        if(0 != this.getRegisterCount()){
            fivePercent = (double)this.getFiveRemain()/this.getRegisterCount();
        }
        return fivePercent;
    }
    public Double getSixPercent() {
        sixPercent = 0D;
        if(0 != this.getRegisterCount()){
            sixPercent = (double)this.getSixRemain()/this.getRegisterCount();
        }
        return sixPercent;
    }
    public Double getSevenPercent() {
        sevenPercent = 0D;
        if(0 != this.getRegisterCount()){
            sevenPercent = (double)this.getSevenRemain()/this.getRegisterCount();
        }
        return sevenPercent;
    }
    public Double getFourteenPercent() {
        fourteenPercent = 0D;
        if(0 != this.getRegisterCount()){
            fourteenPercent = (double)this.getFourteenRemain()/this.getRegisterCount();
        }
        return fourteenPercent;
    }
    public Double getThirtyPercent() {
        thirtyPercent = 0D;
        if(0 != this.getRegisterCount()){
            thirtyPercent = (double)this.getThirtyRemain()/this.getRegisterCount();
        }
        return thirtyPercent;
    }
}
