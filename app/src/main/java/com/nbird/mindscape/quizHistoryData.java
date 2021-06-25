package com.nbird.mindscape;

public class quizHistoryData {
    int Score;
    long Time;
    int Correct;
    int Wrong;

    public quizHistoryData() {
    }

    public quizHistoryData(int score, long time, int correct, int wrong) {
        Score = score;
        Time = time;
        Correct = correct;
        Wrong= wrong;
    }

    public int getWrong() {
        return Wrong;
    }

    public void setWrong(int wrong) {
        Wrong = wrong;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public long getTime() {
        return Time;
    }

    public void setTime(long time) {
        Time = time;
    }

    public int getCorrect() {
        return Correct;
    }

    public void setCorrect(int correct) {
        Correct = correct;
    }
}
