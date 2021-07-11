package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;

public class SongChoiceActivity extends AppCompatActivity {
    CardView audioquiz,videoquiz;
    MediaPlayer mediaPlayer,mp;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_choice);




        mainMenuActivity menuActivity=new mainMenuActivity();
        menuActivity.releaseSong();

        final SharedPreferences songStopper = this.getSharedPreferences("SongStopperManu", 0);
        final SharedPreferences.Editor editorsongStopper = songStopper.edit();


        editorsongStopper.putInt("songStopper", 1);
        editorsongStopper.commit();


        final SharedPreferences songHolder = this.getSharedPreferences("songHolder", 0);
        final SharedPreferences.Editor editorsongHolder = songHolder.edit();

        editorsongHolder.putInt("songHolder",1);
        editorsongHolder.commit();



        final SharedPreferences songHolder1 = getSharedPreferences("songKnowe", 0);
        final SharedPreferences.Editor editorsongHolder1 = songHolder1.edit();

        editorsongHolder1.putInt("timerStarterInt",0);
        editorsongHolder1.commit();

        audioquiz=(CardView) findViewById(R.id.audioquiz);
        videoquiz=(CardView) findViewById(R.id.videoquiz);

        audioquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick();
                Intent intent=new Intent(SongChoiceActivity.this,AudioQuizSinglePlayer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
            }
        });

        videoquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick();
                Intent intent=new Intent(SongChoiceActivity.this,VideoQuizSinglePlayer.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
            }
        });



        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Category");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public void buttonClick(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(SongChoiceActivity.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });

    }

    public void onBackPressed() {
        Intent intent=new Intent(SongChoiceActivity.this,mainMenuActivity.class);
        intent.putExtra("mainfinder",1);
        startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }


}