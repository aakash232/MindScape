package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Random;
import java.util.zip.Inflater;

public class tournamentLobbyActivity extends AppCompatActivity {
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    ImageView image1,image2,image3,image4,main1Image,main2Image,main3Image,main4Image;
    TextView name1,totalTimeTaken1,accuracy1,score1;
    TextView name2,totalTimeTaken2,accuracy2,score2;
    TextView name3,totalTimeTaken3,accuracy3,score3;
    TextView name4,totalTimeTaken4,accuracy4,score4;

    leaderBoardHolder list1,list2,list3,list4;
    roomDataHolder listHostChanged;
    private List<mainMenuFactsHolder> list;
    String image1Url,image2Url,image3Url,image4Url,name1String,name2String,name3String,name4String;
    int a1,a2,a3,a4;
    int correct1,correct2,correct3,correct4;
    int wrong1,wrong2,wrong3,wrong4;
    int totalTime1Int,totalTime2Int,totalTime3Int,totalTime4Int;
    int score1Int,score2Int,score3Int,score4Int;
    int roomCode1,playerNum;
    String hostUid,hostImageUrl,hostName;
    String player1Uid,player2Uid,player3Uid,player4Uid;

    CardView chatButton,factsButton,settingButton,privacyButton,cancelButton;
   // AlertDialog.Builder builderFact;
   // View viewFact;
    ShimmerFrameLayout shimmerFact;

