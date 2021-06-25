package com.nbird.mindscape;

public class barGroupHolder {
    int correct;
    int wrong;

    public barGroupHolder() {
    }

    public barGroupHolder(int correct, int wrong) {
        this.correct = correct;
        this.wrong = wrong;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }
}
