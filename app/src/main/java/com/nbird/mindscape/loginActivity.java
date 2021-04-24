package com.nbird.mindscape;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {

    Dialog loadingDialog;
    TextInputEditText mail,password;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        password=(TextInputEditText) findViewById(R.id.password);
        mail=(TextInputEditText) findViewById(R.id.email) ;

        Button forgotPassword=(Button) findViewById(R.id.forgotpassword);
        final Button loginButton=(Button) findViewById(R.id.login);
        Button backButton=(Button) findViewById(R.id.back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForgotPassword=new Intent(loginActivity.this,welcomeActivity.class);
                startActivity(intentForgotPassword);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentForgotPassword=new Intent(loginActivity.this,forgotPasswordActivity.class);
                startActivity(intentForgotPassword);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
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

                            login();
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

                            login();
                    }
                }
                return false;
            }
        });
    }


    public void login(){
        String email=mail.getText().toString().trim();
        String password1=password.getText().toString().trim();

        if(!mail()||!password()){
            return;
        }
        loadingDialog=new Dialog(loginActivity.this);
        loadingDialog.setContentView(R.layout.loading_screen);
        loadingDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        loadingDialog.setCancelable(true);
        loadingDialog.show();

        mAuth.signInWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete( Task<AuthResult> task) {
                if(task.isSuccessful()){

                    if(mAuth.getCurrentUser().isEmailVerified()){
                            Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),mainMenuActivity.class));
                            loadingDialog.dismiss();
                            finish();
                    }else{

                        Toast.makeText(loginActivity.this, "Email Not Verified.Please Check Your Mail!", Toast.LENGTH_SHORT).show();
                        loadingDialog.dismiss();

                    }
                }else{

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