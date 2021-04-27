package com.nbird.mindscape;

public class mainMenuFactsHolder {

    String title;
    String dis;
    int set;

    public mainMenuFactsHolder() {
    }


    public mainMenuFactsHolder(String title, String dis, int set) {
        this.title = title;
        this.dis = dis;
        this.set = set;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }
}
