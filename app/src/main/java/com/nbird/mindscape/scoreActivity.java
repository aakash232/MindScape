package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class scoreActivity extends AppCompatActivity {

    int score,lifelineSum,minutes,second;
    String minutestext,secondtext;
    TextView totalScoreNumber,totalTimeTaken,totalLifeLine,totalCorrectAns,highScoreTextview;
    Long milliholder;
    int millnum;
    long totalSum=0;
    CardView cardViewHome,cardViewRematch,cardViewChangeCategory;
    int category;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    ImageView imageView;
    int highScore=0;
    String imageurl;
    ShimmerFrameLayout mShimmerViewContainer;
    List<leaderBoardHolder> list;
    RecyclerView recyclerView;
    recyclerViewLeaderBoardAdapter categoryAdapter;
    String username123;
    int score123;
    int rank123;
    int totalTime123;
    String correctByWrong123;
    String imageUrl123;
    int timeHolder=0;
    int correct;
    int wrongfire=0,wrong=0;
    LottieAnimationView partypoper,party2;
    ImageView levelImage;
    TextView levelText;
    int sumationOfScore=0;
    ImageView image1,image2;
    int collider;
    TextView disDialog;
    int timeInt;
    barGroupHolder man;
    int mainfinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        totalScoreNumber=(TextView) findViewById(R.id.totalPoins);
        totalTimeTaken=(TextView) findViewById(R.id.totalTimeTaken);
        totalLifeLine=(TextView) findViewById(R.id.totalLifeLinesUsed);
        totalCorrectAns=(TextView) findViewById(R.id.totalScore);
        highScoreTextview=(TextView) findViewById(R.id.highScore);

        cardViewHome=(CardView) findViewById(R.id.cardView500);
        cardViewChangeCategory=(CardView) findViewById(R.id.cardView600);
        cardViewRematch=(CardView) findViewById(R.id.cardView700);
        imageView=(ImageView) findViewById(R.id.propic);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        partypoper = (LottieAnimationView)findViewById(R.id.partypoper);
        party2 = (LottieAnimationView)findViewById(R.id.party2);
        levelImage=(ImageView) findViewById(R.id.levelImage);
        levelText=(TextView) findViewById(R.id.levelText);

        partypoper.loop(false);
        MediaPlayer musicNav;
        musicNav = MediaPlayer.create(scoreActivity.this, R.raw.partypoppermusic);
        musicNav.start();


        category=getIntent().getIntExtra("category",1);



        score=getIntent().getIntExtra("score", 0);
        lifelineSum=getIntent().getIntExtra("lifeline",0);
        minutes=getIntent().getIntExtra("minutes",0);
        second=getIntent().getIntExtra("seconds",0);
        collider=getIntent().getIntExtra("Collider",0);
        minutestext=getIntent().getStringExtra("minutestext");
        secondtext=getIntent().getStringExtra("secondtext");
        imageurl=getIntent().getStringExtra("imageurl");
        milliholder=getIntent().getLongExtra("milliholder",0);
        mainfinder=getIntent().getIntExtra("mainfinder",0);



        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("LineChartGradient").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    long i=snapshot.getValue(Integer.class);
                    long k=((1000*60*10)-milliholder)/1000;
                    i=i+k;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("LineChartGradient").child(date).setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    long k=((1000*60*10)-milliholder)/1000;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("LineChartGradient").child(date).setValue(k).addOnCompleteListener(new OnCompleteListener<Void>() {
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


        if(collider==100||collider==200||collider==300){
            cardViewChangeCategory.setAlpha(0);
            cardViewChangeCategory.setEnabled(false);
            cardViewChangeCategory.setClickable(false);
        }else{

            final int mm= (int) ((1000*60*10)-milliholder);

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("pieChart").child(String.valueOf(category)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        int y=snapshot.getValue(Integer.class);
                        y=y+mm;
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("pieChart").child(String.valueOf(category)).setValue(y).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }catch (Exception e){
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("pieChart").child(String.valueOf(category)).setValue(mm).addOnCompleteListener(new OnCompleteListener<Void>() {
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


        }

        numberOfTimesPlayed();

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();

        categoryAdapter = new recyclerViewLeaderBoardAdapter(list);
        recyclerView.setAdapter(categoryAdapter);



        Glide.with(getBaseContext()).load(imageurl).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(imageView);



        totalCorrectAns.setText(" Correct Answer : "+score+"/10 ");
        totalLifeLine.setText(" Life-Lines Used : "+lifelineSum+"/4 ");
        totalTimeTaken.setText(" Time Taken : "+minutestext+":"+secondtext+" ");


        if(collider==100){
            totalSum= milliholder/150;
            for(int i=1;i<=lifelineSum;i++){
                totalSum=totalSum-1500;
            }
            for (int i=1;i<=score;i++){
                totalSum=totalSum+1000;
            }
            totalScoreNumber.setText(" Total Score : "+totalSum+" ");
        }else{
            totalSum= milliholder/150;
            for(int i=1;i<=lifelineSum;i++){
                totalSum=totalSum-1500;
            }
            for (int i=1;i<=score;i++){
                totalSum=totalSum+1000;
            }
            totalScoreNumber.setText(" Total Score : "+totalSum+" ");

        }




        buttonFunction();
        highestScoreSet();
        highScorePushInLeaderBoard();

        final int f=10-score;
        long k=(60*1000*10)-milliholder;
        quizHistoryData s5 = new quizHistoryData((int) totalSum, k,score,f);
        String key = database.getReference().child("User").child(mAuth.getCurrentUser().getUid()).child("SinglePlayerQuizHistory").push().getKey();
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("SinglePlayerQuizHistory").child(key).setValue(s5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        final String dayOfTheWeek = sdf.format(d);

        man=new barGroupHolder();
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("barGroupData").child(dayOfTheWeek).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    man=snapshot.getValue(barGroupHolder.class);
                    int cA=man.getCorrect()+score;
                    int wA=man.getWrong()+f;
                    barGroupHolder g1=new barGroupHolder(cA,wA);
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("barGroupData").child(dayOfTheWeek).setValue(g1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }catch (Exception e){
                    barGroupHolder g1=new barGroupHolder(score,f);
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("barGroupData").child(dayOfTheWeek).setValue(g1).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    }

    public void highScorePushInLeaderBoard(){

        myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    username123 = (String) snapshot.getValue();

                    myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            try{
                                score123 =  snapshot.getValue(Integer.class);
                                if(score123<totalSum){
                                    score123= (int) totalSum;
                                }



                                myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("totalTime").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        timeHolder= snapshot.getValue(Integer.class);
                                        double manu= (600000-milliholder)/1000;
                                        timeHolder= (int) (timeHolder+manu);



                                        myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("correct").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                correct= snapshot.getValue(Integer.class);

                                                wrong=10-score;

                                                correct=correct+score;

                                                myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("wrong").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        wrongfire=snapshot.getValue(Integer.class);
                                                        wrongfire=wrongfire+wrong;


                                                        myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                sumationOfScore=snapshot.getValue(Integer.class);
                                                                sumationOfScore= (int) (sumationOfScore+totalSum);
                                                                levelManupulation();

                                                                leaderBoardHolder s1 = new leaderBoardHolder(username123,score123,timeHolder,correct,wrongfire,imageurl,sumationOfScore);

                                                                myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        dataSetter();

                                                                    }
                                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                                    }
                                                                });


                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                            }
                                                        });





                                                    }


                                                    public void onCancelled(@NonNull DatabaseError error) {

                                                    }
                                                });





                                            }
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }catch(Exception e){
                                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                                        sumationOfScore= (int) totalSum;
                                        levelImage.setBackgroundResource(R.drawable.blackiron);
                                        levelText.setText(" Lv. 1 ");
                                        username123=snapshot.getValue(String.class);
                                        wrong=10-score;
                                        int manu= (int) ((600000-milliholder)/1000);
                                        int sums=(int)totalSum;
                                        leaderBoardHolder s1 = new leaderBoardHolder(username123,sums,manu,score,wrong,imageurl,sumationOfScore);

                                        myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                 dataSetter();

                                            }
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }




                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }catch (Exception e){





                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }






    public void highestScoreSet(){

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("Score").child("singlePlayerHS").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        highScore =  snapshot.getValue(Integer.class);
                        highScoreTextview.setText(" Your Highest Score : "+String.valueOf(highScore)+" ");

                        if(highScore<totalSum){
                            highScoreTextview.setText(" Your Highest Score : "+String.valueOf(totalSum)+" ");
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("Score").child("singlePlayerHS").setValue(totalSum).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });
                        }
                    }catch(Exception e){
                        highScoreTextview.setText(" Your Highest Score : "+String.valueOf(totalSum)+" ");
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("Score").child("singlePlayerHS").setValue(totalSum).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    }



    public void buttonFunction(){

        if(collider==100){
            cardViewRematch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,activity_picture_singlePlayer.class);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });
            cardViewHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    scoreActivity.super.onBackPressed();
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });
        }else if(collider==200){
            cardViewRematch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,AudioQuizSinglePlayer.class);
                    intent.putExtra("mainfinder",1);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });

            cardViewHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,mainMenuActivity.class);
                    intent.putExtra("mainfinder",1);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });
        }else if(collider==300){
            cardViewRematch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,VideoQuizSinglePlayer.class);
                    intent.putExtra("mainfinder",1);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });

            cardViewHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,mainMenuActivity.class);
                    intent.putExtra("mainfinder",1);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });
        }else{
            cardViewRematch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,quizActivity.class);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });

            cardViewHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    scoreActivity.super.onBackPressed();
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });

            cardViewChangeCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(scoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    Intent intent=new Intent(scoreActivity.this,singleModeListView.class);
                    startActivity(intent);overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            });
        }




    }

    public void dataSetter(){
        myRef.child("leaderBoard").child("singlePlayer").orderByChild("score").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(leaderBoardHolder.class));
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                }

                categoryAdapter.notifyDataSetChanged();


                //     highScorePushInLeaderBoard();




                // Toast.makeText(scoreActivity.this, "The " + dataSnapshot.getKey() + " score is " + dataSnapshot.getValue(), Toast.LENGTH_LONG).show();
                //  System.out.println("The " + dataSnapshot.getKey() + " score is " + dataSnapshot.getValue());
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

            // ...
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }


    public void levelManupulation(){

        if(sumationOfScore<100000){
            if(sumationOfScore<50000){
                levelText.setText(" Lv. 1 ");
            }else{
                levelText.setText(" Lv. 2 ");
            }
        }else{
            int holder;
            holder=sumationOfScore/50000;
            levelText.setText(" Lv. "+holder+" ");
        }
        SharedPreferences batch = getApplicationContext().getSharedPreferences("Batch3", 0);
        SharedPreferences.Editor editor = batch.edit();
        int s1;





        if(sumationOfScore<50000){

            levelImage.setBackgroundResource(R.drawable.blackiron);
        }else if(sumationOfScore<200000){
            s1 = batch.getInt("bronze", 0);

            if(s1==0){
                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To bronze");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.bronze);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("bronze", 1);
                editor.apply();
            }




            levelImage.setBackgroundResource(R.drawable.bronze);
        }else if(sumationOfScore<800000){

            s1 = batch.getInt("silver", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To silver");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.silver);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("silver", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.silver);
        }else if(sumationOfScore<1800000){

            s1 = batch.getInt("gold", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To Gold");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.gold);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("gold", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.gold);
        }else if(sumationOfScore<3000000){

            s1 = batch.getInt("platinum", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To Platinum");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.platinum);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("platinum", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.platinum);
        }else if(sumationOfScore<4000000){

            s1 = batch.getInt("diamond", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To Diamond");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.diamond);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("diamond", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.diamond);
        }else if(sumationOfScore<8000000){

            s1 = batch.getInt("amethyst", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To Amethyst");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.amethyst);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("amethyst", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.amethyst);
        }else if(sumationOfScore<12000000){

            s1 = batch.getInt("master", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To Master");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.master);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("master", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.master);
        }else{

            s1 = batch.getInt("king", 0);
            if(s1==0){

                dialogFunction();
                disDialog.setText("Congratulations "+username123+".You Are Upgraded To King");      //   Aakash Changes Are to be done here
                image2.setBackgroundResource(R.drawable.king);
                Animation imgAnim1 = AnimationUtils.loadAnimation(this, R.anim.batchanim);
                image2.setAnimation(imgAnim1);
                editor.putInt("king", 1);
                editor.apply();
            }
            levelImage.setBackgroundResource(R.drawable.king);
        }
    }

    public void dialogFunction(){
        AlertDialog.Builder builder=new AlertDialog.Builder(scoreActivity.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(scoreActivity.this).inflate(R.layout.batch_display_dialog_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        disDialog=((TextView) view1.findViewById(R.id.textTitle));
        ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
        image1=((ImageView) view1.findViewById(R.id.imageIcon));
        image2=((ImageView) view1.findViewById(R.id.imageIcon2));
        final AlertDialog alertDialog=builder.create();
        party2.loop(false);
        party2.playAnimation();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
        view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }

    public void numberOfTimesPlayed(){

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfSingleModePlayed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int i=snapshot.getValue(Integer.class);
                    i++;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfSingleModePlayed").setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfSingleModePlayed").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
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



        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfTimesPlayed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int i=snapshot.getValue(Integer.class);
                    i++;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfTimesPlayed").setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfTimesPlayed").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
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
    }

}