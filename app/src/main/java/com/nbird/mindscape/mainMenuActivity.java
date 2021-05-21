package com.nbird.mindscape;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class mainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle mToggle;
    androidx.appcompat.widget.Toolbar toolbar;
    List<Modes> lstExam;
    List<Facts> facts;
    int setter=0;
    List<Avatar> avatarList;
    TextInputEditText usernameEditText;
    ImageView nav_image;
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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        slideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        dotLayout=(LinearLayout) findViewById(R.id.dotLayout);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        //ProfileButton
        ImageView profilebutton = (ImageView) findViewById(R.id.profilebutton);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainMenuActivity.this,profile.class);
                startActivity(i);
            }
        });

        //Navigation View: ProfilePic and Email fetch
        final SharedPreferences mailreminder = this.getSharedPreferences("mailreminder123", 0);
        final SharedPreferences.Editor editormailreminder = mailreminder.edit();

        SharedPreferences propicurl = this.getSharedPreferences("propicurl123",0);
        final SharedPreferences.Editor editorpropicurl = mailreminder.edit();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View hView =  navigationView.getHeaderView(0);
        TextView nav_user = (TextView)hView.findViewById(R.id.mailidtext);
        mailid123 = mailreminder.getString("123", "abc@gmail.com");
        nav_user.setText(mailid123);
        nav_image = (CircleImageView) hView.findViewById(R.id.proimage);



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
        }catch (Exception e){

        }


        list = new ArrayList<>();

         firstTimeFunction();

        fAuth = FirebaseAuth.getInstance();

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


                navigationView.setCheckedItem(R.id.nav_view);
         RecyclerCardView();

           for(int i=1;i<=3;i++){
               dataForHorizontalSlide();
           }

    }



    public void dataForHorizontalSlide(){

        // create instance of Random class
        Random rand = new Random();

        // Generate random integers in range 0 to 999

        final int categoryRandomNumber = rand.nextInt(3)+1;  //NEED TO CHANGE HERE
        int setRandomNumber = rand.nextInt(4)+1;   //NEED TO CHANGE HERE



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

        switch (menuItem.getItemId()){
            case R.id.nav_logout:
                Intent intent1=new Intent(mainMenuActivity.this,loginActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent2=new Intent(mainMenuActivity.this,profile.class);
                startActivity(intent2);
                break;
            case R.id.nav_ps:
                Intent browserIntentps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FPRIVACY%20STATEMENT-converted.pdf?alt=media&token=90a45a44-0844-4468-ac76-0180fc262f74"));
                startActivity(browserIntentps);
                break;
            case R.id.nav_tos:
                Intent browserintenttos = new Intent(Intent.ACTION_VIEW,Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FTERMS%20OF%20SERVICE-converted.pdf?alt=media&token=d07a0294-a15f-4c30-802b-d1ddc0a3eb31"));
                startActivity(browserintenttos);
                break;
            case R.id.nav_ref:
                Intent browserintentref = new Intent(Intent.ACTION_VIEW,Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FRefund%20Policy-converted.pdf?alt=media&token=2679a62d-0b1a-4b57-8eb0-903295225076"));
                startActivity(browserintentref);
                break;
            default : return true;


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
        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview);
        RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(this,lstExam,setter);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }

    /*public void HorizontalRecyclerView(){
        facts=new ArrayList<Facts>();
        marta();
        RecyclerView myrv=(RecyclerView) findViewById(R.id.horizontalrecyclerview);
        RecyclerViewAdapterHorizontal myAdapter=new RecyclerViewAdapterHorizontal(this,facts,setter);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);
    }

    public void horirecyclerview(){
        RecyclerView recyclerView = findViewById(R.id.horizontalrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainMenuActivity.this);
        linearLayoutManager.setOrientation(recyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        facts = new ArrayList<>();
        marta();
        final recyclerViewAdapterHori categoryAdapter = new recyclerViewAdapterHori(facts);
        recyclerView.setAdapter(categoryAdapter);
    }*/

    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

    public void parto(){
        lstExam.add(new Modes("Single Mode",R.drawable.singleicon,"Test your knowledge and compete against time. Score points for accuracy and achieve ranks."));
        lstExam.add(new Modes("1 Vs 1",R.drawable.versusicon,"Time for the One-On-One. Compete with a rival online. Time your knowledge and be the champion."));
        lstExam.add(new Modes("Tournament Mode",R.drawable.tournament,"Quizzers from all over the world come together in the arena to show who's the ultimate leaderboard breaker."));
        lstExam.add(new Modes("Picture Quiz",R.drawable.picturequizicon,"Test your visual skills and ace your pictorial predicts. Compete in single mode or join the online multiplayer."));
        lstExam.add(new Modes("KBC",R.drawable.kbc_main,"The legendary KBC is back! Crack the questions and earn as much as you can. It's your time to set the leaderboard UP!"));
        lstExam.add(new Modes("Custom Quiz",R.drawable.customicon,"Do your friends really know you? Shh..we got the plan. Create, share and enjoy with these custom quiz that YOU design."));
    }



    public void firstTimeFunction(){
        firstTime=getIntent().getIntExtra("firstTime",1);

        if(firstTime==0){




                    AlertDialog.Builder builder=new AlertDialog.Builder(mainMenuActivity.this,R.style.AlertDialogTheme);

                    final View view1= LayoutInflater.from(mainMenuActivity.this).inflate(R.layout.profile_selection_layout,(ConstraintLayout) findViewById(R.id.layoutDialogContainer));
                    builder.setView(view1);
                    builder.setCancelable(false);
                    ((TextView) view1.findViewById(R.id.textTitle)).setText(" Enter Username ");
                    ((Button) view1.findViewById(R.id.buttonYes)).setText("OKAY");
                    ((TextView) view1.findViewById(R.id.textView6)).setText(" Either Select An Avatar Or Upload Your Pic ");
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
                            nav_image.setImageBitmap(selectedImage);

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

            settingBlack();

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
                settingBlack();
                linearLayout3.setBackgroundResource(R.drawable.whitewithblackstroke);
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

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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


}