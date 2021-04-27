package com.nbird.mindscape;

public class Modes {
    private String Title;
    private int Thumbnail;
    private String Dis;






    public Modes() {
    }

    public Modes(String title, int thumbnail,String dis) {
        Title = title;
        Thumbnail = thumbnail;
        Dis=dis;


    }



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }
}
