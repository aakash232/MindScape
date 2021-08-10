package com.nbird.mindscape;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class OneVsOneBOTScoreActivity extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    int minrev,secriv;
    String opponentUID;
    String opponentimageUrl;
    String opponentUsername;
    String myProPicUrl;
    String myName123;
    TextView disDialog;
    ImageView image2,image1;
    int score,lifelineSum,minutes,second,oppoScoreCounter,oppoWrongAnsCounter;
    long milliholder;
    long totalSum=0;
    int opponentScorebro;
    Button buttonYes,buttonNo;
    TextView titleDialog;
    LottieAnimationView animDialog,requestAnim;

    ImageView onlineImage,opponentImage;
    TextView myName,myScore,myAcuu,myRatio,myTime,myLifelines,oppoName,oppoScore,oppoAccu,oppoRatio,oppoTime,oppoLifelines;
    List<Integer> arrlist,arroppo;
    LottieAnimationView anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;
    LottieAnimationView anim1oppo,anim2oppo,anim3oppo,anim4oppo,anim5oppo,anim6oppo,anim7oppo,anim8oppo,anim9oppo,anim10oppo;
    BarChart barChart;
    PieChart pieChart;
    Button home,rematch;
    List<Integer> arrlist30;
    String username;
    int sumScore;
    CountDownTimer countDownTimerBOTRequest;

    int score123,timeHolder,correct,wrong,wrongfire,sumationOfScore;

    String mine;

    int minman,secman,botTime,botCorrectAns;

    int desider,amin,asec;

    int parcel,isHostFinal,isRequesSendValid=1;
    private void loadAds(){
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
    TextView oppoLevel;
    ImageView oppoBatch;
    ImageView levelImage;
    TextView levelText;


    public void levelManupulation123(int oppoScore123){

        if(oppoScore123<100000){
            if(oppoScore123<50000){
                oppoLevel.setText(" Lv. 1 ");
            }else{
                oppoLevel.setText(" Lv. 2 ");
            }
        }else{
            int holder;
            holder=oppoScore123/50000;
            oppoLevel.setText(" Lv. "+holder+" ");
        }

        if(oppoScore123<50000){
            oppoBatch.setBackgroundResource(R.drawable.blackiron);
        }else if(oppoScore123<200000){
            oppoBatch.setBackgroundResource(R.drawable.bronze);
        }else if(oppoScore123<800000){
            oppoBatch.setBackgroundResource(R.drawable.silver);
        }else if(oppoScore123<1800000){
            oppoBatch.setBackgroundResource(R.drawable.gold);
        }else if(oppoScore123<3000000){
            oppoBatch.setBackgroundResource(R.drawable.platinum);
        }else if(oppoScore123<4000000){
            oppoBatch.setBackgroundResource(R.drawable.diamond);
        }else if(oppoScore123<8000000){
            oppoBatch.setBackgroundResource(R.drawable.amethyst);
        }else if(oppoScore123<12000000){
            oppoBatch.setBackgroundResource(R.drawable.master);
        }else{
            oppoBatch.setBackgroundResource(R.drawable.king);
        }
    }
    barGroupHolder man;
    CountDownTimer cf;
    int yalo=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onevsone_online_score_card);

        loadAds();
        arrlist30 = new ArrayList<>(13);

        cf=new CountDownTimer(1000*45,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                 yalo=1;
            }
        }.start();

        onlineImage=(ImageView) findViewById(R.id.onlineImage);
        opponentImage=(ImageView) findViewById(R.id.opponentImage);
        home=(Button) findViewById(R.id.home);
        rematch=(Button) findViewById(R.id.rematch);

        myName=(TextView) findViewById(R.id.myName);
        myScore=(TextView) findViewById(R.id.myScore);
        myAcuu=(TextView) findViewById(R.id.myAccu);
        myRatio=(TextView) findViewById(R.id.myCorrectOrWrong);
        myTime=(TextView) findViewById(R.id.myTime);
        myLifelines=(TextView) findViewById(R.id.myLifeLine);
        oppoName=(TextView) findViewById(R.id.oppoName);
        oppoScore=(TextView) findViewById(R.id.oppoScore);
        oppoAccu=(TextView) findViewById(R.id.oppoAccu);
        oppoRatio=(TextView) findViewById(R.id.oppoCorrectOrWrong);
        oppoTime=(TextView) findViewById(R.id.oppoTime);
        oppoLifelines=(TextView) findViewById(R.id.oppoLifeLine);
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
        anim1oppo=(LottieAnimationView) findViewById(R.id.anim1oppo);
        anim2oppo=(LottieAnimationView) findViewById(R.id.anim2oppo);
        anim3oppo=(LottieAnimationView) findViewById(R.id.anim3oppo);
        anim4oppo=(LottieAnimationView) findViewById(R.id.anim4oppo);
        anim5oppo=(LottieAnimationView) findViewById(R.id.anim5oppo);
        anim6oppo=(LottieAnimationView) findViewById(R.id.anim6oppo);
        anim7oppo=(LottieAnimationView) findViewById(R.id.anim7oppo);
        anim8oppo=(LottieAnimationView) findViewById(R.id.anim8oppo);
        anim9oppo=(LottieAnimationView) findViewById(R.id.anim9oppo);
        anim10oppo=(LottieAnimationView) findViewById(R.id.anim10oppo);
        barChart=(BarChart) findViewById(R.id.barChart);
        pieChart=(PieChart) findViewById(R.id.pieChart);
        levelImage =(ImageView) findViewById(R.id.batch4);
        levelText   =(TextView) findViewById(R.id.levelText1234);
        oppoLevel=(TextView) findViewById(R.id.levelText12345);
        oppoBatch=(ImageView) findViewById(R.id.batch5);


        //  mShimmerViewContainer=(ShimmerFrameLayout) view1.findViewById(R.id.shimmer90);

        arrlist = new ArrayList<>();
        arroppo = new ArrayList<>();
        opponentUID=getIntent().getStringExtra("opponentUID");
        opponentimageUrl=getIntent().getStringExtra("opponentImageUrl");
        opponentUsername=getIntent().getStringExtra("opponentUserName");
        myProPicUrl=getIntent().getStringExtra("mypropic");
        myName123=getIntent().getStringExtra("myName");
        mine=getIntent().getStringExtra("mine");
        score=getIntent().getIntExtra("score", 0);
        lifelineSum=getIntent().getIntExtra("lifeline",0);
        minutes=getIntent().getIntExtra("minutes",0);
        second=getIntent().getIntExtra("seconds",0);
        milliholder=getIntent().getLongExtra("milliholder",0);
        oppoScoreCounter=getIntent().getIntExtra("oppoScoreCounter",0);
        oppoWrongAnsCounter=getIntent().getIntExtra("oppoWrongAnsCounter",0);
        arrlist=getIntent().getIntegerArrayListExtra("myArr");
        arroppo=getIntent().getIntegerArrayListExtra("oppoArr");
        minman=getIntent().getIntExtra("minman",0);
        secman=getIntent().getIntExtra("secman",0);
        desider=getIntent().getIntExtra("desider",0);
        amin=getIntent().getIntExtra("actualmin",0);
        asec=getIntent().getIntExtra("actualsec",0);
        parcel=getIntent().getIntExtra("parsel",0);
        isHostFinal=getIntent().getIntExtra("isHostFinal",0);
        botTime=getIntent().getIntExtra("botTime",0);
        botCorrectAns=getIntent().getIntExtra("botCorrectAns",0);
        sumScore=getIntent().getIntExtra("sumScore",0);


        dataSetterFun();

        highScorePushInLeaderBoard();
        levelManupulation123(sumScore);




        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("LineChartGradient").child(date).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    long i=snapshot.getValue(Integer.class);
                    long k=((1000*60*3)-milliholder)/1000;
                    i=i+k;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("LineChartGradient").child(date).setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    long k=((1000*60*3)-milliholder)/1000;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("LineChartGradient").child(date).setValue(k).addOnCompleteListener(new OnCompleteListener<Void>() {
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



        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        final String dayOfTheWeek = sdf.format(d);

        man=new barGroupHolder();
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("barGroupData").child(dayOfTheWeek).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    man=snapshot.getValue(barGroupHolder.class);
                    int cA=man.getCorrect()+score;
                    int wA=man.getWrong()+(10-score);


                    barGroupHolder g1=new barGroupHolder(cA,wA);
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("barGroupData").child(dayOfTheWeek).setValue(g1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }catch (Exception e){
                    int t=10-score;
                    barGroupHolder g1=new barGroupHolder(score,t);
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("barGroupData").child(dayOfTheWeek).setValue(g1).addOnCompleteListener(new OnCompleteListener<Void>() {
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




        numberOfTimesPlayed();

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfOnlineModePlayed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int s=snapshot.getValue(Integer.class);
                    s++;
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfOnlineModePlayed").setValue(s).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfOnlineModePlayed").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
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

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    cancelDialogFunction();
                }catch (Exception e){

                }
            }
        });

        rematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRequesSendValid=0;
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTScoreActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });


                if(yalo==0){
                      randomNumberGeneratorFunction();




                        Toast.makeText(OneVsOneBOTScoreActivity.this, "Request Send To "+opponentUsername, Toast.LENGTH_SHORT).show();
                        rematch.setEnabled(false);

                        new CountDownTimer(1000*5,1000){

                            @Override
                            public void onTick(long millisUntilFinished) {

                            }

                            @Override
                            public void onFinish() {
                                rematch.setEnabled(true);
                            }
                        }.start();
                }else {
                    Toast.makeText(OneVsOneBOTScoreActivity.this, opponentUsername+ " Has Left The Room!!!Please Find Another Opponent.", Toast.LENGTH_SHORT).show();

                }






            }
        });

        Random r=new Random();
        int b=r.nextInt(10)+1;

        if(b<=8){
            int l=r.nextInt(8)+10;
            countDownTimerBOTRequest=new CountDownTimer(1000*l,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }

                @Override
                public void onFinish() {

                    requestDialogBoxFun();
                }
            }.start();
        }

        
    }


    public void requestDialogBoxFun(){






            AlertDialog.Builder builder = new AlertDialog.Builder(OneVsOneBOTScoreActivity.this, R.style.AlertDialogTheme);

            final View view1 = LayoutInflater.from(OneVsOneBOTScoreActivity.this).inflate(R.layout.request_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            builder.setCancelable(false);

            titleDialog = (TextView) view1.findViewById(R.id.textTitle);
            buttonYes = (Button) view1.findViewById(R.id.buttonYes);
            buttonNo = (Button) view1.findViewById(R.id.buttonNo);
            requestAnim = (LottieAnimationView) view1.findViewById(R.id.backlitemodeanim);

            final AlertDialog alertDialog = builder.create();
            if (alertDialog.getWindow() != null) {
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            try{
                alertDialog.show();
            }catch (Exception e){

            }

            titleDialog.setText(opponentUsername + " Has Send You A Request For A Rematch!");


            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(OneVsOneBOTScoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    alertDialog.dismiss();


                            if (desider == 1) {
                                for(int i=0;i<14;i++){
                                    // create instance of Random class
                                    Random rand = new Random();

                                    // Generate random integers in range 0 to 6326

                                    int setNumber=rand.nextInt(6326)+1; //NEED TO CHANGE HERE
                                    //NEED TO CHANGE HERE
                                    arrlist30.add(setNumber);
                                }
                                Intent intent = new Intent(OneVsOneBOTScoreActivity.this, OneVsOneBOTNormalQuiz.class);
                                intent.putExtra("opponentImageUrl", opponentimageUrl);
                                intent.putExtra("opponentUserName", opponentUsername);
                                intent.putExtra("mypropic", myProPicUrl);
                                intent.putExtra("myName", myName123);

                                intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                                startActivity(intent);
                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                finish();


                            } else {
                                for(int i=0;i<14;i++){
                                    // create instance of Random class
                                    Random rand = new Random();

                                    int setNumber = rand.nextInt(4999)+1;

                                    if(setNumber>1210&&setNumber<2000){
                                        setNumber=setNumber-1000;
                                    }  //NEED TO CHANGE HERE
                                    //NEED TO CHANGE HERE
                                    arrlist30.add(setNumber);
                                }
                                Intent intent = new Intent(OneVsOneBOTScoreActivity.this, OneVsOneBotPictureQuiz.class);
                                intent.putExtra("opponentImageUrl", opponentimageUrl);
                                intent.putExtra("opponentUserName", opponentUsername);
                                intent.putExtra("mypropic", myProPicUrl);
                                intent.putExtra("myName", myName123);

                                intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);

                                startActivity(intent);
                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                finish();


                            }

                        }
                    });




            buttonNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(OneVsOneBOTScoreActivity.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });

                            //    myRef.child("User").child(opponentUID).child("1vs1Online").removeValue();
                            alertDialog.dismiss();



                }
            });


    }
    public void numberOfTimesPlayed(){
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
    public void dataSetterFun(){

        Glide.with(getBaseContext()).load(opponentimageUrl).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(opponentImage);

        Glide.with(getBaseContext()).load(myProPicUrl).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(onlineImage);

        if(parcel==1){
            minrev=amin;
            secriv=asec;
            totalSum= ((60*(2-amin))+(59-asec))*80;
        }else{
            minrev=2-minutes;
            secriv=59-second;
            totalSum= ((60*minutes)+second)*80;
        }



        totalSum=totalSum+2000;
        for(int i=1;i<=lifelineSum;i++){
            totalSum=totalSum-500;
        }
        for (int i=1;i<=score;i++){
            totalSum=totalSum+1000;
        }





        int accUser=score*10;




        myName.setText(myName123);
        myScore.setText("Total Score : "+totalSum+" ");
        myAcuu.setText("Accuracy : "+accUser+"%");

        if(parcel==1){
            myTime.setText("Time Taken : "+minrev+"min "+secriv+"sec");
        }else{

            myTime.setText(mine);
        }

        if(parcel==1){
            mine="Time Taken : "+minrev+"min "+secriv+"sec";
        }

        myLifelines.setText("Life-Lines Used : "+lifelineSum+"/4");
        oppoName.setText(opponentUsername);

        int mer=(100-accUser)/10;
        myRatio.setText("Correct/Wrong : "+score+"/"+mer);

        greatFunSetter();
        final int f=10-score;
        long k=(60*1000*10)-milliholder;
        quizHistoryData s5 = new quizHistoryData((int) totalSum, k,score,f);
        String key = database.getReference().child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeQuizHistory").push().getKey();
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeQuizHistory").child(key).setValue(s5).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

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
        SharedPreferences batch = getApplicationContext().getSharedPreferences("Batch1vs1_1", 0);
        SharedPreferences.Editor editor = batch.edit();
        int s1;
        if(sumationOfScore<50000){

            levelImage.setBackgroundResource(R.drawable.blackiron);
        }else if(sumationOfScore<200000){
            s1 = batch.getInt("bronze", 0);

            if(s1==0){
                dialogFunction();
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To bronze");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To silver");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To Gold");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To Platinum");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To Diamond");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To Amethyst");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To Master");      //   Aakash Changes Are to be done here
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
                disDialog.setText("Congratulations "+myName123+".You Are Upgraded To King");      //   Aakash Changes Are to be done here
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
        AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTScoreActivity.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(OneVsOneBOTScoreActivity.this).inflate(R.layout.batch_display_dialog_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        disDialog=((TextView) view1.findViewById(R.id.textTitle));
        ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
        image1=((ImageView) view1.findViewById(R.id.imageIcon));
        image2=((ImageView) view1.findViewById(R.id.imageIcon2));
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
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTScoreActivity.this, R.raw.finalbuttonmusic);
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

    public void animManu(){

        try {
            for (int i = 0; i < 10; i++) {
                int numOppo=2;
                int numMy=2;
                try{
                    numMy = arrlist.get(i);
                }catch (Exception e){

                }
                try{
                    numOppo = arroppo.get(i);
                }catch (Exception e){

                }



                switch (i + 1) {
                    case 1:
                        try{
                            if (numMy == 1) {
                                anim1.setAnimation(R.raw.tickanim);
                                anim1.playAnimation();
                                anim1.loop(false);
                            } else  {
                                anim1.setAnimation(R.raw.wronganim);
                                anim1.playAnimation();
                                anim1.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim1oppo.setAnimation(R.raw.tickanim);
                                anim1oppo.playAnimation();
                                anim1oppo.loop(false);
                            } else  {
                                anim1oppo.setAnimation(R.raw.wronganim);
                                anim1oppo.playAnimation();
                                anim1oppo.loop(false);
                            }
                        }catch (Exception e){

                        }

                        break;
                    case 2:
                        try{
                            if (numMy == 1) {
                                anim2.setAnimation(R.raw.tickanim);
                                anim2.playAnimation();
                                anim2.loop(false);
                            } else  {
                                anim2.setAnimation(R.raw.wronganim);
                                anim2.playAnimation();
                                anim2.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim2oppo.setAnimation(R.raw.tickanim);
                                anim2oppo.playAnimation();
                                anim2oppo.loop(false);
                            } else  {
                                anim2oppo.setAnimation(R.raw.wronganim);
                                anim2oppo.playAnimation();
                                anim2oppo.loop(false);
                            }
                        }catch (Exception e){

                        }



                        break;
                    case 3:
                        try{
                            if (numMy == 1) {
                                anim3.setAnimation(R.raw.tickanim);
                                anim3.playAnimation();
                                anim3.loop(false);
                            } else  {
                                anim3.setAnimation(R.raw.wronganim);
                                anim3.playAnimation();
                                anim3.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim3oppo.setAnimation(R.raw.tickanim);
                                anim3oppo.playAnimation();
                                anim3oppo.loop(false);
                            } else  {
                                anim3oppo.setAnimation(R.raw.wronganim);
                                anim3oppo.playAnimation();
                                anim3oppo.loop(false);
                            }
                        }catch (Exception e){

                        }


                        break;
                    case 4:
                        try{
                            if (numMy == 1) {
                                anim4.setAnimation(R.raw.tickanim);
                                anim4.playAnimation();
                                anim4.loop(false);
                            } else  {
                                anim4.setAnimation(R.raw.wronganim);
                                anim4.playAnimation();
                                anim4.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim4oppo.setAnimation(R.raw.tickanim);
                                anim4oppo.playAnimation();
                                anim4oppo.loop(false);
                            } else  {
                                anim4oppo.setAnimation(R.raw.wronganim);
                                anim4oppo.playAnimation();
                                anim4oppo.loop(false);
                            }
                        }catch (Exception e){

                        }


                        break;
                    case 5:
                        try{
                            if (numMy == 1) {
                                anim5.setAnimation(R.raw.tickanim);
                                anim5.playAnimation();
                                anim5.loop(false);
                            } else  {
                                anim5.setAnimation(R.raw.wronganim);
                                anim5.playAnimation();
                                anim5.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim5oppo.setAnimation(R.raw.tickanim);
                                anim5oppo.playAnimation();
                                anim5oppo.loop(false);
                            } else  {
                                anim5oppo.setAnimation(R.raw.wronganim);
                                anim5oppo.playAnimation();
                                anim5oppo.loop(false);
                            }
                        }catch (Exception e){

                        }


                        break;
                    case 6:
                        try{
                            if (numMy == 1) {
                                anim6.setAnimation(R.raw.tickanim);
                                anim6.playAnimation();
                                anim6.loop(false);
                            } else  {
                                anim6.setAnimation(R.raw.wronganim);
                                anim6.playAnimation();
                                anim6.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim6oppo.setAnimation(R.raw.tickanim);
                                anim6oppo.playAnimation();
                                anim6oppo.loop(false);
                            } else  {
                                anim6oppo.setAnimation(R.raw.wronganim);
                                anim6oppo.playAnimation();
                                anim6oppo.loop(false);
                            }
                        }catch (Exception e){

                        }


                        break;
                    case 7:
                        try{
                            if (numMy == 1) {

                                anim7.setAnimation(R.raw.tickanim);
                                anim7.playAnimation();
                                anim7.loop(false);
                            } else  {
                                anim7.setAnimation(R.raw.wronganim);
                                anim7.playAnimation();
                                anim7.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim7oppo.setAnimation(R.raw.tickanim);
                                anim7oppo.playAnimation();
                                anim7oppo.loop(false);
                            } else  {
                                anim7oppo.setAnimation(R.raw.wronganim);
                                anim7oppo.playAnimation();
                                anim7oppo.loop(false);
                            }
                        }catch (Exception e){

                        }



                        break;
                    case 8:
                        try{
                            if (numMy == 1) {

                                anim8.setAnimation(R.raw.tickanim);
                                anim8.playAnimation();
                                anim8.loop(false);
                            } else  {
                                anim8.setAnimation(R.raw.wronganim);
                                anim8.playAnimation();
                                anim8.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{

                            if (numOppo == 1) {
                                anim8oppo.setAnimation(R.raw.tickanim);
                                anim8oppo.playAnimation();
                                anim8oppo.loop(false);
                            } else  {
                                anim8oppo.setAnimation(R.raw.wronganim);
                                anim8oppo.playAnimation();
                                anim8oppo.loop(false);
                            }
                        }catch (Exception e){

                        }

                        break;
                    case 9:
                        try{
                            if (numMy == 1) {

                                anim9.setAnimation(R.raw.tickanim);
                                anim9.playAnimation();
                                anim9.loop(false);
                            } else  {
                                anim9.setAnimation(R.raw.wronganim);
                                anim9.playAnimation();
                                anim9.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim9oppo.setAnimation(R.raw.tickanim);
                                anim9oppo.playAnimation();
                                anim9oppo.loop(false);
                            } else  {
                                anim9oppo.setAnimation(R.raw.wronganim);
                                anim9oppo.playAnimation();
                                anim9oppo.loop(false);
                            }
                        }catch (Exception e){

                        }


                        break;
                    case 10:
                        try{
                            if (numMy == 1) {
                                anim10.setAnimation(R.raw.tickanim);
                                anim10.playAnimation();
                                anim10.loop(false);
                            } else  {
                                anim10.setAnimation(R.raw.wronganim);
                                anim10.playAnimation();
                                anim10.loop(false);
                            }
                        }catch (Exception e){

                        }
                        try{
                            if (numOppo == 1) {
                                anim10oppo.setAnimation(R.raw.tickanim);
                                anim10oppo.playAnimation();
                                anim10oppo.loop(false);
                            } else  {
                                anim10oppo.setAnimation(R.raw.wronganim);
                                anim10oppo.playAnimation();
                                anim10oppo.loop(false);
                            }
                        }catch (Exception e){

                        }


                        break;
                }
            }
        }catch (Exception e){

        }
    }

    public void dialogBoxForDisiderFunction(int opponentScorebro){

        AlertDialog.Builder builder=new AlertDialog.Builder(OneVsOneBOTScoreActivity.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(OneVsOneBOTScoreActivity.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        titleDialog=(TextView) view1.findViewById(R.id.textTitle);
        ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
        animDialog=(LottieAnimationView) view1.findViewById(R.id.imageIcon);

        if(totalSum> opponentScorebro){
            titleDialog.setText(myName123+" You Won!!. You have Beaten "+opponentUsername);  // Aakash changes to be done here
            animDialog.setAnimation(R.raw.winneranim);
            animDialog.playAnimation();
        }else if(totalSum== opponentScorebro){
            titleDialog.setText(myName123+" Match Is Draw!!");
            animDialog.setAnimation(R.raw.draawanim);
            animDialog.playAnimation();
        }else{
            titleDialog.setText(myName123+" You Lose. "+opponentUsername+" Won.");
            animDialog.setAnimation(R.raw.losseranim);
            animDialog.playAnimation();
        }


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
                animManu();
            }
        });

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(cf!=null){
            cf.cancel();
        }
        if(countDownTimerBOTRequest!=null){
            countDownTimerBOTRequest.cancel();
        }

        Runtime.getRuntime().gc();

    }




    public void randomNumberGeneratorFunction(){
        if(desider==1){
            for(int i=0;i<14;i++){
                // create instance of Random class
                Random rand = new Random();

                // Generate random integers in range 0 to 6326

                int setNumber=rand.nextInt(6326)+1; //NEED TO CHANGE HERE
                //NEED TO CHANGE HERE
                arrlist30.add(setNumber);
            }
            Random r=new Random();
            final int nu=r.nextInt(4)+2;


                 new CountDownTimer(1000 * nu, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }
                @Override
                public void onFinish() {
                    if(nu==3){
                        Toast.makeText(OneVsOneBOTScoreActivity.this, opponentUsername + " Rejected Your Request For A Rematch", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent=new Intent(OneVsOneBOTScoreActivity.this,OneVsOneBOTNormalQuiz.class);
                        intent.putExtra("opponentImageUrl",opponentimageUrl);
                        intent.putExtra("opponentUserName",opponentUsername);
                        intent.putExtra("mypropic",myProPicUrl);
                        intent.putExtra("myName",myName123);
                        intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        finish();
                    }
                }
            }.start();





        }else if(desider==2){
            for(int i=0;i<14;i++){
                // create instance of Random class
                Random rand = new Random();

                int setNumber = rand.nextInt(4999)+1;

                if(setNumber>1210&&setNumber<2000){
                    setNumber=setNumber-1000;
                }  //NEED TO CHANGE HERE
                //NEED TO CHANGE HERE
                arrlist30.add(setNumber);
            }
            Random r=new Random();
            final int nu=r.nextInt(4)+2;


            new CountDownTimer(1000 * nu, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                }
                @Override
                public void onFinish() {
                    if(nu==3){
                        Toast.makeText(OneVsOneBOTScoreActivity.this, opponentUsername + " Rejected Your Request For A Rematch", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent=new Intent(OneVsOneBOTScoreActivity.this,OneVsOneBotPictureQuiz.class);
                        intent.putExtra("opponentImageUrl",opponentimageUrl);
                        intent.putExtra("opponentUserName",opponentUsername);
                        intent.putExtra("mypropic",myProPicUrl);
                        intent.putExtra("myName",myName123);
                        intent.putExtra("sumScore",  getIntent().getIntExtra("sumScore",0));
                        intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                        startActivity(intent);
                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                        finish();
                    }
                }
            }.start();

        }


    }







    public void greatFunSetter(){





        int botmin=2-botTime/60;
        int botsec=59-botTime%60;
        opponentScorebro= (180-botTime)*80;
        oppoTime.setText("Time Taken : "+botmin+" min "+botsec+" sec");

        opponentScorebro=opponentScorebro+2000;

        Random r=new Random();
        int lifelineSum=r.nextInt(3)+1;
        oppoLifelines.setText("Life-Lines Used : "+lifelineSum+"/4");
        for(int i=1;i<=lifelineSum;i++){
            opponentScorebro=opponentScorebro-500;
        }
        for (int i=1;i<=botCorrectAns;i++){
            opponentScorebro=opponentScorebro+1000;
        }


                                dialogBoxForDisiderFunction(opponentScorebro);
                                oppoScore.setText(" Total Score : "+opponentScorebro);



                                ArrayList<PieEntry> visitors1=new ArrayList<>();

                                visitors1.add(new PieEntry(totalSum,myName123));
                                visitors1.add(new PieEntry(opponentScorebro,opponentUsername));


                                PieDataSet pieDataSet=new PieDataSet(visitors1,"");
                                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                                pieDataSet.setValueTextColor(Color.BLACK);
                                pieDataSet.setValueLineColor(R.color.white);
                                pieDataSet.setValueTextSize(5f);

                                PieData pieData=new PieData(pieDataSet);

                                pieChart.setData(pieData);
                                pieChart.getDescription().setEnabled(false);
                                pieChart.setCenterText("Score");
                                pieChart.animateXY(1000,1000);




                                int accOppo=botCorrectAns*10;
                                oppoAccu.setText("Accuracy : "+accOppo+"%");
                                int myWrong=10-botCorrectAns;
                                oppoRatio.setText("Correct/Wrong : "+botCorrectAns+"/"+myWrong);

                                final ArrayList<BarEntry> visitors=new ArrayList<>();
                                visitors.add(new BarEntry(1,score));
                                visitors.add(new BarEntry(2, botCorrectAns));


                                BarDataSet barDataSet=new BarDataSet(visitors,"Correct Answer");
                                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                                barDataSet.setValueTextSize(10f);

                                BarData barData=new BarData(barDataSet);

                                barChart.setFitBars(true);
                                barChart.setData(barData);
                                barChart.getDescription().setText("Green Is Yours And Yellow Is For "+opponentUsername);
                                barChart.getDescription().setTextSize(3f);
                                barChart.animateY(2000);






    }


    public void highScorePushInLeaderBoard(){

        myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    username = (String) snapshot.getValue();

                    myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            try{
                                score123 =  snapshot.getValue(Integer.class);
                                if(score123<totalSum){
                                    score123= (int) totalSum;
                                }



                                myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("totalTime").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        timeHolder= snapshot.getValue(Integer.class);
                                        double manu= (minman*60)+secman;
                                        timeHolder= (int) (timeHolder+manu);



                                        myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("correct").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                correct= snapshot.getValue(Integer.class);

                                                wrong=10-score;

                                                correct=correct+score;

                                                myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("wrong").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        wrongfire=snapshot.getValue(Integer.class);
                                                        wrongfire=wrongfire+wrong;


                                                        myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                sumationOfScore=snapshot.getValue(Integer.class);
                                                                sumationOfScore= (int) (sumationOfScore+totalSum);
                                                                levelManupulation();

                                                                leaderBoardHolder s1 = new leaderBoardHolder(myName123,score123,timeHolder,correct,wrongfire,myProPicUrl,sumationOfScore);

                                                                myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {


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


                                sumationOfScore= (int) totalSum;

                                wrong=10-score;
                                int manu= (int) (minman*60)+secman;
                                int sums=(int)totalSum;
                                leaderBoardHolder s1 = new leaderBoardHolder(myName123,sums,manu,score,wrong,myProPicUrl,sumationOfScore);

                                myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        //  dataSetter();

                                    }
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






    public void cancelDialogFunction(){
        AlertDialog.Builder builderRemove=new AlertDialog.Builder(OneVsOneBOTScoreActivity.this,R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(OneVsOneBOTScoreActivity.this).inflate(R.layout.quit_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
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


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTScoreActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });

                Intent intent=new Intent(OneVsOneBOTScoreActivity.this,mainMenuActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();

            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(OneVsOneBOTScoreActivity.this, R.raw.finalbuttonmusic);
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





    public void onBackPressed() {
        try{
            cancelDialogFunction();
        }catch (Exception e){

        }
    }

}
    
