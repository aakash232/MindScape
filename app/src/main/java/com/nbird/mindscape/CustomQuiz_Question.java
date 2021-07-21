package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class CustomQuiz_Question extends AppCompatActivity {
    RecyclerView recyclerView;
    int numberOfQuestions;
    int timeDuration;
    String name;
     LinearLayout dotLayout;
     TextView[] mDots;
     ViewPager slideViewPager;
     CustomQuizAdapter CustomQuizAdapter;
     Button Submitbutton;


    int expertAdvise,fiftyfifty,swap,AudiencePoll;
     int currentPage;
    int pointer=0;
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz__question);

        numberOfQuestions= getIntent().getIntExtra("numberOfQuestionsKey",3);
        name=getIntent().getStringExtra("nameKey");
        timeDuration=getIntent().getIntExtra("timeDurationKey", 0);
        expertAdvise=getIntent().getIntExtra("expertAdviseKey", 0);
        fiftyfifty=getIntent().getIntExtra("fiftyfiftyKey", 0);
        swap=getIntent().getIntExtra("swapKey", 0);
        AudiencePoll=getIntent().getIntExtra("AudiencePollKey", 0);

        Toast.makeText(this,"Number of question: "+numberOfQuestions, Toast.LENGTH_SHORT).show();


        Submitbutton=(Button) findViewById(R.id.button);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(recyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        final customQuizRecyclerAdapter categoryAdapter = new customQuizRecyclerAdapter(this,Submitbutton,numberOfQuestions);
        recyclerView.setAdapter(categoryAdapter);



    }





}