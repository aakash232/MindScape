package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.Index;

import java.util.ArrayList;
import java.util.List;

public class customQuizMainMenu extends AppCompatActivity {
    List<customQuizMainMenuHolder> lstExam;
    int setter=0;
    CardView customQuizMakerButton,joinPrivateButton;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    customQuizPropertiesSetter S;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz_main_menu);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Category");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        customQuizMakerButton=(CardView) findViewById(R.id.onlineButton);
        joinPrivateButton=(CardView) findViewById(R.id.joinPrivateButton);

        joinPrivateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(customQuizMainMenu.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                AlertDialog.Builder builder=new AlertDialog.Builder(customQuizMainMenu.this,R.style.AlertDialogTheme);

                final View view1= LayoutInflater.from(customQuizMainMenu.this).inflate(R.layout.custom_private_codeasker,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                builder.setView(view1);
                builder.setCancelable(false);

                final TextInputEditText codeTextView=(TextInputEditText) view1.findViewById(R.id.codeTextView);
                Button cancelButton=(Button) view1.findViewById(R.id.buttonNo);
                Button submitButton=(Button) view1.findViewById(R.id.buttonYes);




                final AlertDialog alertDialog=builder.create();
                if(alertDialog.getWindow()!=null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                try{
                    alertDialog.show();
                }catch (Exception e){

                }

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(customQuizMainMenu.this, R.raw.finalbuttonmusic);
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

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        final MediaPlayer musicNav;
                        musicNav = MediaPlayer.create(customQuizMainMenu.this, R.raw.finalbuttonmusic);
                        musicNav.start();
                        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mediaPlayer) {
                                musicNav.reset();
                                musicNav.release();
                            }
                        });

                        String t=codeTextView.getText().toString();
                        int p;
                        try{
                             p=Integer.parseInt(t);
                        }catch (Exception e){
                            p= -100;
                        }

                        S=new customQuizPropertiesSetter();
                        myRef.child("CustomQuiz").child("0").child("QuizProperties").orderByChild("quizCode").equalTo(p).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try{
                                    for(DataSnapshot snapshot1:snapshot.getChildren()){
                                        S=snapshot1.getValue(customQuizPropertiesSetter.class);
                                    }


                                    Intent intent=new Intent(customQuizMainMenu.this,customPublicQuiz.class);

                                    intent.putExtra("quizName",S.getQuizName());
                                    intent.putExtra("privacy",0);
                                    intent.putExtra("time",S.getTimeDuration());
                                    intent.putExtra("audienceLL",S.getAudienceLL());
                                    intent.putExtra("fiftyfiftyLL",S.getFiftyfiftyLL());
                                    intent.putExtra("expertLL",S.getExpertLL());
                                    intent.putExtra("key",S.getKey());
                                    intent.putExtra("rate",S.getRate());
                                    intent.putExtra("myKey",S.getMyKey());
                                    intent.putExtra("numberOfTimesPlayed",S.getNumberOfTimesPlayed());
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e){
                                    codeTextView.setError("Code Is Invalid");
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

        customQuizMakerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(customQuizMainMenu.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                Intent intent=new Intent(customQuizMainMenu.this,customQuizFirstProperties.class);
                startActivity(intent);
                finish();Runtime.getRuntime().gc();
            }
        });

        lstExam=new ArrayList<>();
        parto();
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        customQuizMainMenuAdapter myAdapter=new customQuizMainMenuAdapter(this,lstExam,setter);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);

    }

    public void parto(){
        lstExam.add(new customQuizMainMenuHolder("Historical Monuments",R.drawable.cathistoricalmonuments,"Check out the Quiz on historical monuments all over the world and test your trivia facts about the rich culture in various places and explore the essence of history. Get aboard for the fun-filled yet challenging tour you've ever experienced."));
        lstExam.add(new customQuizMainMenuHolder("Flora AndFauna",R.drawable.catfloranandfauna,"Do you have the green thumbs and know about plants and animals? Ace the wild quiz and be the king of the jungle!"));
        lstExam.add(new customQuizMainMenuHolder("Geography",R.drawable.catgeography,"Earth is an amazing planet. Take a spin around the Globe and test how much do you know about your Home!"));
        lstExam.add(new customQuizMainMenuHolder("Astronomy And Space",R.drawable.catspaceandastrology,"The mystery of space never stops to amze us. All aboard on the spacecraft! Time to inspire some out-of-the-world fantasy."));
        lstExam.add(new customQuizMainMenuHolder("Sports",R.drawable.catsports,"Take your mind on a jog across fields and tracks. Time to turn the Game Mode ON!"));
        lstExam.add(new customQuizMainMenuHolder("Some Superlatives",R.drawable.catsomesuperlatives,"The big game is here! Check out your skills and ace it all the way down!"));
        lstExam.add(new customQuizMainMenuHolder("Country,Capitals And Currencies",R.drawable.catcountriesandcapitals,"Bonjour quizzers! Get your compass aligned as we take a voyage across the globe scalling every landmass! Test your mind with capitals, currency and more things right here. Guide your ship to victory!"));
        lstExam.add(new customQuizMainMenuHolder("Famous Personalities",R.drawable.catfamousperson,"Life is a long journey and gave us many prominent people. Those who stood up and change the world's perspective forever in their own ways. Scan through different time eras and know abut these famous personalities."));
        lstExam.add(new customQuizMainMenuHolder("Science",R.drawable.catscience,"You won't need a PhD degree for these questions - though it would certainly help to ace it. Fire up your skills whith the magic that bloomed life."));
        lstExam.add(new customQuizMainMenuHolder("Important Dates And Events",R.drawable.catimportantdates,"Did you know? The first quiz was made in 1782! Do you know those several crucial dates in human history and want to test your knowledge about facts and events that changed the entire world? Its your time then! prove it who's the greatest quizzer right here!"));
        lstExam.add(new customQuizMainMenuHolder("Religion And Mythology",R.drawable.catrelionandculture,"Two terms with overlapping concepts considered even older than the universe! Time to put your spiritual skills to test."));
        lstExam.add(new customQuizMainMenuHolder("History",R.drawable.cathistory,"History is who we are and why we are the way we are! Get on your time machine for we are going way back to storm your brain with these quiz questions!"));
        lstExam.add(new customQuizMainMenuHolder("Film And Entertainment",R.drawable.catfilmandentertainment,"From Bollywood to Hollywood, from the Kardashians to Friends! and from The Oscars to Grammy! this quiz will make sure you have kept an eye on the world of TV,film,music and the intenet."));
        lstExam.add(new customQuizMainMenuHolder("Inventions And Discoveries",R.drawable.catinventionsanddiscovery,"Long, long time ago... Our earliest human ancestors invented the wheel, but who invented the ball bearing that reduces the friction in it? Let the wheels in your brain roll for the ultimate quiz has just walked in! "));
        lstExam.add(new customQuizMainMenuHolder("First In Different Fields",R.drawable.catfirstindifferentfields,"Many things in the world that took place for the first time. Ranging from adventures, expeditions, discoveries and inventions!"));
        lstExam.add(new customQuizMainMenuHolder("Festival,Art And Culture",R.drawable.catfestivalartandculture,"The soul and heart of a country. In this diverse world, quiz your mind about these varse events and ace up to the top!"));
        lstExam.add(new customQuizMainMenuHolder("Polity And Constitution",R.drawable.catlaws,"Order! Order! Test your knowlege about the ultimate essence that rules and guides a country ahead."));
        lstExam.add(new customQuizMainMenuHolder("Literature",R.drawable.literature,"Take a break from the busy life and get involved in the beautiful world of literature. Full of imagination, creativity and inspiration. Open doors to this new world and ace the questions right ahead!"));
        lstExam.add(new customQuizMainMenuHolder("Health And Disease",R.drawable.cathealthanddisease,"From your nose to toes, do you know how your body works? Are you aware of the physical and mental well-being? Test your doctor skills right here!"));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();Runtime.getRuntime().gc();
        }


        return super.onOptionsItemSelected(item);
    }


}