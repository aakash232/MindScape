package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView animation;
    private static int SPLASH_TIME_OUT = 2675;
    TextView title,title1;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences songStopper = this.getSharedPreferences("SongStopperManu", 0);
        final SharedPreferences.Editor editorsongStopper = songStopper.edit();

        editorsongStopper.putInt("songStopper", 1);
        editorsongStopper.commit();




        final SharedPreferences mailreminder123 = this.getSharedPreferences("isPlaying", 0);
        final SharedPreferences.Editor isPlaying1 = mailreminder123.edit();


            isPlaying1.putInt("I",1);

        final SharedPreferences songHolder = this.getSharedPreferences("songHolder", 0);
        final SharedPreferences.Editor editorsongHolder = songHolder.edit();

        editorsongHolder.putInt("songHolder",1);
        editorsongHolder.commit();


        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        final int s1 = sh.getInt("codeRemove", 0);

        final SharedPreferences songHolder1 = this.getSharedPreferences("songKnowe", 0);
        final SharedPreferences.Editor editorsongHolder1 = songHolder1.edit();

        editorsongHolder1.putInt("timerStarterInt",0);
        editorsongHolder1.commit();

        myRef.child("Lobby").child(String.valueOf(s1)).child("player1Status").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int p=snapshot.getValue(Integer.class);
                    if(p==0){
                        myRef.child("Lobby").child(String.valueOf(s1)).removeValue();
                    }
                }catch (Exception e){
                    myRef.child("Lobby").child(String.valueOf(s1)).removeValue();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                Intent slideIntent = new Intent(MainActivity.this, SlideActivity.class);
                startActivity(slideIntent);
                finish();

                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }

        }, SPLASH_TIME_OUT);


    }




}