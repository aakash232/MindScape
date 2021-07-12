package com.nbird.mindscape;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityManager;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
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
import java.util.Random;
import java.util.UUID;

import static java.lang.Integer.parseInt;

public class mainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle mToggle;
    androidx.appcompat.widget.Toolbar toolbar;
    List<Modes> lstExam;
    List<prizeRecyclerHolder> lstExam123;
    List<Facts> facts;
    int setter=0;
    List<Avatar> avatarList;
    TextInputEditText usernameEditText;
    ImageView nav_image,nav_image123;
    int firstTime;
    Uri imageUri;
    public ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private slideAdapterMainMenuHorizontalSlide sliderAdapter;
    private TextView[] mDots;
    private int currentPage;
    RecyclerView recyclerView11;
    StorageReference ref;
    private List<mainMenuFactsHolder> list;
    mainMenuFactsAdapter categoryAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
   int num=0;
    String randomuid,mailid123,imageurl,imageurl123;

    FirebaseStorage storage;
    StorageReference storageReference;

    FirebaseAuth fAuth;

    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10;
    CardView cardView11,cardView12,cardView13,cardView14,cardView15,cardView16,cardView17,cardView18,cardView19,cardView20;
    CardView cardView21,cardView22,cardView23,cardView24,cardView25,cardView26;

    LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6,linearLayout7,linearLayout8,linearLayout9,linearLayout10;
    LinearLayout linearLayout11,linearLayout12,linearLayout13,linearLayout14,linearLayout15,linearLayout16,linearLayout17,linearLayout18,linearLayout19,linearLayout20;
    LinearLayout linearLayout21,linearLayout22,linearLayout23,linearLayout24,linearLayout25,linearLayout26;

    ImageView ava1,ava2,ava3,ava4,ava5,ava6,ava7,ava8,ava9,ava10,ava11,ava12,ava13,ava14,ava15,ava16,ava17,ava18,ava19,ava20;
    ImageView ava21,ava22,ava23,ava24,ava25,ava26;

    private ShimmerFrameLayout mShimmerViewContainer;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

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

    int removal;
    String hostName10;


    LottieAnimationView prizeModeAnim;
    LottieAnimationView partypoper,party2;


    MediaPlayer music,musicNav;

    int surveyInt1=-1,surveyInt2=-1,surveyInt3=-1;
    int surveyIntYesOrNo=-1;
    String str1="",str2="",str3="";


    ArrayList<Integer> l;
    Boolean isInBackground;
    Switch  switch1,switch2,switch3,switch4,switch5,switch6;
    int rand_int1;
    CountDownTimer countDownTimer;
    String linkdata;
    String musicURLString;

    int timerStarterInt;

    int highjack=0;

    private class DownloadData extends AsyncTask<String,Void,String> {
        private static final String TAG = "DownloadData";

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            return;
        }

        @Override
        protected String doInBackground(String... strings) {

            final Random rand = new Random();

            int i = 1;

            try{
                i = rand.nextInt(5)+1;
            }catch (Exception e){

            }




            try {
               // music.setDataSource(strings[0]);
                music = MediaPlayer.create(mainMenuActivity.this, l.get(i));

            } catch (Exception e) {
                e.printStackTrace();
            }


            music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {

                    music.start();

                    lol=1;
                    music.setVolume(0.7f,0.7f);
                    finalManu=0;

                    final SharedPreferences songHolder1 = getSharedPreferences("songKnowe", 0);
                    final SharedPreferences.Editor editorsongHolder1 = songHolder1.edit();

                    timerStarterInt=songHolder1.getInt("timerStarterInt",0);




                    timerStarterInt++;
                    editorsongHolder1.putInt("timerStarterInt",timerStarterInt);
                    editorsongHolder1.commit();

                    if(timerStarterInt==1){

                        try {
                            countDownTimer.start();
                        }catch (Exception e){

                        }

                    }
                }
            });
            try {
                music.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }


            return null;
        }
    }

    int lol=0;
    int finalManu=0;
    int mainfinder;
    CountDownTimer countIsInternet;
    Boolean connected=false;
     AlertDialog alertDialog6;
     int wanted=1;

     //String To Be Changed After Each Update
     String version="Version1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);




        myRef.child("UpdateString").child("version").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!version.equals(snapshot.getValue(String.class))){
                    UpdateAsker();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        countIsInternet=new CountDownTimer(1000*60*30,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network
                    connected = true;
                    try {
                        alertDialog6.dismiss();
                    }catch (Exception e){

                    }

                }
                else {
                    connected = false;
                    if(wanted==1){
                        InternetDialog();
                        wanted=0;
                    }


                }

                if(connected){

                    if(countIsInternet!=null){
                        countIsInternet.cancel();
                        wanted=1;
                    }
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();




        final SharedPreferences songStopper = this.getSharedPreferences("SongStopperManu", 0);
        final SharedPreferences.Editor editorsongStopper = songStopper.edit();

        int p=songStopper.getInt("songStopper",1);

        mainfinder=getIntent().getIntExtra("mainfinder",0);

        if(p==1||mainfinder==1) {

            RecyclerCardView();
        }else if(mainfinder==0){
            lstExam=new ArrayList<>();
            parto();
            RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
            RecyclerViewSecondary myAdapter=new RecyclerViewSecondary(this,lstExam,setter);
            myrv.setLayoutManager(new GridLayoutManager(this,2));
            myrv.setAdapter(myAdapter);
        }
        partypoper=(LottieAnimationView) findViewById(R.id.partypoper);
        party2=(LottieAnimationView) findViewById(R.id.party2);

        partypoper.setVisibility(View.GONE);
        party2.setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        slideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        dotLayout=(LinearLayout) findViewById(R.id.dotLayout);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        prizeModeAnim=((LottieAnimationView) findViewById(R.id.prizeMode));

        lstExam123 = new ArrayList<>();



        prizeModeAnim.setVisibility(View.GONE);

        //ProfileButton
        LottieAnimationView profilebutton = (LottieAnimationView) findViewById(R.id.profilebutton);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainMenuActivity.this,profile.class);
                startActivity(i);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            }
        });



        l=new ArrayList<>(6);


        final SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("musicCollection",0);
        final SharedPreferences.Editor myEdit = sharedPreferences.edit();
        

            l.add(R.raw.music1);
            l.add(R.raw.music2);
            l.add(R.raw.music3);
            l.add(R.raw.music4);
            l.add(R.raw.music5);
            l.add(R.raw.music6);


            myRef.child("appMainLink").child("link").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    linkdata=snapshot.getValue(String.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        

        final Random rand = new Random();


        try{
             rand_int1 = rand.nextInt(6);
        }catch (Exception e){
            
        }


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        final TextView nav_mail = (TextView)hView.findViewById(R.id.mailidtext);
        nav_image123 = (ImageView) hView.findViewById(R.id.proimage);





        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 String nameString=snapshot.getValue(String.class);
                 nav_mail.setText(nameString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("propic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String nav_img_url=snapshot.getValue(String.class);
                  Glide.with(getBaseContext()).load(nav_img_url).apply(RequestOptions
                        .bitmapTransform(new RoundedCorners(18)))
                        .into(nav_image123);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        nav_image123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicManu();
                selectImage(mainMenuActivity.this);
            }
        });








        final SharedPreferences songHolder = this.getSharedPreferences("songHolder", 0);
        final SharedPreferences.Editor editorsongHolder = songHolder.edit();

        int pk=songHolder.getInt("songHolder",1);

        if(pk==1) {
            editorsongHolder.putInt("songHolder",0);

            editorsongHolder.commit();

            try {
             //   songURLDownload();
            }catch (Exception e){

            }
        }





        try{
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineCorrectAns").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineCurrentScore").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineLifeLineUsed").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineTimeTaken").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").child("OnCompleteHolder").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1Online").child("accept").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").removeValue();
            myRef.child("battleGround").child("onevsoneOnline").child(mAuth.getCurrentUser().getUid()).removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("1vs1onlineOpponentUID").removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("questionNUmberPicUP").removeValue();
            myRef.child("oneVsoneLocalPlayers").child(mAuth.getCurrentUser().getUid()).removeValue();
            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("myStatus").removeValue();
            myRef.child("oneVsoneOnlinePlayers").child(mAuth.getCurrentUser().getUid()).removeValue();
        }catch (Exception e){

        }



        numberOfTimesPlayed();

        final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();




        mailid123 = mailreminder.getString("123", "abc@gmail.com");

        list = new ArrayList<>();

         firstTimeFunction();

        fAuth = FirebaseAuth.getInstance();

        prizeModeMain();


        removal=getIntent().getIntExtra("removal",0);
        hostName10=getIntent().getStringExtra("hostName");

        if(removal==5){
            AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);
            View view=LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.removal_lobby_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));


            TextView disText=(TextView) view.findViewById(R.id.textTitle);
            final Button buttonYes=(Button) view.findViewById(R.id.buttonYes);

            disText.setText(" Reasons For Getting Out Of The Room:\n* You Were Either Kicked By "+hostName10+"(Host).\n* "+hostName10+"(Host) Either Left The Room Or Was DisConnected Due To Which The Room Was Dissolved.\n* Or You Your Self-Left The Room");



            builder.setView(view);
            builder.setCancelable(true);

            final AlertDialog alertDialog=builder.create();
            if(alertDialog.getWindow()!=null){
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            alertDialog.show();

            buttonYes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });



        }




        myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("personal").child("propic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // convert the data back to the model
                imageurl = (String) dataSnapshot.getValue();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

                mToggle = new ActionBarDrawerToggle(mainMenuActivity.this,drawerLayout,R.string.open,R.string.close);

                navigationView.bringToFront();


        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        navigationView.setNavigationItemSelectedListener(mainMenuActivity.this);



        Menu menu = navigationView.getMenu();

        MenuItem com = menu.findItem(R.id.nav_com);
        MenuItem legal = menu.findItem(R.id.nav_legal);

        //Set Menu Titles White
        SpannableString comSpanString = new SpannableString(com.getTitle().toString());
        comSpanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, comSpanString.length(), 0); // fix the color to white
        com.setTitle(comSpanString);
        SpannableString legalSpanString = new SpannableString(legal.getTitle().toString());
        legalSpanString.setSpan(new ForegroundColorSpan(Color.WHITE), 0, legalSpanString.length(), 0); // fix the color to white
        legal.setTitle(legalSpanString);

        //Set Menu Sub Titles White
        navigationView.setNavigationItemSelectedListener(mainMenuActivity.this);
        navigationView.setItemTextColor(ContextCompat.getColorStateList(this, R.color.white));
        navigationView.setItemIconTintList(ContextCompat.getColorStateList(this, R.color.white));

        navigationView.setCheckedItem(R.id.nav_view);





        for(int i=1;i<=3;i++){
               dataForHorizontalSlide();
           }
        removalOfLastTournamentIfHost();



    }


    public void timerStarter(){

        countDownTimer=new CountDownTimer(1000 * 60 * 24 * 30, 1000) {
            ArrayList<Integer> r=l;
            @Override
            public void onTick(long l) {

                try{
                      ActivityManager.RunningAppProcessInfo myProcess = new ActivityManager.RunningAppProcessInfo();
                ActivityManager.getMyMemoryState(myProcess);
                isInBackground = myProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
                if (isInBackground) {
                    music.pause();
                } else {
                    try{
                        if (!music.isPlaying()) {
                        music.start();
                        music.setVolume(0.4f,0.4f);
                    }
                    }catch (Exception e){

                    }

                }
                music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        if(finalManu==0){
                            if (music.isPlaying()) {
                                music.stop();
                                try {
                                    music.reset();
                                }catch (Exception e){

                                }
                                music.release();

                            } else {
                                music.reset();
                                music.release();
                            }


                           // songURLDownload();
                        }

                    }
                });
                }catch (Exception e){

                }


            }

            @Override
            public void onFinish() {

            }
        };

    }

    public void InternetDialog(){
        AlertDialog.Builder builderRemove=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);
        View viewRemove1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.nointernetlayout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer),false);
        builderRemove.setView(viewRemove1);
        builderRemove.setCancelable(false);
        Button noButton=(Button) viewRemove1.findViewById(R.id.buttonNo);





         alertDialog6=builderRemove.create();
        if(alertDialog6.getWindow()!=null){
            alertDialog6.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog6.show();



        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(mainMenuActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                alertDialog6.dismiss();
            }
        });


    }

    public void releaseSong(){
        try {
            music.pause();
            music.release();
            music=null;
        }catch (Exception e){

        }
    }



    public void UpdateAsker(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.update_app_asker_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        Button noButton=(Button) view1.findViewById(R.id.buttonYes);
        Button yesButton=(Button) view1.findViewById(R.id.buttonNo);

        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

       noButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               alertDialog.dismiss();
               int pid = android.os.Process.myPid();
               android.os.Process.killProcess(pid);
               Intent intent = new Intent(Intent.ACTION_MAIN);
               intent.addCategory(Intent.CATEGORY_HOME);
               startActivity(intent);
               finish();
           }
       });

       yesButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               try{
                   Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(linkdata));
                   startActivity(browserIntent);
               }catch (Exception e){

               }

           }
       });
    }

    public void speakersAlertDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.music_manu,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);
        SeekBar seekBar=(SeekBar) view1.findViewById(R.id.determinateBar);

        CardView musicCard1=(CardView) view1.findViewById(R.id.card31);
        CardView musicCard2=(CardView) view1.findViewById(R.id.card7);
        CardView musicCard3=(CardView) view1.findViewById(R.id.card8);
        CardView musicCard4=(CardView) view1.findViewById(R.id.card32);
        CardView musicCard5=(CardView) view1.findViewById(R.id.card50);
        CardView musicCard6=(CardView) view1.findViewById(R.id.card51);
        final TextView progressTextView=(TextView) view1.findViewById(R.id.progressTextView);

        LinearLayout linear1=(LinearLayout) view1.findViewById(R.id.linear31);
        LinearLayout linear2=(LinearLayout) view1.findViewById(R.id.linear7);
        LinearLayout linear3=(LinearLayout) view1.findViewById(R.id.linear8);
        LinearLayout linear4=(LinearLayout) view1.findViewById(R.id.linear32);
        LinearLayout linear5=(LinearLayout) view1.findViewById(R.id.buzzerNormalLinear);
        LinearLayout linear6=(LinearLayout) view1.findViewById(R.id.buzzerPictureLinear);
        switch1=(Switch) view1.findViewById(R.id.switch1);
        switch2=(Switch) view1.findViewById(R.id.switch2);
        switch3=(Switch) view1.findViewById(R.id.switch3);
        switch4=(Switch) view1.findViewById(R.id.switch4);
        switch5=(Switch) view1.findViewById(R.id.switch5);
        switch6=(Switch) view1.findViewById(R.id.switch6);

        cardOnSetClickListner(musicCard1,musicCard2,musicCard3,musicCard4,musicCard5,musicCard6,linear1,linear2,linear3,linear4,linear5,linear6);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                progressTextView.setText(progressChangedValue+"% ");
              
            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(mainMenuActivity.this, "Volume At :" + progressChangedValue,
                        Toast.LENGTH_SHORT).show();

            }
        });
        final SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("musicCollection",0);
        final SharedPreferences.Editor myEdit = sharedPreferences.edit();
        final Boolean switchState1 = switch1.isChecked();
        final Boolean switchState2 = switch2.isChecked();
        final Boolean switchState3 = switch3.isChecked();
        final Boolean switchState4 = switch4.isChecked();
        final Boolean switchState5 = switch5.isChecked();
        final Boolean switchState6 = switch6.isChecked();



        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        int k1=sharedPreferences.getInt("m1",1);
        int k2=sharedPreferences.getInt("m2",1);
        int k3=sharedPreferences.getInt("m3",1);
        int k4=sharedPreferences.getInt("m4",1);
        int k5=sharedPreferences.getInt("m5",1);
        int k6=sharedPreferences.getInt("m6",1);

        if(k1==0){
            Toast.makeText(this, "fine", Toast.LENGTH_SHORT).show();
            switch1.setChecked(false);
        }
        if(k2==0){
            switch2.setChecked(false);
        }
        if(k3==0){
            switch3.setChecked(false);
        }
        if(k4==0){
            switch4.setChecked(false);
        }
        if(k5==0){
            switch5.setChecked(false);
        }
        if(k6==0){
            switch6.setChecked(false);
        }



        view1.findViewById(R.id.doneButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch1.isChecked()){
                    myEdit.putInt("m1", 1);
                    myEdit.commit();

                }else{
                    myEdit.putInt("m1", 0);
                    myEdit.commit();
                }
                if(switch2.isChecked()){
                    myEdit.putInt("m2", 1);
                    myEdit.commit();

                }else{
                    myEdit.putInt("m2", 0);
                    myEdit.commit();

                }
                if(switch3.isChecked()){
                    myEdit.putInt("m3", 1);
                    myEdit.commit();

                }else{
                    myEdit.putInt("m3", 0);
                    myEdit.commit();

                }
                if(switch4.isChecked()){
                    myEdit.putInt("m4", 1);
                    myEdit.commit();

                }else{
                    myEdit.putInt("m4", 0);
                    myEdit.commit();

                }
                if(switch5.isChecked()){
                    myEdit.putInt("m5", 1);
                    myEdit.commit();

                }else{
                    myEdit.putInt("m5", 0);
                    myEdit.commit();

                }
                if(switch6.isChecked()){
                    myEdit.putInt("m6", 1);
                    myEdit.commit();

                }else{
                    myEdit.putInt("m6", 0);
                    myEdit.commit();

                }
                alertDialog.dismiss();
            }
        });
    }



    private void stopPlaying() {
        if (music != null) {
            music.stop();
            music.release();
            music = null;
        }
    }
    
    public void musicChangerFunction(int n){
        try{
             if (music.isPlaying()) {
            music.stop();
            music.reset();
        } else {
            music.reset();
        }
        }catch (Exception e){

        }

        music = MediaPlayer.create(mainMenuActivity.this, n);
        music.start();

    }





    public void cardOnSetClickListner(final CardView musicCard1, final CardView musicCard2, final CardView musicCard3, final CardView musicCard4, final CardView musicCard5, final CardView musicCard6, final LinearLayout linear1, final LinearLayout linear2, final LinearLayout linear3, final LinearLayout linear4, final LinearLayout linear5, final LinearLayout linear6){
        final SharedPreferences kali = getBaseContext().getSharedPreferences("kali",0);
        final SharedPreferences.Editor editkali = kali.edit();

        musicCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardManu47(linear1,linear2,linear3,linear4,linear5,linear6);
                int a = kali.getInt("m1", 1);
                if(a==1){
                    editkali.putInt("m1", 0);
                    editkali.commit();
                    linear1.setAlpha(0.5f);
                    musicChangerFunction(R.raw.music1);
                }else{
                    editkali.putInt("m1", 1);
                    editkali.commit();
                    linear1.setAlpha(1f);

                }

            }
        });
        musicCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardManu47(linear1,linear2,linear3,linear4,linear5,linear6);
                int a = kali.getInt("m2", 1);
                if(a==1){
                    editkali.putInt("m2", 0);
                    editkali.commit();
                    linear2.setAlpha(0.5f);
                    musicChangerFunction(R.raw.music2);
                }else{
                    editkali.putInt("m2", 1);
                    editkali.commit();
                    linear2.setAlpha(1f);

                }
            }
        });
        musicCard3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardManu47(linear1,linear2,linear3,linear4,linear5,linear6);
                int a = kali.getInt("m3", 1);
                if(a==1){
                    editkali.putInt("m3", 0);
                    editkali.commit();
                    linear3.setAlpha(0.5f);
                    musicChangerFunction(R.raw.music3);
                }else{
                    editkali.putInt("m3", 1);
                    editkali.commit();
                    linear3.setAlpha(1f);

                }
            }
        });
        musicCard4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardManu47(linear1,linear2,linear3,linear4,linear5,linear6);
                int a = kali.getInt("m4", 1);
                if(a==1){
                    editkali.putInt("m4", 0);
                    editkali.commit();
                    linear4.setAlpha(0.5f);
                    musicChangerFunction(R.raw.music4);
                }else{
                    editkali.putInt("m4", 1);
                    editkali.commit();
                    linear4.setAlpha(1f);

                }
            }
        });
        musicCard5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardManu47(linear1,linear2,linear3,linear4,linear5,linear6);
                int a = kali.getInt("m5", 1);
                if(a==1){
                    editkali.putInt("m5", 0);
                    editkali.commit();
                    linear5.setAlpha(0.5f);
                    musicChangerFunction(R.raw.music5);
                }else{
                    editkali.putInt("m5", 1);
                    editkali.commit();
                    linear5.setAlpha(1f);

                }
            }
        });
        musicCard6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardManu47(linear1,linear2,linear3,linear4,linear5,linear6);
                int a = kali.getInt("m6", 1);
                if(a==1){
                    editkali.putInt("m6", 0);
                    editkali.commit();
                    linear6.setAlpha(0.5f);
                    musicChangerFunction(R.raw.music6);
                }else{
                    editkali.putInt("m6", 1);
                    editkali.commit();
                    linear6.setAlpha(1f);

                }
            }
        });
    }

    public void cardManu47(LinearLayout musicCard1, LinearLayout musicCard2, LinearLayout musicCard3, LinearLayout musicCard4, LinearLayout musicCard5, LinearLayout musicCard6){
        musicCard1.setAlpha(1);
        musicCard2.setAlpha(1);
        musicCard3.setAlpha(1);
        musicCard4.setAlpha(1);
        musicCard5.setAlpha(1);
        musicCard6.setAlpha(1);
    }

    public void prizeModeMain(){
        myRef.child("PrizeMode").child("indicator").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    int i=snapshot.getValue(Integer.class);
                    if(i==1){
                        prizeModeAnim.setVisibility(View.VISIBLE);
                        prizeModeAnim.setAnimation(R.raw.moneystackanim);
                        prizeModeAnim.playAnimation();
                        prizeModeAnim.loop(true);


                        prizeModeAnim.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alertDialogForPrizeMode();
                                partypoper.setVisibility(View.VISIBLE);
                                partypoper.setAnimation(R.raw.partypoppersanim);
                                partypoper.playAnimation();
                                partypoper.loop(false);
                                party2.setVisibility(View.VISIBLE);
                                party2.setAnimation(R.raw.party3);
                                party2.playAnimation();
                                party2.loop(false);
                            }
                        });

                    }
                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void alertDialogForPrizeMode(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);

        final View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.prize_quiz_selector_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(true);
        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        lstExam123.clear();

        RecyclerView recyclerView=(RecyclerView) view1.findViewById(R.id.recyclerview);


        final prizeRecyclerAdapter myAdapter=new prizeRecyclerAdapter(this,lstExam123,alertDialog);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(myAdapter);


        AdView mAdView = view1.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        myRef.child("PrizeMode").child("Packets").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot1 : snapshot.getChildren() ){
                    lstExam123.add(dataSnapshot1.getValue(prizeRecyclerHolder.class));
                    myAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    public void dataForHorizontalSlide(){

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999

        final int categoryRandomNumber = rand.nextInt(4)+1;  //NEED TO CHANGE HERE
        int setRandomNumber = rand.nextInt(50)+1;   //NEED TO CHANGE HERE



        myRef.child("Facts").child(String.valueOf(categoryRandomNumber)).orderByChild("set").equalTo(setRandomNumber).addListenerForSingleValueEvent(new ValueEventListener() {


            @Override
            public void onDataChange( DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(mainMenuFactsHolder.class));
                    num++;
                }

                if(num==3){
                    mShimmerViewContainer.stopShimmerAnimation();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    AdapterManupulation();
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(mainMenuActivity.this,"Facts Data Can't be Loaded", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void AdapterManupulation(){
        sliderAdapter=new slideAdapterMainMenuHorizontalSlide(mainMenuActivity.this,list);
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

    public void addDotsIndicator(int position){
        mDots=new TextView[3];
        dotLayout.removeAllViews();
        for(int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(40);
            mDots[i].setTextColor(getResources().getColor(R.color.white));
            dotLayout.addView(mDots[i]);

        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
    ViewPager.OnPageChangeListener viewListner=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage=position;

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

        @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
            musicNav = MediaPlayer.create(mainMenuActivity.this, R.raw.navclick);
            musicNav.start();
            musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    musicNav.reset();
                    musicNav.release();
                }
            });
        switch (menuItem.getItemId()){
            case R.id.nav_logout:
                fAuth = FirebaseAuth.getInstance();

                AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);
                View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.logout_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                builder.setView(view1);
                ((Button) view1.findViewById(R.id.buttonNo)).setText("No");
                ((Button) view1.findViewById(R.id.buttonYes)).setText("Yes,Logout");

                final AlertDialog alertDialog=builder.create();
                if(alertDialog.getWindow()!=null){
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();
                view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(mainMenuActivity.this,welcomeActivity.class));
                        finish();

                    }
                });
                view1.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                break;
            case R.id.nav_profile:
                Intent intent=new Intent(mainMenuActivity.this,profile.class);
                startActivity(intent);
                break;
            case R.id.nav_about:
                Intent intent12=new Intent(mainMenuActivity.this,AboutUsActivity.class);
                startActivity(intent12);
                break;
            case R.id.nav_rate:
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(linkdata));
                startActivity(browserIntent);
                break;
            case R.id.nav_tos:
                Intent browserIntenttos = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FTERMS%20OF%20SERVICE-converted.pdf?alt=media&token=d07a0294-a15f-4c30-802b-d1ddc0a3eb31"));
                startActivity(browserIntenttos);
                break;
            case R.id.nav_ref:
                Intent browserIntenttos1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FRefund%20Policy-converted.pdf?alt=media&token=2679a62d-0b1a-4b57-8eb0-903295225076"));
                startActivity(browserIntenttos1);
                break;
            case R.id.nav_ps:
                Intent browserIntenttos2 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FPRIVACY%20STATEMENT-converted.pdf?alt=media&token=90a45a44-0844-4468-ac76-0180fc262f74"));
                startActivity(browserIntenttos2);
                break;
            case R.id.nav_help:
                Intent helpguide = new Intent(mainMenuActivity.this,HelpGuide1.class);
                startActivity(helpguide);
                break;
            case R.id.nav_contact:
                String[] TO = {"niftynile@gmail.com"};

                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("*/*");
                email.putExtra(Intent.EXTRA_EMAIL, TO);
                if(email.resolveActivity(getPackageManager()) != null)
                    startActivity(email);
                break;
            default :
                return true;


        }
        return true;
    }

    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START)))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    public void RecyclerCardView(){

        lstExam=new ArrayList<>();
        parto();
        music = new MediaPlayer();
     //   timerStarter();
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(this,lstExam,setter,music,countDownTimer);
        myrv.setLayoutManager(new GridLayoutManager(this,2));

        myrv.setAdapter(myAdapter);
    }


    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
      //  music.start();

    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
       // music.pause();
    }

    public void parto(){





        lstExam.add(new Modes("Single Mode",R.drawable.singleicon,"Test your knowledge and compete against time. Score points for accuracy and achieve ranks."));
        lstExam.add(new Modes("1 Vs 1",R.drawable.versusicon,"Time for the One-On-One. Compete with a rival online. Time your knowledge and be the champion."));
        lstExam.add(new Modes("Tournament Mode",R.drawable.leaguefinalfinal,"Quizzers from all over the world come together in the arena to show who's the ultimate leaderboard breaker."));
        lstExam.add(new Modes("Picture Quiz",R.drawable.picturequizicon,"Test your visual skills and ace your pictorial predicts. Compete in single mode or join the online multiplayer."));
        //lstExam.add(new Modes("Buzzer Round",R.drawable.buzzer2,"Buzzzz! A knowledgeable and alert mind is the one which rules. Buzz with players across the world in this mode of knowledge and Agility!"));
        lstExam.add(new Modes("Audio And Video",R.drawable.svfinal,"The ultimate test of senses! A knowledgeable and alert mind is the one which rules. Fight and make your way up!"));
        lstExam.add(new Modes("Custom Quiz",R.drawable.customquizfinal3,"Do your friends really know you? Shh..we got the plan. Create, share and enjoy with these custom quiz that YOU design."));
        lstExam.add(new Modes("KBC",R.drawable.kbc123,"The legendary KBC is back! Crack the questions and earn as much as you can. It's your time to set the leaderboard UP!"));
        lstExam.add(new Modes("League (Coming Soon)",R.drawable.tournament,"The Ultimate MindScape League. Group matches,Knockout rounds,Finale, and many more. Survive till the end and wear the Crown of a super Quizzer! Coming soon..."));
    }




    public void firstTimeFunction(){
        firstTime=getIntent().getIntExtra("firstTime",1);

        if(firstTime==0){
                    AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.profile_selection_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText(" Enter Username ");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OK");
                    ((TextView) view1.findViewById(R.id.textView6)).setText("Select An Avatar Or Upload Your Pic ");
                    nav_image=((ImageView) view1.findViewById(R.id.propic));
                    usernameEditText=((TextInputEditText) view1.findViewById(R.id.username));

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

                    nav_image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            musicManu();
                            selectImage(mainMenuActivity.this);
                        }
                    });


                    final AlertDialog alertDialog=builder.create();
                    if(alertDialog.getWindow()!=null){
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    }
                    alertDialog.show();


                    usernameEditText.setOnKeyListener(new View.OnKeyListener()
                    {
                        public boolean onKey(View v, int keyCode, KeyEvent event)
                        {
                            if (event.getAction() == KeyEvent.ACTION_DOWN)
                            {
                                switch (keyCode)
                                {
                                    case KeyEvent.KEYCODE_DPAD_CENTER:
                                    case KeyEvent.KEYCODE_ENTER:
                                    firstTime=1;
                                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("personal").child("firstTime").setValue(firstTime).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if(username()){
                                            String usernameEntered=usernameEditText.getText().toString();

                                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").setValue(usernameEntered).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });



                                            alertDialog.dismiss();
                                        }

                                    }
                                    });

                                }
                            }
                            return false;
                        }
                    });

                    view1.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            firstTime=1;
                            myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("personal").child("firstTime").setValue(firstTime).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(username()){
                                        String usernameEntered=usernameEditText.getText().toString();

                                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("userName").setValue(usernameEntered).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });
                                        alertDialog.dismiss();
                                    }

                                }
                            });
                        }
                    });

        }else{

            myRef.child("PrizeMode").child("indicator").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    try {
                        int i=snapshot.getValue(Integer.class);
                        if(i==1){

                            myRef.child("PrizeModePlayerData").child("1").child(mAuth.getCurrentUser().getUid()).child("attempts").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try{
                                        int a=snapshot.getValue(Integer.class);
                                        if(a==1){

                                        }
                                    }catch (Exception e){
                                        alertDialogForPrizeMode();
                                        partypoper.setVisibility(View.VISIBLE);
                                        partypoper.setAnimation(R.raw.partypoppersanim);
                                        partypoper.playAnimation();
                                        partypoper.loop(false);
                                        party2.setVisibility(View.VISIBLE);
                                        party2.setAnimation(R.raw.party3);
                                        party2.playAnimation();
                                        party2.loop(false);
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }catch (Exception e){

                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

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
                            try{
                                nav_image.setImageBitmap(selectedImage);
                            }catch (Exception e){
                                nav_image123.setImageBitmap(selectedImage);
                            }


                            uploadImage();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(mainMenuActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                        }

                    }else {
                        Toast.makeText(mainMenuActivity.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    }

    private void uploadImage()
    {



        if (imageUri != null) {

            try {
                settingBlack();
            }catch (Exception e){

            }


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
                                    Toast.makeText(mainMenuActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT).show();
                                   try{

                                        StorageReference urlref = storageRef.child("images/" + mailid123+"/"+randomuid);
                                        urlref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                        {
                                            @Override
                                            public void onSuccess(Uri downloadUrl)
                                            {
                                                imageurl=downloadUrl.toString();

                                                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(imageurl).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                                    .makeText(mainMenuActivity.this,
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




    private boolean username(){
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

    public void avaMunupulator(){

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout1.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout2.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout3.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva3).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout4.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva4).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout5.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva5).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout6.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva6).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout7.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva7).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout8.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva8).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout9.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva9).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout10.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva10).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout11.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva11).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout12.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva12).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout13.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva13).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout14.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva14).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout15.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva15).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout16.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva16).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout17.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva17).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout18.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva18).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout19.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva19).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout20.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva20).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout21.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva21).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout22.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva22).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout23.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva23).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });


        cardView24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout24.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva24).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout25.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva25).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
            }
        });

        cardView26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardMusic();
                settingBlack();
                linearLayout26.setBackgroundResource(R.drawable.whitewithblackstroke);
                myRef.child("User").child(fAuth.getCurrentUser().getUid()).child("propic").setValue(urlAva26).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
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

    public void removalOfLastTournamentIfHost(){
        myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).child("roomCode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int roomCode=snapshot.getValue(Integer.class);
                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).removeValue();
                    myRef.child("Lobby").child(String.valueOf(roomCode)).removeValue();
                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.child("room").child(String.valueOf(0)).child(mAuth.getCurrentUser().getUid()).child("roomCode").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int roomCode=snapshot.getValue(Integer.class);
                    myRef.child("room").child(String.valueOf(1)).child(mAuth.getCurrentUser().getUid()).removeValue();
                    myRef.child("Lobby").child(String.valueOf(roomCode)).removeValue();
                }catch (Exception e){

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void alertDialogForSurvey(){
        AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);
        View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.survey_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
        builder.setView(view1);
        builder.setCancelable(false);

        final LottieAnimationView starA1,starA2,starA3,starA4,starA5;
        final LottieAnimationView starB1,starB2,starB3,starB4,starB5;
        final LottieAnimationView starC1,starC2,starC3,starC4,starC5;
        final CheckBox check1_No,check1_Yes;
        final TextInputEditText text1,text2,text3;
        final RadioGroup radioGroup;

        starA1=(LottieAnimationView) view1.findViewById(R.id.star1);
        starA2=(LottieAnimationView) view1.findViewById(R.id.star2);
        starA3=(LottieAnimationView) view1.findViewById(R.id.star3);
        starA4=(LottieAnimationView) view1.findViewById(R.id.star4);
        starA5=(LottieAnimationView) view1.findViewById(R.id.star5);

        starB1=(LottieAnimationView) view1.findViewById(R.id.star21);
        starB2=(LottieAnimationView) view1.findViewById(R.id.star22);
        starB3=(LottieAnimationView) view1.findViewById(R.id.star23);
        starB4=(LottieAnimationView) view1.findViewById(R.id.star24);
        starB5=(LottieAnimationView) view1.findViewById(R.id.star25);

        starC1=(LottieAnimationView) view1.findViewById(R.id.star31);
        starC2=(LottieAnimationView) view1.findViewById(R.id.star32);
        starC3=(LottieAnimationView) view1.findViewById(R.id.star33);
        starC4=(LottieAnimationView) view1.findViewById(R.id.star34);
        starC5=(LottieAnimationView) view1.findViewById(R.id.star35);

        radioGroup=(RadioGroup) view1.findViewById(R.id.radioGroup);

        text1=(TextInputEditText) view1.findViewById(R.id.question1);
        text2=(TextInputEditText) view1.findViewById(R.id.question2);
        text3=(TextInputEditText) view1.findViewById(R.id.question3);

        Button doneButton=(Button) view1.findViewById(R.id.doneButton);






        final AlertDialog alertDialog=builder.create();
        if(alertDialog.getWindow()!=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

        starA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt1=1;

                animPlayer(starA1);
                starA1.setEnabled(false);
                starA2.setEnabled(false);
                starA3.setEnabled(false);
                starA4.setEnabled(false);
                starA5.setEnabled(false);
                
            }
        });

        starA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt1=2;animPlayer(starA1);animPlayer(starA2);
                starA1.setEnabled(false);
                starA2.setEnabled(false);
                starA3.setEnabled(false);
                starA4.setEnabled(false);
                starA5.setEnabled(false);
            }
        });

        starA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt1=3;animPlayer(starA1);animPlayer(starA2);animPlayer(starA3);
                starA1.setEnabled(false);
                starA2.setEnabled(false);
                starA3.setEnabled(false);
                starA4.setEnabled(false);
                starA5.setEnabled(false);
            }
        });

        starA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt1=4;animPlayer(starA1);animPlayer(starA2);animPlayer(starA3);animPlayer(starA4);
                starA1.setEnabled(false);
                starA2.setEnabled(false);
                starA3.setEnabled(false);
                starA4.setEnabled(false);
                starA5.setEnabled(false);
            }
        });

        starA5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt1=5;animPlayer(starA1);animPlayer(starA2);animPlayer(starA3);animPlayer(starA4);animPlayer(starA5);
                starA1.setEnabled(false);
                starA2.setEnabled(false);
                starA3.setEnabled(false);
                starA4.setEnabled(false);
                starA5.setEnabled(false);
            }
        });


        starB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt2=1;animPlayer(starB1);
                starB1.setEnabled(false);
                starB2.setEnabled(false);
                starB3.setEnabled(false);
                starB4.setEnabled(false);
                starB5.setEnabled(false);
            }
        });

        starB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt2=2;animPlayer(starB1);animPlayer(starB2);
                starB1.setEnabled(false);
                starB2.setEnabled(false);
                starB3.setEnabled(false);
                starB4.setEnabled(false);
                starB5.setEnabled(false);
            }
        });

        starB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt2=3;animPlayer(starB1);animPlayer(starB2);animPlayer(starB3);
                starB1.setEnabled(false);
                starB2.setEnabled(false);
                starB3.setEnabled(false);
                starB4.setEnabled(false);
                starB5.setEnabled(false);
            }
        });

        starB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt2=4;animPlayer(starB1);animPlayer(starB2);animPlayer(starB3);animPlayer(starB4);
                starB1.setEnabled(false);
                starB2.setEnabled(false);
                starB3.setEnabled(false);
                starB4.setEnabled(false);
                starB5.setEnabled(false);
            }
        });

        starB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt2=5;animPlayer(starB1);animPlayer(starB2);animPlayer(starB3);animPlayer(starB4);animPlayer(starB5);
                starB1.setEnabled(false);
                starB2.setEnabled(false);
                starB3.setEnabled(false);
                starB4.setEnabled(false);
                starB5.setEnabled(false);
            }
        });



        starC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt3=1;animPlayer(starC1);
                starC1.setEnabled(false);
                starC2.setEnabled(false);
                starC3.setEnabled(false);
                starC4.setEnabled(false);
                starC5.setEnabled(false);
            }
        });

        starC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt3=2;animPlayer(starC1);animPlayer(starC2);
                starC1.setEnabled(false);
                starC2.setEnabled(false);
                starC3.setEnabled(false);
                starC4.setEnabled(false);
                starC5.setEnabled(false);
            }
        });

        starC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt3=3;animPlayer(starC1);animPlayer(starC2);animPlayer(starC3);
                starC1.setEnabled(false);
                starC2.setEnabled(false);
                starC3.setEnabled(false);
                starC4.setEnabled(false);
                starC5.setEnabled(false);
            }
        });

        starC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt3=4;animPlayer(starC1);animPlayer(starC2);animPlayer(starC3);animPlayer(starC4);
                starC1.setEnabled(false);
                starC2.setEnabled(false);
                starC3.setEnabled(false);
                starC4.setEnabled(false);
                starC5.setEnabled(false);
            }
        });

        starC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                starManuMusic();
                surveyInt3=5;animPlayer(starC1);animPlayer(starC2);animPlayer(starC3);animPlayer(starC4);animPlayer(starC5);
                starC1.setEnabled(false);
                starC2.setEnabled(false);
                starC3.setEnabled(false);
                starC4.setEnabled(false);
                starC5.setEnabled(false);
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musicManu();



                int i;

                i = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=(RadioButton) radioGroup.findViewById(i);

                try {
                    if(radioButton.getText().toString().equals("Yes")){
                        surveyIntYesOrNo=1;
                    }else{
                        surveyIntYesOrNo=0;
                    }
                }catch (Exception e){

                }


                str1=text1.getText().toString();
                str2=text2.getText().toString();
                str3=text3.getText().toString();



                surveyDataSetter(starA1,starA2,starA3,starA4,starA5,starB1,starB2,starB3,starB4,starB5,starC1,starC2,starC3,starC4,starC5,radioGroup,text1,text2,text3);
                alertDialog.cancel();
                Toast.makeText(mainMenuActivity.this, "Thankyou,For Your FeedBack!!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void animPlayer(LottieAnimationView anim){
        anim.setAnimation(R.raw.ratequizsanim);
        anim.playAnimation();
        anim.loop(false);
    }



    public void surveyDataSetter(LottieAnimationView starA1, LottieAnimationView starA2, LottieAnimationView starA3, LottieAnimationView starA4, LottieAnimationView starA5, LottieAnimationView starB1, LottieAnimationView starB2, LottieAnimationView starB3, LottieAnimationView starB4, LottieAnimationView starB5, LottieAnimationView starC1, LottieAnimationView starC2, LottieAnimationView starC3, LottieAnimationView starC4, LottieAnimationView starC5, RadioGroup radioGroup, TextInputEditText text1, TextInputEditText text2, TextInputEditText text3){



        if(surveyInt1==-1){
            Toast.makeText(mainMenuActivity.this, "Please Rate You Experience Using Stars", Toast.LENGTH_SHORT).show();return;
        }else if(surveyInt2==-1){
            Toast.makeText(mainMenuActivity.this, "Please Rate The Interface Using Stars", Toast.LENGTH_SHORT).show();return;
        }else if(surveyInt3==-1){
            Toast.makeText(mainMenuActivity.this, "Please Rate The Quality Of Questions Using Stars", Toast.LENGTH_SHORT).show();return;
        }else if(surveyIntYesOrNo==-1){
            Toast.makeText(mainMenuActivity.this, "Please Select The Radio Button", Toast.LENGTH_SHORT).show();return;
        }




        SurveyHolder surveyHolder=new SurveyHolder(surveyInt1,surveyInt2,surveyInt3,surveyIntYesOrNo,str1,str2,str3);

        myRef.child("Survey1").child(mAuth.getCurrentUser().getUid()).setValue(surveyHolder).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });

        myRef.child("Survey1").child("SURVEYSTARINT1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    surveyAvgHolder i=snapshot.getValue(surveyAvgHolder.class);

                    int numberOfTimesPlayed=i.getNumberOfSurvey()+1;
                    int ratingNum=i.getStarAvgNum();

                    ratingNum=(ratingNum+surveyInt1)/2;

                    i.setNumberOfSurvey(numberOfTimesPlayed);
                    i.setStarAvgNum(ratingNum);

                    myRef.child("Survey1").child("SURVEYSTARINT1").setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });



                }catch (Exception e){
                    surveyAvgHolder i = new surveyAvgHolder(surveyInt1,1);
                    myRef.child("Survey1").child("SURVEYSTARINT1").setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myRef.child("Survey1").child("SURVEYSTARINT2").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    surveyAvgHolder J=snapshot.getValue(surveyAvgHolder.class);

                    int numberOfTimesPlayed=J.getNumberOfSurvey()+1;
                    int ratingNum=J.getStarAvgNum();

                    ratingNum=(ratingNum+surveyInt2)/2;

                    J.setNumberOfSurvey(numberOfTimesPlayed);
                    J.setStarAvgNum(ratingNum);

                    myRef.child("Survey1").child("SURVEYSTARINT2").setValue(J).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });



                }catch (Exception e){
                    surveyAvgHolder i = new surveyAvgHolder(surveyInt2,1);
                    myRef.child("Survey1").child("SURVEYSTARINT2").setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        myRef.child("Survey1").child("SURVEYSTARINT3").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    surveyAvgHolder K=snapshot.getValue(surveyAvgHolder.class);

                    int numberOfTimesPlayed=K.getNumberOfSurvey()+1;
                    int ratingNum=K.getStarAvgNum();

                    ratingNum=(ratingNum+surveyInt3)/2;

                    K.setNumberOfSurvey(numberOfTimesPlayed);
                    K.setStarAvgNum(ratingNum);

                    myRef.child("Survey1").child("SURVEYSTARINT3").setValue(K).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });



                }catch (Exception e){
                    surveyAvgHolder i = new surveyAvgHolder(surveyInt3,1);
                    myRef.child("Survey1").child("SURVEYSTARINT3").setValue(i).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void numberOfTimesPlayed(){
        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("numberOfTimesPlayed").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try{
                    int i=snapshot.getValue(Integer.class);
                    if(i>=10){
                        myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("isSurveyOfVersion1").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                try {
                                    int i=snapshot.getValue(Integer.class);
                                    if(i==0){
                                        alertDialogForSurvey();
                                    }
                                }catch (Exception e){
                                    alertDialogForSurvey();
                                    myRef.child("User").child(mAuth.getCurrentUser().getUid()).child("isSurveyOfVersion1").setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }

                }catch (Exception e){

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void musicManu(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(mainMenuActivity.this, R.raw.finalbuttonmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
    }

    public void starManuMusic(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(mainMenuActivity.this, R.raw.correctmusic);
        musicNav.start();
        musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                musicNav.reset();
                musicNav.release();
            }
        });
    }

    public void cardMusic(){
        final MediaPlayer musicNav;
        musicNav = MediaPlayer.create(mainMenuActivity.this, R.raw.navclick);
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