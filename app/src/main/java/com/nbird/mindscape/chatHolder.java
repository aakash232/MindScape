package com.nbird.mindscape;

public class chatHolder {
    String imageUrl;
    String name;
    String text;
    int playerNum;
    int position10;


    public chatHolder() {
    }

    public chatHolder(String imageUrl, String name, String text,int playerNum,int position10) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.text = text;
        this.playerNum=playerNum;
        this.position10=position10;
    }

    public int getPosition10() {
        return position10;
    }

    public void setPosition10(int position10) {
        this.position10 = position10;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
