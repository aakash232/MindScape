package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Delayed;



public class HelpGuide1 extends AppCompatActivity {

    Context context;
    boolean flag=false;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_help_guide1);


            context=this;
            final Handler handler = new Handler();
            TextView skip = (TextView) findViewById(R.id.skip1);
            final ImageView hamburger = (ImageView) findViewById(R.id.hamburger);
            final ImageView profile = (ImageView) findViewById(R.id.profilebutton);
            final GridLayout modegrid = (GridLayout) findViewById(R.id.modegrid);
            final ConstraintLayout facts = (ConstraintLayout) findViewById(R.id.facts);
            final ImageView profilearrow = (ImageView) findViewById(R.id.profilearrow);
            final ImageView hamburgerarrow = (ImageView) findViewById(R.id.hamburgerarrow);
            //HelpView
            final LinearLayout help = (LinearLayout) findViewById(R.id.help);
            final TextView helpInfo = (TextView) findViewById(R.id.textView3);

            //Skip
            skip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent skipInt1 = new Intent(HelpGuide1.this, HelpGuide2.class);
                    startActivity(skipInt1);
                    flag=true;
                    finish();
                }
            });


               //Intro
                Toast.makeText(HelpGuide1.this,"Sit back and enjoy the tour!",Toast.LENGTH_LONG).show();

                //Step 1
                    hamburger.setAlpha(0.1f);
                    profile.setAlpha(0.1f);
                    modegrid.setAlpha(0.1f);
                    facts.setAlpha(0.1f);
                    help.setVisibility(View.VISIBLE);
                    fadeInAnimation(help);
                    helpInfo.setText("Welcome To MindScape!");

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fadeOutAnimation(help);

                            //Step 2

                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        fadeInAnimation(facts);
                                        fadeInAnimation(help);
                                        helpInfo.setText("Boost your knowledge every-day with new facts!");
                                    }
                                },1000);


                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        fadeOutAnimation(facts);
                                        fadeOutAnimation(help);

                                        //Step 3
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    fadeInAnimation(help);
                                                    helpInfo.setText("Select your Quiz mode from a wide list here!");
                                                }
                                            },1000);


                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        fadeOutAnimation(help);

                                                        //Step 4
                                                            fadeInAnimation(modegrid);

                                                            handler.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    fadeOutAnimation(modegrid);
                                                                    fadeOutAnimation(help);

                                                                    //Step 5
                                                                        profilearrow.setVisibility(View.VISIBLE);
                                                                        profilearrow.setAlpha(0.1f);
                                                                        fadeInAnimation(profilearrow);
                                                                        fadeInAnimation(profile);
                                                                        fadeInAnimation(help);
                                                                        helpInfo.setText("Track your performance and achievements in My Profile here!");

                                                                        handler.postDelayed(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                fadeOutAnimation(profilearrow);
                                                                                profilearrow.setVisibility(View.INVISIBLE);
                                                                                fadeOutAnimation(profile);
                                                                                fadeOutAnimation(help);

                                                                                //Step 6
                                                                                    handler.postDelayed(new Runnable() {
                                                                                        @Override
                                                                                        public void run() {
                                                                                            hamburgerarrow.setVisibility(View.VISIBLE);
                                                                                            hamburgerarrow.setAlpha(0.1f);
                                                                                            fadeInAnimation(hamburgerarrow);
                                                                                            fadeInAnimation(hamburger);
                                                                                            fadeInAnimation(help);
                                                                                            helpInfo.setText("For more Options, Here's your Drawer!");
                                                                                        }
                                                                                    },1000);


                                                                                    handler.postDelayed(new Runnable() {
                                                                                        @Override
                                                                                        public void run() {
                                                                                            fadeOutAnimation(hamburgerarrow);
                                                                                            hamburgerarrow.setVisibility(View.INVISIBLE);
                                                                                            fadeOutAnimation(hamburger);
                                                                                            fadeOutAnimation(help);

                                                                                            //Step 7
                                                                                                Intent move1 = new Intent(HelpGuide1.this,HelpGuide2.class);
                                                                                                if(!flag) startActivity(move1);
                                                                                                finish();
                                                                                        }
                                                                                    },5000);
                                                                            }
                                                                        },5000);
                                                                }
                                                            },3000);

                                                }
                                            },5000);
                                    }
                                },5000);
                        }
                    },3000);








        }


        void fadeOutAnimation(View viewToFadeOut) {
            ObjectAnimator fadeOut = ObjectAnimator.ofFloat(viewToFadeOut, "alpha", 1f, 0.1f);

            fadeOut.setDuration(1000);
            /*fadeOut.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                }
            }); */
            fadeOut.start();
        }


        void fadeInAnimation(View viewToFadeIn){
            ObjectAnimator fadeIn = ObjectAnimator.ofFloat(viewToFadeIn, "alpha", 0.1f, 1f);

            fadeIn.setDuration(1200);
            /*fadeIn.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                   viewToFadeIn.setVisibility(View.VISIBLE);
                }
            }); */
            fadeIn.start();
        }








}