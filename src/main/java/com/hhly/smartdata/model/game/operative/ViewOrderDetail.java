package com.hhly.smartdata.model.game.operative;

import com.hhly.smartdata.util.ToolUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewOrderDetail{
    public static final String formatStyle = "yyyy-MM-dd HH:mm:ss";

    private String orderNo;
    private int payStatus;
    private String payStatusName;
    private String userId;
    private String nickName;
    /**
     * 充值方式：1：微信支付  2：支付宝 3：乐盈币兑换  4：银联充值 5：优贝支付
     * 6：CC电子钱包  7：CC点卡 9：mol电子钱包  10：mol点卡 11：越南CardChargePay
     */
    private int rechargeWay;
    private String rechargeWayName;
    private int platformId;
    private String platformName;
    private int serverId;
    private String serverName;
    private double amount;
    private double receiptAmount;
    private double fee;
    private String createTime;
    private String payTime;
    private String paymentOrderNo;
    private String tradeNo;
    private String channelId;
    private String channelName;
    private String countryName;
    /**
     * 充值类型，1充值，2返点
     */
    private int orderType;
    private String orderTypeName;
    private String developers;
    //终端类型
    private String platformTerminalName;

    public String getOrderNo(){
        return orderNo;
    }

    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public int getPayStatus(){
        return payStatus;
    }

    public void setPayStatus(int payStatus){
        this.payStatus = payStatus;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getNickName(){
        return nickName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public int getRechargeWay(){
        return rechargeWay;
    }

    public void setRechargeWay(int rechargeWay){
        this.rechargeWay = rechargeWay;
    }

    public int getPlatformId(){
        return platformId;
    }

    public void setPlatformId(int platformId){
        this.platformId = platformId;
    }

    public double getAmount(){
        return amount;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public String getCreateTime(){
        return createTime;
    }

    public void setCreateTime(Date createTime){
        this.createTime = new SimpleDateFormat(formatStyle).format(createTime);
    }

    public String getPayTime(){
        return payTime;
    }

    public void setPayTime(Date payTime){
        this.payTime = new SimpleDateFormat(formatStyle).format(payTime);
    }

    public String getPaymentOrderNo(){
        return paymentOrderNo;
    }

    public void setPaymentOrderNo(String paymentOrderNo){
        this.paymentOrderNo = paymentOrderNo;
    }

    public String getTradeNo(){
        return tradeNo;
    }

    public void setTradeNo(String tradeNo){
        this.tradeNo = tradeNo;
    }

    public String getPlatformName(){
        return platformName;
    }

    public void setPlatformName(String platformName){
        this.platformName = platformName;
    }

    public int getServerId(){
        return serverId;
    }

    public void setServerId(int serverId){
        this.serverId = serverId;
    }

    public String getServerName(){
        return serverName;
    }

    public void setServerName(String serverName){
        this.serverName = serverName;
    }

    public double getReceiptAmount(){
        return receiptAmount;
    }

    public void setReceiptAmount(double receiptAmount){
        this.receiptAmount = receiptAmount;
    }

    public double getFee(){
        return fee;
    }

    public void setFee(double fee){
        this.fee = fee;
    }

    public String getRechargeWayName(){
        if(1 == this.rechargeWay){
            rechargeWayName = "微信支付";
        }else if(14 == this.rechargeWay){
            rechargeWayName = "微信.威富通";
        }else if(2 == this.rechargeWay){
            rechargeWayName = "支付宝";
        }else if(13 == this.rechargeWay){
            rechargeWayName = "支付宝.威富通";
        }else if(3 == this.rechargeWay){
            rechargeWayName = "乐盈币兑换";
        }else if(4 == this.rechargeWay){
            rechargeWayName = "银联充值";
        }else if(5 == this.rechargeWay){
            rechargeWayName = "优贝支付";
        }else if(6 == this.rechargeWay){
            rechargeWayName = "CC电子钱包";
        }else if(7 == this.rechargeWay){
            rechargeWayName = "CC点卡";
        }else if(9 == this.rechargeWay){
            rechargeWayName = "mol电子钱包";
        }else if(10 == this.rechargeWay){
            rechargeWayName = "mol点卡";
        }else if(11 == this.rechargeWay){
            rechargeWayName = "IOS内购";
        }else if(16 == this.rechargeWay){
            rechargeWayName = " 快捷支付";
        }else if(17 == this.rechargeWay){
            rechargeWayName = "网银支付";
        }else if(18 == this.rechargeWay){
            rechargeWayName = "易游酷点卡支付";
        }else if(19 == this.rechargeWay){
            rechargeWayName = "支付宝.聚合支付";
        }else if(20 == this.rechargeWay){
            rechargeWayName = "微信.聚合支付";
        }else if(100 == this.rechargeWay){
            rechargeWayName = "其他";
        }else{
            rechargeWayName = String.valueOf(this.rechargeWay);
        }
        return rechargeWayName;
    }

    public String getPayStatusName(){
        if(0 == this.payStatus){
            payStatusName = "处理中";
        }else if(1 == this.payStatus){
            payStatusName = "成功";
        }else if(2 == this.payStatus){
            payStatusName = "失败";
        }else if(3 == this.payStatus){
            payStatusName = "已关闭";
        }else{
            payStatusName = String.valueOf(this.payStatus);
        }
        return payStatusName;
    }

    public String getChannelId(){
        return channelId;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }

    public String getChannelName(){
        return channelName;
    }

    public void setChannelName(String channelName){
        this.channelName = channelName;
    }

    public int getOrderType(){
        return orderType;
    }

    public void setOrderType(int orderType){
        this.orderType = orderType;
    }

    public String getOrderTypeName(){
        if(1 == orderType){
            orderTypeName = "充值";
        }else if(2 == orderType){
            orderTypeName = "返点";
        }else{
            orderTypeName = ToolUtil.objectToString(orderType);
        }
        return orderTypeName;
    }

    public String getDevelopers(){
        return developers;
    }

    public void setDevelopers(String developers){
        this.developers = developers;
    }

    public String getCountryName(){
        return countryName;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public String getPlatformTerminalName(){
        return platformTerminalName;
    }

    public void setPlatformTerminalName(String platformTerminalName){
        this.platformTerminalName = platformTerminalName;
    }
}
