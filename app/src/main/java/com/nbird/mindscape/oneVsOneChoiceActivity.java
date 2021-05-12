package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.VerifiedInputEvent;
import android.view.View;
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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class oneVsOneChoiceActivity extends AppCompatActivity {
    CardView online;
    ImageView userImage, batch;
    TextView userName123, level, secondPlayername;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private List<mainMenuFactsHolder> list;
    private List<onevsoneOnlinePlayerList> list123;
    public ViewPager slideViewPager;
    String userName, proPicUrl;
    private TextView[] mDots;
    int num = 0;
    private ShimmerFrameLayout mShimmerViewContainer, shimmer1;
    private onevsoneonlineAdapter sliderAdapter;
    ActionBarDrawerToggle mToggle;
    private LinearLayout dotLayout;
    private int currentPage;
    AlertDialog.Builder builder;
    View view1;
    int i = 0;
    CardView cardView5;
    Button cancelButton;
    int matcherStatus, timeHolder, correct, wrong, wrongfire, sumationOfScore;
    String opponentUID;
    int status = 1;
    int jocker;
    int score123;
    CountDownTimer countDownTimer, countDownTimer123;
    String opponentimageUrl, opponentUsername;
    ImageView secondplayerimg;
    String secondMan;
    int leader = 0;
    List<Integer> arrlist;
    int globalInt;
    String username;
    TextView myLevel, oppoLevel, oppoRatio;
    ImageView myBatch, oppoBatch;
    int sumofq;

    TextView totalTime, oppoAccu;
    TextView highestScore;
    LinearLayout linearLayout100,batchLinearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_vs_one_choice);

        online = (CardView) findViewById(R.id.online);
        arrlist = new ArrayList<>(13);



        builder = new AlertDialog.Builder(oneVsOneChoiceActivity.this, R.style.AlertDialogTheme);
        view1 = LayoutInflater.from(oneVsOneChoiceActivity.this).inflate(R.layout.onevsone_online_layout, (ConstraintLayout) findViewById(R.id.layoutDialogContainer), false);
        mShimmerViewContainer = (ShimmerFrameLayout) view1.findViewById(R.id.shimmer_view_container);
        shimmer1 = (ShimmerFrameLayout) view1.findViewById(R.id.shimmer1);
        oppoRatio = (TextView) view1.findViewById(R.id.oppoRatio);
        oppoAccu = (TextView) view1.findViewById(R.id.oppoAccu);
        highestScore = (TextView) view1.findViewById(R.id.highestScore);
        totalTime = (TextView) view1.findViewById(R.id.totalTime);
        linearLayout100=(LinearLayout) view1.findViewById(R.id.linearLayout100);
        batchLinearLayout=(LinearLayout) view1.findViewById(R.id.batchdisplay);
        cardView5=(CardView) view1.findViewById(R.id.cardView5);
        list = new ArrayList<>();
        list123 = new ArrayList<>();
        buttonFunction();
        userData();
    }

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
        shimmer1.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        shimmer1.stopShimmerAnimation();
        super.onPause();
    }


    public void dataForHorizontalSlide() {

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999

        final int categoryRandomNumber = rand.nextInt(3) + 1;  //NEED TO CHANGE HERE
        int setRandomNumber = rand.nextInt(4) + 1;   //NEED TO CHANGE HERE


        myRef.child("Facts").child(String.valueOf(categoryRandomNumber)).orderByChild("set").equalTo(setRandomNumber).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(mainMenuFactsHolder.class));
                    num++;
                }

                if (num == 3) {
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    AdapterManupulation();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(oneVsOneChoiceActivity.this, "Facts Data Can't be Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void AdapterManupulation() {
        sliderAdapter = new onevsoneonlineAdapter(oneVsOneChoiceActivity.this, list);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListner);
        sliderAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    public void addDotsIndicator(int position) {
        mDots = new TextView[3];
        dotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.lightgrey));
            dotLayout.addView(mDots[i]);

        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener viewListner = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public void userData() {
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userName = snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                proPicUrl = snapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void buttonFunction() {
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findingOponentFunction();
                builder.setView(view1);
                builder.setCancelable(false);
                cancelButton = ((Button) view1.findViewById(R.id.cancelButton));
                userImage = (ImageView) view1.findViewById(R.id.exam_img_id);
                userName123 = (TextView) view1.findViewById(R.id.userName123);
                batch = (ImageView) view1.findViewById(R.id.batch4);
                level = (TextView) view1.findViewById(R.id.levelText1234);
                slideViewPager = (ViewPager) view1.findViewById(R.id.slideViewPager);
                dotLayout = (LinearLayout) view1.findViewById(R.id.dotLayout);
                secondplayerimg = (ImageView) view1.findViewById(R.id.secondplayerimg);
                secondPlayername = (TextView) view1.findViewById(R.id.secondPlayername);
                myLevel = (TextView) view1.findViewById(R.id.levelText1234);
                myBatch = (ImageView) view1.findViewById(R.id.batch4);
                oppoLevel = (TextView) view1.findViewById(R.id.levelText12341);
                oppoBatch = (ImageView) view1.findViewById(R.id.batch41);
                dataSetterForOneVsOne();
                myDataSeeter();

                for (int i = 1; i <= 3; i++) {
                    dataForHorizontalSlide();
                }

                final AlertDialog alertDialog = builder.create();
                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();


                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();

                        } catch (Exception e) {

                        }

                        alertDialog.dismiss();
                        Intent intent=new Intent(oneVsOneChoiceActivity.this,mainMenuActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });


            }
        });
    }

    public void dataSetterForOneVsOne() {
        userName123.setText(userName);
        Glide.with(getBaseContext())
                .load(proPicUrl)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                .into(userImage);
    }


    public void matchMakingFunction() {
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("personal").child("onevsoneOnlineFinder").setValue(status).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void findingOponentFunction() {

        myRef.child("oneVsoneOnlinePlayers").orderByChild("status").equalTo(1).limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list123.add(dataSnapshot1.getValue(onevsoneOnlinePlayerList.class));
                }
                try {
                    opponentUID = list123.get(0).getUID();
                    leader = 1;
                    myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();
                    myRef.child("oneVsoneOnlinePlayers").child(opponentUID).removeValue();

                    randomNumberGeneratorFunction(opponentUID);



                    myRef.child("User").child(opponentUID).child("1vs1onlineOpponentUID").setValue(mAuth.getCurrentUser().getUid()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            myRef.child("User").child(opponentUID).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    opponentimageUrl = snapshot.getValue(String.class);

                                    myRef.child("User").child(opponentUID).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            opponentUsername = snapshot.getValue(String.class);
                                            cardView5.setAlpha(1);
                                            linearLayout100.setAlpha(1);
                                            batchLinearLayout.setAlpha(1);
                                            shimmer1.stopShimmerAnimation();
                                            shimmer1.setVisibility(View.GONE);
                                            Glide.with(getBaseContext())
                                                    .load(opponentimageUrl)
                                                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                    .into(secondplayerimg);

                                            opponentDataGetterFunction();

                                            secondPlayername.setText(opponentUsername);


                                            cancelButton.setEnabled(false);
                                            cancelButton.setAlpha(1);
                                            countDownTimerFun();
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
                    });

                } catch (Exception e) {
                    onevsoneOnlinePlayerList s1 = new onevsoneOnlinePlayerList(mAuth.getCurrentUser().getUid(), 1);
                    myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineOpponentUID").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    opponentUID = snapshot.getValue(String.class);
                                    if (opponentUID != null) {


                                        opponentDataGetterFunction();

                                        myRef.child("User").child(opponentUID).child("propic").addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                opponentimageUrl = snapshot.getValue(String.class);

                                                myRef.child("User").child(opponentUID).child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                        opponentUsername = snapshot.getValue(String.class);
                                                        cardView5.setAlpha(1);
                                                        linearLayout100.setAlpha(1);
                                                        batchLinearLayout.setAlpha(1);
                                                        shimmer1.stopShimmerAnimation();
                                                        shimmer1.setVisibility(View.GONE);

                                                        Glide.with(getBaseContext())
                                                                .load(opponentimageUrl)
                                                                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                                                                .into(secondplayerimg);

                                                        secondPlayername.setText(opponentUsername);


                                                        cancelButton.setEnabled(false);
                                                        cancelButton.setAlpha(1);
                                                        countDownForArr();
                                                        countDownTimerFun();
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


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    });
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void countDownTimerFun() {   //Clock Algo
        countDownTimer = new CountDownTimer(1000 * 10, 1000) {


            public void onTick(long millisUntilFinished) {
                int sec = (int) (millisUntilFinished / 1000);
                cancelButton.setText("Quiz Starts In " + sec + " Seconds");
                cancelButton.setTextSize(15f);
            }

            @Override
            public void onFinish() {

                Intent intent = new Intent(oneVsOneChoiceActivity.this, onevsoneQuizActivity.class);
                intent.putExtra("opponentUID", opponentUID);
                intent.putExtra("opponentImageUrl", opponentimageUrl);
                intent.putExtra("opponentUserName", opponentUsername);
                intent.putExtra("mypropic", proPicUrl);
                intent.putExtra("myName", userName);
                intent.putExtra("leader", leader);
                intent.putIntegerArrayListExtra("arrList12345", (ArrayList<Integer>) arrlist);
                startActivity(intent);
                finish();
            }
        }.start();

    }


    public void countDownForArr() {   //Clock Algo
        countDownTimer123 = new CountDownTimer(1000 * 10, 1000) {


            public void onTick(long millisUntilFinished) {
                randomNumberGeneratorTaker();
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }


    public void randomNumberGeneratorTaker() {

        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child("OnCompleteHolder").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int num47 = snapshot.getValue(Integer.class);
                    if (num47 == 1) {
                        countDownTimer123.cancel();
                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(1)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(1)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(2)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(2)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(3)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(3)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(4)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(4)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(5)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(5)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(6)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(6)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(7)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(7)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(8)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(8)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(9)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(9)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(10)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(10)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(11)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(11)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(12)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(12)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                        myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(13)).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    arrlist.add(snapshot.getValue(Integer.class));
                                    myRef.child("User").child(opponentUID).child("questionNUmberPicUP").child(String.valueOf(13)).removeValue();
                                } catch (Exception e) {

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void randomNumberGeneratorFunction(String opponentUID) {
        for (int i = 0; i < 14; i++) {
            // create instance of Random class
            Random rand = new Random();

            // Generate random integers in range 0 to 29

            int setNumber = rand.nextInt(29) + 1;  //NEED TO CHANGE HERE
            //NEED TO CHANGE HERE
            arrlist.add(setNumber);
        }

        try {
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(1)).setValue(arrlist.get(0)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(2)).setValue(arrlist.get(1)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(3)).setValue(arrlist.get(2)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(4)).setValue(arrlist.get(3)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(5)).setValue(arrlist.get(4)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(6)).setValue(arrlist.get(5)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(7)).setValue(arrlist.get(6)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(8)).setValue(arrlist.get(7)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(9)).setValue(arrlist.get(8)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(10)).setValue(arrlist.get(9)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(11)).setValue(arrlist.get(10)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(12)).setValue(arrlist.get(11)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                }
            });

            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child(String.valueOf(13)).setValue(arrlist.get(12)).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child("OnCompleteHolder").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });

                }
            });

        } catch (Exception e) {

        }


    }


    public void opponentDataGetterFunction() {

        myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("score").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

              try{
                  score123 = snapshot.getValue(Integer.class);

                  highestScore.setText(" Higest Score : " + score123 + " ");

                  myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("totalTime").addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          timeHolder = snapshot.getValue(Integer.class);

                          if (timeHolder < 60) {
                              totalTime.setText("Total Time : " + totalTime + " sec ");
                          } else {
                              int minutes = timeHolder / 60;
                              int sec = timeHolder % 60;
                              totalTime.setText("Total Time : " + minutes + " min " + sec + " sec ");
                          }



                          myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("correct").addListenerForSingleValueEvent(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot snapshot) {

                                  correct = snapshot.getValue(Integer.class);


                                  myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("wrong").addListenerForSingleValueEvent(new ValueEventListener() {
                                      @Override
                                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                                          wrongfire = snapshot.getValue(Integer.class);

                                          sumofq = correct + wrongfire;
                                          jocker = (correct *100) / sumofq;

                                          oppoAccu.setText(" Accuracy : " + jocker + "%");
                                          oppoRatio.setText(" Correct/Wrong : " + correct + "/" + wrongfire);

                                          myRef.child("leaderBoard").child("1vs1").child(opponentUID).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
                                              @Override
                                              public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                  sumationOfScore = snapshot.getValue(Integer.class);
                                                  levelManupulation();

                                              }

                                              @Override
                                              public void onCancelled(@NonNull DatabaseError error) {

                                              }
                                          });


                                      }


                                      public void onCancelled(@NonNull DatabaseError error) {

                                      }
                                  });


                              }

                              public void onCancelled(@NonNull DatabaseError error) {

                              }
                          });

                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });
              }catch (Exception e){
                  highestScore.setText(" Higest Score : null");
                  totalTime.setText("Total Time : null");
                  oppoAccu.setText(" Accuracy : null");
                  oppoRatio.setText(" Correct/Wrong : null");
              }

                }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    public void levelManupulation(){

        if(sumationOfScore<100000){
            if(sumationOfScore<50000){
                oppoLevel.setText(" Lv. 1 ");
            }else{
                oppoLevel.setText(" Lv. 2 ");
            }
        }else{
            int holder;
            holder=sumationOfScore/50000;
            oppoLevel.setText(" Lv. "+holder+" ");
        }

        if(sumationOfScore<50000){
            oppoBatch.setBackgroundResource(R.drawable.blackiron);
        }else if(sumationOfScore<200000){
            oppoBatch.setBackgroundResource(R.drawable.bronze);
        }else if(sumationOfScore<800000){
            oppoBatch.setBackgroundResource(R.drawable.silver);
        }else if(sumationOfScore<1800000){
            oppoBatch.setBackgroundResource(R.drawable.gold);
        }else if(sumationOfScore<3000000){
            oppoBatch.setBackgroundResource(R.drawable.platinum);
        }else if(sumationOfScore<4000000){
            oppoBatch.setBackgroundResource(R.drawable.diamond);
        }else if(sumationOfScore<8000000){
            oppoBatch.setBackgroundResource(R.drawable.amethyst);
        }else if(sumationOfScore<12000000){
            oppoBatch.setBackgroundResource(R.drawable.master);
        }else{
            oppoBatch.setBackgroundResource(R.drawable.king);
        }
    }


    public void myDataSeeter(){
        myRef.child("leaderBoard").child("1vs1").child(mAuth.getCurrentUser().getUid()).child("sumationScore").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int sumationScore=snapshot.getValue(Integer.class);
                    if(sumationScore<100000){
                        if(sumationScore<50000){
                            myLevel.setText(" Lv. 1 ");
                        }else{
                            myLevel.setText(" Lv. 2 ");
                        }
                    }else{
                        int holder;
                        holder=sumationScore/50000;
                        myLevel.setText(" Lv. "+holder+" ");
                    }

                    if(sumationScore<50000){
                        myBatch.setBackgroundResource(R.drawable.blackiron);
                    }else if(sumationScore<200000){
                        myBatch.setBackgroundResource(R.drawable.bronze);
                    }else if(sumationScore<800000){
                        myBatch.setBackgroundResource(R.drawable.silver);
                    }else if(sumationScore<1800000){
                        myBatch.setBackgroundResource(R.drawable.gold);
                    }else if(sumationScore<3000000){
                        myBatch.setBackgroundResource(R.drawable.platinum);
                    }else if(sumationScore<4000000){
                        myBatch.setBackgroundResource(R.drawable.diamond);
                    }else if(sumationScore<8000000){
                        myBatch.setBackgroundResource(R.drawable.amethyst);
                    }else if(sumationScore<12000000){
                        myBatch.setBackgroundResource(R.drawable.master);
                    }else{
                        myBatch.setBackgroundResource(R.drawable.king);
                    }
                }catch (Exception e){
                    myLevel.setText(" Lv. 1 ");
                    myBatch.setBackgroundResource(R.drawable.blackiron);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}