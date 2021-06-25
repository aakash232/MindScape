package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;

public class KbcWel extends AppCompatActivity {
    MediaPlayer kbcmusic;
    private static int SPLASH_TIME_OUT = 5500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_wel);

        final SharedPreferences songHolder = this.getSharedPreferences("songHolder", 0);
        final SharedPreferences.Editor editorsongHolder = songHolder.edit();

            editorsongHolder.putInt("songHolder",1);
            editorsongHolder.commit();

        kbcmusic = MediaPlayer.create(KbcWel.this,R.raw.kbc_mus);
        if(kbcmusic != null){
            kbcmusic.start();
        }

        new Handler().postDelayed(new Runnable() {


            public void run() {
                Intent slideIntent = new Intent(KbcWel.this,KbcPlay.class);
                startActivity(slideIntent);
                finish();
                kbcmusic.stop();
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }

        }, SPLASH_TIME_OUT);

    }
}