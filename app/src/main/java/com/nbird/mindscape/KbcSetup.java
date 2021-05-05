package com.nbird.mindscape;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class KbcSetup extends AppCompatActivity {


    LinearLayout linearLayoutButton,linearLayoutPrice,lifelineLayout;
    int count=0,position=0,score=0;
    TextView kbc_que;
    private List<questionHolder> list;
    TextView timerText;
    CountDownTimer countDownTimer;
    Button half,advice,audience,flip,quit;
    Button button1,button2,button3,button4;

    MediaPlayer kbcQueMus,kbcCountMus;

    int selectNum;
    int halfnum=0;
    int audiencenum=0;
    int expertnum;
    int flipnum=0;
    int manupulator=0;
    int manupulator1=0;

    int yo1;
    int yo2;
    int yo3;
    int yo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_setup);

        kbcQueMus=MediaPlayer.create(KbcSetup.this,R.raw.kbc_que_start);
        kbcCountMus=MediaPlayer.create(KbcSetup.this,R.raw.kb_tick);

        half= findViewById(R.id.half);
        advice= findViewById(R.id.expert);
        flip= findViewById(R.id.flip);
        audience= findViewById(R.id.audience);
        quit= findViewById(R.id.quit);

        button1=findViewById(R.id.opt1);
        button2=findViewById(R.id.opt2);
        button3=findViewById(R.id.opt3);
        button4=findViewById(R.id.opt4);

        linearLayoutButton = findViewById(R.id.optLayout);
        linearLayoutPrice=findViewById(R.id.linearLayoutPrice);
        lifelineLayout=findViewById(R.id.lifeLineLayout);
        kbc_que = findViewById(R.id.kbc_que);
        timerText = findViewById(R.id.timerText);

        list = new ArrayList<>();
        easyFunction();

        countDownTimer = new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                kbcCountMus.start();
                if(millisUntilFinished<=9)
                    timerText.setText("0" + millisUntilFinished / 1000);
                else
                    timerText.setText(""+ millisUntilFinished/1000);
            }
            public void onFinish() {
                countDownTimer.start();
                kbcCountMus.stop();
            }

        }.start();


        playAnim(kbc_que,0,list.get(position).getQuestionTextView());

        for (int i = 0; i < 4; i++) {
            linearLayoutButton.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View view) {
                    checkAnswer((Button) view);
                }
            });
        }


        half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(halfnum==0) {
                    halfnum = 1;
                    lifelineLayout.getChildAt(0).setBackgroundResource(R.drawable.half_used);
                    if (button1.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator1 = 1;
                    } else if (button2.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator1 = 2;
                    } else if (button3.getText().toString().equals(list.get(position).getCorrectAnswer())) {
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
                                    button3.setText("");
                                    button4.setText("");
                                    break;
                                case 3:
                                    button2.setText("");
                                    button4.setText("");
                                    break;
                                case 4:
                                    button2.setText("");
                                    button3.setText("");
                                    break;
                            }
                            break;
                        case 2:
                            switch (manupulator1) {
                                case 1:
                                    button3.setText("");
                                    button4.setText("");
                                    break;
                                case 3:
                                    button1.setText("");
                                    button4.setText("");
                                    break;
                                case 4:
                                    button3.setText("");
                                    button1.setText("");
                                    break;
                            }
                            break;
                        case 3:
                            switch (manupulator1) {
                                case 1:
                                    button2.setText("");
                                    button4.setText("");
                                    break;
                                case 2: button1.setText("");
                                    button4.setText("");
                                    break;
                                case 4:
                                    button2.setText("");
                                    button1.setText("");
                                    break;
                            }
                            break;
                        case 4:
                            switch (manupulator1) {
                                case 1:
                                    button3.setText("");
                                    button2.setText("");
                                    break;
                                case 2:
                                    button1.setText("");
                                    button3.setText("");
                                    break;
                                case 3:
                                    button2.setText("");
                                    button1.setText("");
                                    break;
                            }
                            break;
                    }

                }else{

                    AlertDialog.Builder builder=new AlertDialog.Builder(KbcSetup.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(KbcSetup.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your FIFTY-FIFTY Life Line Once.");
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

        audience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(audiencenum==0) {
                    audiencenum=1;
                    lifelineLayout.getChildAt(1).setBackgroundResource(R.drawable.audience_used);

                    if (button1.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator = 1;
                    } else if (button2.getText().toString().equals(list.get(position).getCorrectAnswer())) {
                        manupulator = 2;
                    } else if (button2.getText().toString().equals(list.get(position).getCorrectAnswer())) {
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


                    AlertDialog.Builder builder = new AlertDialog.Builder(KbcSetup.this, R.style.AlertDialogTheme);

                    final View view1 = LayoutInflater.from(KbcSetup.this).inflate(R.layout.audience_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer));
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

                    AlertDialog.Builder builder=new AlertDialog.Builder(KbcSetup.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(KbcSetup.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your AUDIENCE POLL Life Line Once.");
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



        flip.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                if(flipnum==0){
                    flipnum=1;
                    lifelineLayout.getChildAt(2).setBackgroundResource(R.drawable.flip_used);
                    enableOption(true);
                    position++;
                    LLTrueManupulator();


                    count = 0;
                    playAnim(kbc_que, 0, list.get(position).getQuestionTextView());
                }else{

                    AlertDialog.Builder builder=new AlertDialog.Builder(KbcSetup.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(KbcSetup.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your SWAP Life Line Once.");
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





        advice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(expertnum==0){

                    expertnum=1;
                    lifelineLayout.getChildAt(3).setBackgroundResource(R.drawable.expert_used);
                    String answerByExpert=list.get(position).getCorrectAnswer();


                    AlertDialog.Builder builder=new AlertDialog.Builder(KbcSetup.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(KbcSetup.this).inflate(R.layout.expertadvicelayout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Earn 10 Paper Notes By Entering Your Friends Referral Code!");
                    ((TextView) view1.findViewById(R.id.textMessage)).setText("I Think It's : \n'"+answerByExpert+"'");
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


                }else{
                    AlertDialog.Builder builder=new AlertDialog.Builder(KbcSetup.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(KbcSetup.this).inflate(R.layout.sorry_layout_for_helplines,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText("Sorry Lucy! You Have Used Your EXPERT ADVICE Life Line Once.");
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

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kbcCountMus.stop();
                Intent i = new Intent(KbcSetup.this,KbcPlay.class);
                startActivity(i);
            }
        });

    }

    private void playAnim(final View view, final int value,final String data){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationStart(Animator animator) {
                if(value==0 && count<4){
                    String option="";
                    if(count==0){
                        option=list.get(position).getOption1();
                        linearLayoutButton.getChildAt(0).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269BFF")));
                    }else if(count==1){
                        option=list.get(position).getOption2();
                        linearLayoutButton.getChildAt(1).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269BFF")));
                    }else if(count==2){
                        option=list.get(position).getOption3();
                        linearLayoutButton.getChildAt(2).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269BFF")));
                    }else if(count==3){
                        option=list.get(position).getOption4();
                        linearLayoutButton.getChildAt(3).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#269BFF")));
                    }
                    playAnim(linearLayoutButton.getChildAt(count),0,option);
                    count++;
                }
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (value == 0) {
                    try {
                        ((TextView) view).setText(data);
                        //scoreBoard.setText("Question "+(position+1)+"/"+list.size());
                    } catch (ClassCastException ex) {
                        ((Button) view).setText(data);
                    }
                    view.setTag(data);
                    playAnim(view, 1,data);
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



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(Button selectedOption){
        enableOption(false);
        kbcCountMus.stop();
        if(selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){
            nextQue();
            countDownTimer.cancel();
            countDownTimer.start();
            kbcCountMus.start();
            if(score==0){
                linearLayoutPrice.getChildAt(12-score).setBackgroundResource(R.drawable.border);
                score++;
            }
            else {
                linearLayoutPrice.getChildAt(12 - score).setBackgroundResource(R.drawable.border);
                linearLayoutPrice.getChildAt(12 - score + 1).setBackgroundResource(R.drawable.border_removed);
                score++;
            }


        }else {
            countDownTimer.cancel();;

            String string = "Wrong Answer";
            View res = linearLayoutPrice.getChildAt(score+1);
            Intent intent = new Intent(KbcSetup.this,KbcScoreActivity.class);
            intent.putExtra("string",string);
            intent.putExtra("score", res.toString());
            startActivity(intent);

        }
    }

    private void enableOption(boolean enable){
        for (int i=0;i<4;i++) {
            linearLayoutButton.getChildAt(i).setEnabled(enable);
            if (enable) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    linearLayoutButton.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E4E4E4")));
                }
            }
        }
    }

    public void easyFunction(){

        list.add(new questionHolder("What is the capital of Russia?", "Moscow", "Dhaka", "New York", "Paris", "Moscow"));
        list.add(new questionHolder("Which of these fractions is of the greatest value?", "1/4", "1/3", "1/2", "1/1", "1/1"));
        list.add(new questionHolder("When is the International Yoga Day Celebrated", "June 21", "March 21", "April 22", "May 31", "June 21"));
        list.add(new questionHolder("What is the name of the protective outer layer of trees called?", "Phloem", "Cambium Layer", "Heartwood", "Bark", "Bark"));
        list.add(new questionHolder("Which liquid do plants need for photosynthesis?", "Liquid Methane", "Water", "Liquid Nitrogen", "Liquid Oxygen", "Water"));
        list.add(new questionHolder("Which is the fastest animal on two legs?", "Kiwi", "Ostrich", "Emu", "Penguin", "Ostrich"));
        list.add(new questionHolder("Which mountain is the highest in the world", "Kanchenjunga", "K2", "Mount Everest", "Nanda Devi", "Mount Everest"));
        list.add(new questionHolder("According to a proverb,what is said to be 'The Mother Of Invention'?", "Society", "Problem", "Science", "Necessity", "Necessity"));
        list.add(new questionHolder("What do the five rings of the Olympics represent?", "Five Games", "Five Languages", "Five Continents", "Five Oceans", "Five Continents"));
        list.add(new questionHolder("How many watts equal a megawatt?", "One Hundred", "One Thousand", "Ten Thousand", "One Lakh", "One Lakh"));

    }

    public void LLFalseManupulator(){        //Setting all the lifelines enable:False when an option is selected.
        audience.setClickable(false);
        audience.setEnabled(false);
        audience.setAlpha(0.8f);

        advice.setClickable(false);
        advice.setEnabled(false);
        advice.setAlpha(0.8f);

        half.setClickable(false);
        half.setEnabled(false);
        half.setAlpha(0.8f);

        flip.setClickable(false);
        flip.setEnabled(false);
        flip.setAlpha(0.8f);

    }

    public void LLTrueManupulator(){       //Setting all the lifelines enable:False when next button is pressed.
        audience.setClickable(true);
        audience.setEnabled(true);
        audience.setAlpha(1.0f);

        advice.setClickable(true);
        advice.setEnabled(true);
        advice.setAlpha(1.0f);

        half.setClickable(true);
        half.setEnabled(true);
        half.setAlpha(1.0f);

        flip.setClickable(true);
        flip.setEnabled(true);
        flip.setAlpha(1.0f);
    }

    public void nextQue(){
        count = 0;
        position++;
        enableOption(true);
        if(position==list.size()) {
            finish();
            return;
        }

        playAnim(kbc_que, 0, list.get(position).getQuestionTextView());
    }
}