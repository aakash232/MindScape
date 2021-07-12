package com.nbird.mindscape;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.Random;

public class songActivity {
    ArrayList<Integer> l;
    Context context;
    MediaPlayer mediaPlayer;
    CountDownTimer countDownTimer;
    Boolean isInBackground;

    public songActivity(Context context) {
        this.context = context;
    }


    public void songStop(){
        mediaPlayer.pause();
        mediaPlayer.release();
        mediaPlayer=null;
    }

    private void musicArray(){
        l=new ArrayList<>();
        l.add(R.raw.song1);
        l.add(R.raw.song11);
        l.add(R.raw.song2);
        l.add(R.raw.song20);
        l.add(R.raw.song26);
        l.add(R.raw.song27);
        l.add(R.raw.song6);
        l.add(R.raw.song7);
    }

    private void mainMusic(){
        final Random rand = new Random();

        int i = 1;

        try{
            i = rand.nextInt(7);
        }catch (Exception e){

        }
        mediaPlayer=MediaPlayer.create(context,l.get(i));
        mediaPlayer.start();
        mediaPlayer.setVolume(0.8f,0.8f);
    }

    public void startMusic(){
        musicArray();
        mainMusic();
        countDownTimer=new CountDownTimer(1000 * 60 * 24 * 30, 1000) {
            ArrayList<Integer> r=l;
            @Override
            public void onTick(long l) {

                try{
                    ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();
                    ActivityManager.getMyMemoryState(myProcess);
                    isInBackground = myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
                    if (isInBackground) {
                        mediaPlayer.pause();
                    } else {
                        try{
                            if (!mediaPlayer.isPlaying()) {
                                mediaPlayer.start();
                                mediaPlayer.setVolume(0.4f,0.4f);
                            }
                        }catch (Exception e){

                        }

                    }
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {


                                if (mediaPlayer.isPlaying()) {
                                    mediaPlayer.stop();
                                    try {
                                        mediaPlayer.reset();
                                    }catch (Exception e){

                                    }
                                    mediaPlayer.release();

                                } else {
                                    mediaPlayer.reset();
                                    mediaPlayer.release();
                                }

                                mainMusic();

                        }
                    });
                }catch (Exception e){

                }


            }

            @Override
            public void onFinish() {

            }
        }.start();

    }





}
