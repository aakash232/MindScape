package com.nbird.mindscape;

public class mainMenuFactsHolder {

    String title;
    String dis;
    int set;
    int category;

    public mainMenuFactsHolder() {
    }


    public mainMenuFactsHolder(String title, String dis, int set,int category) {
        this.title = title;
        this.dis = dis;
        this.set = set;
        this.category=category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
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
