package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HelpGuide2 extends AppCompatActivity {

    Context context;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_guide2);

        context=this;
        final Handler handler = new Handler();
        TextView skip = (TextView) findViewById(R.id.skip2);
        final LinearLayout timer = (LinearLayout) findViewById(R.id.timer);
        final LinearLayout qno = (LinearLayout) findViewById(R.id.questionNumber);
        final ImageView timerarrow = (ImageView) findViewById(R.id.timerarrow);
        final ImageView qnoarrow = (ImageView) findViewById(R.id.qnoarrow);
        final LinearLayout question = (LinearLayout) findViewById(R.id.question);
        final LinearLayout lifelines = (LinearLayout) findViewById(R.id.linearLayout6);
        final LinearLayout lifelinedes = (LinearLayout) findViewById(R.id.lifelinedes);
        final LinearLayout options = (LinearLayout) findViewById(R.id.linearButtonlayout);
        final LinearLayout help2 = (LinearLayout) findViewById(R.id.help2);
        final TextView help2info = (TextView) findViewById(R.id.help2info);
        final Button next = (Button) findViewById(R.id.nextbutton);

        //Skip
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent skipInt2 = new Intent(HelpGuide2.this, HelpGuide3.class);
                startActivity(skipInt2);
                flag=true;
                finish();
            }
        });


        //Intro
        Toast.makeText(HelpGuide2.this,"Quiz Layout",Toast.LENGTH_SHORT).show();

        //Step 1
            timer.setAlpha(0f);
            qno.setAlpha(0f);
            timerarrow.setAlpha(0f);
            qnoarrow.setAlpha(0f);
            question.setAlpha(0f);
            lifelinedes.setAlpha(0f);
            lifelines.setAlpha(0f);
            options.setAlpha(0f);
            help2.setAlpha(0f);
            next.setAlpha(0f);
            help2.setVisibility(View.VISIBLE);
            fadeInAnimation(question);
            fadeInAnimation(help2);
            help2info.setText("Read the question here!");

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //fadeOutAnimation(question);
                    fadeOutAnimation(help2);

                    //Step 2
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                fadeInAnimation(help2);
                                help2info.setText("Select the correct option! Green colour indicates you marked it right! If red, Oops! you missed it.");
                            }
                        },1000);

                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                fadeInAnimation(options);
                                fadeOutAnimation(help2);


                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        //Step 3
                                        fadeOutAnimation(options);

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                options.setVisibility(View.GONE);
                                            }
                                        },1000);

                                        fadeInAnimation(help2);
                                        help2info.setText("Four lifelines right up your alley to assist you. Choose them wisely!");

                                             handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                fadeOutAnimation(help2);

                                                //Step 4
                                                    lifelinedes.setVisibility(View.VISIBLE);
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            fadeInAnimation(lifelinedes);
                                                            fadeInAnimation(lifelines);
                                                            help2.setVisibility(View.INVISIBLE);
                                                        }
                                                    },1000);


                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            fadeOutAnimation(lifelines);
                                                            fadeOutAnimation(lifelinedes);

                                                            handler.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    lifelinedes.setVisibility(View.INVISIBLE);
                                                                    question.setVisibility(View.GONE);
                                                                }
                                                            },1000);


                                                            //Step 5
                                                                handler.postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {

                                                                        /* handler.postDelayed(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                fadeOutAnimation(question);
                                                                                question.setVisibility(View.GONE);
                                                                            }
                                                                        },1000); */

                                                                        help2.setVisibility(View.VISIBLE);
                                                                        fadeInAnimation(help2);
                                                                        timerarrow.setVisibility(View.VISIBLE);
                                                                        fadeInAnimation(timerarrow);
                                                                        fadeInAnimation(timer);
                                                                        help2info.setText("The clock keeps ticking! Keep your eye on it else the quiz will auto-finish!");
                                                                    }
                                                                },1000);


                                                                handler.postDelayed(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        fadeOutAnimation(timer);
                                                                        fadeOutAnimation(timerarrow);
                                                                        handler.postDelayed(new Runnable() {
                                                                            @Override
                                                                            public void run() {
                                                                                timerarrow.setVisibility(View.INVISIBLE);
                                                                            }
                                                                        },1000);

                                                                        fadeOutAnimation(help2);

                                                                        //Step 6
                                                                            handler.postDelayed(new Runnable() {
                                                                                @Override
                                                                                public void run() {
                                                                                    fadeInAnimation(qno);
                                                                                    qnoarrow.setVisibility(View.VISIBLE);
                                                                                    fadeInAnimation(qnoarrow);
                                                                                    fadeInAnimation(help2);
                                                                                    help2info.setText("Track your question progress here!");
                                                                                }
                                                                            },1000);

                                                                            handler.postDelayed(new Runnable() {
                                                                                @Override
                                                                                public void run() {
                                                                                    fadeOutAnimation(help2);
                                                                                    fadeOutAnimation(qno);
                                                                                    fadeOutAnimation(qnoarrow);

                                                                                    handler.postDelayed(new Runnable() {
                                                                                        @Override
                                                                                        public void run() {
                                                                                            qnoarrow.setVisibility(View.INVISIBLE);
                                                                                        }
                                                                                    },1000);



                                                                                    //Step 7
                                                                                        handler.postDelayed(new Runnable() {
                                                                                            @Override
                                                                                            public void run() {
                                                                                                //question.setVisibility(View.VISIBLE);
                                                                                                //options.setVisibility(View.VISIBLE);
                                                                                                fadeInAnimation(next);
                                                                                                fadeInAnimation(help2);
                                                                                                help2info.setText("Click next and move on to the new question!");

                                                                                                handler.postDelayed(new Runnable() {
                                                                                                    @Override
                                                                                                    public void run() {

                                                                                                        fadeOutAnimation(help2);
                                                                                                        fadeOutAnimation(next);

                                                                                                        //Step 8
                                                                                                        Intent move2 = new Intent(HelpGuide2.this,HelpGuide3.class);
                                                                                                        if(!flag) startActivity(move2);
                                                                                                        finish();
                                                                                                    }
                                                                                                },5000);

                                                                                            }
                                                                                        },1000);
                                                                                }
                                                                            },5000);
                                                                    }
                                                                },5000);


                                                        }
                                                    },15000);


                                                }
                                            },5000);
                                    }
                                },5000);
                            }
                        },5000);
                }
            },5000);






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
}