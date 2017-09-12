package com.hhly.smartdata.model.game.peoluckywheel;

import java.sql.Date;

public class GamePage{
    private String tblId;
    private String gameId;
    private String gameName;
    //0有效，1无效
    private String status;
    private Date createdDate;
    private String createdUser;
    private Date updatedDate;
    private String updatedUser;

    public String getTblId(){
        return tblId;
    }

    public void setTblId(String tblId){
        this.tblId = tblId;
    }

    public String getGameId(){
        return gameId;
    }

    public void setGameId(String gameId){
        this.gameId = gameId;
    }

    public String getGameName(){
        return gameName;
    }

    public void setGameName(String gameName){
        this.gameName = gameName;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public Date getCreatedDate(){
        return createdDate;
    }

    public void setCreatedDate(Date createdDate){
        this.createdDate = createdDate;
    }

    public String getCreatedUser(){
        return createdUser;
    }

    public void setCreatedUser(String createdUser){
        this.createdUser = createdUser;
    }

    public Date getUpdatedDate(){
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate){
        this.updatedDate = updatedDate;
    }

    public String getUpdatedUser(){
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser){
        this.updatedUser = updatedUser;
    }
}
