package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class profile extends AppCompatActivity {


    LineChart lineChart,lineChart2;
    LineData lineData,lineData2;
    Uri imageUri;
    List<Entry> entryList = new ArrayList<>();
    List<Entry> entryList2 = new ArrayList<>();
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    quizHistoryData historyData,historyData2;
    int x=1;
    TextView correctvswrong,totalTime,numberOfGamesPlayed,accuracy,percentile;
    TextView correctvswrong2,totalTime2,numberOfGamesPlayed2,accuracy2,percentile2;
    leaderBoardHolder listTextView,listTextView2;
    List<leaderBoardHolder> list,list2;
    List<barGroupHolder> listBarGroup;

    BarChart barChart,barChart2,barChart5;
    BarDataSet barDataSet1, barDataSet2,barDataSet3,barDataSet4;
    ArrayList correctBar,wrongBar,barEntries,correctBar2,wrongBar2;
    String[] days = new String[]{"Sunday", "Monday", "Tuesday","Wednesday", "Thursday", "Friday", "Saturday"};

    int lu=0,mu=0,lu2=0,mu2=0;
    CountDownTimer countDownTimer,countDownTimer1,countDownTimer2;
    PieChart pieChart;
    ArrayList<PieEntry> visitors1;

    com.github.mikephil.charting.charts.RadarChart RadarChart;
    float percentileFloat;
    BarChart barChart1;
    int v=0,v2=0;
    int sumationOfScore=0;
    TextView level1,level2;
    ImageView levelicon1,levelicon2;
    Dialog loadingDialog;
    TextView name;
    ImageView profilepic;
    String imageurl;
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    String mailid123,randomuid;
    StorageReference ref;
    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();




        mailid123 = mailreminder.getString("123", "abc@gmail.com");

            correctvswrong=(TextView) findViewById(R.id.correctvswrong1);
            totalTime=(TextView) findViewById(R.id.totalTime);
            numberOfGamesPlayed=(TextView) findViewById(R.id.numberOfGamesPlayed);
            accuracy=(TextView) findViewById(R.id.accuracy);
            percentile=(TextView) findViewById(R.id.percentile);

        correctvswrong2=(TextView) findViewById(R.id.correctvswrong2);
        totalTime2=(TextView) findViewById(R.id.totalTime2);
        numberOfGamesPlayed2=(TextView) findViewById(R.id.numberOfGamesPlayed2);
        accuracy2=(TextView) findViewById(R.id.accuracy2);
        percentile2=(TextView) findViewById(R.id.percentile2);

        level1=(TextView) findViewById(R.id.level1);
        level2=(TextView) findViewById(R.id.level2);
        levelicon1=(ImageView) findViewById(R.id.levelicon1);
        levelicon2=(ImageView) findViewById(R.id.levelicon2);

        lineChart = findViewById(R.id.lineChart);
            barChart = findViewById(R.id.idBarChart);
        barChart2 = findViewById(R.id.idBarChart2);
            pieChart=(PieChart) findViewById(R.id.pieChart);
        barChart1=(BarChart) findViewById(R.id.barChart);
        barChart5=(BarChart) findViewById(R.id.barChart2);

        name=(TextView) findViewById(R.id.name);
        profilepic=(ImageView) findViewById(R.id.profilepic);

        lineChart2=findViewById(R.id.lineChart2);

        loadingDialog=new Dialog(this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(false);
        loadingDialog.show();

        correctBar = new ArrayList<>();
        wrongBar =new ArrayList<>();
        correctBar2 = new ArrayList<>();
        wrongBar2 =new ArrayList<>();
        visitors1=new ArrayList<>();

        proPicAndName();
        batchManu();
        linearChart();
            textViewDataReceive();
        correctArrFun();
        pieCaller();
        barChart();
        textViewDataReceiveOnline();
        linearChartForOnline();
        correctArrFun2();
        barChart2();
        // Back Button
        ImageView back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(profile.this, mainMenuActivity.class);
                startActivity(back);
            }
        });

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(profile.this);
            }
        });

        }

        public void proPicAndName(){
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String nameString=snapshot.getValue(String.class);
                    name.setText(nameString);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String nav_img_url=snapshot.getValue(String.class);
                    Glide.with(getBaseContext()).load(nav_img_url).apply(RequestOptions
                            .bitmapTransform(new RoundedCorners(18)))
                            .into(profilepic);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        public void batchManu(){
            myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    try {
                        int sumationOfScore1=snapshot.getValue(Integer.class);
                        levelManupulation(level1,levelicon1,sumationOfScore1);

                    }catch (Exception e){
                        level1.setText(" Level : 1 ");
                        levelicon1.setBackgroundResource(R.drawable.blackiron);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    try {
                        int sumationOfScore2=snapshot.getValue(Integer.class);
                        levelManupulation(level2,levelicon2,sumationOfScore2);
                    }catch (Exception e){
                        level2.setText(" Level : 1 ");
                        levelicon2.setBackgroundResource(R.drawable.blackiron);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

    public void levelManupulation(TextView level, ImageView levelicon, int sumationOfScoreFinal){
        if(sumationOfScoreFinal <100000){
            if(sumationOfScoreFinal <50000){
               level.setText(" Level : 1 ");
            }else{
                level.setText(" Level : 2 ");
            }
        }else{
            int holder;
            holder= sumationOfScoreFinal /50000;
            level.setText(" Level "+holder+" ");
        }
        if(sumationOfScoreFinal <50000){

            levelicon.setBackgroundResource(R.drawable.blackiron);
        }else if(sumationOfScoreFinal <200000){


                   //   Aakash Changes Are to be done here
            levelicon.setBackgroundResource(R.drawable.bronze);
        }else if(sumationOfScoreFinal <800000){


            levelicon.setBackgroundResource(R.drawable.silver);
        }else if(sumationOfScoreFinal <1800000){


            levelicon.setBackgroundResource(R.drawable.gold);
        }else if(sumationOfScoreFinal <3000000){


            levelicon.setBackgroundResource(R.drawable.platinum);
        }else if(sumationOfScoreFinal <4000000){


            levelicon.setBackgroundResource(R.drawable.diamond);
        }else if(sumationOfScoreFinal <8000000){


            levelicon.setBackgroundResource(R.drawable.amethyst);
        }else if(sumationOfScoreFinal <12000000){


            levelicon.setBackgroundResource(R.drawable.master);
        }else{


            levelicon.setBackgroundResource(R.drawable.king);
        }
    }

    public void barChart2(){

        final ArrayList<BarEntry> visitors2=new ArrayList<>();



        final List<Integer> q2 = new ArrayList<>();
        final ArrayList<String> xLabel = new ArrayList<>();
        final ArrayList<String> xAxisLabel2 = new ArrayList<>();
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("LineChartGradient").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    q2.add(dataSnapshot1.getValue(Integer.class));
                    float minc=q2.get(v2)/60;
                    visitors2.add(new BarEntry(v2,minc));

                    v2++;
                    xAxisLabel2.add(dataSnapshot1.getKey());
                }




                BarDataSet barDataSet=new BarDataSet(visitors2,"Time In Minutes");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                barDataSet.setValueTextSize(8f);

                BarData barData=new BarData(barDataSet);

                barChart5.setFitBars(true);
                barChart5.setData(barData);
                barChart5.getDescription().setText("Time Vs Day");
                barChart5.getDescription().setTextSize(3f);
                barChart5.animateY(2000);






                XAxis xAxis = barChart5.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                xAxis.setTextSize(5f);

                ValueFormatter formatter = new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return xAxisLabel2.get((int) value);
                    }
                };
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }


        public void barChart(){

            final ArrayList<BarEntry> visitors=new ArrayList<>();



            final List<Integer> q = new ArrayList<>();
            final ArrayList<String> xLabel = new ArrayList<>();
            final ArrayList<String> xAxisLabel = new ArrayList<>();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("LineChartGradient").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                            q.add(dataSnapshot1.getValue(Integer.class));
                            float minc=q.get(v)/60;
                            visitors.add(new BarEntry(v,minc));

                                    v++;
                            xAxisLabel.add(dataSnapshot1.getKey());
                        }




                        BarDataSet barDataSet=new BarDataSet(visitors,"Time In Minutes");
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                        barDataSet.setValueTextSize(8f);

                        BarData barData=new BarData(barDataSet);

                        barChart1.setFitBars(true);
                        barChart1.setData(barData);
                        barChart1.getDescription().setText("Time Vs Day");
                        barChart1.getDescription().setTextSize(3f);
                        barChart1.animateY(2000);






                    XAxis xAxis = barChart1.getXAxis();
                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
                    xAxis.setTextSize(5f);

                    ValueFormatter formatter = new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            return xAxisLabel.get((int) value);
                        }
                    };
                    xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                    xAxis.setValueFormatter(formatter);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        }


        public void pieCaller(){
           pieDataManu(1,"Historical Monuments");
            pieDataManu(2,"Flora And Fauna");
            pieDataManu(3,"Geography");
            pieDataManu(4,"Astronomy And Space");
            pieDataManu(5,"Sports");
            pieDataManu(6,"Some Superlatives");
            pieDataManu(7,"Country,Capitals And Currencies");
            pieDataManu(8,"Famous Personalities");
            pieDataManu(9,"Science");
            pieDataManu(10,"Important Dates And Events");
            pieDataManu(11,"Religion And Mythology");
            pieDataManu(12,"History");
            pieDataManu(13,"Film And Entertainment");
            pieDataManu(14,"Inventions And Discoveries");
            pieDataManu(15,"First In Different Fields");
            pieDataManu(16,"Festival,Art And Culture");
            pieDataManu(17,"Polity And Constitution");
            pieDataManu(18,"Literature");
            pieDataManu(19,"Health And Disease");
            pieDataManu(20,"Miscellaneous");


            countDownTimer1=new CountDownTimer(1000*60*60,1000) {
                @Override
                public void onTick(long l) {
                    if(mu>=20){
                        pieChart();
                        countDownTimer1.cancel();
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();


        }

        public void pieDataManu(int i, final String category){
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("pieChart").child(String.valueOf(i)).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        int i=snapshot.getValue(Integer.class);
                        i=i/1000;


                        visitors1.add(new PieEntry(i,category));

                    }catch (Exception e){

                    }
                   mu++;
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }

        public void pieChart(){


            PieDataSet pieDataSet=new PieDataSet(visitors1,"Category Wise Total Seconds Played");
            pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
            pieDataSet.setValueTextColor(Color.BLACK);
            pieDataSet.setValueTextSize(10f);
            pieDataSet.setValueLineColor(R.color.black);
            pieDataSet.setFormSize(3);



            PieData pieData=new PieData(pieDataSet);

            pieChart.setData(pieData);
            pieChart.setEntryLabelTextSize(5);
            pieChart.setEntryLabelColor(R.color.black);
            pieChart.getDescription().setEnabled(false);
            pieChart.setCenterText("Subjects");
            pieChart.animate();
        }

        public void mainbarGroupChartManu(String dayName, final float i){

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("ProfileData").child("barGroupData").child(dayName).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        barGroupHolder lop1=new barGroupHolder();
                        lop1=snapshot.getValue(barGroupHolder.class);
                        int c=lop1.getCorrect();
                        int w=lop1.getWrong();
                        correctBar.add(new BarEntry(i, c));
                        wrongBar.add(new BarEntry(i, w));
                        lu++;


                    }catch (Exception e){
                        correctBar.add(new BarEntry(i, 0));
                        wrongBar.add(new BarEntry(i, 0));
                        lu++;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        public void correctArrFun(){
            mainbarGroupChartManu("Sunday",1);
            mainbarGroupChartManu("Monday",2);
            mainbarGroupChartManu("Tuesday",3);
            mainbarGroupChartManu("Wednesday",4);
            mainbarGroupChartManu("Thursday",5);
            mainbarGroupChartManu("Friday",6);
            mainbarGroupChartManu("Saturday",7);

            countDownTimer=new CountDownTimer(1000*60*60,1000) {
                @Override
                public void onTick(long l) {
                    if(lu==7){
                        barGroupChart();
                        countDownTimer.cancel();
                    }
                }

                @Override
                public void onFinish() {

                }
            }.start();


        }


    public void mainbarGroupChartManuOnlineMode(String dayName, final float i){

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeProfileData").child("barGroupData").child(dayName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    barGroupHolder lop1=new barGroupHolder();
                    lop1=snapshot.getValue(barGroupHolder.class);
                    int c=lop1.getCorrect();
                    int w=lop1.getWrong();
                    correctBar2.add(new BarEntry(i, c));
                    wrongBar2.add(new BarEntry(i, w));
                    lu2++;


                }catch (Exception e){
                    correctBar2.add(new BarEntry(i, 0));
                    wrongBar2.add(new BarEntry(i, 0));
                    lu2++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void correctArrFun2(){
        mainbarGroupChartManuOnlineMode("Sunday",1);
        mainbarGroupChartManuOnlineMode("Monday",2);
        mainbarGroupChartManuOnlineMode("Tuesday",3);
        mainbarGroupChartManuOnlineMode("Wednesday",4);
        mainbarGroupChartManuOnlineMode("Thursday",5);
        mainbarGroupChartManuOnlineMode("Friday",6);
        mainbarGroupChartManuOnlineMode("Saturday",7);

        countDownTimer2=new CountDownTimer(1000*60*60,1000) {
            @Override
            public void onTick(long l) {
                if(lu2==7){
                    barGroupChartOnline();
                    countDownTimer2.cancel();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();


    }

    public void barGroupChartOnline(){

        barDataSet3 = new BarDataSet(correctBar2, "Correct");
        barDataSet3.setColor(getApplicationContext().getResources().getColor(R.color.black));
        barDataSet4 = new BarDataSet(wrongBar2, "Wrong");
        barDataSet4.setColor(Color.BLUE);


        BarData data = new BarData(barDataSet3, barDataSet4);
        barChart2.setData(data);
        barChart2.getDescription().setEnabled(false);
        XAxis xAxis = barChart2.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(5f);
        barChart2.setDragEnabled(true);
        barChart2.setVisibleXRangeMaximum(7);
        float barSpace = 0.1f;
        float groupSpace = 0.5f;
        data.setBarWidth(0.15f);
        data.setValueTextSize(5f);
        barChart2.getXAxis().setAxisMinimum(0);
        barChart2.animate();
        barChart2.groupBars(0, groupSpace, barSpace);
        barChart2.invalidate();
    }


        public void barGroupChart(){

            barDataSet1 = new BarDataSet(correctBar, "Correct");
            barDataSet1.setColor(getApplicationContext().getResources().getColor(R.color.black));
            barDataSet2 = new BarDataSet(wrongBar, "Wrong");
            barDataSet2.setColor(Color.BLUE);


            BarData data = new BarData(barDataSet1, barDataSet2);
            barChart.setData(data);
            barChart.getDescription().setEnabled(false);
            XAxis xAxis = barChart.getXAxis();
            xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
            xAxis.setCenterAxisLabels(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setGranularity(1);
            xAxis.setGranularityEnabled(true);
            xAxis.setTextSize(5f);
            barChart.setDragEnabled(true);
            barChart.setVisibleXRangeMaximum(7);
            float barSpace = 0.1f;
            float groupSpace = 0.5f;
            data.setBarWidth(0.15f);
            data.setValueTextSize(5f);
            barChart.getXAxis().setAxisMinimum(0);
            barChart.animate();
            barChart.groupBars(0, groupSpace, barSpace);
            barChart.invalidate();
        }

        public void textViewDataReceiveOnline(){
            list2=new ArrayList<>();
            listTextView2= new leaderBoardHolder();
            myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        listTextView2=snapshot.getValue(leaderBoardHolder.class);



                        int correct=listTextView2.getCorrect();
                        int wrong=listTextView2.getWrong();

                        correctvswrong2.setText("Correct/Wrong : "+correct+"/"+wrong);

                        long p=listTextView2.getTotalTime();


                        if(p<60){
                            totalTime2.setText("Total Time : "+p+" sec ");
                        }else{
                            long minutes=p/60;
                            long sec=p%60;
                            totalTime2.setText("Total Time : "+minutes+" min "+sec+" sec ");
                        }


                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfOnlineModePlayed").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    int i=snapshot.getValue(Integer.class);
                                    numberOfGamesPlayed2.setText("Total Quiz Played : "+i);
                                }catch (Exception e){
                                    numberOfGamesPlayed2.setText("Total Quiz Played : 0");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        float accu=((correct*100)/(correct+wrong));
                        accuracy2.setText("Accuracy : "+accu+"%");

                        myRef.child("leaderBoard").child("1vs1").orderByChild("score").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    list2.add(dataSnapshot1.getValue(leaderBoardHolder.class));
                                }
                                int highestScoreOfOppo=list2.get(0).getScore();

                                int myHighest=listTextView2.getScore();

                                float percentileFloat2=(myHighest*100)/highestScoreOfOppo;
                                percentile2.setText("Percentile : "+percentileFloat2+"%");

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }catch (Exception e){
                        correctvswrong2.setText("Correct/Wrong : 0/0");
                        totalTime2.setText("Total Time : 0 Sec");
                        numberOfGamesPlayed2.setText("Total Quiz Played : 0");
                        accuracy2.setText("Accuracy : 0%");
                        percentile2.setText("Percentile : 0%");
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        public void textViewDataReceive(){
            list=new ArrayList<>();
            listTextView= new leaderBoardHolder();
            myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try {
                        listTextView=snapshot.getValue(leaderBoardHolder.class);



                        int correct=listTextView.getCorrect();
                        int wrong=listTextView.getWrong();

                        correctvswrong.setText("Correct/Wrong : "+correct+"/"+wrong);

                        long p=listTextView.getTotalTime();


                        if(p<60){
                            totalTime.setText("Total Time : "+p+" sec ");
                        }else{
                            long minutes=p/60;
                            long sec=p%60;
                            totalTime.setText("Total Time : "+minutes+" min "+sec+" sec ");
                        }

                      
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfSingleModePlayed").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    int i=snapshot.getValue(Integer.class);
                                    numberOfGamesPlayed.setText("Total Quiz Played : "+i);
                                }catch (Exception e){
                                    numberOfGamesPlayed.setText("Total Quiz Played : 0");
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        float accu=((correct*100)/(correct+wrong));
                        accuracy.setText("Accuracy : "+accu+"%");

                        myRef.child("leaderBoard").child("singlePlayer").orderByChild("score").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                                    list.add(dataSnapshot1.getValue(leaderBoardHolder.class));
                                }
                                int highestScoreOfOppo=list.get(0).getScore();

                                int myHighest=listTextView.getScore();

                                percentileFloat=(myHighest*100)/highestScoreOfOppo;
                                percentile.setText("Percentile : "+percentileFloat+"%");

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }catch (Exception e){
                        correctvswrong.setText("Correct/Wrong : 0/0");
                        totalTime.setText("Total Time : 0 Sec");
                        numberOfGamesPlayed.setText("Total Quiz Played : 0");
                        accuracy.setText("Accuracy : 0%");
                        percentile.setText("Percentile : 0%");

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    public void linearChartForOnline(){
        historyData2=new quizHistoryData();
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("OnlineModeQuizHistory").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                    historyData2 = dataSnapshot1.getValue(quizHistoryData.class);

                    entryList2.add(new Entry(x, historyData2.getScore()));
                    x++;

                }



                LineDataSet lineDataSet = new LineDataSet(entryList2,"Quiz Score");
                lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                lineDataSet.setFillAlpha(110);
                lineData2 = new LineData(lineDataSet);
                lineChart2.getDescription().setEnabled(false);
                lineChart2.setData(lineData2);
                lineChart2.setVisibleXRangeMaximum(10);
                lineChart2.moveViewToX(lineChart2.getXChartMax());
                lineChart2.invalidate();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        public void linearChart(){
            historyData=new quizHistoryData();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("SinglePlayerQuizHistory").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {

                        historyData = dataSnapshot1.getValue(quizHistoryData.class);

                        entryList.add(new Entry(x, historyData.getScore()));
                        x++;

                    }

                    loadingDialog.dismiss();


                    LineDataSet lineDataSet = new LineDataSet(entryList,"Quiz Score");
                    lineDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                    lineDataSet.setFillAlpha(110);
                    lineData = new LineData(lineDataSet);
                    lineChart.getDescription().setEnabled(false);
                    lineChart.setData(lineData);
                    lineChart.setVisibleXRangeMaximum(10);
                    lineChart.moveViewToX(lineChart.getXChartMax());
                    lineChart.invalidate();



                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


    private void selectImage(Context context) {
        final CharSequence[] options = {"Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

               /* if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } */if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
              /*  case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        nav_image.setImageBitmap(selectedImage);
                        uploadImage();
                    }
                    break;*/
                case 1:
                    if (resultCode == RESULT_OK) {
                        try {
                            imageUri = data.getData();
                            final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                            final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                                profilepic.setImageBitmap(selectedImage);


                            uploadImage();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(profile.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(profile.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }

    private void uploadImage()
    {



        if (imageUri != null) {




            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            storage = FirebaseStorage.getInstance();
            storageReference = storage.getReference();


            randomuid= UUID.randomUUID().toString();

            ref = storageReference.child("images/" + mailid123+"/"+randomuid);



            // adding listeners on upload
            // or failure of image
            ref.putFile(imageUri)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {

                                    // Image uploaded successfully
                                    // Dismiss dialog
                                    progressDialog.dismiss();
                                    Toast.makeText(profile.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                    try{

                                        StorageReference urlref = storageRef.child("images/" + mailid123+"/"+randomuid);
                                        urlref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                        {
                                            @Override
                                            public void onSuccess(Uri downloadUrl)
                                            {
                                                imageurl=downloadUrl.toString();

                                                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(imageurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if(task.isSuccessful()){

                                                        }else{

                                                        }
                                                    }
                                                });
                                            }
                                        });

                                    }catch (Exception e){

                                    }
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {

                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast
                                    .makeText(profile.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {

                                // Progress Listener for loading
                                // percentage on the dialog box
                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount());
                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int)progress + "%");
                                }
                            });
        }
    }


}
