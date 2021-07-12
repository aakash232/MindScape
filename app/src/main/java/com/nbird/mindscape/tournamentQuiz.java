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
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
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
import java.util.Random;

public class tournamentQuiz extends AppCompatActivity {
    LottieAnimationView anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;
    LottieAnimationView anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20;
    LottieAnimationView anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30;
    LottieAnimationView anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40;
    LottieAnimationView animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10;
    LottieAnimationView animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20;
    LottieAnimationView animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30;
    LottieAnimationView animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40;
    TextView opponentName,opponentName2,opponentName3,opponentName4;
    ImageView opponentPic,opponentPic2,opponentPic3,opponentPic4;
    Dialog loadingDialog;
    private List<questionHolder> list;
    long milliHolder;
    TextView questionTextView,scoreBoard;
    Button option1,option2,option3,option4,nextButton;
    LinearLayout linearLayout;
    int num=0;
    int expertnum=0;
    int swapnum=0;
    int audiencenum=0;
    int category;
    int manupulator=0;
    int manupulator1=0;
    int fiftyfiftynum=0;
    private int score=0;
    TextView timerText;
    TextView titleText;
    int yo1;
    int yo2;
    int yo3;
    int yo4;
    String userName;
    int selectNum;
    int lifelineSum=0;
    private int position=0;
    CountDownTimer countDownTimer;
    CardView audienceLL,expertAdviceLL,fiftyfiftyLL,swapTheQuestionLL;
    LinearLayout linearLayoutexpert,linearLayoutAudience,linearLayoutFiftyFifty,linearLayoutSwap;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private int count;
    int minutes=2;
    int second=59;
    String minutestext;
    String secondtext;
    ImageView expertImage;
    String myName,myImageUrl,hostUid,hostName,hostImageUrl,name2String,name3String,name4String,image2Url,image3Url,image4Url;
    int playerNum;
    List<Integer> arrlist12345;
    int roomCode;
    int questionCounter=1;
    int playerACounter=1,playerBCounter=1,playerCCounter=1;
    int questionNum,timerNum;
    int milliHolder47;
    int counter=0;
    int numberOfPlayers;
    ValueEventListener listner;
    int oppoStatus=5;
    int setNumber;
    private InterstitialAd mInterstitialAd;

    private void loadAds(){

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitialAd_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
    songActivity songActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_quiz);

        loadAds();
         songActivity=new songActivity(this);
        songActivity.startMusic();

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
        anim21=(LottieAnimationView) findViewById(R.id.anim21);
        anim22=(LottieAnimationView) findViewById(R.id.anim22);
        anim23=(LottieAnimationView) findViewById(R.id.anim23);
        anim24=(LottieAnimationView) findViewById(R.id.anim24);
        anim25=(LottieAnimationView) findViewById(R.id.anim25);
        anim26=(LottieAnimationView) findViewById(R.id.anim26);
        anim27=(LottieAnimationView) findViewById(R.id.anim27);
        anim28=(LottieAnimationView) findViewById(R.id.anim28);
        anim29=(LottieAnimationView) findViewById(R.id.anim29);
        anim30=(LottieAnimationView) findViewById(R.id.anim30);
        anim31=(LottieAnimationView) findViewById(R.id.anim31);
        anim32=(LottieAnimationView) findViewById(R.id.anim32);
        anim33=(LottieAnimationView) findViewById(R.id.anim33);
        anim34=(LottieAnimationView) findViewById(R.id.anim34);
        anim35=(LottieAnimationView) findViewById(R.id.anim35);
        anim36=(LottieAnimationView) findViewById(R.id.anim36);
        anim37=(LottieAnimationView) findViewById(R.id.anim37);
        anim38=(LottieAnimationView) findViewById(R.id.anim38);
        anim39=(LottieAnimationView) findViewById(R.id.anim39);
        anim40=(LottieAnimationView) findViewById(R.id.anim40);

        animz1=(LottieAnimationView) findViewById(R.id.animz1);
        animz2=(LottieAnimationView) findViewById(R.id.animz2);
        animz3=(LottieAnimationView) findViewById(R.id.animz3);
        animz4=(LottieAnimationView) findViewById(R.id.animz4);
        animz5=(LottieAnimationView) findViewById(R.id.animz5);
        animz6=(LottieAnimationView) findViewById(R.id.animz6);
        animz7=(LottieAnimationView) findViewById(R.id.animz7);
        animz8=(LottieAnimationView) findViewById(R.id.animz8);
        animz9=(LottieAnimationView) findViewById(R.id.animz9);
        animz10=(LottieAnimationView) findViewById(R.id.animz10);
        animz11=(LottieAnimationView) findViewById(R.id.animz11);
        animz12=(LottieAnimationView) findViewById(R.id.animz12);
        animz13=(LottieAnimationView) findViewById(R.id.animz13);
        animz14=(LottieAnimationView) findViewById(R.id.animz14);
        animz15=(LottieAnimationView) findViewById(R.id.animz15);
        animz16=(LottieAnimationView) findViewById(R.id.animz16);
        animz17=(LottieAnimationView) findViewById(R.id.animz17);
        animz18=(LottieAnimationView) findViewById(R.id.animz18);
        animz19=(LottieAnimationView) findViewById(R.id.animz19);
        animz20=(LottieAnimationView) findViewById(R.id.animz20);
        animz21=(LottieAnimationView) findViewById(R.id.animz21);
        animz22=(LottieAnimationView) findViewById(R.id.animz22);
        animz23=(LottieAnimationView) findViewById(R.id.animz23);
        animz24=(LottieAnimationView) findViewById(R.id.animz24);
        animz25=(LottieAnimationView) findViewById(R.id.animz25);
        animz26=(LottieAnimationView) findViewById(R.id.animz26);
        animz27=(LottieAnimationView) findViewById(R.id.animz27);
        animz28=(LottieAnimationView) findViewById(R.id.animz28);
        animz29=(LottieAnimationView) findViewById(R.id.animz29);
        animz30=(LottieAnimationView) findViewById(R.id.animz30);
        animz31=(LottieAnimationView) findViewById(R.id.animz31);
        animz32=(LottieAnimationView) findViewById(R.id.animz32);
        animz33=(LottieAnimationView) findViewById(R.id.animz33);
        animz34=(LottieAnimationView) findViewById(R.id.animz34);
        animz35=(LottieAnimationView) findViewById(R.id.animz35);
        animz36=(LottieAnimationView) findViewById(R.id.animz36);
        animz37=(LottieAnimationView) findViewById(R.id.animz37);
        animz38=(LottieAnimationView) findViewById(R.id.animz38);
        animz39=(LottieAnimationView) findViewById(R.id.animz39);
        animz40=(LottieAnimationView) findViewById(R.id.animz40);


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
        linearLayoutSwap=(LinearLayout) findViewById(R.id.linearLayoutSwap) ;

