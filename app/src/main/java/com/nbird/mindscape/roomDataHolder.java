package com.nbird.mindscape;

public class roomDataHolder {
    String hostUid;
    int numberOfPlayers;
    String hostImageUrl;
    String hostName;
    int roomCode;
    int questionNumber;
    int time;
    int mode;
    int privacy;

    public roomDataHolder() {
    }

    public roomDataHolder(String hostUid, int numberOfPlayers, String hostImageUrl, String hostName,int roomCode,int questionNumber,int time,int mode,int privacy) {
        this.hostUid = hostUid;
        this.numberOfPlayers = numberOfPlayers;
        this.hostImageUrl = hostImageUrl;
        this.hostName = hostName;
        this.roomCode = roomCode;
        this.questionNumber=questionNumber;
        this.time=time;
        this.mode=mode;
        this.privacy=privacy;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(int roomCode) {
        this.roomCode = roomCode;
    }

    public String getHostUid() {
        return hostUid;
    }

    public void setHostUid(String hostUid) {
        this.hostUid = hostUid;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    public String getHostImageUrl() {
        return hostImageUrl;
    }

    public void setHostImageUrl(String hostImageUrl) {
        this.hostImageUrl = hostImageUrl;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
