package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class tournamentBuzzerScoreCard extends AppCompatActivity {

    ImageView img1,img2,img3,img4,propic1,propic2,propic3,propic4;
    TextView headText1,headText2,headText3,headText4,pos1,pos2,pos3,pos4;
    TextView name1,name2,name3,name4,totalTimeTaken1,totalTimeTaken2,totalTimeTaken3,totalTimeTaken4,accuracy1,accuracy2,accuracy3,accuracy4;
    TextView correctvswrong1,correctvswrong2,correctvswrong3,correctvswrong4,totalLifeLines1,totalLifeLines2,totalLifeLines3,totalLifeLines4,score1,score2,score3,score4;
    LottieAnimationView anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10;
    LottieAnimationView anim11,anim12,anim13,anim14,anim15,anim16,anim17,anim18,anim19,anim20;
    LottieAnimationView anim21,anim22,anim23,anim24,anim25,anim26,anim27,anim28,anim29,anim30;
    LottieAnimationView anim31,anim32,anim33,anim34,anim35,anim36,anim37,anim38,anim39,anim40;
    LottieAnimationView animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10;
    LottieAnimationView animz11,animz12,animz13,animz14,animz15,animz16,animz17,animz18,animz19,animz20;
    LottieAnimationView animz21,animz22,animz23,animz24,animz25,animz26,animz27,animz28,animz29,animz30;
    LottieAnimationView animz31,animz32,animz33,animz34,animz35,animz36,animz37,animz38,animz39,animz40;
    Button quitButton,lobbyButton;
    int sco1=-100,sco2=-100,sco3=-100,sco4=-100;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    ShimmerFrameLayout headShimmer,player1ShimmerPic,player2ShimmerPic,player3ShimmerPic,player4ShimmerPic,player1Shimmer,player2Shimmer,player3Shimmer,player4Shimmer;

    LinearLayout linearLayout2,linearLayout3,linearLayout4;
    String scoreString,accuString,correctVsWrongString,totalTimeString,totalLifeLineString;
    List<Integer> listAnim1,listAnim2,listAnim3,listAnim4;

    int correctNum,wrongNum,lifelineSum,minutes,second;
    long milliHolder47;
    int minrev,secrev;
    int counter;
    int totalSum;
    int numHoster;
    int acc;
    int m1;
    int roomCode,playerNum,questionNum,timerNum;
    String myName,myImageUrl,hostUid,hostName,hostImageUrl,name2String,name3String,name4String,image2Url,image3Url,image4Url;
    ValueEventListener listner45,lisner89;
    int mycorrect,mywrong;
    int numberOfPlayers;
    int roomEntry=0;
    CountDownTimer countDownTimer,countDownTimer1234,Countdownlast;
    int j=0,privacyFinder,numMode;
    correctWrongAnsweredBuzzerHolder m;
    LottieAnimationView partypoper,party2;
    ValueEventListener lisnernumber1,lisnernumber2,lisnernumber3,lisnerdog1,lisnercat1,lisnercat2,lisnercat3,lisnercat4,lisnercat5,lisnercat6,lisnercat7,lisnercat8,lisnercat9,lisnercat10;
    ValueEventListener lisnercat11,lisnercat12,lisnercat13,lisnercat14,lisnercat15,lisnercat16,lisnercat17,lisnercat18,lisnercat19,lisnercat20,lisnercat21,lisnercat22;
    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisner89);
        }catch (Exception e){

        }
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
        if(countDownTimer1234!=null){
            countDownTimer1234.cancel();
        }
        if(Countdownlast!=null){
            Countdownlast.cancel();
        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnerdog1);
        }catch (Exception e){

        }
        switch (playerNum){
            case 1:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 2:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 3:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 4:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
        }

        lisnerremover("player1Answer",1,lisnercat3);
        lisnerremover("player1Answer",2,lisnercat4);
        lisnerremover("player1Answer",3,lisnercat5);
        lisnerremover("player1Answer",4,lisnercat6);
        lisnerremover("player1Answer",5,lisnercat7);
        lisnerremover("player1Answer",6,lisnercat8);
        lisnerremover("player1Answer",7,lisnercat9);
        lisnerremover("player1Answer",8,lisnercat10);
        lisnerremover("player1Answer",9,lisnercat11);
        lisnerremover("player1Answer",10,lisnercat12);
        lisnerremover("player1Answer",11,lisnercat13);
        lisnerremover("player1Answer",2,lisnercat14);
        lisnerremover("player1Answer",13,lisnercat15);
        lisnerremover("player1Answer",14,lisnercat16);
        lisnerremover("player1Answer",15,lisnercat17);
        lisnerremover("player1Answer",16,lisnercat18);
        lisnerremover("player1Answer",17,lisnercat19);
        lisnerremover("player1Answer",18,lisnercat20);
        lisnerremover("player1Answer",19,lisnercat21);
        lisnerremover("player1Answer",20,lisnercat22);

        lisnerremover("player2Answer",1,lisnercat3);
        lisnerremover("player2Answer",2,lisnercat4);
        lisnerremover("player2Answer",3,lisnercat5);
        lisnerremover("player2Answer",4,lisnercat6);
        lisnerremover("player2Answer",5,lisnercat7);
        lisnerremover("player2Answer",6,lisnercat8);
        lisnerremover("player2Answer",7,lisnercat9);
        lisnerremover("player2Answer",8,lisnercat10);
        lisnerremover("player2Answer",9,lisnercat11);
        lisnerremover("player2Answer",10,lisnercat12);
        lisnerremover("player2Answer",11,lisnercat13);
        lisnerremover("player2Answer",2,lisnercat14);
        lisnerremover("player2Answer",13,lisnercat15);
        lisnerremover("player2Answer",14,lisnercat16);
        lisnerremover("player2Answer",15,lisnercat17);
        lisnerremover("player2Answer",16,lisnercat18);
        lisnerremover("player2Answer",17,lisnercat19);
        lisnerremover("player2Answer",18,lisnercat20);
        lisnerremover("player2Answer",19,lisnercat21);
        lisnerremover("player2Answer",20,lisnercat22);

        lisnerremover("player3Answer",1,lisnercat3);
        lisnerremover("player3Answer",2,lisnercat4);
        lisnerremover("player3Answer",3,lisnercat5);
        lisnerremover("player3Answer",4,lisnercat6);
        lisnerremover("player3Answer",5,lisnercat7);
        lisnerremover("player3Answer",6,lisnercat8);
        lisnerremover("player3Answer",7,lisnercat9);
        lisnerremover("player3Answer",8,lisnercat10);
        lisnerremover("player3Answer",9,lisnercat11);
        lisnerremover("player3Answer",10,lisnercat12);
        lisnerremover("player3Answer",11,lisnercat13);
        lisnerremover("player3Answer",2,lisnercat14);
        lisnerremover("player3Answer",13,lisnercat15);
        lisnerremover("player3Answer",14,lisnercat16);
        lisnerremover("player3Answer",15,lisnercat17);
        lisnerremover("player3Answer",16,lisnercat18);
        lisnerremover("player3Answer",17,lisnercat19);
        lisnerremover("player3Answer",18,lisnercat20);
        lisnerremover("player3Answer",19,lisnercat21);
        lisnerremover("player3Answer",20,lisnercat22);

        lisnerremover("player4Answer",1,lisnercat3);
        lisnerremover("player4Answer",2,lisnercat4);
        lisnerremover("player4Answer",3,lisnercat5);
        lisnerremover("player4Answer",4,lisnercat6);
        lisnerremover("player4Answer",5,lisnercat7);
        lisnerremover("player4Answer",6,lisnercat8);
        lisnerremover("player4Answer",7,lisnercat9);
        lisnerremover("player4Answer",8,lisnercat10);
        lisnerremover("player4Answer",9,lisnercat11);
        lisnerremover("player4Answer",10,lisnercat12);
        lisnerremover("player4Answer",11,lisnercat13);
        lisnerremover("player4Answer",2,lisnercat14);
        lisnerremover("player4Answer",13,lisnercat15);
        lisnerremover("player4Answer",14,lisnercat16);
        lisnerremover("player4Answer",15,lisnercat17);
        lisnerremover("player4Answer",16,lisnercat18);
        lisnerremover("player4Answer",17,lisnercat19);
        lisnerremover("player4Answer",18,lisnercat20);
        lisnerremover("player4Answer",19,lisnercat21);
        lisnerremover("player4Answer",20,lisnercat22);

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").removeEventListener(lisnercat1);
        }catch (Exception e){

        }

        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
        }catch (Exception e){

        }


        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("correctWrongAnswered").child("Player1").removeEventListener(lisnercat2);
        }catch (Exception e){

        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("correctWrongAnswered").child("Player2").removeEventListener(lisnercat2);
        }catch (Exception e){

        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("correctWrongAnswered").child("Player3").removeEventListener(lisnercat2);
        }catch (Exception e){

        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("correctWrongAnswered").child("Player4").removeEventListener(lisnercat2);
        }catch (Exception e){

        }
        Runtime.getRuntime().gc();

       
    }

    public void lisnerremover(String str,int i,ValueEventListener lisner){
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(str).child(String.valueOf(i)).removeEventListener(lisner);
        }catch (Exception e){

        }

    }

    public void fishManu(final int kali){
        myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").setValue(kali).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(tournamentBuzzerScoreCard.this, tournamentLobbyActivity.class);
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                intent.putExtra("hostUid", hostUid);
                intent.putExtra("hostImage",hostImageUrl);
                intent.putExtra("hostName",hostName);
                intent.putExtra("isHost",0);
                intent.putExtra("roomCode",roomCode);
                intent.putExtra("Playernum",kali);
                intent.putExtra("privacy",privacyFinder);
                switch (playerNum){
                    case 1:
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber1);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                        }catch (Exception e){

                        }break;
                    case 2:
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                        }catch (Exception e){

                        }break;
                    case 3:
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                        }catch (Exception e){

                        }break;
                    case 4:
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                        }catch (Exception e){

                        }
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber3);
                        }catch (Exception e){

                        }break;
                }

                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();

                }catch(Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                }catch(Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();

                }catch(Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();

                }catch(Exception e){

                }


                //  myRef.child("room").child(hostUid).child("numberOfPlayers").removeEventListener(listner99);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_buzzer_score_card);

        partypoper=(LottieAnimationView) findViewById(R.id.partypoper);
        party2=(LottieAnimationView) findViewById(R.id.party2);


        headShimmer=(ShimmerFrameLayout) findViewById(R.id.headShimmer);
        player1Shimmer=(ShimmerFrameLayout) findViewById(R.id.player1Shimmer);
        player2Shimmer=(ShimmerFrameLayout) findViewById(R.id.player2Shimmer);
        player3Shimmer=(ShimmerFrameLayout) findViewById(R.id.player3Shimmer);
        player4Shimmer=(ShimmerFrameLayout) findViewById(R.id.player4Shimmer);

        player1ShimmerPic=(ShimmerFrameLayout) findViewById(R.id.player1ShimmerPic);
        player2ShimmerPic=(ShimmerFrameLayout) findViewById(R.id.player2ShimmerPic);
        player3ShimmerPic=(ShimmerFrameLayout) findViewById(R.id.player3ShimmerPic);
        player4ShimmerPic=(ShimmerFrameLayout) findViewById(R.id.player4ShimmerPic);

        img1=(ImageView) findViewById(R.id.img1);
        img2=(ImageView) findViewById(R.id.img2);
        img3=(ImageView) findViewById(R.id.img3);
        img4=(ImageView) findViewById(R.id.img4);

        linearLayout2=(LinearLayout) findViewById(R.id.linearLayout36);
        linearLayout3=(LinearLayout) findViewById(R.id.linearLayout77);
        linearLayout4=(LinearLayout) findViewById(R.id.linearLayout96);

        propic1=(ImageView) findViewById(R.id.propic1);
        propic2=(ImageView) findViewById(R.id.propic2);
        propic3=(ImageView) findViewById(R.id.propic3);
        propic4=(ImageView) findViewById(R.id.propic4);

        headText1=(TextView) findViewById(R.id.headtext1);
        headText2=(TextView) findViewById(R.id.headtext2);
        headText3=(TextView) findViewById(R.id.headtext3);
        headText4=(TextView) findViewById(R.id.headtext4);
        pos1=(TextView) findViewById(R.id.pos1);
        pos2=(TextView) findViewById(R.id.pos2);
        pos3=(TextView) findViewById(R.id.pos3);
        pos4=(TextView) findViewById(R.id.pos4);

        name1=(TextView) findViewById(R.id.name1);
        name2=(TextView) findViewById(R.id.name2);
        name3=(TextView) findViewById(R.id.name3);
        name4=(TextView) findViewById(R.id.name4);


        accuracy1=(TextView) findViewById(R.id.accuracy1);
        accuracy2=(TextView) findViewById(R.id.accuracy2);
        accuracy3=(TextView) findViewById(R.id.accuracy3);
        accuracy4=(TextView) findViewById(R.id.accuracy4);

        correctvswrong1=(TextView) findViewById(R.id.correctvswrong1);
        correctvswrong2=(TextView) findViewById(R.id.correctvswrong2);
        correctvswrong3=(TextView) findViewById(R.id.correctvswrong3);
        correctvswrong4=(TextView) findViewById(R.id.correctvswrong4);


        score1=(TextView) findViewById(R.id.score1);
        score2=(TextView) findViewById(R.id.score2);
        score3=(TextView) findViewById(R.id.score3);
        score4=(TextView) findViewById(R.id.score4);

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

        quitButton=(Button) findViewById(R.id.quitButton);
        lobbyButton=(Button) findViewById(R.id.lobbyButton);

        quitButton.setEnabled(false);
        quitButton.setAlpha((float) 0.6);
        lobbyButton.setAlpha((float) 0.6);
        lobbyButton.setEnabled(false);



        privacyFinder=getIntent().getIntExtra("privacy",0);
        correctNum=getIntent().getIntExtra("score",0);
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
        questionNum=getIntent().getIntExtra("questionNum",0);
        timerNum=getIntent().getIntExtra("timerNum",0);
        counter=getIntent().getIntExtra("counter",0);
        mycorrect=getIntent().getIntExtra("correctMy",0);
        mywrong=getIntent().getIntExtra("wrongMy",0);
        numMode=getIntent().getIntExtra("numMode",0);

        partypoper.setVisibility(View.VISIBLE);
        partypoper.setAnimation(R.raw.partypoppersanim);
        partypoper.playAnimation();
        partypoper.loop(false);
        party2.setVisibility(View.VISIBLE);
        party2.setAnimation(R.raw.party3);
        party2.playAnimation();
        party2.loop(false);

        numberOfTimesPlayed();
        if(playerNum==2||playerNum==3||playerNum==4){
            lisnercat1=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        j=snapshot.getValue(Integer.class);
                        if(j==1){
                            try {
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                            }catch (Exception e){

                            }
                            if(Countdownlast!=null){
                                Countdownlast.cancel();
                            }
                            lobbyButton.setEnabled(false);
                            lobbyButton.setAlpha(0.8f);
                            lobbyButton.setText("Host Has Left The Room");
                            lobbyButton.setTextSize(10);
                            numberOfPlayersReducerNotHostBecauseOfHost(1);
                            numberOfPlayersReducerNotHostBecauseOfHost(0);
                            quitorjoinasker();


                        }
                    }catch (Exception e){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").addValueEventListener(lisnercat1);
        }

        if(playerNum==2||playerNum==3||playerNum==4){

         /*   myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int z=snapshot.getValue(Integer.class);
                        if(z==0){
                            sco1=-20000;
                            Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(img1);
                            Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(propic1);
                            headText1.setText(hostName);
                            name1.setText(hostName+" (Left)");
                            accuracy1.setText("Accuracy : 0%");
                            correctvswrong1.setText("Correct/Wrong : 0/0");
                            score1.setText("Total Score : 0");
                            sco2=0;
                            player1Shimmer.stopShimmerAnimation();
                            player1Shimmer.setVisibility(View.GONE);
                            player1ShimmerPic.stopShimmerAnimation();
                            player1ShimmerPic.setVisibility(View.GONE);
                        }
                    }catch (Exception e){
                        Toast.makeText(tournamentBuzzerScoreCard.this, "Host Left The Game!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/




            listner45=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        int combo=snapshot.getValue(Integer.class);
                    }catch (Exception e){
                        int k=0;
                        if(playerNum==2){
                            k=1;
                        }else if(playerNum==3){
                            k=3;
                        }else if(playerNum==4){
                            k=6;
                        }
                        Countdownlast=new CountDownTimer(1000*k,1000){
                            @Override
                            public void onTick(long l) {

                            }
                            @Override
                            public void onFinish() {

                                myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        try{
                                            m1=snapshot.getValue(Integer.class);
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }

                                            if(m1<4){
                                                if(m1==1){
                                                    fishManu(2);
                                                }else if(m1==2){
                                                    fishManu(3);
                                                }else if(m1==3){
                                                    fishManu(4);
                                                }

                                            }else{
                                                Intent intent = new Intent(tournamentBuzzerScoreCard.this, mainMenuActivity.class);
                                                startActivity(intent);
                                                Toast.makeText(tournamentBuzzerScoreCard.this, "Room Is Full!", Toast.LENGTH_LONG).show();
                                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                                finish();
                                               

                                            }

                                        }catch (Exception e){


                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }.start();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            };
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").addValueEventListener(listner45);
        }


        if(playerNum==1){
            myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        numberOfPlayers=snapshot.getValue(Integer.class);
                        listAnim1=new ArrayList<Integer>();
                        listAnim2=new ArrayList<Integer>();
                        listAnim3=new ArrayList<Integer>();
                        listAnim4=new ArrayList<Integer>();

                        if(numberOfPlayers==4){
                            mainManu2("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                            mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);
                            mainManu4("player4Status",image4Url,name4String,headText4,name4,accuracy4,correctvswrong4,score4,img4,propic4,player4ShimmerPic,player4Shimmer);
                        }else if(numberOfPlayers==3){
                            mainManu2("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                            mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);

                        }else if(numberOfPlayers==2){
                            mainManu2("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                        }

                        showStopper(numberOfPlayers);

                        dataManupulator();
                        waiter();
                        timerWaiting();
                    }catch (Exception e){
                        myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    numberOfPlayers=snapshot.getValue(Integer.class);
                                    listAnim1=new ArrayList<Integer>();
                                    listAnim2=new ArrayList<Integer>();
                                    listAnim3=new ArrayList<Integer>();
                                    listAnim4=new ArrayList<Integer>();

                                    if(numberOfPlayers==4){
                                        mainManu2("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                        mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);
                                        mainManu4("player4Status",image4Url,name4String,headText4,name4,accuracy4,correctvswrong4,score4,img4,propic4,player4ShimmerPic,player4Shimmer);
                                    }else if(numberOfPlayers==3){
                                        mainManu2("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                        mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);

                                    }else if(numberOfPlayers==2){
                                        mainManu2("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                    }


                                    showStopper(numberOfPlayers);
                                    dataManupulator();
                                    waiter();
                                    timerWaiting();
                                }catch (Exception e){

                                }
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
        }else{
            myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        numberOfPlayers=snapshot.getValue(Integer.class);
                        listAnim1=new ArrayList<Integer>();
                        listAnim2=new ArrayList<Integer>();
                        listAnim3=new ArrayList<Integer>();
                        listAnim4=new ArrayList<Integer>();

                        switch (playerNum){
                            case 2:
                                if(numberOfPlayers==4){
                                    mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                    mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);
                                    mainManu4("player4Status",image4Url,name4String,headText4,name4,accuracy4,correctvswrong4,score4,img4,propic4,player4ShimmerPic,player4Shimmer);
                                }else if(numberOfPlayers==3){
                                    mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                    mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);

                                }else if(numberOfPlayers==2){
                                    mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                }break;
                            case 3:
                                if(numberOfPlayers==4){
                                    mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                    mainManu3("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                    mainManu4("player4Status",image4Url,name4String,headText4,name4,accuracy4,correctvswrong4,score4,img4,propic4,player4ShimmerPic,player4Shimmer);
                                }else if(numberOfPlayers==3){
                                    mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                    mainManu3("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);

                                }break;
                            case 4:
                                if(numberOfPlayers==4){
                                    mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                    mainManu3("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                    mainManu4("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);
                                }break;
                        }





                        showStopper(numberOfPlayers);
                        dataManupulator();
                        waiter();
                        timerWaiting();
                    }catch (Exception e){
                        myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    numberOfPlayers=snapshot.getValue(Integer.class);
                                    listAnim1=new ArrayList<Integer>();
                                    listAnim2=new ArrayList<Integer>();
                                    listAnim3=new ArrayList<Integer>();
                                    listAnim4=new ArrayList<Integer>();


                                    switch (playerNum){
                                        case 2:
                                            if(numberOfPlayers==4){
                                                mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                                mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);
                                                mainManu4("player4Status",image4Url,name4String,headText4,name4,accuracy4,correctvswrong4,score4,img4,propic4,player4ShimmerPic,player4Shimmer);
                                            }else if(numberOfPlayers==3){
                                                mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                                mainManu3("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);

                                            }else if(numberOfPlayers==2){
                                                mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                            }break;
                                        case 3:
                                            if(numberOfPlayers==4){
                                                mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                                mainManu3("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                                mainManu4("player4Status",image4Url,name4String,headText4,name4,accuracy4,correctvswrong4,score4,img4,propic4,player4ShimmerPic,player4Shimmer);
                                            }else if(numberOfPlayers==3){
                                                mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                                mainManu3("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);

                                            }break;
                                        case 4:
                                            if(numberOfPlayers==4){
                                                mainManu2("player1Status",hostImageUrl,hostName,headText1,name1,accuracy1,correctvswrong1,score1,img1,propic1,player1ShimmerPic,player1Shimmer);
                                                mainManu3("player2Status",image2Url,name2String,headText2,name2,accuracy2,correctvswrong2,score2,img2,propic2,player2ShimmerPic,player2Shimmer);
                                                mainManu4("player3Status",image3Url,name3String,headText3,name3,accuracy3,correctvswrong3,score3,img3,propic3,player3ShimmerPic,player3Shimmer);
                                            }break;
                                    }




                                    showStopper(numberOfPlayers);
                                    dataManupulator();
                                    waiter();
                                    timerWaiting();
                                }catch (Exception e){

                                }
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

        if(playerNum==1){

        }else{
            playerOnlineStatusManupulator("player1Status");
        }

        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);

                }catch (Exception e){

                }
                if(playerNum==1){
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    del(mAuth.getCurrentUser().getUid());
                }else{
                    try {
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                    }catch (Exception e){

                    }
                    if(Countdownlast!=null){
                        Countdownlast.cancel();
                    }
                    del(hostUid);
                }
            }
        });

        if(playerNum==1){

            lobbyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).removeValue();
                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myRef.child("Lobby").child(String.valueOf(roomCode)).removeValue();
                         //   myRef.child("room").child(mAuth.getCurrentUser().getUid()).removeValue();
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();
                            }catch (Exception e){

                            }
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                            }catch (Exception e){

                            }
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                            }catch (Exception e){

                            }
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                            }catch (Exception e){

                            }


                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("roomCode").setValue(roomCode).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });

                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("gameStarter").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });

                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("gameFinder").setValue(0).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                }
                            });


                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer1234!=null){
                                countDownTimer1234.cancel();
                            }

                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentLobbyActivity.class);
                            intent.putExtra("Playernum",1);
                            intent.putExtra("roomCode",roomCode);
                            intent.putExtra("privacy",privacyFinder);

                            intent.putExtra("numQuestion",questionNum);
                            intent.putExtra("numTime",timerNum);
                            intent.putExtra("numMode",numMode);


                            startActivity(intent);
                            finish();
                           

                        }
                    });
                }
            });
        }else{
            lobbyButton.setText("Please Wait! Waiting For Host To Join The Room First");
            lobbyButton.setEnabled(false);
            lobbyButton.setTextSize(10);
        }



    }

    public void cancelDialogFunction(){
        AlertDialog.Builder builderRemove=new AlertDialog.Builder(tournamentBuzzerScoreCard.this,R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(tournamentBuzzerScoreCard.this).inflate(R.layout.quit_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
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
                try {
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                }catch (Exception e){

                }
                if(Countdownlast!=null){
                    Countdownlast.cancel();
                }
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if(playerNum==1){
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    del(mAuth.getCurrentUser().getUid());
                }else{
                    del(hostUid);
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
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



    public void dataGetter(String playerName, final TextView correctvswrong, final TextView accuracy){

        try{
            lisnercat2=new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        m=snapshot.getValue(correctWrongAnsweredBuzzerHolder.class);
                        correctvswrong.setText("Correct/Wrong : "+m.getCorrect()+"/"+m.getWrong());
                        accuracy.setText("Accuracy : "+((m.getCorrect()*100)/(m.getCorrect()+m.getWrong()))+"%");
                    }catch (Exception e){

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            }; myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("correctWrongAnswered").child(playerName).addValueEventListener(lisnercat2);
        }catch (Exception e){

        }

    }

    public void mainMenuBro(){
        switch (playerNum){
            case 1:
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player2Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player3Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player4Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dataGetter("Player2",correctvswrong2,accuracy2);
                dataGetter("Player3",correctvswrong3,accuracy3);
                dataGetter("Player4",correctvswrong4,accuracy4);break;
            case 2:
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player1Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player3Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player4Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                dataGetter("Player1",correctvswrong1,accuracy1);
                dataGetter("Player3",correctvswrong3,accuracy3);
                dataGetter("Player4",correctvswrong4,accuracy4);break;
            case 3:
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player2Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player1Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player4Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dataGetter("Player2",correctvswrong2,accuracy2);
                dataGetter("Player1",correctvswrong1,accuracy1);
                dataGetter("Player4",correctvswrong4,accuracy4);break;
            case 4:
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player2Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player3Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try{
                            if(snapshot.getValue(Integer.class)==1){
                                scoreGetter("player1Score");
                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dataGetter("Player2",correctvswrong2,accuracy2);
                dataGetter("Player3",correctvswrong3,accuracy3);
                dataGetter("Player1",correctvswrong1,accuracy1);break;
        }
    }

    public void scoreGetter(final String playerData){
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("Buzzer").child("BuzzerScore").child(playerData).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        if(playerData.equals("player1Score")){

                                sco1=snapshot.getValue(Integer.class);
                                score1.setText("Score : "+sco1);


                            Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(img1);
                            Glide.with(getBaseContext()).load(hostImageUrl).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(propic1);
                            headText1.setText(hostName);
                            name1.setText(hostName);
                            player1Shimmer.stopShimmerAnimation();
                            player1Shimmer.setVisibility(View.GONE);
                            player1ShimmerPic.stopShimmerAnimation();
                            player1ShimmerPic.setVisibility(View.GONE);
                        }else if(playerData.equals("player2Score")){
                            sco2=snapshot.getValue(Integer.class);
                            score2.setText("Score : "+sco2);
                            Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(img2);
                            Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(propic2);
                            headText2.setText(name2String);
                            name2.setText(name2String);
                            player2Shimmer.stopShimmerAnimation();
                            player2Shimmer.setVisibility(View.GONE);
                            player2ShimmerPic.stopShimmerAnimation();
                            player2ShimmerPic.setVisibility(View.GONE);
                        }else if(playerData.equals("player3Score")){
                            sco3=snapshot.getValue(Integer.class);
                            score3.setText("Score : "+sco3);
                            Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(img3);
                            Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(propic3);
                            headText3.setText(name3String);
                            name3.setText(name3String);
                            player3Shimmer.stopShimmerAnimation();
                            player3Shimmer.setVisibility(View.GONE);
                            player3ShimmerPic.stopShimmerAnimation();
                            player3ShimmerPic.setVisibility(View.GONE);
                        }else if(playerData.equals("player4Score")){
                            sco4=snapshot.getValue(Integer.class);
                            score4.setText("Score : "+sco4);
                            Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(img4);
                            Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                    .bitmapTransform(new RoundedCorners(14)))
                                    .into(propic4);
                            headText4.setText(name4String);
                            name4.setText(name4String);
                            player4Shimmer.stopShimmerAnimation();
                            player4Shimmer.setVisibility(View.GONE);
                            player4ShimmerPic.stopShimmerAnimation();
                            player4ShimmerPic.setVisibility(View.GONE);
                        }
                    }catch (Exception e){

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
    public void onBackPressed() {
        try{
            cancelDialogFunction();
        }catch (Exception e){

        }

    }


    public void deleter(String playerAnswer, String playerData, String playerStatus, String playerUid){
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).removeValue();

        }catch (Exception e){

        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerData).removeValue();

        }catch (Exception e){

        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeValue();

        }catch (Exception e){

        }
        try{
            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerUid).removeValue();
        }catch (Exception e){

        }

    }

    public void holeDeleteIfRoomIsEmpty(String uid){
        myRef.child("room").child(String.valueOf(1)).child(uid).removeValue();
        myRef.child("room").child(String.valueOf(0)).child(uid).removeValue();
        myRef.child("Lobby").child(String.valueOf(roomCode)).removeValue();
    }

    public void oppoFinder(){
        switch (numberOfPlayers){
            case 1:
                if(sco1!=-100){
                    roomEntry=1;

                }break;
            case 2:

                if(sco2!=-50&&sco2!=-100&&sco1!=-100){
                    roomEntry=2;
                 //   Toast.makeText(this, "1", Toast.LENGTH_LONG).show();
                }else if(sco2!=-50&&sco2!=-100&&sco3==0&&sco1!=-100){
                    roomEntry=2;
               //     Toast.makeText(this, "2", Toast.LENGTH_LONG).show();
                } else if(sco3!=-50&&sco3!=-100&&sco2==-50&&sco1!=-100){
                    roomEntry=2;
               //     Toast.makeText(this, "3", Toast.LENGTH_LONG).show();
                }else if(sco2!=-100&&sco2!=-50&&sco3==-50&&sco4==-50&&sco1!=-100){
                    roomEntry=2;
               //     Toast.makeText(this, "4", Toast.LENGTH_LONG).show();
                }else if(sco3!=-100&&sco3!=-50&&sco2==-50&&sco4==-50&&sco1!=-100){
                    roomEntry=2;
                //    Toast.makeText(this, "5", Toast.LENGTH_LONG).show();
                }else if(sco4!=-100&&sco4!=-50&&sco3==-50&&sco2==-50&&sco1!=-100){
                    roomEntry=2;
               //     Toast.makeText(this, "6", Toast.LENGTH_LONG).show();
                }
                break;
            case 3:

                if(sco2!=-50&&sco2!=-100&&sco3!=-50&&sco3!=-100&&sco4==-50&&sco1!=-100){
                    roomEntry=3;
                }else if(sco2!=-50&&sco2!=-100&&sco4!=-50&&sco4!=-100&&sco3==-50&&sco1!=-100){
                    roomEntry=3;
                }else if(sco3!=-50&&sco3!=-100&&sco4!=-50&&sco4!=-100&&sco2==-50&&sco1!=-100){
                    roomEntry=3;
                }else if(sco2!=-50&&sco2!=-100&&sco3!=-50&&sco3!=-100&&sco4==-100&&sco1!=-100){
                    roomEntry=3;
                }
                break;
            case 4:
                if(sco1!=-100&&sco2!=-100&&sco3!=-100&&sco4!=-100){
                    roomEntry=4;

                }break;
        }
    }

    public void del47(String u){
        myRef.child("room").child(String.valueOf(1)).child(u).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try{
                    int num=snapshot.getValue(Integer.class);
                    num--;
                    if(num==0){
                        if(playerNum==1){


                            holeDeleteIfRoomIsEmpty(mAuth.getCurrentUser().getUid());
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer1234!=null){
                                countDownTimer1234.cancel();
                            }
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();
                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);

                            finish();
                           

                        }else{


                            holeDeleteIfRoomIsEmpty(hostUid);
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer1234!=null){
                                countDownTimer1234.cancel();
                            }
                            if(playerNum==2){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }


                            }else if(playerNum==3){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }
                            }else if(playerNum==4){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }
                            }
                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                           

                        }
                    }else {
                        if(playerNum==1){
                            final int finalNum = num;
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(finalNum).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();
                                         //   Intent intent23=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                          //  startActivity(intent23);
                                          //  overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                          //  finish();
                                        }
                                    });

                                }
                            });
                        }else{
                            if(j==1){
                                switch (playerNum){
                                    case 2:
                                        deleter("player2Answer","player2Data","player2Status","player2Uid");
                                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();break;
                                    case 3:
                                        deleter("player3Answer","player3Data","player3Status","player3Uid");
                                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();break;
                                    case 4:
                                        deleter("player4Answer","player4Data","player4Status","player4Uid");
                                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();break;
                                }

                                myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int m=snapshot.getValue(Integer.class);
                                        m--;
                                        myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(countDownTimer!=null){
                                                    countDownTimer.cancel();
                                                }
                                                if(countDownTimer1234!=null){
                                                    countDownTimer1234.cancel();
                                                }
                                                Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                                finish();
                                               

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }else{
                                myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(playerNum==2){

                                            deleter("player2Answer","player2Data","player2Status","player2Uid");
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);

                                            finish();
                                           

                                        }else if(playerNum==3){

                                            deleter("player3Answer", "player3Data", "player3Status", "player3Uid");
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                           

                                        }else{
                                            deleter("player4Answer", "player4Data", "player4Status", "player4Uid");
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                           

                                        }

                                    }
                                });
                            }


                        }

                    }

                }catch (Exception e){

                    switch (playerNum){
                        case 2:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                           break;
                        case 3:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                           break;
                        case 4:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                           break;
                    }
                    Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                   

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("room").child(String.valueOf(0)).child(u).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num = snapshot.getValue(Integer.class);
                    num--;
                    if (num == 0) {
                        if (playerNum == 1) {

                            holeDeleteIfRoomIsEmpty(mAuth.getCurrentUser().getUid());
                            if (countDownTimer != null) {
                                countDownTimer.cancel();
                            }
                            if (countDownTimer1234 != null) {
                                countDownTimer1234.cancel();
                            }
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();

                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                           

                        } else {

                            holeDeleteIfRoomIsEmpty(hostUid);
                            if (countDownTimer != null) {
                                countDownTimer.cancel();
                            }
                            if (countDownTimer1234 != null) {
                                countDownTimer1234.cancel();
                            }
                            if(playerNum==2){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                                }catch (Exception e){

                                }

                            }else if(playerNum==3){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();

                                }catch (Exception e){

                                }

                            }else if(playerNum==4){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();

                                }catch (Exception e){

                                }

                            }
                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                           

                        }
                    } else {
                        if (playerNum == 1) {
                            final int finalNum = num;
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(finalNum).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();

                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                           

                                        }
                                    });


                                }
                            });
                        } else {
                            if (j == 1) {
                                switch (playerNum) {
                                    case 2:
                                        try{
                                            deleter("player2Answer", "player2Data", "player2Status", "player2Uid");

                                        }catch (Exception e){

                                        }
                                        try{
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                                        }catch (Exception e){

                                        }

                                        break;
                                    case 3:
                                        try{
                                            deleter("player3Answer", "player3Data", "player3Status", "player3Uid");
                                        }catch (Exception e){

                                        }
                                        try{
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                        }catch (Exception e){

                                        }


                                        break;
                                    case 4:
                                        try{
                                            deleter("player4Answer", "player4Data", "player4Status", "player4Uid");
                                        }catch (Exception e){

                                        }
                                        try{
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                        }catch (Exception e){

                                        }


                                        break;
                                }

                                myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int m = snapshot.getValue(Integer.class);
                                        m--;
                                        myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (countDownTimer != null) {
                                                    countDownTimer.cancel();
                                                }
                                                if (countDownTimer1234 != null) {
                                                    countDownTimer1234.cancel();
                                                }
                                                Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                                startActivity(intent);
                                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                                finish();
                                               

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            } else {
                                myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (playerNum == 2) {

                                            deleter("player2Answer", "player2Data", "player2Status", "player2Uid");
                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            try{
                                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                                            }catch (Exception e){

                                            }

                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                           

                                        } else if (playerNum == 3) {

                                            deleter("player3Answer", "player3Data", "player3Status", "player3Uid");
                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            try{
                                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                            }catch (Exception e){

                                            }

                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                           

                                        } else {
                                            deleter("player4Answer", "player4Data", "player4Status", "player4Uid");
                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            try{
                                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                            }catch (Exception e){

                                            }

                                            Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                           

                                        }

                                    }
                                });
                            }


                        }

                    }


                } catch (Exception e) {
                    Intent intent=new Intent(tournamentBuzzerScoreCard.this,tournamentChoiceActicity.class);
                    startActivity(intent);
                    switch (playerNum){
                        case 2:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                           break;
                        case 3:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                           break;
                        case 4:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                           break;
                    }
                    finish();
                   

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void del(String u){
        myRef.child("room").child(String.valueOf(1)).child(u).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try{
                    int num=snapshot.getValue(Integer.class);
                    num--;
                    if(num==0){
                        if(playerNum==1){


                            holeDeleteIfRoomIsEmpty(mAuth.getCurrentUser().getUid());
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer1234!=null){
                                countDownTimer1234.cancel();
                            }
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();
                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);

                            finish();
                           

                        }else{


                            holeDeleteIfRoomIsEmpty(hostUid);
                            if(countDownTimer!=null){
                                countDownTimer.cancel();
                            }
                            if(countDownTimer1234!=null){
                                countDownTimer1234.cancel();
                            }
                            if(playerNum==2){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }


                            }else if(playerNum==3){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }
                            }else if(playerNum==4){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }
                            }
                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                           

                        }
                    }else {
                        if(playerNum==1){
                            final int finalNum = num;
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(finalNum).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();
                                            //   Intent intent23=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            //  startActivity(intent23);
                                            //  overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            //  finish();
                                        }
                                    });

                                }
                            });
                        }else{
                            if(j==1){
                                switch (playerNum){
                                    case 2:
                                        deleter("player2Answer","player2Data","player2Status","player2Uid");
                                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();break;
                                    case 3:
                                        deleter("player3Answer","player3Data","player3Status","player3Uid");
                                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();break;
                                    case 4:
                                        deleter("player4Answer","player4Data","player4Status","player4Uid");
                                        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();break;
                                }

                                myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int m=snapshot.getValue(Integer.class);
                                        m--;
                                        myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(countDownTimer!=null){
                                                    countDownTimer.cancel();
                                                }
                                                if(countDownTimer1234!=null){
                                                    countDownTimer1234.cancel();
                                                }
                                                Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                                startActivity(i);
                                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                                finish();
                                               

                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }else{
                                myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(playerNum==2){

                                            deleter("player2Answer","player2Data","player2Status","player2Uid");
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);

                                            finish();
                                        }else if(playerNum==3){

                                            deleter("player3Answer", "player3Data", "player3Status", "player3Uid");
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                        }else{
                                            deleter("player4Answer", "player4Data", "player4Status", "player4Uid");
                                            if(countDownTimer!=null){
                                                countDownTimer.cancel();
                                            }
                                            if(countDownTimer1234!=null){
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                        }

                                    }
                                });
                            }


                        }

                    }

                }catch (Exception e){

                    switch (playerNum){
                        case 2:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            break;
                        case 3:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            break;
                        case 4:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            break;
                    }
                    Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("room").child(String.valueOf(0)).child(u).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num = snapshot.getValue(Integer.class);
                    num--;
                    if (num == 0) {
                        if (playerNum == 1) {

                            holeDeleteIfRoomIsEmpty(mAuth.getCurrentUser().getUid());
                            if (countDownTimer != null) {
                                countDownTimer.cancel();
                            }
                            if (countDownTimer1234 != null) {
                                countDownTimer1234.cancel();
                            }
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();

                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        } else {

                            holeDeleteIfRoomIsEmpty(hostUid);
                            if (countDownTimer != null) {
                                countDownTimer.cancel();
                            }
                            if (countDownTimer1234 != null) {
                                countDownTimer1234.cancel();
                            }
                            if(playerNum==2){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                                }catch (Exception e){

                                }

                            }else if(playerNum==3){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();

                                }catch (Exception e){

                                }

                            }else if(playerNum==4){
                                try{
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();

                                }catch (Exception e){

                                }

                            }
                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                            startActivity(i);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();

                        }
                    } else {
                        if (playerNum == 1) {
                            final int finalNum = num;
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(finalNum).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").onDisconnect().cancel();

                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                        }
                                    });


                                }
                            });
                        } else {
                            if (j == 1) {
                                switch (playerNum) {
                                    case 2:
                                        try{
                                            deleter("player2Answer", "player2Data", "player2Status", "player2Uid");

                                        }catch (Exception e){

                                        }
                                        try{
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                                        }catch (Exception e){

                                        }

                                        break;
                                    case 3:
                                        try{
                                            deleter("player3Answer", "player3Data", "player3Status", "player3Uid");
                                        }catch (Exception e){

                                        }
                                        try{
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                        }catch (Exception e){

                                        }


                                        break;
                                    case 4:
                                        try{
                                            deleter("player4Answer", "player4Data", "player4Status", "player4Uid");
                                        }catch (Exception e){

                                        }
                                        try{
                                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                        }catch (Exception e){

                                        }


                                        break;
                                }

                                myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        int m = snapshot.getValue(Integer.class);
                                        m--;
                                        myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").setValue(m).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (countDownTimer != null) {
                                                    countDownTimer.cancel();
                                                }
                                                if (countDownTimer1234 != null) {
                                                    countDownTimer1234.cancel();
                                                }
                                                Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                                startActivity(i);
                                                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                                finish();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });


                            } else {
                                myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").setValue(num).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (playerNum == 2) {

                                            deleter("player2Answer", "player2Data", "player2Status", "player2Uid");
                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            try{
                                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();

                                            }catch (Exception e){

                                            }

                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                        } else if (playerNum == 3) {

                                            deleter("player3Answer", "player3Data", "player3Status", "player3Uid");
                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            try{
                                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                                            }catch (Exception e){

                                            }

                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                        } else {
                                            deleter("player4Answer", "player4Data", "player4Status", "player4Uid");
                                            if (countDownTimer != null) {
                                                countDownTimer.cancel();
                                            }
                                            if (countDownTimer1234 != null) {
                                                countDownTimer1234.cancel();
                                            }
                                            try{
                                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                                            }catch (Exception e){

                                            }

                                            Intent i=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                            finish();
                                        }

                                    }
                                });
                            }


                        }

                    }


                } catch (Exception e) {
                    Intent intent=new Intent(tournamentBuzzerScoreCard.this,mainMenuActivity.class);
                    startActivity(intent);
                    switch (playerNum){
                        case 2:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            break;
                        case 3:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            break;
                        case 4:
                            try{
                                myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            break;
                    }
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void mainManu2(final String playerStatus, final String imageUrl, final String nameString, final TextView headText, final TextView name, final TextView accuracy, final TextView correctvswrong, final TextView score, final ImageView img, final ImageView propic, final ShimmerFrameLayout playerShimmerPic, final ShimmerFrameLayout playerShimmer){

        lisnernumber1=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int t=snapshot.getValue(Integer.class);
                    if(t==0){
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeEventListener(lisnernumber1);
                        }catch (Exception e){

                        }

                        numberOfPlayers--;
                        switch (playerNum){
                            case 1:
                                sco2=-50;break;
                            case 2:
                            case 3:
                            case 4:
                                sco1=-50;break;
                        }
                        Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                                .bitmapTransform(new RoundedCorners(14)))
                                .into(img);
                        Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                                .bitmapTransform(new RoundedCorners(14)))
                                .into(propic);
                        headText.setText(nameString);
                        name.setText(nameString+" (Left)");
                        accuracy.setText("Accuracy : 0%");
                        correctvswrong.setText("Correct/Wrong : 0/0");
                        score.setText("Total Score : 0");

                        playerShimmer.stopShimmerAnimation();
                        playerShimmer.setVisibility(View.GONE);
                        playerShimmerPic.stopShimmerAnimation();
                        playerShimmerPic.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    try{
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeEventListener(lisnernumber1);
                    }catch (Exception e1){

                    }
                    numberOfPlayers--;
                    switch (playerNum){
                        case 1:
                            sco2=-50;break;
                        case 2:
                        case 3:
                        case 4:
                            sco1=-50;break;
                    }
                    Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(img);
                    Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(propic);
                    headText.setText(nameString);
                    name.setText(nameString+" (Left)");
                    accuracy.setText("Accuracy : 0%");
                    correctvswrong.setText("Correct/Wrong : 0/0");
                  //  totalLifeLines.setText("Total Life-Lines : 0/0");
                    score.setText("Total Score : 0");
                  //  totalTimeTaken.setText("Total Time Taken : 0");

                    playerShimmer.stopShimmerAnimation();
                    playerShimmer.setVisibility(View.GONE);
                    playerShimmerPic.stopShimmerAnimation();
                    playerShimmerPic.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).addListenerForSingleValueEvent(lisnernumber1);
    }

    public void mainManu3(final String playerStatus, final String imageUrl, final String nameString, final TextView headText, final TextView name, final TextView accuracy, final TextView correctvswrong, final TextView score, final ImageView img, final ImageView propic, final ShimmerFrameLayout playerShimmerPic, final ShimmerFrameLayout playerShimmer){
        lisnernumber2=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int t=snapshot.getValue(Integer.class);
                    if(t==0){
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeEventListener(lisnernumber2);
                        }catch (Exception e){

                        }
                        numberOfPlayers--;
                        switch (playerNum){
                            case 1:
                            case 2:
                                sco3=-50;break;
                            case 3:
                            case 4:
                                sco2=-50;break;
                        }
                        Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                                .bitmapTransform(new RoundedCorners(14)))
                                .into(img);
                        Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                                .bitmapTransform(new RoundedCorners(14)))
                                .into(propic);
                        headText.setText(nameString);
                        name.setText(nameString+" (Left)");
                        accuracy.setText("Accuracy : 0%");
                        correctvswrong.setText("Correct/Wrong : 0/0");
                        score.setText("Total Score : 0");

                        playerShimmer.stopShimmerAnimation();
                        playerShimmer.setVisibility(View.GONE);
                        playerShimmerPic.stopShimmerAnimation();
                        playerShimmerPic.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    try{
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeEventListener(lisnernumber2);
                    }catch (Exception e1){

                    }
                    numberOfPlayers--;
                    switch (playerNum){
                        case 1:
                        case 2:
                            sco3=-50;break;
                        case 3:
                        case 4:
                            sco2=-50;break;
                    }
                    Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(img);
                    Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(propic);
                    headText.setText(nameString);
                    name.setText(nameString+" (Left)");
                    accuracy.setText("Accuracy : 0%");
                    correctvswrong.setText("Correct/Wrong : 0/0");
                   // totalLifeLines.setText("Total Life-Lines : 0/0");
                    score.setText("Total Score : 0");
                   // totalTimeTaken.setText("Total Time Taken : 0");

                    playerShimmer.stopShimmerAnimation();
                    playerShimmer.setVisibility(View.GONE);
                    playerShimmerPic.stopShimmerAnimation();
                    playerShimmerPic.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).addListenerForSingleValueEvent(lisnernumber2);
    }
    public void mainManu4(final String playerStatus, final String imageUrl, final String nameString, final TextView headText, final TextView name, final TextView accuracy, final TextView correctvswrong, final TextView score, final ImageView img, final ImageView propic, final ShimmerFrameLayout playerShimmerPic, final ShimmerFrameLayout playerShimmer){
        lisnernumber3= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int t=snapshot.getValue(Integer.class);
                    if(t==0){
                        try{
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeEventListener(lisnernumber3);
                        }catch (Exception e){

                        }
                        numberOfPlayers--;
                        switch (playerNum){
                            case 1:
                            case 2:
                            case 3:
                                sco4=-50;break;
                            case 4:
                                sco3=-50;break;
                        }
                        Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                                .bitmapTransform(new RoundedCorners(14)))
                                .into(img);
                        Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                                .bitmapTransform(new RoundedCorners(14)))
                                .into(propic);
                        headText.setText(nameString);
                        name.setText(nameString+" (Left)");
                        accuracy.setText("Accuracy : 0%");
                        correctvswrong.setText("Correct/Wrong : 0/0");
                        score.setText("Total Score : 0");

                        playerShimmer.stopShimmerAnimation();
                        playerShimmer.setVisibility(View.GONE);
                        playerShimmerPic.stopShimmerAnimation();
                        playerShimmerPic.setVisibility(View.GONE);

                    }
                }catch (Exception e){
                    try{
                        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).removeEventListener(lisnernumber3);
                    }catch (Exception e1){

                    }
                    numberOfPlayers--;
                    switch (playerNum){
                        case 1:
                        case 2:
                        case 3:
                            sco4=-50;break;
                        case 4:
                            sco3=-50;break;
                    }
                    Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(img);
                    Glide.with(getBaseContext()).load(imageUrl).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(propic);
                    headText.setText(nameString);
                    name.setText(nameString+" (Left)");
                    accuracy.setText("Accuracy : 0%");
                    correctvswrong.setText("Correct/Wrong : 0/0");
                   // totalLifeLines.setText("Total Life-Lines : 0/0");
                    score.setText("Total Score : 0");
                 //   totalTimeTaken.setText("Total Time Taken : 0");

                    playerShimmer.stopShimmerAnimation();
                    playerShimmer.setVisibility(View.GONE);
                    playerShimmerPic.stopShimmerAnimation();
                    playerShimmerPic.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).addListenerForSingleValueEvent(lisnernumber3);
    }
    public void playerOnlineStatusManupulator(String playerStatus){
        lisnerdog1=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int swaper = snapshot.getValue(Integer.class);
                    if (swaper == 0) {
                        try {
                            myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                        }catch (Exception e){

                        }
                        if(Countdownlast!=null){
                            Countdownlast.cancel();
                        }
                        lobbyButton.setEnabled(false);
                        lobbyButton.setAlpha(0.9f);
                        lobbyButton.setText("Host Has Left The Room");
                        lobbyButton.setTextSize(10);
                        numberOfPlayersReducerNotHostBecauseOfHost(1);
                        numberOfPlayersReducerNotHostBecauseOfHost(0);
                        quitorjoinasker();
                    }
                }catch (Exception e) {


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerStatus).addValueEventListener(lisnerdog1);
    }

    public void quitorjoinasker(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.lifelineused);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(tournamentBuzzerScoreCard.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(tournamentBuzzerScoreCard.this).inflate(R.layout.askingtomoveintournamentchoiceactivity,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(true);
        Button buttonYes=(Button) view1.findViewById(R.id.buttonYes);
        Button buttonNo=(Button) view1.findViewById(R.id.buttonNo);


        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }

        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                try {
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                }catch (Exception e){

                }



                if(playerNum==1){
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("exit").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    del47(mAuth.getCurrentUser().getUid());
                }else{
                    del47(hostUid);
                }
            }
        });
    }

    public void numberOfPlayersReducerNotHostBecauseOfHost(final int i){
        myRef.child("room").child(String.valueOf(i)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    numHoster=snapshot.getValue(Integer.class);
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                int t=snapshot.getValue(Integer.class);
                                if(t==1){
                                    numHoster--;
                                    myRef.child("room").child(String.valueOf(i)).child(hostUid).child("numberOfPlayers").setValue(numHoster).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                }
                            }catch (Exception e){
                                if(numberOfPlayers>2){
                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            try {
                                                int t=snapshot.getValue(Integer.class);
                                                if(t==1){
                                                    numHoster--;
                                                    myRef.child("room").child(String.valueOf(i)).child(hostUid).child("numberOfPlayers").setValue(numHoster).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {

                                                        }
                                                    });
                                                }
                                            }catch (Exception e){
                                                if(numberOfPlayers==4){
                                                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").addListenerForSingleValueEvent(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            try{
                                                                int t=snapshot.getValue(Integer.class);
                                                                if(t==1) {
                                                                    numHoster--;
                                                                    myRef.child("room").child(String.valueOf(i)).child(hostUid).child("numberOfPlayers").setValue(numHoster).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

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
                                            }

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                                }
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
    public void decider(){
        switch (numberOfPlayers){
            case 1:
                if(roomEntry==1){
                    alertDialog123(myName+"\nYour Are Alone",R.drawable.alone);
                    headShimmer.stopShimmerAnimation();
                    headShimmer.setVisibility(View.GONE);
                    pos1.setText("1st");
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimer1234!=null){
                        countDownTimer1234.cancel();
                    }
                }break;
            case 2:
                if(roomEntry==2){
                    int p1,p2;
                    if(playerNum==1){
                        p1= sco1;
                        if(sco2==-50&&sco3!=-50&&sco3!=-100){
                            p2= sco3;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("1st");
                                pos3.setText("2nd");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("2nd");
                                pos3.setText("1st");
                            }
                        }else if(sco3==-50&&sco2!=-50&&sco2!=-100){
                            p2= sco2;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("1st");
                                pos2.setText("2nd");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("2nd");
                                pos2.setText("1st");
                            }
                        }else if(sco3==-50&&sco2!=-50&&sco2!=-100&&sco4==-50){
                            p2= sco2;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("1st");
                                pos2.setText("2nd");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("2nd");
                                pos2.setText("1st");
                            }
                        }else if(sco2==-50&&sco3!=-50&&sco3!=-100&&sco4==-50){
                            p2= sco3;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("1st");
                                pos3.setText("2nd");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("2nd");
                                pos3.setText("1st");
                            }
                        }else if(sco2==-50&&sco4!=-50&&sco4!=-100&&sco3==-50){
                            p2= sco4;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("1st");
                                pos4.setText("2nd");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("2nd");
                                pos4.setText("1st");
                            }
                        }else if(sco3==-100&&sco2!=-50&&sco2!=-100&&sco4==-100){
                            p2= sco2;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("1st");
                                pos2.setText("2nd");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("2nd");
                                pos2.setText("1st");
                            }
                        }
                    }else{
                        if(playerNum==2){
                            p1= sco2;
                            p2= sco1;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("2nd");
                                pos2.setText("1st");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("1st");
                                pos2.setText("2nd");
                            }
                        }else if(playerNum==3){
                            p1= sco3;
                            p2= sco1;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("2nd");
                                pos3.setText("1st");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("1st");
                                pos3.setText("2nd");
                            }
                        }
                        else if(playerNum==4){
                            p1= sco4;
                            p2= sco1;
                            if(p1>p2){
                                alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                pos1.setText("2nd");
                                pos4.setText("1st");
                            }else{
                                alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                pos1.setText("1st");
                                pos4.setText("2nd");
                            }
                        }

                    }


                    headShimmer.stopShimmerAnimation();
                    headShimmer.setVisibility(View.GONE);
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimer1234!=null){
                        countDownTimer1234.cancel();
                    }
                }break;
            case 3:
                if(roomEntry==3){
                    int o1=0,o2=0,o3=0;
                    int p1=0,p2=0,p3=0;
                    if(playerNum==1){
                        if(sco1!=-50&&sco2!=-50&&sco3!=-50){
                            p1= sco1;
                            p2= sco2;
                            p3=sco3;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos1.setText("2nd");
                                        if(p2>=p3){
                                            pos2.setText("1st");
                                            pos3.setText("3rd");
                                        }else{
                                            pos3.setText("1st");
                                            pos2.setText("3rd");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos1.setText("1st");
                                        if(p2>=p3){
                                            pos2.setText("2nd");
                                            pos3.setText("3rd");
                                        }else{
                                            pos3.setText("2nd");
                                            pos2.setText("3rd");
                                        }
                                        break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos1.setText("3rd");
                                if(p2>=p3){
                                    pos2.setText("1st");
                                    pos3.setText("2nd");
                                }else{
                                    pos3.setText("1st");
                                    pos2.setText("2nd");
                                }
                            }
                        }else if(sco2==-50){
                            p1= sco1;
                            p2= sco3;
                            p3=sco4;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos1.setText("2nd");
                                        if(p2>=p3){
                                            pos3.setText("1st");
                                            pos4.setText("3rd");
                                        }else{
                                            pos4.setText("1st");
                                            pos3.setText("3rd");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos1.setText("1st");
                                        if(p2>=p3){
                                            pos3.setText("2nd");
                                            pos4.setText("3rd");
                                        }else{
                                            pos4.setText("2nd");
                                            pos3.setText("3rd");
                                        }
                                        break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos1.setText("3rd");
                                if(p2>=p3){
                                    pos3.setText("1st");
                                    pos4.setText("2nd");
                                }else{
                                    pos4.setText("1st");
                                    pos3.setText("2nd");
                                }
                            }
                        }else if(sco3==-50){
                            p1= sco1;
                            p2= sco2;
                            p3=sco4;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos1.setText("2nd");
                                        if(p2>=p3){
                                            pos2.setText("1st");
                                            pos4.setText("3rd");
                                        }else{
                                            pos4.setText("1st");
                                            pos2.setText("3rd");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos1.setText("1st");
                                        if(p2>=p3){
                                            pos2.setText("2nd");
                                            pos4.setText("3rd");
                                        }else{
                                            pos4.setText("2nd");
                                            pos2.setText("3rd");
                                        }
                                        break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos1.setText("3rd");
                                if(p2>=p3){
                                    pos2.setText("1st");
                                    pos4.setText("2nd");
                                }else{
                                    pos4.setText("1st");
                                    pos2.setText("2nd");
                                }
                            }
                        }

                    }else if(playerNum==2){
                        if(sco3!=-50){
                            p1= sco2;
                            p2= sco3;
                            p3=sco1;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos2.setText("2nd");
                                        if(p2>=p3){
                                            pos3.setText("1st");
                                            pos1.setText("3rd");
                                        }else{
                                            pos1.setText("1st");
                                            pos3.setText("3rd");
                                        }break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos2.setText("1st");
                                        if(p2>=p3){
                                            pos3.setText("2nd");
                                            pos1.setText("3rd");
                                        }else{
                                            pos3.setText("3rd");
                                            pos1.setText("2nd");
                                        }break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos2.setText("3rd");
                                if(p2>=p3){
                                    pos3.setText("1st");
                                    pos1.setText("2nd");
                                }else{
                                    pos3.setText("2nd");
                                    pos1.setText("1st");
                                }
                            }
                        }else if(sco3==-50){
                            p1= sco2;
                            p2= sco4;
                            p3=sco1;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos2.setText("2nd");
                                        if(p2>=p3){
                                            pos4.setText("1st");
                                            pos1.setText("3rd");
                                        }else{
                                            pos1.setText("1st");
                                            pos4.setText("3rd");
                                        }break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos2.setText("1st");
                                        if(p2>=p3){
                                            pos4.setText("2nd");
                                            pos1.setText("3rd");
                                        }else{
                                            pos4.setText("3rd");
                                            pos1.setText("2nd");
                                        }break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos2.setText("3rd");
                                if(p2>=p3){
                                    pos4.setText("1st");
                                    pos1.setText("2nd");
                                }else{
                                    pos4.setText("2nd");
                                    pos1.setText("1st");
                                }
                            }
                        }

                    }else if(playerNum==3){
                        if(sco2!=-50){
                            p1= sco3;
                            p2= sco1;
                            p3=sco2;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos3.setText("2nd");
                                        if(p2>=p3){
                                            pos1.setText("1st");
                                            pos2.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos2.setText("1st");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos3.setText("1st");
                                        if(p2>=p3){
                                            pos1.setText("2nd");
                                            pos2.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos2.setText("2nd");
                                        }break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos3.setText("3rd");
                                if(p2>=p3){
                                    pos1.setText("1st");
                                    pos2.setText("2nd");
                                }else{
                                    pos1.setText("2nd");
                                    pos2.setText("1st");
                                }
                            }
                        }else if(sco2==-50){
                            p1= sco3;
                            p2= sco1;
                            p3=sco4;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos3.setText("2nd");
                                        if(p2>=p3){
                                            pos1.setText("1st");
                                            pos4.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos4.setText("1st");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos3.setText("1st");
                                        if(p2>=p3){
                                            pos1.setText("2nd");
                                            pos4.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos4.setText("2nd");
                                        }break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos3.setText("3rd");
                                if(p2>=p3){
                                    pos1.setText("1st");
                                    pos4.setText("2nd");
                                }else{
                                    pos1.setText("2nd");
                                    pos4.setText("1st");
                                }
                            }
                        }

                    }else if(playerNum==4){
                        if(sco2!=-50){
                            p1= sco4;
                            p2= sco1;
                            p3=sco2;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos4.setText("2nd");
                                        if(p2>=p3){
                                            pos1.setText("1st");
                                            pos2.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos2.setText("1st");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos4.setText("1st");
                                        if(p2>=p3){
                                            pos1.setText("2nd");
                                            pos2.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos2.setText("2nd");
                                        }break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos4.setText("3rd");
                                if(p2>=p3){
                                    pos1.setText("1st");
                                    pos2.setText("2nd");
                                }else{
                                    pos1.setText("2nd");
                                    pos2.setText("1st");
                                }
                            }
                        }else if(sco2==-50){
                            p1= sco4;
                            p2= sco1;
                            p3=sco3;
                            if(p1>=p2||p1>=p3){
                                if(p1>=p3){
                                    o1++;
                                }
                                if(p1>=p2){
                                    o1++;
                                }

                                switch (o1){
                                    case 1:
                                        alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                        pos4.setText("2nd");
                                        if(p2>=p3){
                                            pos1.setText("1st");
                                            pos3.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos3.setText("1st");
                                        }
                                        break;
                                    case 2:
                                        alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                        pos4.setText("1st");
                                        if(p2>=p3){
                                            pos1.setText("2nd");
                                            pos3.setText("3rd");
                                        }else{
                                            pos1.setText("3rd");
                                            pos3.setText("2nd");
                                        }break;
                                }

                            }else if(p1<p2&&p1<p3){
                                alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                pos4.setText("3rd");
                                if(p2>=p3){
                                    pos1.setText("1st");
                                    pos3.setText("2nd");
                                }else{
                                    pos1.setText("2nd");
                                    pos3.setText("1st");
                                }
                            }
                        }
                    }


                    headShimmer.stopShimmerAnimation();
                    headShimmer.setVisibility(View.GONE);
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimer1234!=null){
                        countDownTimer1234.cancel();
                    }

                }break;
            case 4:
                if(roomEntry==4){
                    int o1=0,o2=0,o3=0,o4=0;
                    int p1=0,p2=0,p3=0,p4=0;
                    if(playerNum==1){
                        p1= sco1;
                        p2= sco2;
                        p3=sco3;
                        p4=sco4;
                        if(p1>=p2||p1>=p3||p1>=p4){
                            if(p1>=p3){
                                o1++;
                            }
                            if(p1>=p2){
                                o1++;
                            }
                            if(p1>=p4){
                                o1++;
                            }

                            switch (o1){
                                case 1:
                                    alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                    pos1.setText("3rd");
                                    if(p2>=p3&&p2>=p4){
                                        pos2.setText("1st");
                                        if(p3>=p4){
                                            pos3.setText("2nd");
                                            pos4.setText("4th");
                                        }else{
                                            pos3.setText("4th");
                                            pos4.setText("2nd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos3.setText("1st");
                                        if(p2>=p4){
                                            pos2.setText("2nd");
                                            pos4.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos4.setText("2nd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos4.setText("1st");
                                        if(p2>=p3){
                                            pos2.setText("2nd");
                                            pos3.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos3.setText("2nd");
                                        }
                                    }



                                    break;
                                case 2:
                                    alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                    pos1.setText("2nd");

                                    if(p2>=p3&&p2>=p4){
                                        pos2.setText("1st");
                                        if(p3>=p4){
                                            pos3.setText("3rd");
                                            pos4.setText("4th");
                                        }else{
                                            pos3.setText("4th");
                                            pos4.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos3.setText("1st");
                                        if(p2>=p4){
                                            pos2.setText("3rd");
                                            pos4.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos4.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos4.setText("1st");
                                        if(p2>=p3){
                                            pos2.setText("3rd");
                                            pos3.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos3.setText("3rd");
                                        }
                                    }


                                    break;
                                case 3:
                                    alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                    pos1.setText("1st");
                                    if(p2>=p3&&p2>=p4){
                                        pos2.setText("2nd");
                                        if(p3>=p4){
                                            pos3.setText("3rd");
                                            pos4.setText("4th");
                                        }else{
                                            pos3.setText("4th");
                                            pos4.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos3.setText("2nd");
                                        if(p2>=p4){
                                            pos2.setText("3rd");
                                            pos4.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos4.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos4.setText("2nd");
                                        if(p2>=p3){
                                            pos2.setText("3rd");
                                            pos3.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos3.setText("3rd");
                                        }
                                    }break;
                            }
                        }else {
                            alertDialog4(myName+"\n You Lose");
                            pos1.setText("4th");
                            if(p2>=p3&&p2>=p4){
                                pos2.setText("1st");
                                if(p3>=p4){
                                    pos3.setText("2nd");
                                    pos4.setText("3rd");
                                }else{
                                    pos3.setText("3rd");
                                    pos4.setText("2nd");
                                }
                            }else if(p3>=p2&&p3>=p4){
                                pos3.setText("1st");
                                if(p2>=p4){
                                    pos2.setText("2nd");
                                    pos4.setText("3rd");
                                }else{
                                    pos2.setText("3rd");
                                    pos4.setText("2nd");
                                }
                            }else if(p4>=p2&&p4>=p3){
                                pos4.setText("1st");
                                if(p2>=p3){
                                    pos2.setText("2nd");
                                    pos3.setText("3rd");
                                }else{
                                    pos2.setText("3rd");
                                    pos3.setText("2nd");
                                }
                            }
                        }
                    }else if(playerNum==2){
                        p1= sco2;
                        p2= sco1;
                        p3=sco4;
                        p4=sco3;
                        if(p1>=p2||p1>=p3||p1>=p4){
                            if(p1>=p3){
                                o1++;
                            }
                            if(p1>=p2){
                                o1++;
                            }
                            if(p1>=p4){
                                o1++;
                            }

                            switch (o1){

                                case 1:
                                    alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                    pos2.setText("3rd");

                                    if(p2>=p3&&p2>=p4){
                                        pos1.setText("1st");
                                        if(p3>=p4){
                                            pos4.setText("2nd");
                                            pos3.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos3.setText("2nd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos4.setText("1st");
                                        if(p2>=p4){
                                            pos1.setText("2nd");
                                            pos3.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos3.setText("2nd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos3.setText("1st");
                                        if(p2>=p3){
                                            pos1.setText("2nd");
                                            pos4.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos4.setText("2nd");
                                        }
                                    }


                                    break;
                                case 2:
                                    alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                    pos2.setText("2nd");

                                    if(p2>=p3&&p2>=p4){
                                        pos1.setText("1st");
                                        if(p3>=p4){
                                            pos4.setText("3rd");
                                            pos3.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos3.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos4.setText("1st");
                                        if(p2>=p4){
                                            pos1.setText("3rd");
                                            pos3.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos3.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos3.setText("1st");
                                        if(p2>=p3){
                                            pos1.setText("3rd");
                                            pos4.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos4.setText("3rd");
                                        }
                                    }

                                    break;
                                case 3:
                                    alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                    pos2.setText("1st");

                                    if(p2>=p3&&p2>=p4){
                                        pos1.setText("2nd");
                                        if(p3>=p4){
                                            pos4.setText("3rd");
                                            pos3.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos3.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos4.setText("2nd");
                                        if(p2>=p4){
                                            pos1.setText("3rd");
                                            pos3.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos3.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos3.setText("2nd");
                                        if(p2>=p3){
                                            pos1.setText("3rd");
                                            pos4.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos4.setText("3rd");
                                        }
                                    }

                                    break;
                            }
                        }else {
                            alertDialog4(myName+"\n You Lose");
                            pos2.setText("4th");
                            if(p2>=p3&&p2>=p4){
                                pos1.setText("1st");
                                if(p3>=p4){
                                    pos4.setText("2nd");
                                    pos3.setText("3rd");
                                }else{
                                    pos4.setText("3rd");
                                    pos3.setText("2nd");
                                }
                            }else if(p3>=p2&&p3>=p4){
                                pos4.setText("1st");
                                if(p2>=p4){
                                    pos1.setText("2nd");
                                    pos3.setText("3rd");
                                }else{
                                    pos1.setText("3rd");
                                    pos3.setText("2nd");
                                }
                            }else if(p4>=p2&&p4>=p3){
                                pos3.setText("1st");
                                if(p2>=p3){
                                    pos1.setText("2nd");
                                    pos4.setText("3rd");
                                }else{
                                    pos1.setText("3rd");
                                    pos4.setText("2nd");
                                }
                            }
                        }
                    }else if(playerNum==3){
                        p1= sco3;
                        p2= sco4;
                        p3=sco1;
                        p4=sco2;
                        if(p1>=p2||p1>=p3||p1>=p4){
                            if(p1>=p3){
                                o1++;
                            }
                            if(p1>=p2){
                                o1++;
                            }
                            if(p1>=p4){
                                o1++;
                            }

                            switch (o1){

                                case 1:
                                    alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                    pos3.setText("3rd");

                                    if(p2>=p3&&p2>=p4){
                                        pos4.setText("1st");
                                        if(p3>=p4){
                                            pos1.setText("2nd");
                                            pos2.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos2.setText("2nd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos1.setText("1st");
                                        if(p2>=p4){
                                            pos4.setText("2nd");
                                            pos2.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos2.setText("2nd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos2.setText("1st");
                                        if(p2>=p3){
                                            pos4.setText("2nd");
                                            pos1.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos1.setText("2nd");
                                        }
                                    }



                                    break;
                                case 2:
                                    alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                    pos3.setText("2nd");

                                    if(p2>=p3&&p2>=p4){
                                        pos4.setText("1st");
                                        if(p3>=p4){
                                            pos1.setText("3rd");
                                            pos2.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos2.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos1.setText("1st");
                                        if(p2>=p4){
                                            pos4.setText("3rd");
                                            pos2.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos2.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos2.setText("1st");
                                        if(p2>=p3){
                                            pos4.setText("3rd");
                                            pos1.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos1.setText("3rd");
                                        }
                                    }

                                    break;
                                case 3:
                                    alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                    pos3.setText("1st");
                                    if(p2>=p3&&p2>=p4){
                                        pos4.setText("2nd");
                                        if(p3>=p4){
                                            pos1.setText("3rd");
                                            pos2.setText("4th");
                                        }else{
                                            pos1.setText("4th");
                                            pos2.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos1.setText("2nd");
                                        if(p2>=p4){
                                            pos4.setText("3rd");
                                            pos2.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos2.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos2.setText("2nd");
                                        if(p2>=p3){
                                            pos4.setText("3rd");
                                            pos1.setText("4th");
                                        }else{
                                            pos4.setText("4th");
                                            pos1.setText("3rd");
                                        }
                                    }


                                    break;
                            }
                        }else {
                            alertDialog4(myName+"\n You Lose");
                            pos3.setText("4th");
                            if(p2>=p3&&p2>=p4){
                                pos4.setText("1st");
                                if(p3>=p4){
                                    pos1.setText("2nd");
                                    pos2.setText("3rd");
                                }else{
                                    pos1.setText("3rd");
                                    pos2.setText("2nd");
                                }
                            }else if(p3>=p2&&p3>=p4){
                                pos1.setText("1st");
                                if(p2>=p4){
                                    pos4.setText("2nd");
                                    pos2.setText("3rd");
                                }else{
                                    pos4.setText("3rd");
                                    pos2.setText("2nd");
                                }
                            }else if(p4>=p2&&p4>=p3){
                                pos2.setText("1st");
                                if(p2>=p3){
                                    pos4.setText("2nd");
                                    pos1.setText("3rd");
                                }else{
                                    pos4.setText("3rd");
                                    pos1.setText("2nd");
                                }
                            }

                        }
                    }else if(playerNum==4){
                        p1= sco4;
                        p2= sco3;
                        p3=sco2;
                        p4=sco1;
                        if(p1>=p2||p1>=p3||p1>=p4){
                            if(p1>=p3){
                                o1++;
                            }
                            if(p1>=p2){
                                o1++;
                            }
                            if(p1>=p4){
                                o1++;
                            }
                            switch (o1){
                                case 1:
                                    alertDialog123(myName+"\n You Are Third",R.drawable.thirdpos);
                                    pos4.setText("3rd");

                                    if(p2>=p3&&p2>=p4){
                                        pos3.setText("1st");
                                        if(p3>=p4){
                                            pos2.setText("2nd");
                                            pos1.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos1.setText("2nd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos2.setText("1st");
                                        if(p2>=p4){
                                            pos3.setText("2nd");
                                            pos1.setText("4th");
                                        }else{
                                            pos3.setText("4th");
                                            pos1.setText("2nd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos1.setText("1st");
                                        if(p2>=p3){
                                            pos3.setText("2nd");
                                            pos2.setText("4th");
                                        }else{
                                            pos2.setText("2nd");
                                            pos3.setText("4th");
                                        }
                                    }


                                    break;
                                case 2:
                                    alertDialog123(myName+"\n You Are Second",R.drawable.secondpos);
                                    pos4.setText("2nd");

                                    if(p2>=p3&&p2>=p4){
                                        pos3.setText("1st");
                                        if(p3>=p4){
                                            pos2.setText("3rd");
                                            pos1.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos1.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos2.setText("1st");
                                        if(p2>=p4){
                                            pos3.setText("3rd");
                                            pos1.setText("4th");
                                        }else{
                                            pos3.setText("4th");
                                            pos1.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos1.setText("1st");
                                        if(p2>=p3){
                                            pos3.setText("3rd");
                                            pos2.setText("4th");
                                        }else{
                                            pos2.setText("3rd");
                                            pos3.setText("4th");
                                        }
                                    }


                                    break;
                                case 3:
                                    alertDialog123(myName+"\n You Are First",R.drawable.firstpos);
                                    pos4.setText("1st");

                                    if(p2>=p3&&p2>=p4){
                                        pos3.setText("2nd");
                                        if(p3>=p4){
                                            pos2.setText("3rd");
                                            pos1.setText("4th");
                                        }else{
                                            pos2.setText("4th");
                                            pos1.setText("3rd");
                                        }
                                    }else if(p3>=p2&&p3>=p4){
                                        pos2.setText("2nd");
                                        if(p2>=p4){
                                            pos3.setText("3rd");
                                            pos1.setText("4th");
                                        }else{
                                            pos3.setText("4th");
                                            pos1.setText("3rd");
                                        }
                                    }else if(p4>=p2&&p4>=p3){
                                        pos1.setText("2nd");
                                        if(p2>=p3){
                                            pos3.setText("3rd");
                                            pos2.setText("4th");
                                        }else{
                                            pos2.setText("3rd");
                                            pos3.setText("4th");
                                        }
                                    }


                                    break;
                            }
                        }else {
                            alertDialog4(myName+"\n You Lose");
                            pos4.setText("4th");

                            if(p2>=p3&&p2>=p4){
                                pos3.setText("1st");
                                if(p3>=p4){
                                    pos2.setText("2nd");
                                    pos1.setText("3rd");
                                }else{
                                    pos2.setText("3rd");
                                    pos1.setText("2nd");
                                }
                            }else if(p3>=p2&&p3>=p4){
                                pos2.setText("1st");
                                if(p2>=p4){
                                    pos3.setText("2nd");
                                    pos1.setText("3rd");
                                }else{
                                    pos3.setText("3rd");
                                    pos1.setText("2nd");
                                }
                            }else if(p4>=p2&&p4>=p3){
                                pos1.setText("1st");
                                if(p2>=p3){
                                    pos3.setText("2nd");
                                    pos2.setText("3rd");
                                }else{
                                    pos2.setText("2nd");
                                    pos3.setText("3rd");
                                }
                            }


                        }
                    }


                    headShimmer.stopShimmerAnimation();
                    headShimmer.setVisibility(View.GONE);
                    if(countDownTimer!=null){
                        countDownTimer.cancel();
                    }
                    if(countDownTimer1234!=null){
                        countDownTimer1234.cancel();
                    }
                }break;
        }
    }

    public void timerWaiting(){
        countDownTimer=new CountDownTimer(1000*60*60,1000) {
            @Override
            public void onTick(long l) {
                decider();

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void waiter(){
        countDownTimer1234=new CountDownTimer(1000*60*60,2000) {
            @Override
            public void onTick(long l) {
                oppoFinder();

            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void dataManupulator(){

        acc=((mycorrect*100)/(mywrong+mycorrect));
        mainMenuBro();

        switch (playerNum){
            case 1:
                sco1=correctNum;
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(img1);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(propic1);
                headText1.setText(myName);
                name1.setText(myName);
                accuracy1.setText("Accuracy : "+acc+"%");
                correctvswrong1.setText("Correct/Wrong : "+mycorrect+"/"+mywrong);
                score1.setText("Total Score : "+correctNum);

                player1Shimmer.stopShimmerAnimation();
                player1Shimmer.setVisibility(View.GONE);
                player1ShimmerPic.stopShimmerAnimation();
                player1ShimmerPic.setVisibility(View.GONE);

                break;
            case 2:
                sco2=correctNum;
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(img2);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(propic2);
                headText2.setText(myName);
                name2.setText(myName);
                accuracy2.setText("Accuracy : "+acc+"%");
                correctvswrong2.setText("Correct/Wrong : "+mycorrect+"/"+mywrong);
                score2.setText("Total Score : "+correctNum);

                player2Shimmer.stopShimmerAnimation();
                player2Shimmer.setVisibility(View.GONE);
                player2ShimmerPic.stopShimmerAnimation();
                player2ShimmerPic.setVisibility(View.GONE);
                break;
            case 3:
                sco3=correctNum;
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(img3);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(propic3);
                headText3.setText(myName);
                name3.setText(myName);
                accuracy3.setText("Accuracy : "+acc+"%");
                correctvswrong3.setText("Correct/Wrong : "+mycorrect+"/"+mywrong);
                score3.setText("Total Score : "+correctNum);

                player3Shimmer.stopShimmerAnimation();
                player3Shimmer.setVisibility(View.GONE);
                player3ShimmerPic.stopShimmerAnimation();
                player3ShimmerPic.setVisibility(View.GONE);
                break;
            case 4:
                sco4=correctNum;
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(img4);
                Glide.with(getBaseContext()).load(myImageUrl).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(propic4);
                headText4.setText(myName);
                name4.setText(myName);
                accuracy4.setText("Accuracy : "+acc+"%");
                correctvswrong4.setText("Correct/Wrong : "+mycorrect+"/"+mywrong);
                score4.setText("Total Score : "+correctNum);

                player4Shimmer.stopShimmerAnimation();
                player4Shimmer.setVisibility(View.GONE);
                player4ShimmerPic.stopShimmerAnimation();
                player4ShimmerPic.setVisibility(View.GONE);
                break;
        }



        animManupulator("player1Answer","player2Answer","player3Answer","player4Answer");


    }
    public void animManupulator(String playerAAnswer, String playerBAnswer, String playerCAnswer, String playerDAnswer){

        animDataReceiving(playerAAnswer,anim1,anim2,anim3,anim4,anim5,anim6,anim7,anim8,anim9,anim10,animz1,animz2,animz3,animz4,animz5,animz6,animz7,animz8,animz9,animz10);
        animDataReceiving(playerBAnswer, anim11, anim12, anim13, anim14, anim15, anim16, anim17, anim18, anim19, anim20, animz11, animz12, animz13, animz14, animz15, animz16, animz17, animz18, animz19, animz20);
        animDataReceiving(playerCAnswer, anim21, anim22, anim23, anim24, anim25, anim26, anim27, anim28, anim29, anim30, animz21, animz22, animz23, animz24, animz25, animz26, animz27, animz28, animz29, animz30);
        animDataReceiving(playerDAnswer, anim31, anim32, anim33, anim34, anim35, anim36, anim37, anim38, anim39, anim40, animz31, animz32, animz33, animz34, animz35, animz36, animz37, animz38, animz39, animz40);

    }

    public void alertDialog123(String text, int pos){
        try {
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisner89);
        }catch (Exception e){

        }

        quitButton.setEnabled(true);
        quitButton.setAlpha(1);
        if(playerNum==1){
            lobbyButton.setEnabled(true);
            lobbyButton.setAlpha(1);
        }

        switch (playerNum){
            case 1:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 2:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 3:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 4:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
        }



        AlertDialog.Builder builder=new AlertDialog.Builder(tournamentBuzzerScoreCard.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(tournamentBuzzerScoreCard.this).inflate(R.layout.position_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        ((TextView) view1.findViewById(R.id.textTitle)).setText(text);
        ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
        ImageView img= view1.findViewById(R.id.imageIcon);
        img.setBackgroundResource(pos);

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
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
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

    public void alertDialog4(String s){
        try {
            myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisner89);
        }catch (Exception e){

        }

        quitButton.setEnabled(true);
        quitButton.setAlpha(1);
        if(playerNum==1){
            lobbyButton.setEnabled(true);
            lobbyButton.setAlpha(1);
        }

        switch (playerNum){
            case 1:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 2:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 3:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player4Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
            case 4:
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").removeEventListener(lisnernumber1);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player2Status").removeEventListener(lisnernumber2);
                }catch (Exception e){

                }
                try{
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("player3Status").removeEventListener(lisnernumber3);
                }catch (Exception e){

                }break;
        }



        AlertDialog.Builder builder=new AlertDialog.Builder(tournamentBuzzerScoreCard.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(tournamentBuzzerScoreCard.this).inflate(R.layout.lose_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        ((TextView) view1.findViewById(R.id.textTitle)).setText(s);
        Button yesButton=((Button) view1.findViewById(R.id.buttonYes));
        yesButton.setText("OKAY");

        final AlertDialog alertDialog=builder.create();
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
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
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

    public void showStopper(int numberOfPlayers){
        switch (numberOfPlayers){
            case 1:
                linearLayout2.setAlpha(0.5f);
                linearLayout3.setAlpha(0.5f);
                linearLayout4.setAlpha(0.5f);
                player2Shimmer.stopShimmerAnimation();
                player2Shimmer.setVisibility(View.GONE);
                player2ShimmerPic.stopShimmerAnimation();
                player2ShimmerPic.setVisibility(View.GONE);
                player3Shimmer.stopShimmerAnimation();
                player3Shimmer.setVisibility(View.GONE);
                player3ShimmerPic.stopShimmerAnimation();
                player3ShimmerPic.setVisibility(View.GONE);
                player4Shimmer.stopShimmerAnimation();
                player4Shimmer.setVisibility(View.GONE);
                player4ShimmerPic.stopShimmerAnimation();
                player4ShimmerPic.setVisibility(View.GONE);
                break;
            case 2:
                linearLayout3.setAlpha(0.5f);
                linearLayout4.setAlpha(0.5f);
                player3Shimmer.stopShimmerAnimation();
                player3Shimmer.setVisibility(View.GONE);
                player3ShimmerPic.stopShimmerAnimation();
                player3ShimmerPic.setVisibility(View.GONE);
                player4Shimmer.stopShimmerAnimation();
                player4Shimmer.setVisibility(View.GONE);
                player4ShimmerPic.stopShimmerAnimation();
                player4ShimmerPic.setVisibility(View.GONE);
                break;
            case 3:
                linearLayout4.setAlpha(0.5f);
                player4Shimmer.stopShimmerAnimation();
                player4Shimmer.setVisibility(View.GONE);
                player4ShimmerPic.stopShimmerAnimation();
                player4ShimmerPic.setVisibility(View.GONE);
                break;
            case 4:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        headShimmer.startShimmerAnimation();
        player1ShimmerPic.startShimmerAnimation();
        player2ShimmerPic.startShimmerAnimation();
        player3ShimmerPic.startShimmerAnimation();
        player4ShimmerPic.startShimmerAnimation();
        player1Shimmer.startShimmerAnimation();
        player2Shimmer.startShimmerAnimation();
        player3Shimmer.startShimmerAnimation();
        player4Shimmer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        headShimmer.stopShimmerAnimation();
        player1ShimmerPic.stopShimmerAnimation();
        player2ShimmerPic.stopShimmerAnimation();
        player3ShimmerPic.stopShimmerAnimation();
        player4ShimmerPic.stopShimmerAnimation();
        player1Shimmer.stopShimmerAnimation();
        player2Shimmer.stopShimmerAnimation();
        player3Shimmer.stopShimmerAnimation();
        player4Shimmer.stopShimmerAnimation();
        super.onPause();
    }

    public void animUiCallerCorrect(LottieAnimationView anim){
        anim.setAnimation(R.raw.tickanim);
        anim.playAnimation();
        anim.loop(false);
    }

    public void animUiCallerWrong(LottieAnimationView anim){
        anim.setAnimation(R.raw.wronganim);
        anim.playAnimation();
        anim.loop(false);
    }

    public void animUiCallerNotAnswered(LottieAnimationView anim){
        anim.setAnimation(R.raw.notattemptedanim);
        anim.setScaleX(0.85f);
        anim.setScaleY(0.85f);
        anim.playAnimation();
        anim.loop(false);
    }

    public void animDataReceiving(String playerAnswer, final LottieAnimationView anim1, final LottieAnimationView anim2, final LottieAnimationView anim3, final LottieAnimationView anim4, final LottieAnimationView anim5, final LottieAnimationView anim6, final LottieAnimationView anim7, final LottieAnimationView anim8, final LottieAnimationView anim9, final LottieAnimationView anim10, final LottieAnimationView animz1, final LottieAnimationView animz2, final LottieAnimationView animz3, final LottieAnimationView animz4, final LottieAnimationView animz5, final LottieAnimationView animz6, final LottieAnimationView animz7, final LottieAnimationView animz8, final LottieAnimationView animz9, final LottieAnimationView animz10){

        lisnercat3=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num) {
                        case 1:
                            animUiCallerCorrect(anim1);
                            break;
                        case 2:
                            animUiCallerWrong(anim1);
                            break;
                        case 3:
                            animUiCallerNotAnswered(anim1);
                            break;
                    }
                }catch (Exception e){

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(1)).addValueEventListener(lisnercat3);

        lisnercat4=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim2);break;
                        case 2:
                            animUiCallerWrong(anim2);break;
                        case 3:
                            animUiCallerNotAnswered(anim2);
                            break;
                    }
                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(2)).addValueEventListener(lisnercat4);

        lisnercat5=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim3);break;
                        case 2:
                            animUiCallerWrong(anim3);break;
                        case 3:
                            animUiCallerNotAnswered(anim3);
                            break;
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }; myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(3)).addValueEventListener(lisnercat5);

        lisnercat6=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim4);break;
                        case 2:
                            animUiCallerWrong(anim4);break;
                        case 3:
                            animUiCallerNotAnswered(anim4);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(4)).addValueEventListener(lisnercat6);

        lisnercat7=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim5);break;
                        case 2:
                            animUiCallerWrong(anim5);break;
                        case 3:
                            animUiCallerNotAnswered(anim5);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(5)).addValueEventListener(lisnercat7);

        lisnercat8=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim6);break;
                        case 2:
                            animUiCallerWrong(anim6);break;
                        case 3:
                            animUiCallerNotAnswered(anim6);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(6)).addValueEventListener(lisnercat8);

        lisnercat9=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim7);break;
                        case 2:
                            animUiCallerWrong(anim7);break;
                        case 3:
                            animUiCallerNotAnswered(anim7);
                            break;
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(7)).addValueEventListener(lisnercat9);

        lisnercat10=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim8);break;
                        case 2:
                            animUiCallerWrong(anim8);break;
                        case 3:
                            animUiCallerNotAnswered(anim8);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }; myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(8)).addValueEventListener(lisnercat10);

        lisnercat11=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim9);break;
                        case 2:
                            animUiCallerWrong(anim9);break;
                        case 3:
                            animUiCallerNotAnswered(anim9);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(9)).addValueEventListener(lisnercat11);

        lisnercat12=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(anim10);break;
                        case 2:
                            animUiCallerWrong(anim10);break;
                        case 3:
                            animUiCallerNotAnswered(anim10);
                            break;
                    }
                }catch (Exception e){

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(10)).addValueEventListener(lisnercat12);

        lisnercat13=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz1);break;
                        case 2:
                            animUiCallerWrong(animz1);break;
                        case 3:
                            animUiCallerNotAnswered(animz1);
                            break;
                    }
                }catch (Exception e){

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(11)).addValueEventListener(lisnercat13);

        lisnercat14=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz2);break;
                        case 2:
                            animUiCallerWrong(animz2);break;
                        case 3:
                            animUiCallerNotAnswered(animz2);
                            break;
                    }

                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(12)).addValueEventListener(lisnercat14);

        lisnercat15=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz3);break;
                        case 2:
                            animUiCallerWrong(animz3);break;
                        case 3:
                            animUiCallerNotAnswered(animz3);
                            break;
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(13)).addValueEventListener(lisnercat15);

        lisnercat16=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz4);break;
                        case 2:
                            animUiCallerWrong(animz4);break;
                        case 3:
                            animUiCallerNotAnswered(animz4);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }; myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(14)).addValueEventListener(lisnercat16);

        lisnercat17=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz5);break;
                        case 2:
                            animUiCallerWrong(animz5);break;
                        case 3:
                            animUiCallerNotAnswered(animz5);
                            break;
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(15)).addValueEventListener(lisnercat17);

        lisnercat18=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz6);break;
                        case 2:
                            animUiCallerWrong(animz6);break;
                        case 3:
                            animUiCallerNotAnswered(animz6);
                            break;
                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(16)).addValueEventListener(lisnercat18);

        lisnercat19=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz7);break;
                        case 2:
                            animUiCallerWrong(animz7);break;
                        case 3:
                            animUiCallerNotAnswered(animz7);
                            break;

                    }

                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(17)).addValueEventListener(lisnercat19);

        lisnercat20=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz8);break;
                        case 2:
                            animUiCallerWrong(animz8);break;
                        case 3:
                            animUiCallerNotAnswered(animz8);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(18)).addValueEventListener(lisnercat20);

        lisnercat21=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz9);break;
                        case 2:
                            animUiCallerWrong(animz9);break;
                        case 3:
                            animUiCallerNotAnswered(animz9);
                            break;
                    }
                }catch (Exception e){

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(19)).addValueEventListener(lisnercat21);

        lisnercat22=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num=snapshot.getValue(Integer.class);
                    switch (num){
                        case 1:
                            animUiCallerCorrect(animz10);break;
                        case 2:
                            animUiCallerWrong(animz10);break;
                        case 3:
                            animUiCallerNotAnswered(animz10);
                            break;
                    }
                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }; myRef.child("Lobby").child(String.valueOf(roomCode)).child(playerAnswer).child(String.valueOf(20)).addValueEventListener(lisnercat22);
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
    public void leaveDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(tournamentBuzzerScoreCard.this,R.style.AlertDialogTheme);
        View view =LayoutInflater.from(tournamentBuzzerScoreCard.this).inflate(R.layout.removal_lobby_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view);
        builder.setCancelable(false);


        TextView disText=(TextView) view.findViewById(R.id.textTitle);
        Button buttonYes=(Button) view.findViewById(R.id.buttonYes);
        disText.setText("Your Host "+hostName+" Has Either Disconnected Or Left The Game!");

        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        try{
            alertDialog.show();
        }catch (Exception e){

        }



        buttonYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentBuzzerScoreCard.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                try {
                    myRef.child("Lobby").child(String.valueOf(roomCode)).child("OnCompleteHolder").removeEventListener(listner45);
                }catch (Exception e){

                }

                if(playerNum==1){
                    del(mAuth.getCurrentUser().getUid());
                }else{
                    del(hostUid);
                }
            }
        });



    }

    public void opponentRemovedKnower(){
        lisner89=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int oppoStatus=snapshot.getValue(Integer.class);
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
        myRef.child("Lobby").child(String.valueOf(roomCode)).child("player1Status").addValueEventListener(lisner89);
    }

}