    private TextView[] mDots;
    private onevsoneonlineAdapter sliderAdapter;
    ActionBarDrawerToggle mToggle;
    private LinearLayout dotLayout;
    private int currentPage;
    public ViewPager slideViewPager;
    int num=0;
    RadioGroup radioGroup;
    Inflater inflater;
    TextView privacyTextView;
    int privacyFinder=0;
    CardView time3Button,time45Button,time6Button,num10Button,num15Button,num20Button,normalModeButton,pictureModeButton,shareButton,removePlayerButton;
    int numTime=0;int numQuestion=0;int numMode=0;
    TextView modeTextView,timeTextView,questionNumberTextView,roomCodeTextView;
    int numQ,numT,numM;
    private List<chatHolder> listChat;
    String myImageUrl,myName;
    chatAdapter oppoAdapter;
    chatMyAdapter myAdapter;
    int position=0;
    CountDownTimer countDownTimer,counterRemovalDownTimer,countDownHost;
    Button startButton;
    int starterNotHost=0;
    int removeInt1=0,removeInt2=0,removeInt3=0;
    String playerStatus89;
    int acu1,acu2,acu3,acu4;
    int min1,min2,min3,min4;
    int sec1,sec2,sec3,sec4;
    int numHostRemove,numQuestionRemove,numTimeRemove,numPrivacyRemove,numModeRemove;
    List<Integer> arrlist;
    ValueEventListener listener1,listener2,listener3,listener4,listener5;
    ValueEventListener listner10,listener31,listener32,listener33,listener34,removeListener;
    DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
    CardView buzzerNormalCard,buzzerPictureCard;
    List<pictureQuizHolder> list100;
    int setNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament_lobby);

        list = new ArrayList<>();
        listChat = new ArrayList<>();
        arrlist = new ArrayList<>(13);
        list100=new ArrayList<>();


        myAdapter= new chatMyAdapter(listChat);

        chatButton=(CardView) findViewById(R.id.card1chat);
        factsButton=(CardView) findViewById(R.id.card1fact);
        settingButton=(CardView) findViewById(R.id.cardcancel);
        privacyButton=(CardView) findViewById(R.id.cardchat);
        cancelButton=(CardView) findViewById(R.id.card1cancel);
        shareButton=(CardView) findViewById(R.id.shareButton);
        startButton=(Button) findViewById(R.id.startButton);
        removePlayerButton=(CardView) findViewById(R.id.removePlayer);

        main1Image=(ImageView) findViewById(R.id.imageView5);
        main2Image=(ImageView) findViewById(R.id.imageView7);
        main3Image=(ImageView) findViewById(R.id.imageView8);
        main4Image=(ImageView) findViewById(R.id.imageView6);

        modeTextView=(TextView) findViewById(R.id.modeTextView);
        privacyTextView=(TextView) findViewById(R.id.privacyTextView);
        timeTextView=(TextView) findViewById(R.id.timeTextView);
        questionNumberTextView=(TextView) findViewById(R.id.questionTextView);
        roomCodeTextView=(TextView) findViewById(R.id.roomCodeTextView);


        image1=(ImageView) findViewById(R.id.propic1);
        name1=(TextView) findViewById(R.id.name1);
        totalTimeTaken1=(TextView) findViewById(R.id.totalTimeTaken1);
        accuracy1=(TextView) findViewById(R.id.accuracy1);
        score1=(TextView) findViewById(R.id.score1);
        image2=(ImageView) findViewById(R.id.propic2);
        name2=(TextView) findViewById(R.id.name2);
        totalTimeTaken2=(TextView) findViewById(R.id.totalTimeTaken2);
        accuracy2=(TextView) findViewById(R.id.accuracy2);
        score2=(TextView) findViewById(R.id.score2);
        image3=(ImageView) findViewById(R.id.propic3);
        name3=(TextView) findViewById(R.id.name3);
        totalTimeTaken3=(TextView) findViewById(R.id.totalTimeTaken3);
        accuracy3=(TextView) findViewById(R.id.accuracy3);
        score3=(TextView) findViewById(R.id.score3);
        image4=(ImageView) findViewById(R.id.propic4);
        name4=(TextView) findViewById(R.id.name4);
        totalTimeTaken4=(TextView) findViewById(R.id.totalTimeTaken4);
        accuracy4=(TextView) findViewById(R.id.accuracy4);
        score4=(TextView) findViewById(R.id.score4);

        hostUid=getIntent().getStringExtra("hostUid");
        hostImageUrl=getIntent().getStringExtra("hostImage");
        hostName=getIntent().getStringExtra("hostName");
        roomCode1=getIntent().getIntExtra("roomCode",0);
        playerNum=getIntent().getIntExtra("Playernum",1);

        list1= new leaderBoardHolder();

        roomCodeTextView.setText(" Room Code : "+roomCode1);

        oppoAdapter = new chatAdapter(listChat,playerNum);


        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myImageUrl=snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myName=snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
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
                String shareBody="MindScape\nA Tournament Room Has Been Created\n"+"Here's Your Room Code : "+roomCode1+".";
                String sharesub="MindScape";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Room Code"));
            }
        });





     //   list3= new leaderBoardHolder();
     //   list4= new leaderBoardHolder();
        if(playerNum==1){
            hostDetailsRetrievingCreate();
            findplayer1Status();
            dataRetrievingByHost("player2Uid",image2,name2,totalTimeTaken2,accuracy2,score2,main2Image,2);
            dataRetrievingByHost("player3Uid",image3,name3,totalTimeTaken3,accuracy3,score3,main3Image,3);
            dataRetrievingByHost("player4Uid",image4,name4,totalTimeTaken4,accuracy4,score4,main4Image,4);
            ButtonManupulation(playerNum);
            otherPlayerUpdateWhenAPlayerIsRemoved();
            countDownForHost();
        }else{
            if(playerNum==2){
                setUidPlayer("player2Uid");
                DetailRetrievingJoin(mAuth.getCurrentUser().getUid(),image2,name2,totalTimeTaken2,accuracy2,score2,main2Image,2);
                hostDetailRetrievingJoin();
                findplayerSatus("player2Status",listener32);
                dataRetrievingByHost("player3Uid",image3,name3,totalTimeTaken3,accuracy3,score3,main3Image,3);
                dataRetrievingByHost("player4Uid",image4,name4,totalTimeTaken4,accuracy4,score4,main4Image,4);
                ButtonManupulation(playerNum);
                otherPlayerUpdateWhenAPlayerIsRemoved();
                playersRemovedWhenHostClosesTheApp();
            }else if(playerNum==3){
                setUidPlayer("player3Uid");
                DetailRetrievingJoin(mAuth.getCurrentUser().getUid(),image3,name3,totalTimeTaken3,accuracy3,score3,main3Image,3);
                hostDetailRetrievingJoin();
                findplayerSatus("player3Status",listener33);
                dataRetrievingByHost("player2Uid",image2,name2,totalTimeTaken2,accuracy2,score2,main2Image,2);
                dataRetrievingByHost("player4Uid",image4,name4,totalTimeTaken4,accuracy4,score4,main4Image,4);
                ButtonManupulation(playerNum);
                otherPlayerUpdateWhenAPlayerIsRemoved();
                playersRemovedWhenHostClosesTheApp();
            }else if(playerNum==4){
                setUidPlayer("player4Uid");
                DetailRetrievingJoin(mAuth.getCurrentUser().getUid(),image4,name4,totalTimeTaken4,accuracy4,score4,main4Image,4);
                hostDetailRetrievingJoin();
                findplayerSatus("player4Status",listener34);
                dataRetrievingByHost("player2Uid",image2,name2,totalTimeTaken2,accuracy2,score2,main2Image,2);
                dataRetrievingByHost("player3Uid",image3,name3,totalTimeTaken3,accuracy3,score3,main3Image,3);
                ButtonManupulation(playerNum);
                otherPlayerUpdateWhenAPlayerIsRemoved();
                playersRemovedWhenHostClosesTheApp();
            }

        }

    }


    public void ButtonManupulation(int playerNum){
        switch (playerNum){
            case 1:
                buttonHost();break;
            case 2:
            case 3:
            case 4:
                lobbyDataGettingNotHost();
                buttonNotHost();break;
        }
    }

    public void buttonHost(){
        factsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                factButtonFunction();
            }
        });

        privacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                privacyButtonFunction();
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                settingButtonFunction();
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                chatFunction();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                randomNumberGeneratorFunction();
                countDownSarter();
            }
        });
        removePlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                removePlayerFunction();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });

                cancelDialogFunction();

            }
        });
    }




    public void buttonNotHost(){
        gameStartNotHost();
        startButton.setText("Waiting For Host To Start The Game");
        startButton.setTextSize(12f);

        settingButton.setAlpha(0);
        privacyButton.setAlpha(0);
        removePlayerButton.setAlpha(0);

        factsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });

                factButtonFunction();
            }
        });

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                chatFunction();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                cancelDialogFunction();

            }
        });


    }

    @Override
    public void onBackPressed() {
        if(playerNum==1||playerNum==2||playerNum==3||playerNum==4){
            cancelDialogFunction();
        }

    }



    public void cancelDialogFunction(){
        AlertDialog.Builder builderRemove=new AlertDialog.Builder(tournamentLobbyActivity.this,R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(tournamentLobbyActivity.this).inflate(R.layout.quit_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
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
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
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
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
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

    public void playersRemovedWhenHostClosesTheApp(){
        removeListener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int t=snapshot.getValue(Integer.class);
                    if(t==0){
                        cancelLeaveFunction();
                        myRef.child("room").child("1").child(hostUid).removeValue();
                        myRef.child("room").child("0").child(hostUid).removeValue();
                        myRef.child("Lobby").child(String.valueOf(roomCode1)).removeValue();
                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Status").removeEventListener(removeListener);
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Status").addValueEventListener(removeListener);

    }


    public void countDownForHost(){
        countDownHost=new CountDownTimer(1000*60*120,1000) {
            @Override
            public void onTick(long l) {
                netStopper();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void netStopper(){
        if(playerNum==1){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int status=snapshot.getValue(Integer.class);
                        if(status==0){

                            String n=name2String;
                         //   myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                        //    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                       //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                      //      otherPlayerUpdateWhenAPlayerIsRemoved();
                                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").removeValue();
                                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeValue();

                                numPlayerManu();

                            removeInt1 =0;
                            main2Image.setImageDrawable(null);
                            image2.setImageDrawable(null);
                            name2.setText(null);
                            accuracy2.setText(null);
                            totalTimeTaken2.setText(null);
                            score2.setText(null);
                            player2Uid=null;
                            image2Url=null;
                            name2String=null;

                            if (!name3.getText().toString().isEmpty()) {
                                Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                                        .bitmapTransform(new RoundedCorners(14)))
                                        .into(image2);

                                Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                                        .bitmapTransform(new RoundedCorners(14)))
                                        .into(main2Image);
                                name2.setText(name3.getText().toString());
                                accuracy2.setText(accuracy3.getText().toString());
                                totalTimeTaken2.setText(totalTimeTaken3.getText().toString());
                                score2.setText(score3.getText().toString());

                                main3Image.setImageDrawable(null);
                                image3.setImageDrawable(null);
                                name3.setText(null);
                                accuracy3.setText(null);
                                totalTimeTaken3.setText(null);
                                score3.setText(null);
                                player2Uid=player3Uid;
                                image2Url=image3Url;
                                name2String=name3String;
                                player3Uid=null;
                                image3Url=null;
                                name3String=null;
                            }

                            if (!name4.getText().toString().isEmpty()) {

                                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                        .bitmapTransform(new RoundedCorners(14)))
                                        .into(image3);

                                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                        .bitmapTransform(new RoundedCorners(14)))
                                        .into(main3Image);
                                name3.setText(name4.getText().toString());
                                accuracy3.setText(accuracy4.getText().toString());
                                totalTimeTaken3.setText(totalTimeTaken4.getText().toString());
                                score3.setText(score4.getText().toString());

                                main4Image.setImageDrawable(null);
                                image4.setImageDrawable(null);
                                name4.setText(null);
                                accuracy4.setText(null);
                                totalTimeTaken4.setText(null);
                                score4.setText(null);
                                player3Uid=player4Uid;
                                image3Url=image4Url;
                                name3String=name4String;
                                player4Uid=null;
                                image4Url=null;
                                name4String=null;
                            }

                             //   deletingPlayerFromLayout(main2Image,image2,name2,accuracy2,totalTimeTaken2,score2);
                           // Toast.makeText(tournamentLobbyActivity.this, n+" Left The Lobby!", Toast.LENGTH_SHORT).show();

                        }
                    }catch (Exception e){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int status=snapshot.getValue(Integer.class);
                        if(status==0){
                            String n=name3String;
                    //        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                      //      myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                     //       myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                     //       otherPlayerUpdateWhenAPlayerIsRemoved();
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").removeValue();
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeValue();
                            numPlayerManu();

                            removeInt2 =0;
                            main3Image.setImageDrawable(null);
                            image3.setImageDrawable(null);
                            name3.setText(null);
                            accuracy3.setText(null);
                            totalTimeTaken3.setText(null);
                            score3.setText(null);
                            player3Uid=null;
                            image3Url=null;
                            name3String=null;

                            if (!name4.getText().toString().isEmpty()) {
                                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                        .bitmapTransform(new RoundedCorners(14)))
                                        .into(image2);

                                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                        .bitmapTransform(new RoundedCorners(14)))
                                        .into(main2Image);
                                name3.setText(name4.getText().toString());
                                accuracy3.setText(accuracy4.getText().toString());
                                totalTimeTaken3.setText(totalTimeTaken4.getText().toString());
                                score3.setText(score4.getText().toString());

                                main4Image.setImageDrawable(null);
                                image4.setImageDrawable(null);
                                name4.setText("");
                                accuracy4.setText("");
                                totalTimeTaken4.setText("");
                                score4.setText("");
                                player3Uid=player4Uid;
                                image3Url=image4Url;
                                name3String=name4String;
                                player4Uid=null;
                                image4Url=null;
                                name4String=null;
                            }

                        //    deletingPlayerFromLayout(main3Image,image3,name3,accuracy3,totalTimeTaken3,score3);
                   //         Toast.makeText(tournamentLobbyActivity.this, n+" Left The Lobby!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int status=snapshot.getValue(Integer.class);
                        if(status==0){
                            String n=name4String;
                         //   myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                     //       myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                       //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                     //       otherPlayerUpdateWhenAPlayerIsRemoved();

                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").removeValue();
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeValue();
                            numPlayerManu();

                            removeInt3 =0;
                            main4Image.setImageDrawable(null);
                            image4.setImageDrawable(null);
                            name4.setText("");
                            accuracy4.setText("");
                            totalTimeTaken4.setText("");
                            score4.setText("");
                            player4Uid=null;
                            image4Url=null;
                            name4String=null;

                         //   deletingPlayerFromLayout(main4Image,image4,name4,accuracy4,totalTimeTaken4,score4);
                      //      Toast.makeText(tournamentLobbyActivity.this, n+" Left The Lobby!", Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }else if(playerNum==2){

        }else if(playerNum==3){

        }else{

        }
    }



    public void cancelLeaveFunction(){
        if(playerNum==1){
                myRef.child("Lobby").child(String.valueOf(roomCode1)).removeValue();
                myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).removeValue();
                myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).removeValue();
                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Status").onDisconnect().cancel();
             if(counterRemovalDownTimer!=null){
                counterRemovalDownTimer.cancel();}
            if(countDownTimer!=null){
                countDownTimer.cancel();}
            if(countDownHost!=null){
                countDownHost.cancel();}
            tournamentLobbyActivity.super.onBackPressed();
            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            finish();

        } else if(playerNum==2){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").onDisconnect().cancel();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").removeEventListener(listener1);
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
            cancelNumManu();
            otherPlayerUpdateWhenAPlayerIsRemoved();
        }else if(playerNum==3){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").onDisconnect().cancel();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").removeEventListener(listener2);
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
            cancelNumManu();
            otherPlayerUpdateWhenAPlayerIsRemoved();
        }else if (playerNum==4){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").onDisconnect().cancel();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").removeEventListener(listener3);
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
            cancelNumManu();
            otherPlayerUpdateWhenAPlayerIsRemoved();
        }

    }


    public void cancelNumManu(){
        myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int numPlayers=snapshot.getValue(Integer.class);
                    numPlayers--;
                    myRef.child("room").child(String.valueOf(1)).child(hostUid).child("numberOfPlayers").setValue(numPlayers).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                int numPlayers=snapshot.getValue(Integer.class);
                                numPlayers--;
                                myRef.child("room").child(String.valueOf(0)).child(hostUid).child("numberOfPlayers").setValue(numPlayers).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void gameStartNotHost(){
        listener5=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    starterNotHost=snapshot.getValue(Integer.class);
                    if(starterNotHost==1){


                        countDownSarter();
                    }
                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").addValueEventListener(listener5);

    }

    public void countDownSarter(){

        if(playerNum==1){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameFinder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });



                    startButton.setEnabled(false);
                    chatButton.setEnabled(false);
                    factsButton.setEnabled(false);
                    settingButton.setEnabled(false);
                    privacyButton.setEnabled(false);
                    cancelButton.setEnabled(false);
                    removePlayerButton.setEnabled(false);
                    new CountDownTimer(1000 * 10, 1000) {


                        public void onTick(long millisUntilFinished) {
                            int sec = (int) (millisUntilFinished / 1000);
                            startButton.setText("Quiz Starts In " + sec + " Seconds");
                            startButton.setTextSize(15f);
                        }

                        @Override
                        public void onFinish() {
                            int numberOfPlayers=4;
                            if(name4.getText().toString().isEmpty()){
                                numberOfPlayers--;
                            }
                            if(name3.getText().toString().isEmpty()){
                                numberOfPlayers--;
                            }
                            if(name2.getText().toString().isEmpty()){
                                numberOfPlayers--;
                            }
                            if(counterRemovalDownTimer!=null){
                                counterRemovalDownTimer.cancel();}
                            if(countDownTimer!=null){
                                countDownTimer.cancel();}
                            if(countDownHost!=null){
                                countDownHost.cancel();}
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Uid").removeEventListener(listener4);
                           Intent intent;
                    if(numMode==0){
                         intent=new Intent(tournamentLobbyActivity.this,tournamentQuiz.class);
                    }else if(numMode==1){
                         intent=new Intent(tournamentLobbyActivity.this,tournamentPictureQuiz.class);
                    }else if(numMode==2){
                        intent=new Intent(tournamentLobbyActivity.this,tournamentBuzzerNormalQuiz.class);
                    }else{
                        intent=new Intent(tournamentLobbyActivity.this,tournamentBuzzerPictureQuiz.class);
                    }

                            intent.putExtra("myName",myName);
                            intent.putExtra("myImageUrl",myImageUrl);
                            intent.putExtra("hostUID",hostUid);
                            intent.putExtra("hostName",hostName);
                            intent.putExtra("hostImageUrl",hostImageUrl);
                            intent.putExtra("player2Name",name2String);
                            intent.putExtra("player3Name",name3String);
                            intent.putExtra("player4Name",name4String);
                            intent.putExtra("player2ImageUrl",image2Url);
                            intent.putExtra("player3ImageUrl",image3Url);
                            intent.putExtra("player4ImageUrl",image4Url);
                            intent.putExtra("playerNumber",playerNum);
                            intent.putExtra("roomCode",roomCode1);
                            intent.putExtra("questionNum",numQuestion);
                            intent.putExtra("timerNum",numTime);
                            intent.putExtra("numberOfPlayers",numberOfPlayers);
                            intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist);
                            startActivity(intent);
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }
                    }.start();
                }
            });
        }else{
            randomNumberGeneratorTaker();
            startButton.setEnabled(false);
            chatButton.setEnabled(false);
            factsButton.setEnabled(false);
            settingButton.setEnabled(false);
            privacyButton.setEnabled(false);
            cancelButton.setEnabled(false);
            removePlayerButton.setEnabled(false);
            new CountDownTimer(1000 * 9, 900) {


                public void onTick(long millisUntilFinished) {
                    int sec = (int) (millisUntilFinished / 900);
                    startButton.setText("Quiz Starts In " + sec + " Seconds");
                    startButton.setTextSize(15f);
                }

                @Override
                public void onFinish() {
                    int numberOfPlayers=4;
                    if(name4.getText().toString().isEmpty()){
                        numberOfPlayers--;
                    }
                    if(name3.getText().toString().isEmpty()){
                        numberOfPlayers--;
                    }
                    if(name2.getText().toString().isEmpty()){
                        numberOfPlayers--;
                    }
                    if(counterRemovalDownTimer!=null){
                        counterRemovalDownTimer.cancel();}
                    if(countDownTimer!=null){
                        countDownTimer.cancel();}
                    if(countDownHost!=null){
                        countDownHost.cancel();}
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                    Intent intent;
                    if(numMode==0){
                        intent=new Intent(tournamentLobbyActivity.this,tournamentQuiz.class);
                    }else if(numMode==1){
                        intent=new Intent(tournamentLobbyActivity.this,tournamentPictureQuiz.class);
                    }else if(numMode==2){
                        intent=new Intent(tournamentLobbyActivity.this,tournamentBuzzerNormalQuiz.class);
                    }else{
                        intent=new Intent(tournamentLobbyActivity.this,tournamentBuzzerPictureQuiz.class);
                    }
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
                    intent.putExtra("myName",myName);
                    intent.putExtra("myImageUrl",myImageUrl);
                    intent.putExtra("hostUID",hostUid);
                    intent.putExtra("hostName",hostName);
                    intent.putExtra("hostImageUrl",hostImageUrl);
                    intent.putExtra("player2Name",name2String);
                    intent.putExtra("player3Name",name3String);
                    intent.putExtra("player4Name",name4String);
                    intent.putExtra("player2ImageUrl",image2Url);
                    intent.putExtra("player3ImageUrl",image3Url);
                    intent.putExtra("player4ImageUrl",image4Url);
                    intent.putExtra("playerNumber",playerNum);
                    intent.putExtra("roomCode",roomCode1);
                    intent.putExtra("questionNum",numQuestion);
                    intent.putExtra("timerNum",numTime);
                    intent.putExtra("numberOfPlayers",numberOfPlayers);
                    intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }
            }.start();
        }
    }


    public void randomNumberGeneratorTaker() {

        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("OnCompleteHolder").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num47 = snapshot.getValue(Integer.class);
                    if (num47 == 1) {

                        if(numQuestion==0){
                            for(int i=1;i<=13;i++){
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        arrlist.add(snapshot.getValue(Integer.class));

                                    } catch (Exception e) {

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        }else if(numQuestion==1){
                            for(int i=1;i<=18;i++){
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        arrlist.add(snapshot.getValue(Integer.class));

                                    } catch (Exception e) {

                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        }else if(numQuestion==2){
                            for(int i=1;i<=23;i++){
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("questionPack").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        arrlist.add(snapshot.getValue(Integer.class));

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
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void randomNumberGeneratorFunction() {

        if(numQuestion==0){
            for (int i = 1; i <= 13; i++) {
            // create instance of Random class
            Random rand = new Random();

            // Generate random integers in range 0 to 29

                if(numMode==0){
                    Random rand1 = new Random();

                    setNumber=rand1.nextInt(6326)+1;
                }else if(numMode==1){
                    setNumber = rand.nextInt(4999)+1;

                    if(setNumber>1210&&setNumber<2000){
                        setNumber=setNumber-1000;
                    }
                }else if(numMode==2){
                    Random rand1 = new Random();

                    setNumber=rand1.nextInt(6326)+1;
                }else{
                    setNumber = rand.nextInt(4999)+1;

                    if(setNumber>1210&&setNumber<2000){
                        setNumber=setNumber-1000;
                    }
                }
             //NEED TO CHANGE HERE
            //NEED TO CHANGE HERE
            arrlist.add(setNumber);
            try{
                final int finalI = i;
                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("questionPack").child(String.valueOf(i)).setValue(arrlist.get(i-1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(finalI ==13){
                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("OnCompleteHolder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    }

                }
            });
            }catch (Exception e){
                Toast.makeText(this, "Your Internet Connection Speed Is Slow! Please Try To Fix It", Toast.LENGTH_SHORT).show();
            }

        }
        }else if(numQuestion==1){
            for (int i = 1; i <= 18; i++) {
            // create instance of Random class
            Random rand = new Random();

            // Generate random integers in range 0 to 29

                int setNumber;
                if(numMode==0){
                    Random rand1 = new Random();

                    setNumber=rand1.nextInt(6326)+1;
                }else if(numMode==1){
                    setNumber = rand.nextInt(4999)+1;

                    if(setNumber>1210&&setNumber<2000){
                        setNumber=setNumber-1000;
                    }
                }else if(numMode==2){
                    Random rand1 = new Random();

                    setNumber=rand1.nextInt(6326)+1;
                }else{
                    setNumber = rand.nextInt(4999)+1;

                    if(setNumber>1210&&setNumber<2000){
                        setNumber=setNumber-1000;
                    }
                } //NEED TO CHANGE HERE
            //NEED TO CHANGE HERE
            arrlist.add(setNumber);
            try{
                final int finalI = i;
                final int finalI1 = i;
                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("questionPack").child(String.valueOf(i)).setValue(arrlist.get(i-1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if(finalI ==18){
                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("OnCompleteHolder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {



                        }
                    });
                    }

                }
            });
            }catch (Exception e){
                Toast.makeText(this, "Your Internet Connection Speed Is Slow! Please Try To Fix It", Toast.LENGTH_SHORT).show();
            }

        }
        }else {
            for (int i = 1; i <= 23; i++) {
                // create instance of Random class
                Random rand = new Random();

                // Generate random integers in range 0 to 29

                int setNumber;
                if(numMode==0){
                    Random rand1 = new Random();

                    setNumber=rand1.nextInt(6326)+1;
                }else if(numMode==1){
                    setNumber = rand.nextInt(4999)+1;

                    if(setNumber>1210&&setNumber<2000){
                        setNumber=setNumber-1000;
                    }
                }else if(numMode==2){
                    Random rand1 = new Random();

                    setNumber=rand1.nextInt(6326)+1;
                }else{
                    setNumber = rand.nextInt(4999)+1;

                    if(setNumber>1210&&setNumber<2000){
                        setNumber=setNumber-1000;
                    }
                } //NEED TO CHANGE HERE
                //NEED TO CHANGE HERE
                arrlist.add(setNumber);
                try {
                    final int finalI = i;
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("questionPack").child(String.valueOf(i)).setValue(arrlist.get(i - 1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (finalI == 23) {
                                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("OnCompleteHolder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });
                            }

                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(this, "Your Internet Connection Speed Is Slow! Please Try To Fix It", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }



    public void removePlayerFunction(){
         AlertDialog.Builder builderRemove=new AlertDialog.Builder(tournamentLobbyActivity.this,R.style.AlertDialogTheme);
         View viewRemove= LayoutInflater.from(tournamentLobbyActivity.this).inflate(R.layout.remove_player_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
         builderRemove.setView(viewRemove);
         builderRemove.setCancelable(false);
         Button cancelButton=(Button) viewRemove.findViewById(R.id.cancelButton);
         final CardView cardView1=(CardView) viewRemove.findViewById(R.id.card10);
         final CardView cardView2=(CardView) viewRemove.findViewById(R.id.card15);
         final CardView cardView3=(CardView) viewRemove.findViewById(R.id.card20);
         final LinearLayout linearLayout1=(LinearLayout) viewRemove.findViewById(R.id.linear1);
        final LinearLayout linearLayout2=(LinearLayout) viewRemove.findViewById(R.id.linear2);
        final LinearLayout linearLayout3=(LinearLayout) viewRemove.findViewById(R.id.linear3);
        Button doneButton=(Button) viewRemove.findViewById(R.id.doneButton);
        ImageView img1=(ImageView) viewRemove.findViewById(R.id.img1);
        ImageView img2=(ImageView) viewRemove.findViewById(R.id.img2);
        ImageView img3=(ImageView) viewRemove.findViewById(R.id.img3);
        TextView text1=(TextView) viewRemove.findViewById(R.id.text1);
        TextView text2=(TextView) viewRemove.findViewById(R.id.text2);
        TextView text3=(TextView) viewRemove.findViewById(R.id.text3);



        final AlertDialog alertDialog=builderRemove.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        removeImageAndTextSetter(img1,img2,img3,text1,text2,text3,cardView1,cardView2,cardView3);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                removeInt1=removeInt2=removeInt3=0;
                alertDialog.dismiss();
            }
        });

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.navclick);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if(removeInt1==0){
                    linearLayout1.setAlpha(0.5f);
                    removeInt1=1;
                    if(!name3.getText().toString().isEmpty()){
                        cardView2.setAlpha(0.5f);
                        cardView2.setEnabled(false);
                    }
                    if(!name4.getText().toString().isEmpty()){
                        cardView3.setAlpha(0.5f);
                        cardView3.setEnabled(false);
                    }


                }else{
                    linearLayout1.setAlpha(1f);
                    removeInt1=0;
                    if(!name3.getText().toString().isEmpty()){
                        cardView2.setAlpha(1f);
                        cardView2.setEnabled(true);
                    }
                    if(!name4.getText().toString().isEmpty()){
                        cardView3.setAlpha(1f);
                        cardView3.setEnabled(true);
                    }


                }

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.navclick);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if(removeInt2==0){
                    linearLayout2.setAlpha(0.5f);
                    removeInt2=1;
                    cardView1.setAlpha(0.5f);
                    cardView1.setEnabled(false);

                    if(!name4.getText().toString().isEmpty()){
                        cardView3.setAlpha(0.5f);
                        cardView3.setEnabled(false);
                    }


                }else{
                    linearLayout2.setAlpha(1f);
                    removeInt2=0;
                    cardView1.setAlpha(1f);
                    cardView1.setEnabled(true);
                    if(!name4.getText().toString().isEmpty()){
                        cardView3.setAlpha(1f);
                        cardView3.setEnabled(true);
                    }

                }

            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.navclick);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if(removeInt3==0){
                    linearLayout3.setAlpha(0.5f);
                    removeInt3=1;
                    cardView2.setAlpha(0.5f);
                    cardView2.setEnabled(false);
                    cardView1.setAlpha(0.5f);
                    cardView1.setEnabled(false);
                }else{
                    linearLayout3.setAlpha(1f);
                    removeInt3=0;
                    cardView2.setAlpha(1f);
                    cardView2.setEnabled(true);
                    cardView1.setAlpha(1f);
                    cardView1.setEnabled(true);
                }

            }
        });


        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                removeActualFunction();

                alertDialog.dismiss();
            }
        });




    }

    public void removeNotAdmin(){
        counterRemovealOfPlayer();
    }

    public void counterRemovealOfPlayer(){
        counterRemovalDownTimer=new CountDownTimer(1000*60*120,1000) {
            @Override
            public void onTick(long l) {

                if(playerNum==2){
                    removeActualNotAdminBoss("player2Uid","player2Status");

                }
                if(playerNum==3){
                    removeActualNotAdminBoss("player3Uid","player3Status");

                }
                if(playerNum==4){
                    removeActualNotAdminBoss("player4Uid","player4Status");

                }

            }


            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void removeActualNotAdminBoss(final String player2Uid, final String playerStatus){
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child(player2Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                    String playerStatus89=snapshot.getValue(String.class);
                try{

                    if(playerStatus89.isEmpty()){

                    }


                }catch (Exception e){
                  //  Toast.makeText(tournamentLobbyActivity.this, "Gate 1", Toast.LENGTH_LONG).show();
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child(playerStatus).onDisconnect().cancel();
                    Intent intent=new Intent(tournamentLobbyActivity.this,mainMenuActivity.class);
                    intent.putExtra("removal",5);
                    intent.putExtra("hostName",hostName);
                    playerNum=0;
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Uid").removeEventListener(listener4);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
              //      Toast.makeText(tournamentLobbyActivity.this, " Reasons For Getting Out Of The Room:\n You Were Either Kicked By "+hostName+"\n Host : "+hostName+" Either Left The Room Or Was DisConnected Due To Which The Room Was Dissolved.\n Or You Your Self-Left The Room", Toast.LENGTH_LONG).show();
                    startActivity(intent);
                    if(counterRemovalDownTimer!=null){
                        counterRemovalDownTimer.cancel();}
                    if(countDownTimer!=null){
                        countDownTimer.cancel();}
                    if(countDownHost!=null){
                        countDownHost.cancel();
                    }
                    overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                    finish();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void otherPlayerUpdateWhenAPlayerIsRemoved(){



        listener1= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String playerStatus89=snapshot.getValue(String.class);
                try{

                    if(playerStatus89.isEmpty()){

                    }

                   if(playerNum==2){
                        if(!playerStatus89.equals(mAuth.getCurrentUser().getUid())){
                          //  Toast.makeText(tournamentLobbyActivity.this, "Gate 2", Toast.LENGTH_LONG).show();

                            Intent intent=new Intent(tournamentLobbyActivity.this,mainMenuActivity.class);
                            intent.putExtra("removal",5);
                            intent.putExtra("hostName",hostName);
                            playerNum=0;
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").onDisconnect().cancel();
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
                            //     Toast.makeText(tournamentLobbyActivity.this, " Reasons For Getting Out Of The Room:\n You Were Either Kicked By "+hostName+"\n Host : "+hostName+" Either Left The Room Or Was DisConnected Due To Which The Room Was Dissolved.\n Or You Your Self-Left The Room", Toast.LENGTH_LONG).show();
                            startActivity(intent);
                            if(counterRemovalDownTimer!=null){
                                counterRemovalDownTimer.cancel();}
                            if(countDownTimer!=null){
                                countDownTimer.cancel();}
                            if(countDownHost!=null){
                                countDownHost.cancel();
                            }
                            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }
                    }
                }catch (Exception e){
                    removeInt1=1;
                    if(playerNum==4){


                                deletingPlayerFromLayout(main2Image,image2,name2,accuracy2,totalTimeTaken2,score2);
                        try{
                            //connectedRef.removeEventListener(listener34);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").onDisconnect().cancel();
                        }catch (Exception e1){

                        }
                                findplayerSatus("player3Status",listener33);
                           //        connectedRef.removeEventListener(listener33);
                        playerNum--;


                        try{
                            main4Image.setImageDrawable(null);
                            image4.setImageDrawable(null);
                            name4.setText("");
                            accuracy4.setText("");
                            totalTimeTaken4.setText("");
                            score4.setText("");
                        }catch (Exception e1){

                        }


                   //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                     //      myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                       //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                   //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").addValueEventListener(listener3);






                    }else if(playerNum==3){
                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").setValue(mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(name4.getText().toString().isEmpty()){
                                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").removeValue();
                                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeValue();
                                    try{
                                        // connectedRef.removeEventListener(listener33);
                                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").onDisconnect().cancel();
                                    }catch (Exception e){

                                    }
                                    deletingPlayerFromLayout(main2Image,image2,name2,accuracy2,totalTimeTaken2,score2);
                                    //connectedRef.removeEventListener(listener33);
                                    findplayerSatus("player2Status",listener32);
                                    playerNum--;



                               //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                                 //   myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                              //      myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);

                               //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").addValueEventListener(listener1);
                                //    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").addValueEventListener(listener2);
                               //     myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").addValueEventListener(listener3);
                                } else{

                                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String uids=snapshot.getValue(String.class);
                                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").setValue(uids).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeValue();
                                                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").removeValue();
                                                    try{
                                                        // connectedRef.removeEventListener(listener33);
                                                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").onDisconnect().cancel();
                                                    }catch (Exception e){

                                                    }
                                                    deletingPlayerFromLayout(main2Image,image2,name2,accuracy2,totalTimeTaken2,score2);
                                             //       connectedRef.removeEventListener(listener33);
                                                    findplayerSatus("player2Status",listener32);

                                                    playerNum--;

                                                //    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").onDisconnect().cancel();


                                                    //            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                                        //            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                                         //           myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);

                                            //        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").addValueEventListener(listener1);
                                         //           myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").addValueEventListener(listener2);
                                          //          myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").addValueEventListener(listener3);

                                                    //      myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener2);
                                             //      myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener1);
                                                //    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener3);
                                                    otherPlayerUpdateWhenAPlayerIsRemoved();

                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }


                            }
                        });

                    }else if(playerNum==1){
                        deletingPlayerFromLayout(main2Image,image2,name2,accuracy2,totalTimeTaken2,score2);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").addValueEventListener(listener1);



        listener2=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String playerStatus89=snapshot.getValue(String.class);
                try{
                    if(playerStatus89.isEmpty()){

                    }
                   /* if(playerNum==3){
                        if(!playerStatus89.equals(mAuth.getCurrentUser().getUid())){
                            Intent intent=new Intent(tournamentLobbyActivity.this,mainMenuActivity.class);
                            intent.putExtra("removal",5);
                            intent.putExtra("hostName",hostName);
                            playerNum=0;
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").onDisconnect().cancel();
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
                            //   Toast.makeText(tournamentLobbyActivity.this, " Reasons For Getting Out Of The Room:\n You Were Either Kicked By "+hostName+"\n Host : "+hostName+" Either Left The Room Or Was DisConnected Due To Which The Room Was Dissolved.\n Or You Your Self-Left The Room", Toast.LENGTH_LONG).show();
                            startActivity(intent);

                            if(counterRemovalDownTimer!=null){
                                counterRemovalDownTimer.cancel();}
                            if(countDownTimer!=null){
                                countDownTimer.cancel();}
                            if(countDownHost!=null){
                                countDownHost.cancel();
                            }
                            finish();
                        }
                    }*/

                }catch (Exception e){
                    removeInt2=1;
                    if(playerNum==3){
                        if(!name4.getText().toString().isEmpty()){
                      //      Toast.makeText(tournamentLobbyActivity.this, "Gate 1", Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(tournamentLobbyActivity.this,mainMenuActivity.class);
                            intent.putExtra("removal",5);
                            intent.putExtra("hostName",hostName);
                            playerNum=0;
                            try{
                                // connectedRef.removeEventListener(listener33);
                                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").onDisconnect().cancel();
                            }catch (Exception e1){

                            }
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Uid").removeEventListener(listener4);
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").onDisconnect().cancel();
                            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("gameStarter").removeEventListener(listener5);
                            //   Toast.makeText(tournamentLobbyActivity.this, " Reasons For Getting Out Of The Room:\n You Were Either Kicked By "+hostName+"\n Host : "+hostName+" Either Left The Room Or Was DisConnected Due To Which The Room Was Dissolved.\n Or You Your Self-Left The Room", Toast.LENGTH_LONG).show();
                            startActivity(intent);

                            if(counterRemovalDownTimer!=null){
                                counterRemovalDownTimer.cancel();}
                            if(countDownTimer!=null){
                                countDownTimer.cancel();}
                            if(countDownHost!=null){
                                countDownHost.cancel();
                            }   overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                            finish();
                        }
                    } else if(playerNum==4){

                        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").setValue(mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeValue();
                            //    Toast.makeText(tournamentLobbyActivity.this, "gATE1", Toast.LENGTH_LONG).show();
                                deletingPlayerFromLayout(main3Image,image3,name3,accuracy3,totalTimeTaken3,score3);
                                try{
                                    // connectedRef.removeEventListener(listener33);
                                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").onDisconnect().cancel();
                                }catch (Exception e){

                                }
                                myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").removeValue();
                                findplayerSatus("player3Status",listener33);
                                playerNum--;
                                try{
                                    main4Image.setImageDrawable(null);
                                    image4.setImageDrawable(null);
                                    name4.setText("");
                                    accuracy4.setText("");
                                    totalTimeTaken4.setText("");
                                    score4.setText("");
                                }catch (Exception e1){

                                }
                            }
                        });
                    }else if(playerNum==2||playerNum==1){
                        deletingPlayerFromLayout(main3Image,image3,name3,accuracy3,totalTimeTaken3,score3);
                        try{
                            main4Image.setImageDrawable(null);
                            image4.setImageDrawable(null);
                            name4.setText("");
                            accuracy4.setText("");
                            totalTimeTaken4.setText("");
                            score4.setText("");
                        }catch (Exception e1){

                        }
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").addValueEventListener(listener2);


        listener3=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String playerStatus89=snapshot.getValue(String.class);
                try{
                    if(playerStatus89.isEmpty()){

                    }
                }catch (Exception e){
                    if(playerNum==2||playerNum==3||playerNum==1){
                        main4Image.setImageDrawable(null);
                        image4.setImageDrawable(null);
                        name4.setText("");
                        accuracy4.setText("");
                        totalTimeTaken4.setText("");
                        score4.setText("");
                        player4Uid=null;
                        image4Url=null;
                        name4String=null;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").addValueEventListener(listener3);

    }

    public void removeActualFunction(){
        if(removeInt1==1){
             myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").removeValue();
             myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player2Status").onDisconnect().cancel();
             numPlayerManu();
             deletingPlayerFromLayout(main2Image,image2,name2,accuracy2,totalTimeTaken2,score2);
        }else if(removeInt2==1){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player3Status").onDisconnect().cancel();
            numPlayerManu();
            deletingPlayerFromLayout(main3Image,image3,name3,accuracy3,totalTimeTaken3,score3);
        }else if(removeInt3==1){
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Uid").removeValue();
            myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player4Status").onDisconnect().cancel();
            numPlayerManu();
            deletingPlayerFromLayout(main4Image,image4,name4,accuracy4,totalTimeTaken4,score4);
        }
    }

    public void deletingPlayerFromLayout( ImageView main2Image, ImageView image2, TextView name2, TextView accuracy2, TextView totalTimeTaken2, TextView score2) {
        if (removeInt1 == 1) {
            removeInt1 =0;
            main2Image.setImageDrawable(null);
            image2.setImageDrawable(null);
            name2.setText(null);
            accuracy2.setText(null);
            totalTimeTaken2.setText(null);
            score2.setText(null);
            player2Uid=null;
            image2Url=null;
            name2String=null;


            if (!name3.getText().toString().isEmpty()) {
                Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(image2);

                Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(main2Image);
                name2.setText(name3.getText().toString());
                accuracy2.setText(accuracy3.getText().toString());
                totalTimeTaken2.setText(totalTimeTaken3.getText().toString());
                score2.setText(score3.getText().toString());

                main3Image.setImageDrawable(null);
                image3.setImageDrawable(null);
                name3.setText(null);
                accuracy3.setText(null);
                totalTimeTaken3.setText(null);
                score3.setText(null);
                player2Uid=player3Uid;
                image2Url=image3Url;
                name2String=name3String;
                player3Uid=null;
                image3Url=null;
                name3String=null;
                dataRetrievingByHost("player3Uid",image3,name3,totalTimeTaken3,accuracy3,score3,main3Image,3);
            }

            if (!name4.getText().toString().isEmpty()) {

                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(image3);

                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(main3Image);
                name3.setText(name4.getText().toString());
                accuracy3.setText(accuracy4.getText().toString());
                totalTimeTaken3.setText(totalTimeTaken4.getText().toString());
                score3.setText(score4.getText().toString());
                main4Image.setImageDrawable(null);
                image4.setImageDrawable(null);
                name4.setText(null);
                accuracy4.setText(null);
                totalTimeTaken4.setText(null);
                score4.setText(null);
                player3Uid=player4Uid;
                image3Url=image4Url;
                name3String=name4String;
                player4Uid=null;
                image4Url=null;
                name4String=null;
                dataRetrievingByHost("player4Uid",image4,name4,totalTimeTaken4,accuracy4,score4,main4Image,4);

            }

        }else if (removeInt2==1){
            removeInt2 =0;
            main3Image.setImageDrawable(null);
            image3.setImageDrawable(null);
            name3.setText(null);
            accuracy3.setText(null);
            totalTimeTaken3.setText(null);
            score3.setText(null);
            player3Uid=null;
            image3Url=null;
            name3String=null;

            if (!name4.getText().toString().isEmpty()) {
                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(image2);

                Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(14)))
                        .into(main2Image);
                name3.setText(name4.getText().toString());
                accuracy3.setText(accuracy4.getText().toString());
                totalTimeTaken3.setText(totalTimeTaken4.getText().toString());
                score3.setText(score4.getText().toString());

                main4Image.setImageDrawable(null);
                image4.setImageDrawable(null);
                name4.setText("");
                accuracy4.setText("");
                totalTimeTaken4.setText("");
                score4.setText("");
                player3Uid=player4Uid;
                image3Url=image4Url;
                name3String=name4String;
                player4Uid=null;
                image4Url=null;
                name4String=null;

                dataRetrievingByHost("player4Uid",image4,name4,totalTimeTaken4,accuracy4,score4,main4Image,4);
            }
        }else if(removeInt3==1){
            removeInt3 =0;
            main4Image.setImageDrawable(null);
            image4.setImageDrawable(null);
            name4.setText("");
            accuracy4.setText("");
            totalTimeTaken4.setText("");
            score4.setText("");
            player4Uid=null;
            image4Url=null;
            name4String=null;

        }

    }

    public void numPlayerManu(){
        myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int numPlayers=snapshot.getValue(Integer.class);
                    numPlayers--;
                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(numPlayers).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }catch (Exception e){
                    myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try {
                                int numPlayers=snapshot.getValue(Integer.class);
                                numPlayers--;
                                myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").setValue(numPlayers).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void removeImageAndTextSetter(ImageView img1, ImageView img2, ImageView img3, TextView text1, TextView text2, TextView text3, CardView cardView1, CardView cardView2, CardView cardView3){
        if(image2Url!=null&&name2String!=null){
            cardView1.setAlpha(1);
            text1.setText(name2String);
            Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(img1);
        }
        if(image3Url!=null&&name3String!=null){
            cardView2.setAlpha(1);
            text2.setText(name3String);
            Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(img2);
        }
        if(image4Url!=null&&name4String!=null){
            cardView3.setAlpha(1);
            text3.setText(name4String);
            Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                    .bitmapTransform(new RoundedCorners(14)))
                    .into(img3);
        }
    }



    public void chatFunction(){
        AlertDialog.Builder builderFact=new AlertDialog.Builder(tournamentLobbyActivity.this,R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(tournamentLobbyActivity.this).inflate(R.layout.chat_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);
        Button cancelButton=(Button) viewFact.findViewById(R.id.cancelButton);
        Button sendButton=(Button) viewFact.findViewById(R.id.sendButton);
        final EditText editText=(EditText) viewFact.findViewById(R.id.editText);
        final RecyclerView recyclerView=(RecyclerView) viewFact.findViewById(R.id.recyclerview);

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
       // linearLayoutManager.setStackFromEnd(true);
      //  linearLayoutManager.setReverseLayout(true);
       // linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(oppoAdapter);

        linearLayoutManager.setStackFromEnd(true);






        final AlertDialog alertDialog=builderFact.create();


        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                listChat.clear();
                alertDialog.dismiss();

            }
        });

        countDownTimer(recyclerView);



        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myText=editText.getText().toString();

                editText.setText("");
                if(myText.isEmpty()){
                    Toast.makeText(tournamentLobbyActivity.this, "Please Enter Some Text!!", Toast.LENGTH_SHORT).show();
                }else{
                    final MediaPlayer musicNav;
                    musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.navclick);
                    musicNav.start();
                    musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            musicNav.reset();
                            musicNav.release();
                        }
                    });
                    chatHolder s1=new chatHolder(myImageUrl,myName,myText,playerNum,position);
                    listChat.add(s1);
                    position++;
                    recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
                    oppoAdapter.notifyDataSetChanged();
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("chat").child(String.valueOf(playerNum)).push().setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
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
            }
        });





    }

    public void countDownTimer(final RecyclerView recyclerView){
        countDownTimer = new CountDownTimer(1000*60*120, 2000) {


            public void onTick(long millisUntilFinished) {

                if(playerNum==1){
                    chatOpponentRet(2,recyclerView);
                    chatOpponentRet(3, recyclerView);
                    chatOpponentRet(4, recyclerView);
                }else if(playerNum==2){
                    chatOpponentRet(1, recyclerView);
                    chatOpponentRet(3, recyclerView);
                    chatOpponentRet(4, recyclerView);
                }else if(playerNum==3){
                    chatOpponentRet(1, recyclerView);
                    chatOpponentRet(2, recyclerView);
                    chatOpponentRet(4, recyclerView);
                }else if(playerNum==4){
                    chatOpponentRet(1, recyclerView);
                    chatOpponentRet(2, recyclerView);
                    chatOpponentRet(3, recyclerView);
                }
            }

            @Override
            public void onFinish() {


            }
        }.start();

    }

    public void chatOpponentRet(int i, final RecyclerView recyclerView){

         myRef.child("Lobby").child(String.valueOf(roomCode1)).child("chat").child(String.valueOf(i)).orderByChild("position10").equalTo(position).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                        listChat.add(dataSnapshot1.getValue(chatHolder.class));
                        position++;
                    }
                    recyclerView.smoothScrollToPosition(recyclerView.getAdapter().getItemCount());
                    oppoAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    public void lobbyDataGettingNotHost(){

        myRef.child("room").child(String.valueOf(1)).child(hostUid).child("privacy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {

                    int p = snapshot.getValue(Integer.class);


                    privacyTextView.setText(" Privacy : Public ");
                    myRef.child("room").child(String.valueOf(1)).child(hostUid).child("questionNumber").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            try{
                                int c=snapshot.getValue(Integer.class);
                                questionNumberTextView.setText(" Question : " + snapshot.getValue(Integer.class));

                                switch (c){
                                    case 10:
                                        numQuestion=0;break;
                                    case 15:
                                        numQuestion=1;break;
                                    case 20:
                                        numQuestion=2;break;
                                }
                            }catch (Exception e){

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    myRef.child("room").child(String.valueOf(1)).child(hostUid).child("mode").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                 int jack = snapshot.getValue(Integer.class);
                            if (jack == 0) {
                                numMode=0;
                                modeTextView.setText(" Mode : Normal ");
                            } else if(jack==1){
                                numMode=1;
                                modeTextView.setText(" Mode : Picture ");
                            }else if(jack==2){
                                numMode=2;
                                modeTextView.setText(" Mode : Buzzer Normal ");
                            }else{
                                numMode=3;
                                modeTextView.setText(" Mode : Buzzer Picture ");
                            }
                            }catch (Exception e){
                            //    saver();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    myRef.child("room").child(String.valueOf(1)).child(hostUid).child("time").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                int goat = snapshot.getValue(Integer.class);
                            if (goat == 45) {
                                int f=snapshot.getValue(Integer.class);
                                switch (f){
                                    case 3:
                                        numTime=0;break;
                                    case 45:
                                        numTime=1;break;
                                    case 6:
                                        numTime=2;break;
                                }
                                timeTextView.setText(" Time : 4.5 Mins ");
                            } else {
                                int f=snapshot.getValue(Integer.class);
                                switch (f){
                                    case 3:
                                        numTime=0;break;
                                    case 45:
                                        numTime=1;break;
                                    case 6:
                                        numTime=2;break;
                                }
                                timeTextView.setText(" Time : " + goat + " Mins ");
                            }
                            }catch (Exception e){
                            //    saver();
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } catch (Exception e) {

               //      saver();


                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myRef.child("room").child(String.valueOf(0)).child(hostUid).child("privacy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {

                    int p = snapshot.getValue(Integer.class);



                    myRef.child("room").child(String.valueOf(0)).child(hostUid).child("questionNumber").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {


                            try{
                                int c=snapshot.getValue(Integer.class);
                                questionNumberTextView.setText(" Question : " + snapshot.getValue(Integer.class));
                                switch (c){
                                    case 10:
                                        numQuestion=0;break;
                                    case 15:
                                        numQuestion=1;break;
                                    case 20:
                                        numQuestion=2;break;
                                }
                            }catch (Exception e){

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                    myRef.child("room").child(String.valueOf(0)).child(hostUid).child("mode").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                int jack = snapshot.getValue(Integer.class);

                                if (jack == 0) {
                                    numMode=0;
                                    modeTextView.setText(" Mode : Normal ");
                                } else if(jack==1){
                                    numMode=1;
                                    modeTextView.setText(" Mode : Picture ");
                                }else if(jack==2){
                                    numMode=2;
                                    modeTextView.setText(" Mode : Buzzer Normal ");
                                }else{
                                    numMode=3;
                                    modeTextView.setText(" Mode : Buzzer Picture ");
                                }
                                privacyTextView.setText(" Privacy : Private ");
                            }catch (Exception e){

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    myRef.child("room").child(String.valueOf(0)).child(hostUid).child("time").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            try{
                                int goat = snapshot.getValue(Integer.class);
                                if (goat == 45) {
                                    int f=snapshot.getValue(Integer.class);
                                    switch (f){
                                        case 3:
                                            numTime=0;break;
                                        case 45:
                                            numTime=1;break;
                                        case 6:
                                            numTime=2;break;
                                    }
                                    timeTextView.setText(" Time : 4.5 Mins ");
                                } else {
                                    int f=snapshot.getValue(Integer.class);
                                    switch (f){
                                        case 3:
                                            numTime=0;break;
                                        case 45:
                                            numTime=1;break;
                                        case 6:
                                            numTime=2;break;
                                    }
                                    timeTextView.setText(" Time : " + goat + " Mins ");
                                }
                            }catch (Exception e){

                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                } catch (Exception e) {




                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public void settingButtonFunction(){
        AlertDialog.Builder builderFact=new AlertDialog.Builder(tournamentLobbyActivity.this,R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(tournamentLobbyActivity.this).inflate(R.layout.setting_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);
        Button okayButton=(Button) viewFact.findViewById(R.id.factButton);

        final AlertDialog alertDialog=builderFact.create();

        time3Button=(CardView) viewFact.findViewById(R.id.card4);
        time45Button=(CardView) viewFact.findViewById(R.id.card5);
        time6Button=(CardView) viewFact.findViewById(R.id.card6);
        num10Button=(CardView) viewFact.findViewById(R.id.card10);
        num15Button=(CardView) viewFact.findViewById(R.id.card15);
        num20Button=(CardView) viewFact.findViewById(R.id.card20);
        normalModeButton=(CardView) viewFact.findViewById(R.id.card7);
        pictureModeButton=(CardView) viewFact.findViewById(R.id.card8);
        buzzerNormalCard=(CardView) viewFact.findViewById(R.id.buzzerNormalCard);
        buzzerPictureCard=(CardView) viewFact.findViewById(R.id.buzzerPictureCard);
        LinearLayout linearTime3 =(LinearLayout) viewFact.findViewById(R.id.linear4);
        LinearLayout linearTime45 =(LinearLayout) viewFact.findViewById(R.id.linear5);
        LinearLayout linearTime6 =(LinearLayout) viewFact.findViewById(R.id.linear6);
        LinearLayout linearnum10 =(LinearLayout) viewFact.findViewById(R.id.linear1);
        LinearLayout linearnum15 =(LinearLayout) viewFact.findViewById(R.id.linear2);
        LinearLayout linearnum20 =(LinearLayout) viewFact.findViewById(R.id.linear3);
        LinearLayout linearmodeNormal =(LinearLayout) viewFact.findViewById(R.id.linear7);
        LinearLayout linearmodePicture =(LinearLayout) viewFact.findViewById(R.id.linear8);
        LinearLayout buzzerNormalLinear=(LinearLayout) viewFact.findViewById(R.id.buzzerNormalLinear);
        LinearLayout buzzerPictureLinear=(LinearLayout) viewFact.findViewById(R.id.buzzerPictureLinear);


        Button done=(Button) viewFact.findViewById(R.id.doneButton);


        settingButtonsManupulation(linearTime3,linearTime6,linearTime45,linearnum10,linearnum15,linearnum20,linearmodeNormal,linearmodePicture,time3Button,time6Button,time45Button,num10Button,num15Button,num20Button,normalModeButton,pictureModeButton,buzzerNormalLinear,buzzerPictureLinear);

        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                switch (numQuestion){
                    case 0:
                        questionNumberTextView.setText(" Questions : 10");
                        dataSettingHostQuestion(10);break;
                    case 1:
                        questionNumberTextView.setText(" Questions : 15");
                        dataSettingHostQuestion(15);break;
                    case 2:
                        questionNumberTextView.setText(" Questions : 20");
                        dataSettingHostQuestion(20);break;
                }

                switch (numTime){
                    case 0:
                        timeTextView.setText(" Time : 3 Mins");
                        dataSettingHostTime(3);break;
                    case 1:
                        timeTextView.setText(" Time : 4.5 Mins");
                        dataSettingHostTime(45);break;
                    case 2:
                        timeTextView.setText(" Time : 6 Mins");
                        dataSettingHostTime(6);break;
                }

                switch (numMode){
                    case 0:
                        modeTextView.setText(" Mode : Normal ");
                        dataSettingHostMode(0);break;
                    case 1:
                        modeTextView.setText(" Mode : Picture ");
                        dataSettingHostMode(1);break;
                    case 2:
                        modeTextView.setText(" Mode : Buzzer Normal");
                        dataSettingHostMode(2);break;
                    case 3:
                        modeTextView.setText(" Mode : Buzzer Picture");
                        dataSettingHostMode(3);break;
                }

                alertDialog.dismiss();
            }
        });
    }

    public void dataSettingHostQuestion(int question){

        int can;
        if(privacyFinder==0){
            can=1;
        }else{
            can=0;
        }

            myRef.child("room").child(String.valueOf(can)).child(mAuth.getCurrentUser().getUid()).child("questionNumber").setValue(question).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

    }

    public void dataSettingHostMode(int mode){
        int can;
        if(privacyFinder==0){
            can=1;
        }else{
            can=0;
        }

        myRef.child("room").child(String.valueOf(can)).child(mAuth.getCurrentUser().getUid()).child("mode").setValue(mode).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }

    public void dataSettingHostTime(int time){
        int can;
        if(privacyFinder==0){
            can=1;
        }else{
            can=0;
        }
        myRef.child("room").child(String.valueOf(can)).child(mAuth.getCurrentUser().getUid()).child("time").setValue(time).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

    }


    public void settingButtonsManupulation(final LinearLayout linearTime3, final LinearLayout linearTime6, final LinearLayout linearTime45, final LinearLayout linearnum10, final LinearLayout linearnum15, final LinearLayout linearnum20, final LinearLayout linearmodeNormal, final LinearLayout linearmodePicture, CardView time3Button, CardView time6Button, CardView time45Button, CardView num10Button, CardView num15Button, CardView num20Button, CardView normalModeButton, CardView pictureModeButton, final LinearLayout buzzerNormalLinear, final LinearLayout buzzerPictureLinear){
        if(numQuestion==0){
            linearnum10.setAlpha(0.5f);
            linearnum15.setAlpha(1);
            linearnum20.setAlpha(1);
        }else if(numQuestion==1){
            linearnum10.setAlpha(1);
            linearnum15.setAlpha(0.5f);
            linearnum20.setAlpha(1);
        }else{
            linearnum10.setAlpha(1);
            linearnum15.setAlpha(1);
            linearnum20.setAlpha(0.5f);
        }

        if(numTime==0){
            linearTime3.setAlpha(0.5f);
            linearTime45.setAlpha(1);
            linearTime6.setAlpha(1);
        }else if(numTime==1){
            linearTime3.setAlpha(1);
            linearTime45.setAlpha(0.5f);
            linearTime6.setAlpha(1);
        }else{
            linearTime3.setAlpha(1);
            linearTime45.setAlpha(1);
            linearTime6.setAlpha(0.5f);
        }

        if(numMode==0){
            linearmodeNormal.setAlpha(0.5f);
            linearmodePicture.setAlpha(1);
            buzzerNormalLinear.setAlpha(1);
            buzzerPictureLinear.setAlpha(1);
        }else if(numMode==1){
            linearmodeNormal.setAlpha(1);
            linearmodePicture.setAlpha(0.5f);
            buzzerNormalLinear.setAlpha(1);
            buzzerPictureLinear.setAlpha(1);
        }else if(numMode==2){
            linearmodeNormal.setAlpha(1);
            linearmodePicture.setAlpha(1);
            buzzerNormalLinear.setAlpha(0.5f);
            buzzerPictureLinear.setAlpha(1);
        }else{
            linearmodeNormal.setAlpha(1);
            linearmodePicture.setAlpha(1);
            buzzerNormalLinear.setAlpha(1);
            buzzerPictureLinear.setAlpha(0.5f);
        }

        time3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numTime=0;
                linearTime3.setAlpha(0.5f);
                linearTime45.setAlpha(1);
                linearTime6.setAlpha(1);
            }
        });

        time45Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numTime=1;
                linearTime3.setAlpha(1);
                linearTime45.setAlpha(0.5f);
                linearTime6.setAlpha(1);
            }
        });

        time6Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numTime=2;
                linearTime3.setAlpha(1);
                linearTime45.setAlpha(1);
                linearTime6.setAlpha(0.5f);
            }
        });

        num10Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numQuestion=0;
                linearnum10.setAlpha(0.5f);
                linearnum15.setAlpha(1);
                linearnum20.setAlpha(1);
            }
        });

        num15Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numQuestion=1;
                linearnum10.setAlpha(1);
                linearnum15.setAlpha(0.5f);
                linearnum20.setAlpha(1);
            }
        });

        num20Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numQuestion=2;
                linearnum10.setAlpha(1);
                linearnum15.setAlpha(1);
                linearnum20.setAlpha(0.5f);
            }
        });

        normalModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numMode=0;
                linearmodeNormal.setAlpha(0.5f);
                linearmodePicture.setAlpha(1);
                buzzerNormalLinear.setAlpha(1);
                buzzerPictureLinear.setAlpha(1);
            }
        });

        pictureModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numMode=1;
                linearmodeNormal.setAlpha(1);
                linearmodePicture.setAlpha(0.5f);
                buzzerNormalLinear.setAlpha(1);
                buzzerPictureLinear.setAlpha(1);
            }
        });

        buzzerNormalCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numMode=2;
                linearmodeNormal.setAlpha(1);
                linearmodePicture.setAlpha(1);
                buzzerNormalLinear.setAlpha(0.5f);
                buzzerPictureLinear.setAlpha(1);
            }
        });

        buzzerPictureCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numMode=3;
                linearmodeNormal.setAlpha(1);
                linearmodePicture.setAlpha(1);
                buzzerNormalLinear.setAlpha(1);
                buzzerPictureLinear.setAlpha(0.5f);
            }
        });




    }

    public void privacyButtonFunction(){

        AlertDialog.Builder builderFact=new AlertDialog.Builder(tournamentLobbyActivity.this,R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(tournamentLobbyActivity.this).inflate(R.layout.privacy_setting_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);
        Button okayButton=(Button) viewFact.findViewById(R.id.factButton);

        final AlertDialog alertDialog=builderFact.create();

        radioGroup = (RadioGroup) viewFact.findViewById(R.id.groupradio);
        Button done=(Button) viewFact.findViewById(R.id.buttonYes);

        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();

        RadioButton radioButton1 = (RadioButton) radioGroup.findViewById(R.id.radio_public);
        RadioButton radioButton2 = (RadioButton) radioGroup.findViewById(R.id.radio_private);
        // Get the selected Radio Button
        if(privacyFinder==0){
            radioButton1.setChecked(true);
            radioButton2.setChecked(false);
        }else{
            radioButton2.setChecked(true);
            radioButton1.setChecked(false);
        }

        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId)
                    {

                    }
                });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When submit button is clicked,
                // Ge the Radio Button which is set
                // If no Radio Button is set, -1 will be returned
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                int selectedId = radioGroup.getCheckedRadioButtonId();


                    RadioButton radioButton = (RadioButton)radioGroup.findViewById(selectedId);

                privacyTextView.setText(" Privacy : "+radioButton.getText()+" ");
                if (selectedId == -1) {
                    Toast.makeText(tournamentLobbyActivity.this, "Select Any One Option", Toast.LENGTH_LONG);
                }else if(radioButton.getText().equals("Public")){
                    privacyFinder=0;
                    privacyDataUploaderHost(0);
                    Toast.makeText(tournamentLobbyActivity.this, "Public", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }else{
                    privacyFinder=1;
                    privacyDataUploaderHost(1);
                    Toast.makeText(tournamentLobbyActivity.this, "Private", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
                }



                    // Now display the value of selected item
                    // by the Toast message


            }
        });

    }

    public void privacyDataUploaderHost(int privacyFinder){

        switch (numQuestion){
            case 0:
                 numQ=10;break;
            case 1:
                numQ=15;break;
            case 2:
                numQ=20;break;
        }

        switch (numTime){
            case 0:
                numT=3;break;
            case 1:
                numT=45;break;
            case 2:
                numT=6;break;
        }

        switch (numMode){
            case 0:
                numM=0;break;
            case 1:
                numM=1;break;
        }


        if(privacyFinder==0){

                myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                    int numberplayer=snapshot.getValue(Integer.class);
                    roomDataHolder s1 = new roomDataHolder(mAuth.getCurrentUser().getUid(),numberplayer,image1Url,name1String,roomCode1,numQ,numT,numM,1);

                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).removeValue();
                        }
                    });
                    }catch (Exception e){

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }else{

                myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("numberOfPlayers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                        int numberplayer=snapshot.getValue(Integer.class);
                        roomDataHolder s1 = new roomDataHolder(mAuth.getCurrentUser().getUid(),numberplayer,image1Url,name1String,roomCode1,numQ,numT,numM,0);

                        myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).removeValue();
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
    }

    public void factButtonFunction(){
        list.clear();
        AlertDialog.Builder builderFact=new AlertDialog.Builder(tournamentLobbyActivity.this,R.style.AlertDialogTheme);
        final View viewFact= LayoutInflater.from(tournamentLobbyActivity.this).inflate(R.layout.factfull_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderFact.setView(viewFact);
        builderFact.setCancelable(false);
        Button okayButton=(Button) viewFact.findViewById(R.id.factButton);
        slideViewPager=(ViewPager) viewFact.findViewById(R.id.slideViewPager);
        dotLayout=(LinearLayout) viewFact.findViewById(R.id.dotLayout);

        final AlertDialog alertDialog=builderFact.create();



        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        alertDialog.show();
        for(int i=1;i<=3;i++){
            dataForHorizontalSlide();
        }
        okayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // alertDialog.cancel();

                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(tournamentLobbyActivity.this, R.raw.finalbuttonmusic);
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

    public void dataRetrievingByHost(String pUid, final ImageView image, final TextView name, final TextView totalTimeTaken, final TextView accuracy, final TextView score, final ImageView mainImage, final int i){
        listener4=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String playerUid;
                playerUid=snapshot.getValue(String.class);

                if(playerUid!=null){
                    DetailRetrievingJoin(playerUid,image,name,totalTimeTaken,accuracy,score,mainImage, i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child(pUid).addValueEventListener(listener4);

    }

    public void setUidPlayer(String playerUID){
        myRef.child("Lobby").child(String.valueOf(roomCode1)).child(playerUID).setValue(mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                removeNotAdmin();
            }
        });
    }

    public void findplayerSatus(final String statusStr,ValueEventListener listener){

        listener=new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child(statusStr).setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child(statusStr).onDisconnect().setValue(0);

                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        connectedRef.addValueEventListener(listener);
    }



    public void findplayer1Status(){
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Status").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    myRef.child("Lobby").child(String.valueOf(roomCode1)).child("player1Status").onDisconnect().setValue(0);

                }
                else {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void roomListViewPushingByHost(){
        roomDataHolder s1 = new roomDataHolder(mAuth.getCurrentUser().getUid(),1,image1Url,name1String,roomCode1,10,3,0,1);

        myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void DetailRetrievingJoin(final String Uid, final ImageView image2, final TextView name2, final TextView totalTimeTaken2, final TextView accuracy2, final TextView score2, final ImageView main2Image, int i){
        switch (i){
            case 2:
                list2= new leaderBoardHolder();
        myRef.child("leaderBoard").child("1vs1").child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    list2 = snapshot.getValue(leaderBoardHolder.class);
                    image2Url = list2.getImageUrl();
                    Glide.with(getBaseContext())
                            .load(image2Url)
                            .preload(20, 10);


                    Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(image2);

                    Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(main2Image);
                    name2String = list2.getUsername();
                    correct2 = list2.getCorrect();
                    wrong2 = list2.getWrong();
                    score2Int = list2.getScore();
                    totalTime2Int = list2.getTotalTime();
                    acu2 = ((correct2 * 100) / (correct2 + wrong2));

                    if (totalTime2Int < 60) {
                        totalTimeTaken2.setText(String.valueOf("  Total Time : " + totalTime2Int + " sec "));
                    } else {
                        min2 = totalTime2Int / 60;
                        sec2 = totalTime2Int % 60;
                        totalTimeTaken2.setText(String.valueOf("  Total Time : " + min2 + " min " + sec2 + " sec "));
                    }

                    name2.setText(" " + name2String + " ");
                    accuracy2.setText("  Accuracy : " + acu2 + "% ");
                    score2.setText(" Highest Score : " + score2Int + " ");

                } catch (Exception e) {
                    myRef.child("User").child(Uid).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            image2Url = snapshot.getValue(String.class);
                            myRef.child("User").child(Uid).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    name2String = snapshot.getValue(String.class);

                                    Glide.with(getBaseContext())
                                            .load(image2Url)
                                            .preload(20, 10);

                                    Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(image2);
                                    Glide.with(getBaseContext()).load(image2Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(main2Image);
                                    name2.setText(" " + name2String + " ");
                                    totalTimeTaken2.setText("  Total Time Played : null");
                                    accuracy2.setText("  Accuracy : null");
                                    score2.setText(" Highest Score : null");
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
        });break;
            case 3:
                list2= new leaderBoardHolder();
        myRef.child("leaderBoard").child("1vs1").child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    list2 = snapshot.getValue(leaderBoardHolder.class);
                    image3Url = list2.getImageUrl();


                    Glide.with(getBaseContext())
                            .load(image3Url)
                            .preload(20, 10);

                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(image2);

                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(main2Image);
                    name3String = list2.getUsername();
                    correct3 = list2.getCorrect();
                    wrong3 = list2.getWrong();
                    score3Int = list2.getScore();
                    totalTime3Int = list2.getTotalTime();
                    acu3 = ((correct3 * 100) / (correct3 + wrong3));

                    if (totalTime3Int < 60) {
                        totalTimeTaken2.setText(String.valueOf("  Total Time : " + totalTime3Int + " sec "));
                    } else {
                        min3 = totalTime3Int / 60;
                        sec3 = totalTime3Int % 60;
                        totalTimeTaken2.setText(String.valueOf("  Total Time : " + min3 + " min " + sec3 + " sec "));
                    }

                    name2.setText(" " + name3String + " ");
                    accuracy2.setText("  Accuracy : " + acu3 + "% ");
                    score2.setText(" Highest Score : " + score3Int + " ");

                } catch (Exception e) {
                    myRef.child("User").child(Uid).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            image3Url = snapshot.getValue(String.class);
                            myRef.child("User").child(Uid).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    name3String = snapshot.getValue(String.class);

                                    Glide.with(getBaseContext())
                                            .load(image3Url)
                                            .preload(20, 10);

                                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(image2);
                                    Glide.with(getBaseContext()).load(image3Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(main2Image);
                                    name2.setText(" " + name3String + " ");
                                    totalTimeTaken2.setText("  Total Time Played : null");
                                    accuracy2.setText("  Accuracy : null");
                                    score2.setText(" Highest Score : null");
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
        });break;
            case 4:
                list2= new leaderBoardHolder();
        myRef.child("leaderBoard").child("1vs1").child(Uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    list2 = snapshot.getValue(leaderBoardHolder.class);
                    image4Url = list2.getImageUrl();

                    Glide.with(getBaseContext())
                            .load(image4Url)
                            .preload(20, 10);

                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(image2);

                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(main2Image);
                    name4String = list2.getUsername();
                    correct4 = list2.getCorrect();
                    wrong4 = list2.getWrong();
                    score4Int = list2.getScore();
                    totalTime4Int = list2.getTotalTime();
                    acu4 = ((correct4 * 100) / (correct4 + wrong4));

                    if (totalTime4Int < 60) {
                        totalTimeTaken2.setText(String.valueOf("  Total Time : " + totalTime4Int + " sec "));
                    } else {
                        min4 = totalTime4Int / 60;
                        sec4 = totalTime4Int % 60;
                        totalTimeTaken2.setText(String.valueOf("  Total Time : " + min4 + " min " + sec4 + " sec "));
                    }

                    name2.setText(" " + name4String + " ");
                    accuracy2.setText("  Accuracy : " + acu4 + "% ");
                    score2.setText(" Highest Score : " + score4Int + " ");

                } catch (Exception e) {
                    myRef.child("User").child(Uid).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            image4Url = snapshot.getValue(String.class);
                            myRef.child("User").child(Uid).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    name4String = snapshot.getValue(String.class);
                                    Glide.with(getBaseContext())
                                            .load(image4Url)
                                            .preload(20, 10);
                                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(image2);
                                    Glide.with(getBaseContext()).load(image4Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(main2Image);
                                    name2.setText(" " + name4String + " ");
                                    totalTimeTaken2.setText("  Total Time Played : null");
                                    accuracy2.setText("  Accuracy : null");
                                    score2.setText(" Highest Score : null");
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
        });break;



        }

    }

    public void hostDetailRetrievingJoin(){

        myRef.child("leaderBoard").child("1vs1").child(hostUid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    list1=snapshot.getValue(leaderBoardHolder.class);
                    image1Url=list1.getImageUrl();

                    Glide.with(getBaseContext())
                            .load(image1Url)
                            .preload(20, 10);

                    Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(image1);

                    Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(main1Image);
                    name1String=list1.getUsername();correct1=list1.getCorrect();wrong1=list1.getWrong();score1Int=list1.getScore();totalTime1Int=list1.getTotalTime();
                    int accuPercent=((correct1*100)/(correct1+wrong1));

                    if(totalTime1Int<60){
                        totalTimeTaken1.setText(String.valueOf("  Total Time : "+totalTime1Int+" sec "));
                    }else{
                        int minutes=totalTime1Int/60;
                        int sec=totalTime1Int%60;
                        totalTimeTaken1.setText(String.valueOf("  Total Time : "+minutes+" min "+sec+" sec "));
                    }


                    name1.setText(" "+name1String+" ");
                    accuracy1.setText("  Accuracy : "+accuPercent+"% ");score1.setText(" Highest Score : "+score1Int+" ");

                }catch (Exception e){
                    myRef.child("User").child(hostUid).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            image1Url=snapshot.getValue(String.class);
                            myRef.child("User").child(hostUid).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    name1String=snapshot.getValue(String.class);
                                    Glide.with(getBaseContext())
                                            .load(image1Url)
                                            .preload(20, 10);
                                    Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(image1);
                                    Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(main1Image);
                                    name1.setText(" "+name1String+" ");totalTimeTaken1.setText("  Total Time Played : null");
                                    accuracy1.setText("  Accuracy : null");score1.setText(" Highest Score : null");
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


    public void hostDetailsRetrievingCreate(){

        myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                      list1=snapshot.getValue(leaderBoardHolder.class);
                      image1Url=list1.getImageUrl();

                    Glide.with(getBaseContext())
                            .load(image1Url)
                            .preload(20, 10);

                      Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(image1);

                      Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(14)))
                            .into(main1Image);
                      name1String=list1.getUsername();correct1=list1.getCorrect();wrong1=list1.getWrong();score1Int=list1.getScore();totalTime1Int=list1.getTotalTime();
                      int accuPercent=((correct1*100)/(correct1+wrong1));

                      roomListViewPushingByHost();

                    if(totalTime1Int<60){
                        totalTimeTaken1.setText(String.valueOf("  Total Time : "+totalTime1Int+" sec "));
                    }else{
                        int minutes=totalTime1Int/60;
                        int sec=totalTime1Int%60;
                        totalTimeTaken1.setText(String.valueOf("  Total Time : "+minutes+" min "+sec+" sec "));
                    }

                      name1.setText(" "+name1String+" ");
                      accuracy1.setText("  Accuracy : "+accuPercent+"% ");score1.setText(" Highest Score : "+score1Int+" ");

                }catch (Exception e){
                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            image1Url=snapshot.getValue(String.class);
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                   name1String=snapshot.getValue(String.class);
                                    roomListViewPushingByHost();

                                    Glide.with(getBaseContext())
                                            .load(image1Url)
                                            .preload(20, 10);

                                    Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(image1);
                                    Glide.with(getBaseContext()).load(image1Url).apply(RequestOptions
                                            .bitmapTransform(new RoundedCorners(14)))
                                            .into(main1Image);
                                    name1.setText(" "+name1String+" ");totalTimeTaken1.setText("  Total Time Played : null");
                                    accuracy1.setText("  Accuracy : null");score1.setText(" Highest Score : null");
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

    public void dataForHorizontalSlide() {

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
                    AdapterManupulation();
                    num=0;
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(tournamentLobbyActivity.this, "Facts Data Can't be Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void AdapterManupulation() {
        sliderAdapter = new onevsoneonlineAdapter(tournamentLobbyActivity.this, list);
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

}