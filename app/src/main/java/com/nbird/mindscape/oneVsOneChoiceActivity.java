package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class oneVsOneChoiceActivity extends AppCompatActivity {
    CardView online,local,share,cardView11;
    ImageView userImage, batch,batch99;
    TextView userName123, level, secondPlayername,levelText99;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private List<mainMenuFactsHolder> list;
    private List<onevsoneOnlinePlayerList> list123;
    public ViewPager slideViewPager;
    String userName, proPicUrl;
    private TextView[] mDots;
    int num = 0;
    int codeInteger;
    LinearLayout linearLayout300;
    TextView shareText;
    private ShimmerFrameLayout mShimmerViewContainer, shimmer1,shimmer40,shimmer30;
    private onevsoneonlineAdapter sliderAdapter;
    ActionBarDrawerToggle mToggle;
    private LinearLayout dotLayout;
    private int currentPage;
    AlertDialog.Builder builder,builder9;
    View view1,view9;
    int i = 0;
    AlertDialog alertDialog123;
    int roomCode;
    CardView cardView5;
    Button cancelButton;
    int matcherStatus, timeHolder, correct, wrong, wrongfire, sumationOfScore=0;
    String opponentUID;
    int status = 1;
    int jocker;
    int score123;
    CountDownTimer countDownTimer, countDownTimer123;
    String opponentimageUrl, opponentUsername;
    ImageView secondplayerimg;
    String secondMan;
    int leader = 0;
    List<Integer> arrlist;
    int globalInt;
    String username;
    Button joinButtonFinal;
    TextView myLevel, oppoLevel, oppoRatio,oppoRatio1,oppoAccu1,highestScore1,totalTime1;
    ImageView myBatch, oppoBatch;
    int sumofq;
    Button joinButton,createButton;
    TextView totalTime, oppoAccu;
    TextView highestScore;
    LinearLayout linearLayout100,batchLinearLayout,batchdisplay1;
    TextInputEditText roomCodeEditText;
    String roomCodeString;
    androidx.appcompat.widget.Toolbar toolbar;
    int isHost=0;
    int setNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_vs_one_choice);

        try{
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineCorrectAns").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineCurrentScore").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineLifeLineUsed").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineTimeTaken").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child("OnCompleteHolder").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").child("accept").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").removeValue();
            myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineOpponentUID").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").removeValue();
            myRef.child("oneVsoneLocalPlayers").child(mAuth.getCurrentUser().getUid()).removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("myStatus").removeValue();
        }catch (Exception e){

        }

        online = (CardView) findViewById(R.id.online);
        local = (CardView) findViewById(R.id.local);
        arrlist = new ArrayList<>(13);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Mode Activity");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        builder = new AlertDialog.Builder(oneVsOneChoiceActivity.this, R.style.AlertDialogTheme);
        view1 = LayoutInflater.from(oneVsOneChoiceActivity.this).inflate(R.layout.onevsone_online_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);

        builder9 = new AlertDialog.Builder(oneVsOneChoiceActivity.this, R.style.AlertDialogTheme);
        view9 = LayoutInflater.from(oneVsOneChoiceActivity.this).inflate(R.layout.onevsone_local_main_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);




        mShimmerViewContainer = (ShimmerFrameLayout) view1.findViewById(R.id.shimmer_view_container);
        shimmer1 = (ShimmerFrameLayout) view1.findViewById(R.id.shimmer1);
        shimmer40=(ShimmerFrameLayout)view9.findViewById(R.id.shimmer40);
        shimmer30 = (ShimmerFrameLayout) view9.findViewById(R.id.shimmer30);

        shareText=(TextView) view9.findViewById(R.id.shareText);

        oppoRatio = (TextView) view1.findViewById(R.id.oppoRatio);
        oppoAccu = (TextView) view1.findViewById(R.id.oppoAccu);
        highestScore = (TextView) view1.findViewById(R.id.highestScore);
        totalTime = (TextView) view1.findViewById(R.id.totalTime);

        oppoRatio1 = (TextView) view9.findViewById(R.id.oppoRatio);
        oppoAccu1 = (TextView) view9.findViewById(R.id.oppoAccu);
        highestScore1 = (TextView) view9.findViewById(R.id.highestScore);
        totalTime1 = (TextView) view9.findViewById(R.id.totalTime);

        linearLayout100=(LinearLayout) view1.findViewById(R.id.linearLayout100);
        linearLayout300=(LinearLayout) view9.findViewById(R.id.linearLayout300);
        batchLinearLayout=(LinearLayout) view1.findViewById(R.id.batchdisplay);
        batchdisplay1=(LinearLayout) view1.findViewById(R.id.batchdisplay1);

        batch99=(ImageView) view9.findViewById(R.id.batch99);
        levelText99=(TextView) view9.findViewById(R.id.levelText99);

        cardView5=(CardView) view1.findViewById(R.id.cardView5);
        cardView11=(CardView) view9.findViewById(R.id.cardView11);
        list = new ArrayList<>();
        list123 = new ArrayList<>();


        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                AlertDialog.Builder builder2 = new AlertDialog.Builder(oneVsOneChoiceActivity.this, R.style.AlertDialogTheme);
                View view2 = LayoutInflater.from(oneVsOneChoiceActivity.this).inflate(R.layout.onevsone_local_mode_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);
                builder2.setView(view2);
                builder2.setCancelable(true);
                joinButton = ((Button) view2.findViewById(R.id.joinButton));
                createButton = ((Button) view2.findViewById(R.id.createButton));



                final AlertDialog alertDialog = builder2.create();
                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();


                joinButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                        musicNav.start();
                        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                musicNav.reset();
                                musicNav.release();
                            }
                        });
                        alertDialog.dismiss();
                        joinFunction();

                    }
                });

                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                        musicNav.start();
                        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                musicNav.reset();
                                musicNav.release();
                            }
                        });
                        alertDialog.dismiss();
                        roomCodeGenerator();
                    }
                });
            }
        });


        buttonFunction();
        userData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
        shimmer1.startShimmerAnimation();
        shimmer30.startShimmerAnimation();
        shimmer40.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        shimmer1.stopShimmerAnimation();
        shimmer30.stopShimmerAnimation();
        shimmer40.stopShimmerAnimation();
        super.onPause();
    }


    public void dataForHorizontalSlide() {

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int setRandomNumber;
        final int categoryRandomNumber = rand.nextInt(7)+1;
        if(categoryRandomNumber<=5||categoryRandomNumber==7){
            setRandomNumber = rand.nextInt(49)+1;
        }else{
            setRandomNumber = rand.nextInt(199)+1;
        }
        myRef.child("Facts").child(String.valueOf(categoryRandomNumber)).orderByChild("set").equalTo(setRandomNumber).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(mainMenuFactsHolder.class));
                    num++;
                }

                if (num == 3) {
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    shimmer30.stopShimmerAnimation();
                    shimmer30.setVisibility(View.GONE);
                    AdapterManupulation();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(oneVsOneChoiceActivity.this, "Facts Data Can't be Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void AdapterManupulation() {
        sliderAdapter = new onevsoneonlineAdapter(oneVsOneChoiceActivity.this, list);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListner);
        sliderAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        dotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.lightgrey));
            dotLayout.addView(mDots[i]);

        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void userData() {
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userName = snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                proPicUrl = snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void buttonFunction() {
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                findingOponentFunction();
                builder.setView(view1);
                builder.setCancelable(false);
                cancelButton = ((Button) view1.findViewById(R.id.cancelButton));
                userImage = (ImageView) view1.findViewById(R.id.exam_img_id);
                userName123 = (TextView) view1.findViewById(R.id.userName123);
                batch = (ImageView) view1.findViewById(R.id.batch4);
                level = (TextView) view1.findViewById(R.id.levelText1234);
                slideViewPager = (ViewPager) view1.findViewById(R.id.slideViewPager);
                dotLayout = (LinearLayout) view1.findViewById(R.id.dotLayout);
                secondplayerimg = (ImageView) view1.findViewById(R.id.secondplayerimg);
                secondPlayername = (TextView) view1.findViewById(R.id.secondPlayername);
                myLevel = (TextView) view1.findViewById(R.id.levelText1234);
                myBatch = (ImageView) view1.findViewById(R.id.batch4);
                oppoLevel = (TextView) view1.findViewById(R.id.levelText12341);
                oppoBatch = (ImageView) view1.findViewById(R.id.batch41);
                dataSetterForOneVsOne();
                myDataSeeter();

                for (int i = 1; i <= 3; i++) {
                    dataForHorizontalSlide();
                }

                final AlertDialog alertDialog = builder.create();
                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();


                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();

                        } catch (Exception e) {

                        }
                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                        musicNav.start();
                        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                musicNav.reset();
                                musicNav.release();
                            }
                        });
                        alertDialog.dismiss();
                        oneVsOneChoiceActivity.super.onBackPressed();
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        finish();
                    }
                });


            }
        });
    }

    public void dataSetterForOneVsOne() {
        userName123.setText(userName);
        Glide.with(getBaseContext())
                .load(proPicUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(userImage);
    }


    public void matchMakingFunction() {
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("personal").child("onevsoneOnlineFinder").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void findingOponentFunction() {

        myRef.child("oneVsoneOnlinePlayers").orderByChild("status").equalTo(1).limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list123.add(dataSnapshot1.getValue(onevsoneOnlinePlayerList.class));
                }
                try {
                    opponentUID = list123.get(0).getUID();

                    leader = 1;
                    myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();
                    myRef.child("oneVsoneOnlinePlayers").child(opponentUID).removeValue();

                    randomNumberGeneratorFunction(opponentUID);



                    myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").setValue(mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            myRef.child("User").child(opponentUID).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    opponentimageUrl = snapshot.getValue(String.class);

                                    myRef.child("User").child(opponentUID).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            opponentUsername = snapshot.getValue(String.class);
                                            cardView5.setAlpha(1);
                                            linearLayout100.setAlpha(1);
                                            batchLinearLayout.setAlpha(1);
                                            shimmer1.stopShimmerAnimation();
                                            shimmer1.setVisibility(View.GONE);
                                            Glide.with(getBaseContext())
                                                    .load(opponentimageUrl)
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                    .into(secondplayerimg);

                                            opponentDataGetterFunction();

                                            secondPlayername.setText(opponentUsername);


                                            cancelButton.setEnabled(false);
                                            cancelButton.setAlpha(1);
                                            countDownTimerFun();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                    });

                } catch (Exception e) {
                    onevsoneOnlinePlayerList s1 = new onevsoneOnlinePlayerList(mAuth.getCurrentUser().getUid(), 1);
                    myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineOpponentUID").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    opponentUID = snapshot.getValue(String.class);
                                    if (opponentUID != null) {


                                        opponentDataGetterFunction();
                                        isHost=1;

                                        myRef.child("User").child(opponentUID).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                opponentimageUrl = snapshot.getValue(String.class);

                                                myRef.child("User").child(opponentUID).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        opponentUsername = snapshot.getValue(String.class);
                                                        cardView5.setAlpha(1);
                                                        linearLayout100.setAlpha(1);
                                                        batchLinearLayout.setAlpha(1);
                                                        shimmer1.stopShimmerAnimation();
                                                        shimmer1.setVisibility(View.GONE);

                                                        Glide.with(getBaseContext())
                                                                .load(opponentimageUrl)
                                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                                .into(secondplayerimg);

                                                        secondPlayername.setText(opponentUsername);


                                                        cancelButton.setEnabled(false);
                                                        cancelButton.setAlpha(1);
                                                        countDownForArr();
                                                        countDownTimerFun();
                                                    }

                                                    @Override
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
                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void countDownTimerFun() {   //Clock Algo
        countDownTimer = new CountDownTimer(1000 * 10, 1000) {


            public void onTick(long millisUntilFinished) {
                int sec = (int) (millisUntilFinished / 1000);
                cancelButton.setText("Quiz Starts In " + sec + " Seconds");
                cancelButton.setTextSize(15f);
            }

            @Override
            public void onFinish() {
                myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(isHost==0){
                            String str=snapshot.getValue(String.class);
                            String uid=mAuth.getCurrentUser().getUid();
                            try{
                            if(str.equals(uid)){
                            Intent intent = new Intent(oneVsOneChoiceActivity.this, onevsoneQuizActivity.class);
                            intent.putExtra("opponentUID", opponentUID);
                            intent.putExtra("opponentImageUrl", opponentimageUrl);
                            intent.putExtra("opponentUserName", opponentUsername);
                            intent.putExtra("mypropic", proPicUrl);
                            intent.putExtra("myName", userName);
                            intent.putExtra("leader", leader);
                            intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist);
                                if(countDownTimer!=null){
                                    countDownTimer.cancel();
                                }
                                if(countDownTimer123!=null){
                                    countDownTimer123.cancel();
                                }
                            startActivity(intent);
                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }else{
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer123!=null){
                                countDownTimer123.cancel();
                            }
                                Toast.makeText(oneVsOneChoiceActivity.this, "Your Opponent Joined Another Room!!Try Again", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(oneVsOneChoiceActivity.this,mainMenuActivity.class);
                            startActivity(intent);
                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }}catch (Exception e){
                                Intent intent = new Intent(oneVsOneChoiceActivity.this, onevsoneQuizActivity.class);
                                intent.putExtra("opponentUID", opponentUID);
                                intent.putExtra("opponentImageUrl", opponentimageUrl);
                                intent.putExtra("opponentUserName", opponentUsername);
                                intent.putExtra("mypropic", proPicUrl);
                                intent.putExtra("myName", userName);
                                intent.putExtra("leader", leader);
                                intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist);
                                if(countDownTimer!=null){
                                    countDownTimer.cancel();
                                }
                                if(countDownTimer123!=null){
                                    countDownTimer123.cancel();
                                }
                                startActivity(intent);
                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                finish();
                            }
                        }else{
                            Intent intent = new Intent(oneVsOneChoiceActivity.this, onevsoneQuizActivity.class);
                            intent.putExtra("opponentUID", opponentUID);
                            intent.putExtra("opponentImageUrl", opponentimageUrl);
                            intent.putExtra("opponentUserName", opponentUsername);
                            intent.putExtra("mypropic", proPicUrl);
                            intent.putExtra("myName", userName);
                            intent.putExtra("leader", leader);
                            intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist);
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer123!=null){
                                countDownTimer123.cancel();
                            }
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        }.start();

    }


    public void countDownForArr() {   //Clock Algo
        countDownTimer123 = new CountDownTimer(1000 * 10, 1000) {


            public void onTick(long millisUntilFinished) {
                randomNumberGeneratorTaker();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }


    public void randomNumberGeneratorTaker() {

        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child("OnCompleteHolder").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num47 = snapshot.getValue(Integer.class);
                    if (num47 == 1) {
                        countDownTimer123.cancel();
                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(1)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(1)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(2)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(2)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(3)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(3)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(4)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(4)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(5)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(5)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(6)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(6)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(7)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(7)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(8)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(8)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(9)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(9)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(10)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(10)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(11)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(11)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(12)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(12)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(13)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(13)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void randomNumberGeneratorFunction(String opponentUID) {
        for (int i = 0; i < 14; i++) {
            // create instance of Random class

            // Generate random integers in range 0 to 29
            Random rand1 = new Random();
             setNumber=rand1.nextInt(6326)+1;
             //NEED TO CHANGE HERE//NEED TO CHANGE HERE
            //NEED TO CHANGE HERE
            arrlist.add(setNumber);
        }

        try {
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(1)).setValue(arrlist.get(0)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(2)).setValue(arrlist.get(1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(3)).setValue(arrlist.get(2)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(4)).setValue(arrlist.get(3)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(5)).setValue(arrlist.get(4)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(6)).setValue(arrlist.get(5)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(7)).setValue(arrlist.get(6)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(8)).setValue(arrlist.get(7)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(9)).setValue(arrlist.get(8)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(10)).setValue(arrlist.get(9)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(11)).setValue(arrlist.get(10)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(12)).setValue(arrlist.get(11)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(13)).setValue(arrlist.get(12)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child("OnCompleteHolder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, "mainer", Toast.LENGTH_SHORT).show();
        }


    }


    public void opponentDataGetterFunction() {

        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              try{
                  score123 = snapshot.getValue(Integer.class);

                  highestScore.setText(" Higest Score : " + score123 + " ");

                  myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("totalTime").addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          timeHolder = snapshot.getValue(Integer.class);

                          if (timeHolder < 60) {
                              totalTime.setText("Total Time : " + timeHolder + " sec ");
                          } else {
                              int minutes = timeHolder / 60;
                              int sec = timeHolder % 60;
                              totalTime.setText("Total Time : " + minutes + " min " + sec + " sec ");
                          }



                          myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("correct").addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {

                                  correct = snapshot.getValue(Integer.class);


                                  myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("wrong").addListenerForSingleValueEvent(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                                          wrongfire = snapshot.getValue(Integer.class);

                                          sumofq = correct + wrongfire;
                                          jocker = (correct *100) / sumofq;

                                          oppoAccu.setText(" Accuracy : " + jocker + "%");
                                          oppoRatio.setText(" Correct/Wrong : " + correct + "/" + wrongfire);

                                          myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                  sumationOfScore = snapshot.getValue(Integer.class);
                                                  levelManupulation();

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
              }catch (Exception e){
                  levelManupulation();
                  highestScore.setText(" Higest Score : null");
                  totalTime.setText(" Total Time : null ");
                  oppoAccu.setText(" Accuracy : null");
                  oppoRatio.setText(" Correct/Wrong : null");
              }

                }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void levelManupulation(){

        if(sumationOfScore<100000){
            if(sumationOfScore<50000){
                oppoLevel.setText(" Lv. 1 ");
            }else{
                oppoLevel.setText(" Lv. 2 ");
            }
        }else{
            int holder;
            holder=sumationOfScore/50000;
            oppoLevel.setText(" Lv. "+holder+" ");
        }

        if(sumationOfScore<50000){
            oppoBatch.setBackgroundResource(R.drawable.blackiron);
        }else if(sumationOfScore<200000){
            oppoBatch.setBackgroundResource(R.drawable.bronze);
        }else if(sumationOfScore<800000){
            oppoBatch.setBackgroundResource(R.drawable.silver);
        }else if(sumationOfScore<1800000){
            oppoBatch.setBackgroundResource(R.drawable.gold);
        }else if(sumationOfScore<3000000){
            oppoBatch.setBackgroundResource(R.drawable.platinum);
        }else if(sumationOfScore<4000000){
            oppoBatch.setBackgroundResource(R.drawable.diamond);
        }else if(sumationOfScore<8000000){
            oppoBatch.setBackgroundResource(R.drawable.amethyst);
        }else if(sumationOfScore<12000000){
            oppoBatch.setBackgroundResource(R.drawable.master);
        }else{
            oppoBatch.setBackgroundResource(R.drawable.king);
        }
    }


    public void myDataSeeter(){
        myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int sumationScore=snapshot.getValue(Integer.class);
                    if(sumationScore<100000){
                        if(sumationScore<50000){
                            myLevel.setText(" Lv. 1 ");
                        }else{
                            myLevel.setText(" Lv. 2 ");
                        }
                    }else{
                        int holder;
                        holder=sumationScore/50000;
                        myLevel.setText(" Lv. "+holder+" ");
                    }

                    if(sumationScore<50000){
                        myBatch.setBackgroundResource(R.drawable.blackiron);
                    }else if(sumationScore<200000){
                        myBatch.setBackgroundResource(R.drawable.bronze);
                    }else if(sumationScore<800000){
                        myBatch.setBackgroundResource(R.drawable.silver);
                    }else if(sumationScore<1800000){
                        myBatch.setBackgroundResource(R.drawable.gold);
                    }else if(sumationScore<3000000){
                        myBatch.setBackgroundResource(R.drawable.platinum);
                    }else if(sumationScore<4000000){
                        myBatch.setBackgroundResource(R.drawable.diamond);
                    }else if(sumationScore<8000000){
                        myBatch.setBackgroundResource(R.drawable.amethyst);
                    }else if(sumationScore<12000000){
                        myBatch.setBackgroundResource(R.drawable.master);
                    }else{
                        myBatch.setBackgroundResource(R.drawable.king);
                    }
                }catch (Exception e){
                    myLevel.setText(" Lv. 1 ");
                    myBatch.setBackgroundResource(R.drawable.blackiron);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void joinFunction(){
        AlertDialog.Builder builder = new AlertDialog.Builder(oneVsOneChoiceActivity.this, R.style.AlertDialogTheme);
        View view3 = LayoutInflater.from(oneVsOneChoiceActivity.this).inflate(R.layout.join_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);
        builder.setView(view3);
        builder.setCancelable(true);
        joinButtonFinal = ((Button) view3.findViewById(R.id.joinButton1));
        roomCodeEditText=((TextInputEditText) view3.findViewById(R.id.password));



        alertDialog123 = builder.create();
        if (alertDialog123.getWindow() != null) {
            alertDialog123.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog123.show();


        joinButtonFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomCodeString=roomCodeEditText.getText().toString();
                codeInteger= Integer.parseInt(roomCodeString);
                joinTunedFunction();

            }
        });
    }

    public void joinTunedFunction(){

        myRef.child("oneVsoneLocalPlayers").orderByChild("status").equalTo(codeInteger).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list123.add(dataSnapshot1.getValue(onevsoneOnlinePlayerList.class));
               }


                try {
                    opponentUID = list123.get(0).getUID();
                    leader = 1;
                    alertDialog123.dismiss();
                    myRef.child("oneVsoneLocalPlayers").child(opponentUID).removeValue();


                    randomNumberGeneratorFunction(opponentUID);


                    myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").setValue(mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            myRef.child("User").child(opponentUID).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    opponentimageUrl = snapshot.getValue(String.class);

                                    myRef.child("User").child(opponentUID).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            opponentUsername = snapshot.getValue(String.class);



                                            builder.setView(view1);
                                            builder.setCancelable(false);
                                            cancelButton = ((Button) view1.findViewById(R.id.cancelButton));
                                            userImage = (ImageView) view1.findViewById(R.id.exam_img_id);
                                            userName123 = (TextView) view1.findViewById(R.id.userName123);
                                            batch = (ImageView) view1.findViewById(R.id.batch4);
                                            level = (TextView) view1.findViewById(R.id.levelText1234);
                                            slideViewPager = (ViewPager) view1.findViewById(R.id.slideViewPager);
                                            dotLayout = (LinearLayout) view1.findViewById(R.id.dotLayout);
                                            secondplayerimg = (ImageView) view1.findViewById(R.id.secondplayerimg);
                                            secondPlayername = (TextView) view1.findViewById(R.id.secondPlayername);
                                            myLevel = (TextView) view1.findViewById(R.id.levelText1234);
                                            myBatch = (ImageView) view1.findViewById(R.id.batch4);
                                            oppoLevel = (TextView) view1.findViewById(R.id.levelText12341);
                                            oppoBatch = (ImageView) view1.findViewById(R.id.batch41);


                                            dataSetterForOneVsOne();
                                            myDataSeeter();

                                            for (int i = 1; i <= 3; i++) {
                                                dataForHorizontalSlide();
                                            }

                                            final AlertDialog alertDialog = builder.create();
                                            if (alertDialog.getWindow() != null) {
                                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                            }
                                            alertDialog.show();


                                            cancelButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    try {
                                                        myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();

                                                    } catch (Exception e) {

                                                    }
                                                    final MediaPlayer musicNav;
                                                    musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                                                    musicNav.start();
                                                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                                        @Override
                                                        public void onCompletion(MediaPlayer mediaPlayer) {
                                                            musicNav.reset();
                                                            musicNav.release();
                                                        }
                                                    });
                                                    alertDialog.dismiss();
                                                    Intent intent=new Intent(oneVsOneChoiceActivity.this,mainMenuActivity.class);
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });




                                            Glide.with(getBaseContext())
                                                    .load(opponentimageUrl)
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                    .into(secondplayerimg);

                                            myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                    try{
                                                        score123 = snapshot.getValue(Integer.class);

                                                        highestScore.setText(" Higest Score : " + score123 + " ");

                                                        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("totalTime").addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                timeHolder = snapshot.getValue(Integer.class);

                                                                if (timeHolder < 60) {
                                                                    totalTime.setText("Total Time : " + totalTime + " sec ");
                                                                } else {
                                                                    int minutes = timeHolder / 60;
                                                                    int sec = timeHolder % 60;
                                                                    totalTime.setText("Total Time : " + minutes + " min " + sec + " sec ");
                                                                }



                                                                myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("correct").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                        correct = snapshot.getValue(Integer.class);


                                                                        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("wrong").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                                wrongfire = snapshot.getValue(Integer.class);

                                                                                sumofq = correct + wrongfire;
                                                                                jocker = (correct *100) / sumofq;

                                                                                oppoAccu.setText(" Accuracy : " + jocker + "%");
                                                                                oppoRatio.setText(" Correct/Wrong : " + correct + "/" + wrongfire);

                                                                                myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                                        sumationOfScore = snapshot.getValue(Integer.class);
                                                                                        levelManupulation();

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
                                                    }catch (Exception e){
                                                        highestScore.setText(" Higest Score : null");
                                                        totalTime.setText(" Total Time : null ");
                                                        oppoAccu.setText(" Accuracy : null");
                                                        oppoRatio.setText(" Correct/Wrong : null");
                                                    }

                                                }




                                                @Override
                                                public void onCancelled(@NonNull DatabaseError error) {

                                                }
                                            });

                                            cardView5.setAlpha(1);
                                            linearLayout100.setAlpha(1);
                                            batchLinearLayout.setAlpha(1);
                                            shimmer1.stopShimmerAnimation();
                                            shimmer1.setVisibility(View.GONE);

                                            secondPlayername.setText(opponentUsername);


                                            cancelButton.setEnabled(false);
                                            cancelButton.setAlpha(1);
                                            countDownTimerFun();
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                        }
                    });
                }catch (Exception e){
                    roomCodeEditText.setError("Room Code Is Wrong");

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void roomCodeGenerator(){
        final Dialog loadingDialog;
        loadingDialog=new Dialog(oneVsOneChoiceActivity.this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();
        Random rand = new Random();
        // Generate random integers in range 0 to 29
        roomCode = rand.nextInt(999999)+1;
        myRef.child("onevsoneLocalPlayers").orderByChild("status").equalTo(roomCode).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.getValue(Integer.class)!=null){
                   roomCodeGenerator();
               }else{
                   loadingDialog.dismiss();
                   createFunction();
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void createFunction(){

       // roomCodeGenerator();

        onevsoneOnlinePlayerList s1 = new onevsoneOnlinePlayerList(mAuth.getCurrentUser().getUid(), roomCode);
        myRef.child("oneVsoneLocalPlayers").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                builder9.setView(view9);
                builder9.setCancelable(false);
                cancelButton = ((Button) view9.findViewById(R.id.cancelButton));
                userImage = (ImageView) view9.findViewById(R.id.exam_img_id);
                userName123 = (TextView) view9.findViewById(R.id.userName123);
                batch = (ImageView) view9.findViewById(R.id.batch4);
                level = (TextView) view9.findViewById(R.id.levelText1234);
                slideViewPager = (ViewPager) view9.findViewById(R.id.slideViewPager);
                dotLayout = (LinearLayout) view9.findViewById(R.id.dotLayout);
                secondplayerimg = (ImageView) view9.findViewById(R.id.secondplayerimg);
                secondPlayername = (TextView) view9.findViewById(R.id.secondPlayername);
                myLevel = (TextView) view9.findViewById(R.id.levelText1234);
                myBatch = (ImageView) view9.findViewById(R.id.batch4);
                oppoLevel = (TextView) view9.findViewById(R.id.levelText12341);
                oppoBatch = (ImageView) view9.findViewById(R.id.batch41);
                share=(CardView) view9.findViewById(R.id.share);

                dataSetterForOneVsOne();
                myDataSeeter();

                for (int i = 1; i <= 3; i++) {
                    dataForHorizontalSlide();
                }

                final AlertDialog alertDialog = builder9.create();
                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();


                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();

                        } catch (Exception e) {

                        }
                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                        musicNav.start();
                        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                musicNav.reset();
                                musicNav.release();
                            }
                        });

                        myRef.child("oneVsoneLocalPlayers").child(mAuth.getCurrentUser().getUid()).removeValue();
                        oneVsOneChoiceActivity.super.onBackPressed();
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        finish();
                    }
                });

                shareText.setText(" Room Code : "+roomCode);



                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(oneVsOneChoiceActivity.this, R.raw.finalbuttonmusic);
                        musicNav.start();
                        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                musicNav.reset();
                                musicNav.release();
                            }
                        });
                        Intent shareIntent=new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plane");
                        String shareBody=userName+" Has Created A Room To Play With You.\n"+"Here's Your Room Code : "+roomCode+".";
                        String sharesub="MindScape";
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                        shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                        startActivity(Intent.createChooser(shareIntent,"Room Code"));

                    }
                });


                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineOpponentUID").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        opponentUID = snapshot.getValue(String.class);
                        if (opponentUID != null) {


                            myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    try{
                                        score123 = snapshot.getValue(Integer.class);

                                        highestScore1.setText(" Higest Score : " + score123 + " ");

                                        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("totalTime").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                timeHolder = snapshot.getValue(Integer.class);

                                                if (timeHolder < 60) {
                                                    totalTime1.setText("Total Time : " + totalTime + " sec ");
                                                } else {
                                                    int minutes = timeHolder / 60;
                                                    int sec = timeHolder % 60;
                                                    totalTime1.setText("Total Time : " + minutes + " min " + sec + " sec ");
                                                }



                                                myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("correct").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                        correct = snapshot.getValue(Integer.class);


                                                        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("wrong").addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                                wrongfire = snapshot.getValue(Integer.class);

                                                                sumofq = correct + wrongfire;
                                                                jocker = (correct *100) / sumofq;

                                                                oppoAccu1.setText(" Accuracy : " + jocker + "%");
                                                                oppoRatio1.setText(" Correct/Wrong : " + correct + "/" + wrongfire);

                                                                myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                                                                    @Override
                                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                        sumationOfScore = snapshot.getValue(Integer.class);
                                                                        levelManupulation99();

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
                                    }catch (Exception e){
                                        highestScore.setText(" Highest Score : null");
                                        totalTime.setText(" Total Time : null ");
                                        oppoAccu.setText(" Accuracy : null");
                                        oppoRatio.setText(" Correct/Wrong : null");
                                    }

                                }




                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                            myRef.child("User").child(opponentUID).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    opponentimageUrl = snapshot.getValue(String.class);

                                    myRef.child("User").child(opponentUID).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            opponentUsername = snapshot.getValue(String.class);




                                            cardView5.setAlpha(1);
                                            linearLayout300.setAlpha(1);
                                            batchLinearLayout.setAlpha(1);
                                            shimmer40.stopShimmerAnimation();
                                            shimmer40.setVisibility(View.GONE);

                                            Glide.with(getBaseContext())
                                                    .load(opponentimageUrl)
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                    .into(secondplayerimg);

                                            secondPlayername.setText(opponentUsername);

                                            cancelButton.setEnabled(false);
                                            cancelButton.setAlpha(1);
                                            countDownForArr();
                                            countDownTimerFun();
                                        }

                                        @Override
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
                        }
                    });
    }


    public void levelManupulation99(){

        if(sumationOfScore<100000){
            if(sumationOfScore<50000){
                levelText99.setText(" Lv. 1 ");
            }else{
                levelText99.setText(" Lv. 2 ");
            }
        }else{
            int holder;
            holder=sumationOfScore/50000;
            levelText99.setText(" Lv. "+holder+" ");
        }

        if(sumationOfScore<50000){
            batch99.setBackgroundResource(R.drawable.blackiron);
        }else if(sumationOfScore<200000){
            batch99.setBackgroundResource(R.drawable.bronze);
        }else if(sumationOfScore<800000){
            batch99.setBackgroundResource(R.drawable.silver);
        }else if(sumationOfScore<1800000){
            batch99.setBackgroundResource(R.drawable.gold);
        }else if(sumationOfScore<3000000){
            batch99.setBackgroundResource(R.drawable.platinum);
        }else if(sumationOfScore<4000000){
            batch99.setBackgroundResource(R.drawable.diamond);
        }else if(sumationOfScore<8000000){
            batch99.setBackgroundResource(R.drawable.amethyst);
        }else if(sumationOfScore<12000000){
            batch99.setBackgroundResource(R.drawable.master);
        }else{
            batch99.setBackgroundResource(R.drawable.king);
        }
    }



}