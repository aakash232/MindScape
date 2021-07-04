package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.Random;

public class customQuizFirstProperties extends AppCompatActivity {
    TextInputEditText name,timeDuration,description;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myRef=database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    int expertAdvise=0;

    int fiftyfifty=0;
    int AudiencePoll=0;
    int NOS,quizType=-1;
    RadioGroup radioGroup;
    String NAME;
    Button start;
    int privacyInt=0;   //0->Private   ,,, 1->Public
    CheckBox expertAdviseCB;
    CheckBox swapCB;
    CheckBox fiftyfiftyCB;
    CheckBox AudiencePollCB;
    String nameUser;
    String proPic;
    int r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_quiz_first_properties);
        name = (TextInputEditText) findViewById(R.id.name);
        description = (TextInputEditText) findViewById(R.id.dis);

        NAME= Objects.requireNonNull(name.getText()).toString();

        timeDuration = (TextInputEditText) findViewById(R.id.timeDuration);
        radioGroup=(RadioGroup) findViewById(R.id.radioGroup);

         expertAdviseCB = (CheckBox) findViewById(R.id.expertAdvise);

         fiftyfiftyCB = (CheckBox) findViewById(R.id.fiftyfifty);
         AudiencePollCB = (CheckBox) findViewById(R.id.AudiencePoll);


        //Start Button
        start = (Button) findViewById(R.id.start);

        StartButtonFun();
    }

    public void fireBaseDataSetter(){
        if(!numberValidator()){
            return;
        }

        Random rand=new Random();
        r=rand.nextInt(10000)+1;
        quizType = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton=(RadioButton) radioGroup.findViewById(quizType);

        if(radioButton.getText().toString().equals("Personnel")){
            privacyInt=0;
        }else{
            privacyInt=1;
        }
        if(expertAdviseCB.isChecked()) expertAdvise=1;
        if(fiftyfiftyCB.isChecked()) fiftyfifty=1;
        if(AudiencePollCB.isChecked()) AudiencePoll=1;

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 nameUser=snapshot.getValue(String.class);

                myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        proPic=snapshot.getValue(String.class);

                        if(privacyInt==1){
                            AlertDialog.Builder builder=new AlertDialog.Builder(customQuizFirstProperties.this,R.style.AlertDialogTheme);

                            final View view1= LayoutInflater.from(customQuizFirstProperties.this).inflate(R.layout.category_selection_asset,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                            builder.setView(view1);
                            builder.setCancelable(false);

                            CardView card1=(CardView) view1.findViewById(R.id.card1);
                            CardView card2=(CardView) view1.findViewById(R.id.card2);
                            CardView card3=(CardView) view1.findViewById(R.id.card3);
                            CardView card4=(CardView) view1.findViewById(R.id.card4);
                            CardView card5=(CardView) view1.findViewById(R.id.card5);
                            CardView card6=(CardView) view1.findViewById(R.id.card6);
                            CardView card7=(CardView) view1.findViewById(R.id.card7);
                            CardView card8=(CardView) view1.findViewById(R.id.card8);
                            CardView card9=(CardView) view1.findViewById(R.id.card9);
                            CardView card10=(CardView) view1.findViewById(R.id.card10);
                            CardView card11=(CardView) view1.findViewById(R.id.card11);
                            CardView card12=(CardView) view1.findViewById(R.id.card12);
                            CardView card13=(CardView) view1.findViewById(R.id.card13);
                            CardView card14=(CardView) view1.findViewById(R.id.card14);
                            CardView card15=(CardView) view1.findViewById(R.id.card15);
                            CardView card16=(CardView) view1.findViewById(R.id.card16);
                            CardView card17=(CardView) view1.findViewById(R.id.card17);
                            CardView card18=(CardView) view1.findViewById(R.id.card18);
                            CardView card19=(CardView) view1.findViewById(R.id.card19);

                            final AlertDialog alertDialog=builder.create();
                            if(alertDialog.getWindow()!=null){
                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            }
                            alertDialog.show();



                            card1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(1);
                                    alertDialog.dismiss();
                                }
                            });

                            card2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(2);alertDialog.dismiss();
                                }
                            });

                            card3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(3);alertDialog.dismiss();
                                }
                            });

                            card4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(4);alertDialog.dismiss();
                                }
                            });

                            card5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(5);alertDialog.dismiss();
                                }
                            });

                            card6.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(6);alertDialog.dismiss();
                                }
                            });


                            card7.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(7);alertDialog.dismiss();
                                }
                            });


                            card8.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(8);alertDialog.dismiss();
                                }
                            });


                            card9.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(9);alertDialog.dismiss();
                                }
                            });


                            card10.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(10);alertDialog.dismiss();
                                }
                            });


                            card11.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(11);alertDialog.dismiss();
                                }
                            });


                            card12.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(12);alertDialog.dismiss();
                                }
                            });


                            card13.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(13);alertDialog.dismiss();
                                }
                            });


                            card14.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(14);alertDialog.dismiss();
                                }
                            });


                            card15.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(15);alertDialog.dismiss();
                                }
                            });


                            card16.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(16);alertDialog.dismiss();
                                }
                            });


                            card17.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(17);alertDialog.dismiss();
                                }
                            });


                            card18.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(18);alertDialog.dismiss();
                                }
                            });

                            card19.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    buttonMusic();
                                    intentFunction(19);alertDialog.dismiss();
                                }
                            });


                        }else{
                            intentFunction(0);
                        }






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

           public void intentFunction(int category){
               int n;
        try {
            n=Integer.parseInt(timeDuration.getText().toString());
        }catch (Exception e){
            n=2;
        }

               Intent begin  =  new Intent(customQuizFirstProperties.this,customFlipperMain.class);
               begin.putExtra("quizName",name.getText().toString());
               begin.putExtra("timeDuration",n);
               begin.putExtra("description",description.getText().toString());
               begin.putExtra("experLL",expertAdvise);
              // begin.putExtra("swapLL",swap);
               begin.putExtra("audienceLL",AudiencePoll);
               begin.putExtra("fiftyfiftyLL",fiftyfifty);
               begin.putExtra("quizCode",r);
               begin.putExtra("privacy",privacyInt);
               begin.putExtra("userName",nameUser);
               begin.putExtra("rate",0);
               begin.putExtra("report",0);
               begin.putExtra("category",category);
               begin.putExtra("proPic",proPic);

               //begin.putExtra("quizType", quizType);
               startActivity(begin);
               overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
           }

    public void StartButtonFun(){


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                fireBaseDataSetter();

            }
        });

    }
    public boolean numberValidator(){
        quizType = radioGroup.getCheckedRadioButtonId();
        if(name.getText().toString().isEmpty()){
            name.setError("Fields Cannot Be Empty");return false;
        }else if(timeDuration.getText().toString().isEmpty()){
            timeDuration.setError("Fields Cannot Be Empty");return false;
        }else if(description.getText().toString().isEmpty()){
            description.setError("Fields Cannot Be Empty");return false;
        } else if(quizType==-1){
            Toast.makeText(this, "Please Select The Type Of Quiz", Toast.LENGTH_LONG).show();
            return false;
        }else{
            NOS= Integer.parseInt(timeDuration.getText().toString());
        }

        if(NOS<=0){
            timeDuration.setError("Field Cannot Be Zero Or Negative");return false;
        }else if(NOS>5){
            timeDuration.setError("Field Cannot Be Greater Than 5 Mins");return false;
        }else{
            timeDuration.setError(null);
        }
        return true;
    }

    public void buttonMusic(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(customQuizFirstProperties.this, R.raw.finalbuttonmusic);
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