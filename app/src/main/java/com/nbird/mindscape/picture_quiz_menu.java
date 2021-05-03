package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class picture_quiz_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_quiz_menu);

        Button singlePlayer = findViewById(R.id.single_player);

        singlePlayer.setOnClickListener(new View.OnClickListener(){
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

}

