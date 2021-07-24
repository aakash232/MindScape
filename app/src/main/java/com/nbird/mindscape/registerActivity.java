package com.nbird.mindscape;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class registerActivity extends AppCompatActivity {

    TextInputEditText mail,password;
    Dialog loadingDialog;
    FirebaseAuth fAuth= FirebaseAuth.getInstance();
    @Override
    public void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        password=(TextInputEditText) findViewById(R.id.password);
        mail=(TextInputEditText) findViewById(R.id.email) ;

        Button registerButton=(Button) findViewById(R.id.continuebutton);
        Button backButton=(Button) findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForgotPassword=new Intent(registerActivity.this,welcomeActivity.class);
                startActivity(intentForgotPassword);
            }
        });

        //Policy Buttons

        Button tos = (Button) findViewById(R.id.tos);
        tos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(registerActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                Intent browserintenttos = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FTERMS%20OF%20SERVICE-converted.pdf?alt=media&token=d07a0294-a15f-4c30-802b-d1ddc0a3eb31"));
                startActivity(browserintenttos);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            }
        });

        Button ps = (Button) findViewById(R.id.ps);
        ps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(registerActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                Intent browserIntentps = new Intent(Intent.ACTION_VIEW, Uri.parse("https://firebasestorage.googleapis.com/v0/b/mindscape-3a832.appspot.com/o/LegalFiles%2FPRIVACY%20STATEMENT-converted.pdf?alt=media&token=90a45a44-0844-4468-ac76-0180fc262f74"));
                startActivity(browserIntentps);
                overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
            }
        });



        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        mail.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(registerActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            signUp();
                    }
                }
                return false;
            }
        });

        password.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                final MediaPlayer musicNav;
                musicNav = MediaPlayer.create(registerActivity.this, R.raw.finalbuttonmusic);
                musicNav.start();
                musicNav.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        musicNav.reset();
                        musicNav.release();
                    }
                });
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    switch (keyCode)
                    {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:

                            signUp();
                    }
                }
                return false;
            }
        });




    }

    public void signUp(){
        if(!mail()||!password()){
            return;
        }

        loadingDialog=new Dialog(registerActivity.this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();

        final String email=mail.getText().toString().trim();
        final String password1=password.getText().toString().trim();
        fAuth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();

                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete( Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(registerActivity.this, "A Verification Link Has been Send To Your Mail. Please Check!", Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();

                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(getApplicationContext(),loginActivity.class));
                                        overridePendingTransition(R.anim.fadeinmain, R.anim.fadeoutmain);
                                        finish();
                                       

                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();

                    loadingDialog.dismiss();
                }
            }
        });
    }

    private boolean mail(){
        String name2=mail.getText().toString();
        String emailPattern=("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        if(name2.isEmpty()){
            mail.setError("Fields cannot be empty");
            return false;
        }else if(name2.length()>45){
            mail.setError("Email should be less than 45 characters");
            return false;
        }else if(!name2.matches(emailPattern)){
            mail.setError("Email should not carry such characters or spaces");
            return false;
        }
        else
            mail.setError(null);
        return true;
    }
    private boolean password(){
        String name2=password.getText().toString();
        if(name2.isEmpty()){
            password.setError("Fields cannot be empty");
            return false;
        }else if(name2.length()>18){
            password.setError("Password should be less than 18 characters");
            return false;
        }
        else
            password.setError(null);
        return true;
    }

}