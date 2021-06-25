package com.nbird.mindscape;

public class SurveyHolder {
    int starA;
    int starB;
    int starC;
    int surveyIntYesOrNo;
    String str1;
    String str2;
    String str3;

    public SurveyHolder() {
    }

    public SurveyHolder(int starA, int starB, int starC, int surveyIntYesOrNo, String str1, String str2, String str3) {
        this.starA = starA;
        this.starB = starB;
        this.starC = starC;
        this.surveyIntYesOrNo = surveyIntYesOrNo;
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
    }

    public int getStarA() {
        return starA;
    }

    public void setStarA(int starA) {
        this.starA = starA;
    }

    public int getStarB() {
        return starB;
    }

    public void setStarB(int starB) {
        this.starB = starB;
    }

    public int getStarC() {
        return starC;
    }

    public void setStarC(int starC) {
        this.starC = starC;
    }

    public int getSurveyIntYesOrNo() {
        return surveyIntYesOrNo;
    }

    public void setSurveyIntYesOrNo(int surveyIntYesOrNo) {
        this.surveyIntYesOrNo = surveyIntYesOrNo;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public String getStr3() {
        return str3;
    }

    public void setStr3(String str3) {
        this.str3 = str3;
    }
}
