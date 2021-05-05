package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class picture_quiz_menu extends AppCompatActivity {
    CardView singlePlayerButton;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_quiz_menu);

        singlePlayerButton = (CardView) findViewById(R.id.singleplayer);


        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Category");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        singlePlayerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSinglePlayer();
            }
        });
    }

    public void openSinglePlayer() {
        Intent intent = new Intent(this, activity_picture_singlePlayer.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

}

