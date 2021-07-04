package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class KbcPlay extends AppCompatActivity {

    ImageView music;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    int score;
    int scoreConvertedToMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_play);

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("KbcHS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    score=snapshot.getValue(Integer.class);
                }catch (Exception e){
                  score=50;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void playB(View view){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(KbcPlay.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
        Intent intent = new Intent(getApplicationContext(),KbcSetup.class);
        startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();
    }

    public void score(View view){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(KbcPlay.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(KbcPlay.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(KbcPlay.this).inflate(R.layout.kbc_alertdialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);



        TextView mainDis=(TextView) view1.findViewById(R.id.textTitle);
        LottieAnimationView anim=(LottieAnimationView) view1.findViewById(R.id.imageIcon);

        anim.setAnimation(R.raw.rocketsforkbcanim);



        switch (score) {
            case 0: ;
                break;
            case 1: mainDis.setText("You Highest Ever Win : 5000");
                break;
            case 2:mainDis.setText("You Highest Ever Win : 10000");
                break;
            case 3: mainDis.setText("You Highest Ever Win : 20000");
                break;
            case 4:mainDis.setText("You Highest Ever Win : 40000");
                break;
            case 5: mainDis.setText("You Highest Ever Win : 80000");
                break;
            case 6:mainDis.setText("You Highest Ever Win : 160000");
                break;
            case 7: mainDis.setText("You Highest Ever Win : 320000");
                break;
            case 8:mainDis.setText("You Highest Ever Win : 640000");
                break;
            case 9: mainDis.setText("You Highest Ever Win : 1250000");
                break;
            case 10:mainDis.setText("You Highest Ever Win : 25 lakh");
                break;
            case 11: mainDis.setText("You Highest Ever Win : 50 lakh");
                break;
            case 12:mainDis.setText("You Highest Ever Win : 1 crore");
                break;
            case 13: mainDis.setText("You Highest Ever Win : 5 crore");
                break;
            case 50: mainDis.setText("You Have To Play The Game First In Order To Get The Highest Score");
                break;
            default:
                mainDis.setText("Network Slow!Try Again");break;
        }

        ((Button) view1.findViewById(R.id.buttonYes)).setText("OK");


        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(KbcPlay.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                alertDialog.dismiss();
            }
        });
    }
    public void exit(View view){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(KbcPlay.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
        Intent intent = new Intent(this,mainMenuActivity.class);
        startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();
    }

}