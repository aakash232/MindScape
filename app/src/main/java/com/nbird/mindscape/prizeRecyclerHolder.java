package com.nbird.mindscape;

public class prizeRecyclerHolder {
    String pic;
    String textDis;
    String textHead;
    int set;
    int type;

    public prizeRecyclerHolder() {
    }

    public prizeRecyclerHolder(String pic, String textDis, String textHead,int set,int type) {
        this.pic = pic;
        this.textDis = textDis;
        this.textHead = textHead;
        this.set=set;
        this.type=type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTextDis() {
        return textDis;
    }

    public void setTextDis(String textDis) {
        this.textDis = textDis;
    }

    public String getTextHead() {
        return textHead;
    }

    public void setTextHead(String textHead) {
        this.textHead = textHead;
    }
}
