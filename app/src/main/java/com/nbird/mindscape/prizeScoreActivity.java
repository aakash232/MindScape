package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
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

public class prizeScoreActivity extends AppCompatActivity {

    int score,lifelineSum,minutes,second;
    String minutestext,secondtext;
    TextView totalScoreNumber,totalTimeTaken,totalLifeLine,totalCorrectAns,highScoreTextview;
    Long milliholder;
    int millnum;
    long totalSum=0;
    CardView cardViewHome,cardViewChangeCategory;
    int category;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    ImageView imageView;
    int highScore=0;
    String imageurl;
    ShimmerFrameLayout mShimmerViewContainer;
    List<prizePlayerDataHolder> list;
    RecyclerView recyclerView;
    prizeLeaderBoardAdapter categoryAdapter;
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
    int sets,type;
    List<prizeRecyclerHolder> lstExam123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prize_score);

        totalScoreNumber=(TextView) findViewById(R.id.totalPoins);
        totalTimeTaken=(TextView) findViewById(R.id.totalTimeTaken);
        totalLifeLine=(TextView) findViewById(R.id.totalLifeLinesUsed);
        totalCorrectAns=(TextView) findViewById(R.id.totalScore);
        highScoreTextview=(TextView) findViewById(R.id.highScore);

        cardViewHome=(CardView) findViewById(R.id.cardView500);
        cardViewChangeCategory=(CardView) findViewById(R.id.cardView600);

        imageView=(ImageView) findViewById(R.id.propic);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        partypoper = (LottieAnimationView)findViewById(R.id.partypoper);
        party2 = (LottieAnimationView)findViewById(R.id.party2);



        partypoper.loop(false);

        category=getIntent().getIntExtra("category",1);
        lstExam123 = new ArrayList<>();


        score=getIntent().getIntExtra("score", 0);
        lifelineSum=getIntent().getIntExtra("lifeline",0);
        minutes=getIntent().getIntExtra("minutes",0);
        second=getIntent().getIntExtra("seconds",0);
        collider=getIntent().getIntExtra("Collider",0);
        minutestext=getIntent().getStringExtra("minutestext");
        secondtext=getIntent().getStringExtra("secondtext");
        imageurl=getIntent().getStringExtra("imageurl");
        milliholder=getIntent().getLongExtra("milliholder",0);

        sets=getIntent().getIntExtra("sets",1);
        type=getIntent().getIntExtra("type",1);



        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();
        categoryAdapter = new prizeLeaderBoardAdapter(list);
        recyclerView.setAdapter(categoryAdapter);



        Glide.with(getBaseContext()).load(imageurl).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(imageView);



        totalCorrectAns.setText(" Correct Answer : "+score+"/10 ");
        totalLifeLine.setText(" Life-Lines Used : "+lifelineSum+"/4 ");
        totalTimeTaken.setText(" Time Taken : "+minutestext+":"+secondtext+" ");



            totalSum= milliholder/100;
            for(int i=1;i<=lifelineSum;i++){
                totalSum=totalSum-1500;
            }
            for (int i=1;i<=score;i++){
                totalSum=totalSum+1000;
            }
            totalScoreNumber.setText(" Total Score : "+totalSum+" ");


        cardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(prizeScoreActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                prizeScoreActivity.super.onBackPressed();
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
            }
        });

        cardViewChangeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(prizeScoreActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                    AlertDialog.Builder builder=new AlertDialog.Builder(prizeScoreActivity.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(prizeScoreActivity.this).inflate(R.layout.prize_quiz_selector_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(true);

                    lstExam123.clear();

                    RecyclerView recyclerView=(RecyclerView) view1.findViewById(R.id.recyclerview);


                final prizeRecyclerAdapter myAdapter=new prizeRecyclerAdapter(prizeScoreActivity.this,lstExam123);
                    recyclerView.setLayoutManager(new GridLayoutManager(prizeScoreActivity.this,2));
                    recyclerView.setAdapter(myAdapter);




                    myRef.child("PrizeMode").child("Packets").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dataSnapshot1 : snapshot.getChildren() ){
                                lstExam123.add(dataSnapshot1.getValue(prizeRecyclerHolder.class));
                                myAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();
                }

        });

        highestScoreSet();
    }


    public void highestScoreSet(){

        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("highestScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    highScore =  snapshot.getValue(Integer.class);
                    highScoreTextview.setText(" Your Highest Score : "+String.valueOf(highScore)+" ");

                    if(highScore<totalSum){
                        highScoreTextview.setText(" Your Highest Score : "+String.valueOf(totalSum)+" ");
                        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("highestScore").setValue(totalSum).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("attempts").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        try {
                                            int s=snapshot.getValue(Integer.class);
                                            s++;
                                            myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("attempts").setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    dataSetter();
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
                        });
                    }
                }catch(Exception e){
                    highScoreTextview.setText(" Your Highest Score : "+String.valueOf(totalSum)+" ");
                    myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).child(mAuth.getCurrentUser().getUid()).child("highestScore").setValue(totalSum).addOnCompleteListener(new OnCompleteListener<Void>() {
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

    public void dataSetter(){
        myRef.child("PrizeModePlayerData").child(String.valueOf(sets)).orderByChild("highestScore").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(prizePlayerDataHolder.class));
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


}