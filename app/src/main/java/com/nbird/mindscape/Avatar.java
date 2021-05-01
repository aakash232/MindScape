package com.nbird.mindscape;

public class Avatar {

    int image;
    int linearLayoutback;

    public Avatar() {
    }

    public Avatar(int image,int linearLayoutback) {
        this.image = image;
        this.linearLayoutback = linearLayoutback;
    }

    public int getLinearLayoutback() {
        return linearLayoutback;
    }

    public void setLinearLayoutback(int linearLayoutback) {
        this.linearLayoutback = linearLayoutback;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
