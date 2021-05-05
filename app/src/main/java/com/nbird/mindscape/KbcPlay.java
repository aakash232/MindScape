package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;

public class KbcPlay extends AppCompatActivity {

    ImageView music;

    public void playB(View view){
        Intent intent = new Intent(getApplicationContext(),KbcSetup.class);
        startActivity(intent);
    }

    public void score(View view){
        Intent intent = new Intent(getApplicationContext(),KbcScoreActivity.class);
        startActivity(intent);
    }
    public void exit(View view){
        Intent intent = new Intent(this,singleModeListView.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kbc_play);


    }



}