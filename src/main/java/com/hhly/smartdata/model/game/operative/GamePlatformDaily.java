package com.hhly.smartdata.model.game.operative;

public class GamePlatformDaily extends GameTotalDaily{
    private int gameId;
    private String gameName;

    public int getGameId(){
        return gameId;
    }

    public void setGameId(int gameId){
        this.gameId = gameId;
    }

    public String getGameName(){
        return gameName;
    }

    public void setGameName(String gameName){
        this.gameName = gameName;
    }
}
