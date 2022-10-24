package com.nbird.mindscape;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OneVsOneBOTNormalQuiz extends AppCompatActivity {
    Dialog loadingDialog,expert1dialog;
    //1344
    TextView questionTextView,scoreBoard;
    Button option1,option2,option3,option4,nextButton;
    LinearLayout linearLayout;
    private int count;
    private List<questionHolder> list,listsecondary;
    private List<mainMenuFactsHolder> list4;
    CountDownTimer countDownTimer,countDownTimer47,countDownTimer50,countDownTimerMine;
    int position=0;
    private int score=0;
    private int currentPage;
    int category;
    private int setNo;
    long milliHolder;
    int kong;
    int minMine,secMine;
    int bull=0;
    int car=0;
    int a1,a2;
    String mine;
    private TextView[] mDots;
    private onevsoneonlineAdapter sliderAdapter;
    ActionBarDrawerToggle mToggle;
    private LinearLayout dotLayout;
    private ShimmerFrameLayout mShimmerViewContainer;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    TextView timerText;
    String imageurl;
    int jack=1;
    int num=0;
    ImageView expertImage;
    int expertnum=0;
    int swapnum=0;
    int audiencenum=0;
    public ViewPager slideViewPager;
    int manupulator=0;
    int manupulator1=0;
    int fiftyfiftynum=0;
    int leader;
    TextView titleText;
    int yo1;
    int yo2;
    int yo3;
    int yo4;
    int minman,secman;
    String userName;
    int selectNum;
    int lifelineSum=0;
    int myArrPos=1;
    CardView audienceLL,expertAdviceLL,fiftyfiftyLL,swapTheQuestionLL;
    LinearLayout linearLayoutexpert,linearLayoutAudience,linearLayoutFiftyFifty,linearLayoutSwap;
    CountDownTimer cCompany;
    int minutes=2;
    int second=59;
    String minutestext,myProPicUrl,myName;
    String secondtext;
    int cou=0;
    int questionNumber=1;
    int num123;
    String opponentUID,opponentUsername,opponentimageUrl;
    int binaryPosition=1;
    int opponentAnswer;
    LottieAnimationView anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;
    ImageView opponentPic;
    TextView opponentName;
    int isComplete;
    ImageView propic;
    TextView yourName,timeTaken,ratio,accuracy,lifeLines;
    AlertDialog.Builder builder;
    View view1;
    int oppoScoreCounter=0;
    int oppoWrongAnsCounter=0;
    int myArr[];
    List<Integer> arrlist,arroppo;
    List<Integer> arrlist12345;
    int min12345,sec12345;
    int milliHolder47;
    ValueEventListener listner,listner1;
    int oppoStatus=5;
    ImageView myPicImageView;
    TextView myNameTextView;
    LottieAnimationView anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20;
    int myPosition=0;
    int setNumber,isHostFinal;
    long stopperLong;
    songActivity songActivity;
    int finisherNum=0;
    ValueEventListener listenerFast0,listenerFast1,listenerFast2,listenerFast3,listenerFast4,listenerFast5,listenerFast6,listenerFast7,listenerFast8,listenerFast9,listenerFast10,listenerFast11,listenerFast12;
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
                    songActivity=new songActivity(OneVsOneBOTNormalQuiz.this);
                    songActivity.startMusic();
                    Speaker.setBackgroundResource(R.drawable.whitewithblackstroke);
                    speakerImage.setBackgroundResource(R.drawable.speakeron);
                    editorsongStopper.putBoolean("IsPlaying", true);
                    editorsongStopper.commit();
                }




            }
        });
    }

    CountDownTimer countDownTimerForBot;
    int botTime,botCorrectAns;

    public void botFunction(){
         Random r=new Random();
         int jk=r.nextInt(10)+5;
        countBot(jk);
    }

    public void countBot(int jk){
        countDownTimerForBot=new CountDownTimer(1000*jk,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                botTime++;
            }

            @Override
            public void onFinish() {
                Random r=new Random();
                Boolean b=r.nextBoolean();
                int ans;
                if(b){
                    botCorrectAns++;
                    ans=1;
                }else{
                    ans=2;
                }
               animManupulation(ans,binaryPosition);
               binaryPosition++;
               if(binaryPosition<=10){
                   Random ro=new Random();
                   int jk=ro.nextInt(8)+5;
                   countBot(jk);
               }

            }
        }.start();
    }

    int sumScore;
    private InterstitialAd mInterstitialAd;
    private void loadAds(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onevsone_quiz);

        loadAds();
        songStopperAndResumer();

        botFunction();

        builder=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);
        view1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.waiting_dialog_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        arrlist12345 = new ArrayList<>(11);
        opponentPic=(ImageView) findViewById(R.id.opponentPic);
        opponentName=(TextView) findViewById(R.id.opponentName);
        questionTextView=findViewById(R.id.question);
        scoreBoard=findViewById(R.id.questionNumber);
        option1=(Button) findViewById(R.id.button1);
        option2=(Button) findViewById(R.id.button2);
        option3=(Button) findViewById(R.id.button3);
        option4=(Button) findViewById(R.id.button4);
        nextButton=(Button) findViewById(R.id.nextbutton);
        linearLayout=(LinearLayout) findViewById(R.id.linearButtonlayout);
        timerText=(TextView) findViewById(R.id.timer);
        audienceLL=(CardView) findViewById(R.id.audience);
        expertAdviceLL=(CardView) findViewById(R.id.expert);
        fiftyfiftyLL=(CardView) findViewById(R.id.fiftyfifty);
        swapTheQuestionLL=(CardView) findViewById(R.id.swap);
        linearLayoutexpert=(LinearLayout) findViewById(R.id.linearLayoutexpert) ;
        linearLayoutAudience=(LinearLayout) findViewById(R.id.linearLayoutAudience) ;
        linearLayoutFiftyFifty=(LinearLayout) findViewById(R.id.linearLayoutfiftyfifty) ;
        linearLayoutSwap=(LinearLayout) findViewById(R.id.linearLayoutSwap);
        mShimmerViewContainer = (ShimmerFrameLayout) view1.findViewById(R.id.shimmer_view_container);
        slideViewPager=(ViewPager) view1.findViewById(R.id.slideViewPager);
        dotLayout=(LinearLayout) view1.findViewById(R.id.dotLayout);
        myNameTextView=(TextView) findViewById(R.id.myName);
        myPicImageView=(ImageView) findViewById(R.id.myPic);

        anim1=(LottieAnimationView) findViewById(R.id.anim1);
        anim2=(LottieAnimationView) findViewById(R.id.anim2);
        anim3=(LottieAnimationView) findViewById(R.id.anim3);
        anim4=(LottieAnimationView) findViewById(R.id.anim4);
        anim5=(LottieAnimationView) findViewById(R.id.anim5);
        anim6=(LottieAnimationView) findViewById(R.id.anim6);
        anim7=(LottieAnimationView) findViewById(R.id.anim7);
        anim8=(LottieAnimationView) findViewById(R.id.anim8);
        anim9=(LottieAnimationView) findViewById(R.id.anim9);
        anim10=(LottieAnimationView) findViewById(R.id.anim10);

        anim11=(LottieAnimationView) findViewById(R.id.anim11);
        anim12=(LottieAnimationView) findViewById(R.id.anim12);
        anim13=(LottieAnimationView) findViewById(R.id.anim13);
        anim14=(LottieAnimationView) findViewById(R.id.anim14);
        anim15=(LottieAnimationView) findViewById(R.id.anim15);
        anim16=(LottieAnimationView) findViewById(R.id.anim16);
        anim17=(LottieAnimationView) findViewById(R.id.anim17);
        anim18=(LottieAnimationView) findViewById(R.id.anim18);
        anim19=(LottieAnimationView) findViewById(R.id.anim19);
        anim20=(LottieAnimationView) findViewById(R.id.anim20);

        arrlist = new ArrayList<>(12);
        arroppo = new ArrayList<>(12);

        userNameFunction();

        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);


        list=new ArrayList<>();
        list4=new ArrayList<>();
        listsecondary=new ArrayList<>();



        isHostFinal=getIntent().getIntExtra("isHostFinal",0);
        category=getIntent().getIntExtra("category",1);
        setNo=getIntent().getIntExtra("setNo",10);
        opponentUID=getIntent().getStringExtra("opponentUID");
        opponentimageUrl=getIntent().getStringExtra("opponentImageUrl");
        opponentUsername=getIntent().getStringExtra("opponentUserName");
        myProPicUrl=getIntent().getStringExtra("mypropic");
        myName=getIntent().getStringExtra("myName");
        leader=getIntent().getIntExtra("leader",0);
        sumScore=getIntent().getIntExtra("sumScore",0);
        arrlist12345=getIntent().getIntegerArrayListExtra("arrList12345");







        opponentName.setText(opponentUsername);
        myNameTextView.setText(myName);

        Glide.with(getBaseContext())
                .load(opponentimageUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(opponentPic);

        Glide.with(getBaseContext())
                .load(myProPicUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(myPicImageView);




        loadingDialog.show();
        proPicFunction();


        countDownTimerFun();
        countDownTimerFun47();



        //countDownTimerFun50();
        for(int i=0;i<12;i++){
            try{
                // create instance of Random class
                fireBaseData(arrlist12345.get(i));
            }catch (Exception e){
                Random rand1 = new Random();
                setNumber=rand1.nextInt(6326)+1; //NEED TO CHANGE HERE
                fireBaseData(setNumber);
            }

        }


        fiftyfiftyLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fiftyfiftynum==0) {
                     final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelinemusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                    lifelineSum++;
                    fiftyfiftynum = 1;
                    linearLayoutFiftyFifty.setBackgroundResource(R.drawable.usedicon);
                    if (option1.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator1 = 1;
                    } else if (option2.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator1 = 2;
                    } else if (option3.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator1 = 3;
                    } else {
                        manupulator1 = 4;
                    }

                    Random rand3 = new Random();
                    int runStopper = 0;
                    while (runStopper == 0) {

                        selectNum = rand3.nextInt(4) + 1;

                        if (manupulator1 != selectNum) {
                            runStopper = 1;

                        }
                    }

                    switch (selectNum) {
                        case 1:
                            switch (manupulator1) {
                                case 2:
                                    option3.setText("");
                                    option4.setText("");
                                    break;
                                case 3:
                                    option2.setText("");
                                    option4.setText("");
                                    break;
                                case 4:
                                    option2.setText("");
                                    option3.setText("");
                                    break;
                            }
                            break;
                        case 2:
                            switch (manupulator1) {
                                case 1:
                                    option3.setText("");
                                    option4.setText("");
                                    break;
                                case 3:
                                    option1.setText("");
                                    option4.setText("");
                                    break;
                                case 4:
                                    option3.setText("");
                                    option1.setText("");
                                    break;
                            }
                            break;
                        case 3:
                            switch (manupulator1) {
                                case 1:
                                    option2.setText("");
                                    option4.setText("");
                                    break;
                                case 2:
                                    option1.setText("");
                                    option4.setText("");
                                    break;
                                case 4:
                                    option2.setText("");
                                    option1.setText("");
                                    break;
                            }
                            break;
                        case 4:
                            switch (manupulator1) {
                                case 1:
                                    option3.setText("");
                                    option2.setText("");
                                    break;
                                case 2:
                                    option1.setText("");
                                    option3.setText("");
                                    break;
                                case 3:
                                    option2.setText("");
                                    option1.setText("");
                                    break;
                            }
                            break;
                    }

                }else{
                    final MediaPlayer musicNav1;
                    musicNav1 = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelineused);
                    musicNav1.start();
                    musicNav1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav1.reset();
                            musicNav1.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your FIFTY-FIFTY Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    try{
                        alertDialog.show();
                    }catch (Exception e){

                    }

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });

                }



            }
        });

        audienceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(audiencenum==0) {
                     final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelinemusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                    lifelineSum++;
                    audiencenum=1;
                    linearLayoutAudience.setBackgroundResource(R.drawable.usedicon);

                    if (option1.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator = 1;
                    } else if (option2.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator = 2;
                    } else if (option3.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator = 3;
                    } else {
                        manupulator = 4;
                    }

                    Random rand2 = new Random();


                    int setMax = 100 - rand2.nextInt(60);

                    switch (manupulator) {
                        case 1:
                            yo1 = setMax;
                            try{
                                yo2 = rand2.nextInt(100 - yo1);
                                yo3 = rand2.nextInt(100 - yo1 - yo2);
                            }catch (Exception e){
                                yo2 = rand2.nextInt(100 - yo1+1);
                                yo3 = rand2.nextInt(100 - yo1 - yo2+1);
                            }
                            yo4 = 100 - yo1 - yo2 - yo3;
                            break;
                        case 2:
                            yo2 = setMax;
                            try{
                                yo1 = rand2.nextInt(100 - yo2);
                                yo3 = rand2.nextInt(100 - yo2 - yo1);
                            }catch (Exception e){
                                yo1 = rand2.nextInt(100 - yo2+1);
                                yo3 = rand2.nextInt(100 - yo2 - yo1+1);
                            }
                            yo4 = 100 - yo2 - yo1 - yo3;
                            break;
                        case 3:
                            yo3 = setMax;
                            try{
                                yo2 = rand2.nextInt(100 - yo3);
                                yo1 = rand2.nextInt(100 - yo3 - yo2);
                            }catch (Exception e){
                                yo2 = rand2.nextInt(100 - yo3+1);
                                yo1 = rand2.nextInt(100 - yo3 - yo2+1);
                            }

                            yo4 = 100 - yo3 - yo2 - yo1;
                            break;
                        case 4:
                            yo4 = setMax;
                            try{
                                yo2 = rand2.nextInt(100 - yo4);
                                yo1 = rand2.nextInt(100 - yo4 - yo2);
                            }catch (Exception e){
                                yo2 = rand2.nextInt(100 - yo4+1);
                                yo1 = rand2.nextInt(100 - yo4 - yo2+1);
                            }

                            yo3 = 100 - yo4 - yo2 - yo1;
                            break;
                    }


                    AlertDialog.Builder builder = new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this, R.style.AlertDialogTheme);

                    final View view1 = LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.audience_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText(" MindScapers from across the world have casted their votes above. Choose your option! ");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    BarChart barChart = ((BarChart) view1.findViewById(R.id.barChart));


                    final ArrayList<BarEntry> visitors = new ArrayList<>();
                    visitors.add(new BarEntry(1, yo1));
                    visitors.add(new BarEntry(2, yo2));
                    visitors.add(new BarEntry(3, yo3));
                    visitors.add(new BarEntry(4, yo4));

                    AdView mAdView = view1.findViewById(R.id.adView);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);

                    BarDataSet barDataSet = new BarDataSet(visitors, "Bar Data");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                    barDataSet.setValueTextColor(Color.BLUE);
                    barDataSet.setValueTextSize(11f);

                    BarData barData = new BarData(barDataSet);

                    barChart.setFitBars(true);
                    barChart.setData(barData);
                    barChart.getDescription().setText("Audience Poll");
                    barChart.animateY(2000);


                    final AlertDialog alertDialog = builder.create();
                    if (alertDialog.getWindow() != null) {
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    try{
                        alertDialog.show();
                    }catch (Exception e){

                    }

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }else{
                    final MediaPlayer musicNav1;
                    musicNav1 = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelineused);
                    musicNav1.start();
                    musicNav1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav1.reset();
                            musicNav1.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your AUDIENCE POLL Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    try{
                        alertDialog.show();
                    }catch (Exception e){

                    }

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });



        swapTheQuestionLL.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if(swapnum==0){
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelinemusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    lifelineSum++;
                    swapnum=1;
                    linearLayoutSwap.setBackgroundResource(R.drawable.usedicon);
                    nextButton.setEnabled(false);
                    nextButton.setAlpha(0.7f);
                    enableOption(true);
                    position++;
                    LLTrueManupulator();


                    count = 0;
                    playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
                }else{
                    final MediaPlayer musicNav1;
                    musicNav1 = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelineused);
                    musicNav1.start();
                    musicNav1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav1.reset();
                            musicNav1.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your SWAP Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    try{
                        alertDialog.show();
                    }catch (Exception e){

                    }

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });

                }

            }
        });





        expertAdviceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(expertnum==0){
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelinemusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    lifelineSum++;
                    expertnum=1;
                    linearLayoutexpert.setBackgroundResource(R.drawable.usedicon);
                    String answerByExpert=list.get(position).getCorrectAnswer();


                    AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.expertadvicelayout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    titleText=((TextView) view1.findViewById(R.id.textTitle));
                    ((TextView) view1.findViewById(R.id.textMessage)).setText(userName+" I feel you should go for  : '"+answerByExpert+"'");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    expertImage=((ImageView) view1.findViewById(R.id.imageIcon));
                    AdView mAdView = view1.findViewById(R.id.adView);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    mAdView.loadAd(adRequest);
                    expertAdviceImageManupulator();


                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    try{
                        alertDialog.show();
                    }catch (Exception e){

                    }

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });


                }else{
                    final MediaPlayer musicNav1;
                    musicNav1 = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.lifelineused);
                    musicNav1.start();
                    musicNav1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav1.reset();
                            musicNav1.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    titleText=((TextView) view1.findViewById(R.id.textTitle));
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    expertAdviceImageManupulator();

                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    try{
                        alertDialog.show();
                    }catch (Exception e){

                    }

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }
            }
        });



    }

    @Override
    public void onDestroy() {
        super.onDestroy();



        if(countDownTimerMine!=null){
            countDownTimerMine.cancel();
        }

        if(countDownTimer!=null){
            countDownTimer.cancel();
        }

        if(countDownTimer47!=null){
            countDownTimer47.cancel();
        }

        if(countDownTimer50!=null){
            countDownTimer50.cancel();
        }

        if(cCompany!=null){
            cCompany.cancel();
        }

        if(countDownTimerForBot!=null){
            countDownTimerForBot.cancel();
        }



        Runtime.getRuntime().gc();
    }


    public void fireBaseData(int setNumber){
        myRef.child("NormalQuizBIGJSON").child(String.valueOf(setNumber)).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.add(snapshot.getValue(questionHolder.class));
                num++;

                if(num==10) {
                    if (list.size() > 0) {
                        for (int i = 0; i < 4; i++) {
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View view) {
                                    try{
                                        checkAnswer((Button) view);
                                    }catch (Exception e){
                                        // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Please Wait", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                        nextButton.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer musicNav1;
                                musicNav1 = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.finalbuttonmusic);
                                musicNav1.start();
                                musicNav1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        musicNav1.reset();
                                        musicNav1.release();
                                    }
                                });
                                nextButton.setEnabled(false);
                                nextButton.setAlpha(0.7f);
                                enableOption(true);
                                position++;
                                LLTrueManupulator();




                                if(swapnum==0){
                                    if (position == 10) {
                                        scoreBoard.setAlpha(0);
                                        questionTextView.setAlpha(0);
                                        option1.setAlpha(0);
                                        option2.setAlpha(0);
                                        option3.setAlpha(0);
                                        option4.setAlpha(0);
                                        min12345=minutes;
                                        sec12345=second;
                                        if(countDownTimer!=null){
                                            countDownTimer.cancel();}
                                        waitingFunction();
                                    }else{
                                        count = 0;

                                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
                                    }

                                }else {
                                    if (position == 11) {
                                        scoreBoard.setAlpha(0);
                                        questionTextView.setAlpha(0);
                                        option1.setAlpha(0);
                                        option2.setAlpha(0);
                                        option3.setAlpha(0);
                                        option4.setAlpha(0);
                                        min12345=minutes;
                                        sec12345=second;
                                        if(countDownTimer!=null){
                                            countDownTimer.cancel();}
                                        waitingFunction();
                                    }else{
                                        count = 0;

                                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());
                                    }
                                }



                            }
                        });
                    } else {
                        finish();
                        Toast.makeText(OneVsOneBOTNormalQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();


                    }
                    loadingDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OneVsOneBOTNormalQuiz.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
            }
        });
    }


    private void playAnim(final View view, final int value, final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationStart(Animator animator) {
                if(value==0 && count<4){
                    String option="";
                    if(count==0){
                        option=list.get(position).getOption1();
                        option1.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(0).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    }else if(count==1){
                        option=list.get(position).getOption2();
                        option2.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(1).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    }else if(count==2){
                        option=list.get(position).getOption3();
                        option3.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(2).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    }else if(count==3){
                        option=list.get(position).getOption4();
                        option4.setTextColor(Color.parseColor("#ffffff"));
                        linearLayout.getChildAt(3).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#000000")));
                    }
                    playAnim(linearLayout.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        if(swapnum==0){
                            scoreBoard.setText(" Question "+(position+1)+"/10 ");
                        }else{
                            scoreBoard.setText(" Question "+(position)+"/10 ");
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

    public void LLFalseManupulator(){        //Setting all the lifelines enable:False when an option is selected.
        audienceLL.setClickable(false);
        audienceLL.setEnabled(false);
        audienceLL.setAlpha(0.8f);

        expertAdviceLL.setClickable(false);
        expertAdviceLL.setEnabled(false);
        expertAdviceLL.setAlpha(0.8f);

        fiftyfiftyLL.setClickable(false);
        fiftyfiftyLL.setEnabled(false);
        fiftyfiftyLL.setAlpha(0.8f);

        swapTheQuestionLL.setClickable(false);
        swapTheQuestionLL.setEnabled(false);
        swapTheQuestionLL.setAlpha(0.8f);

    }

    public void LLTrueManupulator(){       //Setting all the lifelines enable:False when next button is pressed.
        audienceLL.setClickable(true);
        audienceLL.setEnabled(true);
        audienceLL.setAlpha(1.0f);

        expertAdviceLL.setClickable(true);
        expertAdviceLL.setEnabled(true);
        expertAdviceLL.setAlpha(1.0f);

        fiftyfiftyLL.setClickable(true);
        fiftyfiftyLL.setEnabled(true);
        fiftyfiftyLL.setAlpha(1.0f);

        swapTheQuestionLL.setClickable(true);
        swapTheQuestionLL.setEnabled(true);
        swapTheQuestionLL.setAlpha(1.0f);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption){
        enableOption(false);
        nextButton.setEnabled(true);
        nextButton.setAlpha(1);

        LLFalseManupulator();

        if(selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){
            //correct
            final MediaPlayer musicNav;
            musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.correctmusic);
            musicNav.start();
            musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicNav.reset();
                    musicNav.release();
                }
            });

            myanimManuCorrect();


            num123=1;
            arrlist.add(1);
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));   //green color
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            selectedOption.setShadowLayer(3,1,1,R.color.lightgreen);
            score++;
        }else {
            //incorrect
            final MediaPlayer musicNav;
            musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.wrongansfinal);
            musicNav.start();
            musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicNav.reset();
                    musicNav.release();
                }
            });
            myanimManuWrong();



            num123=0;
            arrlist.add(2);
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF8888")));     //red color
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            selectedOption.setShadowLayer(3,1,1,R.color.lightgreen);
            Button correctOption = (Button) linearLayout.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));     //green color
            correctOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            correctOption.setShadowLayer(3,1,1,R.color.lightred);
        }
    }

    public void myanimManuCorrect(){
        myPosition++;
        switch (myPosition){
            case 1:
                anim11.setAnimation(R.raw.tickanim);
                anim11.playAnimation();
                anim11.loop(false);break;
            case 2:
                anim12.setAnimation(R.raw.tickanim);
                anim12.playAnimation();
                anim12.loop(false);break;
            case 3:
                anim13.setAnimation(R.raw.tickanim);
                anim13.playAnimation();
                anim13.loop(false);break;
            case 4:
                anim14.setAnimation(R.raw.tickanim);
                anim14.playAnimation();
                anim14.loop(false);break;
            case 5:
                anim15.setAnimation(R.raw.tickanim);
                anim15.playAnimation();
                anim15.loop(false);break;
            case 6:
                anim16.setAnimation(R.raw.tickanim);
                anim16.playAnimation();
                anim16.loop(false);break;
            case 7:
                anim17.setAnimation(R.raw.tickanim);
                anim17.playAnimation();
                anim17.loop(false);break;
            case 8:
                anim18.setAnimation(R.raw.tickanim);
                anim18.playAnimation();
                anim18.loop(false);break;
            case 9:
                anim19.setAnimation(R.raw.tickanim);
                anim19.playAnimation();
                anim19.loop(false);break;
            case 10:
                anim20.setAnimation(R.raw.tickanim);
                anim20.playAnimation();
                anim20.loop(false);break;
        }
    }

    public void myanimManuWrong(){
        myPosition++;
        switch (myPosition){
            case 1:
                anim11.setAnimation(R.raw.wronganim);
                anim11.playAnimation();
                anim11.loop(false);break;
            case 2:
                anim12.setAnimation(R.raw.wronganim);
                anim12.playAnimation();
                anim12.loop(false);break;
            case 3:
                anim13.setAnimation(R.raw.wronganim);
                anim13.playAnimation();
                anim13.loop(false);break;
            case 4:
                anim14.setAnimation(R.raw.wronganim);
                anim14.playAnimation();
                anim14.loop(false);break;
            case 5:
                anim15.setAnimation(R.raw.wronganim);
                anim15.playAnimation();
                anim15.loop(false);break;
            case 6:
                anim16.setAnimation(R.raw.wronganim);
                anim16.playAnimation();
                anim16.loop(false);break;
            case 7:
                anim17.setAnimation(R.raw.wronganim);
                anim17.playAnimation();
                anim17.loop(false);break;
            case 8:
                anim18.setAnimation(R.raw.wronganim);
                anim18.playAnimation();
                anim18.loop(false);
                break;
            case 9:
                anim19.setAnimation(R.raw.wronganim);
                anim19.playAnimation();
                anim19.loop(false);break;
            case 10:
                anim20.setAnimation(R.raw.wronganim);
                anim20.playAnimation();
                anim20.loop(false);break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void enableOption(boolean enable){
        for (int i=0;i<4;i++) {
            linearLayout.getChildAt(i).setEnabled(enable);
            if (enable) {
                linearLayout.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
            }
        }
    }



    public void countDownTimerFun47(){   //Clock Algo
        countDownTimer47=new CountDownTimer(60000*3, 1000) {
            CardView Timer = (CardView) findViewById(R.id.cardView3);

            public void onTick(long millisUntilFinished) {



                milliHolder47= (int) millisUntilFinished;

                if(second==0){
                    minutes--;
                    minutestext="0"+String.valueOf(minutes);
                    second=59;
                    if(second<10){
                        secondtext="0"+String.valueOf(second);
                    }else{
                        secondtext=String.valueOf(second);
                    }
                    timerText.setText(" Timer "+minutestext+":"+secondtext+" ");

                }else{
                    minutestext="0"+String.valueOf(minutes);
                    if(second<10){
                        secondtext="0"+String.valueOf(second);
                    }else{
                        secondtext=String.valueOf(second);
                    }
                    timerText.setText(" Timer "+minutestext+":"+secondtext+" ");
                    second--;
                }

                if(minutes==0 && second<=15){

                    timerText.setTextColor(getResources().getColor(R.color.av_red));

                    //Continuous zoomIn - zoomOut
                    ObjectAnimator scaleX = ObjectAnimator.ofFloat(Timer, "scaleX", 0.9f, 1f);
                    ObjectAnimator scaleY = ObjectAnimator.ofFloat(Timer, "scaleY", 0.9f, 1f);

                    scaleX.setRepeatCount(ObjectAnimator.INFINITE);
                    scaleX.setRepeatMode(ObjectAnimator.REVERSE);

                    scaleY.setRepeatCount(ObjectAnimator.INFINITE);
                    scaleY.setRepeatMode(ObjectAnimator.REVERSE);

                    AnimatorSet scaleAnim = new AnimatorSet();
                    scaleAnim.setDuration(500);
                    scaleAnim.play(scaleX).with(scaleY);

                    scaleAnim.start();
                }

            }
            public void onFinish() {
                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                mInterstitialAd.setAdListener(new AdListener(){
                    public void onAdClosed(){
                        super.onAdClosed();
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        Toast.makeText(OneVsOneBOTNormalQuiz.this, "Time Over", Toast.LENGTH_SHORT).show();
                        Intent scoreIntent = new Intent(OneVsOneBOTNormalQuiz.this, OneVsOneBOTScoreActivity.class);
                        //    myRef.child("battleGround").child("onevsoneOnline").child(opponentUID).removeValue();
                        try{
                            //         myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").removeValue();
                        }catch (Exception e){

                        }
                        if(countDownTimerMine!=null){
                            countDownTimerMine.cancel();}
                        if(countDownTimer47!=null){
                            countDownTimer47.cancel();}
                        if(countDownTimer50!=null){
                            countDownTimer50.cancel();}
                        if(countDownTimer!=null){
                            countDownTimer.cancel();
                        }




                        // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Gate2", Toast.LENGTH_LONG).show();
                        try{
                            songActivity.songStop();
                        }catch (Exception e){

                        }
                        minman=2-minutes;
                        secman=59-second;
                        mine=" Time Taken : "+(2-minutes)+"min "+(59-second)+"sec ";

                        //   myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).child("isComplete").removeValue();
                        scoreIntent.putExtra("opponentUID",opponentUID);
                        scoreIntent.putExtra("opponentImageUrl",opponentimageUrl);
                        scoreIntent.putExtra("opponentUserName",opponentUsername);
                        scoreIntent.putExtra("mypropic",myProPicUrl);
                        scoreIntent.putExtra("myName",myName);
                        scoreIntent.putExtra("desider",1);
                        scoreIntent.putExtra("botTime",botTime);
                        scoreIntent.putExtra("score", score);
                        scoreIntent.putExtra("lifeline",lifelineSum);
                        scoreIntent.putExtra("minutes",minutes);
                        scoreIntent.putExtra("seconds",second);
                        scoreIntent.putExtra("minutestext",minutestext);
                        scoreIntent.putExtra("secondtext",secondtext);
                        scoreIntent.putExtra("milliholder",milliHolder);
                        scoreIntent.putExtra("category",category);
                        scoreIntent.putExtra("imageurl",imageurl);
                        scoreIntent.putExtra("mine",mine);
                        scoreIntent.putExtra("botCorrectAns",botCorrectAns);
                        scoreIntent.putExtra("actualmin",a1);
                        scoreIntent.putExtra("actualsec",a2);
                        scoreIntent.putExtra("minman",minman);
                        scoreIntent.putExtra("secman",secman);
                        scoreIntent.putExtra("isHostFinal", isHostFinal);
                        scoreIntent.putExtra("sumScore", sumScore);
                        scoreIntent.putExtra("oppoScoreCounter",oppoScoreCounter);
                        scoreIntent.putExtra("oppoWrongAnsCounter",oppoWrongAnsCounter);
                        scoreIntent.putIntegerArrayListExtra("myArr", (ArrayList<Integer>) arrlist);
                        scoreIntent.putIntegerArrayListExtra("oppoArr", (ArrayList<Integer>) arroppo);
                        startActivity(scoreIntent);
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        finish();

                    }

                });

                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    return ;
                }


                Toast.makeText(OneVsOneBOTNormalQuiz.this, "Time Over", Toast.LENGTH_SHORT).show();
                Intent scoreIntent = new Intent(OneVsOneBOTNormalQuiz.this, OneVsOneBOTScoreActivity.class);
                //    myRef.child("battleGround").child("onevsoneOnline").child(opponentUID).removeValue();
                try{
                    //         myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").removeValue();
                }catch (Exception e){

                }
                if(countDownTimerMine!=null){
                    countDownTimerMine.cancel();}
                if(countDownTimer47!=null){
                    countDownTimer47.cancel();}
                if(countDownTimer50!=null){
                    countDownTimer50.cancel();}
                if(countDownTimer!=null){
                    countDownTimer.cancel();
                }




                // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Gate2", Toast.LENGTH_LONG).show();
                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                minman=2-minutes;
                secman=59-second;
                mine=" Time Taken : "+(2-minutes)+"min "+(59-second)+"sec ";

                //   myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).child("isComplete").removeValue();
                scoreIntent.putExtra("opponentUID",opponentUID);
                scoreIntent.putExtra("opponentImageUrl",opponentimageUrl);
                scoreIntent.putExtra("opponentUserName",opponentUsername);
                scoreIntent.putExtra("mypropic",myProPicUrl);
                scoreIntent.putExtra("myName",myName);
                scoreIntent.putExtra("desider",1);
                scoreIntent.putExtra("botTime",botTime);
                scoreIntent.putExtra("score", score);
                scoreIntent.putExtra("lifeline",lifelineSum);
                scoreIntent.putExtra("minutes",minutes);
                scoreIntent.putExtra("seconds",second);
                scoreIntent.putExtra("minutestext",minutestext);
                scoreIntent.putExtra("secondtext",secondtext);
                scoreIntent.putExtra("milliholder",milliHolder);
                scoreIntent.putExtra("category",category);
                scoreIntent.putExtra("imageurl",imageurl);
                scoreIntent.putExtra("mine",mine);
                scoreIntent.putExtra("botCorrectAns",botCorrectAns);
                scoreIntent.putExtra("actualmin",a1);
                scoreIntent.putExtra("actualsec",a2);
                scoreIntent.putExtra("minman",minman);
                scoreIntent.putExtra("secman",secman);
                scoreIntent.putExtra("isHostFinal", isHostFinal);
                scoreIntent.putExtra("sumScore", sumScore);
                scoreIntent.putExtra("oppoScoreCounter",oppoScoreCounter);
                scoreIntent.putExtra("oppoWrongAnsCounter",oppoWrongAnsCounter);
                scoreIntent.putIntegerArrayListExtra("myArr", (ArrayList<Integer>) arrlist);
                scoreIntent.putIntegerArrayListExtra("oppoArr", (ArrayList<Integer>) arroppo);
                startActivity(scoreIntent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();


            }

        }.start();
    }


    public void countDownTimerFun(){   //Clock Algo
        countDownTimer=new CountDownTimer(60000*3, 1000) {


            public void onTick(long millisUntilFinished) {


                milliHolder=millisUntilFinished;


            }
            public void onFinish() {



            }

        }.start();
    }





    public void proPicFunction(){


        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageurl = (String) snapshot.getValue();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void expertAdviceImageManupulator(){     //Aakash changes in this functions are to be done
        Random rand = new Random();
        int num = rand.nextInt(11)+1;

        switch (num){
            case 1:               //If possible, avatars can match the facial descriptions
                expertImage.setBackgroundResource(R.drawable.expert1female);
                titleText.setText(" Dorjana Sirola: Highest woman scorer in World Quizzing Championship, Croatian linguist and anglicist! is Expert for the day");
                break;            //white complexion,short hair
            case 2:
                expertImage.setBackgroundResource(R.drawable.expert2male);
                titleText.setText("Dr.Neil deGrasse Tyson: Astrophysicist, Planetory scientist, Author and Science communicator! is Expert for the day");
                break;            //Dark complexion
            case 3:
                expertImage.setBackgroundResource(R.drawable.expert3male);
                titleText.setText("Kevin Ashman: Six times World Quizzing Championship winner and Five times British Quizing Champion! is Expert for the day");
                break;            //white complexion
            case 4:
                expertImage.setBackgroundResource(R.drawable.expert4male);
                titleText.setText("Derek O'Brian: Quiz Master, Indian politician and television personality! is Expert for the day");
                break;            //white complexion, spects
            case 5:
                expertImage.setBackgroundResource(R.drawable.expert5male);
                titleText.setText("Pat Gibson: Multiple World champion in quizzing, Software developer and professional Irish quizzer! is Expert for the day");
                break;             // white complexion, spectacles
            case 6:
                expertImage.setBackgroundResource(R.drawable.expert6female);
                titleText.setText("Elsie Kaufmann: Quiz mistress, Ghanaian academic and Biomedical engineer! is Expert for the day.");
                break;          //Dark complexion
            case 7:
                expertImage.setBackgroundResource(R.drawable.expert7male);
                titleText.setText("Olav Bjortomt: Four time World champion and English international quiz star player! is Expert for the day");
                break;          //White complexion
            case 8:
                expertImage.setBackgroundResource(R.drawable.expert8female);
                titleText.setText("Anne Hegerty: English quizzer and famous UK television personality! is Expert for the day");
                break;              //White complexion,short hair,fat face
            case 9:
                expertImage.setBackgroundResource(R.drawable.expert9female);
                titleText.setText("Seema Chari: Quiz mistress, author, anchor and knowledge media professional! is Expert for the day");
                break;          //curly hair
            case 10:
                expertImage.setBackgroundResource(R.drawable.expert10male);
                titleText.setText("Siddhartha Basu: Father of Indian television quizzing, producer-director and quiz show host! is Expert for the day");
                break;          //almost no hair,fair complexion
            case 11:
                expertImage.setBackgroundResource(R.drawable.expert11male);
                titleText.setText("Tom Trogh: Belgian quiz player and European quizzing champion! is Expert for the day");
                break;            //White complexion
            case 12:
                expertImage.setBackgroundResource(R.drawable.expert12male);
                titleText.setText("Ravi Avva: 2020 World Quizzing champion, Singaporean hailing from India and an Engineer! is Expert for the day");
                break;          //Fair complexion,spectacles

        }


    }

    public void userNameFunction(){
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userName=snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }











    public void animManupulation(int opponentAnswer, int binaryPosition){
        switch (binaryPosition){
            case 1:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim1.setAnimation(R.raw.tickanim);
                    anim1.playAnimation();
                    anim1.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim1.setAnimation(R.raw.wronganim);
                    anim1.playAnimation();
                    anim1.loop(false);
                }break;
            case 2:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim2.setAnimation(R.raw.tickanim);
                    anim2.playAnimation();
                    anim2.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim2.setAnimation(R.raw.wronganim);
                    anim2.playAnimation();
                    anim2.loop(false);
                }break;
            case 3:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim3.setAnimation(R.raw.tickanim);
                    anim3.playAnimation();
                    anim3.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim3.setAnimation(R.raw.wronganim);
                    anim3.playAnimation();
                    anim3.loop(false);
                }break;
            case 4:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim4.setAnimation(R.raw.tickanim);
                    anim4.playAnimation();
                    anim4.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim4.setAnimation(R.raw.wronganim);
                    anim4.playAnimation();
                    anim4.loop(false);
                }break;
            case 5:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim5.setAnimation(R.raw.tickanim);
                    anim5.playAnimation();
                    anim5.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim5.setAnimation(R.raw.wronganim);
                    anim5.playAnimation();
                    anim5.loop(false);
                }break;
            case 6:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim6.setAnimation(R.raw.tickanim);
                    anim6.playAnimation();
                    anim6.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim6.setAnimation(R.raw.wronganim);
                    anim6.playAnimation();
                    anim6.loop(false);
                }break;
            case 7:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim7.setAnimation(R.raw.tickanim);
                    anim7.playAnimation();
                    anim7.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim7.setAnimation(R.raw.wronganim);
                    anim7.playAnimation();
                    anim7.loop(false);
                }break;
            case 8:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim8.setAnimation(R.raw.tickanim);
                    anim8.playAnimation();
                    anim8.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim8.setAnimation(R.raw.wronganim);
                    anim8.playAnimation();
                    anim8.loop(false);
                }break;
            case 9:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim9.setAnimation(R.raw.tickanim);
                    anim9.playAnimation();
                    anim9.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim9.setAnimation(R.raw.wronganim);
                    anim9.playAnimation();
                    anim9.loop(false);
                }break;
            case 10:
                if(opponentAnswer ==1){
                    arroppo.add(1);
                    oppoScoreCounter++;
                    anim10.setAnimation(R.raw.tickanim);
                    anim10.playAnimation();
                    anim10.loop(false);
                }else{
                    arroppo.add(2);
                    oppoWrongAnsCounter++;
                    anim10.setAnimation(R.raw.wronganim);
                    anim10.playAnimation();
                    anim10.loop(false);
                }break;
        }
        // this.binaryPosition++;
    }

    public void waitingFunction(){

        if(binaryPosition>10){
            try{
                songActivity.songStop();
            }catch (Exception e){

            }
            mInterstitialAd.setAdListener(new AdListener(){
                public void onAdClosed(){
                    super.onAdClosed();
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());

                    Intent scoreIntent = new Intent(OneVsOneBOTNormalQuiz.this, OneVsOneBOTScoreActivity.class);
                    //    myRef.child("battleGround").child("onevsoneOnline").child(opponentUID).removeValue();
                    try{
                        //         myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").removeValue();
                    }catch (Exception e){

                    }
                    if(countDownTimerMine!=null){
                        countDownTimerMine.cancel();}
                    if(countDownTimer47!=null){
                        countDownTimer47.cancel();}
                    if(countDownTimer50!=null){
                        countDownTimer50.cancel();}
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }



                    // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Gate2", Toast.LENGTH_LONG).show();
                    try{
                        songActivity.songStop();
                    }catch (Exception e){

                    }
                    minman=2-minutes;
                    secman=59-second;
                    mine=" Time Taken : "+(2-minutes)+"min "+(59-second)+"sec ";

                    //   myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).child("isComplete").removeValue();
                    scoreIntent.putExtra("opponentUID",opponentUID);
                    scoreIntent.putExtra("opponentImageUrl",opponentimageUrl);
                    scoreIntent.putExtra("opponentUserName",opponentUsername);
                    scoreIntent.putExtra("mypropic",myProPicUrl);
                    scoreIntent.putExtra("myName",myName);
                    scoreIntent.putExtra("score", score);
                    scoreIntent.putExtra("lifeline",lifelineSum);
                    scoreIntent.putExtra("minutes",minutes);
                    scoreIntent.putExtra("seconds",second);
                    scoreIntent.putExtra("minutestext",minutestext); scoreIntent.putExtra("sumScore", sumScore);
                    scoreIntent.putExtra("secondtext",secondtext);
                    scoreIntent.putExtra("milliholder",milliHolder);
                    scoreIntent.putExtra("category",category);
                    scoreIntent.putExtra("imageurl",imageurl);
                    scoreIntent.putExtra("mine",mine);
                    scoreIntent.putExtra("botTime",botTime);
                    scoreIntent.putExtra("desider",1);
                    scoreIntent.putExtra("botCorrectAns",botCorrectAns);
                    scoreIntent.putExtra("actualmin",a1);
                    scoreIntent.putExtra("actualsec",a2);
                    scoreIntent.putExtra("minman",minman);
                    scoreIntent.putExtra("secman",secman);
                    scoreIntent.putExtra("isHostFinal", isHostFinal);
                    scoreIntent.putExtra("oppoScoreCounter",oppoScoreCounter);
                    scoreIntent.putExtra("oppoWrongAnsCounter",oppoWrongAnsCounter);
                    scoreIntent.putIntegerArrayListExtra("myArr", (ArrayList<Integer>) arrlist);
                    scoreIntent.putIntegerArrayListExtra("oppoArr", (ArrayList<Integer>) arroppo);
                    startActivity(scoreIntent);
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }

            });

            if(mInterstitialAd.isLoaded()){
                mInterstitialAd.show();
                return ;
            }


                  Intent scoreIntent = new Intent(OneVsOneBOTNormalQuiz.this, OneVsOneBOTScoreActivity.class);
                            //    myRef.child("battleGround").child("onevsoneOnline").child(opponentUID).removeValue();
                            try{
                                //         myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").removeValue();
                            }catch (Exception e){

                            }
                            if(countDownTimerMine!=null){
                                countDownTimerMine.cancel();}
                            if(countDownTimer47!=null){
                                countDownTimer47.cancel();}
                            if(countDownTimer50!=null){
                                countDownTimer50.cancel();}
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }



                            // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Gate2", Toast.LENGTH_LONG).show();
                            try{
                                songActivity.songStop();
                            }catch (Exception e){

                            }
                            minman=2-minutes;
                            secman=59-second;
                            mine=" Time Taken : "+(2-minutes)+"min "+(59-second)+"sec ";

                            //   myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).child("isComplete").removeValue();
                            scoreIntent.putExtra("opponentUID",opponentUID);
                            scoreIntent.putExtra("opponentImageUrl",opponentimageUrl);
                            scoreIntent.putExtra("opponentUserName",opponentUsername);
                            scoreIntent.putExtra("mypropic",myProPicUrl);
                            scoreIntent.putExtra("myName",myName);
                            scoreIntent.putExtra("score", score);
                            scoreIntent.putExtra("lifeline",lifelineSum);
                            scoreIntent.putExtra("minutes",minutes);
                            scoreIntent.putExtra("seconds",second);
                            scoreIntent.putExtra("minutestext",minutestext); scoreIntent.putExtra("sumScore", sumScore);
                            scoreIntent.putExtra("secondtext",secondtext);
                            scoreIntent.putExtra("milliholder",milliHolder);
                            scoreIntent.putExtra("category",category);
                            scoreIntent.putExtra("imageurl",imageurl);
                            scoreIntent.putExtra("mine",mine);
            scoreIntent.putExtra("botTime",botTime);
            scoreIntent.putExtra("desider",1);
            scoreIntent.putExtra("botCorrectAns",botCorrectAns);
                            scoreIntent.putExtra("actualmin",a1);
                            scoreIntent.putExtra("actualsec",a2);
                            scoreIntent.putExtra("minman",minman);
                            scoreIntent.putExtra("secman",secman);
                            scoreIntent.putExtra("isHostFinal", isHostFinal);
                            scoreIntent.putExtra("oppoScoreCounter",oppoScoreCounter);
                            scoreIntent.putExtra("oppoWrongAnsCounter",oppoWrongAnsCounter);
                            scoreIntent.putIntegerArrayListExtra("myArr", (ArrayList<Integer>) arrlist);
                            scoreIntent.putIntegerArrayListExtra("oppoArr", (ArrayList<Integer>) arroppo);
                            startActivity(scoreIntent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();

        }else{
            dialogBoxManupulation();
        }

    }



    public void dialogBoxManupulation(){
        builder.setView(view1);
        builder.setCancelable(false);
        propic=((ImageView) view1.findViewById(R.id.exam_img_id));
        yourName=((TextView) view1.findViewById(R.id.userName123));
        ratio=((TextView) view1.findViewById(R.id.ratio));
        timeTaken=((TextView) view1.findViewById(R.id.timeTaken));
        accuracy=((TextView) view1.findViewById(R.id.accuracy));
        lifeLines=((TextView) view1.findViewById(R.id.lifeLines));

        for(int i=1;i<=3;i++){
            dataForHorizontalSlide();
        }

        finisherNum=1;

        AdView mAdView = view1.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
    //    mAdView.loadAd(adRequest);

        ImageView cancelButton=(ImageView) view1.findViewById(R.id.cancelButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelDialogFunction(1);
            }
        });

        Glide.with(getBaseContext())
                .load(myProPicUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(propic);

        yourName.setText(myName);
        int wr=10-score;
        minman=2-minutes;
        secman=59-second;
        a1=minman;
        a2=secman;
        final int kalimin=2-minutes;
        final int kalisec=59-second;
        kong= (int) milliHolder;
        mine=" Time Taken : "+(a1)+"min "+(a2)+"sec ";
        ratio.setText(" Correct/Wrong : "+score+"/"+wr+" ");
        timeTaken.setText(mine);
        int acc=score*10;
        accuracy.setText(" Accuracy : "+acc+"% ");
        lifeLines.setText(" Total Life-Lines Used : "+lifelineSum+"/4 ");
        bull=1;

        final AlertDialog alertDialog=builder.create();

        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }





                cCompany=new CountDownTimer(1000*180,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if(binaryPosition>10){
                            try{
                                songActivity.songStop();
                            }catch (Exception e){

                            }
                            if(cCompany!=null){
                                cCompany.cancel();
                            }
                            mInterstitialAd.setAdListener(new AdListener(){
                                public void onAdClosed(){
                                    super.onAdClosed();
                                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                                    Intent scoreIntent = new Intent(OneVsOneBOTNormalQuiz.this, OneVsOneBOTScoreActivity.class);
                                    //    myRef.child("battleGround").child("onevsoneOnline").child(opponentUID).removeValue();
                                    try{
                                        //         myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").removeValue();
                                    }catch (Exception e){

                                    }
                                    if(countDownTimerMine!=null){
                                        countDownTimerMine.cancel();}
                                    if(countDownTimer47!=null){
                                        countDownTimer47.cancel();}
                                    if(countDownTimer50!=null){
                                        countDownTimer50.cancel();}
                                    if(countDownTimer!=null){
                                        countDownTimer.cancel();
                                    }
                                    minman=2-minutes;
                                    secman=59-second;

                                    mine=" Time Taken : "+(2-minutes)+"min "+(59-second)+"sec ";



                                    // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Gate1", Toast.LENGTH_LONG).show();

                                    try{
                                        songActivity.songStop();
                                    }catch (Exception e){

                                    }
                                    //   myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).child("isComplete").removeValue();
                                    scoreIntent.putExtra("opponentUID",opponentUID);
                                    scoreIntent.putExtra("opponentImageUrl",opponentimageUrl);
                                    scoreIntent.putExtra("opponentUserName",opponentUsername);
                                    scoreIntent.putExtra("mypropic",myProPicUrl);
                                    scoreIntent.putExtra("myName",myName);
                                    scoreIntent.putExtra("score", score);
                                    scoreIntent.putExtra("desider",1);
                                    scoreIntent.putExtra("lifeline",lifelineSum);
                                    scoreIntent.putExtra("minutes",minutes);
                                    scoreIntent.putExtra("seconds",second);
                                    scoreIntent.putExtra("minutestext",minutestext);
                                    scoreIntent.putExtra("secondtext",secondtext);
                                    scoreIntent.putExtra("milliholder",milliHolder);
                                    scoreIntent.putExtra("category",category);
                                    scoreIntent.putExtra("imageurl",imageurl);
                                    scoreIntent.putExtra("mine",mine);
                                    scoreIntent.putExtra("botTime",botTime);
                                    scoreIntent.putExtra("actualmin",a1);
                                    scoreIntent.putExtra("actualsec",a2);
                                    scoreIntent.putExtra("minman",kalimin);
                                    scoreIntent.putExtra("sumScore", sumScore);
                                    scoreIntent.putExtra("botCorrectAns",botCorrectAns);
                                    scoreIntent.putExtra("secman",kalisec);
                                    scoreIntent.putExtra("parsel",1);
                                    scoreIntent.putExtra("isHostFinal", isHostFinal);
                                    scoreIntent.putExtra("oppoScoreCounter",oppoScoreCounter);
                                    scoreIntent.putExtra("oppoWrongAnsCounter",oppoWrongAnsCounter);
                                    scoreIntent.putIntegerArrayListExtra("myArr", (ArrayList<Integer>) arrlist);
                                    scoreIntent.putIntegerArrayListExtra("oppoArr", (ArrayList<Integer>) arroppo);
                                    startActivity(scoreIntent);
                                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                    finish();
                                }



                            });

                            if(mInterstitialAd.isLoaded()){
                                mInterstitialAd.show();
                                return ;
                            }




                            Intent scoreIntent = new Intent(OneVsOneBOTNormalQuiz.this, OneVsOneBOTScoreActivity.class);
                            //    myRef.child("battleGround").child("onevsoneOnline").child(opponentUID).removeValue();
                            try{
                                //         myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").removeValue();
                            }catch (Exception e){

                            }
                            if(countDownTimerMine!=null){
                                countDownTimerMine.cancel();}
                            if(countDownTimer47!=null){
                                countDownTimer47.cancel();}
                            if(countDownTimer50!=null){
                                countDownTimer50.cancel();}
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            minman=2-minutes;
                            secman=59-second;

                            mine=" Time Taken : "+(2-minutes)+"min "+(59-second)+"sec ";



                            // Toast.makeText(OneVsOneBOTNormalQuiz.this, "Gate1", Toast.LENGTH_LONG).show();

                            try{
                                songActivity.songStop();
                            }catch (Exception e){

                            }
                            //   myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).child("isComplete").removeValue();
                            scoreIntent.putExtra("opponentUID",opponentUID);
                            scoreIntent.putExtra("opponentImageUrl",opponentimageUrl);
                            scoreIntent.putExtra("opponentUserName",opponentUsername);
                            scoreIntent.putExtra("mypropic",myProPicUrl);
                            scoreIntent.putExtra("myName",myName);
                            scoreIntent.putExtra("score", score);
                            scoreIntent.putExtra("desider",1);
                            scoreIntent.putExtra("lifeline",lifelineSum);
                            scoreIntent.putExtra("minutes",minutes);
                            scoreIntent.putExtra("seconds",second);
                            scoreIntent.putExtra("minutestext",minutestext);
                            scoreIntent.putExtra("secondtext",secondtext);
                            scoreIntent.putExtra("milliholder",milliHolder);
                            scoreIntent.putExtra("category",category);
                            scoreIntent.putExtra("imageurl",imageurl);
                            scoreIntent.putExtra("mine",mine);
                            scoreIntent.putExtra("botTime",botTime);
                            scoreIntent.putExtra("actualmin",a1);
                            scoreIntent.putExtra("actualsec",a2);
                            scoreIntent.putExtra("minman",kalimin);
                            scoreIntent.putExtra("sumScore", sumScore);
                            scoreIntent.putExtra("botCorrectAns",botCorrectAns);
                            scoreIntent.putExtra("secman",kalisec);
                            scoreIntent.putExtra("parsel",1);
                            scoreIntent.putExtra("isHostFinal", isHostFinal);
                            scoreIntent.putExtra("oppoScoreCounter",oppoScoreCounter);
                            scoreIntent.putExtra("oppoWrongAnsCounter",oppoWrongAnsCounter);
                            scoreIntent.putIntegerArrayListExtra("myArr", (ArrayList<Integer>) arrlist);
                            scoreIntent.putIntegerArrayListExtra("oppoArr", (ArrayList<Integer>) arroppo);
                            startActivity(scoreIntent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }

                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();







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


    public void dataForHorizontalSlide(){

        // create instance of Random class
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
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list4.add(dataSnapshot1.getValue(mainMenuFactsHolder.class));
                    cou++;
                }

                if(cou==3){
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    AdapterManupulation();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(OneVsOneBOTNormalQuiz.this,"Facts Data Can't be Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void AdapterManupulation(){
        sliderAdapter=new onevsoneonlineAdapter(OneVsOneBOTNormalQuiz.this,list4);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListner);
        sliderAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        dotLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.lightgrey));
            dotLayout.addView(mDots[i]);

        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void onBackPressed() {

        cancelDialogFunction(2);


    }


    public void cancelDialogFunction(int i){
        AlertDialog.Builder builderRemove=new AlertDialog.Builder(OneVsOneBOTNormalQuiz.this,R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(OneVsOneBOTNormalQuiz.this).inflate(R.layout.quit_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderRemove.setView(viewRemove1);
        builderRemove.setCancelable(false);
        Button yesButton=(Button) viewRemove1.findViewById(R.id.buttonYes);
        Button noButton=(Button) viewRemove1.findViewById(R.id.buttonNo);


        final AlertDialog alertDialog=builderRemove.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }


        if(i==1){
            TextView textTitle=(TextView) viewRemove1.findViewById(R.id.textTitle);
            textTitle.setText("If You Quit Now You Will Not Be Able To See Your Score More Over Opponent Will Also Get Disconnect\nYou Really Want To Quit?");
        }


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });


                        if(countDownTimerMine!=null){
                            countDownTimerMine.cancel();}
                        if(countDownTimer47!=null){
                            countDownTimer47.cancel();}
                        if(countDownTimer50!=null){
                            countDownTimer50.cancel();}


                        try{
                            songActivity.songStop();
                        }catch (Exception e){

                        }

                        Intent intent=new Intent(OneVsOneBOTNormalQuiz.this,mainMenuActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        finish();



            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTNormalQuiz.this, R.raw.finalbuttonmusic);
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


}