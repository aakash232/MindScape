package com.nbird.mindscape;

public class tournamentUserDataHolder {
    String score;
    String correctVsWrong;
    String totalTime;
    String totalLifeLine;
    String accu;
    int scoreInt;

    public tournamentUserDataHolder() {
    }

    public tournamentUserDataHolder(String score, String correctVsWrong, String totalTime, String totalLifeLine, String accu, int scoreInt) {
        this.score = score;
        this.correctVsWrong = correctVsWrong;
        this.totalTime = totalTime;
        this.totalLifeLine = totalLifeLine;
        this.accu = accu;
        this.scoreInt=scoreInt;
    }

    public int getScoreInt() {
        return scoreInt;
    }

    public void setScoreInt(int scoreInt) {
        this.scoreInt = scoreInt;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCorrectVsWrong() {
        return correctVsWrong;
    }

    public void setCorrectVsWrong(String correctVsWrong) {
        this.correctVsWrong = correctVsWrong;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getTotalLifeLine() {
        return totalLifeLine;
    }

    public void setTotalLifeLine(String totalLifeLine) {
        this.totalLifeLine = totalLifeLine;
    }

    public String getAccu() {
        return accu;
    }

    public void setAccu(String accu) {
        this.accu = accu;
    }
}
