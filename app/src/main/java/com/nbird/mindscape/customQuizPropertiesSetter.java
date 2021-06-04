package com.nbird.mindscape;

public class customQuizPropertiesSetter {
    String quizName;
    int timeDuration;
    int expertLL;
    int swapLL;
    int audienceLL;
    int fiftyfiftyLL;
    int quizCode;
    int quizPrivacy;
    String usename;
    int rate;
    int report;
    String dis;
    String proPic;
    String key;
    String myKey;
    int numberOfTimesPlayed;

    public customQuizPropertiesSetter() {
    }

    public customQuizPropertiesSetter(String quizName, int timeDuration, int expertLL, int swapLL, int audienceLL, int fiftyfiftyLL, int quizCode, int quizPrivacy,String username,int rate,int report,String dis,String proPic,String key,String myKey,int numberOfTimesPlayed) {
        this.quizName = quizName;
        this.timeDuration = timeDuration;
        this.expertLL = expertLL;
        this.swapLL = swapLL;
        this.audienceLL = audienceLL;
        this.fiftyfiftyLL = fiftyfiftyLL;
        this.quizCode = quizCode;
        this.quizPrivacy = quizPrivacy;
        this.usename=username;
        this.rate=rate;
        this.report=report;
        this.dis=dis;
        this.proPic=proPic;
        this.key=key;
        this.myKey=myKey;
        this.numberOfTimesPlayed=numberOfTimesPlayed;
    }

    public int getNumberOfTimesPlayed() {
        return numberOfTimesPlayed;
    }

    public void setNumberOfTimesPlayed(int numberOfTimesPlayed) {
        this.numberOfTimesPlayed = numberOfTimesPlayed;
    }

    public String getMyKey() {
        return myKey;
    }

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getProPic() {
        return proPic;
    }

    public void setProPic(String proPic) {
        this.proPic = proPic;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public String getUsename() {
        return usename;
    }

    public void setUsename(String usename) {
        this.usename = usename;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }

    public int getExpertLL() {
        return expertLL;
    }

    public void setExpertLL(int expertLL) {
        this.expertLL = expertLL;
    }

    public int getSwapLL() {
        return swapLL;
    }

    public void setSwapLL(int swapLL) {
        this.swapLL = swapLL;
    }

    public int getAudienceLL() {
        return audienceLL;
    }

    public void setAudienceLL(int audienceLL) {
        this.audienceLL = audienceLL;
    }

    public int getFiftyfiftyLL() {
        return fiftyfiftyLL;
    }

    public void setFiftyfiftyLL(int fiftyfiftyLL) {
        this.fiftyfiftyLL = fiftyfiftyLL;
    }

    public int getQuizCode() {
        return quizCode;
    }

    public void setQuizCode(int quizCode) {
        this.quizCode = quizCode;
    }

    public int getQuizPrivacy() {
        return quizPrivacy;
    }

    public void setQuizPrivacy(int quizPrivacy) {
        this.quizPrivacy = quizPrivacy;
    }
}
