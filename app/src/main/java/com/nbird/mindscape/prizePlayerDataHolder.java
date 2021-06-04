package com.nbird.mindscape;

public class prizePlayerDataHolder {
    String upiId;
    int highestScore;
    String UID;
    int set;
    String img;
    String userName;
    int attempts;
    int isPlayed;

    public prizePlayerDataHolder() {
    }

    public prizePlayerDataHolder(String upiId, int highestScore, String UID, int set,String img,String userName,int attempts,int isPlayed) {
        this.upiId = upiId;
        this.highestScore = highestScore;
        this.UID = UID;
        this.set = set;
        this.img=img;
        this.userName=userName;
        this.attempts=attempts;
        this.isPlayed=isPlayed;

    }

    public int getIsPlayed() {
        return isPlayed;
    }

    public void setIsPlayed(int isPlayed) {
        this.isPlayed = isPlayed;
    }



    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUpiId() {
        return upiId;
    }

    public void setUpiId(String upiId) {
        this.upiId = upiId;
    }

    public int getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(int highestScore) {
        this.highestScore = highestScore;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }
}
