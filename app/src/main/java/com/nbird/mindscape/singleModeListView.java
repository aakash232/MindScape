package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.hardware.camera2.CameraDevice;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class singleModeListView extends AppCompatActivity {

    CardView historicalMonuments,floraAndFauna,geography,astronmyAndSpace,sports,someSuperlatives,countriesCapitalsAndCurriencies,famousPersonalities;
    CardView science,importantDatesAndEvents,religionAndMythology,history,filmAndEntertainment,inventionAndDiscoveries,firstInDifferentFields;
    CardView festivalArtAndCulture,polityAndConstitution,literature,healthAndDiseases,miscellaneous;

    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_mode_list_view);
        historicalMonuments=(CardView) findViewById(R.id.cardviewHistoricalMonuments);
        floraAndFauna=(CardView) findViewById(R.id.cardviewFloraAndFauna);
        geography=(CardView) findViewById(R.id.cardviewGeography);
        astronmyAndSpace=(CardView) findViewById(R.id.cardviewAstronomyAndSpace);
        sports=(CardView) findViewById(R.id.cardviewSports);
        someSuperlatives=(CardView) findViewById(R.id.cardviewSomeSuperlatives);
        countriesCapitalsAndCurriencies=(CardView) findViewById(R.id.cardviewConuntryCapitalAndCurrencies);
        famousPersonalities=(CardView) findViewById(R.id.cardviewFamousPersonalities);
        science=(CardView) findViewById(R.id.cardviewHealthAndDiseases);
        importantDatesAndEvents=(CardView) findViewById(R.id.cardviewScience);
        religionAndMythology=(CardView) findViewById(R.id.cardviewReligionAndMythology);
        history=(CardView) findViewById(R.id.cardviewHistory);
        filmAndEntertainment=(CardView) findViewById(R.id.cardviewFilmAndEntertainment);
        inventionAndDiscoveries=(CardView) findViewById(R.id.cardviewInventionsAndDiscoveries);
        firstInDifferentFields=(CardView) findViewById(R.id.cardviewFirstInDifferentFields);
        festivalArtAndCulture=(CardView) findViewById(R.id.cardviewFestivalArtAndCulture);
        polityAndConstitution=(CardView) findViewById(R.id.cardviewPolityAndConstitution);
        literature=(CardView) findViewById(R.id.cardviewLiterature);
        healthAndDiseases=(CardView) findViewById(R.id.cardviewHealthDiseases);
        miscellaneous=(CardView) findViewById(R.id.cardviewMiscellaneous);


        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Category");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


         cardViewOnClick();

    }

    public void cardViewOnClick(){
        historicalMonuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",1);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });

        floraAndFauna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",2);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });

        geography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",3);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        astronmyAndSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",4);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",5);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        someSuperlatives.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",6);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        countriesCapitalsAndCurriencies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",7);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        famousPersonalities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",8);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });

        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",9);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        importantDatesAndEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",10);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        religionAndMythology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",11);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",12);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        filmAndEntertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",13);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        inventionAndDiscoveries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",14);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        firstInDifferentFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",15);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        festivalArtAndCulture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",16);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        polityAndConstitution.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",17);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        literature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",18);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        healthAndDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",19);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });


        miscellaneous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer musicNav;
                musicNav = MediaPlayer.create(singleModeListView.this, R.raw.buttonmusic);
                musicNav.start();
                Intent intent= new Intent(singleModeListView.this,quizActivity.class);
                intent.putExtra("category",20);
                startActivity(intent);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                finish();
               
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            Intent i=new Intent(singleModeListView.this,mainMenuActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            finish();
           
        }


        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Intent i=new Intent(singleModeListView.this,mainMenuActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
        finish();

    }

}