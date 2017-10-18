package com.hhly.smartdata.model.member;

import java.math.BigDecimal;
import java.util.Date;

public class PlatformGoldConsumeRecord{
    /**
     * id
     */
    private Integer id;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 用户唯一标识
     */
    private String userId ;

    /**
     * 消费金币数
     */
    private BigDecimal gold;

    /**
     * 最后金币数
     */
    private BigDecimal lastGold;

    /**
     * 消费类型
     */
    private Integer consumeType;

    /**
     * 终端
     */
    private Integer terminal;

    /**
     * 消费详情
     */
    private String consumDesc;

    /**
     * 平台id
     */
    private Integer platformid;

    /**
     * 消费时间
     */
    private Date consumeTime;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getOrderNo(){
        return orderNo;
    }

    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public BigDecimal getGold(){
        return gold;
    }

    public void setGold(BigDecimal gold){
        this.gold = gold;
    }

    public BigDecimal getLastGold(){
        return lastGold;
    }

    public void setLastGold(BigDecimal lastGold){
        this.lastGold = lastGold;
    }

    public Integer getConsumeType(){
        return consumeType;
    }

    public void setConsumeType(Integer consumeType){
        this.consumeType = consumeType;
    }

    public Integer getTerminal(){
        return terminal;
    }

    public void setTerminal(Integer terminal){
        this.terminal = terminal;
    }

    public String getConsumDesc(){
        return consumDesc;
    }

    public void setConsumDesc(String consumDesc){
        this.consumDesc = consumDesc;
    }

    public Integer getPlatformid(){
        return platformid;
    }

    public void setPlatformid(Integer platformid){
        this.platformid = platformid;
    }

    public Date getConsumeTime(){
        return consumeTime;
    }

    public void setConsumeTime(Date consumeTime){
        this.consumeTime = consumeTime;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
}