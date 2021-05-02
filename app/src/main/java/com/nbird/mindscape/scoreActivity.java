package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class scoreActivity extends AppCompatActivity {

    int score,lifelineSum,minutes,second;
    String minutestext,secondtext;
    TextView totalScoreNumber,totalTimeTaken,totalLifeLine,totalCorrectAns;
    Long milliholder;
    int millnum;
    long totalSum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

     //   totalScoreNumber=(TextView) findViewById(R.id.totalScoreNumber);
    //    totalTimeTaken=(TextView) findViewById(R.id.totalTimeTaken);
    //    totalLifeLine=(TextView) findViewById(R.id.totalLifeLine);
    //    totalCorrectAns=(TextView) findViewById(R.id.totalCorrectAns);



        score=getIntent().getIntExtra("score", 0);
        lifelineSum=getIntent().getIntExtra("lifeline",0);
        minutes=getIntent().getIntExtra("minutes",0);
        second=getIntent().getIntExtra("seconds",0);
        minutestext=getIntent().getStringExtra("minutestext");
        secondtext=getIntent().getStringExtra("secondtext");
        milliholder=getIntent().getLongExtra("milliholder",0);

        totalCorrectAns.setText(" "+score+"/10 ");
        totalLifeLine.setText(" "+lifelineSum+"/4 ");
        totalTimeTaken.setText(" "+minutestext+":"+secondtext+" ");

        totalSum= milliholder/100;
        totalSum=totalSum+6000;
        for(int i=1;i<=lifelineSum;i++){
            totalSum=totalSum-1500;
        }
        for (int i=1;i<=score;i++){
            totalSum=totalSum+1000;
        }
        totalScoreNumber.setText(" "+totalSum+" ");



    }
}