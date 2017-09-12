package com.hhly.smartdata.model.game;

public class DebitExchange{
    private String orderId;
    private String exchangeStatus;
    private String userId;
    private String nickName;
    private String exchangeGame;
    private String exchangeAmout;
    private String commitTime;
    private String tradeTime;

    public String getOrderId(){
        return orderId;
    }

    public void setOrderId(String orderId){
        this.orderId = orderId;
    }

    public String getExchangeStatus(){
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus){
        this.exchangeStatus = exchangeStatus;
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

    public String getExchangeGame(){
        return exchangeGame;
    }

    public void setExchangeGame(String exchangeGame){
        this.exchangeGame = exchangeGame;
    }

    public String getExchangeAmout(){
        return exchangeAmout;
    }

    public void setExchangeAmout(String exchangeAmout){
        this.exchangeAmout = exchangeAmout;
    }

    public String getCommitTime(){
        return commitTime;
    }

    public void setCommitTime(String commitTime){
        this.commitTime = commitTime;
    }

    public String getTradeTime(){
        return tradeTime;
    }

    public void setTradeTime(String tradeTime){
        this.tradeTime = tradeTime;
    }
}
