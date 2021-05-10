package com.nbird.mindscape;


import androidx.annotation.NonNull;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    Dialog loadingDialog;
    TextInputEditText mail,password;
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    String personEmail;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");
    int firstTime=0;
    int onevsoneOnlineFinder=0;
    String imageurl="https://firebasestorage.googleapis.com/v0/b/paper-wind.appspot.com/o/BydefalutPic%2Fdefaultpropic.png?alt=media&token=f655727d-9740-4ac9-9ba2-f53ea02dc778";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

                        if (task.isSuccessful()) {
                            GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                            personEmail = mail.getText().toString();
                            final SharedPreferences mailreminder = getBaseContext().getSharedPreferences("mailreminder123", 0);
                            final SharedPreferences.Editor editormailreminder = mailreminder.edit();
                            editormailreminder.putString("123", personEmail);
                            editormailreminder.commit();

                            table_user.child(mAuth.getCurrentUser().getUid()).child("personal").child("firstTime").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    try {
                                        firstTime =  snapshot.getValue(Integer.class);

                                    } catch (Exception e) {
                                        if (firstTime == 0) {
                                            table_user.child(mAuth.getCurrentUser().getUid()).child("propic").setValue(imageurl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                }
                                            });
                                            firstTime = 1;
                                            User s1 = new User(firstTime,onevsoneOnlineFinder);







                                            table_user.child(mAuth.getCurrentUser().getUid()).child("personal").setValue(s1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(loginActivity.this, mainMenuActivity.class);
                                                        intent.putExtra("firstTime", 0);
                                                        startActivity(intent);
                                                        loadingDialog.dismiss();
                                                        finish();
                                                    } else {
                                                        Toast.makeText(loginActivity.this, "Record Not Saved!", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });
                                        }
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            Toast.makeText(getBaseContext(), "Logged In Successfully!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(loginActivity.this, mainMenuActivity.class);
                            intent.putExtra("firstTime", 1);
                            startActivity(intent);
                            loadingDialog.dismiss();
                            finish();




                        } else {

                            Toast.makeText(getBaseContext(), "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            loadingDialog.dismiss();

                        }


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