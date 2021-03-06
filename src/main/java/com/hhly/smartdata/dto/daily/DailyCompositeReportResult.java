package com.hhly.smartdata.dto.daily;

import com.hhly.smartdata.model.smartdata.DailyCompositeReport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Created by Iritchie.ren on 2017/10/27.
 */
public class DailyCompositeReportResult extends DailyCompositeReport{

    /**
     * 次日留存率=次日登录的用户数/前一日注册的用户数*100%
     *
     * @return
     */
    private String nextDayStayRate;

    /**
     * 注册体验率(%)=体验人数/注册人数*100%
     *
     * @return
     */
    public String getRegisterExpCountRate(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if(this.getRegisterPopulation() == 0){
            return "0.00";
        }
        return numberFormat.format(((float) this.getRegisterExpCount() / (float) this.getRegisterPopulation()) * 100);
    }

    /**
     * 新用户充值转化率(%)=新用户充值人数/新注册的人数*100%
     *
     * @return
     */
    public String getNewUserRechargeRate(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if(this.getRegisterPopulation() == 0){
            return "0.00";
        }
        return numberFormat.format(((float) this.getNewUserRechargePopulation() / (float) this.getRegisterPopulation()) * 100);
    }

    /**
     * 新用户ARPU(%)=新用户充值金额/新用户充值人数*100%
     *
     * @return
     */
    public BigDecimal getNewUserARPU(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getNewUserLoginCount() != 0){
            result = this.getNewUserRechargeAmount().divide(new BigDecimal(this.getNewUserLoginCount()), 2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     * 新用户登录转化率(%)=新用户登录人数/新注册的人数*100%
     *
     * @return
     */
    public String getNewUserLoginTransformRate(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if(this.getRegisterPopulation() == 0){
            return "0.00";
        }
        return numberFormat.format(((float) this.getNewUserLoginCount() / (float) this.getRegisterPopulation()) * 100);
    }

    /**
     * 老用户ARPU(%)=老用户充值金额/老用户充值人数*100%
     *
     * @return
     */
    public BigDecimal getOldUserARPU(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getOldUserLoginCount() != 0){
            result = this.getOldUserRechargeAmount().divide(new BigDecimal(this.getOldUserLoginCount()), 2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     * 老用户充值转化率(%)=老用户充值人数/当天以前注册的人数*100%
     *
     * @return
     */
    public String getOldUserRechargeRate(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        Integer oldRegisterPopulation = this.getTotalRegisterPopulation() - this.getRegisterPopulation();
        if(oldRegisterPopulation == 0){
            return "0.00";
        }
        return numberFormat.format(((float) (this.getOldUserRechargePopulation()) / (float) oldRegisterPopulation) * 100);
    }

    /**
     * 老用户登录转化率(%)=老用户登录人数/当天以前注册的人数*100%
     *
     * @return
     */
    public String getOldUserLoginTransformRate(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        Integer oldRegisterPopulation = this.getTotalRegisterPopulation() - this.getRegisterPopulation();
        if(oldRegisterPopulation == 0){
            return "0.00";
        }
        return numberFormat.format(((float) this.getOldUserLoginCount() / (float) oldRegisterPopulation) * 100);
    }

    /**
     * 新用户ARPPU : “平均每付费用户收入”= 新用户充值金额/新用户充值人数
     *
     * @return
     */
    public BigDecimal getNewUserARPPU(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getNewUserRechargeCount() != 0){
            result = this.getNewUserRechargeAmount().divide(new BigDecimal(this.getNewUserRechargePopulation()), 2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     * 老用户ARPPU : “平均每付费用户收入”= 老用户充值金额/老用户充值人数
     *
     * @return
     */
    public BigDecimal getOldUserARPPU(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getNewUserRechargeCount() != 0){
            result = this.getOldUserRechargeAmount().divide(new BigDecimal(this.getOldUserRechargePopulation()), 2, RoundingMode.HALF_UP);
        }
        return result;
    }

    public String getNextDayStayRate(){
        return nextDayStayRate;
    }

    /**
     * 次日留存率=次日登录的用户数/前一日注册的用户数*100%
     *
     * @return
     */
    public void setNextDayKeepRate(Integer nextDayStayCount, Integer registerPopulation){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        if(registerPopulation == 0){
            this.nextDayStayRate = "0.00";
        }else{
            this.nextDayStayRate = numberFormat.format(((float) nextDayStayCount / (float) registerPopulation) * 100);
        }
    }
}
