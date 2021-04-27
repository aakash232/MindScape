package com.nbird.mindscape;

public class Facts {

    String headingText;
    String disText;


    public Facts(){

    }

    public Facts(String headingText, String disText) {
        this.headingText = headingText;
        this.disText = disText;
    }


    public String getHeadingText() {
        return headingText;
    }

    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    public String getDisText() {
        return disText;
    }

    public void setDisText(String disText) {
        this.disText = disText;
    }
}
