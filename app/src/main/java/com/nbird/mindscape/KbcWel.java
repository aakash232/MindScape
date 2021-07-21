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
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_wel);


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


        kbcmusic = MediaPlayer.create(KbcWel.this,R.raw.kbc_mus);
        if(kbcmusic != null){
            kbcmusic.start();
            kbcmusic.setVolume(0.1f,0.1f);
        }

        new Handler().postDelayed(new Runnable() {


            public void run() {
                Intent slideIntent = new Intent(KbcWel.this,KbcPlay.class);
                startActivity(slideIntent);
                finish();
                kbcmusic.stop();
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            }

        }, SPLASH_TIME_OUT);

    }
    public void onBackPressed() {
       /* Intent intent=new Intent(KbcWel.this,mainMenuActivity.class);
        intent.putExtra("mainfinder",1);
        startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();*/

    }
}