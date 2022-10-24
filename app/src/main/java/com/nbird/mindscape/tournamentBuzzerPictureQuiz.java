package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tournamentBuzzerPictureQuiz extends AppCompatActivity {

    TextView player1Score, player2Score, player3Score, player4Score;
    LottieAnimationView player1anim, player2anim, player3anim, player4anim;

    LottieAnimationView anim1, anim2, anim3, anim4, anim5, anim6, anim7, anim8, anim9, anim10;
    LottieAnimationView anim11, anim12, anim13, anim14, anim15, anim16, anim17, anim18, anim19, anim20;
    LottieAnimationView anim21, anim22, anim23, anim24, anim25, anim26, anim27, anim28, anim29, anim30;
    LottieAnimationView anim31, anim32, anim33, anim34, anim35, anim36, anim37, anim38, anim39, anim40;
    LottieAnimationView animz1, animz2, animz3, animz4, animz5, animz6, animz7, animz8, animz9, animz10;
    LottieAnimationView animz11, animz12, animz13, animz14, animz15, animz16, animz17, animz18, animz19, animz20;
    LottieAnimationView animz21, animz22, animz23, animz24, animz25, animz26, animz27, animz28, animz29, animz30;
    LottieAnimationView animz31, animz32, animz33, animz34, animz35, animz36, animz37, animz38, animz39, animz40;
    TextView opponentName, opponentName2, opponentName3, opponentName4;
    ImageView opponentPic, opponentPic2, opponentPic3, opponentPic4;
    Dialog loadingDialog;
    private List<pictureQuizHolder> list;
    long milliHolder;
    TextView questionTextView, scoreBoard, timerText;
    Button option1, option2, option3, option4;
    LinearLayout linearLayout;

    String myName, myImageUrl, hostUid, hostName, hostImageUrl, name2String, name3String, name4String, image2Url, image3Url, image4Url;
    int playerNum;
    int roomCode;
    int questionNum, timerNum;
    int numberOfPlayers;
    List<Integer> arrlist12345;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    int minutes = 2;
    int second = 59;
    int category;
    int num = 0;

    int timePerQuestion;
    int totalSecond;
    int totalMinutes;
    int totalQuestion;
    private int position = 0;
    private int count;
    CountDownTimer countDownTimer,countDownTimeralerDialog;

    String minutestext, secondtext;
    int secondSecond;
    buzzerManuHolder mainHolder;
    int mainInt = 0;

    int myScore = 0;

    int correctAns = 0;
    int wrongAns = 0;
    ValueEventListener listner,lisnerrat1,lisnerrat2,lisnerrat3,lisnerrat4,lisnerrat5,lisnerrat6,lisnerrat7,lisnerrat8,lisnerrat9,lisnerrat10,lisnerrat11,lisnerrat12,lisnerrat13,lisnerrat14;
    int oppoStatus;

    ImageView questionImage;
    String linkHolder;
    int isAttempt=0,numMode;
    private InterstitialAd mInterstitialAd;

    private void loadAds(){

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    songActivity songActivity;
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        if(countDownTimeralerDialog!=null){
            countDownTimeralerDialog.cancel();
        }

        for(int i=1;i<=20;i++){
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("player1").child(String.valueOf(i)).removeEventListener(lisnerrat1);
            }catch (Exception e){

            }
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("player2").child(String.valueOf(i)).removeEventListener(lisnerrat1);
            }catch (Exception e){

            }
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("player3").child(String.valueOf(i)).removeEventListener(lisnerrat1);
            }catch (Exception e){

            }
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("player4").child(String.valueOf(i)).removeEventListener(lisnerrat1);
            }catch (Exception e){

            }
        }

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child("player1Score").removeEventListener(lisnerrat2);

        }catch (Exception e){

        }

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child("player2Score").removeEventListener(lisnerrat2);
        }catch (Exception e){

        }

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child("player3Score").removeEventListener(lisnerrat2);
        }catch (Exception e){

        }

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child("player4Score").removeEventListener(lisnerrat2);
        }catch (Exception e){

        }


        for(int i=1;i<=20;i++){
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Answer").child(String.valueOf(i)).removeEventListener(lisnerrat3);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Answer").child(String.valueOf(i)).removeEventListener(lisnerrat4);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Answer").child(String.valueOf(i)).removeEventListener(lisnerrat5);

            }catch (Exception e){

            }
        }

        for(int i=1;i<=20;i++){
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Answer").child(String.valueOf(i)).removeEventListener(lisnerrat6);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Answer").child(String.valueOf(i)).removeEventListener(lisnerrat7);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Answer").child(String.valueOf(i)).removeEventListener(lisnerrat8);

            }catch (Exception e){

            }
        }

        for(int i=1;i<=20;i++){
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Answer").child(String.valueOf(i)).removeEventListener(lisnerrat9);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Answer").child(String.valueOf(i)).removeEventListener(lisnerrat10);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Answer").child(String.valueOf(i)).removeEventListener(lisnerrat11);

            }catch (Exception e){

            }
        }


        for(int i=1;i<=20;i++){
            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Answer").child(String.valueOf(i)).removeEventListener(lisnerrat12);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Answer").child(String.valueOf(i)).removeEventListener(lisnerrat13);

            }catch (Exception e){

            }

            try{
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Answer").child(String.valueOf(i)).removeEventListener(lisnerrat14);

            }catch (Exception e){

            }
        }


        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(listner);
        }catch (Exception e){

        }



        Runtime.getRuntime().gc();

       
    }
    public void songStopperAndResumer(){
        final SharedPreferences songStopper = this.getSharedPreferences("SongRemember", 0);
        final SharedPreferences.Editor editorsongStopper = songStopper.edit();

        final Boolean songDetect = songStopper.getBoolean("IsPlaying",true);
        CardView cardViewSpeaker=(CardView) findViewById(R.id.cardViewSpeaker);
        final ImageView speakerImage=(ImageView) findViewById(R.id.speakerImage);
        final LinearLayout Speaker=(LinearLayout) findViewById(R.id.Speaker);
        if(songDetect){
            songActivity=new songActivity(this);
            songActivity.startMusic();
        }else{
            Speaker.setBackgroundResource(R.drawable.usedicon);
            speakerImage.setBackgroundResource(R.drawable.speakeroff);
        }

        cardViewSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean songDetect9 = songStopper.getBoolean("IsPlaying",true);
                if(songDetect9){
                    songActivity.songStop();
                    Speaker.setBackgroundResource(R.drawable.usedicon);
                    speakerImage.setBackgroundResource(R.drawable.speakeroff);
                    editorsongStopper.putBoolean("IsPlaying", false);
                    editorsongStopper.commit();
                }else{
                    songActivity=new songActivity(tournamentBuzzerPictureQuiz.this);
                    songActivity.startMusic();
                    Speaker.setBackgroundResource(R.drawable.whitewithblackstroke);
                    speakerImage.setBackgroundResource(R.drawable.speakeron);
                    editorsongStopper.putBoolean("IsPlaying", true);
                    editorsongStopper.commit();
                }




            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_buzzer_picture_quiz);

        loadAds();
        songStopperAndResumer();


        player1Score = (TextView) findViewById(R.id.player1Score);
        player2Score = (TextView) findViewById(R.id.player2Score);
        player3Score = (TextView) findViewById(R.id.player3Score);
        player4Score = (TextView) findViewById(R.id.player4Score);

        player1anim = (LottieAnimationView) findViewById(R.id.player1anim);
        player2anim = (LottieAnimationView) findViewById(R.id.player2anim);
        player3anim = (LottieAnimationView) findViewById(R.id.player3anim);
        player4anim = (LottieAnimationView) findViewById(R.id.player4anim);


        anim1 = (LottieAnimationView) findViewById(R.id.anim1);
        anim2 = (LottieAnimationView) findViewById(R.id.anim2);
        anim3 = (LottieAnimationView) findViewById(R.id.anim3);
        anim4 = (LottieAnimationView) findViewById(R.id.anim4);
        anim5 = (LottieAnimationView) findViewById(R.id.anim5);
        anim6 = (LottieAnimationView) findViewById(R.id.anim6);
        anim7 = (LottieAnimationView) findViewById(R.id.anim7);
        anim8 = (LottieAnimationView) findViewById(R.id.anim8);
        anim9 = (LottieAnimationView) findViewById(R.id.anim9);
        anim10 = (LottieAnimationView) findViewById(R.id.anim10);
        anim11 = (LottieAnimationView) findViewById(R.id.anim11);
        anim12 = (LottieAnimationView) findViewById(R.id.anim12);
        anim13 = (LottieAnimationView) findViewById(R.id.anim13);
        anim14 = (LottieAnimationView) findViewById(R.id.anim14);
        anim15 = (LottieAnimationView) findViewById(R.id.anim15);
        anim16 = (LottieAnimationView) findViewById(R.id.anim16);
        anim17 = (LottieAnimationView) findViewById(R.id.anim17);
        anim18 = (LottieAnimationView) findViewById(R.id.anim18);
        anim19 = (LottieAnimationView) findViewById(R.id.anim19);
        anim20 = (LottieAnimationView) findViewById(R.id.anim20);
        anim21 = (LottieAnimationView) findViewById(R.id.anim21);
        anim22 = (LottieAnimationView) findViewById(R.id.anim22);
        anim23 = (LottieAnimationView) findViewById(R.id.anim23);
        anim24 = (LottieAnimationView) findViewById(R.id.anim24);
        anim25 = (LottieAnimationView) findViewById(R.id.anim25);
        anim26 = (LottieAnimationView) findViewById(R.id.anim26);
        anim27 = (LottieAnimationView) findViewById(R.id.anim27);
        anim28 = (LottieAnimationView) findViewById(R.id.anim28);
        anim29 = (LottieAnimationView) findViewById(R.id.anim29);
        anim30 = (LottieAnimationView) findViewById(R.id.anim30);
        anim31 = (LottieAnimationView) findViewById(R.id.anim31);
        anim32 = (LottieAnimationView) findViewById(R.id.anim32);
        anim33 = (LottieAnimationView) findViewById(R.id.anim33);
        anim34 = (LottieAnimationView) findViewById(R.id.anim34);
        anim35 = (LottieAnimationView) findViewById(R.id.anim35);
        anim36 = (LottieAnimationView) findViewById(R.id.anim36);
        anim37 = (LottieAnimationView) findViewById(R.id.anim37);
        anim38 = (LottieAnimationView) findViewById(R.id.anim38);
        anim39 = (LottieAnimationView) findViewById(R.id.anim39);
        anim40 = (LottieAnimationView) findViewById(R.id.anim40);

        animz1 = (LottieAnimationView) findViewById(R.id.animz1);
        animz2 = (LottieAnimationView) findViewById(R.id.animz2);
        animz3 = (LottieAnimationView) findViewById(R.id.animz3);
        animz4 = (LottieAnimationView) findViewById(R.id.animz4);
        animz5 = (LottieAnimationView) findViewById(R.id.animz5);
        animz6 = (LottieAnimationView) findViewById(R.id.animz6);
        animz7 = (LottieAnimationView) findViewById(R.id.animz7);
        animz8 = (LottieAnimationView) findViewById(R.id.animz8);
        animz9 = (LottieAnimationView) findViewById(R.id.animz9);
        animz10 = (LottieAnimationView) findViewById(R.id.animz10);
        animz11 = (LottieAnimationView) findViewById(R.id.animz11);
        animz12 = (LottieAnimationView) findViewById(R.id.animz12);
        animz13 = (LottieAnimationView) findViewById(R.id.animz13);
        animz14 = (LottieAnimationView) findViewById(R.id.animz14);
        animz15 = (LottieAnimationView) findViewById(R.id.animz15);
        animz16 = (LottieAnimationView) findViewById(R.id.animz16);
        animz17 = (LottieAnimationView) findViewById(R.id.animz17);
        animz18 = (LottieAnimationView) findViewById(R.id.animz18);
        animz19 = (LottieAnimationView) findViewById(R.id.animz19);
        animz20 = (LottieAnimationView) findViewById(R.id.animz20);
        animz21 = (LottieAnimationView) findViewById(R.id.animz21);
        animz22 = (LottieAnimationView) findViewById(R.id.animz22);
        animz23 = (LottieAnimationView) findViewById(R.id.animz23);
        animz24 = (LottieAnimationView) findViewById(R.id.animz24);
        animz25 = (LottieAnimationView) findViewById(R.id.animz25);
        animz26 = (LottieAnimationView) findViewById(R.id.animz26);
        animz27 = (LottieAnimationView) findViewById(R.id.animz27);
        animz28 = (LottieAnimationView) findViewById(R.id.animz28);
        animz29 = (LottieAnimationView) findViewById(R.id.animz29);
        animz30 = (LottieAnimationView) findViewById(R.id.animz30);
        animz31 = (LottieAnimationView) findViewById(R.id.animz31);
        animz32 = (LottieAnimationView) findViewById(R.id.animz32);
        animz33 = (LottieAnimationView) findViewById(R.id.animz33);
        animz34 = (LottieAnimationView) findViewById(R.id.animz34);
        animz35 = (LottieAnimationView) findViewById(R.id.animz35);
        animz36 = (LottieAnimationView) findViewById(R.id.animz36);
        animz37 = (LottieAnimationView) findViewById(R.id.animz37);
        animz38 = (LottieAnimationView) findViewById(R.id.animz38);
        animz39 = (LottieAnimationView) findViewById(R.id.animz39);
        animz40 = (LottieAnimationView) findViewById(R.id.animz40);


        questionTextView = findViewById(R.id.questionTip);
        scoreBoard = findViewById(R.id.questionNumber);
        option1 = (Button) findViewById(R.id.button1);
        option2 = (Button) findViewById(R.id.button2);
        option3 = (Button) findViewById(R.id.button3);
        option4 = (Button) findViewById(R.id.button4);
        linearLayout = (LinearLayout) findViewById(R.id.linearButtonlayout);
        timerText = (TextView) findViewById(R.id.timer);
        questionImage=(ImageView) findViewById(R.id.questionImage);

        opponentName = (TextView) findViewById(R.id.opponentName);
        opponentName2 = (TextView) findViewById(R.id.opponentName2);
        opponentName3 = (TextView) findViewById(R.id.opponentName3);
        opponentName4 = (TextView) findViewById(R.id.opponentName4);

        opponentPic = (ImageView) findViewById(R.id.opponentPic);
        opponentPic2 = (ImageView) findViewById(R.id.opponentPic2);
        opponentPic3 = (ImageView) findViewById(R.id.opponentPic3);
        opponentPic4 = (ImageView) findViewById(R.id.opponentPic4);


        myName = getIntent().getStringExtra("myName");
        myImageUrl = getIntent().getStringExtra("myImageUrl");
        hostUid = getIntent().getStringExtra("hostUID");
        hostName = getIntent().getStringExtra("hostName");
        hostImageUrl = getIntent().getStringExtra("hostImageUrl");
        name2String = getIntent().getStringExtra("player2Name");
        name3String = getIntent().getStringExtra("player3Name");
        name4String = getIntent().getStringExtra("player4Name");
        image2Url = getIntent().getStringExtra("player2ImageUrl");
        image3Url = getIntent().getStringExtra("player3ImageUrl");
        image4Url = getIntent().getStringExtra("player4ImageUrl");
        roomCode = getIntent().getIntExtra("roomCode", 0);
        playerNum = getIntent().getIntExtra("playerNumber", 1);
        arrlist12345 = getIntent().getIntegerArrayListExtra("arrList12345");
        questionNum = getIntent().getIntExtra("questionNum", 0);
        timerNum = getIntent().getIntExtra("timerNum", 0);
        numberOfPlayers = getIntent().getIntExtra("numberOfPlayers", 1);
        numMode=getIntent().getIntExtra("numMode",0);


        if (playerNum == 2 || playerNum == 3 || playerNum == 4) {
            opponentRemovedKnower();
        }



        switch (numberOfPlayers) {
            case 1:
                player1Score.setText("Score : 0 ");
                break;
            case 2:
                player1Score.setText("Score : 0 ");
                player2Score.setText("Score : 0 ");
                break;
            case 3:
                player1Score.setText("Score : 0 ");
                player2Score.setText("Score : 0 ");
                player3Score.setText("Score : 0 ");
                break;
            case 4:
                player1Score.setText("Score : 0 ");
                player2Score.setText("Score : 0 ");
                player3Score.setText("Score : 0 ");
                player4Score.setText("Score : 0 ");
                break;
        }

        if (playerNum == 1) {
            switch (questionNum) {
                case 0:
                    for (int i = 1; i <= 10; i++) {
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(i)).setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                    break;
                case 1:
                    for (int i = 1; i <= 15; i++) {
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(i)).setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                    break;
                case 2:
                    for (int i = 1; i <= 20; i++) {
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(i)).setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                    break;
            }
        }

        dataSetterOfIntent();

        if (timerNum == 0) {
            minutes = 2;
            totalSecond = 180;
        } else if (timerNum == 1) {
            minutes = 4;
            second = 29;
            totalSecond = 270;
        } else if (timerNum == 2) {
            minutes = 5;
            totalSecond = 360;
        }


        loadingDialog = new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        category = getIntent().getIntExtra("category", 1);
        list = new ArrayList<>();

       // loadingDialog.show();


        if (questionNum == 0) {
            for (int i = 0; i < 12; i++) {

                totalQuestion = 10;
                // create instance of Random class
                if(arrlist12345.size()==12){
                    fireBaseData(arrlist12345.get(i));
                }else{

                    final int finalI = i;
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                arrlist12345.add(snapshot.getValue(Integer.class));
                                fireBaseData(arrlist12345.get(finalI));
                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }




                /*    final int finalI = i;
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                arrlist12345.add(snapshot.getValue(Integer.class));
                                fireBaseData(arrlist12345.get(finalI));
                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });*/
                /*    Random rand = new Random();
                    // Generate random integers in range 0 to 29
                    final int setNumber = rand.nextInt(29)+1;  //NEED TO CHANGE HERE
                    //NEED TO CHANGE HERE
                    fireBaseData(setNumber);*/


            }
        } else if (questionNum == 1) {
            for (int i = 0; i < 17; i++) {

                totalQuestion = 15;
                // create instance of Random class
                if(arrlist12345.size()==17){

                    // create instance of Random class
                    fireBaseData(arrlist12345.get(i));
                } else{

                    final int finalI = i;
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                arrlist12345.add(snapshot.getValue(Integer.class));
                                fireBaseData(arrlist12345.get(finalI));
                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }

                /*    final int finalI = i;
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                arrlist12345.add(snapshot.getValue(Integer.class));
                                fireBaseData(arrlist12345.get(finalI));
                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });*/


            }
        } else {
            for (int i = 0; i < 22; i++) {

                totalQuestion = 20;
                // create instance of Random class
                if(arrlist12345.size()==22){

                    // create instance of Random class
                    fireBaseData(arrlist12345.get(i));
                } else{

                    final int finalI = i;
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                arrlist12345.add(snapshot.getValue(Integer.class));
                                fireBaseData(arrlist12345.get(finalI));
                            } catch (Exception e) {

                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }




            }
        }


        switch (playerNum) {
            case 1:

                scoreReceiver("player2Score", player2Score);
                scoreReceiver("player3Score", player3Score);
                scoreReceiver("player4Score", player4Score);
                switch (questionNum) {
                    case 0:
                        dataReceivingForBuzzer("player2", 10, player2anim);
                        dataReceivingForBuzzer("player3", 10, player3anim);
                        dataReceivingForBuzzer("player4", 10, player4anim);
                        opponentAnswersReceivingPlayer1("player2Answer", "player3Answer", "player4Answer", 10);
                        break;
                    case 1:
                        dataReceivingForBuzzer("player2", 15, player2anim);
                        dataReceivingForBuzzer("player3", 15, player3anim);
                        dataReceivingForBuzzer("player4", 15, player4anim);
                        opponentAnswersReceivingPlayer1("player2Answer", "player3Answer", "player4Answer", 15);
                        break;
                    case 2:
                        dataReceivingForBuzzer("player2", 20, player2anim);
                        dataReceivingForBuzzer("player3", 20, player3anim);
                        dataReceivingForBuzzer("player4", 20, player4anim);
                        opponentAnswersReceivingPlayer1("player2Answer", "player3Answer", "player4Answer", 20);
                        break;
                }
                break;

            case 2:

                scoreReceiver("player1Score", player1Score);
                scoreReceiver("player3Score", player3Score);
                scoreReceiver("player4Score", player4Score);

                switch (questionNum) {
                    case 0:
                        dataReceivingForBuzzer("player1", 10, player1anim);
                        dataReceivingForBuzzer("player3", 10, player3anim);
                        dataReceivingForBuzzer("player4", 10, player4anim);
                        opponentAnswersReceivingPlayer2("player1Answer", "player3Answer", "player4Answer", 10);
                        break;
                    case 1:
                        dataReceivingForBuzzer("player1", 15, player1anim);
                        dataReceivingForBuzzer("player3", 15, player3anim);
                        dataReceivingForBuzzer("player4", 15, player4anim);
                        opponentAnswersReceivingPlayer2("player1Answer", "player3Answer", "player4Answer", 15);
                        break;
                    case 2:
                        dataReceivingForBuzzer("player1", 20, player1anim);
                        dataReceivingForBuzzer("player3", 20, player3anim);
                        dataReceivingForBuzzer("player4", 20, player4anim);
                        opponentAnswersReceivingPlayer2("player1Answer", "player3Answer", "player4Answer", 20);
                        break;
                }
                break;


            case 3:

                scoreReceiver("player1Score", player1Score);
                scoreReceiver("player2Score", player2Score);
                scoreReceiver("player4Score", player4Score);

                switch (questionNum) {
                    case 0:
                        dataReceivingForBuzzer("player1", 10, player1anim);
                        dataReceivingForBuzzer("player2", 10, player2anim);
                        dataReceivingForBuzzer("player4", 10, player4anim);
                        opponentAnswersReceivingPlayer3("player1Answer", "player2Answer", "player4Answer", 10);
                        break;
                    case 1:
                        dataReceivingForBuzzer("player1", 15, player1anim);
                        dataReceivingForBuzzer("player2", 15, player2anim);
                        dataReceivingForBuzzer("player4", 15, player4anim);
                        opponentAnswersReceivingPlayer3("player1Answer", "player2Answer", "player4Answer", 15);
                        break;
                    case 2:
                        dataReceivingForBuzzer("player1", 20, player1anim);
                        dataReceivingForBuzzer("player2", 20, player2anim);
                        dataReceivingForBuzzer("player4", 20, player4anim);
                        opponentAnswersReceivingPlayer3("player1Answer", "player2Answer", "player4Answer", 20);
                        break;
                }
                break;

            case 4:

                scoreReceiver("player1Score", player1Score);
                scoreReceiver("player2Score", player2Score);
                scoreReceiver("player3Score", player3Score);

                switch (questionNum) {
                    case 0:
                        dataReceivingForBuzzer("player1", 10, player1anim);
                        dataReceivingForBuzzer("player2", 10, player2anim);
                        dataReceivingForBuzzer("player3", 10, player3anim);
                        opponentAnswersReceivingPlayer4("player1Answer", "player2Answer", "player3Answer", 10);
                        break;
                    case 1:
                        dataReceivingForBuzzer("player1", 15, player1anim);
                        dataReceivingForBuzzer("player2", 15, player2anim);
                        dataReceivingForBuzzer("player3", 15, player3anim);
                        opponentAnswersReceivingPlayer4("player1Answer", "player2Answer", "player3Answer", 15);
                        break;
                    case 2:
                        dataReceivingForBuzzer("player1", 20, player1anim);
                        dataReceivingForBuzzer("player2", 20, player2anim);
                        dataReceivingForBuzzer("player3", 20, player3anim);
                        opponentAnswersReceivingPlayer4("player1Answer", "player2Answer", "player3Answer", 20);
                        break;
                }
                break;
        }
        timePerQuestion = totalSecond / totalQuestion;
        secondSecond = timePerQuestion;


        AlertDialog.Builder builder=new AlertDialog.Builder(tournamentBuzzerPictureQuiz.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(tournamentBuzzerPictureQuiz.this).inflate(R.layout.buzzerpicturedialog,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        final TextView textView=view1.findViewById(R.id.textTitle);


        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }


        countDownTimeralerDialog=new CountDownTimer(1000*10,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Quiz Starts In "+ millisUntilFinished/1000 + " Seconds. Picture Is Getting Loaded!!!Please Wait.");
            }

            @Override
            public void onFinish() {
                alertDialog.dismiss();
                quizTime(timePerQuestion);
            }
        }.start();






    }







    public void picWaiter(){
        if(questionImage.getDrawable() != null){
            try {
                loadingDialog.dismiss();
            }catch (Exception e){

            }

        }
    }


    public void quizTime(final int timePerQuestion) {
        countDownTimer = new CountDownTimer(timePerQuestion * 1000, 1000) {
            @Override
            public void onTick(long l) {
                minutestext = "00";
                secondSecond--;

                if (secondSecond < 10) {
                    if (secondSecond == 0) {
                        secondtext = "00";
                    } else {
                        secondtext = "0" + String.valueOf(secondSecond);
                    }
                } else {
                    secondtext = String.valueOf(secondSecond);
                }
                timerText.setText(" Timer " + minutestext + ":" + secondtext + " ");
            }

            @Override
            public void onFinish() {

                if(isAttempt==0){

                    switch (playerNum){
                        case 1:
                            answerStatus("player1Answer",3);break;
                        case 2:
                            answerStatus("player2Answer",3);break;
                        case 3:
                            answerStatus("player3Answer",3);break;
                        case 4:
                            answerStatus("player4Answer",3);break;
                    }

                    switch (playerNum){
                        case 1:
                            myNotAnswerAnimManupulator1(anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);break;
                        case 2:
                            myNotAnswerAnimManupulator1(anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);break;
                        case 3:
                            myNotAnswerAnimManupulator1(anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);break;
                        case 4:
                            myNotAnswerAnimManupulator1(anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);break;
                    }
                }

                isAttempt=0;



                mainInt = 0;
                player1anim.setVisibility(View.GONE);
                player2anim.setVisibility(View.GONE);
                player3anim.setVisibility(View.GONE);
                player4anim.setVisibility(View.GONE);

                switch (questionNum) {
                    case 0:
                        if (position == 9) {
                            nextButtonAlternative();
                        } else {
                            secondSecond = timePerQuestion;
                            nextButtonAlternative();
                            quizTime(timePerQuestion);
                        }
                        break;
                    case 1:
                        if (position == 14) {
                            nextButtonAlternative();
                        } else {
                            secondSecond = timePerQuestion;
                            nextButtonAlternative();
                            quizTime(timePerQuestion);
                        }
                        break;
                    case 2:
                        if (position == 19) {
                            nextButtonAlternative();
                        } else {
                            secondSecond = timePerQuestion;
                            nextButtonAlternative();
                            quizTime(timePerQuestion);
                        }
                        break;
                }
            }
        }.start();
    }


    public void fireBaseData(int setNumber) {
        myRef.child("PictureQuizMain").child(String.valueOf(setNumber)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    list.add(snapshot.getValue(pictureQuizHolder.class));
                    try{
                        Glide.with(getBaseContext())
                                .load(list.get(num).getQuestionPicture()).error((Drawable) Glide.with(getBaseContext()).load(list.get(num).getQuestionPicture()).error((Drawable) Glide.with(getBaseContext()).load(list.get(num).getQuestionPicture()).error((Drawable) Glide.with(getBaseContext()).load(list.get(num).getQuestionPicture()).preload(20,10)).preload(20,10)).preload(20,10))
                                .preload(20, 10);
                    }catch (Exception e){

                    }

                    num++;

                quizManupulator();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tournamentBuzzerPictureQuiz.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
               

            }
        });
    }

    public void nextButtonAlternative() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            enableOption(true);
        }
        position++;
        //    LLTrueManupulator();
        switch (questionNum) {
            case 0:
                if (position == 10) {
                    quizIntent();
                }
                break;
            case 1:
                if (position == 15) {
                    quizIntent();
                }
                break;
            case 2:
                if (position == 20) {
                    quizIntent();
                }
                break;
        }
        count = 0;
        try{
            playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
        }catch (Exception e){

        }

    }


    public void quizManupulator() {
        switch (questionNum) {
            case 0:
                if (num == 10) {
                    if (list.size() > 0) {
                        for (int i = 0; i < 4; i++) {
                            final int finalI = i;
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View view) {

                                    try{
                                        checkAnswer((Button) view, finalI);
                                    }catch (Exception e){
                                //        Toast.makeText(tournamentBuzzerPictureQuiz.this, "Please Wait", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
                    } else {
                        finish();
                        Toast.makeText(tournamentBuzzerPictureQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();
                       

                    }
                    //       loadingDialog.dismiss();
                }
                break;
            case 1:
                if (num == 15) {
                    if (list.size() > 0) {
                        for (int i = 0; i < 4; i++) {
                            final int finalI = i;
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View view) {
                                    try{
                                        checkAnswer((Button) view, finalI);
                                    }catch (Exception e){

                                    }

                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
                    } else {
                        finish();
                        Toast.makeText(tournamentBuzzerPictureQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();
                       

                    }
                    //loadingDialog.dismiss();
                }
                break;
            case 2:
                if (num == 20) {
                    if (list.size() > 0) {
                        for (int i = 0; i < 4; i++) {
                            final int finalI = i;
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View view) {
                                    try{
                                        checkAnswer((Button) view, finalI);
                                    }catch (Exception e){

                                    }

                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
                    } else {
                        finish();
                        Toast.makeText(tournamentBuzzerPictureQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();
                       

                    }
                    //loadingDialog.dismiss();
                }
        }

    }

    public void correctWrongAnswered(String playerNumber) {
        correctWrongAnsweredBuzzerHolder s1 = new correctWrongAnsweredBuzzerHolder(correctAns, wrongAns);
        myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("correctWrongAnswered").child(playerNumber).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void quizIntent() {


        switch (playerNum) {
            case 1:
                correctWrongAnswered("Player1");
                break;
            case 2:
                correctWrongAnswered("Player2");
                break;
            case 3:
                correctWrongAnswered("Player3");
                break;
            case 4:
                correctWrongAnswered("Player4");
                break;
        }


        if (playerNum == 2 || playerNum == 3 || playerNum == 4) {
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(listner);
        }
        try{
            songActivity.songStop();
        }catch (Exception e){

        }

        mInterstitialAd.setAdListener(new AdListener(){
            public void onAdClosed(){
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

                if(countDownTimeralerDialog!=null){
                    countDownTimeralerDialog.cancel();
                }
                Intent scoreIntent = new Intent(tournamentBuzzerPictureQuiz.this, tournamentBuzzerScoreCard.class);
                scoreIntent.putExtra("score", myScore);
                scoreIntent.putExtra("numberOfPlayers", numberOfPlayers);
                scoreIntent.putExtra("myName", myName);
                scoreIntent.putExtra("myImageUrl", myImageUrl);
                scoreIntent.putExtra("hostUID", hostUid);
                scoreIntent.putExtra("hostName", hostName);
                scoreIntent.putExtra("hostImageUrl", hostImageUrl);
                scoreIntent.putExtra("player2Name", name2String);
                scoreIntent.putExtra("player3Name", name3String);
                scoreIntent.putExtra("player4Name", name4String);
                scoreIntent.putExtra("player2ImageUrl", image2Url);
                scoreIntent.putExtra("player3ImageUrl", image3Url);
                scoreIntent.putExtra("player4ImageUrl", image4Url);
                scoreIntent.putExtra("playerNumber", playerNum);
                scoreIntent.putExtra("roomCode", roomCode);
                scoreIntent.putExtra("questionNum", questionNum);
                scoreIntent.putExtra("timerNum", timerNum);
                scoreIntent.putExtra("correctMy", correctAns);
                scoreIntent.putExtra("wrongMy", wrongAns);
                scoreIntent.putExtra("numMode",numMode);
                for (int i = 0; i <= 20; i++) {
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                    }
                }
                startActivity(scoreIntent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               

            }

        });

        if(mInterstitialAd.isLoaded()){
            try{
                songActivity.songStop();
            }catch (Exception e){

            }
            mInterstitialAd.show();
            return;
        }

        try{
            songActivity.songStop();
        }catch (Exception e){

        }
        if(countDownTimeralerDialog!=null){
            countDownTimeralerDialog.cancel();
        }
        Intent scoreIntent = new Intent(tournamentBuzzerPictureQuiz.this, tournamentBuzzerScoreCard.class);
        scoreIntent.putExtra("score", myScore);
        scoreIntent.putExtra("numberOfPlayers", numberOfPlayers);
        scoreIntent.putExtra("myName", myName);
        scoreIntent.putExtra("myImageUrl", myImageUrl);
        scoreIntent.putExtra("hostUID", hostUid);
        scoreIntent.putExtra("hostName", hostName);
        scoreIntent.putExtra("hostImageUrl", hostImageUrl);
        scoreIntent.putExtra("player2Name", name2String);
        scoreIntent.putExtra("player3Name", name3String);
        scoreIntent.putExtra("player4Name", name4String);
        scoreIntent.putExtra("player2ImageUrl", image2Url);
        scoreIntent.putExtra("player3ImageUrl", image3Url);
        scoreIntent.putExtra("player4ImageUrl", image4Url);
        scoreIntent.putExtra("playerNumber", playerNum);
        scoreIntent.putExtra("roomCode", roomCode);
        scoreIntent.putExtra("questionNum", questionNum);
        scoreIntent.putExtra("timerNum", timerNum);
        scoreIntent.putExtra("correctMy", correctAns);
        scoreIntent.putExtra("wrongMy", wrongAns);
        scoreIntent.putExtra("numMode",numMode);
        for (int i = 0; i <= 20; i++) {
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
        }
        startActivity(scoreIntent);
        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();
       

    }


    private void playAnim(final View view, final int value, final String data) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationStart(Animator animator) {
                if (value == 0 && count < 4) {
                    String option = "";
                    if (count == 0) {

                        linkHolder=list.get(position).getQuestionPicture();
                        try{
                            Glide.with(getBaseContext())
                                    .load(linkHolder)
                                    .error(Glide.with(getBaseContext()).load(linkHolder).error(Glide.with(getBaseContext()).load(linkHolder).error(Glide.with(getBaseContext()).load(linkHolder))))
                                    .into(questionImage);
                        }catch (Exception e){

                        }
                        Animation imgAnim1 = AnimationUtils.loadAnimation(tournamentBuzzerPictureQuiz.this, R.anim.scaleincanim);
                        questionImage.setAnimation(imgAnim1);


                        option = list.get(position).getOption1();
                        option1.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(0).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    } else if (count == 1) {
                        option = list.get(position).getOption2();
                        option2.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(1).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    } else if (count == 2) {
                        option = list.get(position).getOption3();
                        option3.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(2).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    } else if (count == 3) {
                        option = list.get(position).getOption4();
                        option4.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(3).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    }
                    playAnim(linearLayout.getChildAt(count), 0, option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);

                        switch (questionNum) {
                            case 0:
                                scoreBoard.setText(" Question " + (position + 1) + "/10 ");
                                break;
                            case 1:
                                scoreBoard.setText(" Question " + (position + 1) + "/15 ");
                                break;
                            case 2:
                                scoreBoard.setText(" Question " + (position + 1) + "/20 ");
                                break;

                        }

                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1, data);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    public void buzzerMainManupulator(String playerNumber, int correctOrWrong, int questionNumber, int answerPositionSelected) {
        buzzerManuHolder s1 = new buzzerManuHolder(correctOrWrong, answerPositionSelected);

        myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child(playerNumber).child(String.valueOf(questionNumber)).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }


    public void dataReceivingForBuzzer(String playerName, int r, final LottieAnimationView playeranim) {

        for (int i = 1; i <= r; i++) {
            lisnerrat1=new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    mainHolder = snapshot.getValue(buzzerManuHolder.class);
                    try {

                        int correctOrWrongStatus = mainHolder.getCorrectOrWrong();
                        int positionNumberOfOption = mainHolder.getAnswerPositionSelected();
                        buzzerAnim(playeranim);

                        if (correctOrWrongStatus == 1) {
                            enableOption(false);

                            linearLayout.getChildAt(0).setAlpha(0.5f);
                            linearLayout.getChildAt(1).setAlpha(0.5f);
                            linearLayout.getChildAt(2).setAlpha(0.5f);
                            linearLayout.getChildAt(3).setAlpha(0.5f);

                            linearLayout.getChildAt(positionNumberOfOption - 1).setAlpha(1f);
                            linearLayout.getChildAt(positionNumberOfOption - 1).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));   //green color

                            switch (positionNumberOfOption) {
                                case 1:
                                    option1.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                                    option1.setShadowLayer(3, 1, 1, R.color.lightgreen);
                                    break;
                                case 2:
                                    option2.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                                    option2.setShadowLayer(3, 1, 1, R.color.lightgreen);
                                    break;
                                case 3:
                                    option3.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                                    option3.setShadowLayer(3, 1, 1, R.color.lightgreen);
                                    break;
                                case 4:
                                    option4.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                                    option4.setShadowLayer(3, 1, 1, R.color.lightgreen);
                                    break;
                            }

                        } else {
                            linearLayout.getChildAt(positionNumberOfOption - 1).setEnabled(false);
                            linearLayout.getChildAt(positionNumberOfOption - 1).setAlpha(0.5f);
                        }

                    } catch (Exception e) {

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child(playerName).child(String.valueOf(i)).addValueEventListener(lisnerrat1);
        }

    }

    public void buzzerAnim(LottieAnimationView anim) {
        anim.setVisibility(View.VISIBLE);
        anim.setAnimation(R.raw.buzzerbuttonanim);
        anim.playAnimation();
        anim.loop(true);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption, int optionSelectedPosition) {
        enableOption(false);


        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())) {

            final MediaPlayer musicNav;
            musicNav = MediaPlayer.create(tournamentBuzzerPictureQuiz.this, R.raw.correctmusic);
            musicNav.start();
            musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicNav.reset();
                    musicNav.release();
                }
            });

            isAttempt=1;
            correctAns++;



            switch (playerNum) {
                case 1:
                    buzzerAnim(player1anim);
                    break;
                case 2:
                    buzzerAnim(player2anim);
                    break;
                case 3:
                    buzzerAnim(player3anim);
                    break;
                case 4:
                    buzzerAnim(player4anim);
                    break;
            }


            //correct
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(position + 1)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    final int a = snapshot.getValue(Integer.class);


                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(position + 1)).setValue(a + 1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            switch (a) {
                                case 0:
                                    if (playerNum == 1) {
                                        myScore = myScore + 6;
                                        player1Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player1Score", myScore);
                                    } else if (playerNum == 2) {
                                        myScore = myScore + 6;
                                        player2Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player2Score", myScore);
                                    } else if (playerNum == 3) {
                                        myScore = myScore + 6;
                                        player3Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player3Score", myScore);
                                    } else if (playerNum == 4) {
                                        myScore = myScore + 6;
                                        player4Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player4Score", myScore);
                                    }
                                    break;
                                case 1:
                                    if (playerNum == 1) {
                                        myScore = myScore + 4;
                                        player1Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player1Score", myScore);
                                    } else if (playerNum == 2) {
                                        myScore = myScore + 4;
                                        player2Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player2Score", myScore);
                                    } else if (playerNum == 3) {
                                        myScore = myScore + 4;
                                        player3Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player3Score", myScore);
                                    } else if (playerNum == 4) {
                                        myScore = myScore + 4;
                                        player4Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player4Score", myScore);
                                    }
                                    break;
                                case 2:
                                    if (playerNum == 1) {
                                        myScore = myScore + 2;
                                        player1Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player1Score", myScore);
                                    } else if (playerNum == 2) {
                                        myScore = myScore + 2;
                                        player2Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player2Score", myScore);
                                    } else if (playerNum == 3) {
                                        myScore = myScore + 2;
                                        player3Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player3Score", myScore);
                                    } else if (playerNum == 4) {
                                        myScore = myScore + 2;
                                        player4Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player4Score", myScore);
                                    }
                                    break;
                                case 3:
                                    Toast.makeText(tournamentBuzzerPictureQuiz.this, "It Was The Last Option Left! No,Marks For The Last Option", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));   //green color
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            selectedOption.setShadowLayer(3, 1, 1, R.color.lightgreen);

            int positionSelected;

            if (list.get(position).getOption1().equals(list.get(position).getCorrectAnswer())) {
                positionSelected = 1;
            } else if (list.get(position).getOption2().equals(list.get(position).getCorrectAnswer())) {
                positionSelected = 2;
            } else if (list.get(position).getOption3().equals(list.get(position).getCorrectAnswer())) {
                positionSelected = 3;
            } else {
                positionSelected = 4;
            }

            switch (playerNum) {
                case 1:
                    buzzerMainManupulator("player1", 1, position + 1, positionSelected);
                    break;
                case 2:
                    buzzerMainManupulator("player2", 1, position + 1, positionSelected);
                    break;
                case 3:
                    buzzerMainManupulator("player3", 1, position + 1, positionSelected);
                    break;
                case 4:
                    buzzerMainManupulator("player4", 1, position + 1, positionSelected);
                    break;
            }


            switch (playerNum) {
                case 1:
                    myCorrectAnswerAnimManupulator1(anim1, anim2, anim3, anim4, anim5, anim6, anim7, anim8, anim9, anim10, animz1, animz2, animz3, animz4, animz5, animz6, animz7, animz8, animz9, animz10);
                    break;
                case 2:
                    myCorrectAnswerAnimManupulator1(anim11, anim12, anim13, anim14, anim15, anim16, anim17, anim18, anim19, anim20, animz11, animz12, animz13, animz14, animz15, animz16, animz17, animz18, animz19, animz20);
                    break;
                case 3:
                    myCorrectAnswerAnimManupulator1(anim21, anim22, anim23, anim24, anim25, anim26, anim27, anim28, anim29, anim30, animz21, animz22, animz23, animz24, animz25, animz26, animz27, animz28, animz29, animz30);
                    break;
                case 4:
                    myCorrectAnswerAnimManupulator1(anim31, anim32, anim33, anim34, anim35, anim36, anim37, anim38, anim39, anim40, animz31, animz32, animz33, animz34, animz35, animz36, animz37, animz38, animz39, animz40);
                    break;
            }


            switch (playerNum) {
                case 1:
                    answerStatus("player1Answer", 1);
                    break;
                case 2:
                    answerStatus("player2Answer", 1);
                    break;
                case 3:
                    answerStatus("player3Answer", 1);
                    break;
                case 4:
                    answerStatus("player4Answer", 1);
                    break;
            }

        } else {
            //incorrect

            final MediaPlayer musicNav;
            musicNav = MediaPlayer.create(tournamentBuzzerPictureQuiz.this, R.raw.wrongansfinal);
            musicNav.start();
            musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicNav.reset();
                    musicNav.release();
                }
            });


            isAttempt=1;
            wrongAns++;

            switch (playerNum) {
                case 1:
                    buzzerAnim(player1anim);
                    break;
                case 2:
                    buzzerAnim(player2anim);
                    break;
                case 3:
                    buzzerAnim(player3anim);
                    break;
                case 4:
                    buzzerAnim(player4anim);
                    break;
            }


            myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(position + 1)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    final int a = snapshot.getValue(Integer.class);


                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("BuzzerScoreKnower").child(String.valueOf(position + 1)).setValue(a + 1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            switch (a) {
                                case 0:
                                    if (playerNum == 1) {
                                        myScore = myScore - 2;
                                        player1Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player1Score", myScore);
                                    } else if (playerNum == 2) {
                                        myScore = myScore - 2;
                                        player2Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player2Score", myScore);
                                    } else if (playerNum == 3) {
                                        myScore = myScore - 2;
                                        player3Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player3Score", myScore);
                                    } else if (playerNum == 4) {
                                        myScore = myScore - 2;
                                        player4Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player4Score", myScore);
                                    }
                                    break;
                                case 1:
                                    if (playerNum == 1) {
                                        myScore = myScore - 2;
                                        player1Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player1Score", myScore);
                                    } else if (playerNum == 2) {
                                        myScore = myScore - 2;
                                        player2Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player2Score", myScore);
                                    } else if (playerNum == 3) {
                                        myScore = myScore - 2;
                                        player3Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player3Score", myScore);
                                    } else if (playerNum == 4) {
                                        myScore = myScore - 2;
                                        player4Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player4Score", myScore);
                                    }
                                    break;
                                case 2:
                                    if (playerNum == 1) {
                                        myScore = myScore - 2;
                                        player1Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player1Score", myScore);
                                    } else if (playerNum == 2) {
                                        myScore = myScore - 2;
                                        player2Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player2Score", myScore);
                                    } else if (playerNum == 3) {
                                        myScore = myScore - 2;
                                        player3Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player3Score", myScore);
                                    } else if (playerNum == 4) {
                                        myScore = myScore - 2;
                                        player4Score.setText("Score : " + myScore + " ");
                                        scoreUploader("player4Score", myScore);
                                    }
                                    break;
                                case 3:
                                    Toast.makeText(tournamentBuzzerPictureQuiz.this, "It Was The Last Option Left! No,Negative Marking.", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });


            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF8888")));     //red color
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            selectedOption.setShadowLayer(3, 1, 1, R.color.lightgreen);
            Button correctOption = (Button) linearLayout.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));     //green color
            correctOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            correctOption.setShadowLayer(3, 1, 1, R.color.lightred);


            switch (playerNum) {
                case 1:
                    buzzerMainManupulator("player1", 0, position + 1, optionSelectedPosition + 1);
                    break;
                case 2:
                    buzzerMainManupulator("player2", 0, position + 1, optionSelectedPosition + 1);
                    break;
                case 3:
                    buzzerMainManupulator("player3", 0, position + 1, optionSelectedPosition + 1);
                    break;
                case 4:
                    buzzerMainManupulator("player4", 0, position + 1, optionSelectedPosition + 1);
                    break;
            }


            switch (playerNum) {
                case 1:
                    myWrongAnswerAnimManupulator1(anim1, anim2, anim3, anim4, anim5, anim6, anim7, anim8, anim9, anim10, animz1, animz2, animz3, animz4, animz5, animz6, animz7, animz8, animz9, animz10);
                    break;
                case 2:
                    myWrongAnswerAnimManupulator1(anim11, anim12, anim13, anim14, anim15, anim16, anim17, anim18, anim19, anim20, animz11, animz12, animz13, animz14, animz15, animz16, animz17, animz18, animz19, animz20);
                    break;
                case 3:
                    myWrongAnswerAnimManupulator1(anim21, anim22, anim23, anim24, anim25, anim26, anim27, anim28, anim29, anim30, animz21, animz22, animz23, animz24, animz25, animz26, animz27, animz28, animz29, animz30);
                    break;
                case 4:
                    myWrongAnswerAnimManupulator1(anim31, anim32, anim33, anim34, anim35, anim36, anim37, anim38, anim39, anim40, animz31, animz32, animz33, animz34, animz35, animz36, animz37, animz38, animz39, animz40);
                    break;
            }


            switch (playerNum) {
                case 1:
                    answerStatus("player1Answer", 2);
                    break;
                case 2:
                    answerStatus("player2Answer", 2);
                    break;
                case 3:
                    answerStatus("player3Answer", 2);
                    break;
                case 4:
                    answerStatus("player4Answer", 2);
                    break;
            }
        }
    }

    public void scoreUploader(String playerNameScore, int score) {
        myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child(playerNameScore).setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void scoreReceiver(String playerNameScore, final TextView playerScoreTextView) {
        lisnerrat2=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int score = snapshot.getValue(Integer.class);
                    playerScoreTextView.setText("Score : " + score + " ");
                } catch (Exception e) {

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child(playerNameScore).addValueEventListener(lisnerrat2);
    }

    public void answerStatus(String playerAnswerStatusName, int i) {
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswerStatusName).child(String.valueOf(position + 1)).setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOption(boolean enable) {
        for (int i = 0; i < 4; i++) {
            linearLayout.getChildAt(i).setEnabled(enable);
            if (enable) {
                linearLayout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
            }
        }
    }

    public void myCorrectAnswerAnimManupulator1(LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10, LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20) {
        switch (position + 1) {
            case 1:
                a1.setAnimation(R.raw.tickanim);
                a1.playAnimation();
                a1.loop(false);
                break;
            case 2:
                a2.setAnimation(R.raw.tickanim);
                a2.playAnimation();
                a2.loop(false);
                break;
            case 3:
                a3.setAnimation(R.raw.tickanim);
                a3.playAnimation();
                a3.loop(false);
                break;
            case 4:
                a4.setAnimation(R.raw.tickanim);
                a4.playAnimation();
                a4.loop(false);
                break;
            case 5:
                a5.setAnimation(R.raw.tickanim);
                a5.playAnimation();
                a5.loop(false);
                break;
            case 6:
                a6.setAnimation(R.raw.tickanim);
                a6.playAnimation();
                a6.loop(false);
                break;
            case 7:
                a7.setAnimation(R.raw.tickanim);
                a7.playAnimation();
                a7.loop(false);
                break;
            case 8:
                a8.setAnimation(R.raw.tickanim);
                a8.playAnimation();
                a8.loop(false);
                break;
            case 9:
                a9.setAnimation(R.raw.tickanim);
                a9.playAnimation();
                a9.loop(false);
                break;
            case 10:
                a10.setAnimation(R.raw.tickanim);
                a10.playAnimation();
                a10.loop(false);
                break;
            case 11:
                a11.setAnimation(R.raw.tickanim);
                a11.playAnimation();
                a11.loop(false);
                break;
            case 12:
                a12.setAnimation(R.raw.tickanim);
                a12.playAnimation();
                a12.loop(false);
                break;
            case 13:
                a13.setAnimation(R.raw.tickanim);
                a13.playAnimation();
                a13.loop(false);
                break;
            case 14:
                a14.setAnimation(R.raw.tickanim);
                a14.playAnimation();
                a14.loop(false);
                break;
            case 15:
                a15.setAnimation(R.raw.tickanim);
                a15.playAnimation();
                a15.loop(false);
                break;
            case 16:
                a16.setAnimation(R.raw.tickanim);
                a16.playAnimation();
                a16.loop(false);
                break;
            case 17:
                a17.setAnimation(R.raw.tickanim);
                a17.playAnimation();
                a17.loop(false);
                break;
            case 18:
                a18.setAnimation(R.raw.tickanim);
                a18.playAnimation();
                a18.loop(false);
                break;
            case 19:
                a19.setAnimation(R.raw.tickanim);
                a19.playAnimation();
                a19.loop(false);
                break;
            case 20:
                a20.setAnimation(R.raw.tickanim);
                a20.playAnimation();
                a20.loop(false);
                break;
        }
    }

    public void myNotAnswerAnimManupulator1(LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10,LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20){
        switch (position+1){
            case 1:
                a1.setAnimation(R.raw.notattemptedanim);
                a1.setScaleX(0.85f);
                a1.setScaleY(0.85f);
                a1.playAnimation();
                a1.loop(false);break;
            case 2:
                a2.setAnimation(R.raw.notattemptedanim);
                a2.setScaleX(0.85f);
                a2.setScaleY(0.85f);
                a2.playAnimation();
                a2.loop(false);break;
            case 3:
                a3.setAnimation(R.raw.notattemptedanim);
                a3.setScaleX(0.85f);
                a3.setScaleY(0.85f);
                a3.playAnimation();
                a3.loop(false);break;
            case 4:
                a4.setAnimation(R.raw.notattemptedanim);
                a4.setScaleX(0.85f);
                a4.setScaleY(0.85f);
                a4.playAnimation();
                a4.loop(false);break;
            case 5:
                a5.setAnimation(R.raw.notattemptedanim);
                a5.setScaleX(0.85f);
                a5.setScaleY(0.85f);
                a5.playAnimation();
                a5.loop(false);break;
            case 6:
                a6.setAnimation(R.raw.notattemptedanim);
                a6.setScaleX(0.85f);
                a6.setScaleY(0.85f);
                a6.playAnimation();
                a6.loop(false);break;
            case 7:
                a7.setAnimation(R.raw.notattemptedanim);
                a7.setScaleX(0.85f);
                a7.setScaleY(0.85f);
                a7.playAnimation();
                a7.loop(false);break;
            case 8:
                a8.setAnimation(R.raw.notattemptedanim);
                a8.setScaleX(0.85f);
                a8.setScaleY(0.85f);
                a8.playAnimation();
                a8.loop(false);break;
            case 9:
                a9.setAnimation(R.raw.notattemptedanim);
                a9.setScaleX(0.85f);
                a9.setScaleY(0.85f);
                a9.playAnimation();
                a9.loop(false);break;
            case 10:
                a10.setAnimation(R.raw.notattemptedanim);
                a10.setScaleX(0.85f);
                a10.setScaleY(0.85f);
                a10.playAnimation();
                a10.loop(false);break;
            case 11:
                a11.setAnimation(R.raw.notattemptedanim);
                a11.setScaleX(0.85f);
                a11.setScaleY(0.85f);
                a11.playAnimation();
                a11.loop(false);break;
            case 12:
                a12.setAnimation(R.raw.notattemptedanim);
                a12.setScaleX(0.85f);
                a12.setScaleY(0.85f);
                a12.playAnimation();
                a12.loop(false);break;
            case 13:
                a13.setAnimation(R.raw.notattemptedanim);
                a13.setScaleX(0.85f);
                a13.setScaleY(0.85f);
                a13.playAnimation();
                a13.loop(false);break;
            case 14:
                a14.setAnimation(R.raw.notattemptedanim);
                a14.setScaleX(0.85f);
                a14.setScaleY(0.85f);
                a14.playAnimation();
                a14.loop(false);break;
            case 15:
                a15.setAnimation(R.raw.notattemptedanim);
                a15.setScaleX(0.85f);
                a15.setScaleY(0.85f);
                a15.playAnimation();
                a15.loop(false);break;
            case 16:
                a16.setAnimation(R.raw.notattemptedanim);
                a16.setScaleX(0.85f);
                a16.setScaleY(0.85f);
                a16.playAnimation();
                a16.loop(false);break;
            case 17:
                a17.setAnimation(R.raw.notattemptedanim);
                a17.setScaleX(0.85f);
                a17.setScaleY(0.85f);
                a17.playAnimation();
                a17.loop(false);break;
            case 18:
                a18.setAnimation(R.raw.notattemptedanim);
                a18.setScaleX(0.85f);
                a18.setScaleY(0.85f);
                a18.playAnimation();
                a18.loop(false);break;
            case 19:
                a19.setAnimation(R.raw.notattemptedanim);
                a19.setScaleX(0.85f);
                a19.setScaleY(0.85f);
                a19.playAnimation();
                a19.loop(false);break;
            case 20:
                a20.setAnimation(R.raw.notattemptedanim);
                a20.setScaleX(0.85f);
                a20.setScaleY(0.85f);
                a20.playAnimation();
                a20.loop(false);break;
        }
    }

    public void myWrongAnswerAnimManupulator1(LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10, LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20) {
        switch (position + 1) {
            case 1:
                a1.setAnimation(R.raw.wronganim);
                a1.playAnimation();
                a1.loop(false);
                break;
            case 2:
                a2.setAnimation(R.raw.wronganim);
                a2.playAnimation();
                a2.loop(false);
                break;
            case 3:
                a3.setAnimation(R.raw.wronganim);
                a3.playAnimation();
                a3.loop(false);
                break;
            case 4:
                a4.setAnimation(R.raw.wronganim);
                a4.playAnimation();
                a4.loop(false);
                break;
            case 5:
                a5.setAnimation(R.raw.wronganim);
                a5.playAnimation();
                a5.loop(false);
                break;
            case 6:
                a6.setAnimation(R.raw.wronganim);
                a6.playAnimation();
                a6.loop(false);
                break;
            case 7:
                a7.setAnimation(R.raw.wronganim);
                a7.playAnimation();
                a7.loop(false);
                break;
            case 8:
                a8.setAnimation(R.raw.wronganim);
                a8.playAnimation();
                a8.loop(false);
                break;
            case 9:
                a9.setAnimation(R.raw.wronganim);
                a9.playAnimation();
                a9.loop(false);
                break;
            case 10:
                a10.setAnimation(R.raw.wronganim);
                a10.playAnimation();
                a10.loop(false);
                break;
            case 11:
                a11.setAnimation(R.raw.wronganim);
                a11.playAnimation();
                a11.loop(false);
                break;
            case 12:
                a12.setAnimation(R.raw.wronganim);
                a12.playAnimation();
                a12.loop(false);
                break;
            case 13:
                a13.setAnimation(R.raw.wronganim);
                a13.playAnimation();
                a13.loop(false);
                break;
            case 14:
                a14.setAnimation(R.raw.wronganim);
                a14.playAnimation();
                a14.loop(false);
                break;
            case 15:
                a15.setAnimation(R.raw.wronganim);
                a15.playAnimation();
                a15.loop(false);
                break;
            case 16:
                a16.setAnimation(R.raw.wronganim);
                a16.playAnimation();
                a16.loop(false);
                break;
            case 17:
                a17.setAnimation(R.raw.wronganim);
                a17.playAnimation();
                a17.loop(false);
                break;
            case 18:
                a18.setAnimation(R.raw.wronganim);
                a18.playAnimation();
                a18.loop(false);
                break;
            case 19:
                a19.setAnimation(R.raw.wronganim);
                a19.playAnimation();
                a19.loop(false);
                break;
            case 20:
                a20.setAnimation(R.raw.wronganim);
                a20.playAnimation();
                a20.loop(false);
                break;
        }
    }


    public void dataSetterOfIntent() {


        switch (playerNum) {
            case 1:
                opponentName.setText(myName);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic);
                try {
                    opponentName2.setText(name2String);
                    Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic2);
                } catch (Exception e) {

                }

                try {
                    opponentName3.setText(name3String);
                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic3);
                } catch (Exception e) {

                }

                try {

                    opponentName4.setText(name4String);
                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic4);
                } catch (Exception e) {

                }


                break;
            case 2:
                opponentName.setText(hostName);
                Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic);
                opponentName2.setText(myName);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic2);

                try {
                    opponentName3.setText(name3String);
                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic3);
                } catch (Exception e) {

                }

                try {

                    opponentName4.setText(name4String);
                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic4);

                } catch (Exception e) {

                }


                break;
            case 3:
                opponentName.setText(hostName);
                Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic);

                opponentName2.setText(name2String);
                Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic2);

                opponentName3.setText(myName);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic3);

                try {

                    opponentName4.setText(name4String);
                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic4);
                } catch (Exception e) {

                }

                break;
            case 4:
                opponentName.setText(hostName);
                Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic);

                opponentName2.setText(name2String);
                Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic2);

                opponentName3.setText(name3String);
                Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic3);

                opponentName4.setText(myName);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic4);
                break;

        }
    }

    public void opponentAnswersReceivingPlayer1(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            lisnerrat3=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }else if(ans==2){
                            playerWrongAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }; myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(lisnerrat3);

            lisnerrat4=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }else if(ans==2){
                            playerWrongAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(lisnerrat4);

            lisnerrat5=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }else if(ans==2){
                            playerWrongAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(lisnerrat5);


        }

    }

    public void opponentAnswersReceivingPlayer2(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            lisnerrat6=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }else if(ans==2){
                            playerWrongAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }; myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(lisnerrat6);

            lisnerrat7=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }else if(ans==2){
                            playerWrongAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(lisnerrat7);

            lisnerrat8=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }else if(ans==2){
                            playerWrongAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(lisnerrat8);


        }

    }

    public void opponentAnswersReceivingPlayer3(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            lisnerrat9=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }else if(ans==2){
                            playerWrongAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(lisnerrat9);

            lisnerrat10=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }else if(ans==2){
                            playerWrongAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(lisnerrat10);

            lisnerrat11=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }else if(ans==2){
                            playerWrongAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(lisnerrat11);


        }

    }

    public void opponentAnswersReceivingPlayer4(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            lisnerrat12=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }else if(ans==2){
                            playerWrongAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(lisnerrat12);

            lisnerrat13=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }else if(ans==2){
                            playerWrongAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(lisnerrat13);

            lisnerrat14=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }else if(ans==2){
                            playerWrongAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }else if(ans==3){
                            playerNotAnsweredAnim(position,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);

                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(lisnerrat14);
        }

    }

    public void playerCorrectAnim(int counterNum, LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10, LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20) {
        switch (counterNum + 1) {
            case 1:
                a1.setAnimation(R.raw.tickanim);
                a1.playAnimation();
                a1.loop(false);
                break;
            case 2:
                a2.setAnimation(R.raw.tickanim);
                a2.playAnimation();
                a2.loop(false);
                break;
            case 3:
                a3.setAnimation(R.raw.tickanim);
                a3.playAnimation();
                a3.loop(false);
                break;
            case 4:
                a4.setAnimation(R.raw.tickanim);
                a4.playAnimation();
                a4.loop(false);
                break;
            case 5:
                a5.setAnimation(R.raw.tickanim);
                a5.playAnimation();
                a5.loop(false);
                break;
            case 6:
                a6.setAnimation(R.raw.tickanim);
                a6.playAnimation();
                a6.loop(false);
                break;
            case 7:
                a7.setAnimation(R.raw.tickanim);
                a7.playAnimation();
                a7.loop(false);
                break;
            case 8:
                a8.setAnimation(R.raw.tickanim);
                a8.playAnimation();
                a8.loop(false);
                break;
            case 9:
                a9.setAnimation(R.raw.tickanim);
                a9.playAnimation();
                a9.loop(false);
                break;
            case 10:
                a10.setAnimation(R.raw.tickanim);
                a10.playAnimation();
                a10.loop(false);
                break;
            case 11:
                a11.setAnimation(R.raw.tickanim);
                a11.playAnimation();
                a11.loop(false);
                break;
            case 12:
                a12.setAnimation(R.raw.tickanim);
                a12.playAnimation();
                a12.loop(false);
                break;
            case 13:
                a13.setAnimation(R.raw.tickanim);
                a13.playAnimation();
                a13.loop(false);
                break;
            case 14:
                a14.setAnimation(R.raw.tickanim);
                a14.playAnimation();
                a14.loop(false);
                break;
            case 15:
                a15.setAnimation(R.raw.tickanim);
                a15.playAnimation();
                a15.loop(false);
                break;
            case 16:
                a16.setAnimation(R.raw.tickanim);
                a16.playAnimation();
                a16.loop(false);
                break;
            case 17:
                a17.setAnimation(R.raw.tickanim);
                a17.playAnimation();
                a17.loop(false);
                break;
            case 18:
                a18.setAnimation(R.raw.tickanim);
                a18.playAnimation();
                a18.loop(false);
                break;
            case 19:
                a19.setAnimation(R.raw.tickanim);
                a19.playAnimation();
                a19.loop(false);
                break;
            case 20:
                a20.setAnimation(R.raw.tickanim);
                a20.playAnimation();
                a20.loop(false);
                break;
        }
    }

    public void playerNotAnsweredAnim(int counterNum,LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10,LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20){
        switch (counterNum){
            case 1:
                a1.setAnimation(R.raw.notattemptedanim);
                a1.setScaleX(0.85f);
                a1.setScaleY(0.85f);
                a1.playAnimation();
                a1.loop(false);break;
            case 2:
                a2.setAnimation(R.raw.notattemptedanim);
                a2.setScaleX(0.85f);
                a2.setScaleY(0.85f);
                a2.playAnimation();
                a2.loop(false);break;
            case 3:
                a3.setAnimation(R.raw.notattemptedanim);
                a3.setScaleX(0.85f);
                a3.setScaleY(0.85f);
                a3.playAnimation();
                a3.loop(false);break;
            case 4:
                a4.setAnimation(R.raw.notattemptedanim);
                a4.setScaleX(0.85f);
                a4.setScaleY(0.85f);
                a4.playAnimation();
                a4.loop(false);break;
            case 5:
                a5.setAnimation(R.raw.notattemptedanim);
                a5.setScaleX(0.85f);
                a5.setScaleY(0.85f);
                a5.playAnimation();
                a5.loop(false);break;
            case 6:
                a6.setAnimation(R.raw.notattemptedanim);
                a6.setScaleX(0.85f);
                a6.setScaleY(0.85f);
                a6.playAnimation();
                a6.loop(false);break;
            case 7:
                a7.setAnimation(R.raw.notattemptedanim);
                a7.setScaleX(0.85f);
                a7.setScaleY(0.85f);
                a7.playAnimation();
                a7.loop(false);break;
            case 8:
                a8.setAnimation(R.raw.notattemptedanim);
                a8.setScaleX(0.85f);
                a8.setScaleY(0.85f);
                a8.playAnimation();
                a8.loop(false);break;
            case 9:
                a9.setAnimation(R.raw.notattemptedanim);
                a9.setScaleX(0.85f);
                a9.setScaleY(0.85f);
                a9.playAnimation();
                a9.loop(false);break;
            case 10:
                a10.setAnimation(R.raw.notattemptedanim);
                a10.setScaleX(0.85f);
                a10.setScaleY(0.85f);
                a10.playAnimation();
                a10.loop(false);break;
            case 11:
                a11.setAnimation(R.raw.notattemptedanim);
                a11.setScaleX(0.85f);
                a11.setScaleY(0.85f);
                a11.playAnimation();
                a11.loop(false);break;
            case 12:
                a12.setAnimation(R.raw.notattemptedanim);
                a12.setScaleX(0.85f);
                a12.setScaleY(0.85f);
                a12.playAnimation();
                a12.loop(false);break;
            case 13:
                a13.setAnimation(R.raw.notattemptedanim);
                a13.setScaleX(0.85f);
                a13.setScaleY(0.85f);
                a13.playAnimation();
                a13.loop(false);break;
            case 14:
                a14.setAnimation(R.raw.notattemptedanim);
                a14.setScaleX(0.85f);
                a14.setScaleY(0.85f);
                a14.playAnimation();
                a14.loop(false);break;
            case 15:
                a15.setAnimation(R.raw.notattemptedanim);
                a15.setScaleX(0.85f);
                a15.setScaleY(0.85f);
                a15.playAnimation();
                a15.loop(false);break;
            case 16:
                a16.setAnimation(R.raw.notattemptedanim);
                a16.setScaleX(0.85f);
                a16.setScaleY(0.85f);
                a16.playAnimation();
                a16.loop(false);break;
            case 17:
                a17.setAnimation(R.raw.notattemptedanim);
                a17.setScaleX(0.85f);
                a17.setScaleY(0.85f);
                a17.playAnimation();
                a17.loop(false);break;
            case 18:
                a18.setAnimation(R.raw.notattemptedanim);
                a18.setScaleX(0.85f);
                a18.setScaleY(0.85f);
                a18.playAnimation();
                a18.loop(false);break;
            case 19:
                a19.setAnimation(R.raw.notattemptedanim);
                a19.setScaleX(0.85f);
                a19.setScaleY(0.85f);
                a19.playAnimation();
                a19.loop(false);break;
            case 20:
                a20.setAnimation(R.raw.notattemptedanim);
                a20.setScaleX(0.85f);
                a20.setScaleY(0.85f);
                a20.playAnimation();
                a20.loop(false);break;
        }
    }

    public void playerWrongAnim(int counterNum, LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10, LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20) {
        switch (counterNum + 1) {
            case 1:
                a1.setAnimation(R.raw.wronganim);
                a1.playAnimation();
                a1.loop(false);
                break;
            case 2:
                a2.setAnimation(R.raw.wronganim);
                a2.playAnimation();
                a2.loop(false);
                break;
            case 3:
                a3.setAnimation(R.raw.wronganim);
                a3.playAnimation();
                a3.loop(false);
                break;
            case 4:
                a4.setAnimation(R.raw.wronganim);
                a4.playAnimation();
                a4.loop(false);
                break;
            case 5:
                a5.setAnimation(R.raw.wronganim);
                a5.playAnimation();
                a5.loop(false);
                break;
            case 6:
                a6.setAnimation(R.raw.wronganim);
                a6.playAnimation();
                a6.loop(false);
                break;
            case 7:
                a7.setAnimation(R.raw.wronganim);
                a7.playAnimation();
                a7.loop(false);
                break;
            case 8:
                a8.setAnimation(R.raw.wronganim);
                a8.playAnimation();
                a8.loop(false);
                break;
            case 9:
                a9.setAnimation(R.raw.wronganim);
                a9.playAnimation();
                a9.loop(false);
                break;
            case 10:
                a10.setAnimation(R.raw.wronganim);
                a10.playAnimation();
                a10.loop(false);
                break;
            case 11:
                a11.setAnimation(R.raw.wronganim);
                a11.playAnimation();
                a11.loop(false);
                break;
            case 12:
                a12.setAnimation(R.raw.wronganim);
                a12.playAnimation();
                a12.loop(false);
                break;
            case 13:
                a13.setAnimation(R.raw.wronganim);
                a13.playAnimation();
                a13.loop(false);
                break;
            case 14:
                a14.setAnimation(R.raw.wronganim);
                a14.playAnimation();
                a14.loop(false);
                break;
            case 15:
                a15.setAnimation(R.raw.wronganim);
                a15.playAnimation();
                a15.loop(false);
                break;
            case 16:
                a16.setAnimation(R.raw.wronganim);
                a16.playAnimation();
                a16.loop(false);
                break;
            case 17:
                a17.setAnimation(R.raw.wronganim);
                a17.playAnimation();
                a17.loop(false);
                break;
            case 18:
                a18.setAnimation(R.raw.wronganim);
                a18.playAnimation();
                a18.loop(false);
                break;
            case 19:
                a19.setAnimation(R.raw.wronganim);
                a19.playAnimation();
                a19.loop(false);
                break;
            case 20:
                a20.setAnimation(R.raw.wronganim);
                a20.playAnimation();
                a20.loop(false);
                break;
        }
    }

    public void onBackPressed() {
        if (playerNum == 1 || playerNum == 2 || playerNum == 3 || playerNum == 4) {
            cancelDialogFunction();
        }

    }


    public void cancelDialogFunction() {
        AlertDialog.Builder builderRemove = new AlertDialog.Builder(tournamentBuzzerPictureQuiz.this, R.style.AlertDialogTheme);
        View viewRemove1 = LayoutInflater.from(tournamentBuzzerPictureQuiz.this).inflate(R.layout.quit_asker_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);
        builderRemove.setView(viewRemove1);
        builderRemove.setCancelable(false);
        Button yesButton = (Button) viewRemove1.findViewById(R.id.buttonYes);
        Button noButton = (Button) viewRemove1.findViewById(R.id.buttonNo);


        final AlertDialog alertDialog = builderRemove.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    songActivity.songStop();
                }catch (Exception e){

                }

                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerPictureQuiz.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                cancelLeaveFunction();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerPictureQuiz.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                alertDialog.dismiss();
            }
        });


    }

    public void cancelLeaveFunction(){
        if(playerNum==1){
            //   myRef.child("Lobby").child(String.valueOf(roomCode)).removeValue();
            //    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).removeValue();
            //    myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();

                    Intent intent47=new Intent(tournamentBuzzerPictureQuiz.this,mainMenuActivity.class);

                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimeralerDialog!=null){
                        countDownTimeralerDialog.cancel();
                    }

                    startActivity(intent47);

                    finish();
                   

                }
            });


        } else if(playerNum==2){

            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    Intent intent47=new Intent(tournamentBuzzerPictureQuiz.this,mainMenuActivity.class);

                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimeralerDialog!=null){
                        countDownTimeralerDialog.cancel();
                    }

                    startActivity(intent47);
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeValue();
                    finish();
                   

                }
            });

            //    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status");

        }else if(playerNum==3){

            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    Intent intent47=new Intent(tournamentBuzzerPictureQuiz.this,mainMenuActivity.class);

                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimeralerDialog!=null){
                        countDownTimeralerDialog.cancel();
                    }

                    startActivity(intent47);
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeValue();
                    finish();
                   

                }
            });
            //    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(listener2);

        }else if (playerNum==4){

            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {


                    Intent intent47=new Intent(tournamentBuzzerPictureQuiz.this,mainMenuActivity.class);

                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimeralerDialog!=null){
                        countDownTimeralerDialog.cancel();
                    }

                    startActivity(intent47);
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeValue();
                    finish();
                   

                }
            });
            //     myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(listener3);

        }

    }
    public void leaveDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(tournamentBuzzerPictureQuiz.this, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(tournamentBuzzerPictureQuiz.this).inflate(R.layout.removal_lobby_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        builder.setCancelable(false);


        TextView disText = (TextView) view.findViewById(R.id.textTitle);
        Button buttonYes = (Button) view.findViewById(R.id.buttonYes);
        disText.setText("Your Host " + hostName + " Has Either Disconnected Or Left The Game!");

        final AlertDialog alertDialog = builder.create();
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(listner);
        }catch (Exception e){

        }
        if(playerNum==2){

            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();



            //    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status");

        }else if(playerNum==3){

            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();

            //    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(listener2);

        }else if (playerNum==4){

            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();

            //     myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(listener3);

        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if(countDownTimeralerDialog!=null){
            countDownTimeralerDialog.cancel();
        }
        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerPictureQuiz.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });

                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                alertDialog.dismiss();
                Intent i=new Intent(tournamentBuzzerPictureQuiz.this,mainMenuActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               

            }
        });


    }

    public void opponentRemovedKnower() {
        listner = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    oppoStatus = snapshot.getValue(Integer.class);
                    if (oppoStatus == 0) {
                        leaveDialog();
                    }
                } catch (Exception e) {
                    leaveDialog();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").addValueEventListener(listner);
    }


}