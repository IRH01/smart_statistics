package com.hhly.smartdata.dto.mouth;

import com.hhly.smartdata.model.smartdata.MonthCompositeReport;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * Created by Iritchie.ren on 2017/10/11.
 */
public class MonthCompositeReportResult extends MonthCompositeReport{

    /**
     * 注册体验率(%)=体验人数/注册人数*100%
     *
     * @return
     */
    public String getRegisterExpRate(){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
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
        return numberFormat.format(((float) this.getNewUserRechargeCount() / (float) this.getRegisterPopulation()) * 100);
    }

    /**
     * 新用户ARPU(%)=新用户充值金额/新用户充值人数*100%
     *
     * @return
     */
    public BigDecimal getNewUserARPU(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getNewUserRechargeCount() != 0){
            result = this.getNewUserRechargeAmount().divide(new BigDecimal(this.getNewUserRechargePopulation()), 2, RoundingMode.HALF_UP);
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
        return numberFormat.format(((float) this.getNewUserLoginCount() / (float) this.getRegisterPopulation()) * 100);
    }

    /**
     * 老用户充值转化率(%)=老用户充值人数/当月以前注册的人数*100%
     *
     * @return
     */
    public String getOldUserRechargeRate(){
        return null;
    }

    /**
     * 老用户ARPU(%)=老用户充值金额/老用户充值人数*100%
     *
     * @return
     */
    public BigDecimal getOldUserARPU(){
        BigDecimal result = new BigDecimal(0.00);
        if(this.getNewUserRechargeCount() != 0){
            result = this.getOldUserRechargeAmount().divide(new BigDecimal(this.getOldUserRechargePopulation()), 2, RoundingMode.HALF_UP);
        }
        return result;
    }

    /**
     * 老用户登录转化率(%)=老用户登录人数/当月以前注册的人数*100%
     *
     * @return
     */
    public Float getOldUserPlayRate(){
        return null;
    }

    /**
     * 平均次日留存率=计算出月平均值
     *
     * @return
     */
    public Float getNextDayKeepRate(){
        return null;
    }
}
