package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
        Button backButton=(Button) findViewById(R.id.back);
        final TextInputEditText mailid=(TextInputEditText) findViewById(R.id.username2);
        Button getmail =(Button) findViewById(R.id.continuebutton2);

        mAuth= FirebaseAuth.getInstance();


        getmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usereEmail=mailid.getText().toString();
                if(TextUtils.isEmpty(usereEmail)){
                    Toast.makeText(getBaseContext(), "Please write a valid Email Id", Toast.LENGTH_LONG).show();
                }else{
                    mAuth.sendPasswordResetEmail(usereEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getBaseContext(), "A verification Link Is Send To Your Email Account.Please Check!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getBaseContext(),loginActivity.class));
                                finish();
                            }
                            else{
                                String message=task.getException().getMessage();
                                mailid.setError("Error Occured! "+message);
                            }
                        }
                    });
                }
            }
        });

        mailid.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            String usereEmail=mailid.getText().toString();
                            if(TextUtils.isEmpty(usereEmail)){
                                Toast.makeText(getBaseContext(), "Please write a valid Email Id", Toast.LENGTH_LONG).show();
                            }else{
                                mAuth.sendPasswordResetEmail(usereEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(getBaseContext(), "A verification Link Is Send To Your Email Account.Please Check!", Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(getBaseContext(),loginActivity.class));
                                            finish();
                                        }
                                        else{
                                            String message=task.getException().getMessage();
                                            mailid.setError("Error Occured! "+message);
                                        }
                                    }
                                });
                            }

                    }

                }
                return false;
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(forgotPasswordActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                Intent backIntent=new Intent(forgotPasswordActivity.this,registerActivity.class);
                startActivity(backIntent);
            }
        });




    }
}