package com.nbird.mindscape;

public class User {
    int firstTime;
    int onevsoneOnlineFinder;

    public User() {
    }

    public User(int firstTime,int onevsoneOnlineFinder) {
        this.firstTime = firstTime;
        this.onevsoneOnlineFinder=onevsoneOnlineFinder;
    }

    public int getOnevsoneOnlineFinder() {
        return onevsoneOnlineFinder;
    }

    public void setOnevsoneOnlineFinder(int onevsoneOnlineFinder) {
        this.onevsoneOnlineFinder = onevsoneOnlineFinder;
    }

    public int getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(int firstTime) {
        this.firstTime = firstTime;
    }
}