        opponentName=(TextView) findViewById(R.id.opponentName);
        opponentName2=(TextView) findViewById(R.id.opponentName2);
        opponentName3=(TextView) findViewById(R.id.opponentName3);
        opponentName4=(TextView) findViewById(R.id.opponentName4);

        opponentPic=(ImageView) findViewById(R.id.opponentPic);
        opponentPic2=(ImageView) findViewById(R.id.opponentPic2);
        opponentPic3=(ImageView) findViewById(R.id.opponentPic3);
        opponentPic4=(ImageView) findViewById(R.id.opponentPic4);

        myName=getIntent().getStringExtra("myName");
        myImageUrl=getIntent().getStringExtra("myImageUrl");
        hostUid=getIntent().getStringExtra("hostUID");
        hostName=getIntent().getStringExtra("hostName");
        hostImageUrl=getIntent().getStringExtra("hostImageUrl");
        name2String=getIntent().getStringExtra("player2Name");
        name3String=getIntent().getStringExtra("player3Name");
        name4String=getIntent().getStringExtra("player4Name");
        image2Url=getIntent().getStringExtra("player2ImageUrl");
        image3Url=getIntent().getStringExtra("player3ImageUrl");
        image4Url=getIntent().getStringExtra("player4ImageUrl");
        roomCode=getIntent().getIntExtra("roomCode",0);
        playerNum=getIntent().getIntExtra("playerNumber",1);
        arrlist12345=getIntent().getIntegerArrayListExtra("arrList12345");
        questionNum=getIntent().getIntExtra("questionNum",0);
        timerNum=getIntent().getIntExtra("timerNum",0);
        numberOfPlayers=getIntent().getIntExtra("numberOfPlayers",1);


        if(playerNum==2||playerNum==3||playerNum==4){
            opponentRemovedKnower();
        }


