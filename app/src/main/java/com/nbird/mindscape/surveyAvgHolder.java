package com.nbird.mindscape;

public class surveyAvgHolder {
    int starAvgNum;
    int numberOfSurvey;


    public surveyAvgHolder() {
    }

    public surveyAvgHolder(int starAvgNum, int numberOfSurvey) {
        this.starAvgNum = starAvgNum;
        this.numberOfSurvey = numberOfSurvey;
    }

    public int getStarAvgNum() {
        return starAvgNum;
    }

    public void setStarAvgNum(int starAvgNum) {
        this.starAvgNum = starAvgNum;
    }

    public int getNumberOfSurvey() {
        return numberOfSurvey;
    }

    public void setNumberOfSurvey(int numberOfSurvey) {
        this.numberOfSurvey = numberOfSurvey;
    }
}
