package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animation;
    private static int SPLASH_TIME_OUT = 2675;
    TextView title,title1;

    //Hi Zimmy And Sneha

    // hi kartik

    //hello
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        animation = findViewById(R.id.animationView);
        title=(TextView) findViewById(R.id.textView);
        title1=(TextView) findViewById(R.id.textView1);

        Animation titleAnimfadeintext = AnimationUtils.loadAnimation(this, R.anim.fadeintext);
        Animation animLeftToRight = AnimationUtils.loadAnimation(this, R.anim.animlefttoright);
        Animation animRightToLeft = AnimationUtils.loadAnimation(this, R.anim.animrighttoleft);

        title.setAnimation(titleAnimfadeintext);
        title1.setAnimation(titleAnimfadeintext);

        title.setAnimation(animLeftToRight);
        title1.setAnimation(animRightToLeft);

        new Handler().postDelayed(new Runnable() {


            public void run() {
                Intent slideIntent = new Intent(MainActivity.this, mainMenuActivity.class);
                startActivity(slideIntent);
                finish();

                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }

        }, SPLASH_TIME_OUT);


    }

    public void Animation() {animation.setSpeed(2f);
    }


}