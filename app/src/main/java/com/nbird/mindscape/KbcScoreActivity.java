package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class KbcScoreActivity extends AppCompatActivity {
    LottieAnimationView kbc_anim;
    TextView result,priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_score);

        result=findViewById(R.id.result);
        priceText=findViewById(R.id.priceText);

        String checkAns = getIntent().getStringExtra("string");
        String score = getIntent().getStringExtra("score");
        result.setText(checkAns);
        priceText.setText("You Won "+score);


    }
}