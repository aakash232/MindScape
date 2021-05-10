package com.nbird.mindscape;

public class onevsoneOnlinePlayerList {
    String uid;
    int status;


    public onevsoneOnlinePlayerList() {
    }

    public onevsoneOnlinePlayerList(String uid, int status) {
        this.uid = uid;
        this.status = status;
    }

    public String getUID() {
        return uid;
    }

    public void setUID(String UID) {
        this.uid = UID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
