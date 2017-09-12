package com.hhly.smartdata.model.partner;

import java.util.Date;

/**
 * 撩妹德州投注数据
 *
 * @author wanghuang
 */
public class LmdzBettingLog{
    private Date settleTime;
    private String userId;
    private int platformId;
    //局号
    private String bureauNo;
    //房间号
    private int roomNo;
    private String gameType;
    //收益金额
    private double incomeAmount;
    private String betAmount;
    //游戏结果
    private String boardResult;
    //牌型
    private String cardType;
    //牌面
    private String card;

    private String gameTypeName;

    public Date getSettleTime(){
        return settleTime;
    }

    public void setSettleTime(Date settleTime){
        this.settleTime = settleTime;
    }

    public String getUserId(){
        return userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public int getPlatformId(){
        return platformId;
    }

    public void setPlatformId(int platformId){
        this.platformId = platformId;
    }

    public String getBureauNo(){
        return bureauNo;
    }

    public void setBureauNo(String bureauNo){
        this.bureauNo = bureauNo;
    }

    public int getRoomNo(){
        return roomNo;
    }

    public void setRoomNo(int roomNo){
        this.roomNo = roomNo;
    }

    public String getGameType(){
        return gameType;
    }

    public void setGameType(String gameType){
        this.gameType = gameType;
    }

    public double getIncomeAmount(){
        return incomeAmount;
    }

    public void setIncomeAmount(double incomeAmount){
        this.incomeAmount = incomeAmount;
    }

    public String getBetAmount(){
        return betAmount;
    }

    public void setBetAmount(String betAmount){
        this.betAmount = betAmount;
    }

    public String getBoardResult(){
        return boardResult;
    }

    public void setBoardResult(String boardResult){
        this.boardResult = boardResult;
    }

    public String getCardType(){
        return cardType;
    }

    public void setCardType(String cardType){
        this.cardType = cardType;
    }

    public String getCard(){
        return card;
    }

    public void setCard(String card){
        this.card = card;
    }

    public String getGameTypeName(){
        if("1".equals(gameType)){
            gameTypeName = "PC-海妖直播";
        }else if("2".equals(gameType)){
            gameTypeName = "H5-海妖直播";
        }else{
            gameTypeName = gameType;
        }
        return gameTypeName;
    }
}
