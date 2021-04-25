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

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class mainMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle mToggle;
    androidx.appcompat.widget.Toolbar toolbar;
    List<Modes> lstExam;
    List<Facts> facts;
    int setter=0;


    private ViewPager slideViewPager;
    private LinearLayout dotLayout;
    private slideAdapterMainMenuHorizontalSlide sliderAdapter;
    private TextView[] mDots;
    private int currentPage;

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


        sliderAdapter=new slideAdapterMainMenuHorizontalSlide(this);
        slideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        slideViewPager.addOnPageChangeListener(viewListner);






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
        lstExam.add(new Modes("Single Mode",R.drawable.singleicon,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Modes("1 Vs 1",R.drawable.versusicon,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        lstExam.add(new Modes("Tournment Mode",R.drawable.tournament,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Modes("Picture Quiz",R.drawable.picturequizicon,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        lstExam.add(new Modes("KBC",R.drawable.kbc,"Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        lstExam.add(new Modes("Custom Quiz",R.drawable.customicon,"Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
    }

    public void marta(){
        facts.add(new Facts("Single Mode","Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
        facts.add(new Facts("1 Vs 1","Joint Entrance Examination – Main is a national level entrance exam conducted by NTA to offer admission to BE/BTech, BPlan, BArch courses at IIITs, NITs"));
        facts.add(new Facts("Tournment Mode","Joint Entrance Examination – Advanced, which replaces IIT-JEE, is an annual examination for admissions to the prestigious IITs of India."));
    }


}