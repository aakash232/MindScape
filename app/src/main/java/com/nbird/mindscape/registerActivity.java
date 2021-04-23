package com.nbird.mindscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
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
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    FirebaseUser user = auth.getCurrentUser();

                    user.sendEmailVerification()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(registerActivity.this, "A Verification Link Has been Send To Your Mail. Please Check!", Toast.LENGTH_LONG).show();

                                        loadingDialog.dismiss();

                                        FirebaseAuth.getInstance().signOut();
                                        startActivity(new Intent(getApplicationContext(),loginActivity.class));
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