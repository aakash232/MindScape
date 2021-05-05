package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class KbcScoreActivity extends AppCompatActivity {
    LottieAnimationView kbc_anim;
    TextView result,priceText,wonText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_score);

        result=findViewById(R.id.result);
        priceText=findViewById(R.id.priceText);
        wonText = findViewById(R.id.wonText);

        String checkAns = getIntent().getStringExtra("string");
        int score = getIntent().getIntExtra("score",0);
        result.setText(checkAns);

        switch (score) {
            case 0: priceText.setText("nothing");
                 break;
            case 1: priceText.setText("5000");
                break;
            case 2:priceText.setText("10000");
                break;
            case 3: priceText.setText("20000");
                break;
            case 4:priceText.setText("40000");
                break;
            case 5: priceText.setText("80000");
                break;
            case 6:priceText.setText("160000");
                break;
            case 7: priceText.setText("320000");
                break;
            case 8:priceText.setText("640000");
                break;
            case 9: priceText.setText("1250000");
                break;
            case 10:priceText.setText("25 lakh");
                break;
            case 11: priceText.setText("50 lakh");
                break;
            case 12:priceText.setText("1 crore");
                break;
            case 13: priceText.setText("5 crore");
                break;
        }
    }
}