package com.nbird.mindscape;

public class mainMenuFactsHolder {

    String title;
    String dis;
    int set;
    String imageUrl;
    public mainMenuFactsHolder() {
    }


    public mainMenuFactsHolder(String title, String dis, int set,String imageUrl) {
        this.title = title;
        this.dis = dis;
        this.set = set;
        this.imageUrl=imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
