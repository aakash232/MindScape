package com.nbird.mindscape;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Lobby extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        //BUTTONS LINKS

            //Start button
            Button start = (Button) findViewById(R.id.start);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getBaseContext(), "Start Quiz!", Toast.LENGTH_LONG).show();

                }
            });

            //Cancel button
            ImageView cancel = (ImageView) findViewById(R.id.cancel);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent back = new Intent(Lobby.this, mainMenuActivity.class);
                    startActivity(back);
                }
            });

            //Chat button
            ImageView chat = (ImageView) findViewById(R.id.chat);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent chat = new Intent(Lobby.this, ChatRoom.class);
                    startActivity(chat);

                }
            });

            //Facts button
            ImageView facts = (ImageView) findViewById(R.id.facts);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getBaseContext(), "View Facts.", Toast.LENGTH_LONG).show();

                }
            });

            //Lobby Privacy button
            ImageView lobbyPrivacy = (ImageView) findViewById(R.id.lobby_privacy);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getBaseContext(), "Switch PUBLIC or PRIVATE.", Toast.LENGTH_LONG).show();

                }
            });

            //Lobby Settings button
            ImageView lobbySettings = (ImageView) findViewById(R.id.lobby_settings);
            start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getBaseContext(), "Host can change Settings.", Toast.LENGTH_LONG).show();

                }
            });





    }
}