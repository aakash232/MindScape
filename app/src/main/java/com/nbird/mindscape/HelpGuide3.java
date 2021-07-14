package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HelpGuide3 extends AppCompatActivity {

    Context context;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_guide3);

        context=this;

        final Handler handler = new Handler();
        TextView skip = (TextView) findViewById(R.id.skip3);
        final LinearLayout ms = (LinearLayout) findViewById(R.id.MS);
        final CardView sptag = (CardView) findViewById(R.id.spTag);
        final CardView tourtag = (CardView) findViewById(R.id.TourTag);
        final CardView tourll = (CardView) findViewById(R.id.tour_ll);
        final LinearLayout spll = (LinearLayout) findViewById(R.id.sp_ll);
        final LinearLayout help = (LinearLayout) findViewById(R.id.help3);
        final TextView helpInfo = (TextView) findViewById(R.id.help3info);
        final LinearLayout levels = (LinearLayout) findViewById(R.id.levels);

        //Skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipInt3 = new Intent(HelpGuide3.this, mainMenuActivity.class);
                startActivity(skipInt3);
                flag=true;
                finish();
            }
        });

        //Step 1
        ms.setAlpha(0f);
        help.setAlpha(0f);
        levels.setAlpha(0f);
        tourll.setAlpha(0f);
        tourtag.setAlpha(0f);
        spll.setAlpha(0f);
        sptag.setAlpha(0f);

        help.setVisibility(View.VISIBLE);
        fadeInAnimation(help);
        helpInfo.setText("Technical Time! Let's know a bit about marking scheme! ");

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                fadeOutAnimation(help);

                //Step 2
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ms.setVisibility(View.VISIBLE);
                        sptag.setVisibility(View.VISIBLE);
                        spll.setVisibility(View.VISIBLE);
                        fadeInAnimation(ms);
                        fadeInAnimation(sptag);
                        fadeInAnimation(spll);

                        Toast.makeText(HelpGuide3.this,"Go through for a brief idea",Toast.LENGTH_LONG).show();
                    }
                },1000);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            fadeOutAnimation(sptag);
                            fadeOutAnimation(spll);
                            tourll.setVisibility(View.VISIBLE);
                            tourtag.setVisibility(View.VISIBLE);


                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    fadeInAnimation(tourll);
                                    fadeInAnimation(tourtag);
                                    sptag.setVisibility(View.INVISIBLE);
                                    spll.setVisibility(View.INVISIBLE);
                                }
                            },1000);

                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    fadeOutAnimation(tourll);
                                    fadeOutAnimation(tourtag);
                                    fadeOutAnimation(ms);

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            tourll.setVisibility(View.INVISIBLE);
                                            tourtag.setVisibility(View.INVISIBLE);
                                            ms.setVisibility(View.INVISIBLE);

                                            //Step 3
                                            helpInfo.setText("With each score, you Soar! Fight your way up to be the ultimate king! ");
                                            fadeInAnimation(help);
                                            levels.setVisibility(View.VISIBLE);
                                            fadeInAnimation(levels);

                                        }
                                    },1000);




                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            fadeOutAnimation(help);

                                                                   handler.postDelayed(new Runnable() {
                                                                       @Override
                                                                       public void run() {
                                                                           fadeOutAnimation(levels);

                                                                           handler.postDelayed(new Runnable() {
                                                                               @Override
                                                                               public void run() {
                                                                                   levels.setVisibility(View.GONE);
                                                                               }
                                                                           },1000);

                                                                           //Step 4

                                                                           handler.postDelayed(new Runnable() {
                                                                               @Override
                                                                               public void run() {

                                                                                   helpInfo.setText("Now you are all set! Go ahead and start Quizzing!!!");
                                                                                   fadeInAnimation(help);
                                                                               }
                                                                           },1000);

                                                                           handler.postDelayed(new Runnable() {
                                                                               @Override
                                                                               public void run() {

                                                                                   fadeOutAnimation(help);

                                                                                   //Step 5

                                                                                   handler.postDelayed(new Runnable() {
                                                                                       @Override
                                                                                       public void run() {

                                                                                           Intent move3 = new Intent(HelpGuide3.this, mainMenuActivity.class);
                                                                                           if(!flag) startActivity(move3);
                                                                                           finish();

                                                                                       }
                                                                                   },1000);


                                                                               }
                                                                           },5000);

                                                                       }
                                                                   },8000);



                                                        }
                                                    },5000);




                                }
                            },8000);


                        }
                    },8000);
            }
        }, 3000);







    }

    void fadeOutAnimation(View viewToFadeOut) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(viewToFadeOut, "alpha", 1f, 0f);

        fadeOut.setDuration(1000);
            /*fadeOut.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                }
            }); */
        fadeOut.start();
    }


    void fadeInAnimation(View viewToFadeIn){
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(viewToFadeIn, "alpha", 0f, 1f);

        fadeIn.setDuration(1200);
            /*fadeIn.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                   viewToFadeIn.setVisibility(View.VISIBLE);
                }
            }); */
        fadeIn.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }

}