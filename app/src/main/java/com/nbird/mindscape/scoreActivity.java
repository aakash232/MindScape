package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    LottieAnimationView partypoper;
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

        partypoper.loop(false);

        category=getIntent().getIntExtra("category",1);



        score=getIntent().getIntExtra("score", 0);
        lifelineSum=getIntent().getIntExtra("lifeline",0);
        minutes=getIntent().getIntExtra("minutes",0);
        second=getIntent().getIntExtra("seconds",0);
        minutestext=getIntent().getStringExtra("minutestext");
        secondtext=getIntent().getStringExtra("secondtext");
        imageurl=getIntent().getStringExtra("imageurl");
        milliholder=getIntent().getLongExtra("milliholder",0);




        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        list=new ArrayList<>();

        categoryAdapter = new recyclerViewLeaderBoardAdapter(list);
        recyclerView.setAdapter(categoryAdapter);




        Glide.with(getBaseContext()).load(imageurl).into(imageView);

        totalCorrectAns.setText(" Correct Answer : "+score+"/10 ");
        totalLifeLine.setText(" Life-Lines Used : "+lifelineSum+"/4 ");
        totalTimeTaken.setText(" Time Taken : "+minutestext+":"+secondtext+" ");

        totalSum= milliholder/100;
        totalSum=totalSum+6000;
        for(int i=1;i<=lifelineSum;i++){
            totalSum=totalSum-1500;
        }
        for (int i=1;i<=score;i++){
            totalSum=totalSum+1000;
        }
        totalScoreNumber.setText(" Total Score : "+totalSum+" ");

        buttonFunction();
        highestScoreSet();
        highScorePushInLeaderBoard();


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


                                                        leaderBoardHolder s1 = new leaderBoardHolder(username123,score123,timeHolder,correct,wrongfire,imageurl);

                                                        myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                 dataSetter();

                                                            }
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
                                        username123=snapshot.getValue(String.class);
                                        wrong=10-score;
                                        int manu= (int) ((600000-milliholder)/1000);
                                        int sums=(int)totalSum;
                                        leaderBoardHolder s1 = new leaderBoardHolder(username123,sums,manu,score,wrong,imageurl);

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
        cardViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(scoreActivity.this,mainMenuActivity.class);
                startActivity(intent);
            }
        });

        cardViewChangeCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(scoreActivity.this,singleModeListView.class);
                startActivity(intent);
            }
        });

        cardViewRematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(scoreActivity.this,quizActivity.class);
                intent.putExtra("category",category);
                startActivity(intent);
            }
        });
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

}