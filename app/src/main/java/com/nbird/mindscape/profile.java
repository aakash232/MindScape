package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
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
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
import com.google.android.material.textfield.TextInputEditText;
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

    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10;
    CardView cardView11,cardView12,cardView13,cardView14,cardView15,cardView16,cardView17,cardView18,cardView19,cardView20;
    CardView cardView21,cardView22,cardView23,cardView24,cardView25,cardView26;

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8,linearLayout9,linearLayout10;
    LinearLayout linearLayout11,linearLayout12,linearLayout13,linearLayout14,linearLayout15,linearLayout16,linearLayout17,linearLayout18,linearLayout19,linearLayout20;
    LinearLayout linearLayout21,linearLayout22,linearLayout23,linearLayout24,linearLayout25,linearLayout26;

    String urlAva1="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava1.png?alt=media&token=39fc4486-0021-443f-974d-daa3fc17bec2";
    String urlAva2="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava2.png?alt=media&token=e19cd95b-6012-4fe7-94bb-003c1b9f92c0";
    String urlAva3="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava3.png?alt=media&token=d0a90643-c6f3-446e-a386-8de3dc052765";
    String urlAva4="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava4.png?alt=media&token=ff5f2d52-eb31-4d55-af59-2de2ecda6a51";
    String urlAva5="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava5.png?alt=media&token=6a354ce9-d45a-4bfa-8c26-6d1db00ceb84";
    String urlAva6="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava6.png?alt=media&token=445f480e-c646-45b1-b55c-8ff86469e97f";
    String urlAva7="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava7.png?alt=media&token=41f409b7-6c19-4827-9723-fbd8856215f2";
    String urlAva8="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava8.png?alt=media&token=4a3682cc-3242-4582-b3c3-fff6591b7af5";
    String urlAva9="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava9.png?alt=media&token=f9048bb1-fd25-48fe-95ed-65f70f0607b3";
    String urlAva10="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava10.png?alt=media&token=881f92b8-973a-462a-8a3c-8c6f70cd888b";
    String urlAva11="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava11.png?alt=media&token=26a5711e-2a8c-449e-bc3a-4a2d98811cc1";
    String urlAva12="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava12.png?alt=media&token=e09e3572-c79d-4bb3-9756-29f18967f0db";
    String urlAva13="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava13.png?alt=media&token=82a5dece-0e9a-4e2c-824a-1c4b441073bc";
    String urlAva14="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava14.png?alt=media&token=bb2e7b65-2709-432b-add4-cac32bf3cd6d";
    String urlAva15="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava15.png?alt=media&token=fea2cbe9-2359-4577-a113-562e54341031";
    String urlAva16="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava16.png?alt=media&token=f31d2368-92f4-4e82-a384-aaabf11c6dc3";
    String urlAva17="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava17.png?alt=media&token=4d7eacfd-d75e-432d-bf6d-bc95acdb3ebf";
    String urlAva18="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava18.png?alt=media&token=2e1493eb-905a-47cc-b86c-0540d5a7561c";
    String urlAva19="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava19.png?alt=media&token=faa84af3-2195-46b7-a44f-d6b272698443";
    String urlAva20="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava20.png?alt=media&token=b7aee3f8-a774-4ceb-9be9-4bd4dba39b04";
    String urlAva21="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava21.png?alt=media&token=d32fccfd-57c3-4f07-99c6-0da9cc921d5e";
    String urlAva22="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava22.png?alt=media&token=37793d6e-55bf-4be6-9c3e-b49f69cb25f8";
    String urlAva23="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava23.png?alt=media&token=c4fe300d-b6cb-4102-b956-082c89ed6bcb";
    String urlAva24="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava24.png?alt=media&token=35520f68-1000-4c84-b8ed-56b2bf64b14f";
    String urlAva25="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava25.png?alt=media&token=c2eec723-3f40-4dc6-a294-caa4db20cf66";
    String urlAva26="https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/avatarIcons%2Fava26.png?alt=media&token=2cd731f2-e525-49b3-ac81-dc939cbd82fe";

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
    androidx.appcompat.widget.Toolbar toolbar;
    ImageView pencilImage,nav_image,nav_image123;
    String nameString,imageurl123;

    @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();


        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




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
        pencilImage=findViewById(R.id.pencilImage);

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

        pencilImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardMusic();
                editName();
            }
        });

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  selectImage(profile.this);
                cardMusic();
                ProfileAndAVA();
            }
        });

        }


        public void ProfileAndAVA(){
            AlertDialog.Builder builder=new AlertDialog.Builder(profile.this,R.style.AlertDialogTheme);

            final View view1= LayoutInflater.from(profile.this).inflate(R.layout.profileimageselector,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            builder.setCancelable(true);
            ((Button) view1.findViewById(R.id.buttonYes)).setText("OK");
            ((TextView) view1.findViewById(R.id.textView6)).setText("Select An Avatar Or Upload Your Pic ");
            nav_image123=((ImageView) view1.findViewById(R.id.propic));

            cardView1= (CardView) view1.findViewById(R.id.cardview1);
            cardView2= (CardView) view1.findViewById(R.id.cardview2);
            cardView3= (CardView) view1.findViewById(R.id.cardview3);
            cardView4= (CardView) view1.findViewById(R.id.cardview4);
            cardView5= (CardView) view1.findViewById(R.id.cardview5);
            cardView6= (CardView) view1.findViewById(R.id.cardview6);
            cardView7= (CardView) view1.findViewById(R.id.cardview7);
            cardView8= (CardView) view1.findViewById(R.id.cardview8);
            cardView9= (CardView) view1.findViewById(R.id.cardview9);
            cardView10= (CardView) view1.findViewById(R.id.cardview10);
            cardView11= (CardView) view1.findViewById(R.id.cardview11);
            cardView12= (CardView) view1.findViewById(R.id.cardview12);
            cardView13= (CardView) view1.findViewById(R.id.cardview13);
            cardView14= (CardView) view1.findViewById(R.id.cardview14);
            cardView15= (CardView) view1.findViewById(R.id.cardview15);
            cardView16= (CardView) view1.findViewById(R.id.cardview16);
            cardView17= (CardView) view1.findViewById(R.id.cardview17);
            cardView18= (CardView) view1.findViewById(R.id.cardview18);
            cardView19= (CardView) view1.findViewById(R.id.cardview19);
            cardView20= (CardView) view1.findViewById(R.id.cardview20);
            cardView21= (CardView) view1.findViewById(R.id.cardview21);
            cardView22= (CardView) view1.findViewById(R.id.cardview22);
            cardView23= (CardView) view1.findViewById(R.id.cardview23);
            cardView24= (CardView) view1.findViewById(R.id.cardview24);
            cardView25= (CardView) view1.findViewById(R.id.cardview25);
            cardView26= (CardView) view1.findViewById(R.id.cardview26);


            linearLayout1=(LinearLayout) view1.findViewById(R.id.linearLayout1);
            linearLayout2=(LinearLayout) view1.findViewById(R.id.linearLayout2);
            linearLayout3=(LinearLayout) view1.findViewById(R.id.linearLayout3);
            linearLayout4=(LinearLayout) view1.findViewById(R.id.linearLayout4);
            linearLayout5=(LinearLayout) view1.findViewById(R.id.linearLayout5);
            linearLayout6=(LinearLayout) view1.findViewById(R.id.linearLayout6);
            linearLayout7=(LinearLayout) view1.findViewById(R.id.linearLayout7);
            linearLayout8=(LinearLayout) view1.findViewById(R.id.linearLayout8);
            linearLayout9=(LinearLayout) view1.findViewById(R.id.linearLayout9);
            linearLayout10=(LinearLayout) view1.findViewById(R.id.linearLayout10);
            linearLayout11=(LinearLayout) view1.findViewById(R.id.linearLayout11);
            linearLayout12=(LinearLayout) view1.findViewById(R.id.linearLayout12);
            linearLayout13=(LinearLayout) view1.findViewById(R.id.linearLayout13);
            linearLayout14=(LinearLayout) view1.findViewById(R.id.linearLayout14);
            linearLayout15=(LinearLayout) view1.findViewById(R.id.linearLayout15);
            linearLayout16=(LinearLayout) view1.findViewById(R.id.linearLayout16);
            linearLayout17=(LinearLayout) view1.findViewById(R.id.linearLayout17);
            linearLayout18=(LinearLayout) view1.findViewById(R.id.linearLayout18);
            linearLayout19=(LinearLayout) view1.findViewById(R.id.linearLayout19);
            linearLayout20=(LinearLayout) view1.findViewById(R.id.linearLayout20);
            linearLayout21=(LinearLayout) view1.findViewById(R.id.linearLayout21);
            linearLayout22=(LinearLayout) view1.findViewById(R.id.linearLayout22);
            linearLayout23=(LinearLayout) view1.findViewById(R.id.linearLayout23);
            linearLayout24=(LinearLayout) view1.findViewById(R.id.linearLayout24);
            linearLayout25=(LinearLayout) view1.findViewById(R.id.linearLayout25);
            linearLayout26=(LinearLayout) view1.findViewById(R.id.linearLayout26);




            avaMunupulator();

            nav_image123.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cardMusic();
                    selectImage(profile.this);
                }
            });


            final AlertDialog alertDialog=builder.create();
            if(alertDialog.getWindow()!=null){
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            alertDialog.show();

            view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cardMusic();
                    alertDialog.dismiss();
                }
            });
        }

    public void settingBlack(){
        linearLayout1.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout2.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout3.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout4.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout5.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout6.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout7.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout8.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout9.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout10.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout11.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout12.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout13.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout14.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout15.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout16.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout17.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout18.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout19.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout20.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout21.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout22.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout23.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout24.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout25.setBackgroundResource(R.drawable.blackwithwhitestroke);
        linearLayout26.setBackgroundResource(R.drawable.blackwithwhitestroke);
    }

    public void imageUploader(String urlAva,String Str){
        myRef.child("leaderBoard").child(Str).child(mAuth.getCurrentUser().getUid()).setValue(urlAva).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void glide(String url){
        Glide.with(getBaseContext()).load(url).apply(RequestOptions
                .bitmapTransform(new RoundedCorners(14)))
                .into(profilepic);
    }

    public void avaMunupulator(){

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout1.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva1);


                        }else{

                        }
                    }
                });
                imageUploader(urlAva1,"1vs1");
                imageUploader(urlAva1,"singlePlayer");

            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout2.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva2);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva2,"1vs1");
                imageUploader(urlAva2,"singlePlayer");
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout3.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva3).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva3);
                        }else{

                        }
                    }
                });imageUploader(urlAva3,"1vs1");
                imageUploader(urlAva3,"singlePlayer");
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout4.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva4).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva4);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva4,"1vs1");
                imageUploader(urlAva4,"singlePlayer");
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout5.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva5).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva5);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva5,"1vs1");
                imageUploader(urlAva5,"singlePlayer");
            }
        });


        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout6.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva6).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva6);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva6,"1vs1");
                imageUploader(urlAva6,"singlePlayer");
            }
        });


        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout7.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva7).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva7);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva7,"1vs1");
                imageUploader(urlAva7,"singlePlayer");
            }
        });


        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout8.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva8).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva8);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva8,"1vs1");
                imageUploader(urlAva8,"singlePlayer");
            }
        });


        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout9.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva9).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva9);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva9,"1vs1");
                imageUploader(urlAva9,"singlePlayer");
            }
        });


        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout10.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva10).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva10);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva10,"1vs1");
                imageUploader(urlAva10,"singlePlayer");
            }
        });


        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout11.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva11).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva11);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva11,"1vs1");
                imageUploader(urlAva11,"singlePlayer");
            }
        });


        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout12.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva12).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva12);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva12,"1vs1");
                imageUploader(urlAva12,"singlePlayer");
            }
        });

        cardView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout13.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva13).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva13);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva13,"1vs1");
                imageUploader(urlAva13,"singlePlayer");
            }
        });


        cardView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout14.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva14).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva14);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva14,"1vs1");
                imageUploader(urlAva14,"singlePlayer");
            }
        });


        cardView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout15.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva15).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva15);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva15,"1vs1");
                imageUploader(urlAva15,"singlePlayer");
            }
        });

        cardView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout16.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva16).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva16);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva16,"1vs1");
                imageUploader(urlAva16,"singlePlayer");
            }
        });

        cardView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout17.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva17).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva17);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva17,"1vs1");
                imageUploader(urlAva17,"singlePlayer");
            }
        });


        cardView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout18.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva18).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva18);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva18,"1vs1");
                imageUploader(urlAva18,"singlePlayer");
            }
        });


        cardView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout19.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva19).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva19);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva19,"1vs1");
                imageUploader(urlAva19,"singlePlayer");
            }
        });


        cardView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout20.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva20).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva20);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva20,"1vs1");
                imageUploader(urlAva20,"singlePlayer");
            }
        });

        cardView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout21.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva21).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva21);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva21,"1vs1");
                imageUploader(urlAva21,"singlePlayer");
            }
        });


        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout22.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva22).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva22);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva22,"1vs1");
                imageUploader(urlAva22,"singlePlayer");
            }
        });


        cardView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout23.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva23).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva23);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva23,"1vs1");
                imageUploader(urlAva23,"singlePlayer");
            }
        });


        cardView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout24.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva24).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva24);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva24,"1vs1");
                imageUploader(urlAva24,"singlePlayer");
            }
        });

        cardView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout25.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva25).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva25);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva25,"1vs1");
                imageUploader(urlAva25,"singlePlayer");
            }
        });

        cardView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout26.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva26).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            glide(urlAva26);
                        }else{

                        }
                    }
                });
                imageUploader(urlAva26,"1vs1");
                imageUploader(urlAva26,"singlePlayer");
            }
        });


    }

    public void proPicFunction(){


        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageurl123 = (String) snapshot.getValue();
                SharedPreferences propic = getApplicationContext().getSharedPreferences("Propic", 0);
                SharedPreferences.Editor editor = propic.edit();
                editor.putString("key", imageurl123);
                editor.apply();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

        public void proPicAndName(){
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                     nameString=snapshot.getValue(String.class);
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

        public void editName(){
            final MediaPlayer musicNav;
            musicNav = MediaPlayer.create(profile.this, R.raw.lifelineused);
            musicNav.start();
            musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicNav.reset();
                    musicNav.release();
                }
            });
            AlertDialog.Builder builder=new AlertDialog.Builder(profile.this,R.style.AlertDialogTheme);

            final View view1= LayoutInflater.from(profile.this).inflate(R.layout.rename,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
            builder.setView(view1);
            builder.setCancelable(true);

            final TextInputEditText userNameEditText=(TextInputEditText) view1.findViewById(R.id.userNameEditText);
            Button doneButton=(Button) view1.findViewById(R.id.joinButton1);


            final AlertDialog alertDialog=builder.create();
            if(alertDialog.getWindow()!=null){
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            alertDialog.show();

            doneButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    usernameUploader(userNameEditText,alertDialog);
                }
            });




        }

        public void usernameUploader(final TextInputEditText userNameEditText, final AlertDialog alertDialog){

        if(!username(userNameEditText)){
            return;
        }

        final String str=userNameEditText.getText().toString();

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").setValue(str).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                 alertDialog.dismiss();
                Toast.makeText(profile.this, "Username Changed From "+nameString+" to "+str, Toast.LENGTH_LONG).show();
                name.setText(str);
            }
        });

            myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("username").setValue(str).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("leaderBoard").child("singlePlayer").child(mAuth.getCurrentUser().getUid()).child("username").setValue(str).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });



        }

    private boolean username(TextInputEditText usernameEditText){
        String name1=usernameEditText.getText().toString();
        //String noWhihteSpaces=("\\A\\w{4,20}\\z");

        if(name1.isEmpty()){
            usernameEditText.setError("Field cannot be empty");
            return false;
        }else if(name1.length()>30){
            usernameEditText.setError("Username should be less than 30 characters");
            return false;
        }
        else
            usernameEditText.setError(null);
        return true;
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
            pieChart.setCenterText("Total Seconds");
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
                                nav_image123.setImageBitmap(selectedImage);


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


                                                imageUploader(imageurl,"1vs1");
                                                imageUploader(imageurl,"singlePlayer");


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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    public void cardMusic(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(profile.this, R.raw.navclick);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
    }
}
