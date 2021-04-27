package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle mToggle;
    androidx.appcompat.widget.Toolbar toolbar;
    List<Modes> lstExam;
    List<Facts> facts;
    int setter=0;


    public ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private slideAdapterMainMenuHorizontalSlide sliderAdapter;
    private TextView[] mDots;
    private int currentPage;


    private List<mainMenuFactsHolder> list;
    mainMenuFactsAdapter categoryAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        slideViewPager=(ViewPager) findViewById(R.id.slideViewPager);
        dotLayout=(LinearLayout) findViewById(R.id.dotLayout);



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

         dataForHorizontalSlide();






    }

    public void dataForHorizontalSlide(){
        list = new ArrayList<>();



        myRef.child("Facts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(mainMenuFactsHolder.class));
                }



                sliderAdapter=new slideAdapterMainMenuHorizontalSlide(mainMenuActivity.this,list);
                slideViewPager.setAdapter(sliderAdapter);
                addDotsIndicator(0);
                slideViewPager.addOnPageChangeListener(viewListner);
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(mainMenuActivity.this,"Facts Data Can't be Loaded", Toast.LENGTH_SHORT).show();
            }
        });



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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.referral_code:
                Intent intent1=new Intent(mainMenuActivity.this,loginActivity.class);
                startActivity(intent1);
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

    public void parto(){
        lstExam.add(new Modes("Single Mode",R.drawable.singleicon,"Test your knowledge and compete against time. Score points for accuracy and achieve ranks."));
        lstExam.add(new Modes("1 Vs 1",R.drawable.versusicon,"Time for the One-On-One. Compete with a rival online. Time your knowledge and be the champion."));
        lstExam.add(new Modes("Tournament Mode",R.drawable.tournament,"Quizzers from all over the world come together in the arena to show who's the ultimate leaderboard breaker."));
        lstExam.add(new Modes("Picture Quiz",R.drawable.picturequizicon,"Test your visual skills and ace your pictorial predicts. Compete in single mode or join the online multiplayer."));
        lstExam.add(new Modes("KBC",R.drawable.kbc,"The legendary KBC is back! Crack the questions and earn as much as you can. It's your time to set the leaderboard UP!"));
        lstExam.add(new Modes("Custom Quiz",R.drawable.customicon,"Do your friends really know you? Shh..we got the plan. Create, share and enjoy with these custom quiz that YOU design."));
    }

    public void marta(){
        facts.add(new Facts("Single Mode","Test your knowledge and compete against time. Score points for accuracy and achieve ranks."));
        facts.add(new Facts("1 Vs 1","Time for the One-On-One. Compete with a rival online. Time your knowledge and be the champion."));
        facts.add(new Facts("Tournament Mode","Quizzers from all over the world come together in the arena to show who's the ultimate leaderboard breaker."));
    }


}