        if(playerNum==1){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("gameStarter").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });
        }

        dataSetterOfIntent();

        if(timerNum==0){
            minutes=2;
        }else if(timerNum==1){
            minutes=4;
            second=29;
        }else if(timerNum==2){
            minutes=5;
        }


        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);

        list=new ArrayList<>();
        category=getIntent().getIntExtra("category",1);

        try{
            loadingDialog.show();
        }catch (Exception e){

        }



        if(questionNum==0){
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
        }else if(questionNum==1){
            for(int i=0;i<17;i++){
                try{
                    // create instance of Random class
                    fireBaseData(arrlist12345.get(i));
                }catch (Exception e){
                    Random rand1 = new Random();
                    setNumber=rand1.nextInt(6326)+1;//NEED TO CHANGE HERE
                    fireBaseData(setNumber);
                }

            }
        }else{
            for(int i=0;i<22;i++){
                try{
                    // create instance of Random class
                    fireBaseData(arrlist12345.get(i));
                }catch (Exception e){
                    Random rand1 = new Random();
                    setNumber=rand1.nextInt(6326)+1;  //NEED TO CHANGE HERE
                    //NEED TO CHANGE HERE
                    fireBaseData(setNumber);
                }

            }
        }



        switch (playerNum){
            case 1:
                switch (questionNum){
                    case 0:
                        opponentAnswersReceivingPlayer1("player2Answer","player3Answer","player4Answer",10);break;
                    case 1:
                        opponentAnswersReceivingPlayer1("player2Answer","player3Answer","player4Answer", 15);break;
                    case 2:
                        opponentAnswersReceivingPlayer1("player2Answer","player3Answer","player4Answer", 20);break;
                }break;

            case 2:
                switch (questionNum){
                    case 0:
                        opponentAnswersReceivingPlayer2("player1Answer","player3Answer","player4Answer",10);break;
                    case 1:
                        opponentAnswersReceivingPlayer2("player1Answer","player3Answer","player4Answer",15);break;
                    case 2:
                        opponentAnswersReceivingPlayer2("player1Answer","player3Answer","player4Answer",20);break;
                }break;



            case 3:
                switch (questionNum){
                    case 0:
                        opponentAnswersReceivingPlayer3("player1Answer","player2Answer","player4Answer",10);break;
                    case 1:
                        opponentAnswersReceivingPlayer3("player1Answer","player2Answer","player4Answer",15);break;
                    case 2:
                        opponentAnswersReceivingPlayer3("player1Answer","player2Answer","player4Answer",20);break;
                }break;

            case 4:
                switch (questionNum){
                    case 0:
                        opponentAnswersReceivingPlayer4("player1Answer","player2Answer","player3Answer",10);break;
                    case 1:
                        opponentAnswersReceivingPlayer4("player1Answer","player2Answer","player3Answer",15);break;
                    case 2:
                        opponentAnswersReceivingPlayer4("player1Answer","player2Answer","player3Answer",20);break;
                }break;

        }

        if(timerNum==0){
            countDownTimerFun(1000*60*3);
        }else if(timerNum==1){
            countDownTimerFun(1000 * 270);
        }else if(timerNum==2){
            countDownTimerFun(1000 * 60 * 6);
        }



        fiftyfiftyLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fiftyfiftynum==0) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelinemusic);
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
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelineused);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Oops! You Have Used Your FIFTY-FIFTY Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


                    final AlertDialog alertDialog=builder.create();
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



            }
        });

        audienceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(audiencenum==0) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelinemusic);
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
                            yo2 = rand2.nextInt(100 - yo1);
                            yo3 = rand2.nextInt(100 - yo1 - yo2);
                            yo4 = 100 - yo1 - yo2 - yo3;
                            break;
                        case 2:
                            yo2 = setMax;
                            yo1 = rand2.nextInt(100 - yo2);
                            yo3 = rand2.nextInt(100 - yo2 - yo1);
                            yo4 = 100 - yo2 - yo1 - yo3;
                            break;
                        case 3:
                            yo3 = setMax;
                            yo2 = rand2.nextInt(100 - yo3);
                            yo1 = rand2.nextInt(100 - yo3 - yo2);
                            yo4 = 100 - yo3 - yo2 - yo1;
                            break;
                        case 4:
                            yo4 = setMax;
                            yo2 = rand2.nextInt(100 - yo4);
                            yo1 = rand2.nextInt(100 - yo4 - yo2);
                            yo3 = 100 - yo4 - yo2 - yo1;
                            break;
                    }


                    AlertDialog.Builder builder = new AlertDialog.Builder(tournamentQuiz.this, R.style.AlertDialogTheme);

                    final View view1 = LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.audience_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Earn 10 Paper Notes By Entering Your Friends Referral Code!");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    BarChart barChart = ((BarChart) view1.findViewById(R.id.barChart));


                    final ArrayList<BarEntry> visitors = new ArrayList<>();
                    visitors.add(new BarEntry(1, yo1));
                    visitors.add(new BarEntry(2, yo2));
                    visitors.add(new BarEntry(3, yo3));
                    visitors.add(new BarEntry(4, yo4));


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
                    alertDialog.show();

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog.dismiss();
                        }
                    });
                }else{
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelineused);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Oops! You Have Used Your AUDIENCE POLL Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


                    final AlertDialog alertDialog=builder.create();
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
            }
        });



        swapTheQuestionLL.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if(swapnum==0){
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelinemusic);
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
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelineused);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Oops! You Have Used Your SWAP Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


                    final AlertDialog alertDialog=builder.create();
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

            }
        });





        expertAdviceLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(expertnum==0){
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelinemusic);
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


                    AlertDialog.Builder builder=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.expertadvicelayout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    titleText=((TextView) view1.findViewById(R.id.textTitle));
                    ((TextView) view1.findViewById(R.id.textMessage)).setText(myName+" I Think It's : \n'"+answerByExpert+"'");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    expertImage=((ImageView) view1.findViewById(R.id.imageIcon));
                    expertAdviceImageManupulator();


                    final AlertDialog alertDialog=builder.create();
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


                }else{
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.lifelineused);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    AlertDialog.Builder builder=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Oops! You Have Used Your EXPERT ADVICE Life Line Once.");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    expertAdviceImageManupulator();

                    final AlertDialog alertDialog=builder.create();
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
            }
        });
    }

    public void answerStatus(String playerAnswerStatusName,int i) {
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswerStatusName).child(String.valueOf(questionCounter)).setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
        questionCounter++;
    }

    public void playerCorrectAnim(int counterNum,LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10,LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20){
        switch (counterNum){
            case 1:
                a1.setAnimation(R.raw.tickanim);
                a1.playAnimation();
                a1.loop(false);break;
            case 2:
                a2.setAnimation(R.raw.tickanim);
                a2.playAnimation();
                a2.loop(false);break;
            case 3:
                a3.setAnimation(R.raw.tickanim);
                a3.playAnimation();
                a3.loop(false);break;
            case 4:
                a4.setAnimation(R.raw.tickanim);
                a4.playAnimation();
                a4.loop(false);break;
            case 5:
                a5.setAnimation(R.raw.tickanim);
                a5.playAnimation();
                a5.loop(false);break;
            case 6:
                a6.setAnimation(R.raw.tickanim);
                a6.playAnimation();
                a6.loop(false);break;
            case 7:
                a7.setAnimation(R.raw.tickanim);
                a7.playAnimation();
                a7.loop(false);break;
            case 8:
                a8.setAnimation(R.raw.tickanim);
                a8.playAnimation();
                a8.loop(false);break;
            case 9:
                a9.setAnimation(R.raw.tickanim);
                a9.playAnimation();
                a9.loop(false);break;
            case 10:
                a10.setAnimation(R.raw.tickanim);
                a10.playAnimation();
                a10.loop(false);break;
            case 11:
                a11.setAnimation(R.raw.tickanim);
                a11.playAnimation();
                a11.loop(false);break;
            case 12:
                a12.setAnimation(R.raw.tickanim);
                a12.playAnimation();
                a12.loop(false);break;
            case 13:
                a13.setAnimation(R.raw.tickanim);
                a13.playAnimation();
                a13.loop(false);break;
            case 14:
                a14.setAnimation(R.raw.tickanim);
                a14.playAnimation();
                a14.loop(false);break;
            case 15:
                a15.setAnimation(R.raw.tickanim);
                a15.playAnimation();
                a15.loop(false);break;
            case 16:
                a16.setAnimation(R.raw.tickanim);
                a16.playAnimation();
                a16.loop(false);break;
            case 17:
                a17.setAnimation(R.raw.tickanim);
                a17.playAnimation();
                a17.loop(false);break;
            case 18:
                a18.setAnimation(R.raw.tickanim);
                a18.playAnimation();
                a18.loop(false);break;
            case 19:
                a19.setAnimation(R.raw.tickanim);
                a19.playAnimation();
                a19.loop(false);break;
            case 20:
                a20.setAnimation(R.raw.tickanim);
                a20.playAnimation();
                a20.loop(false);break;
        }
    }

    public void playerWrongAnim(int counterNum, LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10,LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20){
        switch (counterNum){
            case 1:
                a1.setAnimation(R.raw.wronganim);
                a1.playAnimation();
                a1.loop(false);break;
            case 2:
                a2.setAnimation(R.raw.wronganim);
                a2.playAnimation();
                a2.loop(false);break;
            case 3:
                a3.setAnimation(R.raw.wronganim);
                a3.playAnimation();
                a3.loop(false);break;
            case 4:
                a4.setAnimation(R.raw.wronganim);
                a4.playAnimation();
                a4.loop(false);break;
            case 5:
                a5.setAnimation(R.raw.wronganim);
                a5.playAnimation();
                a5.loop(false);break;
            case 6:
                a6.setAnimation(R.raw.wronganim);
                a6.playAnimation();
                a6.loop(false);break;
            case 7:
                a7.setAnimation(R.raw.wronganim);
                a7.playAnimation();
                a7.loop(false);break;
            case 8:
                a8.setAnimation(R.raw.wronganim);
                a8.playAnimation();
                a8.loop(false);break;
            case 9:
                a9.setAnimation(R.raw.wronganim);
                a9.playAnimation();
                a9.loop(false);break;
            case 10:
                a10.setAnimation(R.raw.wronganim);
                a10.playAnimation();
                a10.loop(false);break;
            case 11:
                a11.setAnimation(R.raw.wronganim);
                a11.playAnimation();
                a11.loop(false);break;
            case 12:
                a12.setAnimation(R.raw.wronganim);
                a12.playAnimation();
                a12.loop(false);break;
            case 13:
                a13.setAnimation(R.raw.wronganim);
                a13.playAnimation();
                a13.loop(false);break;
            case 14:
                a14.setAnimation(R.raw.wronganim);
                a14.playAnimation();
                a14.loop(false);break;
            case 15:
                a15.setAnimation(R.raw.wronganim);
                a15.playAnimation();
                a15.loop(false);break;
            case 16:
                a16.setAnimation(R.raw.wronganim);
                a16.playAnimation();
                a16.loop(false);break;
            case 17:
                a17.setAnimation(R.raw.wronganim);
                a17.playAnimation();
                a17.loop(false);break;
            case 18:
                a18.setAnimation(R.raw.wronganim);
                a18.playAnimation();
                a18.loop(false);break;
            case 19:
                a19.setAnimation(R.raw.wronganim);
                a19.playAnimation();
                a19.loop(false);break;
            case 20:
                a20.setAnimation(R.raw.wronganim);
                a20.playAnimation();
                a20.loop(false);break;
        }
    }


    public void opponentAnswersReceivingPlayer1(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerACounter,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                            playerACounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerACounter,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                            playerACounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerBCounter,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);
                            playerBCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerBCounter,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);
                            playerBCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerCCounter,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);
                            playerCCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerCCounter,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);
                            playerCCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }

    public void opponentAnswersReceivingPlayer2(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerACounter,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
                            playerACounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerACounter,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
                            playerACounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerBCounter,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);
                            playerBCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerBCounter,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);
                            playerBCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerCCounter,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);
                            playerCCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerCCounter,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);
                            playerCCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }

    public void opponentAnswersReceivingPlayer3(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerACounter,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
                            playerACounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerACounter,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
                            playerACounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerBCounter,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                            playerBCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerBCounter,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                            playerBCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerCCounter,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);
                            playerCCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerCCounter,anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);
                            playerCCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }

    public void opponentAnswersReceivingPlayer4(final String playerA, String playerB, String playerC, int r){
        for(int i=1;i<=r;i++){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerA).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerACounter,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
                            playerACounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerACounter,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
                            playerACounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerB).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerBCounter,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                            playerBCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerBCounter,anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);
                            playerBCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerC).child(String.valueOf(i)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int ans=snapshot.getValue(Integer.class);
                        if(ans==1){
                            playerCorrectAnim(playerCCounter,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);
                            playerCCounter++;
                        }else if(ans==2){
                            playerWrongAnim(playerCCounter,anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);
                            playerCCounter++;
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }


    public void fireBaseData(int setNumber){
        myRef.child("NormalQuizBIGJSON").orderByChild("sets").equalTo(setNumber).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1:snapshot.getChildren()){
                    list.add(snapshot1.getValue(questionHolder.class));
                    num++;
                }
                quizManupulator();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(tournamentQuiz.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                finish();
            }
        });
    }

    public void quizManupulator(){
        switch (questionNum){
            case 0:
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
                                        Toast.makeText(tournamentQuiz.this, "Please Wait", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                        nextButton.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer musicNav;
                                musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.finalbuttonmusic);
                                musicNav.start();
                                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        musicNav.reset();
                                        musicNav.release();
                                    }
                                });
                                nextButton.setEnabled(false);
                                nextButton.setAlpha(0.7f);
                                enableOption(true);
                                position++;
                                LLTrueManupulator();



                                if(swapnum==0){
                                    switch (questionNum){
                                        case 0:
                                            if (position == 10) {
                                                quizIntent();
                                            }break;
                                        case 1:
                                            if (position == 15) {
                                                quizIntent();
                                            }break;
                                        case 2:
                                            if (position == 20) {
                                                quizIntent();
                                            }break;
                                    }


                                }else {
                                    switch (questionNum){
                                        case 0:
                                            if (position == 11) {
                                                quizIntent();
                                            }break;
                                        case 1:
                                            if (position == 16) {
                                                quizIntent();
                                            }break;
                                        case 2:
                                            if (position == 21) {
                                                quizIntent();
                                            }break;
                                    }
                                }

                                count = 0;
                                playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                            }
                        });
                    } else {
                        finish();
                        Toast.makeText(tournamentQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();

                    }
                    loadingDialog.dismiss();
                }break;
            case 1:
                if(num==15) {
                    if (list.size() > 0) {
                        for (int i = 0; i < 4; i++) {
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View view) {
                                    checkAnswer((Button) view);
                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                        nextButton.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer musicNav;
                                musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.finalbuttonmusic);
                                musicNav.start();
                                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        musicNav.reset();
                                        musicNav.release();
                                    }
                                });
                                nextButton.setEnabled(false);
                                nextButton.setAlpha(0.7f);
                                enableOption(true);
                                position++;
                                LLTrueManupulator();



                                if(swapnum==0){
                                    switch (questionNum){
                                        case 0:
                                            if (position == 10) {
                                                quizIntent();
                                            }break;
                                        case 1:
                                            if (position == 15) {
                                                quizIntent();
                                            }break;
                                        case 2:
                                            if (position == 20) {
                                                quizIntent();
                                            }break;
                                    }


                                }else {
                                    switch (questionNum){
                                        case 0:
                                            if (position == 11) {
                                                quizIntent();
                                            }break;
                                        case 1:
                                            if (position == 16) {
                                                quizIntent();
                                            }break;
                                        case 2:
                                            if (position == 21) {
                                                quizIntent();
                                            }break;
                                    }
                                }

                                count = 0;
                                playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                            }
                        });
                    } else {
                        finish();
                        Toast.makeText(tournamentQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();

                    }
                    loadingDialog.dismiss();
                }break;
            case 2:
                if(num==20) {
                    if (list.size() > 0) {
                        for (int i = 0; i < 4; i++) {
                            linearLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void onClick(View view) {
                                    checkAnswer((Button) view);
                                }
                            });
                        }
                        playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                        nextButton.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onClick(View view) {
                                final MediaPlayer musicNav;
                                musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.finalbuttonmusic);
                                musicNav.start();
                                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    @Override
                                    public void onCompletion(MediaPlayer mediaPlayer) {
                                        musicNav.reset();
                                        musicNav.release();
                                    }
                                });
                                nextButton.setEnabled(false);
                                nextButton.setAlpha(0.7f);
                                enableOption(true);
                                position++;
                                LLTrueManupulator();



                                if(swapnum==0){
                                    switch (questionNum){
                                        case 0:
                                            if (position == 10) {
                                                quizIntent();
                                            }break;
                                        case 1:
                                            if (position == 15) {
                                                quizIntent();
                                            }break;
                                        case 2:
                                            if (position == 20) {
                                                quizIntent();
                                            }break;
                                    }


                                }else {
                                    switch (questionNum){
                                        case 0:
                                            if (position == 11) {
                                                quizIntent();
                                            }break;
                                        case 1:
                                            if (position == 16) {
                                                quizIntent();
                                            }break;
                                        case 2:
                                            if (position == 21) {
                                                quizIntent();
                                            }break;
                                    }
                                }

                                count = 0;
                                playAnim(questionTextView, 0, list.get(position).getQuestionTextView());

                            }
                        });
                    } else {
                        finish();
                        Toast.makeText(tournamentQuiz.this, "No Questions", Toast.LENGTH_SHORT).show();

                    }
                    loadingDialog.dismiss();
                }
        }

    }

    public void quizIntent(){
        if(playerNum==2||playerNum==3||playerNum==4){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(listner);
        }

        mInterstitialAd.setAdListener(new AdListener(){
            public void onAdClosed(){
                super.onAdClosed();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                Intent scoreIntent = new Intent(tournamentQuiz.this, tournamentScoreActivity.class);
                scoreIntent.putExtra("score", score);
                scoreIntent.putExtra("lifeline",lifelineSum);
                scoreIntent.putExtra("minutes",minutes);
                scoreIntent.putExtra("seconds",second);
                scoreIntent.putExtra("milliHolder47",milliHolder47);
                scoreIntent.putExtra("minutestext",minutestext);
                scoreIntent.putExtra("secondtext",secondtext);
                scoreIntent.putExtra("milliholder",milliHolder);
                scoreIntent.putExtra("category",category);
                scoreIntent.putExtra("counter",counter);
                scoreIntent.putExtra("numberOfPlayers",numberOfPlayers);
                scoreIntent.putExtra("myName",myName);
                scoreIntent.putExtra("myImageUrl",myImageUrl);
                scoreIntent.putExtra("hostUID",hostUid);
                scoreIntent.putExtra("hostName",hostName);
                scoreIntent.putExtra("hostImageUrl",hostImageUrl);
                scoreIntent.putExtra("player2Name",name2String);
                scoreIntent.putExtra("player3Name",name3String);
                scoreIntent.putExtra("player4Name",name4String);
                scoreIntent.putExtra("player2ImageUrl",image2Url);
                scoreIntent.putExtra("player3ImageUrl",image3Url);
                scoreIntent.putExtra("player4ImageUrl",image4Url);
                scoreIntent.putExtra("playerNumber",playerNum);
                scoreIntent.putExtra("roomCode",roomCode);
                scoreIntent.putExtra("questionNum",questionNum);
                scoreIntent.putExtra("timerNum",timerNum);
                if(countDownTimer!=null){
                    countDownTimer.cancel();}
                startActivity(scoreIntent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();

            }

        });

        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
            return;
        }

        try{
            songActivity.songStop();
        }catch (Exception e){

        }
        Intent scoreIntent = new Intent(tournamentQuiz.this, tournamentScoreActivity.class);
        scoreIntent.putExtra("score", score);
        scoreIntent.putExtra("lifeline",lifelineSum);
        scoreIntent.putExtra("minutes",minutes);
        scoreIntent.putExtra("seconds",second);
        scoreIntent.putExtra("milliHolder47",milliHolder47);
        scoreIntent.putExtra("minutestext",minutestext);
        scoreIntent.putExtra("secondtext",secondtext);
        scoreIntent.putExtra("milliholder",milliHolder);
        scoreIntent.putExtra("category",category);
        scoreIntent.putExtra("counter",counter);
        scoreIntent.putExtra("numberOfPlayers",numberOfPlayers);
        scoreIntent.putExtra("myName",myName);
        scoreIntent.putExtra("myImageUrl",myImageUrl);
        scoreIntent.putExtra("hostUID",hostUid);
        scoreIntent.putExtra("hostName",hostName);
        scoreIntent.putExtra("hostImageUrl",hostImageUrl);
        scoreIntent.putExtra("player2Name",name2String);
        scoreIntent.putExtra("player3Name",name3String);
        scoreIntent.putExtra("player4Name",name4String);
        scoreIntent.putExtra("player2ImageUrl",image2Url);
        scoreIntent.putExtra("player3ImageUrl",image3Url);
        scoreIntent.putExtra("player4ImageUrl",image4Url);
        scoreIntent.putExtra("playerNumber",playerNum);
        scoreIntent.putExtra("roomCode",roomCode);
        scoreIntent.putExtra("questionNum",questionNum);
        scoreIntent.putExtra("timerNum",timerNum);
        if(countDownTimer!=null){
            countDownTimer.cancel();}
        startActivity(scoreIntent);
        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();

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
                            switch (questionNum){
                                case 0:
                                    scoreBoard.setText(" Question "+(position+1)+"/10 ");break;
                                case 1:
                                    scoreBoard.setText(" Question "+(position+1)+"/15 ");break;
                                case 2:
                                    scoreBoard.setText(" Question "+(position+1)+"/20 ");break;

                            }
                        }else{
                            switch (questionNum){
                                case 0:
                                    scoreBoard.setText(" Question "+(position)+"/10 ");break;
                                case 1:
                                    scoreBoard.setText(" Question "+(position)+"/15 ");break;
                                case 2:
                                    scoreBoard.setText(" Question "+(position)+"/20 ");break;

                            }
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

    public void myCorrectAnswerAnimManupulator1(LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10,LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20){
        switch (questionCounter){
            case 1:
                a1.setAnimation(R.raw.tickanim);
                a1.playAnimation();
                a1.loop(false);break;
            case 2:
                a2.setAnimation(R.raw.tickanim);
                a2.playAnimation();
                a2.loop(false);break;
            case 3:
                a3.setAnimation(R.raw.tickanim);
                a3.playAnimation();
                a3.loop(false);break;
            case 4:
                a4.setAnimation(R.raw.tickanim);
                a4.playAnimation();
                a4.loop(false);break;
            case 5:
                a5.setAnimation(R.raw.tickanim);
                a5.playAnimation();
                a5.loop(false);break;
            case 6:
                a6.setAnimation(R.raw.tickanim);
                a6.playAnimation();
                a6.loop(false);break;
            case 7:
                a7.setAnimation(R.raw.tickanim);
                a7.playAnimation();
                a7.loop(false);break;
            case 8:
                a8.setAnimation(R.raw.tickanim);
                a8.playAnimation();
                a8.loop(false);break;
            case 9:
                a9.setAnimation(R.raw.tickanim);
                a9.playAnimation();
                a9.loop(false);break;
            case 10:
                a10.setAnimation(R.raw.tickanim);
                a10.playAnimation();
                a10.loop(false);break;
            case 11:
                a11.setAnimation(R.raw.tickanim);
                a11.playAnimation();
                a11.loop(false);break;
            case 12:
                a12.setAnimation(R.raw.tickanim);
                a12.playAnimation();
                a12.loop(false);break;
            case 13:
                a13.setAnimation(R.raw.tickanim);
                a13.playAnimation();
                a13.loop(false);break;
            case 14:
                a14.setAnimation(R.raw.tickanim);
                a14.playAnimation();
                a14.loop(false);break;
            case 15:
                a15.setAnimation(R.raw.tickanim);
                a15.playAnimation();
                a15.loop(false);break;
            case 16:
                a16.setAnimation(R.raw.tickanim);
                a16.playAnimation();
                a16.loop(false);break;
            case 17:
                a17.setAnimation(R.raw.tickanim);
                a17.playAnimation();
                a17.loop(false);break;
            case 18:
                a18.setAnimation(R.raw.tickanim);
                a18.playAnimation();
                a18.loop(false);break;
            case 19:
                a19.setAnimation(R.raw.tickanim);
                a19.playAnimation();
                a19.loop(false);break;
            case 20:
                a20.setAnimation(R.raw.tickanim);
                a20.playAnimation();
                a20.loop(false);break;
        }
    }

    public void myWrongAnswerAnimManupulator1(LottieAnimationView a1, LottieAnimationView a2, LottieAnimationView a3, LottieAnimationView a4, LottieAnimationView a5, LottieAnimationView a6, LottieAnimationView a7, LottieAnimationView a8, LottieAnimationView a9, LottieAnimationView a10,LottieAnimationView a11, LottieAnimationView a12, LottieAnimationView a13, LottieAnimationView a14, LottieAnimationView a15, LottieAnimationView a16, LottieAnimationView a17, LottieAnimationView a18, LottieAnimationView a19, LottieAnimationView a20){
        switch (questionCounter){
            case 1:
                a1.setAnimation(R.raw.wronganim);
                a1.playAnimation();
                a1.loop(false);break;
            case 2:
                a2.setAnimation(R.raw.wronganim);
                a2.playAnimation();
                a2.loop(false);break;
            case 3:
                a3.setAnimation(R.raw.wronganim);
                a3.playAnimation();
                a3.loop(false);break;
            case 4:
                a4.setAnimation(R.raw.wronganim);
                a4.playAnimation();
                a4.loop(false);break;
            case 5:
                a5.setAnimation(R.raw.wronganim);
                a5.playAnimation();
                a5.loop(false);break;
            case 6:
                a6.setAnimation(R.raw.wronganim);
                a6.playAnimation();
                a6.loop(false);break;
            case 7:
                a7.setAnimation(R.raw.wronganim);
                a7.playAnimation();
                a7.loop(false);break;
            case 8:
                a8.setAnimation(R.raw.wronganim);
                a8.playAnimation();
                a8.loop(false);break;
            case 9:
                a9.setAnimation(R.raw.wronganim);
                a9.playAnimation();
                a9.loop(false);break;
            case 10:
                a10.setAnimation(R.raw.wronganim);
                a10.playAnimation();
                a10.loop(false);break;
            case 11:
                a11.setAnimation(R.raw.wronganim);
                a11.playAnimation();
                a11.loop(false);break;
            case 12:
                a12.setAnimation(R.raw.wronganim);
                a12.playAnimation();
                a12.loop(false);break;
            case 13:
                a13.setAnimation(R.raw.wronganim);
                a13.playAnimation();
                a13.loop(false);break;
            case 14:
                a14.setAnimation(R.raw.wronganim);
                a14.playAnimation();
                a14.loop(false);break;
            case 15:
                a15.setAnimation(R.raw.wronganim);
                a15.playAnimation();
                a15.loop(false);break;
            case 16:
                a16.setAnimation(R.raw.wronganim);
                a16.playAnimation();
                a16.loop(false);break;
            case 17:
                a17.setAnimation(R.raw.wronganim);
                a17.playAnimation();
                a17.loop(false);break;
            case 18:
                a18.setAnimation(R.raw.wronganim);
                a18.playAnimation();
                a18.loop(false);break;
            case 19:
                a19.setAnimation(R.raw.wronganim);
                a19.playAnimation();
                a19.loop(false);break;
            case 20:
                a20.setAnimation(R.raw.wronganim);
                a20.playAnimation();
                a20.loop(false);break;
        }
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
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.correctmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));   //green color
            selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
            selectedOption.setShadowLayer(3,1,1,R.color.lightgreen);
            score++;

            switch (playerNum){
                case 1:
                    myCorrectAnswerAnimManupulator1(anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);break;
                case 2:
                    myCorrectAnswerAnimManupulator1(anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);break;
                case 3:
                    myCorrectAnswerAnimManupulator1(anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);break;
                case 4:
                    myCorrectAnswerAnimManupulator1(anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);break;
            }




            switch (playerNum){
                case 1:
                    answerStatus("player1Answer",1);break;
                case 2:
                    answerStatus("player2Answer",1);break;
                case 3:
                    answerStatus("player3Answer",1);break;
                case 4:
                    answerStatus("player4Answer",1);break;
            }

        }else {
            //incorrect
 final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.wrongansfinal);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });

                        selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF8888")));     //red color
                        selectedOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                        selectedOption.setShadowLayer(3,1,1,R.color.lightgreen);
                        Button correctOption = (Button) linearLayout.findViewWithTag(list.get(position).getCorrectAnswer());
                        correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#B1FF88")));     //green color
                        correctOption.setTextColor(ColorStateList.valueOf(Color.parseColor("#000000")));
                        correctOption.setShadowLayer(3,1,1,R.color.lightred);

          
            switch (playerNum){
                case 1:
                    myWrongAnswerAnimManupulator1(anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);break;
                case 2:
                    myWrongAnswerAnimManupulator1(anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20,animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20);break;
                case 3:
                    myWrongAnswerAnimManupulator1(anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30,animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30);break;
                case 4:
                    myWrongAnswerAnimManupulator1(anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40,animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40);break;
            }


            switch (playerNum){
                case 1:
                    answerStatus("player1Answer",2);break;
                case 2:
                    answerStatus("player2Answer",2);break;
                case 3:
                    answerStatus("player3Answer",2);break;
                case 4:
                    answerStatus("player4Answer",2);break;
            }
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


    public void dataSetterOfIntent(){


        switch (playerNum) {
            case 1:
                opponentName.setText(myName);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(opponentPic);
                try{
                    opponentName2.setText(name2String);
                    Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic2);
                }catch (Exception e){

                }

                try{
                    opponentName3.setText(name3String);
                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic3);
                }catch (Exception e){

                }

                try{

                    opponentName4.setText(name4String);
                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic4);
                }catch (Exception e){

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

                try{
                    opponentName3.setText(name3String);
                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(opponentPic3);
                }catch (Exception e){

               }

               try {

                       opponentName4.setText(name4String);
                       Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                               .bitmapTransform(new RoundedCorners(14)))
                               .into(opponentPic4);

               }catch (Exception e){

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
                    }catch (Exception e){

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


    public void countDownTimerFun(int i){   //Clock Algo
        countDownTimer=new CountDownTimer(i, 1000) {


            public void onTick(long millisUntilFinished) {
                milliHolder47= (int) millisUntilFinished;

                counter++;

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
            }
            public void onFinish() {
                if(playerNum==2||playerNum==3||playerNum==4){
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(listner);
                }

                mInterstitialAd.setAdListener(new AdListener(){
                    public void onAdClosed(){
                        super.onAdClosed();
                        mInterstitialAd.loadAd(new AdRequest.Builder().build());
                        try{
                            songActivity.songStop();
                        }catch (Exception e){

                        }
                        Toast.makeText(tournamentQuiz.this, "Time Over", Toast.LENGTH_SHORT).show();
                        Intent scoreIntent = new Intent(tournamentQuiz.this, tournamentScoreActivity.class);
                        scoreIntent.putExtra("score", score);
                        scoreIntent.putExtra("lifeline",lifelineSum);
                        scoreIntent.putExtra("minutes",minutes);
                        scoreIntent.putExtra("seconds",second);
                        scoreIntent.putExtra("milliHolder47",milliHolder47);
                        scoreIntent.putExtra("minutestext",minutestext);
                        scoreIntent.putExtra("secondtext",secondtext);
                        scoreIntent.putExtra("milliholder",milliHolder);
                        scoreIntent.putExtra("category",category);
                        scoreIntent.putExtra("counter",counter);
                        scoreIntent.putExtra("myName",myName);
                        scoreIntent.putExtra("numberOfPlayers",numberOfPlayers);
                        scoreIntent.putExtra("myImageUrl",myImageUrl);
                        scoreIntent.putExtra("hostUID",hostUid);
                        scoreIntent.putExtra("hostName",hostName);
                        scoreIntent.putExtra("hostImageUrl",hostImageUrl);
                        scoreIntent.putExtra("player2Name",name2String);
                        scoreIntent.putExtra("player3Name",name3String);
                        scoreIntent.putExtra("player4Name",name4String);
                        scoreIntent.putExtra("player2ImageUrl",image2Url);
                        scoreIntent.putExtra("player3ImageUrl",image3Url);
                        scoreIntent.putExtra("player4ImageUrl",image4Url);
                        scoreIntent.putExtra("playerNumber",playerNum);
                        scoreIntent.putExtra("roomCode",roomCode);
                        scoreIntent.putExtra("questionNum",questionNum);
                        scoreIntent.putExtra("timerNum",timerNum);
                        startActivity(scoreIntent);
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        if(countDownTimer!=null){
                            countDownTimer.cancel();}
                        finish();

                    }

                });

                if(mInterstitialAd.isLoaded()){
                    mInterstitialAd.show();
                    return;
                }

                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                Toast.makeText(tournamentQuiz.this, "Time Over", Toast.LENGTH_SHORT).show();
                Intent scoreIntent = new Intent(tournamentQuiz.this, tournamentScoreActivity.class);
                scoreIntent.putExtra("score", score);
                scoreIntent.putExtra("lifeline",lifelineSum);
                scoreIntent.putExtra("minutes",minutes);
                scoreIntent.putExtra("seconds",second);
                scoreIntent.putExtra("milliHolder47",milliHolder47);
                scoreIntent.putExtra("minutestext",minutestext);
                scoreIntent.putExtra("secondtext",secondtext);
                scoreIntent.putExtra("milliholder",milliHolder);
                scoreIntent.putExtra("category",category);
                scoreIntent.putExtra("counter",counter);
                scoreIntent.putExtra("myName",myName);
                scoreIntent.putExtra("numberOfPlayers",numberOfPlayers);
                scoreIntent.putExtra("myImageUrl",myImageUrl);
                scoreIntent.putExtra("hostUID",hostUid);
                scoreIntent.putExtra("hostName",hostName);
                scoreIntent.putExtra("hostImageUrl",hostImageUrl);
                scoreIntent.putExtra("player2Name",name2String);
                scoreIntent.putExtra("player3Name",name3String);
                scoreIntent.putExtra("player4Name",name4String);
                scoreIntent.putExtra("player2ImageUrl",image2Url);
                scoreIntent.putExtra("player3ImageUrl",image3Url);
                scoreIntent.putExtra("player4ImageUrl",image4Url);
                scoreIntent.putExtra("playerNumber",playerNum);
                scoreIntent.putExtra("roomCode",roomCode);
                scoreIntent.putExtra("questionNum",questionNum);
                scoreIntent.putExtra("timerNum",timerNum);
                startActivity(scoreIntent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                if(countDownTimer!=null){
                    countDownTimer.cancel();}
                finish();
            }

        }.start();
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


    public void onBackPressed() {
        if(playerNum==1||playerNum==2||playerNum==3||playerNum==4){
            cancelDialogFunction();
        }

    }


    public void cancelDialogFunction(){
        AlertDialog.Builder builderRemove=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.quit_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderRemove.setView(viewRemove1);
        builderRemove.setCancelable(false);
        Button yesButton=(Button) viewRemove1.findViewById(R.id.buttonYes);
        Button noButton=(Button) viewRemove1.findViewById(R.id.buttonNo);





        final AlertDialog alertDialog=builderRemove.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.finalbuttonmusic);
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
                musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.finalbuttonmusic);
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

                    Intent intent47=new Intent(tournamentQuiz.this,mainMenuActivity.class);

                    if(countDownTimer!=null){
                        countDownTimer.cancel();}

                    startActivity(intent47);

                    finish();
                }
            });


        } else if(playerNum==2){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
        //    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status");

        }else if(playerNum==3){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
        //    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(listener2);

        }else if (playerNum==4){
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
       //     myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(listener3);

        }

    }

    public void leaveDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(tournamentQuiz.this,R.style.AlertDialogTheme);
        View view =LayoutInflater.from(tournamentQuiz.this).inflate(R.layout.removal_lobby_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        builder.setCancelable(false);


        TextView disText=(TextView) view.findViewById(R.id.textTitle);
        Button buttonYes=(Button) view.findViewById(R.id.buttonYes);
        disText.setText("Your Host "+hostName+" Has Either Disconnected Or Left The Game!");

        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();


        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    songActivity.songStop();
                }catch (Exception e){

                }
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentQuiz.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if(countDownTimer!=null){
                    countDownTimer.cancel();}
                alertDialog.dismiss();
                tournamentQuiz.super.onBackPressed();
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
            }
        });



    }

    public void opponentRemovedKnower(){
        listner=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    oppoStatus=snapshot.getValue(Integer.class);
                    if(oppoStatus==0){
                        leaveDialog();
                    }
                }catch (Exception e){
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