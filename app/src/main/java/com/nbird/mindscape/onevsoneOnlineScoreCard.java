package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
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

public class onevsoneOnlineScoreCard extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    int minrev,secriv;
    String opponentUID;
    String opponentimageUrl;
    String opponentUsername;
    String myProPicUrl;
    String myName123;
    int guard1=0;
    int guard2=0;
    int guard3=0;
    int guard4=0;
    int myWrong1234;
    TextView disDialog;
    int highscore;
    int opponentLifeLineInt;
    int oppoScore123;
    int score,lifelineSum,minutes,second,oppoScoreCounter,oppoWrongAnsCounter;
    long milliholder;
    long totalSum=0;
    int opponentScorebro;
    Button buttonYes,buttonNo;
    TextView titleDialog;
    LottieAnimationView animDialog,requestAnim;
    String opponentTimeTakenbro;
    ImageView onlineImage,opponentImage;
    TextView myName,myScore,myAcuu,myRatio,myTime,myLifelines,oppoName,oppoScore,oppoAccu,oppoRatio,oppoTime,oppoLifelines;
    List<Integer> arrlist,arroppo;
    LottieAnimationView anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;
    LottieAnimationView anim1oppo,anim2oppo,anim3oppo,anim4oppo,anim5oppo,anim6oppo,anim7oppo,anim8oppo,anim9oppo,anim10oppo;
    BarChart barChart;
    PieChart pieChart;
    Button home,rematch;
    int acceptInt,REQUESTReceived;
    int oppoScoreMate;
    List<Integer> arrlist30;
    String username;
    CountDownTimer countDownTimer123;
    ImageView levelImage;
    TextView levelText;
    int score123,timeHolder,correct,wrong,wrongfire,sumationOfScore;
    LottieAnimationView partypoper,party2;
    ImageView image1,image2;
    TextView oppoLevel;
    ImageView oppoBatch;
    int min12345,sec12345;
    String mine;
    Button leaderBoardButton;
    int minman,secman;
    List<leaderBoardHolder> list;
    RecyclerView recyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    recyclerViewLeaderBoardAdapter categoryAdapter;
    int desider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onevsone_online_score_card);

        arrlist30 = new ArrayList<>(13);

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
        partypoper = (LottieAnimationView)findViewById(R.id.partypoper);
        party2 = (LottieAnimationView)findViewById(R.id.party2);
        leaderBoardButton=(Button) findViewById(R.id.leaderBoardButton);
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


        timerSetter();
        dataSetterFun();
        requestFinderFunction();
        highScorePushInLeaderBoard();
        oppoLevelFunction();


        list=new ArrayList<>();

        categoryAdapter = new recyclerViewLeaderBoardAdapter(list);


       leaderBoardButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialogFunctionLeaderBoard();
           }
       });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("User").child(opponentUID).child("1vs1onlineCorrectAns").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineCurrentScore").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineLifeLineUsed").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineTimeTaken").removeValue();
                myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child("OnCompleteHolder").removeValue();
                Intent intent=new Intent(onevsoneOnlineScoreCard.this,mainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rematch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomNumberGeneratorFunction();
                myRef.child("User").child(opponentUID).child("1vs1onlineCorrectAns").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineCurrentScore").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineLifeLineUsed").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineTimeTaken").removeValue();
                myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child("OnCompleteHolder").removeValue();
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").child("request").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(onevsoneOnlineScoreCard.this, "Request Send To "+opponentUsername, Toast.LENGTH_LONG).show();

                        myRef.child("User").child(opponentUID).child("1vs1Online").child("accept").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    acceptInt=snapshot.getValue(Integer.class);
                                    if(acceptInt==1){
                                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").removeValue();
                                        myRef.child("User").child(opponentUID).child("1vs1Online").child("accept").removeValue();
                                        if(desider==0){
                                            Intent intent=new Intent(onevsoneOnlineScoreCard.this,onevsoneQuizActivity.class);
                                            intent.putExtra("opponentUID",opponentUID);
                                            intent.putExtra("opponentImageUrl",opponentimageUrl);
                                            intent.putExtra("opponentUserName",opponentUsername);
                                            intent.putExtra("mypropic",myProPicUrl);
                                            intent.putExtra("myName",myName123);
                                            intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                                            startActivity(intent);
                                            finish();
                                        }else{
                                            Intent intent=new Intent(onevsoneOnlineScoreCard.this,multiPlayerPictureQuiz.class);
                                            intent.putExtra("opponentUID",opponentUID);
                                            intent.putExtra("opponentImageUrl",opponentimageUrl);
                                            intent.putExtra("opponentUserName",opponentUsername);
                                            intent.putExtra("mypropic",myProPicUrl);
                                            intent.putExtra("myName",myName123);
                                            intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                                            startActivity(intent);
                                            finish();
                                        }

                                    }else {
                                        Toast.makeText(onevsoneOnlineScoreCard.this, "Request Declined By "+opponentUsername, Toast.LENGTH_SHORT).show();
                                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").removeValue();;
                                    }
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
        });

    }

    public void dataSetterFun(){

        Glide.with(getBaseContext()).load(opponentimageUrl).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(opponentImage);

        Glide.with(getBaseContext()).load(myProPicUrl).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(onlineImage);

        minrev=2-minman;
        secriv=59-secman;

        totalSum= ((60*minrev)+secriv)*80;
        for(int i=1;i<=lifelineSum;i++){
            totalSum=totalSum-1500;
        }
        for (int i=1;i<=score;i++){
            totalSum=totalSum+1000;
        }


       highestScoreSet();


        int accUser=score*10;




        myName.setText(myName123);
        myScore.setText("Total Score : "+totalSum+" ");
        myAcuu.setText("Accuracy : "+accUser+"%");

        myTime.setText(mine);
        myLifelines.setText("Life-Lines Used : "+lifelineSum+"/4");
        oppoName.setText(opponentUsername);

        int mer=(100-accUser)/10;
        myRatio.setText("Correct/Wrong : "+score+"/"+mer);


    }

    public void animManu(){

        try {
            for (int i = 0; i < 10; i++) {
                int numMy = arrlist.get(i);
                int numOppo = arroppo.get(i);

                switch (i + 1) {
                    case 1:
                        if (numMy == 1) {
                            anim1.setAnimation(R.raw.tickanim);
                            anim1.playAnimation();
                            anim1.loop(false);
                        } else  {
                            anim1.setAnimation(R.raw.wronganim);
                            anim1.playAnimation();
                            anim1.loop(false);
                        }
                        if (numOppo == 1) {
                            anim1oppo.setAnimation(R.raw.tickanim);
                            anim1oppo.playAnimation();
                            anim1oppo.loop(false);
                        } else  {
                            anim1oppo.setAnimation(R.raw.wronganim);
                            anim1oppo.playAnimation();
                            anim1oppo.loop(false);
                        }
                        break;
                    case 2:
                        if (numMy == 1) {
                            anim2.setAnimation(R.raw.tickanim);
                            anim2.playAnimation();
                            anim2.loop(false);
                        } else  {
                            anim2.setAnimation(R.raw.wronganim);
                            anim2.playAnimation();
                            anim2.loop(false);
                        }
                        if (numOppo == 1) {
                            anim2oppo.setAnimation(R.raw.tickanim);
                            anim2oppo.playAnimation();
                            anim2oppo.loop(false);
                        } else  {
                            anim2oppo.setAnimation(R.raw.wronganim);
                            anim2oppo.playAnimation();
                            anim2oppo.loop(false);
                        }

                        break;
                    case 3:
                        if (numMy == 1) {
                            anim3.setAnimation(R.raw.tickanim);
                            anim3.playAnimation();
                            anim3.loop(false);
                        } else  {
                            anim3.setAnimation(R.raw.wronganim);
                            anim3.playAnimation();
                            anim3.loop(false);
                        }
                        if (numOppo == 1) {
                            anim3oppo.setAnimation(R.raw.tickanim);
                            anim3oppo.playAnimation();
                            anim3oppo.loop(false);
                        } else  {
                            anim3oppo.setAnimation(R.raw.wronganim);
                            anim3oppo.playAnimation();
                            anim3oppo.loop(false);
                        }
                        break;
                    case 4:
                        if (numMy == 1) {
                            anim4.setAnimation(R.raw.tickanim);
                            anim4.playAnimation();
                            anim4.loop(false);
                        } else  {
                            anim4.setAnimation(R.raw.wronganim);
                            anim4.playAnimation();
                            anim4.loop(false);
                        }
                        if (numOppo == 1) {
                            anim4oppo.setAnimation(R.raw.tickanim);
                            anim4oppo.playAnimation();
                            anim4oppo.loop(false);
                        } else  {
                            anim4oppo.setAnimation(R.raw.wronganim);
                            anim4oppo.playAnimation();
                            anim4oppo.loop(false);
                        }
                        break;
                    case 5:
                        if (numMy == 1) {
                            anim5.setAnimation(R.raw.tickanim);
                            anim5.playAnimation();
                            anim5.loop(false);
                        } else  {
                            anim5.setAnimation(R.raw.wronganim);
                            anim5.playAnimation();
                            anim5.loop(false);
                        }
                        if (numOppo == 1) {
                            anim5oppo.setAnimation(R.raw.tickanim);
                            anim5oppo.playAnimation();
                            anim5oppo.loop(false);
                        } else  {
                            anim5oppo.setAnimation(R.raw.wronganim);
                            anim5oppo.playAnimation();
                            anim5oppo.loop(false);
                        }
                        break;
                    case 6:
                        if (numMy == 1) {
                            anim6.setAnimation(R.raw.tickanim);
                            anim6.playAnimation();
                            anim6.loop(false);
                        } else  {
                            anim6.setAnimation(R.raw.wronganim);
                            anim6.playAnimation();
                            anim6.loop(false);
                        }
                        if (numOppo == 1) {
                            anim6oppo.setAnimation(R.raw.tickanim);
                            anim6oppo.playAnimation();
                            anim6oppo.loop(false);
                        } else  {
                            anim6oppo.setAnimation(R.raw.wronganim);
                            anim6oppo.playAnimation();
                            anim6oppo.loop(false);
                        }
                        break;
                    case 7:
                        if (numMy == 1) {

                            anim7.setAnimation(R.raw.tickanim);
                            anim7.playAnimation();
                            anim7.loop(false);
                        } else  {
                            anim7.setAnimation(R.raw.wronganim);
                            anim7.playAnimation();
                            anim7.loop(false);
                        }

                        if (numOppo == 1) {
                            anim7oppo.setAnimation(R.raw.tickanim);
                            anim7oppo.playAnimation();
                            anim7oppo.loop(false);
                        } else  {
                            anim7oppo.setAnimation(R.raw.wronganim);
                            anim7oppo.playAnimation();
                            anim7oppo.loop(false);
                        }
                        break;
                    case 8:
                        if (numMy == 1) {

                            anim8.setAnimation(R.raw.tickanim);
                            anim8.playAnimation();
                            anim8.loop(false);
                        } else  {
                            anim8.setAnimation(R.raw.wronganim);
                            anim8.playAnimation();
                            anim8.loop(false);
                        }
                        if (numOppo == 1) {
                            anim8oppo.setAnimation(R.raw.tickanim);
                            anim8oppo.playAnimation();
                            anim8oppo.loop(false);
                        } else  {
                            anim8oppo.setAnimation(R.raw.wronganim);
                            anim8oppo.playAnimation();
                            anim8oppo.loop(false);
                        }
                        break;
                    case 9:
                        if (numMy == 1) {

                            anim9.setAnimation(R.raw.tickanim);
                            anim9.playAnimation();
                            anim9.loop(false);
                        } else  {
                            anim9.setAnimation(R.raw.wronganim);
                            anim9.playAnimation();
                            anim9.loop(false);
                        }
                        if (numOppo == 1) {
                            anim9oppo.setAnimation(R.raw.tickanim);
                            anim9oppo.playAnimation();
                            anim9oppo.loop(false);
                        } else  {
                            anim9oppo.setAnimation(R.raw.wronganim);
                            anim9oppo.playAnimation();
                            anim9oppo.loop(false);
                        }
                        break;
                    case 10:
                        if (numMy == 1) {
                            anim10.setAnimation(R.raw.tickanim);
                            anim10.playAnimation();
                            anim10.loop(false);
                        } else  {
                            anim10.setAnimation(R.raw.wronganim);
                            anim10.playAnimation();
                            anim10.loop(false);
                        }
                        if (numOppo == 1) {
                            anim10oppo.setAnimation(R.raw.tickanim);
                            anim10oppo.playAnimation();
                            anim10oppo.loop(false);
                        } else  {
                            anim10oppo.setAnimation(R.raw.wronganim);
                            anim10oppo.playAnimation();
                            anim10oppo.loop(false);
                        }
                        break;
                }
            }
        }catch (Exception e){

        }
    }

    public void dialogBoxForDisiderFunction(){
        AlertDialog.Builder builder=new AlertDialog.Builder(onevsoneOnlineScoreCard.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(onevsoneOnlineScoreCard.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        titleDialog=(TextView) view1.findViewById(R.id.textTitle);
        ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
        animDialog=(LottieAnimationView) view1.findViewById(R.id.imageIcon);

        if(totalSum>opponentScorebro){
             titleDialog.setText(myName123+" You Won!!. You have Beaten "+opponentUsername);  // Aakash changes to be done here
            animDialog.setAnimation(R.raw.winneranim);
            animDialog.playAnimation();
        }else if(totalSum==opponentScorebro){
            titleDialog.setText(myName123+" Match Is Draw!!");
            animDialog.setAnimation(R.raw.draawanim);
            animDialog.playAnimation();
        }else{
            titleDialog.setText(myName123+" You Lose. "+opponentUsername+" Won.");
            animDialog.setAnimation(R.raw.losseranim);
            animDialog.playAnimation();
        }

        partypoper.setAnimation(R.raw.partypoppersanim);
        partypoper.playAnimation();
        partypoper.loop(false);
        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                animManu();
            }
        });

    }

    public void requestFinderFunction(){
        myRef.child("User").child(opponentUID).child("1vs1Online").child("request").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    REQUESTReceived=snapshot.getValue(Integer.class);
                    requestDialogBoxFun();
                }catch(Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void requestDialogBoxFun(){
        if(REQUESTReceived==1){

            randomNumberGeneratorTaker();

            AlertDialog.Builder builder=new AlertDialog.Builder(onevsoneOnlineScoreCard.this,R.style.AlertDialogTheme);

            final View view1= LayoutInflater.from(onevsoneOnlineScoreCard.this).inflate(R.layout.request_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            builder.setCancelable(false);

            titleDialog=(TextView) view1.findViewById(R.id.textTitle);
            buttonYes=(Button) view1.findViewById(R.id.buttonYes);
            buttonNo=(Button) view1.findViewById(R.id.buttonNo);
            requestAnim=(LottieAnimationView) view1.findViewById(R.id.backlitemodeanim);

            final AlertDialog alertDialog=builder.create();
            if(alertDialog.getWindow()!=null){
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            alertDialog.show();

            titleDialog.setText(opponentUsername+" Has Send You A Request For A Rematch!");

            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();

                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").child("accept").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myRef.child("User").child(opponentUID).child("questionNUmberPicUP").removeValue();
                            if(desider==0){
                                Intent intent=new Intent(onevsoneOnlineScoreCard.this,onevsoneQuizActivity.class);
                                intent.putExtra("opponentUID",opponentUID);
                                intent.putExtra("opponentImageUrl",opponentimageUrl);
                                intent.putExtra("opponentUserName",opponentUsername);
                                intent.putExtra("mypropic",myProPicUrl);
                                intent.putExtra("myName",myName123);
                                intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                                startActivity(intent);
                                finish();
                            }else{
                                Intent intent=new Intent(onevsoneOnlineScoreCard.this,multiPlayerPictureQuiz.class);
                                intent.putExtra("opponentUID",opponentUID);
                                intent.putExtra("opponentImageUrl",opponentimageUrl);
                                intent.putExtra("opponentUserName",opponentUsername);
                                intent.putExtra("mypropic",myProPicUrl);
                                intent.putExtra("myName",myName123);
                                intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist30);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });



                }
            });

            buttonNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").child("accept").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myRef.child("User").child(opponentUID).child("questionNUmberPicUP").removeValue();
                            myRef.child("User").child(opponentUID).child("1vs1Online").removeValue();
                            alertDialog.dismiss();
                        }
                    });


                }
            });

        }
    }



    public void randomNumberGeneratorFunction(){
        if(desider==0){
            for(int i=0;i<14;i++){
                // create instance of Random class
                Random rand = new Random();

                // Generate random integers in range 0 to 29

                int setNumber = rand.nextInt(29)+1;  //NEED TO CHANGE HERE
                //NEED TO CHANGE HERE
                arrlist30.add(setNumber);
            }
        }else{
            for(int i=0;i<14;i++){
                // create instance of Random class
                Random rand = new Random();

                // Generate random integers in range 0 to 29

                int setNumber = rand.nextInt(13)+1;  //NEED TO CHANGE HERE
                //NEED TO CHANGE HERE
                arrlist30.add(setNumber);
            }
        }


        try{
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(1)).setValue(arrlist30.get(0)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(2)).setValue(arrlist30.get(1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(3)).setValue(arrlist30.get(2)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(4)).setValue(arrlist30.get(3)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(5)).setValue(arrlist30.get(4)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(6)).setValue(arrlist30.get(5)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(7)).setValue(arrlist30.get(6)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(8)).setValue(arrlist30.get(7)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(9)).setValue(arrlist30.get(8)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(10)).setValue(arrlist30.get(9)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(11)).setValue(arrlist30.get(10)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(12)).setValue(arrlist30.get(11)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(13)).setValue(arrlist30.get(12)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child("OnCompleteHolder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }
            });

        }catch(Exception e){

        }

    }

    public void randomNumberGeneratorTaker(){

        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child("OnCompleteHolder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int num47=snapshot.getValue(Integer.class);
                    if(num47==1){
                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child("OnCompleteHolder").removeValue();
                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(1)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(1)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(2)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(2)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(3)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(3)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(4)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(4)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(5)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(5)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(6)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(6)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(7)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(7)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(8)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(8)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(9)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(9)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(10)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(10)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(11)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(11)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(12)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(12)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(13)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    arrlist30.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(13)).removeValue();
                                }catch (Exception e){

                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void timerSetter(){
       new CountDownTimer(1000*10, 1000) {


            public void onTick(long millisUntilFinished) {
                greatFunSetter();
            }

            @Override
            public void onFinish() {
                myRef.child("User").child(opponentUID).child("1vs1onlineCurrentScore").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineLifeLineUsed").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineTimeTaken").removeValue();
                myRef.child("User").child(opponentUID).child("1vs1onlineCorrectAns").removeValue();
            }
        }.start();
    }


    public void greatFunSetter(){
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineCurrentScore").setValue(totalSum).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                myRef.child("User").child(opponentUID).child("1vs1onlineCurrentScore").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            opponentScorebro=snapshot.getValue(Integer.class);
                            if(guard1==0){
                                guard1=1;
                                oppoScore.setText(" Total Score : "+opponentScorebro);

                                dialogBoxForDisiderFunction();

                                final ArrayList<BarEntry> visitors=new ArrayList<>();
                                visitors.add(new BarEntry(1,score));
                                visitors.add(new BarEntry(2, oppoScoreCounter));


                                BarDataSet barDataSet=new BarDataSet(visitors,"Correct Answer");
                                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                                barDataSet.setValueTextSize(5f);

                                BarData barData=new BarData(barDataSet);

                                barChart.setFitBars(true);
                                barChart.setData(barData);
                                barChart.getDescription().setText("Green Is Yours And Yellow Is For "+opponentUsername);
                                barChart.animateY(2000);



                                ArrayList<PieEntry> visitors1=new ArrayList<>();

                                visitors1.add(new PieEntry(totalSum,myName123));
                                visitors1.add(new PieEntry(opponentScorebro,opponentUsername));


                                PieDataSet pieDataSet=new PieDataSet(visitors1,"Score Distribution");
                                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                                pieDataSet.setValueTextColor(Color.BLACK);
                                pieDataSet.setValueTextSize(5f);

                                PieData pieData=new PieData(pieDataSet);

                                pieChart.setData(pieData);
                                pieChart.getDescription().setEnabled(false);
                                pieChart.setCenterText("Score");
                                pieChart.animateXY(1000,1000);




                            }


                        }catch(Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineLifeLineUsed").setValue(lifelineSum).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                myRef.child("User").child(opponentUID).child("1vs1onlineLifeLineUsed").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            opponentLifeLineInt=snapshot.getValue(Integer.class);

                            if(guard2==0){
                                guard2=1;
                                oppoLifelines.setText("Life-Lines Used : "+opponentLifeLineInt+"/4");

                            }

                        }catch(Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        String timeStr="Total Time Taken : "+minrev+"min "+secriv+"sec";
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineTimeTaken").setValue(mine).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                myRef.child("User").child(opponentUID).child("1vs1onlineTimeTaken").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            opponentTimeTakenbro=snapshot.getValue(String.class);
                            if(opponentTimeTakenbro!=null){
                                if(guard3==0){
                                    guard3=1;
                                    oppoTime.setText(opponentTimeTakenbro);
                                }

                            }
                        }catch (Exception e){

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineCorrectAns").setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                myRef.child("User").child(opponentUID).child("1vs1onlineCorrectAns").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            oppoScoreMate=snapshot.getValue(Integer.class);
                            if(guard4==0){
                                guard4++;
                                int accOppo=oppoScoreMate*10;
                                oppoAccu.setText("Accuracy : "+accOppo+"%");
                                int myWrong=10-oppoScoreMate;
                                oppoRatio.setText("Correct/Wrong : "+oppoScoreMate+"/"+myWrong);

                            }


                        }catch(Exception e){

                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });
    }




    public void highestScoreSet(){

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("Score").child("1vs1HS").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    highscore =  snapshot.getValue(Integer.class);

                    if(highscore<totalSum){
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("Score").child("1vs1HS").setValue(totalSum).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });
                    }
                }catch(Exception e){
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
                                                                        myRef.child("leaderBoard").child("1vs1").orderByChild("score").limitToLast(100).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                            @Override
                                                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                                                                    list.add(dataSnapshot1.getValue(leaderBoardHolder.class));
                                                                                }
                                                                            }


                                                                            @Override
                                                                            public void onCancelled(@NonNull DatabaseError error) {

                                                                            }

                                                                            // ...
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
                                        levelImage.setBackgroundResource(R.drawable.blackiron);
                                        levelText.setText(" Lv. 1 ");
                                        wrong=10-score;
                                        int manu= (int) (minman*60)+secman;
                                        int sums=(int)totalSum;
                                        leaderBoardHolder s1 = new leaderBoardHolder(username,sums,manu,score,wrong,myProPicUrl,sumationOfScore);

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
        AlertDialog.Builder builder=new AlertDialog.Builder(onevsoneOnlineScoreCard.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(onevsoneOnlineScoreCard.this).inflate(R.layout.batch_display_dialog_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
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


    public void dialogFunctionLeaderBoard(){
        AlertDialog.Builder builder=new AlertDialog.Builder(onevsoneOnlineScoreCard.this,R.style.AlertDialogTheme);
        View view1= LayoutInflater.from(onevsoneOnlineScoreCard.this).inflate(R.layout.one_vs_one_leaderboard,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");


        recyclerView = (RecyclerView) view1.findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryAdapter);

        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
        categoryAdapter.notifyDataSetChanged();
        view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


    public void oppoLevelFunction(){
        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try{
                    oppoScore123=snapshot.getValue(Integer.class);
                    levelManupulation123();
                }catch (Exception e){
                    oppoBatch.setBackgroundResource(R.drawable.blackiron);
                    oppoLevel.setText(" Lv. 1 ");
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void levelManupulation123(){

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


}