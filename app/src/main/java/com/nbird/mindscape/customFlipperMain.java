package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
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
import java.util.List;
import java.util.UUID;

public class customFlipperMain extends AppCompatActivity {
    ViewFlipper flipper;
    Button prev_Button, next_Button;
    TextInputEditText question1,question2,question3,question4,question5;
    TextInputEditText optionA1,optionA2,optionA3,optionA4,optionA5;
    TextInputEditText optionB1,optionB2,optionB3,optionB4,optionB5;
    TextInputEditText optionC1,optionC2,optionC3,optionC4,optionC5;
    TextInputEditText optionD1,optionD2,optionD3,optionD4,optionD5;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    RadioGroup radioGroup1,radioGroup2,radioGroup3,radioGroup4,radioGroup5;
    Button choosepic1,choosepic2,choosepic3,choosepic4,choosepic5;
    ImageView questionImage1,questionImage2,questionImage3,questionImage4,questionImage5;
    String randomuid,imageurl;
    int pInt=1,NInt=1;
    TextView questionNumber;
    int dum=1;
    int selectedId1=-1,selectedId2=-1,selectedId3=-1,selectedId4=-1,selectedId5=-1;
    List<customQuizMainHolder> listItem;
    String quizName,userName,description,proPic;
    int timeDuration,experLL,swapLL,audienceLL,fiftyfiftyLL,quizCode,privacy,rate,report;
    customQuizMainHolder mainHolder;
    int cat=1;
    StorageReference ref;
    Uri imageUri;
    FirebaseStorage storage;
    StorageReference storageReference;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    String imgU1="",imgU2="",imgU3="",imgU4="",imgU5="";
    int imgButtonInt;
    Button cancel1,cancel2,cancel3,cancel4,cancel5;
    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_flipper_main);

        question1=(TextInputEditText) findViewById(R.id.question1);
        question2=(TextInputEditText) findViewById(R.id.question2);
        question3=(TextInputEditText) findViewById(R.id.question3);
        question4=(TextInputEditText) findViewById(R.id.question4);
        question5=(TextInputEditText) findViewById(R.id.question5);

        optionA1=(TextInputEditText) findViewById(R.id.optionA1);
        optionA2=(TextInputEditText) findViewById(R.id.optionA2);
        optionA3=(TextInputEditText) findViewById(R.id.optionA3);
        optionA4=(TextInputEditText) findViewById(R.id.optionA4);
        optionA5=(TextInputEditText) findViewById(R.id.optionA5);

        optionB1=(TextInputEditText) findViewById(R.id.optionB1);
        optionB2=(TextInputEditText) findViewById(R.id.optionB2);
        optionB3=(TextInputEditText) findViewById(R.id.optionB3);
        optionB4=(TextInputEditText) findViewById(R.id.optionB4);
        optionB5=(TextInputEditText) findViewById(R.id.optionB5);

        optionC1=(TextInputEditText) findViewById(R.id.optionC1);
        optionC2=(TextInputEditText) findViewById(R.id.optionC2);
        optionC3=(TextInputEditText) findViewById(R.id.optionC3);
        optionC4=(TextInputEditText) findViewById(R.id.optionC4);
        optionC5=(TextInputEditText) findViewById(R.id.optionC5);

        optionD1=(TextInputEditText) findViewById(R.id.optionD1);
        optionD2=(TextInputEditText) findViewById(R.id.optionD2);
        optionD3=(TextInputEditText) findViewById(R.id.optionD3);
        optionD4=(TextInputEditText) findViewById(R.id.optionD4);
        optionD5=(TextInputEditText) findViewById(R.id.optionD5);

        radioGroup1=(RadioGroup) findViewById(R.id.radioGroup1);
        radioGroup2=(RadioGroup) findViewById(R.id.radioGroup2);
        radioGroup3=(RadioGroup) findViewById(R.id.radioGroup3);
        radioGroup4=(RadioGroup) findViewById(R.id.radioGroup4);
        radioGroup5=(RadioGroup) findViewById(R.id.radioGroup5);

        choosepic1=(Button) findViewById(R.id.choosepic1);
        choosepic2=(Button) findViewById(R.id.choosepic2);
        choosepic3=(Button) findViewById(R.id.choosepic3);
        choosepic4=(Button) findViewById(R.id.choosepic4);
        choosepic5=(Button) findViewById(R.id.choosepic5);


        questionImage1=(ImageView) findViewById(R.id.questionImage1);
        questionImage2=(ImageView) findViewById(R.id.questionImage2);
        questionImage3=(ImageView) findViewById(R.id.questionImage3);
        questionImage4=(ImageView) findViewById(R.id.questionImage4);
        questionImage5=(ImageView) findViewById(R.id.questionImage5);

        cancel1=(Button) findViewById(R.id.cancel1);
        cancel2=(Button) findViewById(R.id.cancel2);
        cancel3=(Button) findViewById(R.id.cancel3);
        cancel4=(Button) findViewById(R.id.cancel4);
        cancel5=(Button) findViewById(R.id.cancel5);

        cancel1.setAlpha(0);
        cancel2.setAlpha(0);
        cancel3.setAlpha(0);
        cancel4.setAlpha(0);
        cancel5.setAlpha(0);
        cancel1.setEnabled(false);
        cancel2.setEnabled(false);
        cancel3.setEnabled(false);
        cancel4.setEnabled(false);
        cancel5.setEnabled(false);

        questionNumber=(TextView) findViewById(R.id.questionNumber);

        questionNumber.setText("Question : 1/5");

        listItem=new ArrayList<>();



        flipper = findViewById(R.id.view_flipper);
        prev_Button = findViewById(R.id.prev_button);
        next_Button = findViewById(R.id.next_button);




        quizName=getIntent().getStringExtra("quizName");
        userName=getIntent().getStringExtra("userName");
        timeDuration=getIntent().getIntExtra("timeDuration",5);
        experLL=getIntent().getIntExtra("experLL",1);
        swapLL=getIntent().getIntExtra("swapLL",1);
        audienceLL=getIntent().getIntExtra("audienceLL",1);
        fiftyfiftyLL=getIntent().getIntExtra("fiftyfiftyLL",1);
        quizCode=getIntent().getIntExtra("quizCode",1);
        privacy=getIntent().getIntExtra("privacy",0);
        rate=getIntent().getIntExtra("rate",0);
        report=getIntent().getIntExtra("report",0);
        description=getIntent().getStringExtra("description");
        proPic=getIntent().getStringExtra("proPic");
        cat=getIntent().getIntExtra("category",0);



        choosepic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                imgButtonInt=1;
                selectImage(customFlipperMain.this,questionImage1);

            }
        });

        choosepic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                imgButtonInt=2;
                selectImage(customFlipperMain.this,questionImage2);

            }
        });

        choosepic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                imgButtonInt=3;
                selectImage(customFlipperMain.this,questionImage3);

            }
        });

        choosepic4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                imgButtonInt=4;
                selectImage(customFlipperMain.this,questionImage4);

            }
        });

        choosepic5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                imgButtonInt=5;
                selectImage(customFlipperMain.this,questionImage5);

            }
        });




        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                questionImage1.setImageBitmap(null);
                imgU1="";
                cancel1.setAlpha(0);
                cancel1.setEnabled(false);
            }
        });

        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                questionImage2.setImageBitmap(null);
                imgU2="";
                cancel2.setAlpha(0);
                cancel2.setEnabled(false);
            }
        });

        cancel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                questionImage3.setImageBitmap(null);
                imgU3="";
                cancel3.setAlpha(0);
                cancel3.setEnabled(false);
            }
        });

        cancel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                questionImage4.setImageBitmap(null);
                imgU4="";
                cancel4.setAlpha(0);
                cancel4.setEnabled(false);
            }
        });

        cancel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                questionImage5.setImageBitmap(null);
                imgU5="";
                cancel5.setAlpha(0);
                cancel5.setEnabled(false);
            }
        });




      //  RadioButton radioButton = (RadioButton)radioGroup1.findViewById(selectedId);

     //   privacyTextView.setText(" Privacy : "+radioButton.getText()+" ");


        next_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        buttonMusic();
                        switch (pInt){
                            case 1:
                                if(!errorFinder(question1,optionA1,optionB1,optionC1,optionD1)){
                                    return;
                                }
                                selectedId1 = radioGroup1.getCheckedRadioButtonId();
                                if(selectedId1==-1){
                                    Toast.makeText(customFlipperMain.this, "Please Select Your Correct Option!!!", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                String ansString;

                                RadioButton radioButton = (RadioButton)radioGroup1.findViewById(selectedId1);


                                if(radioButton.getText().toString().equals("A.")){
                                    ansString=optionA1.getText().toString();
                                }else if(radioButton.getText().toString().equals("B.")){
                                    ansString=optionB1.getText().toString();
                                }else if(radioButton.getText().toString().equals("C.")){
                                    ansString=optionC1.getText().toString();
                                }else{
                                    ansString=optionD1.getText().toString();
                                }

                                customQuizMainHolder holder=new customQuizMainHolder(question1.getText().toString(),optionA1.getText().toString(),optionB1.getText().toString(),optionC1.getText().toString(),optionD1.getText().toString(),ansString,pInt,imgU1);
                                listItem.add(pInt-1,holder);

                                break;
                            case 2:
                                if(!errorFinder(question2,optionA2,optionB2,optionC2,optionD2)){
                                    return;
                                }
                                selectedId2 = radioGroup2.getCheckedRadioButtonId();
                                if(selectedId2==-1){
                                    Toast.makeText(customFlipperMain.this, "Please Select The Correct Option!!!", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                String ansString2;

                                RadioButton radioButton2 = (RadioButton)radioGroup2.findViewById(selectedId2);


                                if(radioButton2.getText().toString().equals("A.")){
                                    ansString2=optionA2.getText().toString();
                                }else if(radioButton2.getText().toString().equals("B.")){
                                    ansString2=optionB2.getText().toString();
                                }else if(radioButton2.getText().toString().equals("C.")){
                                    ansString2=optionC2.getText().toString();
                                }else{
                                    ansString2=optionD2.getText().toString();
                                }

                                customQuizMainHolder holder2=new customQuizMainHolder(question2.getText().toString(),optionA2.getText().toString(),optionB2.getText().toString(),optionC2.getText().toString(),optionD2.getText().toString(),ansString2,pInt,imgU2);
                                listItem.add(pInt-1,holder2);


                                break;
                            case 3:
                                if(!errorFinder(question3,optionA3,optionB3,optionC3,optionD3)){
                                    return;
                                }
                                selectedId3 = radioGroup3.getCheckedRadioButtonId();
                                if(selectedId3==-1){
                                    Toast.makeText(customFlipperMain.this, "Please Select The Correct Option!!!", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                String ansString3;

                                RadioButton radioButton3 = (RadioButton)radioGroup3.findViewById(selectedId3);


                                if(radioButton3.getText().toString().equals("A.")){
                                    ansString3=optionA3.getText().toString();
                                }else if(radioButton3.getText().toString().equals("B.")){
                                    ansString3=optionB3.getText().toString();
                                }else if(radioButton3.getText().toString().equals("C.")){
                                    ansString3=optionC3.getText().toString();
                                }else{
                                    ansString3=optionD3.getText().toString();
                                }

                                customQuizMainHolder holder3=new customQuizMainHolder(question3.getText().toString(),optionA3.getText().toString(),optionB3.getText().toString(),optionC3.getText().toString(),optionD3.getText().toString(),ansString3,pInt,imgU3);
                                listItem.add(pInt-1,holder3);




                                break;
                            case 4:
                                if(!errorFinder(question4,optionA4,optionB4,optionC4,optionD4)){
                                    return;
                                }
                                selectedId4 = radioGroup4.getCheckedRadioButtonId();
                                if(selectedId4==-1){
                                    Toast.makeText(customFlipperMain.this, "Please Select The Correct Option!!!", Toast.LENGTH_LONG).show();
                                    return;
                                }


                                String ansString4;

                                RadioButton radioButton4 = (RadioButton)radioGroup4.findViewById(selectedId4);


                                if(radioButton4.getText().toString().equals("A.")){
                                    ansString4=optionA4.getText().toString();
                                }else if(radioButton4.getText().toString().equals("B.")){
                                    ansString4=optionB4.getText().toString();
                                }else if(radioButton4.getText().toString().equals("C.")){
                                    ansString4=optionC4.getText().toString();
                                }else{
                                    ansString4=optionD4.getText().toString();
                                }

                                customQuizMainHolder holder4=new customQuizMainHolder(question4.getText().toString(),optionA4.getText().toString(),optionB4.getText().toString(),optionC4.getText().toString(),optionD4.getText().toString(),ansString4,pInt,imgU4);
                                listItem.add(pInt-1,holder4);




                                break;
                            case 5:
                                if(!errorFinder(question5,optionA5,optionB5,optionC5,optionD5)){
                                    return;
                                }
                                selectedId5 = radioGroup5.getCheckedRadioButtonId();
                                if(selectedId5==-1){
                                    Toast.makeText(customFlipperMain.this, "Please Select The Correct Option!!!", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                String ansString5;

                                RadioButton radioButton5 = (RadioButton)radioGroup5.findViewById(selectedId5);

                                if(radioButton5.getText().toString().equals("A.")){
                                    ansString5=optionA5.getText().toString();
                                }else if(radioButton5.getText().toString().equals("B.")){
                                    ansString5=optionB5.getText().toString();
                                }else if(radioButton5.getText().toString().equals("C.")){
                                    ansString5=optionC5.getText().toString();
                                }else{
                                    ansString5=optionD5.getText().toString();
                                }
                                customQuizMainHolder holder5=new customQuizMainHolder(question5.getText().toString(),optionA5.getText().toString(),optionB5.getText().toString(),optionC5.getText().toString(),optionD5.getText().toString(),ansString5,pInt,imgU5);
                                listItem.add(pInt-1,holder5);

                                break;
                        }

                        if(pInt==5){

                            AlertDialog.Builder builder=new AlertDialog.Builder(customFlipperMain.this,R.style.AlertDialogTheme);

                            final View view1= LayoutInflater.from(customFlipperMain.this).inflate(R.layout.quit_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                            builder.setView(view1);
                            builder.setCancelable(false);
                            ((TextView) view1.findViewById(R.id.textTitle)).setText("Are You Sure You Want to Submit?");
                            Button noButton=((Button) view1.findViewById(R.id.buttonYes));
                            Button yesButton=(Button) view1.findViewById(R.id.buttonNo);
                            LottieAnimationView imageIcon=(LottieAnimationView) view1.findViewById(R.id.imageIcon);

                            imageIcon.setAnimation(R.raw.sanim);
                            imageIcon.playAnimation();
                            imageIcon.loop(true);

                            yesButton.setText("Yes,Submit");
                            noButton.setText("No,Not Now");

                            final AlertDialog alertDialog=builder.create();
                            if(alertDialog.getWindow()!=null){
                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            }
                            alertDialog.show();

                            yesButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    fireBaseSetter();
                                    codeProviderAlertDialog();
                                }
                            });

                            noButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                  alertDialog.dismiss();
                                }
                            });




                        }



                        if(pInt<5){
                            pInt++;
                            questionNumber.setText("Question : "+pInt+"/5");
                        }


                        if(pInt==5){

                            next_Button.setEnabled(true);
                            prev_Button.setEnabled(true);
                            prev_Button.setVisibility(View.VISIBLE);
                            next_Button.setWidth(100);
                            next_Button.setBackgroundResource(R.drawable.donefire);
                            if(dum==1){
                                dum=0;
                                flipper.setInAnimation(customFlipperMain.this,
                                        R.anim.slide_in_right);
                                flipper.setOutAnimation(customFlipperMain.this,
                                        R.anim.slide_out_left);

                                // It shows next item.
                                flipper.showNext();
                            }

                        }else if(pInt==1){
                            next_Button.setEnabled(true);
                            prev_Button.setEnabled(false);
                            prev_Button.setVisibility(View.INVISIBLE);
                            next_Button.setScaleX(1);
                            next_Button.setScaleY(1);
                            next_Button.setBackgroundResource(R.drawable.nextfire);

                            flipper.setInAnimation(customFlipperMain.this,
                                    R.anim.slide_in_right);
                            flipper.setOutAnimation(customFlipperMain.this,
                                    R.anim.slide_out_left);

                            // It shows next item.
                            flipper.showNext();

                        }else if(pInt==2||pInt==3||pInt==4){
                            next_Button.setEnabled(true);
                            prev_Button.setEnabled(true);
                            prev_Button.setVisibility(View.VISIBLE);
                            next_Button.setScaleX(1);
                            next_Button.setBackgroundResource(R.drawable.nextfire);
                            next_Button.setScaleY(1);

                            flipper.setInAnimation(customFlipperMain.this,
                                    R.anim.slide_in_right);
                            flipper.setOutAnimation(customFlipperMain.this,
                                    R.anim.slide_out_left);

                            // It shows next item.
                            flipper.showNext();
                        }
                        // It is used to set the in and out
                        // animation of View flipper.
                    }
                });

        prev_Button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        buttonMusic();
                        if(pInt>1){
                            pInt--;
                            questionNumber.setText("Question : "+pInt+"/5");
                        }

                        dum=1;


                        if(pInt==5){
                            next_Button.setEnabled(true);
                            prev_Button.setEnabled(true);
                            prev_Button.setVisibility(View.VISIBLE);
                            next_Button.setWidth(100);
                            next_Button.setBackgroundResource(R.drawable.donefire);
                            flipper.setInAnimation(customFlipperMain.this,
                                    R.anim.slide_in_left);
                            flipper.setOutAnimation(customFlipperMain.this,
                                    R.anim.slide_out_right);

                            // It shows previous item.
                            flipper.showPrevious();

                        }else if(pInt==1){
                            next_Button.setEnabled(true);
                            prev_Button.setEnabled(false);
                            prev_Button.setVisibility(View.INVISIBLE);
                            next_Button.setScaleX(1);
                            next_Button.setScaleY(1);
                            next_Button.setBackgroundResource(R.drawable.nextfire);

                            flipper.setInAnimation(customFlipperMain.this,
                                    R.anim.slide_in_left);
                            flipper.setOutAnimation(customFlipperMain.this,
                                    R.anim.slide_out_right);


                            flipper.showPrevious();

                        }else if(pInt==2||pInt==3||pInt==4){
                            next_Button.setEnabled(true);
                            prev_Button.setEnabled(true);
                            prev_Button.setVisibility(View.VISIBLE);
                            next_Button.setScaleX(1);
                            next_Button.setBackgroundResource(R.drawable.nextfire);
                            next_Button.setScaleY(1);

                            flipper.setInAnimation(customFlipperMain.this,
                                    R.anim.slide_in_left);
                            flipper.setOutAnimation(customFlipperMain.this,
                                    R.anim.slide_out_right);


                            flipper.showPrevious();
                        }




                    }
                });
    }


    public void codeProviderAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(customFlipperMain.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(customFlipperMain.this).inflate(R.layout.code_share_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
    //    ((TextView) view1.findViewById(R.id.textTitle)).setText("Are You Sure You Want to Submit?");
        TextView nameText=(TextView) view1.findViewById(R.id.nameText);
        TextView codeText=(TextView) view1.findViewById(R.id.codeText);

        Button playButton=((Button) view1.findViewById(R.id.buttonYes));
        Button buttonHome=(Button) view1.findViewById(R.id.buttonHome);
        Button shareButton=(Button) view1.findViewById(R.id.shareButton);

        nameText.setText("Username : "+userName);
        codeText.setText("Password : "+quizCode);


        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                Intent intent=new Intent(customFlipperMain.this,customQuizMainQuizActivity.class);

                    intent.putExtra("quizName",quizName);
                    intent.putExtra("privacy",privacy);
                    intent.putExtra("time",timeDuration);
                    intent.putExtra("audienceLL",audienceLL);
                    intent.putExtra("fiftyfiftyLL",fiftyfiftyLL);
                    intent.putExtra("expertLL",experLL);

                    intent.putExtra("key",key);
                    intent.putExtra("category",cat);

                    startActivity(intent);
                    finish();



              // intent.putExtra("list")

            }
        });

        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                Intent intent=new Intent(customFlipperMain.this,mainMenuActivity.class);
                alertDialog.dismiss();
                startActivity(intent);
                finish();
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonMusic();
                Intent shareIntent=new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plane");
                String shareBody=userName+" Has Created A Quiz .Try Once\n"+"Username : "+userName+"\nQuiz Code : "+quizCode+".";
                String sharesub="MindScape";
                shareIntent.putExtra(Intent.EXTRA_SUBJECT,sharesub);
                shareIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(shareIntent,"Custom Quiz"));
            }
        });
    }

    private void selectImage(Context context, ImageView questionImage1) {
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



                            switch (imgButtonInt){
                                case 1:
                                    cancel1.setEnabled(true);
                                    cancel1.setAlpha(1);
                                    questionImage1.setImageBitmap(selectedImage);break;
                                case 2:
                                    cancel2.setEnabled(true);
                                    cancel2.setAlpha(1);
                                    questionImage2.setImageBitmap(selectedImage);break;
                                case 3:
                                    cancel3.setEnabled(true);
                                    cancel3.setAlpha(1);
                                    questionImage3.setImageBitmap(selectedImage);break;
                                case 4:
                                    cancel4.setEnabled(true);
                                    cancel4.setAlpha(1);
                                    questionImage4.setImageBitmap(selectedImage);break;
                                case 5:
                                    cancel5.setEnabled(true);
                                    cancel5.setAlpha(1);
                                    questionImage5.setImageBitmap(selectedImage);break;
                            }







                            uploadImage();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(customFlipperMain.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(customFlipperMain.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
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

            ref = storageReference.child("customQuiz/" + mAuth.getCurrentUser().getUid()+"/quizPics"+"/"+randomuid);



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
                                    Toast.makeText(customFlipperMain.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                    try{

                                        StorageReference urlref = storageRef.child("customQuiz/" + mAuth.getCurrentUser().getUid()+"/quizPics"+"/"+randomuid);
                                        urlref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                        {
                                            @Override
                                            public void onSuccess(final Uri downloadUrl)
                                            {

                                                Glide.with(getBaseContext())
                                                        .load(downloadUrl.toString())
                                                        .preload(20, 10);

                                                switch (imgButtonInt){
                                                    case 1:
                                                        imgU1=downloadUrl.toString();break;
                                                    case 2:
                                                        imgU2=downloadUrl.toString();break;
                                                    case 3:
                                                        imgU3=downloadUrl.toString();break;
                                                    case 4:
                                                        imgU4=downloadUrl.toString();break;
                                                    case 5:
                                                        imgU5=downloadUrl.toString();break;
                                                }

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
                                    .makeText(customFlipperMain.this,
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



    public void fireBaseSetter(){



                if(privacy==0){
                    key = database.getReference().child("CustomQuiz").child(String.valueOf(privacy)).child("QuestionBank").push().getKey();
                    customQuizPropertiesSetter s1=new customQuizPropertiesSetter(quizName,timeDuration,experLL,swapLL,audienceLL,fiftyfiftyLL,quizCode,privacy,userName,rate,report,description,proPic,key,"",0);

                    myRef.child("CustomQuiz").child(String.valueOf(privacy)).child("QuizProperties").child(key).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            for (int i=0;i<5;i++){
                                mainHolder=listItem.get(i);
                                myRef.child("CustomQuiz").child(String.valueOf(privacy)).child("questions").child(key).child(String.valueOf(i+1)).setValue(mainHolder).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(customFlipperMain.this, "Uploaded!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }


                        }
                    });
                }else {
                     key = database.getReference().child("CustomQuiz").child(String.valueOf(privacy)).child("QuestionBank").push().getKey();
                     String mykey=myRef.child("CustomQuiz").child(String.valueOf(privacy)).child("category").child(String.valueOf(cat)).child("questionsPack").push().getKey();
                    customQuizPropertiesSetter s1=new customQuizPropertiesSetter(quizName,timeDuration,experLL,swapLL,audienceLL,fiftyfiftyLL,quizCode,privacy,userName,rate,report,description,proPic,key,mykey,0);

                    myRef.child("CustomQuiz").child(String.valueOf(privacy)).child("category").child(String.valueOf(cat)).child("questionsPack").child(mykey).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            for (int i=0;i<5;i++){
                                mainHolder=listItem.get(i);
                                myRef.child("CustomQuiz").child(String.valueOf(privacy)).child("QuestionBank").child(key).child(String.valueOf(i+1)).setValue(mainHolder).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(customFlipperMain.this, "Uploaded!!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }


                        }
                    });
                }


    }


    public boolean errorFinder(TextView question,TextView optionA,TextView optionB,TextView optionC,TextView optionD){
        if(question.getText().toString().isEmpty()){
            question.setError("Fields Cannot Be Empty");
            return false;
        }else if(optionA.getText().toString().isEmpty()){
            optionA.setError("Fields Cannot Be Empty");
            return false;
        }else if(optionB.getText().toString().isEmpty()){
            optionB.setError("Fields Cannot Be Empty");
            return false;
        }else if(optionC.getText().toString().isEmpty()){
            optionC.setError("Fields Cannot Be Empty");
            return false;
        }else if(optionD.getText().toString().isEmpty()){
            optionD.setError("Fields Cannot Be Empty");
            return false;
        }
        return true;
    }

    public void buttonMusic(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(customFlipperMain.this, R.raw.finalbuttonmusic);
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