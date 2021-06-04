package com.nbird.mindscape;

public class buzzerManuHolder {

    int correctOrWrong;
    int answerPositionSelected;

    public buzzerManuHolder() {
    }

    public buzzerManuHolder(int correctOrWrong, int answerPositionSelected) {
        this.correctOrWrong = correctOrWrong;
        this.answerPositionSelected = answerPositionSelected;
    }

    public int getCorrectOrWrong() {
        return correctOrWrong;
    }

    public void setCorrectOrWrong(int correctOrWrong) {
        this.correctOrWrong = correctOrWrong;
    }

    public int getAnswerPositionSelected() {
        return answerPositionSelected;
    }

    public void setAnswerPositionSelected(int answerPositionSelected) {
        this.answerPositionSelected = answerPositionSelected;
    }
}
