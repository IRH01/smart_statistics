package com.hhly.smartdata.model.partner;

public class GameType{
    //游戏类别ID
    private int id;
    //游戏类型名称
    private String gameTypeName;
    //终端类型
    private int platformType;
    //终端类型名称
    private String platformTypeName;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getGameTypeName(){
        return gameTypeName;
    }

    public void setGameTypeName(String gameTypeName){
        this.gameTypeName = gameTypeName;
    }

    public int getPlatformType(){
        return platformType;
    }

    public void setPlatformType(int platformType){
        this.platformType = platformType;
    }

    public String getPlatformTypeName(){
        switch(platformType){
            case 1:
                platformTypeName = "PC";
                break;
            case 2:
                platformTypeName = "Android";
                break;
            case 3:
                platformTypeName = "IOS";
                break;
            case 4:
                platformTypeName = "H5";
                break;
            case 5:
                platformTypeName = "Others";
                break;
            default:
                platformTypeName = platformType + "";
                break;
        }
        return platformTypeName;
    }

    public void setPlatformTypeName(String platformTypeName){
        this.platformTypeName = platformTypeName;
    }

}
