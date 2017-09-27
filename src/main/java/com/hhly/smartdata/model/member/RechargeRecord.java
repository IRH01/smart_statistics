package com.hhly.smartdata.model.member;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeRecord{
    /**
     * 充值订单号 pk
     */
    private String orderno;

    /**
     * 用户唯一标识
     */
    private String userId;

    /**
     * 充值金额
     */
    private BigDecimal applyAmount;

    /**
     * （暂且不用）兑换(用乐盈币支付)时候需传值，其他走第三方 充值时候 （关系真正钱时候） 不用传值
     */
    private Double realLyb;

    /**
     * 第三方充值通知状态(0：处理中1：成功2：失败 3关闭)
     */
    private Integer status;

    /**
     * 充值渠道标识1：微信支付  2：支付宝 3：乐盈币兑换  4：银联充值 5：优贝支付6：cc电子钱包  7：cc点卡 9：mol电子钱包  10：mol点卡 11
     */
    private Integer platformType;

    /**
     * 充值申请时间
     */
    private Date applyTime;

    /**
     * 处理时间
     */
    private Date handleTime;

    /**
     * 充值平台订单号
     */
    private String platformOrder;

    /**
     * 充值订单处理方式 1, 在线充值 2系统自动充值
     */
    private Integer handleType;

    /**
     * 操作终端（1pc  2安卓  3ios  4h5 5其他）
     */
    private Integer source;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 充值操作人
     */
    private Integer operatorid;

    /**
     * 业务订单号
     */
    private String paymentOrderno;

    /**
     * 支付通知(0：处理中1：
     */
    private Integer notifyStatus;

    /**
     * 平台来源(从哪跳过来充值的)
     */
    private Long platformSource;

    /**
     * 暂且不用
     */
    private Integer changeType;

    /**
     * 平台id
     */
    private Long plateformId;

    /**
     * 具体平台下面的区
     */
    private Long sonPlateformid;

    /**
     * 具体平台下面的服i
     */
    private Long serviceId;

    /**
     * 币种名称
     */
    private String currency;

    /**
     * 对应的金币
     */
    private Double gold;

    /**
     * 国家
     */
    private String country;

    public String getOrderno(){
        return orderno;
    }

    public void setOrderno(String orderno){
        this.orderno = orderno;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public BigDecimal getApplyAmount(){
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount){
        this.applyAmount = applyAmount;
    }

    public Double getRealLyb(){
        return realLyb;
    }

    public void setRealLyb(Double realLyb){
        this.realLyb = realLyb;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }

    public Integer getPlatformType(){
        return platformType;
    }

    public void setPlatformType(Integer platformType){
        this.platformType = platformType;
    }

    public Date getApplyTime(){
        return applyTime;
    }

    public void setApplyTime(Date applyTime){
        this.applyTime = applyTime;
    }

    public Date getHandleTime(){
        return handleTime;
    }

    public void setHandleTime(Date handleTime){
        this.handleTime = handleTime;
    }

    public String getPlatformOrder(){
        return platformOrder;
    }

    public void setPlatformOrder(String platformOrder){
        this.platformOrder = platformOrder;
    }

    public Integer getHandleType(){
        return handleType;
    }

    public void setHandleType(Integer handleType){
        this.handleType = handleType;
    }

    public Integer getSource(){
        return source;
    }

    public void setSource(Integer source){
        this.source = source;
    }

    public String getRemark(){
        return remark;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public Integer getOperatorid(){
        return operatorid;
    }

    public void setOperatorid(Integer operatorid){
        this.operatorid = operatorid;
    }

    public String getPaymentOrderno(){
        return paymentOrderno;
    }

    public void setPaymentOrderno(String paymentOrderno){
        this.paymentOrderno = paymentOrderno;
    }

    public Integer getNotifyStatus(){
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus){
        this.notifyStatus = notifyStatus;
    }

    public Long getPlatformSource(){
        return platformSource;
    }

    public void setPlatformSource(Long platformSource){
        this.platformSource = platformSource;
    }

    public Integer getChangeType(){
        return changeType;
    }

    public void setChangeType(Integer changeType){
        this.changeType = changeType;
    }

    public Long getPlateformId(){
        return plateformId;
    }

    public void setPlateformId(Long plateformId){
        this.plateformId = plateformId;
    }

    public Long getSonPlateformid(){
        return sonPlateformid;
    }

    public void setSonPlateformid(Long sonPlateformid){
        this.sonPlateformid = sonPlateformid;
    }

    public Long getServiceId(){
        return serviceId;
    }

    public void setServiceId(Long serviceId){
        this.serviceId = serviceId;
    }

    public String getCurrency(){
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
    }

    public Double getGold(){
        return gold;
    }

    public void setGold(Double gold){
        this.gold = gold;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(String country){
        this.country = country;
    }
}