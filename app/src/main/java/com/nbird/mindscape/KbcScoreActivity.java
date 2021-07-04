package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KbcScoreActivity extends AppCompatActivity {
    LottieAnimationView kbc_anim;
    TextView result,priceText,wonText;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();


    public void home(View view){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(KbcScoreActivity.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
        Intent intent=new Intent(KbcScoreActivity.this,KbcPlay.class);
        startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();
    }

    public void restart(View view){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(KbcScoreActivity.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
        Intent intent=new Intent(KbcScoreActivity.this,KbcSetup.class);
        startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_score);

        result=findViewById(R.id.result);
        priceText=findViewById(R.id.priceText);
        wonText = findViewById(R.id.wonText);

        String checkAns = getIntent().getStringExtra("string");
        final int score = getIntent().getIntExtra("score",0);
        result.setText(checkAns);

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("KbcHS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int data=snapshot.getValue(Integer.class);
                    if(data<score){
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("KbcHS").setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                }catch (Exception e){
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("KbcHS").setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






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